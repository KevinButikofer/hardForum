package com.hardforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardforum.models.Post;


@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	Post findById(int post_id);

}
