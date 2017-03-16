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
@Table(name = "C_UserInfo")
@Component
public class UserInfo {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	
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


//STUDENT, ALUMINI,EMPLOYEE, ADMIN
@Column
private String role;

	//enabled - true or false  - active or inactive user 
	//true - authenticated
	//false - user cannot login
	@Column(name="enabled")
	private boolean enabled;

	//inOnline - true, false
	//user login - make this isOnline as true - login 
	//user logout - make this isOnline as false - logout
	@Column(name="isonline")
	private boolean isOnline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return enabled;
	}

	public void setStatus(boolean status) {
		this.enabled = status;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	@Override
	public String toString() {
		return this.username + " " + this.email + " " + this.role + "\n";
	}
	
}
