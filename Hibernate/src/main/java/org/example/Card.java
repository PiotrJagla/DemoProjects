package org.example;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany
    private List<Deck> deck;



    public int getId() {
        return id;
    }

    public Card setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public List<Deck> getDeck() {
        return deck;
    }

    public Card setDeck(List<Deck> deck) {
        this.deck = deck;
        return this;
    }
}
