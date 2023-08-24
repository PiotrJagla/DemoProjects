package com.example.thebestide;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {


    @FXML
    private TextArea sourceCode;

    public void compile() {
        System.out.println(sourceCode.getText());
    }
}