package com.hardforum.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hardforum.models.Post;
import com.hardforum.models.Topic;

public interface PostService {
	public Post findPostById(int id);
	public void savePost(Post post);
	public Iterable<Post> findAll();
	public List<Post> findPostByTopic(Topic topic);
	public Page<Post> getPaginatedPostByTopic(Pageable pageable, Topic topic);
	public long removeById(int id);
}
