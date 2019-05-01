package com.hardforum.services;

import java.util.List;

import com.hardforum.models.Role;
import com.hardforum.models.User;

public interface UserService {
	public User findUserByName(String email);
	public void saveUser(User user);
	public Iterable<User> findAll();
	public User findUserById(int id);
	public List<User> findUserByRole(Role role);
}
