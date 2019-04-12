package com.hardforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardforum.models.SubForum;


@Repository("subforumRepository")
public interface SubForumRepository extends JpaRepository<SubForum, Integer>{
	
	SubForum findByName(String name);

}
