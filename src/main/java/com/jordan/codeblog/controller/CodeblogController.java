package com.jordan.codeblog.controller;

import com.jordan.codeblog.model.Post;
import com.jordan.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

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
    
    @GetMapping(value = "/newpost")
    public String getPostForm() {
    	return "postForm";
    }
    
    @PostMapping(value = "/newpost")
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
    	
    	if (result.hasErrors()) {
    		attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
    	
    	post.setData(LocalDate.now());
    	codeblogService.save(post);
    	
    	return "redirect:/posts";
    }
}
