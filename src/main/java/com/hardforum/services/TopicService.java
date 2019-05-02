package com.hardforum.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hardforum.models.Post;
import com.hardforum.models.SubForum;
import com.hardforum.models.Topic;

public interface TopicService {
	public Topic findTopicById(int email);
	public void saveTopic(Topic topic);
	public Iterable<Topic> findAll();
	public List<Topic> findByNameContaining(String name);
	public List<Topic> findFirst10ByOrderByUpdateDateTimeDesc();

	public List<Topic> findTopicBySubForum(SubForum subForum);
	public Integer findTopicPostNumber();
	public List<Topic> find(String topicName, String authorName, int categoryID);
	public Page<Topic> getPaginatedTopicsBySubForum(Pageable pageable, SubForum subForum);
	public long removeById(int id);
}
