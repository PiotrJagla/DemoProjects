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

        Deck d1 = new Deck().setName("symbioza");
        Deck d2 = new Deck().setName("niedzwiedzie");

        s.persist(d1);
        s.persist(d2);

        Card c1 = new Card().setName("hemdall").setDeck(List.of(d1, d2));
        Card c2 = new Card().setName("kurczak").setDeck(List.of(d1));
        Card c3 = new Card().setName("pieczony").setDeck(List.of( d2));
        Card c4 = new Card().setName("kotlet").setDeck(List.of());

        s.persist(c1);
        s.persist(c2);
        s.persist(c3);
        s.persist(c4);

        d1.setCards(List.of(c1, c3));
        d2.setCards(List.of(c2, c4));

        s.getTransaction().commit();
        s.getTransaction().begin();


        TypedQuery<Deck> tq = s.createQuery("SELECT d FROM Deck d", Deck.class);
        List<Deck> decks = tq.getResultList();
        Deck deck = decks.get(0);

        TypedQuery<Card> tq2 = s.createQuery("SELECT c FROM Card c", Card.class);
        List<Card> cards = tq2.getResultList();
        Card card = cards.get(0);

        System.out.println("Card of deck | " + deck.getName() + " | are:");
        for (int i = 0; i < deck.getCards().size(); i++) {
            System.out.println(deck.getCards().get(i).getName());
        }


        System.out.println("Decks that card: | " + card.getName() + " | are: ");
        for (int i = 0; i < card.getDeck().size(); i++) {
            System.out.println(card.getDeck().get(i).getName());
        }

        s.getTransaction().commit();
        s.close();


    }
}