package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Person person = new Person("name", "lastnaem", 19);
        House house = new House();
        house.setArea(11.6);
        house.setName("nazwa domu");
        house.setRooms(13);
        house.setThereField(true);
        System.out.println(person.getName());

        System.out.println(house.getRooms());
    }
}