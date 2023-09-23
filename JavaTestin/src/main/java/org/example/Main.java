package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.management.ObjectName;
import javax.print.Doc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static final String connectionString = "mongodb://localhost:27017";
    public static void main(String[] args) {

        CRUD crud = new MongoDB();
        crud.createDeck("df", "talisfddsfa");

        List<Deck> decks = crud.getAllDecks();
        decks.forEach(System.out::println);



    }
}

class MongoDB implements CRUD {


    @Override
    public void createDeck(String username, String deckname) {
        try(MongoClient client = MongoClients.create(Main.connectionString)) {
            MongoDatabase db = client.getDatabase("cardgame");
            MongoCollection collection = db.getCollection("cards");

            Document quert = new Document(){{
                put("deckname", deckname);
                put("username", username);
                put("cards", new ArrayList<Document>());
            }};

            collection.insertOne(quert);
        }

    }

    @Override
    public void deleteDeck(String username, String deckname) {

    }

    @Override
    public void addCard(String username, String deckname, CardDisplay card) {

    }

    @Override
    public void deleteCard(String username, String deckname, CardDisplay card) {

    }

    @Override
    public Deck getDeck(String username, String deckname) {
        return null;
    }

    @Override
    public List<Deck> getAllDecks() {
        try(MongoClient client = MongoClients.create(Main.connectionString)) {
            MongoDatabase db = client.getDatabase("cardgame");
            MongoCollection collection = db.getCollection("cards");


            FindIterable<Document> cursor = collection.find();
            List<Deck> decks = new ArrayList<>();
            try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
                while (cursorIterator.hasNext()) {
                    Document doc = cursorIterator.next();
                    List<Document> docCards = (List<Document>) doc.get("cards");

                    for (int i = 0; i < docCards.size(); i++) {
                        
                    }
                    
                    System.out.println(cursorIterator.next());
                }
            }
        }
        return List.of();
    }
}

class FileSerialization implements CRUD  {

    @Override
    public void createDeck(String username, String deckname) {

        try {
            ArrayList<Deck> decks = getAllDecks();
            Deck d = new Deck();
            d.setDeckname(deckname);
            d.setUsername(username);
            decks.add(d);

            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("decks.txt"));
            ous.writeObject(decks);
            ous.reset();
            ous.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteDeck(String username, String deckname) {

    }

    @Override
    public void addCard(String username, String deckname, CardDisplay card) {

    }

    @Override
    public void deleteCard(String username, String deckname, CardDisplay card) {

    }

    @Override
    public Deck getDeck(String username, String deckname) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("decks.txt"));
            Deck d = (Deck)ois.readObject();
            System.out.println(d);
            ois.close();
            return d;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return new Deck();
    }

    @Override
    public ArrayList<Deck> getAllDecks() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("decks.txt"));
            ArrayList<Deck> decks = (ArrayList<Deck>)ois.readObject();
            ois.close();
            return decks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Deck>();
    }
}



interface CRUD  {
    void createDeck(String username, String deckname);
    void deleteDeck(String username, String deckname);
    void addCard(String username, String deckname, CardDisplay card);
    void deleteCard(String username, String deckname, CardDisplay card);
    Deck getDeck(String username, String deckname);
    List<Deck> getAllDecks();
}




class Deck implements Serializable{
    private List<CardDisplay> cards;
    private String username;
    private String deckname;

    public Deck() {
        username = "";
        deckname = "";
        cards = new ArrayList<>();
    }

    public List<CardDisplay> getCards() {
        return cards;
    }

    public void setCards(List<CardDisplay> cards) {
        this.cards = cards;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }

    @Override
    public String toString() {
        return "{" +
                "" + cards +
                ", ='" + username + '\'' +
                ", ='" + deckname + '\'' +
                '}';
    }
}
class CardDisplay implements Serializable{

    public CardDisplay(String name, int points) {
        this.name = name;
        this.points = points;
    }

    private String name;
    private int points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object obj) {
        CardDisplay c = (CardDisplay) obj;
        return c.getName().equals(name);
    }

    @Override
    public String toString() {
        return "{" +
                "'" + name + '\'' +
                ",=" + points +
                '}';
    }
}