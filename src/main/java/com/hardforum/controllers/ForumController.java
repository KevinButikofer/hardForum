package com.hardforum.controllers;


import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hardforum.services.PostService;
import com.hardforum.services.TopicService;
import com.hardforum.services.UserService;
import com.hardforum.models.Post;
import com.hardforum.models.SubForum;
import com.hardforum.models.Topic;
import com.hardforum.models.User;

import org.springframework.ui.Model;
import com.hardforum.services.SubForumService;

@Controller
public class ForumController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private PostService postService;	
	@Autowired
	private SubForumService subForumService;	
    
    @GetMapping("/forum")
    public String forum(Map<String, Object> model) {
		model.put("subforum", new SubForum() );
		model.put("subforums", subForumService.findAll());
        return "forum";
    }
    @GetMapping("/{categoryName}/topic/{id}")
    public ModelAndView post(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, Map<String, Object> model) {
    	Topic topic = topicService.findTopicById(id);
    	SubForum subForum = subForumService.findSubForumByName(categoryName);

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());		

        List<Post> posts= postService.findPostByTopic(topic);
		
		ModelAndView modelAndView = new ModelAndView();
		
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("topic", topic);
        modelAndView.addObject("posts", posts);
        
        modelAndView.setViewName("topic");
	   return modelAndView;
    }
    @PostMapping(value = "/{categoryName}/topic/{id}")
    public String addPost(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, @Valid @ModelAttribute Post post, BindingResult bindingResult,Map<String, Object> model) {    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());		
        Topic topic = topicService.findTopicById(id);
        
        post.setAuthor(user);
        post.setTopic(topic);
        user.setNbPostedMessage(user.getNbPostedMessage() + 1);
        topic.setNbPostedMessage(topic.getNbPostedMessage() + 1);
        postService.savePost(post);
        topicService.saveTopic(topic);
        userService.saveUser(user);
        
        return "redirect:/"+ categoryName +"/topic/" + post.getTopic().getId();
    }
    
    @GetMapping("/{categoryName}/addTopic")
    public ModelAndView post(@PathVariable("categoryName") String categoryName, Map<String, Object> model) {
    	SubForum s = subForumService.findSubForumByName(categoryName);
    	Iterable<SubForum> subforums = subForumService.findAll();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());	
		
		Topic t = new Topic();
		//t.setSubForum(s);
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("topic", t);
        
        modelAndView.setViewName("addtopic");
	   return modelAndView;
    }
    @PostMapping(value = "/{categoryName}/addTopic")
    public String addTopic(@Valid @ModelAttribute Topic topic, @PathVariable("categoryName") String categoryName, BindingResult bindingResult,Map<String, Object> model) {    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());		
        topic.setSubForum(subForumService.findSubForumByName(categoryName));
        topic.setAuthor(user);
        topicService.saveTopic(topic);
        
        return "redirect:/" + categoryName + "/topic/" + topic.getId();
    }
    
    @GetMapping("/users/{username}")
    public ModelAndView profile(@PathVariable("username") String userName, Map<String, Object> model) {
		User user = userService.findUserByName(userName);		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profile");
	   return modelAndView;
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
    
    @RequestMapping(value = "/forum/{name}")
    public String handleTestRequest (@PathVariable("name") String name, Model model) {
    	SubForum subForum = subForumService.findSubForumByName(name);
    	model.addAttribute("topics", topicService.findTopicBySubForum(subForum));
        model.addAttribute("categoryName", name);
        return "subForum";
        
    }

}
