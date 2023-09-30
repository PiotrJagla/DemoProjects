package com.example.SpringBootTestin;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "secureController")
public class SecureController {


    @GetMapping(path = "/")
    @CrossOrigin
    public String getSecureData() {
        return "secure data";
    }
}
