package com.niit.stayconnected.controller;


import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.stayconnected.dao.BlogDAO;
import com.niit.stayconnected.dao.UserDAO;
import com.niit.stayconnected.model.BlogComment;
import com.niit.stayconnected.model.BlogPost;
import com.niit.stayconnected.model.Email;
import com.niit.stayconnected.model.Error;
import com.niit.stayconnected.model.UserInfo;


@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Email email;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ResponseEntity<?> getBlogList(HttpSession session){
		UserInfo user=(UserInfo)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogPosts=blogDAO.getBlogPosts();
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable(value="id") int id,HttpSession session){
		UserInfo user=(UserInfo)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost=blogDAO.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBlogPost( @RequestBody BlogPost blogPost,HttpSession session) {
		UserInfo user=(UserInfo)session.getAttribute("user");// type casting
		if(user==null){
			Error error=new Error(1,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
         BlogPost addedBlogPost= blogDAO.addBlogPost(user, blogPost);
 		
         try {
			email.send(user, "hello "+user.getUsername()+", You post a Blog", "Welcome to StayConnected! Your blog "+blogPost.getBlogTitle()+" is posted successfully. Thank You");
		} catch (MessagingException e) {
		  	  System.out.println("blogController exception in create blog");
			e.printStackTrace();
		}
         return new ResponseEntity<BlogPost>(addedBlogPost,HttpStatus.OK);
    }
	@RequestMapping(value="/getcomments/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogComments(@PathVariable(value="blogId")int blogId,HttpSession session){
		UserInfo user=(UserInfo)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments=blogDAO.getComments(blogId);
		System.out.println("BLOGCOMMENTS::: " + blogComments.size() );
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);		
	}
	
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<?> addBlogComment( @RequestBody BlogComment blogComment,HttpSession session) {
    	UserInfo user=(UserInfo)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println("COMMENT is " + blogComment);
		System.out.println("COMMENT BODY " + blogComment.getBlogDescription());
		
		System.out.println("BLOG POST FROM BLOGCOMMENT " + blogComment.getBlogPost());
		BlogPost blogPost=blogDAO.getBlogPost(blogComment.getBlogPost().getId());
		if(blogPost==null){
			Error error=new Error(2,"Blogpost not found");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
		}
        BlogPost createdBlogPost= blogDAO.addBlogComment(user, blogComment);
        return new ResponseEntity<BlogPost>(createdBlogPost,HttpStatus.OK);
    }
    
    
    
    
    
  //http://localhost:8080/appname/person/1   , PUT  -> DispatcherServlet ->
 // handler -> find a method in controller which handle the request
 @RequestMapping(value="/get/{id}",method=RequestMethod.PUT)
 public ResponseEntity<?> updateBlog(
 		@PathVariable int id,@RequestBody BlogPost blogPost,HttpSession session) {
	 UserInfo user=(UserInfo)session.getAttribute("user");
 	//person -> from client
 	//updatedPerson -> from database 
	 BlogPost updatedBlog=blogDAO.updateBlog(id, blogPost);
 	if(blogPost==null)
 		return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);

 	try {
		email.send(user, "hello "+user.getUsername()+", You edit a blog", "Welcome to StayConnected! Your blog "+blogPost.getBlogTitle()+" is edited successfully.");
	} catch (MessagingException e) {
  	  System.out.println("blogController exception in edit blog");

		e.printStackTrace();
	}
 	return new ResponseEntity<BlogPost>(updatedBlog,HttpStatus.OK);
 	
 }

 @RequestMapping(value="/get/{id}", method=RequestMethod.DELETE)
 public ResponseEntity<Void> deleteBlog(@PathVariable("id") int id,HttpSession session) {
	 UserInfo user=(UserInfo)session.getAttribute("user");
 	System.out.println("Delete function at blog controller1");
 	BlogPost blogpost = blogDAO.getBlogPost(id);
 			if(blogpost==null)
 			{
 				System.out.println("Delete function at blog controller2");
 				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

 			}
 			System.out.println("Delete function at blog controller3");
 			blogDAO.deleteBlog(id);
 			try {
				email.send(user, "hello "+user.getUsername()+", Your blog is deleted", "Welcome to StayConnected! Your blog "+blogpost.getBlogTitle()+" is deleted successfully.");
			} catch (MessagingException e) {
			  	  System.out.println("blogController exception in delete blog");
				e.printStackTrace();
			}

 			return new ResponseEntity<Void>(HttpStatus.OK);
 		}

	
}
