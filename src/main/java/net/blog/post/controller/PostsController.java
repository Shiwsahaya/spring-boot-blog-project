package net.blog.post.controller;
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
import java.util.List;
import org.springframework.data.domain.Pageable;
import javax.servlet.ServletRequest;
import java.util.Map;


@Controller
public class PostsController {

    @Autowired
    public PostsService postsService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public UsersService usersService;

    private static Logger logger= LoggerFactory.getLogger(PostsController.class);
    @GetMapping(value = {"/", "/posts"})
    public ModelAndView home() {
        logger.debug("debug info");
        logger.info("insert in home");
        ModelAndView mav = new ModelAndView("result");
        Pageable pageable = PageRequest.of(0, 3);
        Page<Posts> listPost = postsService.findAllByPage(pageable);
        mav.addObject("listPost", listPost.getContent());
        return mav;
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout-success")
    public String logoutPage() {
        return "redirect:/";
    }

    @RequestMapping("/page/{page-no}")
    public ModelAndView fetchByPage(@PathVariable("page-no") int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 3);
        ModelAndView modelAndView = new ModelAndView("result");
        Page<Posts> allPost = postsService.findAllByPage(pageable);
        modelAndView.addObject("listPost", allPost.getContent());
        return modelAndView;
    }


    @GetMapping("/add")
    public String newPost(Map<String, Object> model) {
        model.put("posts", new Posts());
        return "new";
    }

    @PostMapping(value = "/save")
    public String savePost(@ModelAttribute("posts") Posts posts, @RequestParam String[] name) {
        posts.getCategories().clear();
        for (String itr : name) {
            Category category = categoryService.get(Integer.parseInt(itr));
            posts.getCategories().add(category);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {

            username = ((UserDetails) principal).getUsername();

        } else {

            username = principal.toString();
        }

        Users users=usersService.findByName(username);
        posts.setAuthorId(users);
        users.getPosts().add(posts);
        postsService.save(posts);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPosts(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editPost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletePost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @PostMapping("/delete/delete-confirm")
    public String deletePost(@RequestParam int id) {
        postsService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/published-date")
    public ModelAndView sortByPublishDate() {
        ModelAndView modelAndView = new ModelAndView("result");
        List<Posts> listPost = postsService.sortByPublishedDate();
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @GetMapping("/last-updated")
    public ModelAndView sortByLastUpdatedDate() {
        ModelAndView modelAndView = new ModelAndView("result");
        List<Posts> listPost = postsService.sortByUpLastUpdatedDate();
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @GetMapping("/posts/search")
    public ModelAndView search(@RequestParam String keyword) {
        ModelAndView modelAndView = new ModelAndView("result");
        logger.error("error generated");
        logger.info("search executed");
        logger.trace("search trace");
        List<Posts> listPost = postsService.search(keyword);
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @RequestMapping("/filter/{categoryId}")
    public ModelAndView filter(@PathVariable("categoryId") int categoryId) {
        List<Posts> listPost = null;
        ModelAndView modelAndView = new ModelAndView("result");
        Category category = categoryService.get(categoryId);
        listPost = category.getPosts();
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/sign-up-success")
    public String singUPDone(ServletRequest request) {
        Users users =new Users();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptPass=passwordEncoder.encode(password);
        users.setName(name);
        users.setEmail(email);
        users.setPassword(encryptPass);
        users.setRole("author");
        usersService.save(users);
        return "login";
    }

}