package com.hardforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardforum.models.Topic;


@Repository("topicRepository")
public interface TopicRepository extends JpaRepository<Topic, Integer>{
	
	Topic findById(int topic_id);
	List<Topic> findByNameContaining(String name);

}
