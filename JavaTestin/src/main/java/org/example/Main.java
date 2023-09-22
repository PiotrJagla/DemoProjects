package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    private static final String connectionString = "mongodb://localhost:27017";
    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = mongoDatabase.getCollection("cards");

            Bson bson = BasicDBObject.parse("{'points': 55}");
            Document doc = collection.find(bson).first();
            if(doc != null) {
                System.out.println(doc.toJson());
            }
            else {
                System.out.println("Null");
            }



        }
    }
}