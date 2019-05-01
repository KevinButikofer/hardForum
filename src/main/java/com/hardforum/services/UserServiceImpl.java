package com.hardforum.services;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hardforum.WebSecurityConfig;
import com.hardforum.models.Role;
import com.hardforum.models.User;
import com.hardforum.repository.RoleRepository;
import com.hardforum.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User findUserByName(String name) {
		return userRepository.findByName(name);
	}
	@Override
	public User findUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public void saveUser(User user) {
		user.setActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        Role userRole = roleRepository.findByRole("MOD");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public List<User> findUserByRole(Role role) {
		return userRepository.findByRoles(role);
	}

}
