package com.hardforum.services;

import java.util.List;

import com.hardforum.models.Post;
import com.hardforum.models.Topic;

public interface PostService {
	public Post findPostById(int id);
	public void savePost(Post post);
	public Iterable<Post> findAll();
	public List<Post> findPostByTopic(Topic topic);
}
