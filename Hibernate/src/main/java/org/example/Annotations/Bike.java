package org.example.Annotations;


import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle{
    @Override
    public void drive() {
        System.out.println("I am riding a bike");
    }
}
