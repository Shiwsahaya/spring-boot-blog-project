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
    public ModelAndView home(@RequestParam(value ="p",  defaultValue = "1")Integer pageNo,
                             @RequestParam(defaultValue = "3")Integer pageSize) {
        logger.debug("debug info");
        logger.info("insert in home");
        ModelAndView mav = new ModelAndView("result");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        Page<Posts> listPost = postsService.findAllByPage(pageable);
        mav.addObject("listPost", listPost.getContent());
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


    @GetMapping("/posts/published-date")
    public ModelAndView sortByPublishDate(@RequestParam(value ="p",  defaultValue = "1")Integer pageNo,
                                          @RequestParam(defaultValue = "3")Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView("result");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        Page<Posts> listPost = postsService.sortByPublishedDate(pageable);
        modelAndView.addObject("listPost", listPost.getContent());
        return modelAndView;
    }

    @GetMapping("/posts/last-updated")
    public ModelAndView sortByLastUpdatedDate(@RequestParam(value ="p",  defaultValue = "1")Integer pageNo,
                                              @RequestParam(defaultValue = "3")Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView("result");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        Page<Posts> listPost = postsService.sortByUpLastUpdatedDate(pageable);
        modelAndView.addObject("listPost", listPost.getContent());
        return modelAndView;
    }

    @GetMapping("/posts/search")
    public ModelAndView search(@RequestParam String keyword) {
        ModelAndView modelAndView = new ModelAndView("result");
        logger.error("error generated");
        logger.info("search executed");
        logger.trace("search trace");
        List<Posts>listPost=null;
        try{
            listPost = postsService.search(keyword);
        }
        catch (Exception ex)
        {
            System.out.println("Error in Search");
        }

        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @RequestMapping("/posts/filter/{categoryId}")
    public ModelAndView filter(@PathVariable("categoryId") int categoryId) {
        List<Posts> listPost = null;
        ModelAndView modelAndView = new ModelAndView("result");
        Category category = categoryService.get(categoryId);
        listPost=postsService.findByCategory(category);
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/sign-up-success")
    public String singUPDone(@RequestParam(value = "name") String name,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "password")String password) {
        Users users =new Users();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptPass=passwordEncoder.encode(password);
        users.setName(name);
        users.setEmail(email);
        users.setPassword(encryptPass);
        users.setRole("author");
        usersService.save(users);
        return "login";
    }

    @GetMapping(value = "/error")
    public String defaultErrorMessage(){
        return "error";
    }

    @GetMapping(value = "/posts/view/{id}")
    public ModelAndView postView(@PathVariable("id") int id){
        ModelAndView modelAndView=new ModelAndView("viewPost");
        Posts posts=postsService.get(id);
        modelAndView.addObject("viewPost",posts);
        System.out.println(posts.getTitle());
        return modelAndView;
    }

}