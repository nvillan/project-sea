package com.seashells.manager;

import java.util.List;

import com.seashells.entity.UserEntity;
import com.seashells.model.Creator;
import com.seashells.model.SubscriptionPayload;

public interface UserManager {
	
	public List<UserEntity> retrieveUsers();

	public int addUserSubscription(Creator creator, SubscriptionPayload subscriptionPayload);

	public void cancelUserSubscription(SubscriptionPayload payload);

}