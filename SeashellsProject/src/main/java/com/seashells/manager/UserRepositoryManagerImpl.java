package com.seashells.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seashells.dao.UserDAO;
import com.seashells.entity.UserEntity;

@Service
public class UserRepositoryManagerImpl implements UserRepositoryManager {
	@Autowired
	private UserDAO userDAO;

	@Transactional
	public int addUser(UserEntity user) {
		userDAO.addUser(user);

		return user.getAccountNumber();
	}

	@Transactional
	public List<UserEntity> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}