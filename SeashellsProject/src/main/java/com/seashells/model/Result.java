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
 * The Class Result.
 */
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.NONE)
public class Result implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The success. */
	@XmlElement
	private boolean success;

	/** The message. */
	@XmlElement
	private String message;

	/** The account identifier. */
	@XmlElement
	private String accountIdentifier;

	/**
	 * Checks if is success.
	 *
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the account identifier.
	 *
	 * @return the accountIdentifier
	 */
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	/**
	 * Sets the account identifier.
	 *
	 * @param accountIdentifier
	 *            the accountIdentifier to set
	 */
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

}
