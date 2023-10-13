package org.example;



import java.util.ArrayList;
import java.util.List;

enum Token {
    Sentence,
    Noun,
    Verb,
}

class Node{
    Token token;
    List<Node> children;

    public Node(Token token) {
        this.token = token;
        children = new ArrayList<>();
    }

    public Token getToken() {
        return token;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

}

public class Main {
    // Sentence = Noun Verb Noun
    public static void main(String[] args) {
        String input = "dog bit cat";
        List<Token> tokens = tokenize(input);
        System.out.println(tokens);
        for (Token t : tokens) {


        }


    }



    public static List<Token> tokenize(String input) {
        List<Token> res = new ArrayList<>();

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) != ' ') {
                s.append(input.charAt(i));
            }

            if(s.toString().equals("dog") || s.toString().equals("cat") || s.toString().equals("man")) {
                res.add(Token.Noun);
                s = new StringBuilder();
            }
            else if(s.toString().equals("bit") || s.toString().equals("kicked")) {
                res.add(Token.Verb);
                s = new StringBuilder();
            }
        }

        return res;
    }

}
