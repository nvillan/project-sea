package com.seashells.manager;

import java.util.List;

import com.seashells.entity.UserEntity;
 
/**
 * The Interface UserRepositoryManager.
 */
public interface UserRepositoryManager {
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the int
	 */
	public int addUser(UserEntity user);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<UserEntity> getAllUsers();

	/**
	 * Delete user account.
	 *
	 * @param accountIdentifier the account identifier
	 */
	public void deleteUserAccount(int accountIdentifier);
}