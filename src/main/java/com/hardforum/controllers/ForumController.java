package com.hardforum.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hardforum.services.SubForumService;
import com.hardforum.services.UserService;
import com.hardforum.models.SubForum;
import com.hardforum.models.User;
import com.hardforum.repository.SubForumRepository;

@Controller
public class ForumController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubForumService subForumService;	
    
    @GetMapping("/forum")
    public String forum(Map<String, Object> model) {
		model.put("subforum", new SubForum() );
		model.put("subforums", subForumService.findAll());
        return "forum";
    }
    @GetMapping("/myprofile")
    public String profile(Map<String, Object> model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());
		model.put("user", user);
		
        return "myProfile";
    }
    
    @PostMapping("/addSubforum")
    public ModelAndView greetingSubmit(@ModelAttribute SubForum subforum, Model model, BindingResult bindingResult ) {
        ModelAndView modelAndView = new ModelAndView();

        SubForum subforumExists = subForumService.findSubForumByName(subforum.getName());
        if (subforumExists != null) {
            bindingResult
                    .rejectValue("name", "errorsubforum",
                            "There is already a user registered with the name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("forum");
            modelAndView.addObject("subforum", new SubForum());
            modelAndView.addObject("error", "Subforum already existing");

        } else {
	    	subForumService.saveSubForum(subforum);
	    	model.addAttribute("name", subforum.getName());
	    	
	    	return new ModelAndView("redirect:/"+subforum.getName());

        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/{name}")
    public String handleTestRequest (@PathVariable("name") String name, Model model) {
        
           model.addAttribute("categoryName", name);
           return "subForum";
        
    }

}
