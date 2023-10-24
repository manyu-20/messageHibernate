package com.as.dao2;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfProvider {
    private static EntityManagerFactory factory = null;

    public static EntityManagerFactory getEntityManagerFactory(){
        try{
            System.out.println("hello fetching");
            factory  = Persistence.createEntityManagerFactory("fil23");
            return factory;
        }
        catch (Exception e){
            System.out.println("exception in fetching");
            e.printStackTrace();
            return null;
        }
    }
}
