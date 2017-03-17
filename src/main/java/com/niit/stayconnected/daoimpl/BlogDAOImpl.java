package com.niit.stayconnected.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.stayconnected.dao.BlogDAO;
import com.niit.stayconnected.model.BlogComment;
import com.niit.stayconnected.model.BlogPost;
import com.niit.stayconnected.model.UserInfo;

@Repository
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BlogPost> getBlogPosts() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogPost");
		List<BlogPost> blogPosts=query.list();
		session.close();
		return blogPosts;
	}

	@Override
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		session.close();
		return blogPost;
	}

	@Override
	public BlogPost addBlogPost(UserInfo user, BlogPost blogPost) {
		Session session=sessionFactory.openSession();
		blogPost.setBlogAuthor(user);
		blogPost.setCreatedOn(new Date());
		session.save(blogPost);
		session.flush();
		BlogPost addedBlogPost=(BlogPost)session.get(BlogPost.class, blogPost.getId());
		return addedBlogPost;
	}

	@Override
	public List<BlogComment> getComments(int blogId) {
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogId);
		List<BlogComment> blogComments=blogPost.getComments();
		session.close();
		return blogComments;
	}

	@Override
	public BlogPost addBlogComment(UserInfo user, BlogComment blogComment) {
		Session session=sessionFactory.openSession();
		 blogComment.setBlogAuthor(user);
		 blogComment.setCreatedOn(new Date());
		 BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogComment.getBlogPost().getId());
		 blogComment.setBlogPost(blogPost);
		 session.merge(blogComment);
		 session.flush();
		 session.close();
		 return blogPost;
	}

	@Override
	public BlogPost updateBlog(int id, BlogPost blogPost) {
		//person -> modified value -> 226
		Session session=sessionFactory.openSession();
		//current person -> 226
		//currentPerson, person -> with same id
		//updating only variable person
		//notunique
		//select [before modification]
		
		System.out.println("edit blog1");
		BlogPost currentBlog=(BlogPost)session.get(BlogPost.class, id);//persistent
		if(currentBlog==null) //id doesnt exist in the database
			return null;
		//to modify [update]
		session.merge(blogPost); //update query where personid=?
		//select [after modification]
		BlogPost updatedBlog=(BlogPost)session.get(BlogPost.class, id); //select query
		session.flush();
		session.close();
		return updatedBlog;
	}

	@Override
	public void deleteBlog(int id) {
		Session session = sessionFactory.openSession();
		// it makes the object persistent - person
		BlogPost blog = (BlogPost)session.get(BlogPost.class,id);
		session.delete(blog);
		// transient - person
		session.flush();
		session.close();
	}	
	
	
	
}
