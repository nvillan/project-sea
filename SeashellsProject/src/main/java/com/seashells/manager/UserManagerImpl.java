package com.seashells.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seashells.entity.UserEntity;
import com.seashells.model.Creator;
import com.seashells.model.SubscriptionPayload;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserRepositoryManager userRepoManager;

	public List<UserEntity> retrieveUsers() {
		return getUserRepoManager().getAllUsers();
	}

	@Transactional
	public int addUserSubscription(Creator creator, SubscriptionPayload subscriptionPayload) {
		// validate creator details

		// create employee entity
		UserEntity user = new UserEntity();
		user.setFirstname(creator.getFirstName());
		user.setLastname(creator.getLastName());
		user.setEmail(creator.getEmail());

		user.setEdition(subscriptionPayload.getOrder().getEditionCode());

		return getUserRepoManager().addUser(user);

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

	public int cancelUserSubscription(SubscriptionPayload payload) {

		System.out.println("printing account num " + String.valueOf(payload.getAccount().getAccountIdentifier()));
		getUserRepoManager().deleteUserAccount(payload.getAccount().getAccountIdentifier());
		return payload.getAccount().getAccountIdentifier();

	}

}