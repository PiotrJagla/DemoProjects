package com.mygdx.game.browser;

public class CSSParser {
    private String input;
    private int pos;

    public CSSParser(String input) {
        this.input = input;
        this.pos = 0;
    }



    private SimpleSelector parseSimpleSelector() {
        SimpleSelector selector = new SimpleSelector();

        while(!eof()) {
            switch(peek()) {
                case '#':
                    consume();
                    selector.setId(parseIdentifier());
                    break;
                case '.':
                    consume();
                    selector.getClasses().add(parseIndentifier());
                    break;
                case '*':
                    consume();
                default:
                    if(isValidIdentifierChar(peek())) {
                        selector.setTagName(Optional.of(parseIdentifier()));
                    }
            }
        }
        return selector;
    }

    private boolean eof() {
        return pos >= input.length();
    }

    private char consume() {
        return input.charAt(pos++);
    }

    private char peek() {
        return input.charAt(pos);
    }
}
