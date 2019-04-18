package com.hardforum.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hardforum.models.Post;
import com.hardforum.repository.PostRepository;


@Service("postService")
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Post findPostById(int id) {
		return postRepository.findById(id);
	}

	@Override
	public void savePost(Post post) {
		postRepository.save(post);
	}

	@Override
	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}

}
