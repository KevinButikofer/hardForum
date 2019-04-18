package com.hardforum.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hardforum.models.Topic;
import com.hardforum.repository.TopicRepository;


@Service("topicService")
public class TopicServiceImpl implements TopicService{

	@Autowired
	private TopicRepository topicRepository;
	
	@Override
	public Topic findTopicById(int id) {
		return topicRepository.findById(id);
	}

	@Override
	public void saveTopic(Topic topic) {
		topicRepository.save(topic);
	}

	@Override
	public Iterable<Topic> findAll() {
		return topicRepository.findAll();
	}

}