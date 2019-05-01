package com.hardforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardforum.models.Role;
import com.hardforum.models.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByName(String name);
	User findById(int id);
	List<User> findByRoles(Role role);
}