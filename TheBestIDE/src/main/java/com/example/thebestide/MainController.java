package com.example.thebestide;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Scanner;


public class MainController {
    public static final String srcDir = "./proj";

    private String currentFile;

    public void printToConsole() {
        ObjectState s = new ObjectState();
        s.setName("piotrek");
        s.setSurname("Jagla");
        s.setAge(20);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("testFile.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(s);
            objectOutputStream.flush();
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("testFile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ObjectState s2 = (ObjectState) objectInputStream.readObject();
            objectInputStream.close();

            System.out.println(s);
            System.out.println(s2);

        }
        catch(Exception e ) {
            System.out.println(e.getMessage());
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write("to jest zapisane z kodu javy");
            writer.write("to jest jeszcze jedna linia");
            for (int i = 0; i < 10; i++) {
                writer.write("Countint: " + i + "\n");
            }
            writer.close();

            ProcessBuilder pb = new ProcessBuilder("cat", "output.txt");
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());

        }

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
