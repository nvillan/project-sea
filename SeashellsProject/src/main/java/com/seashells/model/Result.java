/**
 * 
 */
package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;

	private String message;

	private String accountIdentifier;

	/**
	 * @return the success
	 */
	@XmlElement
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the message
	 */
	@XmlElement
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the accountIdentifier
	 */
	@XmlElement
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	/**
	 * @param accountIdentifier
	 *            the accountIdentifier to set
	 */
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

}
