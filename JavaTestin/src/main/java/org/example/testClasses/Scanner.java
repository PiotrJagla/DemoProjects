package org.example.testClasses;

import org.example.Main;

import java.util.ArrayList;
import java.util.List;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {
        while(!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }
    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '{': addToken(TokenType.LEFT_BRACE); break;
            case '}': addToken(TokenType.RIGHT_BRACE); break;
            case ',': addToken(TokenType.COMMA); break;
            case '.': addToken(TokenType.DOT); break;
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case '*': addToken(TokenType.STAR); break;
//            case '!':
//                addToken(match('=') ? BANG_EQUAL : BANG);
//                break;
//            case '=':
//                addToken(match('=') ? EQUAL_EQUAL : EQUAL);
//                break;
//            case '<':
//                addToken(match('=') ? LESS_EQUAL : LESS);
//                break;
//            case '>':
//                addToken(match('=') ? GREATER_EQUAL : GREATER);
//                break;
            default:
                Main.error(line, "unexpected character");
                break;
        }

    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start ,current);
        tokens.add(new Token(type,  text, literal, line));
        start = current;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }
}