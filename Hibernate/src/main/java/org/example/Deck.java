package org.example;

import javax.persistence.OneToMany;
import java.util.List;

public class Deck {
    private long id;
    private String name;

    @OneToMany(mappedBy ="decks")
    private List<CardDisplay> cards;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
