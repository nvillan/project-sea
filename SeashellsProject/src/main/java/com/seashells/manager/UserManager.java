package com.seashells.manager;

import java.util.List;

import com.seashells.entity.UserEntity;
import com.seashells.model.Creator;
import com.seashells.model.SubscriptionPayload;
 
/**
 * The Interface UserManager.
 */
public interface UserManager {
	
	/**
	 * Retrieve users.
	 *
	 * @return the list
	 */
	public List<UserEntity> retrieveUsers();

	/**
	 * Adds the user subscription.
	 *
	 * @param creator the creator
	 * @param subscriptionPayload the subscription payload
	 * @return the int
	 */
	public int addUserSubscription(Creator creator, SubscriptionPayload subscriptionPayload);

	/**
	 * Cancel user subscription.
	 *
	 * @param payload the payload
	 * @return the int
	 */
	public int cancelUserSubscription(SubscriptionPayload payload);

}