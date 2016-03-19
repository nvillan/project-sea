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

	public int createAccount(HttpURLConnection response) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionEvent.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SubscriptionEvent subscriptionOrderEvent = (SubscriptionEvent) jaxbUnmarshaller
				.unmarshal(response.getInputStream());
		System.out.println("printing dummy order :\n" + subscriptionOrderEvent);

		Creator c = subscriptionOrderEvent.getCreator();
		SubscriptionPayload sub = subscriptionOrderEvent.getPayload();
		return getUserManager().addUserSubscription(c, sub);

	}

	public void cancelAccount(HttpURLConnection response) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionEvent.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SubscriptionEvent subscriptionCancelEvent = (SubscriptionEvent) jaxbUnmarshaller
				.unmarshal(response.getInputStream());
		System.out.println("printing dummy order :\n" + subscriptionCancelEvent);

		SubscriptionPayload payload = subscriptionCancelEvent.getPayload();

		getUserManager().cancelUserSubscription(payload);

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
