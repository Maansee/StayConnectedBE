package com.niit.stayconnected.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

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
private String mailid;

@Column
private String gender;

@Column
private String city;

@Column
private int mobileno;

private String ErrorCode;

private String ErrorMessage;


//STUDENT, ALUMINI,EMPLOYEE, ADMIN
private String role;

//enabled - true or false  - active or inactive user 
	//true - authenticated
	//false - user cannot login
	@Column(name="enabled")
	private boolean enabled;

	//inOnline - true, false
	//user login - make this isOnline as true - login 
	//user logout - make this isOnline as false - logout
	@Column(name="isOnline")
	private boolean isOnline;

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

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getMobileno() {
		return mobileno;
	}

	public void setMobileno(int mobileno) {
		this.mobileno = mobileno;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}


}
