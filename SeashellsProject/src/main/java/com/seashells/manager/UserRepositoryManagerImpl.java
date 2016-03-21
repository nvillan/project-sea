package com.seashells.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seashells.dao.UserDAO;
import com.seashells.entity.UserEntity;
 
/**
 * The Class UserRepositoryManagerImpl.
 */
@Service
public class UserRepositoryManagerImpl implements UserRepositoryManager {
	
	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/* (non-Javadoc)
	 * @see com.seashells.manager.UserRepositoryManager#addUser(com.seashells.entity.UserEntity)
	 */
	@Transactional
	public int addUser(UserEntity user) {
		userDAO.addUser(user);
		return user.getAccountNumber();
	}

	/* (non-Javadoc)
	 * @see com.seashells.manager.UserRepositoryManager#getAllUsers()
	 */
	@Transactional
	public List<UserEntity> getAllUsers() {
		return userDAO.getAllUsers();
	}

	/**
	 * Sets the user dao.
	 *
	 * @param userDAO the new user dao
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/* (non-Javadoc)
	 * @see com.seashells.manager.UserRepositoryManager#deleteUserAccount(int)
	 */
	public void deleteUserAccount(int accountIdentifier) {
		userDAO.deleteUser(accountIdentifier);
	}
}