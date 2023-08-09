package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
public class Samsung {

    @Autowired
    private MobileProcessor processor;



    public void config() {
        System.out.println("Octa core, 4 gb RAM, cos tam cos tam " );
        processor.process();
    }

    public MobileProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(MobileProcessor processor) {
        this.processor = processor;
    }
}
