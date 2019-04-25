package com.hardforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hardforum.models.Post;
import com.hardforum.models.SubForum;
import com.hardforum.models.Topic;


@Repository("topicRepository")
public interface TopicRepository extends JpaRepository<Topic, Integer>{
	
	Topic findById(int topic_id);
	List<Topic> findByNameContaining(String name);
	List<Topic> findBySubForum(SubForum subForum);
	@Query( value = "select COUNT(*) from topic t where t.sub_forum_id=?", nativeQuery = true)
	Integer findPostNumber();
}
