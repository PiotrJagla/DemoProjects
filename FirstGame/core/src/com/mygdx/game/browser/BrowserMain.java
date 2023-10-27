package com.mygdx.game.browser;

import java.util.Map;

public class BrowserMain {

    public static void main(String[] args) {
        //HTML parser
        HTMLParser parser = new HTMLParser("<html>" +
                "<body>" +
                "<h1>Title</h1>" +
                "<div id=\"main\" class=\"ltest\">" +
                "<p>Hello <em>world</em>!</p>" +
                "</div>" +
                "</body>" +
                "</html>");
        HTMLParser parser1 = new HTMLParser("<div name=\"this is name\" class=\"firstClass\" > Hello </div>");
        Node root = parser.parse();
        printTree(root);

        //CSSParser
//        CSSParser cssParser = new CSSParser(
//            "div.h1 { padding: auto; }" +
//                    "p,ul,li {color:#cc0011; margin: 20px; }" +
//                    "#andwer {display  :  none; }"
//        );
//        Stylesheet stylesheet = cssParser.parse();
//        System.out.print("Done");
    }

    public static void printTree(Node root) {
        System.out.print("(");

        if(root instanceof TextNode) {
            TextNode textNode = (TextNode)root;
            System.out.print("T: " + textNode.getText());
            return;
        }
        else if(root instanceof ElementNode) {
            ElementNode elementNode = (ElementNode) root;
            System.out.print("N: " + elementNode.getTagName());
            for (Map.Entry<String, String> en : elementNode.getAttrMap().entrySet()) {
                System.out.print( " " + en);
            }
            if(elementNode.getChildren().size() == 0) {
                return;
            }

            for (Node child : elementNode.getChildren()) {
                printTree(child);
            }

        }

        System.out.print(")");
    }
}
