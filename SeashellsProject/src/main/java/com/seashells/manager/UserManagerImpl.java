package com.seashells.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.seashells.entity.UserEntity;
import com.seashells.model.Creator;

public class UserManagerImpl implements UserManager {
	
	@Autowired
	private UserRepositoryManager userRepoManager;

	public List<UserEntity> retrieveUsers() {
		return getUserRepoManager().getAllUsers();
	}

	public void addUser(Creator creator) {
		// validate creator details

		// create employee entity
		UserEntity user = new UserEntity();
		user.setFirstname(creator.getFirstName());
		user.setLastname(creator.getLastName());
		user.setEmail(creator.getFirstName());
		user.setFirstname(creator.getFirstName());
		getUserRepoManager().addUser(user);

	}

	/**
	 * @return the userRepoManager
	 */
	public UserRepositoryManager getUserRepoManager() {
		return userRepoManager;
	}

	/**
	 * @param userRepoManager
	 *            the userRepoManager to set
	 */
	public void setUserRepoManager(UserRepositoryManager userRepoManager) {
		this.userRepoManager = userRepoManager;
	}

}