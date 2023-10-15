package com.example.SpringBootTestin;


import com.example.SpringBootTestin.config.Mouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping(path = "/t")
public class SecureController {


    @Autowired
    private Mouse mouse;

    @GetMapping(path = "test")
    @CrossOrigin
    public String getSecureData() {
        String result2 = invokeCommand("ls");
        System.out.println(result2);
        System.out.println(invokeCommand("pwd"));
        return result2;
    }

    private String invokeCommand(String... cmd) {
        ProcessBuilder pb;
        if(cmd[0].equals("ls") || cmd[0].equals("pwd")) {
            pb = new ProcessBuilder(cmd[0]);
        }
        else {
            pb = new ProcessBuilder(cmd[0], cmd[1]);
        }
        String result = "";
        try {
            Process p = pb.start();
            BufferedReader bis = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while((line = bis.readLine()) != null) {
                result += line;
                result += " ";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
