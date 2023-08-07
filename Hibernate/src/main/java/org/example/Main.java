package org.example;


import org.example.OneToOne.Phone;
import org.example.OneToOne.PhoneDetails;
import org.hibernate.Session;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        oneToOneLoading();
    }
    public static void oneToOneSaving() {
        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        Session s = etf.createEntityManager().unwrap(Session.class);
        Phone phone = new Phone();
        phone.setNumber("123-456-789");
        PhoneDetails phoneDetails= new PhoneDetails();
        phoneDetails.setProvider("T-mobile");
        phoneDetails.setTechnology("5G");

        s.getTransaction().begin();
        phone.setDetails(phoneDetails);
        phoneDetails.setPhone(phone);
        s.persist(phone);
        s.getTransaction().commit();
        s.close();

    }
    public static void oneToOneLoading() {
        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        Session s = etf.createEntityManager().unwrap(Session.class);

        s.getTransaction().begin();

//        Phone phone = null;
        PhoneDetails phoneDetails = null;
//        phone = s.find(Phone.class, 2l);
        phoneDetails = s.find(PhoneDetails.class, 2l);

        System.out.println(phoneDetails);
//        System.out.println(phone);


        s.getTransaction().commit();
        s.close();
    }
    public static void oneToManyFetching() {
        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        Session s = etf.createEntityManager().unwrap(Session.class);


        TypedQuery<Deck> tq = s.createQuery("SELECT d FROM Deck d WHERE username = :uname", Deck.class);
        tq.setParameter("uname", "piotr");
        List<Deck> decks = tq.getResultList();
        decks.forEach(d -> System.out.println(d));


//        TypedQuery<CardDisplay> newTq = s.createQuery("SELECT c FROM CardDisplay c WHERE deckid = :did", CardDisplay.class);
//        for (int i = 0; i < decks.size(); i++) {
//            System.out.println("For deck | " + decks.get(i).getName() + " | there are cards: ");
//
//            newTq.setParameter("did", decks.get(i).getId());
//            List<CardDisplay> cardsInDeck = newTq.getResultList();
//            cardsInDeck.forEach(c -> System.out.println());
//            System.out.println("Owner: " + decks.get(i).getUsername());
//        }



    }
    public static void oneToManySaving() {
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