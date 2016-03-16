package com.seashells.manager;

import java.util.List;

import com.seashells.entity.UserEntity;
import com.seashells.model.Creator;

public interface UserManager {
	
	public List<UserEntity> retrieveUsers();

	public void addUser(Creator creator);

}