package org.example.Annotations;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle{


    private Tyre tyre;


    @Override
    public void drive() {
        System.out.println("I am driving a cardd");
    }


    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }
}
