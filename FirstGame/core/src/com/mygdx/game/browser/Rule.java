package com.mygdx.game.browser;

import java.nio.channels.Selector;
import java.util.List;

public class Rule {
    private List<Selector> selectors;
    private List<Declaration> declarations;

    public List<Selector> getSelectors() {
        return selectors;
    }

    public void addSelector(Selector selector) {
        this.selectors.add(selector);
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void addDeclaration(Declaration declaration) {
        this.declarations.add(declaration);
    }
}
