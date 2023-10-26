package com.mygdx.game.browser;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Stylesheet {
    private List<Rule> rules = new ArrayList<>();

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
}
