package com.hardforum.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hardforum.models.Role;
import com.hardforum.repository.RoleRepository;


@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role findByName(String name) {
		return roleRepository.findByRole(name);
	}



}
