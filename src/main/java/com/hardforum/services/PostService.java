package com.hardforum.services;

import com.hardforum.models.Post;

public interface PostService {
	public Post findPostById(int id);
	public void savePost(Post post);
	public Iterable<Post> findAll();
}
