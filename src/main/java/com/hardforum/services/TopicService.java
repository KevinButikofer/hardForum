package com.hardforum.services;

import java.util.List;

import com.hardforum.models.Post;
import com.hardforum.models.SubForum;
import com.hardforum.models.Topic;

public interface TopicService {
	public Topic findTopicById(int email);
	public void saveTopic(Topic topic);
	public Iterable<Topic> findAll();
	public List<Topic> findByNameContaining(String name);
	public List<Topic> findTopicBySubForum(SubForum subForum);
	public Integer findTopicPostNumber();
}
