package com.hardforum.services;
import com.hardforum.models.SubForum;

public interface SubForumService {
	public SubForum findSubForumByName(String name);
	public void saveSubForum(SubForum subforum);
	public Iterable<SubForum> findAll();
}


