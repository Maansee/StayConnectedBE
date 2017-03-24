package com.niit.stayconnected.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.stayconnected.dao.FileUploadDAO;
import com.niit.stayconnected.dao.UserDAO;
import com.niit.stayconnected.model.Email;
import com.niit.stayconnected.model.ProfilePhoto;
import com.niit.stayconnected.model.UserInfo;



@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private FileUploadDAO fileUploadDAO;
	
	
	@Autowired
	private Email email;

	
	//isOnline - set true when the user login
	//isOnline -set false when the user logout
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserInfo user,HttpSession session){
		// ? means it can return any type of object [Error, User]
		
		// the method login has to return any Type  
		//if the user is invalid - return Error object with status code
		//if the user is valid  - return User object with status code
		System.out.println("h5");
		UserInfo validUser=userDAO.authenticate(user);
		System.out.println("h6");

		if(validUser==null){
			System.out.println("h7");

			Error error=new Error("Username and password doesnt exists...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //401
		}

		else{
			System.out.println("h8");
			session.setAttribute("user", validUser);

			validUser.setOnline(true);
			userDAO.updateUser(validUser); // to update online status in db
			
			
			
			//select * from proj2_profile_pics where username='adam';
			ProfilePhoto getUploadFile=fileUploadDAO.getFile(user.getUsername());
			  if(getUploadFile!=null){
		  	String name=getUploadFile.getPhotoName();
		  	System.out.println(getUploadFile.getData());
		  	byte[] imagefiles=getUploadFile.getData();
		  	try{
		  		String path="F:/Eclipse Workspace/MyProject2/StayConnectedBE/src/main/webapp/WEB-INF/resources/images/"+user.getUsername();
		  		File file=new File(path);
		  		//file.mkdirs();
		  		FileOutputStream fos = new FileOutputStream(file);//to Write some data 
		  		fos.write(imagefiles);
		  		fos.close();
		  		}catch(Exception e){
		  		e.printStackTrace();
		  		}
			  }
			
			return new ResponseEntity<UserInfo>(validUser,HttpStatus.OK);//200
		}
	}



	//'?'  - Any Type [User,Error] 
	//ENDPOINT : http://localhost:8080/proj2backend/register 
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserInfo user,HttpSession session,HttpServletRequest request){
		//client will send only username, password, email, role 
		try{
//		logger.debug("USERCONTROLLER=>REGISTER " + user);
		user.setStatus(true);
		user.setOnline(false);
		System.out.println("h9");

		UserInfo savedUser=userDAO.registerUser(user);
		System.out.println("h10");

//		logger.debug("User Id generated is " + savedUser.getId());
		if(savedUser.getId()==0){
			System.out.println("h111");
			System.out.println("h1111");

			Error error=new Error("Couldnt insert user details ");
			System.out.println("h12");

			return new ResponseEntity<Error>(error , HttpStatus.CONFLICT);
		}
		else
		{
			System.out.println("h13");

			email.send(user, "hello "+user.getUsername()+", Your Account is Activated", "Welcome to StayConnected! Your password is "+user.getPassword()+",and Role is "+user.getRole());

			return new ResponseEntity<UserInfo>(savedUser,HttpStatus.OK);
		}
		}catch(MessagingException e){
			e.printStackTrace();
			System.out.println("h14");

			Error error=new Error("Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		UserInfo user=(UserInfo)session.getAttribute("user");
		if(user!=null){
			user.setOnline(false);
			userDAO.updateUser(user);
			try{
	                        //change according to your workspace path and project name
		  		String path="F:/Eclipse Workspace/MyProject2/StayConnectedBE/src/main/webapp/WEB-INF/resources/images/"+user.getUsername();
		  		File file=new File(path);
		  		System.out.println(file.delete());
		  		
		  		}catch(Exception e){
		  		e.printStackTrace();
		  		}
		}
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@RequestMapping(value="#/getUsers",method=RequestMethod.GET)
	public ResponseEntity<?> getAllUsers(HttpSession session){
		UserInfo user=(UserInfo)session.getAttribute("user");
		if(user==null)
		return new	ResponseEntity<Error>(new Error("Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else
		{
			List<UserInfo> users=userDAO.getAllUsers(user);
			for(UserInfo u:users)
				System.out.println("IsONline " + u.isOnline());
			return new ResponseEntity<List<UserInfo>>(users,HttpStatus.OK);
		}
	}

}