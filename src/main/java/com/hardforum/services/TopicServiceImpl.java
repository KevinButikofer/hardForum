package com.hardforum.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hardforum.models.SubForum;
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

	@Override
	public List<Topic> findByNameContaining(String name) {
		
		return topicRepository.findByNameContaining(name);
	}
	
	@Override
	public List<Topic> find(String topicName, String authorName, int categoryID) {
		
		return topicRepository.find(topicName, authorName, categoryID);
	}

	@Override
	public List<Topic> findTopicBySubForum(SubForum subForum) {
		return topicRepository.findBySubForumOrderByUpdateDateTimeDesc(subForum);
	}

	@Override
	public Integer findTopicPostNumber() {
		return topicRepository.findPostNumber();
	}
	@Override
    public Page<Topic> getPaginatedTopicsBySubForum(Pageable pageable, SubForum subForum) {
        return topicRepository.findBySubForumOrderByUpdateDateTimeDesc(pageable, subForum);
    }
	@Override
	public long removeById(int id) {
		return topicRepository.removeById(id);
	}

	@Override
	public List<Topic> findFirst10ByOrderByUpdateDateTimeDesc() {
		
		return topicRepository.findFirst10ByOrderByUpdateDateTimeDesc();
	}
	
	
}
