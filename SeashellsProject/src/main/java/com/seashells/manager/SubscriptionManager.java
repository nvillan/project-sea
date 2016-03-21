/**
 * 
 */
package com.seashells.manager;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seashells.exception.AccountCreationException;
import com.seashells.model.Creator;
import com.seashells.model.SubscriptionEvent;
import com.seashells.model.SubscriptionPayload;

/**
 * The Class SubscriptionManager.
 *
 * @author Natalie Villanueva
 * @version 1.0
 */

@Service
public class SubscriptionManager {

	final static Logger logger = Logger.getLogger(SubscriptionManager.class);

	/** The user manager. */
	@Autowired
	private UserManager userManager;

	/**
	 * Creates the account for a new customer that has purchased the app. It
	 * extracts the neccessary parts of the event data to create the account and
	 * returns the account number.
	 * 
	 * @param response
	 *            the response
	 * @return the int
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AccountCreationException
	 *             the account creation exception
	 */
	public int createAccount(HttpURLConnection response) throws JAXBException, IOException, AccountCreationException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionEvent.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SubscriptionEvent subscriptionOrderEvent = (SubscriptionEvent) jaxbUnmarshaller
				.unmarshal(response.getInputStream());

		if (logger.isDebugEnabled()) {
			logger.debug("Creation account for order :\n" + subscriptionOrderEvent);
		}

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

	/**
	 * Cancel account method extracts the necessary parts of the event data to
	 * cancel the account and returns the account number.
	 *
	 * @param response
	 *            the response
	 * @return the int
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AccountCreationException
	 *             the account creation exception
	 */
	public int cancelAccount(HttpURLConnection response) throws JAXBException, IOException, AccountCreationException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionEvent.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SubscriptionEvent subscriptionCancelEvent = (SubscriptionEvent) jaxbUnmarshaller
				.unmarshal(response.getInputStream());

		if (logger.isDebugEnabled()) {
			logger.debug("Cancel account for cancel event :\n" + subscriptionCancelEvent);
		}
		SubscriptionPayload payload = subscriptionCancelEvent.getPayload();
		if (payload == null) {
			throw new AccountCreationException("The subscriptionCancelEvent has no payload.");
		}

		return getUserManager().cancelUserSubscription(payload);

	}

	/**
	 * Gets the user manager.
	 *
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Sets the user manager.
	 *
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
