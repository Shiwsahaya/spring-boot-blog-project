package net.blog.post.controller;

import net.blog.post.model.Category;
import net.blog.post.model.Posts;

import net.blog.post.model.Users;
import net.blog.post.service.CategoryService;
import net.blog.post.service.PostsService;

import net.blog.post.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
    public PostsService postsService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public UsersService usersService;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("result");
        Pageable pageable = PageRequest.of(0, 3);
        Page<Posts> listPost = postsService.findAllByPage(pageable);
        mav.addObject("listPost", listPost.getContent());
        return mav;
    }
    @RequestMapping("/page/{page-no}")
    public ModelAndView fetchByPage(@PathVariable("page-no") int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 3);
        ModelAndView modelAndView = new ModelAndView("result");
        Page<Posts> allPost = postsService.findAllByPage(pageable);
        modelAndView.addObject("listPost", allPost.getContent());
        return modelAndView;
    }

    @RequestMapping("/add")
    public String newPost(Map<String, Object> model) {
        model.put("posts", new Posts());
        return "new";
    }
//
//    @RequestMapping(value = "/edit/save", method = RequestMethod.POST)
//    public String editSave(@ModelAttribute("posts") Posts posts,@RequestParam String[] name) {
//        posts.getCategories().clear();
//        for (String itr : name) {
//            Category category = categoryService.get(Integer.parseInt(itr));
//            posts.getCategories().add(category);
//
//        }
//        Users users=usersService.get(127);
//        posts.setAuthorId(users);
//        postsService.save(posts);
//        System.out.println(users.getId());
//        users.getPosts().add(posts);
////        postsService.save(posts);
//        usersService.save(users);
//        return "redirect:/";
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("posts") Posts posts, @RequestParam String[] name) {
        posts.getCategories().clear();
        for (String itr : name) {
            Category category = categoryService.get(Integer.parseInt(itr));
            posts.getCategories().add(category);

        }

        Users users = usersService.get(1);
        posts.setAuthorId(users);
        System.out.println(users.getId());
        users.getPosts().add(posts);
        postsService.save(posts);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editPosts(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("editPost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletePost");
        Posts posts = postsService.get(id);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping("/delete/delete-confirm")
    public String deletePost(@RequestParam int id) {
        System.out.println("delete id: " + id);
        postsService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/sort-by-published-date")
    public ModelAndView sortByPublishDate() {
        ModelAndView modelAndView = new ModelAndView("result");
        List<Posts> listPost = postsService.sortByPublishedDate();
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @RequestMapping("/sort-by-last-updated")
    public ModelAndView sortByLastUpdatedDate() {
        ModelAndView modelAndView = new ModelAndView("result");
        List<Posts> listPost = postsService.sortByUpLastUpdatedDate();
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        ModelAndView modelAndView = new ModelAndView("search");
        List<Posts> listPost = postsService.search(keyword);
        modelAndView.addObject("listPost", listPost);
        System.out.println(listPost.get(0).getTitle());
        return modelAndView;
    }

    @RequestMapping("/filter/{categoryId}")
    public ModelAndView filter(@PathVariable("categoryId") int categoryId) {
        List<Posts> listPost = null;
        ModelAndView modelAndView = new ModelAndView("filter");
        Category category = categoryService.get(categoryId);
        listPost = category.getPosts();
        modelAndView.addObject("listPost", listPost);
        return modelAndView;
    }

}