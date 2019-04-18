package com.hardforum.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hardforum.services.UserService;
import com.hardforum.models.User;

@Controller
public class ForumController {
	
	@Autowired
	private UserService userService;
	
    
    @GetMapping("/forum")
    public String forum() {
        return "forum";
    }
    @GetMapping("/myprofile")
    public String profile(Map<String, Object> model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());
		model.put("user", user);
		
        return "myProfile";
    }
    

}
