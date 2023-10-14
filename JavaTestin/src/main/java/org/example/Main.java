package org.example;



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
        String input = "2+3*2";
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '*') {
                int eval = Integer.parseInt(String.valueOf(input.charAt(i - 1))) *  Integer.parseInt(String.valueOf(input.charAt(i + 1)));
                StringBuilder newExpr = new StringBuilder();
                if(i - 1 >= 0) {
                    newExpr.append(input.substring(0, i - 1)) ;
                }
                newExpr.append(eval);
                if(i + 2 < input.length()) {
                    newExpr.append(input.substring(i + 2)) ;
                }
                input = newExpr.toString();
            }
        }
        System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '+') {
                int eval = Integer.parseInt(String.valueOf(input.charAt(i - 1))) +  Integer.parseInt(String.valueOf(input.charAt(i + 1)));
                StringBuilder newExpr = new StringBuilder();
                if(i - 1 >= 0) {
                    newExpr.append(input.substring(0, i - 1)) ;
                }
                newExpr.append(String.valueOf(eval));
                if(i + 2 < input.length()) {
                    newExpr.append(input.substring(i + 2, input.length())) ;
                }
                input = newExpr.toString();
            }
        }

        System.out.println(input);

//        if(args.length > 1) {
//            System.out.println("Usage: jlox [script]");
//            if(hadError) {
//                System.exit(64);
//            }
//        } else if (args.length == 1) {
//            runFile(args[0]);
//        } else {
//            runPrompt();
//        }
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
        Scanner scanner = new Scanner(source);
//        List<Token> tokens = scanner.scanTokens();
//
//        for (Token token : tokens) {
//            System.out.println(tokens);
//        }
    }
    public static void error(int line, String message) {
        report(line, "", message);
    }
     private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
     }


}
