package com.niit.stayconnected.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "UserInfo")
@Component
public class UserInfo {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int userId;
	
@Column(unique=true,nullable=false)
@Size(min = 4, max = 16)
@NotBlank (message = "Enter a valid User Name !")
private String username;

@Column(nullable=false)
@Size(min = 4, max = 16)
@NotBlank (message = "Enter a password!")
private String password;

@Column(unique=true,nullable=false)
@Email
private String email;

@Column
private String gender;

@Column
private String city;

@Column
private int mobile;

//STUDENT, ALUMINI,EMPLOYEE, ADMIN
@Column
private String userrole;

@Column
@NotNull
private char approved; //'Y','N' by admin

	//inOnline - true, false
	//user login - make this isOnline as true - login 
	//user logout - make this isOnline as false - logout
	@Column(name="isOnline")
	private char isOnline;
	
	@Transient
	private MultipartFile displayimage;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public char getApproved() {
		return approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}

	public MultipartFile getDisplayimage() {
		return displayimage;
	}

	public void setDisplayimage(MultipartFile displayimage) {
		this.displayimage = displayimage;
	}
	


}
