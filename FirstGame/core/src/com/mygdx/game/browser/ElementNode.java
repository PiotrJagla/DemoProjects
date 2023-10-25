package com.mygdx.game.browser;

import java.util.HashMap;
import java.util.Map;

public class ElementNode extends Node{

    private String tagName = "";

    private Map<String,String> attrMap = new HashMap<>();

    private void addAttr(String key, String value) {
        attrMap.put(key, value);
    }

    private String getAttr(String key) {
        return attrMap.get(key);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
