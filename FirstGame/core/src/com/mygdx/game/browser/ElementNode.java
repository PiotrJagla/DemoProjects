package com.mygdx.game.browser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementNode extends Node{

    private String tagName = "";

    private Map<String,String> attrMap = new HashMap<>();

    public ElementNode(String tagName, Map<String, String> attrMap, List<Node> children) {
        super(children);
        this.tagName = tagName;
        this.attrMap = attrMap;
    }


    public void addAttr(String key, String value) {
        attrMap.put(key, value);
    }

    public String getAttr(String key) {
        return attrMap.get(key);
    }

    public Map<String,String> getAttrMap() {
        return attrMap;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
