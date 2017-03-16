package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.UserInfo;

public interface UserDAO {

	UserInfo authenticate(UserInfo user);
    
	void updateUser(UserInfo user);
    
	UserInfo registerUser(UserInfo user);
	
	public List<UserInfo> getAllUsers(UserInfo user);
	
}