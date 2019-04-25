package com.hardforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardforum.models.Post;
import com.hardforum.models.Topic;


@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	Post findById(int post_id);
	List<Post> findByTopic(Topic topic);

}
