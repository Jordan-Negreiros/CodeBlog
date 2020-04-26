package com.jordan.codeblog.controller;

import com.jordan.codeblog.model.Post;
import com.jordan.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CodeblogController {

    @Autowired
    private CodeblogService codeblogService;

    @GetMapping(value = "/posts")
    public ModelAndView getPosts() {

        ModelAndView andView = new ModelAndView("posts");
        List<Post> posts = codeblogService.findAll();

        andView.addObject("posts", posts);

        return andView;
    }
    
    @GetMapping(value = "/posts/{id}")
    public ModelAndView getPostsDetails(@PathVariable("id") long id) {
    	
    	ModelAndView andView = new ModelAndView("postDetails");
    	Post post = codeblogService.findById(id);
    	
    	andView.addObject("post", post);
    	
    	return andView;
    }
}
