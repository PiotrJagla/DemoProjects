package com.mygdx.game.browser;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    private List<Node> children;

    public Node(List<Node> children) {
        this.children = children;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

}
