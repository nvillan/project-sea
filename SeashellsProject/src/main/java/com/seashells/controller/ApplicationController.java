package com.seashells.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seashells.manager.UserManager;

/**
 * The Application controller class is responsible for handling the calls of the
 * view layer.
 *
 * @author Natalie Villanueva
 * @version 1.0
 */
@Controller
public class ApplicationController {

	/** The user manager. */
	@Autowired
	private UserManager userManager;

	/**
	 * List users method returns all the users of that have customer account
	 * with our app.
	 *
	 * @param map
	 *            the map
	 * @return the string
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(ModelMap map) {
		map.addAttribute("userList", getUserManager().retrieveUsers());
		return "userList";
	}

	/**
	 * Gets the user manager.
	 *
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Sets the user manager.
	 *
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}