package org.example.testClasses;



import org.example.testClasses.AstPrinter;
import org.example.testClasses.Expr;
import org.example.testClasses.MyScanner;
import org.example.testClasses.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    static boolean hadError = false;

    public static void main(String[] args) {
        try {
            if(args.length > 1) {
                System.out.println("Usage: jlox [script]");
                if(hadError) {
                    System.exit(64);
                }
            } else if (args.length == 1) {
                runFile(args[0]);
            } else {
                runPrompt();
            }
        } catch(Exception e) {
            throw new RuntimeException(e);

        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while(true) {
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null) break;
            run(line);
            hadError = false;
        }
    }
    private static void run(String source) {
        MyScanner scanner = new MyScanner(source);
        List<Token> tokens = scanner.scanTokens();

        tokens.forEach(System.out::println);
    }
    public static void error(int line, String message) {
        report(line, "", message);
    }
     private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
     }


}
