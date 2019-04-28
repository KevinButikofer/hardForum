package com.hardforum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hardforum.models.Post;
import com.hardforum.models.Topic;


@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	Post findById(int post_id);
	List<Post> findByTopic(Topic topic);
    Page<Post> findAllByTopic(Pageable pageable, Topic topic);
    @Transactional
    Long removeById(int post_id);
    


}
