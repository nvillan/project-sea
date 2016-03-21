package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SubscriptionAccount.
 */
@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionAccount implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The account identifier. */
	@XmlElement
	private int accountIdentifier;

	/**
	 * Instantiates a new subscription account.
	 */
	public SubscriptionAccount() {

	}

	/**
	 * Gets the account identifier.
	 *
	 * @return the accountIdentifier
	 */
	public int getAccountIdentifier() {
		return accountIdentifier;
	}

	/**
	 * Sets the account identifier.
	 *
	 * @param accountIdentifier
	 *            the accountIdentifier to set
	 */
	public void setAccountIdentifier(int accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

}