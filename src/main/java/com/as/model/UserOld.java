package com.as.model;

import javax.persistence.Entity;
import javax.persistence.Id;

public class UserOld {
    private String username;
    private String password;
    public UserOld(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public UserOld() {
        super();
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]";
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

}


