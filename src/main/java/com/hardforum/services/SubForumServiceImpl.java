package com.hardforum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hardforum.models.SubForum;
import com.hardforum.repository.SubForumRepository;

@Service("subForumService")
public class SubForumServiceImpl implements SubForumService{

	@Autowired
	private SubForumRepository subForumRepository;	
	
	@Override
	public SubForum findSubForumByName(String name) {
		return subForumRepository.findByName(name);
	}



	@Override
	public Iterable<SubForum> findAll() {
		return subForumRepository.findAll();

	}



	@Override
	public void saveSubForum(SubForum subforum) {
		subForumRepository.save(subforum);
		
	}
	
}


