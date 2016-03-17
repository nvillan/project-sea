package com.seashells.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seashells.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(UserEntity user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
	}

}