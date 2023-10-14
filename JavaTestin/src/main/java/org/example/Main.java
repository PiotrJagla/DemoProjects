package org.example;



import java.util.*;

enum Token {
    Number,
    Add,
    Multiply,
}

class Node{
    Token token;
    List<Node> children;

    public Node() {
        children = new ArrayList<>();
    }
    public Node(Token token) {
        this.token = token;
        children = new ArrayList<>();
    }

    public Token getToken() {
        return token;
    }
    public void setToken(Token t) {token = t;}

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }


}

class MyIterator {
    private String input;
    private int pos;
    private char peek;

    public MyIterator(String input) {
        this.input = input;
        pos = 0;
        peek = input.charAt(pos++);
    }

    public char next() {
        char c = peek;
        peek = input.charAt(pos++);
        return c;
    }

    public void split(char delimiter, Runnable r) {
        while(true) {
            r.run();
            if(peek == delimiter) {
                next();
            } else {
                break;
            }
        }
    }

    public int number()  {
        return Integer.parseInt(String.valueOf(peek));
    }

}

public class Main {
    public static void main(String[] args) {
        MyIterator iterator = new MyIterator("2*4+3*5");
        int res = 0;
        iterator.split('+', () -> {
            int prod = 1;
            iterator.split('*', () ->{
                prod *= iterator.number();
            });

        });
    }


    public static void split(char delimiter, Runnable r, Iterator<Character> iter) {
        while(true) {
            r.run();

        }
    }



    public static void traverse(Node node) {
        if(node.getChildren().size() == 0) {
            return;
        }

        System.out.println(node.getToken());
        for (Node child : node.getChildren()) {
            traverse(child);
        }
    }

}
