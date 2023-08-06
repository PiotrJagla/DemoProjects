package org.example;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name ="decks")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;

    @OneToMany(mappedBy ="deck")
    private List<CardDisplay> cards;

    public Deck(String name, String username) {
        this.name = name;
        this.username = username;
        this.cards = cards;
    }

    public Deck() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CardDisplay> getCards() {
        return cards;
    }

    public void setCards(List<CardDisplay> cards) {
        this.cards = cards;
    }
}
