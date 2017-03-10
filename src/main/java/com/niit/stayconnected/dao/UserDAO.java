package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.UserInfo;

public interface UserDAO {
	
	public List<UserInfo> list();
	
	public List<UserInfo> getAllUsers(UserInfo user);

	public UserInfo get(int userId, String password);

	public UserInfo get(int userId);

	public boolean save(UserInfo user);

	public boolean update(UserInfo user);

	public void delete(int userId);

	public UserInfo authenticate(int userId, String password);

	public void setOnline(String userId);

	public void setOffLine(String userId);

	
}