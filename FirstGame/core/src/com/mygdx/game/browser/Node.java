package com.mygdx.game.browser;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    private List<Node> children;

    public Node() {
        this.children = new ArrayList<>();
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
    
}
