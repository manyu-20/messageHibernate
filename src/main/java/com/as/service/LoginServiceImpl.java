package com.as.service;

import com.as.dao.UserDaoImpl;
import com.as.model.UserOld;

public class LoginServiceImpl implements LoginService{

    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public boolean checkLogin(String username, String password) {
        UserOld user = userDao.findByUsername(username);
        if(user == null) return false;
        return user.getPassword().equals(password);
    }

}
