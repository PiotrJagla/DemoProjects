package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    private static final String connectionString = "mongodb://localhost:27017";
    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("cardgame");
            MongoCollection<Document> collection = mongoDatabase.getCollection("decks");


            //______________________________MONGODB
            //GET DECKS
//            Document query = new Document();
//            query.put("deckname", "potwory");
//            query.put("username", "piotrek");
//
//            Document res = collection.find(query).first();
//            List<Document> cardsInDoc = (List<Document>)res.get("cards");
//            List<CardDisplay> gettedCardDisplays = new ArrayList<>();
//            for (int i = 0; i < cardsInDoc.size(); i++) {
//                gettedCardDisplays.add(
//                        new CardDisplay(
//                                (String)cardsInDoc.get(i).get("name"), (int)cardsInDoc.get(i).get("points")
//                        )
//                );
//
//            }
//            Deck retrievedDeck = new Deck();
//            retrievedDeck.setUsername((String)res.get("username"));
//            retrievedDeck.setDeckname((String)res.get("deckname"));
//            retrievedDeck.setCards(gettedCardDisplays);
//            System.out.println(retrievedDeck);

            //FIND MANY THINGS
//            Document searchQuery = new Document();
//            searchQuery.put("points", 1);
//            FindIterable<Document> cursor = collection.find(searchQuery);
//            Document result = collection.find(searchQuery).first();
//            System.out.println(result.get("points"));
//
//            try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
//                while (cursorIterator.hasNext()) {
//                    System.out.println(cursorIterator.next());
//                }
//            }

            //ADD CARD TO ARRAY
//            Document findQuery = new Document();
//            findQuery.put("username", "piotrek");
//            findQuery.put("deckname", "ludzie");
//
//            Document updateQuery = new Document();
//
//            Document kartaDoPushniecia = new Document();
//            kartaDoPushniecia.put("name", "breaker");
//            kartaDoPushniecia.put("points", 2);
//
//            Document pushDoc = new Document();
//            pushDoc.put("cards", kartaDoPushniecia);
//
//            updateQuery.put("$push", pushDoc);
//
//            collection.updateMany(findQuery, updateQuery);


            //Delete card from array
//            Document findQuery = new Document(){{
//                put("username", "piotrek"); put("deckname", "potwory");
//            }};
//            findQuery.put("username", "piotrek");
//            findQuery.put("deckname", "potwory");
//
//            Document cardToDelte = new Document() {{put("name", "burn"); put("points", 0); }};
//
//            Document pushToCards = new Document() {{ put("cards", cardToDelte); }};
//
//            Document updateQuery = new Document() {{put("$pull", pushToCards); }};
//
//            collection.updateMany(findQuery, updateQuery);

            //CreateDeck
//            Deck newDeck = new Deck();
//            newDeck.setUsername("ami");
//            newDeck.setDeckname("transformice");
//
//            Document newDeckDoc = new Document(){{
//                put("username", newDeck.getUsername());
//                put("deckname", newDeck.getDeckname());
//                put("cards", List.of());
//            }};
//
//            collection.insertOne(newDeckDoc);

            //Delete deck
//            Document deckToDelete = new Document() {{
//               put("username", "ami");
//                put("deckname", "transformice");
//            }};
//
//            collection.deleteOne(deckToDelete);



            //INSERT DECKS
//            Deck d1 = new Deck();
//            d1.setCards(List.of(
//                    new CardDisplay("knight", 4),
//                    new CardDisplay("burn", 0),
//                    new CardDisplay("giant", 9)));
//            d1.setUsername("piotrek");
//            d1.setDeckname("potwory");
//            Deck d2 = new Deck();
//            d2.setCards(List.of(
//                    new CardDisplay("archer", 2),
//                    new CardDisplay("wild roam", 3),
//                    new CardDisplay("sharpshooter", 5)));
//            d2.setUsername("piotrek");
//            d2.setDeckname("ludzie");
//
//            List<Document> docCards = new ArrayList<>();
//            for (int i = 0; i < d1.getCards().size(); i++) {
//                Document d = new Document();
//                d.put("name", d1.getCards().get(i).getName());
//                d.put("points", d1.getCards().get(i).getPoints());
//                docCards.add(d);
//            }
//            Document deck1 = new Document();
//            deck1.put("username", d1.getUsername());
//            deck1.put("deckname", d1.getDeckname());
//            deck1.put("cards", docCards);
//
//            List<Document> docCards2 = new ArrayList<>();
//            for (int i = 0; i < d2.getCards().size(); i++) {
//                Document d = new Document();
//                d.put("name", d2.getCards().get(i).getName());
//                d.put("points", d2.getCards().get(i).getPoints());
//                docCards2.add(d);
//            }
//            Document deck2 = new Document();
//            deck2.put("username", d2.getUsername());
//            deck2.put("deckname", d2.getDeckname());
//            deck2.put("cards", docCards2);
//
//            collection.insertMany(List.of(deck1, deck2));



            //______________________________FileSTREAM

            //WRITING DECK
//            Deck d1 = new Deck();
//            d1.setUsername("ami");
//            d1.setDeckname("talia");
//            d1.setCards(List.of(new CardDisplay("telefon", 1), new CardDisplay("mileczenie", 2)));
//
//            Deck d2 = new Deck();
//            d2.setUsername("piotrek");
//            d2.setDeckname("ludzie");
//            d2.setCards(List.of(new CardDisplay("lucznik", 1), new CardDisplay("rycesz", 2)));
//
//            try {
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("decks.txt"));
//                objectOutputStream.writeObject(d1);
//                objectOutputStream.writeObject(d2);
//                objectOutputStream.close();
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }


            //READING DECK
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("decks.txt"));

//                Deck readed = (Deck)objectInputStream.readObject();
//                System.out.println(readed);
//
//                Deck readed2 = (Deck)objectInputStream.readObject();
//                System.out.println(readed2);
                Deck readed;
//                while((readed = (Deck)objectInputStream.readObject()) != null) {
//                    System.out.println(readed);
//                }
                while(true) {
                    readed = (Deck)objectInputStream.readObject();
                    if(readed == null)
                        break;
                    System.out.println(readed);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            //Create file with shell
//            ProcessBuilder pb = new ProcessBuilder("touch", "decks.txt");
//
//            try {
//                Process p = pb.start();
//                int e = p.waitFor();
//                System.out.println("Exited with code: " + e);
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
        }

    }
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
    public String toString() {
        return "{" +
                "'" + name + '\'' +
                ",=" + points +
                '}';
    }
}