package com.hardforum.controllers;


import java.awt.Image;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hardforum.services.PostService;
import com.hardforum.services.RoleService;
import com.hardforum.services.TopicService;
import com.hardforum.services.UserService;
import com.hardforum.models.Post;
import com.hardforum.models.Role;
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
	@Autowired
	private RoleService roleService;	
    
    
    @GetMapping("/forum")
    public String forum(Map<String, Object> model) {
    	List<Map.Entry<String, String>> links = new ArrayList<>();
    	links.add(new AbstractMap.SimpleEntry<String, String>("Forum", ""));
    	Iterable<User> users = userService.findUserByRole(roleService.findByName("MOD"));
    	users.forEach(x -> System.out.println(x.getName()));
    	List<Topic> topicsAside = topicService.findFirst10ByOrderByNameAsc();
    	model.put("topicsAside", topicsAside);
    	model.put("moderators", users);
    	model.put("links", links);
		model.put("subforum", new SubForum() );
		model.put("subforums", subForumService.findAll());
        return "forum";
    }
    @GetMapping("/forum/{categoryName}/topic/{id}")
    public String post(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, Map<String, Object> model) {
    	return "redirect:/forum/"+ categoryName + "/topic/" + id + "/page/1";
    }
    @GetMapping("/forum/{categoryName}/topic/{id}/page/{page}")
    public ModelAndView postPage(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, @PathVariable("page") int page, Map<String, Object> model) {
      
    	Topic topic = topicService.findTopicById(id);
    	SubForum subForum = subForumService.findSubForumByName(categoryName);
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());	
		boolean hasModRight =  (subForum.getSubForum_admin() == user || user.getRoles().contains(roleService.findByName("ADMIN"))) && user != null;
		
    	List<Topic> topicsAside = topicService.findFirst10ByOrderByNameAsc();
    	
    	
    	List<Map.Entry<String, String>> links = new ArrayList<>();
    	links.add(new AbstractMap.SimpleEntry<String, String>("Forum", "/forum"));
    	links.add(new AbstractMap.SimpleEntry<String, String>(categoryName, "/forum" + "/" + categoryName));
    	links.add(new AbstractMap.SimpleEntry<String, String>(topic.getName(), ""));
		
		ModelAndView modelAndView = new ModelAndView();
		PageRequest pageable = PageRequest.of(page - 1, 5);
		Page<Post> postPage = postService.getPaginatedPostByTopic(pageable, topic);
        int totalPages = postPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        if(page < totalPages)
        {
        	modelAndView.addObject("next", true);
        }
        if(page == 1)
        {
        	modelAndView.addObject("previous", false);
        }
        else
        {
        	modelAndView.addObject("previous", true);
        }				
		
        System.out.println("MOD " + hasModRight);
        modelAndView.addObject("topicsAside", topicsAside);
        modelAndView.addObject("hasModRight", hasModRight);
        modelAndView.addObject("links", links);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("topic", topic);
        modelAndView.addObject("posts", postPage.getContent());
        modelAndView.addObject("subForum", subForum);
        
        
        modelAndView.setViewName("topic");
	   return modelAndView;
    }
    @PostMapping(value = "/forum/{categoryName}/topic/{id}")
    public String addPost(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, @Valid @ModelAttribute Post post, BindingResult bindingResult,Map<String, Object> model) {    	
    	if(post.getMessage().length() < 2000000)
    	{
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
    	}
    	return "redirect:/forum/"+ categoryName +"/topic/" + post.getTopic().getId();
    }
    @PostMapping(value = "/forum/{categoryName}/topic/{id}/removeTopic")
    public String removeTopic(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, Map<String, Object> model) {    	
    	
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    topicService.removeById(id);
      
    	return "redirect:/forum/"+ categoryName;
    }
    @PostMapping(value = "/forum/{categoryName}/topic/{id}/removePost/{postId}")
    public String removePost(@PathVariable("categoryName") String categoryName, @PathVariable("id") int id, @PathVariable("postId") int postId, Map<String, Object> model) {    	
    	
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    postService.removeById(postId);
      
	    return "redirect:/forum/"+ categoryName +"/topic/" + id;
    }
    
    @GetMapping("/forum/{categoryName}/addTopic")
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
    @PostMapping(value = "/forum/{categoryName}/addTopic")
    public String addTopic(@Valid @ModelAttribute Topic topic, @PathVariable("categoryName") String categoryName, BindingResult bindingResult,Map<String, Object> model) {    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());		
        topic.setSubForum(subForumService.findSubForumByName(categoryName));
        topic.setAuthor(user);
        topicService.saveTopic(topic);

        
        return "redirect:/forum/" + categoryName + "/topic/" + topic.getId();
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
		System.out.println(user.getName());
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("myProfile");
	   return modelAndView;
    }
    @PostMapping("/myprofile")
    public ModelAndView UpdateUser(@ModelAttribute User user, @RequestParam("imageFile") MultipartFile image, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findUserByName(auth.getName());
        if (currentUser != null) {
        	
        	currentUser.setAge(user.getAge());
        	currentUser.setBio(user.getBio());
        	currentUser.setSignature(user.getSignature());
        	
        	try {
        		currentUser.setImage(image.getBytes());
			} catch (IOException e) {
				modelAndView.setViewName("myProfile");
				return modelAndView;
			}
        	
        	userService.saveUser(currentUser);         	
        	

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
    @RequestMapping(value = "/{userId}/image")
    @ResponseBody
    public byte[] userImage(@PathVariable int userId)  {       
      return userService.findUserById(userId).getImage();
    }
    
    @PostMapping("/forum/addSubforum")
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
	    	
	    	return new ModelAndView("redirect:/forum"+ subforum.getName());

        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/forum/{name}")
    public String handleTestRequest (@PathVariable("name") String name, Model model) {
    	return "redirect:/forum/"+ name +"/page/1";
        
    }
    @RequestMapping(value = "/forum/{name}/page/{page}")
    public ModelAndView listArticlesPageByPage(@PathVariable("page") int page, @PathVariable("name") String name) {
    	
    	SubForum subForum = subForumService.findSubForumByName(name);
    	
    	
        ModelAndView modelAndView = new ModelAndView("subForum");
        
        Map<String, String> linkMap = new HashMap<>();
    	linkMap.put("Forum", "/forum");
    	
    	List<Map.Entry<String, String>> links = new ArrayList<>();
    	links.add(new AbstractMap.SimpleEntry<String, String>("Forum", "/forum"));
    	links.add(new AbstractMap.SimpleEntry<String, String>(name, ""));
    	List<Topic> topicsAside = topicService.findFirst10ByOrderByNameAsc();
    	
        
        PageRequest pageable = PageRequest.of(page - 1, 10);
        Page<Topic> topicPage = topicService.getPaginatedTopicsBySubForum(pageable, subForum);
        int totalPages = topicPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
            System.out.println(pageNumbers);
        }
        if(page < totalPages)
        {
        	modelAndView.addObject("next", true);
        }
        if(page == 1)
        {
        	modelAndView.addObject("previous", false);
        }
        else
        {
        	modelAndView.addObject("previous", true);
        }

        Topic t = new Topic();
        modelAndView.addObject("topicsAside", topicsAside);
        modelAndView.addObject("links", links);
        modelAndView.addObject("topic", t);
        modelAndView.addObject("categoryName", name);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("topics", topicPage.getContent());
        return modelAndView;
    }

}
