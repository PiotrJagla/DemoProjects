package org.example;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class SamsungProcessor implements MobileProcessor{

    @Override
    public void process() {
        System.out.println("This is samsung processor performing");
    }
}
