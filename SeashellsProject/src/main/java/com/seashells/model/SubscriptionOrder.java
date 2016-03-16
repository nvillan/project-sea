/**
 * 
 */
package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String editionCode;

	@XmlElement
	private String pricingDuration;

	/**
	 * @return the editionCode
	 */
	public String getEditionCode() {
		return editionCode;
	}

	/**
	 * @param editionCode
	 *            the editionCode to set
	 */
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	/**
	 * @return the pricingDuration
	 */
	public String getPricingDuration() {
		return pricingDuration;
	}

	/**
	 * @param pricingDuration
	 *            the pricingDuration to set
	 */
	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

}
