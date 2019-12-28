package net.blog.post.controller;
import net.blog.post.model.Category;
import net.blog.post.model.Posts;
import net.blog.post.service.CategoryService;
import net.blog.post.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.data.domain.Pageable;

import java.util.Map;


@Controller
public class PostsController {

    @Autowired
    public PostsService service;
    @Autowired
    public CategoryService categoryService;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("result");
        List<Posts> listCustomer = service.listAll();
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }


    @RequestMapping("/add")
    public String newPost(Map<String, Object> model) {
        model.put("posts", new Posts());
        return "new";
    }

    @RequestMapping(value = "/edit/save", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("posts") Posts posts) {
        service.save(posts);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("posts") Posts posts, @RequestParam String[] name) {
        for (String itr : name) {
            Category category = categoryService.get(Integer.parseInt(itr));
            posts.addCategory(category);
            service.save(posts);
        }

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editPosts(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editPost");
        Posts posts = service.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletePost");
        Posts posts = service.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping("/delete/delete-confirm")
    public String deletePost(@RequestParam int id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/sort-by-published-date")
    public ModelAndView sortByPublishDate() {
        ModelAndView modelAndView = new ModelAndView("result");
        List<Posts> listCustomer = service.sortByPublishedDate();
        modelAndView.addObject("listCustomer", listCustomer);
        return modelAndView;
    }

    @RequestMapping("/sort-by-last-updated")
    public ModelAndView sortByLastUpdatedDate() {
        ModelAndView modelAndView = new ModelAndView("result");
        List<Posts> listCustomer = service.sortByUpLastUpdatedDate();
        modelAndView.addObject("listCustomer", listCustomer);
        return modelAndView;
    }

    @RequestMapping("/page")
    public ModelAndView fetchByPage() {
        Pageable pageable = PageRequest.of(0, 3);
        ModelAndView modelAndView = new ModelAndView("result");
        Page<Posts> allPost = service.findAllByPage(pageable);
        modelAndView.addObject("listCustomer", allPost.getContent());
        return modelAndView;
    }


}