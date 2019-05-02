package com.hardforum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hardforum.services.SubForumService;
import com.hardforum.services.TopicService;

@Controller
public class HomeController {
		
	@Autowired
	private TopicService topicService;

	@Autowired
	private SubForumService subforumService;	
    
    @GetMapping("/")
    public String home() {
    	return "redirect:/forum";

    }
    	
    @PostMapping(value = "/search")
    public ModelAndView search (@RequestParam String searchText, Model model) {
    		ModelAndView modelAndView = new ModelAndView("search");
           modelAndView.addObject("topics", topicService.findByNameContaining(searchText));
           modelAndView.addObject("searchText", searchText);

           return modelAndView;      
    }
    
    /*@RequestMapping(value = "/search/{search}")
    public String handleTestRequest (@PathVariable("search") String search, Model model) {
        
           model.addAttribute("searchName", search);
           return "search";
        
    }*/
    
    @GetMapping(value = "/advancedSearch")
    public ModelAndView advancedSearch (Model model) {
		ModelAndView modelAndView = new ModelAndView("/advancedSearch");
		modelAndView.addObject("subforums", subforumService.findAll());
		return modelAndView;

    }
    
    @PostMapping(value = "/advancedSearch")
    public ModelAndView handleAdvancedSearch (@RequestParam String topicName, @RequestParam String authorName, @RequestParam int subforums, Model model) {
    		ModelAndView modelAndView = new ModelAndView("search");
    		modelAndView.addObject("topics", topicService.find(topicName, authorName, subforums));
           return modelAndView;      
    }
    
   


}
