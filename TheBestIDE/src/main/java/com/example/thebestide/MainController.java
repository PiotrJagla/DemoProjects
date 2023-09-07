package com.example.thebestide;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Scanner;


public class MainController {
    public static final String srcDir = "./proj";

    private String currentFile;

    @FXML
    private TextArea sourceCode;

    @FXML
    private TextField fileName;

    @FXML
    private TextArea outputArea;


    public MainController() {
        outputArea = new TextArea();
        outputArea.setEditable(false);
    }

    public void printToConsole() {
        outputArea.clear();
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
            System.out.println("exit code " + exitValue);
            outputArea.setText(output.toString());

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
        StringBuffer fileContent = new StringBuffer();
        try {
            File openTo = new File(srcDir + "/" + fileName.getText());
            Scanner myReader = new Scanner(openTo);
            while(myReader.hasNextLine()) {
                fileContent.append(myReader.nextLine() + "\n");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        sourceCode.setText(fileContent.toString());
    }

    public void compile() {
        save();
        outputArea.clear();
        ProcessBuilder pb = new ProcessBuilder("java", fileName.getText());
        pb.directory(new File(srcDir));
        try {
            Process p = pb.start();
            StringBuilder output = new StringBuilder();

            BufferedReader reader= new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader errorReader= new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line;
            while((line = reader.readLine()) != null) {
                output.append(line + '\n');
            }

            while((line = errorReader.readLine()) != null) {
                output.append(line + '\n');
            }

            int exitValue = p.waitFor();
            System.out.println("exit code " + exitValue);
            outputArea.setText(output.toString());
            outputArea.setText(output.toString());

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }


}
