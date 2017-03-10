package com.niit.stayconnected.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.stayconnected.dao.UserDAO;

import com.niit.stayconnected.model.UserInfo;



@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	
	@Autowired
	private UserInfo user;
	
	@Autowired
	private HttpSession session;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<UserInfo> logoutuser(HttpSession session)	{
		String loggeduser = (String)session.getAttribute("loggeduser");
		session.invalidate();
		System.out.println("Logged user :" + loggeduser);
		user.setIsOnline('N');
		return new ResponseEntity<UserInfo>(HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserInfo> login(@RequestBody UserInfo user, HttpSession session) {
		log.debug("->->->->calling method authenticate");
		user = userDAO.authenticate(user.getUserId(), user.getPassword());
		if (user == null) {
			user = new UserInfo(); // Do wee need to create new user?
			/*user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials.  Please enter valid credentials");*/
			log.debug("->->->->In Valid Credentials");

		} else

		{
			/*user.setErrorCode("200");
			user.setErrorMessage("You have successfully logged in.");*/
			user.setIsOnline('Y');
			log.debug("->->->->Valid Credentials");
			/*session.setAttribute("loggedInUser", user);*/
			session.setAttribute("loggedInUserID", user.getUserId());
			session.setAttribute("loggedInUserRole", user.getUserrole());
			
			log.debug("You are logging with the role : " +session.getAttribute("loggedInUserRole"));

			//need to work on friend
			/*friendDAO.setOnline(user.getUserId());*/
		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}

	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserInfo>> getAllUsers() {
		List users = userDAO.list();
		if (users.isEmpty()) {
		/*	user.setErrorCode("100");
			user.setErrorMessage("Not users are available");*/
			users.add(user);
			return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
		}
		/*user.setErrorCode("200");
		user.setErrorMessage("Successfully fetched the user");*/

		return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);

	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserInfo> getUser(@PathVariable("userId") int userId) {
		user =userDAO.get(userId);

		if (user == null) {
			user = new UserInfo();
			/*user.setErrorCode("404");
			user.setErrorMessage("No user found with this id :" + userId);*/
		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

	}

	@PostMapping("/Authenticate/")
	public ResponseEntity<UserInfo> validateCredentials(@RequestBody UserInfo user) {

		user = userDAO.authenticate(user.getUserId(), user.getPassword());

		if (user == null) { // NLP NullPointerException...what is the solution
			user = new UserInfo();
			/*user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials...Please try again.");*/
		} else {
			/*user.setErrorCode("200");
			user.setErrorMessage("You are successfully logged in....");*/
			
			session.setAttribute("loggedInUserID", user.getUserId());
			session.setAttribute("loggedInUserRole", user.getUserrole());
			
			
		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

	}

	@PostMapping("/CreateUser/")
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo user)

	{

		if (userDAO.get(user.getUserId()) == null) {
			userDAO.save(user);
			/*user.setErrorCode("200");
			user.setErrorMessage("You have successfully registered...");*/
		} else {
			/*user.setErrorCode("404");
			user.setErrorMessage("User exist with this id : " + user.getUserId());*/

		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

	}
	
	//this is usercontroller
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserInfo>> listAllUsers() {

		log.debug("->->->->calling method listAllUsers");
		List<UserInfo> users = userDAO.list();

		// errorCode :200 :404
		// errorMessage :Success :Not found

		if (users.isEmpty()) {
			/*user.setErrorCode("404");
			user.setErrorMessage("No users are available");*/
			users.add(user);
		}

		return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
	}



	@RequestMapping(value = "/UpdateUser/", method = RequestMethod.PUT)
	public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user) {
		log.debug("->->->->calling method updateUser");
		if (userDAO.get(user.getUserId()) == null) {
		/*	logger.debug("->->->->User does not exist with id " + user.getUserId());*/
			user = new UserInfo(); // ?
			/*user.setErrorCode("404");
			user.setErrorMessage("User does not exist with id " + user.getUserId());*/
			return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
		}

		userDAO.update(user);
		log.debug("->->->->User updated successfully");
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}
	
	

}