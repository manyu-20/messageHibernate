package com.as.dao2;

import com.as.hiber.User;

import javax.persistence.EntityManager;

public class UserDaoImp implements  UserDao{

    @Override
    public boolean getUserDetails(String userName,String password) {
        EntityManager em = EmfProvider.getEntityManagerFactory().createEntityManager();
        System.out.println("username : " + userName);
        User user = em.find(User.class, userName);
        System.out.println("noob");
        if(user == null){
            return false;
        }
        return user.getPassword().equals(password);

    }
}
