package com.hardforum.controllers;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hardforum.services.PostService;
import com.hardforum.services.TopicService;
import com.hardforum.services.UserService;
import com.hardforum.models.Post;
import com.hardforum.models.SubForum;
import com.hardforum.models.Topic;
import com.hardforum.models.User;

@Controller
public class ForumController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private PostService postService;
	
    @GetMapping("/forum")
    public String forum() {
        return "forum";
    }
    @GetMapping("/{categoryName}/topic/{id}")
    public ModelAndView post(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, Map<String, Object> model) {
    	Topic topic = topicService.findTopicById(id);
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());		
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", new Post());
        
        modelAndView.setViewName("topic");
	   return modelAndView;
    }
    @PostMapping(value = "/addPost")
    public String addPost(@Valid @ModelAttribute Post post, BindingResult bindingResult,Map<String, Object> model) {    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //postService.savePost(post);
        
        model.put("message", post.getMessage());
        return "message";
        //return "redirect:/test/topic/" + post.getTopic().getId();
    }
    @GetMapping("/myprofile")
    public ModelAndView profile(Map<String, Object> model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("myProfile");
	   return modelAndView;
    }
    @PostMapping(value = "/myprofile")
    public ModelAndView UpdateUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findUserByName(auth.getName());
        User userExists = userService.findUserById(currentUser.getId());
        if (userExists != null) {
        	userExists.setAge(user.getAge());
        	userExists.setBio(user.getBio());
        	userService.saveUser(userExists);   

            modelAndView.addObject("successMessage", "Profile has been update successfully");
            modelAndView.setViewName("myProfile");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("myProfile");
        } else {
        	   bindingResult
               .rejectValue("email", "error.user",
                       "There is already no user registered with the name provided");        

        }
        return modelAndView;
    }
    

}
