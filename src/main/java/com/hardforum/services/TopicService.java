package com.hardforum.services;

import com.hardforum.models.Topic;

public interface TopicService {
	public Topic findTopicById(int email);
	public void saveTopic(Topic topic);
	public Iterable<Topic> findAll();
}
