package org.example;


import org.hibernate.Session;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        oneToMany();



    }
    public static void oneToMany() {
        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        Session s = etf.createEntityManager().unwrap(Session.class);

        Deck deck = new Deck();
        deck.setName("talia");

        Deck anotherDeck = new Deck();
        deck.setName("taktyki");
        List<Deck> decks = new ArrayList<>() {{
            add(new Deck("talia", "piotr"));
            add(new Deck("nowy", "piotr"));
            add(new Deck("taliaMeli", "mela"));
            add(new Deck("kolejnaTaliaMeli", "mela"));
        }};

        List<CardDisplay> cards = new ArrayList<>() {{
            add(new CardDisplay( "warrior", 5, decks.get(0)));
            add(new CardDisplay( "knight", 3, decks.get(0)));
            add(new CardDisplay( "witch", 2, decks.get(1)));
            add(new CardDisplay( "karta", 2, decks.get(1)));
            add(new CardDisplay( "piorun", 0, decks.get(2)));
            add(new CardDisplay( "archer", 2, decks.get(2)));
            add(new CardDisplay( "longer", 7, decks.get(3)));
            add(new CardDisplay( "mocneTo", 9, decks.get(3)));
            add(new CardDisplay( "giant", 6, decks.get(3)));
        }};

        s.getTransaction().begin();

        decks.forEach(d -> s.persist(d));
        cards.forEach(c -> s.persist(c));

        s.getTransaction().commit();
    }

    public static void hibernate() {
        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        EntityManager em = etf.createEntityManager();
        Session s = em.unwrap(Session.class);
        s.beginTransaction();

        //Create
//        User user = new User("Deeel", LocalDate.now());
//        s.persist(user);

        //Read
        List<User> users = s.createQuery("SELECT u FROM User u", User.class).list();
        users.forEach(System.out::println);


        s.getTransaction().commit();
        s.close();
    }
    public static void JPA() {

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
        String query = "SELECT u FROM User u WHERE u.id = :userId";
        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("userId", 1);
        User gettedUser = null;
        try{
            gettedUser = tq.getSingleResult();
            System.out.println(gettedUser);

        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        //READ MULTIPLE
        em = etf.createEntityManager();
        String allUsersQuery = "SELECT u FROM User u";
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
            user = em.find(User.class, 5);
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
            User user = em.find(User.class, 4);
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