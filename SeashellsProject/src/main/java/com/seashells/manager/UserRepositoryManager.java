package com.seashells.manager;

import java.util.List;

import com.seashells.entity.UserEntity;

public interface UserRepositoryManager {
	public int addUser(UserEntity user);

	public List<UserEntity> getAllUsers();
}