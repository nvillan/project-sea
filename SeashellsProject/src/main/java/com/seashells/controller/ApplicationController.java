package com.seashells.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seashells.manager.UserManager;
 
@Controller
public class ApplicationController 
{
    @Autowired
    private UserManager userManager;
    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(ModelMap map)
    { 
        map.addAttribute("userList", getUserManager().retrieveUsers());
        return "userList";
    }
 
  

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
    
}