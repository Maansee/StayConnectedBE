package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.UserInfo;

public interface UserDAO {

	public UserInfo get(int userId, String password);

	public UserInfo get(int userId);

	public boolean save(UserInfo user);

	public boolean update(UserInfo user);

	public void delete(int userId);

	public UserInfo authenticate(int userId, String password);

	public List<UserInfo> getAllUsers(UserInfo user);
	
	public void setOnline(String userID);

	public void setOffLine(String userID);
}
