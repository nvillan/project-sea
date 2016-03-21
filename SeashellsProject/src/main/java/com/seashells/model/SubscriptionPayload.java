/**
 * 
 */
package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SubscriptionPayload.
 */
@XmlRootElement(name = "payload")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionPayload implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The account. */
	@XmlElement
	private SubscriptionAccount account;

	/** The order. */
	@XmlElement
	private SubscriptionOrder order;

	// @XmlElement
	// private String company;

	/**
	 * Instantiates a new subscription payload.
	 */
	public SubscriptionPayload() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public SubscriptionOrder getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order
	 *            the order to set
	 */
	public void setOrder(SubscriptionOrder order) {
		this.order = order;
	}
	//
	// /**
	// * @return the company
	// */
	// public String getCompany() {
	// return company;
	// }
	//
	// /**
	// * @param company the company to set
	// */
	// public void setCompany(String company) {
	// this.company = company;
	// }

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public SubscriptionAccount getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account
	 *            the account to set
	 */
	public void setAccount(SubscriptionAccount account) {
		this.account = account;
	}

}
