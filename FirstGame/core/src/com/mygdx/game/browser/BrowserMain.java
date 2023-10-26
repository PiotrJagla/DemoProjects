package com.mygdx.game.browser;

import java.util.Map;

public class BrowserMain {

    public static void main(String[] args) {
//        HTMLParser parser = new HTMLParser("<html>" +
//                    "<body>" +
//                        "<h1>Title</h1>" +
//                        "<div id='main' class='test'>" +
//                            "<p>Hello <em>world</em>!</p>" +
//                        "</div>" +
//                    "</body>" +
//                "</html>");

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
//        System.out.println("hello world");
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
