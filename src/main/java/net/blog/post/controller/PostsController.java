package net.blog.post.controller;
import net.blog.post.exception.PostNotFoundException;
import net.blog.post.model.Category;
import net.blog.post.model.Posts;
import net.blog.post.model.Users;
import net.blog.post.service.CategoryService;
import net.blog.post.service.PostsService;
import net.blog.post.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import java.util.Map;

@Controller
public class PostsController {

    @Autowired
    public PostsService postsService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public UsersService usersService;
    private static Logger LOGGER = LoggerFactory.getLogger(PostsController.class);
    @GetMapping(value = {"/", "/posts"})
    public ModelAndView home(@RequestParam(value = "p", defaultValue = "1") Integer pageNo,
                             @RequestParam(defaultValue = "3") Integer pageSize,
                             @RequestParam(value = "search", defaultValue = "") String keyWord,
                             @RequestParam(value = "sort-by", defaultValue = "") String sortBy,
                             @RequestParam(value = "filter", defaultValue = "") String filterName) {
        LOGGER.info("Sending URL is working");
        ModelAndView mav = new ModelAndView("result");
        if (!keyWord.equals("")) {
            LOGGER.info("Insert in search controller");
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<Posts> listPost = postsService.search(pageable, keyWord);
            LOGGER.info("After searching result");
            if (listPost.getContent().size() == 0)
                throw new PostNotFoundException("Sorry, we couldn't find any results for: " + keyWord);
            mav.addObject("listPost", listPost.getContent());
            LOGGER.info("Searching work properly");
        } else if (sortBy.equals("published-date")) {
            LOGGER.info("Insert in published-date controller");
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<Posts> listPost = postsService.sortByPublishedDate(pageable);
            LOGGER.info("After getting result of published date");
            mav.addObject("listPost", listPost.getContent());
            LOGGER.info("published-date work successfully");
        } else if (sortBy.equals("last-updated")) {
            LOGGER.info("Insert into last-update controller");
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<Posts> listPost = postsService.sortByUpLastUpdatedDate(pageable);
            LOGGER.info("after getting result of last updated");
            mav.addObject("listPost", listPost.getContent());
            LOGGER.info("last updated work successfully");
        } else if (!filterName.equals("")) {
            LOGGER.info("Insert in Filter Controller");
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Category category = categoryService.get(filterName);
            LOGGER.info("after getting category");
            Page<Posts> listPost = postsService.findByCategory(pageable, category);
            LOGGER.info("after getting filter result");
            if (listPost.getContent().size() == 0)
                throw new PostNotFoundException("Sorry, we couldn't find any results for this filter: " + category.getName());
            mav.addObject("listPost", listPost.getContent());
            LOGGER.info("filter work successfully");
        } else {
            LOGGER.info("insert in home controller");
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<Posts> listPost = postsService.findAllByPage(pageable);
            LOGGER.info("after getting result from home controller");
            if (listPost == null)
                throw new RuntimeException("Something Went Wrong");
            mav.addObject("listPost", listPost.getContent());
            LOGGER.info("home controller work successfully");
        }
        LOGGER.info("Before returning of results");
        return mav;
    }

    @GetMapping("/posts/add")
    public String newPost(Map<String, Object> model) {
        model.put("posts", new Posts());
        return "new";
    }

    @PostMapping(value = "/posts/save")
    public String savePost(@ModelAttribute("posts") Posts posts, @RequestParam String[] name) {
        posts.getCategories().clear();
        for (String itr : name) {
            Category category = categoryService.get(itr);
            posts.getCategories().add(category);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {

            username = ((UserDetails) principal).getUsername();

        } else {

            username = principal.toString();
        }

        Users users = usersService.findByName(username);
        posts.setAuthorId(users);
        users.getPosts().add(posts);
        postsService.save(posts);
        return "redirect:/";
    }

    @GetMapping("/posts/edit/{id}")
    public ModelAndView editPosts(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editPost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @GetMapping(value = "/posts/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletePost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }


    @PostMapping("/posts/delete-confirm")
    public String deletePost(@RequestParam int id) {
        postsService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout-success")
    public String logoutPage() {
        return "redirect:/";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/sign-up-success")
    public String singUPDone(@RequestParam(value = "name") String name,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password) {
        Users users = new Users();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptPass = passwordEncoder.encode(password);
        users.setName(name);
        users.setEmail(email);
        users.setPassword(encryptPass);
        users.setRole("author");
        usersService.save(users);
        return "login";
    }
    @GetMapping(value = "/error")
    public String defaultErrorMessage() {
        return "error";
    }

    @GetMapping(value = "/posts/view/{id}")
    public ModelAndView postView(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("viewPost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("viewPost", posts);
        System.out.println(posts.getTitle());
        return modelAndView;
    }

}

