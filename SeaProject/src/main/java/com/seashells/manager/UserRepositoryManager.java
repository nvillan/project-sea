package com.seashells.manager;

import java.util.List;

import com.seashells.entity.UserEntity;

public interface UserRepositoryManager {
	public void addUser(UserEntity user);

	public List<UserEntity> getAllUsers();
}