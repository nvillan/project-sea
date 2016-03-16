package com.seashells.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class UserEntity 
{
    @Id
    @Column(name="account_num")
    @GeneratedValue
    private Integer id;
    
    @Column(name="firstname")
	private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="email")
    private String email;
    
    @Column(name="edition")
    private String edition;
 
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}
	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}
}