package com.niit.stayconnected.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.stayconnected.dao.FileUploadDAO;
import com.niit.stayconnected.model.ProfilePhoto;
import com.niit.stayconnected.model.UserInfo;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadDAO fileUploadDao;
        
	 @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	    public String handleFileUpload(HttpServletRequest request,
	    		HttpSession session,
	            @RequestParam CommonsMultipartFile fileUpload) throws Exception {
	         UserInfo user=(UserInfo)session.getAttribute("user");
	         if(user==null)
	        	 throw new RuntimeException("Not logged in");
	         System.out.println("USER is " + user.getUsername());
	         
	         if (fileUpload != null ) {
	             CommonsMultipartFile aFile = fileUpload;
	            
	                System.out.println("Saving file: " + aFile.getOriginalFilename());
	                
	                ProfilePhoto uploadFile = new ProfilePhoto();
	                uploadFile.setPhotoName(aFile.getOriginalFilename());
	                uploadFile.setData(aFile.getBytes());//image 
	                uploadFile.setUsername(user.getUsername());//login details
	                fileUploadDao.save(uploadFile);
	 
	                
	                //select * from proj2_profie_pics where username='smith'
	                ProfilePhoto getUploadFile=fileUploadDao.getFile(user.getUsername());
	            	String name=getUploadFile.getPhotoName();
	            	System.out.println(getUploadFile.getData());
	            	byte[] imagefiles=getUploadFile.getData();  //image
	            	try{
	            		//change the path according to your workspace and the name of your project
	            		String path="F://Eclipse Workspace//MyProject2//StayConnectedBE//src/main//webapp//resources//images//"+user.getUsername();
	            		File file=new File(path);
	            		//file.mkdirs();
	            		FileOutputStream fos = new FileOutputStream(file);
	            		fos.write(imagefiles);// write the array of bytes in username file.
	            		fos.close();
	            		}catch(Exception e){
	            		e.printStackTrace();
	            		}
	             }
	                
	  
	        return "Successfully uploaded the Profile Picture";
	    }	
		
	    	@RequestMapping(value="/getFile",method=RequestMethod.GET)
	    	public ResponseEntity<?> getFile(HttpSession session){
	    		UserInfo user=(UserInfo)session.getAttribute("user");
		   ProfilePhoto uploadFile=fileUploadDao.getFile(user.getUsername());
	    	String name=uploadFile.getPhotoName();
	    	System.out.println(uploadFile.getData());
	    	byte[] imagefiles=uploadFile.getData();

	    	return new ResponseEntity<byte[]>(imagefiles,HttpStatus.OK);
	    }
	
}
