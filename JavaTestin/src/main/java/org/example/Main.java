package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

enum Token {
    Expression,
    Operand,
    Addition,
    Multiplication,
    NONE
}


class Node{
    private Token token;
    private List<Node> children;

    public Node(Token token) {
        children = new ArrayList<>();
        this.token = token;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public Token getToken() {
        return token;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();
        try {
            //Tokenize
            BufferedReader fileContent = new BufferedReader(new StringReader("4*3+9"));
            String line;
            while((line = fileContent.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if(Character.isDigit(c)) {
                        tokens.add(Token.Operand);
                    }
                    else if(c =='+') {
                        tokens.add(Token.Addition);
                    }
                    else if(c =='*') {
                        tokens.add(Token.Multiplication);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Analyze syntactically
        Stack<Token> tokenStack = new Stack<>();



    }
    private static void traverseTree(Node root) {

        System.out.println(root.getToken());

        List<Node> children = root.getChildren();
        for (int i = 0; i < children.size(); i++) {
            traverseTree(children.get(i));
        }
    }
}
