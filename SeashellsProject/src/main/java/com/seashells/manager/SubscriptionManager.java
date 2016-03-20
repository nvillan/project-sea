/**
 * 
 */
package com.seashells.manager;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seashells.model.Creator;
import com.seashells.model.SubscriptionEvent;
import com.seashells.model.SubscriptionPayload;

/**
 * @author N&Y
 *
 */

@Service
public class SubscriptionManager {

	@Autowired
	private UserManager userManager;

	public int createAccount(HttpURLConnection response) throws JAXBException, IOException, AccountCreationException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionEvent.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SubscriptionEvent subscriptionOrderEvent = (SubscriptionEvent) jaxbUnmarshaller
				.unmarshal(response.getInputStream());
		System.out.println("Creation account for order :\n" + subscriptionOrderEvent);

		Creator c = subscriptionOrderEvent.getCreator();
		if (c == null) {
			throw new AccountCreationException("The subscriptionOrderEvent has no creator.");
		}
		SubscriptionPayload payload = subscriptionOrderEvent.getPayload();
		if (payload == null) {
			throw new AccountCreationException("The subscriptionOrderEvent has no payload.");
		}
		return getUserManager().addUserSubscription(c, payload);

	}

	public int cancelAccount(HttpURLConnection response) throws JAXBException, IOException, AccountCreationException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionEvent.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SubscriptionEvent subscriptionCancelEvent = (SubscriptionEvent) jaxbUnmarshaller
				.unmarshal(response.getInputStream());
		System.out.println("Cancel account for cancel event :\n" + subscriptionCancelEvent);

		SubscriptionPayload payload = subscriptionCancelEvent.getPayload();
		if (payload == null) {
			throw new AccountCreationException("The subscriptionCancelEvent has no payload.");
		}

		return getUserManager().cancelUserSubscription(payload);

	}

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
