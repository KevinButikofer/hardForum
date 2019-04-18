package com.hardforum.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hardforum.models.Role;
import com.hardforum.models.SubForum;
import com.hardforum.models.User;
import com.hardforum.repository.RoleRepository;
import com.hardforum.repository.SubForumRepository;
import com.hardforum.repository.UserRepository;

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


