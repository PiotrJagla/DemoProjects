package com.example.thebestide;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class MainController {
    public static final String srcDir = "./proj";

    private String currentFile;

    public void printToConsole() {
        List<CardDisplay> deck = new ArrayList<>() {{
            add(new CardDisplay().setPoints(10).setName("Giant"));
            add(new CardDisplay().setPoints(1).setName("Mouse"));
            add(new CardDisplay().setPoints(3).setName("WItch"));

        }};


    }

    public void printFileContent() {
        ProcessBuilder pb = new ProcessBuilder("cat", "test.txt");
        pb.directory(new File(srcDir));

        try {
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = p.waitFor();

            System.out.println(exitVal + " with code this is the code");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

class CardDisplay{
    private String name;
    private int points;

    public String getName() {
        return name;
    }

    public CardDisplay setName(String name) {
        this.name = name;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public CardDisplay setPoints(int points) {
        this.points = points;
        return this;
    }
}



class ObjectState implements Serializable{
    private static final long serializationUID = 1l;
    private String name;
    private String surname;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ObjectState{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}

