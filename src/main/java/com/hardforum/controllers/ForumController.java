package com.hardforum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hardforum.repository.UserRepository;
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
    

}
