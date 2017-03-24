package com.niit.stayconnected.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="A_BlogPost")
@Component
public class BlogPost implements Serializable{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;	

private Date createdOn;

@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name = "create_by_id")
private UserInfo BlogAuthor;

private String BlogTitle;

@Lob
@Column(name="BlogDescription")
private String BlogDescription;

@OneToMany(mappedBy="blogPost",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
@JsonIgnore
private List<BlogComment> comments=new ArrayList<BlogComment>();

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

public String getBlogTitle() {
	return BlogTitle;
}

public void setBlogTitle(String blogTitle) {
	BlogTitle = blogTitle;
}

public String getBlogDescription() {
	return BlogDescription;
}

public void setBlogDescription(String blogDescription) {
	BlogDescription = blogDescription;
}

public List<BlogComment> getComments() {
	return comments;
}

public void setComments(List<BlogComment> comments) {
	this.comments = comments;
}

}