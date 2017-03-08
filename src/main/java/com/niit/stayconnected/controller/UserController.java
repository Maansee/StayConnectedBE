package com.niit.stayconnected.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niit.stayconnected.dao.UserDAO;
import com.niit.stayconnected.model.Email;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Email email;
	

}
