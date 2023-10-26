package com.mygdx.game.browser;

import java.util.ArrayList;

public class TextNode extends Node{
    private String text;

    public TextNode(String text) {
        super(new ArrayList<>());
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
