package com.mygdx.game.browser;

import java.util.Map;

public class BrowserMain {

    public static void main(String[] args) {
        //HTML parser
//        HTMLParser parser = new HTMLParser("<html>" +
//                "<body>" +
//                "<h1>Title</h1>" +
//                "<div id=\"main\" class=\"ltest\">" +
//                "<p>Hello <em>world</em>!</p>" +
//                "</div>" +
//                "</body>" +
//                "</html>");
//        HTMLParser parser1 = new HTMLParser("<div name=\"this is name\" class=\"firstClass\" > Hello </div>");
//        Node root = parser.parse();
//        printTree(root);

        CSSParser cssParser = new CSSParser(
            "div.h1 { padding: auto; }" +
                    "p,ul,li {color:#cc0011; margin: 20px; }" +
                    "#andwer {display  :  none; }"
        );
        Stylesheet stylesheet = cssParser.parse();
        System.out.print("Done");
    }

    public static void printTree(Node root) {
        System.out.print("(");

        if(root instanceof TextNode) {
            System.out.print("T: " + ((TextNode) root).getText());
        }
        else if(root instanceof ElementNode) {
            System.out.print("N: " + ((ElementNode) root).getTagName());
            for (Map.Entry<String, String> en : ((ElementNode) root).getAttrMap().entrySet()) {
                System.out.print( " " + en);
            }
            
        }

        for (Node child : root.getChildren()) {
            printTree(child);
        }
        System.out.print(")");
    }
}
