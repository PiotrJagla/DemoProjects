package com.mygdx.game.browser;

import java.util.List;
import java.util.Optional;

public abstract class Selector {


}

class SimpleSelector extends Selector{
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

    public Optional<String> getTagName() {
        return tagName;
    }

}
