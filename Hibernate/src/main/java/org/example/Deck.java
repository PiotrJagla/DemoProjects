package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "decks")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany
    private List<Card> cards;



    public int getId() {
        return id;
    }

    public Deck setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Deck setName(String name) {
        this.name = name;
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Deck setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }
}
