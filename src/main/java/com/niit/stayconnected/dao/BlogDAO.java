package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.Blog;

public interface BlogDAO {
	public List<Blog> list();
	
	public Blog getBlog(int BlogId);
	
	public List<Blog>  getAllBlogs();
	
	public List<Blog>  getAllBlogs(String userID);
	
	public boolean save(Blog blog);  //create new blog
	
	public boolean update(Blog blog);
	
	public void delete(int blogId);
	
	public Blog get(int blogId);
	
}
