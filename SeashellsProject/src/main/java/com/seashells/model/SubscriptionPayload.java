/**
 * 
 */
package com.seashells.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "payload")
@XmlAccessorType(XmlAccessType.NONE)
public class SubscriptionPayload implements Serializable 
{
   private static final long serialVersionUID = 1L;
 
   
   @XmlElement
   private SubscriptionOrder order;
    
   //@XmlElement
   //private String company;
    	
	/**
	 * 
	 */
	public SubscriptionPayload() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the order
	 */
	public SubscriptionOrder getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(SubscriptionOrder order) {
		this.order = order;
	}
//
//	/**
//	 * @return the company
//	 */
//	public String getCompany() {
//		return company;
//	}
//
//	/**
//	 * @param company the company to set
//	 */
//	public void setCompany(String company) {
//		this.company = company;
//	}

}
