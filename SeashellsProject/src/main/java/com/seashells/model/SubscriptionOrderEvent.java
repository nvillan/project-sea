package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionOrderEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String type;
//
//	@XmlElement
//	private String marketplace;

	@XmlElement
	private Creator creator;

//	@XmlElement
//	private SubscriptionPayload payload;

	public SubscriptionOrderEvent(String type, Creator creator) {
		super();
		this.type=type;
		
	}
	
	public SubscriptionOrderEvent() {

	}
 

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
//
//	/**
//	 * @return the marketplace
//	 */
//	public String getMarketplace() {
//		return marketplace;
//	}
//
//	/**
//	 * @param marketplace
//	 *            the marketplace to set
//	 */
//	public void setMarketplace(String marketplace) {
//		this.marketplace = marketplace;
//	}

//	/**
//	 * @return the payload
//	 */
//	public SubscriptionPayload getPayload() {
//		return payload;
//	}
//
//	/**
//	 * @param payload
//	 *            the payload to set
//	 */
//	public void setPayload(SubscriptionPayload payload) {
//		this.payload = payload;
//	}

	@Override
	public String toString() {
		return "SubscriptionOrder [type=" + type + "]";
	}

	/**
	 * @return the creator
	 */
	public Creator getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
}