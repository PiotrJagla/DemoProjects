package com.example.thebestide;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;


public class MainController {
    public static final String srcDir = "./proj";

    private String currentFile;

    @FXML
    private TextArea sourceCode;

    @FXML
    private TextField fileName;

    public MainController() {

    }

    public void printToConsole() {
        ProcessBuilder pb = new ProcessBuilder("pwd");
        pb.directory(new File(srcDir));
        try {
            Process p = pb.start();
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

        pb = new ProcessBuilder("ls");
        pb.directory(new File(srcDir));
        try {
            Process p = pb.start();
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
        ProcessBuilder pb = new ProcessBuilder("touch" , fileName.getText());
        pb.directory(new File(srcDir));
        try {
            Process p = pb.start();
            p.waitFor();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(srcDir + "/" + fileName.getText());
            writer.write(sourceCode.getText());
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        ProcessBuilder pb = new ProcessBuilder("cat", fileName.getText());
        pb.directory(new File(srcDir));
        try {
            Process p = pb.start();
            StringBuilder output = new StringBuilder();

            BufferedReader reader= new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while((line = reader.readLine()) != null) {
                output.append(line + '\n');
            }

            int exitValue = p.waitFor();
            System.out.println("exit code " + exitValue);
            System.out.println(output);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void open() {
        File openTo = new File(srcDir + "/" + currentFile);

    }


}
