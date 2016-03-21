package com.seashells.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class UserEntity.
 */
@Entity
@Table(name = "customers")
public class UserEntity {

	/** The account number. */
	@Id
	@Column(name = "account_num")
	@GeneratedValue
	private Integer accountNumber;

	/** The firstname. */
	@Column(name = "firstname")
	private String firstname;

	/** The lastname. */
	@Column(name = "lastname")
	private String lastname;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The edition. */
	@Column(name = "edition")
	private String edition;

	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the edition.
	 *
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * Sets the edition.
	 *
	 * @param edition
	 *            the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the accountNumber
	 */
	public Integer getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String toString() {

		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		result.append(" Name: " + getFirstname() + " " + getLastname() + " " + NEW_LINE);
		result.append(" Email: " + getEmail() + NEW_LINE);
		result.append(" Edition: " + getEdition() + NEW_LINE);
		result.append("}");

		return result.toString();
	}

}