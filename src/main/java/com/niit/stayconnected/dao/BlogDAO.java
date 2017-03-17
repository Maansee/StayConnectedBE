package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.BlogComment;
import com.niit.stayconnected.model.BlogPost;
import com.niit.stayconnected.model.UserInfo;

public interface BlogDAO {

	List<BlogPost> getBlogPosts();
	
	BlogPost getBlogPost(int id);
	
	BlogPost addBlogPost(UserInfo user,BlogPost blogPost);
	
	List<BlogComment> getComments(int blogId);
	
	BlogPost addBlogComment(UserInfo user,BlogComment Comment);
	
	BlogPost updateBlog(int id,BlogPost blogPost);
	
	void deleteBlog(int id);
}
