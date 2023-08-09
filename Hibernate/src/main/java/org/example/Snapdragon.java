package org.example;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Primary
public class Snapdragon implements MobileProcessor{
    @Override
    public void process() {
        System.out.println("This is snapdragon processor performing");
    }
}
