package com.niit.stayconnected.dao;

import com.niit.stayconnected.model.ProfilePhoto;

public interface FileUploadDAO {

	void save(ProfilePhoto uploadFile);
	ProfilePhoto getFile(String username);
}
