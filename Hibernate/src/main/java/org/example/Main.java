package org.example;

import org.hibernate.Session;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import javax.swing.plaf.synth.SynthUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        Session s =  etf.createEntityManager().unwrap(Session.class);


        Room first = new Room();
        first.setRoomName("kitchen");
        first.setArea(15.6f);

        Room second= new Room();
        second.setRoomName("livingRoom");
        second.setArea(10.0f);

        User u = new User();
        u.setName("Piotrek");
        User u2 = new User();
        u2.setName("Worek");

        first.setUser(u);
        second.setUser(u2);


        s.getTransaction().begin();
//        //SAVE
//
//        s.persist(u);
//        s.persist(u2);
//
//        s.persist(first);
//        s.persist(second);

        s.getTransaction().commit();
        s.getTransaction().begin();
        //READ
        TypedQuery<User> tq = s.createQuery("SELECT u FROM User u", User.class);
        List<User> users = tq.getResultList();
        for (User user : users) {
            System.out.println(user);

        }


        s.getTransaction().commit();
        s.close();

    }
}