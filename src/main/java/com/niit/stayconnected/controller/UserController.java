package com.niit.stayconnected.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.niit.stayconnected.model.Email;
import com.niit.stayconnected.model.UserInfo;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Email email;
	
	@Autowired
	private UserInfo user;
	
	@Autowired
	private HttpSession session;
	

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from StayConnectedBE server";
	}

	// Mapping
	// GetMapping -> Fetch the data by sending few parameters
	// PostMapping -> create a record OR Save
	// PutMapping -> Update the record
	// DeleteMapping -> delete the record

	// define a simple service and test
	// Call the methods of DAO
	// List<User> -> need to convert into JSON Objects
	// So that we can use in our front end project

	// Hot to test??
	// 1) Postman
	// 2)RestClient

	// http://localhost:8080/Collaboration/getAllUsers
/*	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserInfo>> getAllUsers() {
		List users = userDAO.list();
		if (users.isEmpty()) {
			user.setErrorCode("100");
			user.setErrorMessage("Not users are available");
			users.add(user);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		user.setErrorCode("200");
		user.setErrorMessage("Successfully fetched the user");

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}*/

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserInfo> getUser(@PathVariable("userId") int userId) {
		user =userDAO.get(userId);

		if (user == null) {
			user = new UserInfo();
			user.setErrorCode("404");
			user.setErrorMessage("No user found with this id :" + userId);
		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

	}

	@PostMapping("/Authenticate/")
	public ResponseEntity<UserInfo> validateCredentials(@RequestBody UserInfo user) {

		user = userDAO.authenticate(user.getUserId(), user.getPassword());

		if (user == null) { // NLP NullPointerException...what is the solution
			user = new UserInfo();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials...Please try again.");
		} else {
			user.setErrorCode("200");
			user.setErrorMessage("You are successfully logged in....");
			
			session.setAttribute("loggedInUserID", user.getUserId());
			session.setAttribute("loggedInUserRole", user.getRole());
			
			
		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

	}

	@PostMapping("/CreateUser/")
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo user)

	{

		if (userDAO.get(user.getUserId()) == null) {
			userDAO.save(user);
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully registered...");
		} else {
			user.setErrorCode("404");
			user.setErrorMessage("User exist with this id : " + user.getUserId());

		}

		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);

	}
	
	//this is usercontroller
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserInfo>> listAllUsers() {

		/*logger.debug("->->->->calling method listAllUsers");*/
		List<UserInfo> users = userDAO.list();

		// errorCode :200 :404
		// errorMessage :Success :Not found

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No users are available");
			users.add(user);
		}

		return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
	}

	// http://localhost:8080/Collaboration/user/
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<UserInfo> createUser1(@RequestBody UserInfo user) {
		/*logger.debug("->->->->calling method createUser");*/
		if (userDAO.get(user.getUserId()) == null) {
			/*logger.debug("->->->->User is going to create with id:" + user.getUserId());*/
//need to work on this
			/*user.setOnline('N');
			user.setStatus('N');*/
			  if (userDAO.save(user) ==true)
			  {
				  user.setErrorCode("200");
					user.setErrorMessage("Thank you  for registration. You have successfully registered as " + user.getRole());
			  }
			  else
			  {
				  user.setErrorCode("404");
					user.setErrorMessage("Could not complete the operatin please contact Admin");
		
				  
			  }
			
			return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
		}
		/*logger.debug("->->->->User already exist with id " + user.getUserId());*/
		user.setErrorCode("404");
		user.setErrorMessage("User already exist with id : " + user.getUserId());
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}

	// http://localhost:8080/Collaboration/user/
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user) {
		/*logger.debug("->->->->calling method updateUser");*/
		if (userDAO.get(user.getUserId()) == null) {
		/*	logger.debug("->->->->User does not exist with id " + user.getUserId());*/
			user = new UserInfo(); // ?
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with id " + user.getUserId());
			return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
		}

		userDAO.update(user);
		/*logger.debug("->->->->User updated successfully");*/
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}

}
