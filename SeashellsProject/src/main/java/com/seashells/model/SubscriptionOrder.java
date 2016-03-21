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
 * The Class SubscriptionOrder.
 */
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionOrder implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The edition code. */
	@XmlElement
	private String editionCode;

	/** The pricing duration. */
	@XmlElement
	private String pricingDuration;

	/**
	 * Gets the edition code.
	 *
	 * @return the editionCode
	 */
	public String getEditionCode() {
		return editionCode;
	}

	/**
	 * Sets the edition code.
	 *
	 * @param editionCode
	 *            the editionCode to set
	 */
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	/**
	 * Gets the pricing duration.
	 *
	 * @return the pricingDuration
	 */
	public String getPricingDuration() {
		return pricingDuration;
	}

	/**
	 * Sets the pricing duration.
	 *
	 * @param pricingDuration
	 *            the pricingDuration to set
	 */
	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

}
