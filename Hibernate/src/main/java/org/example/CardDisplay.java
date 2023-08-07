package org.example;

import javax.persistence.*;


@Entity
@Table(name="cardsindeck")
public class CardDisplay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardname;
    private int cardpoints;

    @ManyToOne
    @JoinColumn(name="deckid", nullable = false)
    private Deck deck;

    public CardDisplay(String cardname, int cardpoints, Deck deck) {
        this.cardname = cardname;
        this.cardpoints = cardpoints;
        this.deck = deck;
    }

    public CardDisplay() {
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public int getCardpoints() {
        return cardpoints;
    }

    public void setCardpoints(int cardpoints) {
        this.cardpoints = cardpoints;
    }

    @Override
    public String toString() {
        return "CardDisplay{" +
                "id=" + id +
                ", cardname='" + cardname + '\'' +
                ", cardpoints=" + cardpoints +
                '}';
    }
}
