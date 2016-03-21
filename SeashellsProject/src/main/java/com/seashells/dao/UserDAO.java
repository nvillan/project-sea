/**
 * 
 */
package com.seashells.dao;

import java.util.List;

import com.seashells.entity.UserEntity;
  
/**
 * The Interface UserDAO.
 *
 * @author Natalie Villanueva
 */
public interface UserDAO {

	/**
	 * Adds the user.
	 *
	 * @param employee the employee
	 */
	public void addUser(UserEntity employee);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<UserEntity> getAllUsers();

	/**
	 * Delete user.
	 *
	 * @param accountNum the account num
	 */
	public void deleteUser(int accountNum);
}