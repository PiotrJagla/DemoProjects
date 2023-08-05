package org.example;


import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");

        //CREATE
        EntityManager em = etf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = new User("Piotek", LocalDate.now());
            em.persist(user);
            et.commit();
        }
        catch (Exception e ) {
            if(et != null ) {
                et.rollback();
            }
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
        }

        //READ ONE
        em = etf.createEntityManager();
        String query = "SELECT u FROM users u WHERE u.id = 1";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("id", 1);
        User gettedUser = null;
        try{
            gettedUser = tq.getSingleResult();
            System.out.println(gettedUser.getId() + " " + gettedUser.getName() + " " + gettedUser.getBirthDate().toString());

        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        //READ MULTIPLE
        em = etf.createEntityManager();
        String allUsersQuery = "SELECT u FROM users u";
        tq = em.createQuery(allUsersQuery, User.class);
        List<User> users;
        try {
            users = tq.getResultList();
            users.forEach(u -> System.out.println(u));
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        //UPDATE
        em = etf.createEntityManager();
        et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = null;
            user = em.find(User.class, 1);
            user.setName("NOWE IMIE");
            em.persist(user);
            et.commit();
        }
        catch (Exception e ) {
            if(et != null ) {
                et.rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        //DELETE
        em = etf.createEntityManager();
        et = null;
        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, 1);
            em.remove(user);
            et.commit();
        }
        catch (Exception e ) {
            if(et != null ) {
                et.rollback();
            }
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally {
            em.close();
        }

        etf.close();

    }
}