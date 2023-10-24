package com.as.dao2;

import com.as.hiber.Messages;
import com.as.model.MessagesOld;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;

public class MessagesDaoImp  implements MessagesDao{





    @Override
    public List<Messages> OutboxMessages(String username, int pageNumber) {
        EntityManager em = null;
        List<Messages> ls = null;
        try{
            int itemsPerPage = 5; // Change this to the desired items per page
            System.out.println("page number = " + pageNumber);
            int offset = (pageNumber - 1) * itemsPerPage;
            System.out.println("reading using hibernate");
            em = EmfProvider.getEntityManagerFactory().createEntityManager();
            String jpql = "SELECT m FROM Messages m WHERE m.fromUser = :username";
            ls = em.createQuery(jpql, com.as.hiber.Messages.class).setParameter("username",username)
                    .setFirstResult(offset)
                    .setMaxResults(itemsPerPage)
                    .getResultList();
            ls.forEach((item) -> System.out.println(item));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            assert em != null;
            em.close();
            return ls;
        }
    }

    @Override
    public List<Messages> InboxMessages(String username,int pageNumber) {
        EntityManager em = null;
        List<Messages> ls = null;
        try{
            int itemsPerPage = 5; // Change this to the desired items per page

            int offset = (pageNumber - 1) * itemsPerPage;
            System.out.println("reading using hibernate");
            em = EmfProvider.getEntityManagerFactory().createEntityManager();
            String jpql = "SELECT m FROM Messages m WHERE m.toUser = :username";
            ls = em.createQuery(jpql, com.as.hiber.Messages.class).setParameter("username",username)
                    .setFirstResult(offset)
                    .setMaxResults(itemsPerPage)
                    .getResultList();
            ls.forEach((item) -> System.out.println(item));
            return ls;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            assert em != null;
            em.close();
            return ls;
        }

    }


    @Override
    public void deleteMessage(int id) {
        EntityManager em = null;

        try {
            em = EmfProvider.getEntityManagerFactory().createEntityManager();

            em.getTransaction().begin();
            Messages message = em.find(Messages.class, id);
            if (message != null) {
                em.remove(message);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            assert em != null;
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public void writeMessages(Messages message) {
        EntityManager em = null;
        EntityTransaction transaction = null;

        try{
            em = EmfProvider.getEntityManagerFactory().createEntityManager();
            transaction =  em.getTransaction();
            transaction.begin();
            em.persist(message);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle or log the exception as needed
        } finally {
            em.close();
        }

    }


}
