package com.seashells.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seashells.entity.UserEntity;
import com.seashells.model.Creator;
import com.seashells.model.SubscriptionPayload;
 
/**
 * The Class UserManagerImpl.
 */
@Service
public class UserManagerImpl implements UserManager {

	final static Logger logger = Logger.getLogger(UserManagerImpl.class);

	/** The user repo manager. */
	@Autowired
	private UserRepositoryManager userRepoManager;

	/* (non-Javadoc)
	 * @see com.seashells.manager.UserManager#retrieveUsers()
	 */
	public List<UserEntity> retrieveUsers() {
		return getUserRepoManager().getAllUsers();
	}

	/* (non-Javadoc)
	 * @see com.seashells.manager.UserManager#addUserSubscription(com.seashells.model.Creator, com.seashells.model.SubscriptionPayload)
	 */
	@Transactional
	public int addUserSubscription(Creator creator, SubscriptionPayload subscriptionPayload) {
		// validate creator details
		//TODO: Validate that a user with the same email address doesn't already exist.

		// create employee entity
		UserEntity user = new UserEntity();
		user.setFirstname(creator.getFirstName());
		user.setLastname(creator.getLastName());
		user.setEmail(creator.getEmail());
		user.setEdition(subscriptionPayload.getOrder().getEditionCode());


		if (logger.isDebugEnabled()) {
			logger.debug("Creating user : "+ user.toString()+" ");
		}
		return getUserRepoManager().addUser(user);

	}

	/**
	 * Gets the user repo manager.
	 *
	 * @return the userRepoManager
	 */
	public UserRepositoryManager getUserRepoManager() {
		return userRepoManager;
	}

	/**
	 * Sets the user repo manager.
	 *
	 * @param userRepoManager            the userRepoManager to set
	 */
	public void setUserRepoManager(UserRepositoryManager userRepoManager) {
		this.userRepoManager = userRepoManager;
	}

	/* (non-Javadoc)
	 * @see com.seashells.manager.UserManager#cancelUserSubscription(com.seashells.model.SubscriptionPayload)
	 */
	public int cancelUserSubscription(SubscriptionPayload payload) {

		System.out.println("printing account num " + String.valueOf(payload.getAccount().getAccountIdentifier()));
		getUserRepoManager().deleteUserAccount(payload.getAccount().getAccountIdentifier());
		return payload.getAccount().getAccountIdentifier();

	}

}