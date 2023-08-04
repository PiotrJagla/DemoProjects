package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        User user = new User("Piotek", LocalDate.now());

        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();


                session.persist(user);

                session.getTransaction().commit();
            }

        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }


    }
}