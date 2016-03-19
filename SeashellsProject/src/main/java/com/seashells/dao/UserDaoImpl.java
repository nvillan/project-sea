package com.seashells.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seashells.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Transactional
	public void addUser(UserEntity user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Transactional
	public void deleteUser(int accountNum) {
		UserEntity userEntity = (UserEntity) sessionFactory.getCurrentSession().load(UserEntity.class, accountNum);
		if (null != userEntity) {
			this.sessionFactory.getCurrentSession().delete(userEntity);
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
	}

}