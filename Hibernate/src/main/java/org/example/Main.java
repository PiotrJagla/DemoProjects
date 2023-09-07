package org.example;

import org.hibernate.Session;
import org.hibernate.collection.internal.PersistentArrayHolder;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import javax.swing.plaf.synth.SynthUI;
import java.lang.reflect.Type;
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

        Pracownik p1 = new Pracownik();
        p1.setImie("piotrek");
        p1.setNazwaOddzialu("krakow1");
        p1.setStanowisko("ksiegowy");

        Pracownik p2 = new Pracownik();
        p2.setImie("nowy");
        p2.setNazwaOddzialu("krakow1");
        p2.setStanowisko("ksiegowy");
        p2.setPrzelozony(p1);

        OddzialBanku o1 = new OddzialBanku();
        o1.setNazwaOddialu("krakow1");
        o1.setKierownik(p1);

        p1.setPodopiecznyOddzial(o1);

        s.persist(p1);
        s.persist(p2);


        s.persist(o1);

        Klient k1 = new Klient();
        k1.setImie("klien1");
        k1.setOpiekun(p1);

        Klient k2 = new Klient();
        k2.setImie("klien2");
        k2.setOpiekun(p1);

        Klient k3= new Klient();
        k3.setImie("klien3");
        k3.setOpiekun(p2);

        s.persist(k1);
        s.persist(k2);
        s.persist(k3);

        p1.setKlienci(List.of(k1,k2));
        p2.setKlienci(List.of(k3));

        s.getTransaction().commit();
        s.getTransaction().begin();
        System.out.println("---------- pracownik i kierownik ---------");
        TypedQuery<Pracownik> q = s.createQuery("SELECT p FROM Pracownik p WHERE imie = :pname",Pracownik.class );
        q.setParameter("pname", "nowy");

        Pracownik res = q.getSingleResult();
        System.out.println(res.getImie());
        System.out.println(res.getId());
        System.out.println(res.getPrzelozony().getImie());


        System.out.println("---------- pracownik i oddzial ---------");
        TypedQuery<Pracownik> q2 = s.createQuery("SELECT p FROM Pracownik p WHERE imie = :pname",Pracownik.class );
        q2.setParameter("pname", "piotrek");

        Pracownik res2 = q2.getSingleResult();
        System.out.println(res2.getImie());
        System.out.println(res2.getId());
        System.out.println(res2.getPodopiecznyOddzial().getNazwaOddialu());


        System.out.println("---------- oddzial i pracownik ---------");
        TypedQuery<OddzialBanku> q3 = s.createQuery("SELECT o FROM OddzialBanku o", OddzialBanku.class);
        OddzialBanku res4 = q3.getResultList().get(0);
        System.out.println(res4.getNazwaOddialu());
        System.out.println(res4.getKierownik().getImie());

        s.getTransaction().commit();
        s.getTransaction().begin();

        System.out.println("---------- klienci i opiekunowie ---------");
        TypedQuery<Pracownik> q4 = s.createQuery("SELECT p FROM Pracownik p", Pracownik.class);
        List<Pracownik> pracowniks = q4.getResultList();

        for (var pr : pracowniks) {
            System.out.println(pr.getImie());
            pr.getKlienci().stream().map(k -> k.getImie()).forEach(b -> System.out.println(b));
        }

        System.out.println("---------- opiekunowie i klienci---------");
        TypedQuery<Klient> q5 = s.createQuery("SELECT k FROM Klient k", Klient.class);
        List<Klient> klients = q5.getResultList();

        for (var kl : klients) {
            System.out.println(kl.getImie());
            System.out.println(kl.getOpiekun().getImie());
        }

        s.getTransaction().commit();
        s.close();

    }
}