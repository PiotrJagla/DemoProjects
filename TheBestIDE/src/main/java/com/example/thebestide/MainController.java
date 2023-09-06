package com.example.thebestide;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainController {
    public static final String powershellDir = "";

    @FXML
    private TextArea sourceCode;

    public MainController() {

    }

    public void printToConsole() {
//        ProcessBuilder pb = new ProcessBuilder("powershell.exe  \"D:\\Program Files (x86)\\GitHub\\DemoProjects\\TheBestIDE\\src\\main\\java\\com\\example\\thebestide\\compileCode.ps1\"");
        ProcessBuilder pb = new ProcessBuilder("powershell.exe & 'D:/Program Files (x86)/GitHub/DemoProjects/TheBestIDE/src/main/java/com/example/thebestide/compileCode.ps1' ");
        try {
            Process p = pb.start();
            System.out.println("run successfully");
            StringBuilder output = new StringBuilder();

            BufferedReader reader= new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while((line = reader.readLine()) != null) {
                output.append(line + '\n');
            }

            int exitValue = p.waitFor();
            if(exitValue == 0) {
                System.out.println("SUCCESS!");
                System.out.println(output);
            }
            else {
                System.out.println("NOT success, exit code " + exitValue);
                System.out.println(output);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createFile() {

    }

    public void save() {

    }

    public void open() {

    }


}
