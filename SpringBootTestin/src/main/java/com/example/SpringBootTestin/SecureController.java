package com.example.SpringBootTestin;


import com.example.SpringBootTestin.config.Mouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "secureController")
public class SecureController {


    @Autowired
    private Mouse mouse;


    @GetMapping(path = "/")
    @CrossOrigin
    public String getSecureData() {
        return "secure data";
    }
}
