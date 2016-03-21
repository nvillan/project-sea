package com.seashells.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seashells.entity.UserEntity;
 
/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl implements UserDAO {
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.seashells.dao.UserDAO#addUser(com.seashells.entity.UserEntity)
	 */
	@Transactional
	public void addUser(UserEntity user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	/* (non-Javadoc)
	 * @see com.seashells.dao.UserDAO#deleteUser(int)
	 */
	@Transactional
	public void deleteUser(int accountNum) {
		UserEntity userEntity = (UserEntity) sessionFactory.getCurrentSession().load(UserEntity.class, accountNum);
		if (null != userEntity) {
			this.sessionFactory.getCurrentSession().delete(userEntity);
		}
	}

	/* (non-Javadoc)
	 * @see com.seashells.dao.UserDAO#getAllUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
	}

}