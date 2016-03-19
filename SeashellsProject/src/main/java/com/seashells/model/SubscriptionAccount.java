package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private int accountIdentifier;

	public SubscriptionAccount() {

	}

	/**
	 * @return the accountIdentifier
	 */
	public int getAccountIdentifier() {
		return accountIdentifier;
	}

	/**
	 * @param accountIdentifier the accountIdentifier to set
	 */
	public void setAccountIdentifier(int accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

}