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
        ObjectState s = new ObjectState();
        s.setName("name");
        s.setSurname("surname");
        s.setAge(16);
        ObjectState s2 = new ObjectState();
        s2.setName("other name");
        s2.setSurname("other surname");
        s2.setAge(196);

        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./proj/test.txt"));
//            oos.writeObject(s);
//            oos.writeObject(s2);
//            oos.close();
//            printFileContent();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./proj/test.txt"));
            ObjectState in;
            List<ObjectState> objects = new ArrayList<>();
            while((in = (ObjectState) ois.readObject()) != null) {
                objects.add(in);
            }
            System.out.println(objects);



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


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

