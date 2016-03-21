package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SubscriptionEvent.
 */
@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionEvent implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	@XmlElement
	private String type;
	//
	// @XmlElement
	// private String marketplace;

	/** The creator. */
	@XmlElement
	private Creator creator;

	/** The payload. */
	@XmlElement
	private SubscriptionPayload payload;

	/**
	 * Instantiates a new subscription event.
	 */
	public SubscriptionEvent() {

	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	//
	// /**
	// * @return the marketplace
	// */
	// public String getMarketplace() {
	// return marketplace;
	// }
	//
	// /**
	// * @param marketplace
	// * the marketplace to set
	// */
	// public void setMarketplace(String marketplace) {
	// this.marketplace = marketplace;
	// }

	/**
	 * Gets the payload.
	 *
	 * @return the payload
	 */
	public SubscriptionPayload getPayload() {
		return payload;
	}

	/**
	 * Sets the payload.
	 *
	 * @param payload
	 *            the payload to set
	 */
	public void setPayload(SubscriptionPayload payload) {
		this.payload = payload;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubscriptionOrder [type=" + type + "]";
	}

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	public Creator getCreator() {
		return creator;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
}