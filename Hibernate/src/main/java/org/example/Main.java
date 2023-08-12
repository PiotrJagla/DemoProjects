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
        hibernate();
    }
    private static void hibernate() {
        EntityManagerFactory etf = Persistence.createEntityManagerFactory("test-unit");
        EntityManager s =  etf.createEntityManager();

        s.getTransaction().begin();
        Data d1 = new Data().setAge("16").setWeigth("77 kg").setPhone("111222333");
        Data d2 = new Data().setAge("46").setWeigth("27 kg").setPhone("123456789");


        Apartment a1 = new Apartment();
        a1.setName("stary Budynek");
        Apartment a2 = new Apartment();
        a2.setName("Nowy budynek");

        Tenant t1 = new Tenant();
        t1.setName("Sztanga");
        t1.setData(d1);
        Tenant t2 = new Tenant();
        t2.setName("ZIemia");
        t2.setData(d2);

        a1.setLocators(List.of(t1,t2));
        a2.setLocators(List.of(t2));

        t1.setLivingPlaces(List.of(a1));
        t2.setLivingPlaces(List.of(a1,a2));

        s.persist(t1);
        s.persist(t2);
        s.persist(a1);
        s.persist(a2);
        t1.setName("zmieniony po persist");

        s.detach(a1);
        a1.setName("nie powino sie pokazac to");


        s.getTransaction().commit();
        s.getTransaction().begin();

        TypedQuery<Apartment> tq = s.createQuery("SELECT a FROM Apartment a", Apartment.class);
        List<Apartment> apartments = tq.getResultList();
        for (Apartment a : apartments) {
            System.out.println("Locators of apartment with name: " + a.getName());
            a.getLocators().forEach(l -> System.out.println(l.getName() + l.getData()));
        }

        List<Tenant> tenants = s.createQuery("SELECT t FROM Tenant t", Tenant.class).getResultList();
        for (Tenant t : tenants) {
            System.out.println("tenant of name: " + t.getName() + " is living in bildings with name: ");
            t.getLivingPlaces().forEach(a -> System.out.println(a.getName()));
        }


        Apartment a3 = s.find(Apartment.class, 1);
        a3.setName("changed");

        s.getTransaction().commit();
        s.close();


    }
}