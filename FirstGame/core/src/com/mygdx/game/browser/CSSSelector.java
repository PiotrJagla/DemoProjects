package com.mygdx.game.browser;

import java.util.List;
import java.util.Optional;

public abstract class CSSSelector {

    private SimpleSelector simpleSelector;

    public CSSSelector(SimpleSelector simpleSelector) {
        this.simpleSelector = simpleSelector;
    }

    public CSSSelector(){

    }

    public Specifity specifity() {
        int a = simpleSelector.getId().isPresent() ? 1 : 0;
        int b = simpleSelector.getClasses().size();
        int c = simpleSelector.getTagName().isPresent() ? 1 : 0;
        return new Specifity(a,b,c);
    }


}



class SimpleSelector extends CSSSelector {
    private Optional<String> tagName;
    private Optional<String> id;
    private List<String> classes;
    public void setTagName(Optional<String> tagName) {
        this.tagName = tagName;
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }
    public void addClass(String c) {
        this.classes.add(c);
    }

    public Optional<String> getTagName() {
        return tagName;
    }

}

class Specifity{
    private int a;
    private int b;
    private int c;

    public Specifity(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}