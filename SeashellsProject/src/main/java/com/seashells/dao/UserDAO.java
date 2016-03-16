/**
 * 
 */
package com.seashells.dao;

import java.util.List;

import com.seashells.entity.UserEntity;

/**
 * @author N&Y
 *
 */
public interface UserDAO {

	public void addUser(UserEntity employee);

	public List<UserEntity> getAllUsers();
}