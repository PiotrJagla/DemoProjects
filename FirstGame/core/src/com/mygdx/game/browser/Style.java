package com.mygdx.game.browser;

import com.badlogic.gdx.utils.Select;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

public class Style {
    private boolean matches(ElementNode elem, SimpleSelector selector) {
        //Matches simple selector
        if(!elem.getTagName().equals(selector.getTagName())) {
            return false;
        }

        if(!selector.getId().get().equals(elem.id())) {
            return false;
        }

        HashSet<String> elemClasses = elem.classes();
        if(selector.getClasses().stream().anyMatch(c -> !elemClasses.contains(c))) {
            return false;
        }

        return true;
    }

    public MatchedRule matchRule(ElementNode elem, Rule rule) {
        CSSSelector r = rule.getSelectors().stream().filter(s -> matches(elem, (SimpleSelector) s)).findFirst().orElse(null);
        if(r == null) {
            return null;
        }
        MatchedRule mr = new MatchedRule();
        mr.setRule(rule);
        mr.setSpecifity(r.specifity());
        return mr;
    }

    public List<MatchedRule> matchRules(ElementNode elem, Stylesheet stylesheet) {
        return stylesheet.getRules().stream().map(r -> matchRule(elem,r)).filter(mr -> mr != null).collect(Collectors.toList());
    }

    public PropertyMap specifiedValues(ElementNode elem, Stylesheet stylesheet) {
        HashMap<String,Value> values = new HashMap<>();
        List<MatchedRule> rules = matchRules(elem,stylesheet);

        Comparator<MatchedRule> cmp = (a, b) -> {
            Specifity sA = a.getSpecifity();
            Specifity sB = b.getSpecifity();

            int comp = Integer.compare(sB.getA(), sA.getA());
            if(comp == 0) {
                comp = Integer.compare(sB.getB(), sA.getB());
                if(comp == 0) {
                    comp = Integer.compare(sB.getC(), sA.getC());
                }
            }
            return comp;
        };

        Collections.sort(rules, cmp);
        for (MatchedRule rule : rules) {
            for (Declaration dec : rule.getRule().getDeclarations()) {
                values.put(dec.getName(), dec.getValue());
            }
        }

        PropertyMap pm = new PropertyMap();
        pm.setMap(values);
        return pm;
    }

    public StyledNode styleTree(Node root, Stylesheet stylesheet) {
        StyledNode styledNode = new StyledNode();
        styledNode.setNode(root);
        if(root instanceof TextNode) {
            styledNode.setSpecifiedValues(new PropertyMap());
        } else if (root instanceof ElementNode) {
            styledNode.setSpecifiedValues(specifiedValues((ElementNode) root, stylesheet));
        }
        List<StyledNode> children = ((ElementNode) root).getChildren().stream().map(c -> styleTree(c, stylesheet)).collect(Collectors.toList());
        styledNode.setChildren(children);
        return styledNode;
    }
}

class PropertyMap{
    private Map<String, Value> map = new HashMap<>();

    public Map<String, Value> getMap() {
        return map;
    }

    public void setMap(Map<String, Value> map) {
        this.map = map;
    }
}

class MatchedRule{
    private Specifity specifity;
    private Rule rule;

    public Specifity getSpecifity() {
        return specifity;
    }

    public void setSpecifity(Specifity specifity) {
        this.specifity = specifity;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
class StyledNode {
    private Node node;
    private PropertyMap specifiedValues;
    private List<StyledNode> children;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public PropertyMap getSpecifiedValues() {
        return specifiedValues;
    }

    public void setSpecifiedValues(PropertyMap specifiedValues) {
        this.specifiedValues = specifiedValues;
    }

    public List<StyledNode> getChildren() {
        return children;
    }

    public void setChildren(List<StyledNode> children) {
        this.children = children;
    }

    public Value value(String str) {
        return specifiedValues.getMap().getOrDefault(str,null);
    }

    public Display getDisplay() {
        if(!(value("display") instanceof Keyword)){
            return Display.Inline;
        }
        Keyword k = (Keyword) value("display");
        if(k.getK().equals("block")) {
            return Display.Block;
        } else if (k.getK().equals("none")) {
            return Display.None;
        }
        return Display.Inline;
    }
}
