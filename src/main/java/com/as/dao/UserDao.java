package com.as.dao;

import com.as.model.UserOld;

public interface UserDao {
	
	UserOld findByUsername(String username);

}
