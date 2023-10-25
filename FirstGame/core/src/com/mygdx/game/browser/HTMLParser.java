package com.mygdx.game.browser;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class HTMLParser {
    private String input;
    private int pos;

    public HTMLParser(String input) {
        this.input = input;
        pos = 0;
    }

    private char peek() {
        return input.charAt(pos);
    }

    private boolean startsWith(String str) {
        return input.substring(pos).startsWith(str);
    }

    private boolean eof() {
        return pos >= input.length();
    }

    private char consume() {
        return input.charAt(pos++);
    }

    private Node parseNode() {
        switch(peek()){
            case '<':
                return parseElement();
            default:
                return parseText();
        }
    }

    private Node parseElement() {
        consume();
        String tagName = parseTagName();
        Map<String, String> attrs = parseAttrs();
        consume();

        List<Node> children = parseNodes();
        return null;
    }

    private Node parseText() {
        return new TextNode(consumeUntil(c -> c != '<'));
    }

    private String parseTagName() {
        return consumeUntil(c -> (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'));
    }

    private void consumeWhitespace() {
        consumeUntil((character -> character != ' ' && character != '\n'));
    }

    private String consumeUntil(Predicate<Character> test) {
        StringBuilder res = new StringBuilder();
        while(!eof() && test.test(peek())) {
            res.append(consume());
        }
        return res.toString();
    }

}
