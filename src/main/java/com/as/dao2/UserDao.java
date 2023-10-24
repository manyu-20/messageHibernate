package com.as.dao2;

import com.as.hiber.User;

public interface UserDao {
    public boolean getUserDetails(String userName,String password);
}
