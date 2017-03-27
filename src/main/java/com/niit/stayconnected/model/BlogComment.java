package com.niit.stayconnected.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
/*@Table(name="A_BlogComment")*/
@Component
public class BlogComment implements Serializable{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;

private Date createdOn;

@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name = "create_by_id")
private UserInfo BlogAuthor;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="blog_post_id")
private BlogPost blogPost;

private String BlogDescription;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Date getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}

public UserInfo getBlogAuthor() {
	return BlogAuthor;
}

public void setBlogAuthor(UserInfo blogAuthor) {
	BlogAuthor = blogAuthor;
}

public BlogPost getBlogPost() {
	return blogPost;
}

public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}

public String getBlogDescription() {
	return BlogDescription;
}

public void setBlogDescription(String blogDescription) {
	BlogDescription = blogDescription;
}
	
}