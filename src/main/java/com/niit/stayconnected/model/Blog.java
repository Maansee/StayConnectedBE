package com.niit.stayconnected.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "Blog")
@Component
public class Blog {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogId;
	
	@Column
	@Size(max=50)
	@NotBlank(message="Blog title should not be blank")
	private String blogTitle;
	
	@Column
	@Size(min=50)
	@NotBlank(message="Blog Description should not be blank")
	private String blogDescription;
	
	@Column
	private Date blogDate;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "userid")
	private UserInfo BlogAuthor;
	
	@Column
	private char blogStatus;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public Date getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	public UserInfo getBlogAuthor() {
		return BlogAuthor;
	}

	public void setBlogAuthor(UserInfo blogAuthor) {
		BlogAuthor = blogAuthor;
	}

	public char getBlogStatus() {
		return blogStatus;
	}

	public void setBlogStatus(char blogStatus) {
		this.blogStatus = blogStatus;
	}
}
