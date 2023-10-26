package com.mygdx.game.browser;

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
        Node root = parser1.parse();
        printTree(root);
//        System.out.println("hello world");
    }

    public static void printTree(Node root) {
        System.out.print("(");

        if(root instanceof TextNode) {
            System.out.print(((TextNode) root).getText());
        }
        else if(root instanceof ElementNode) {
            System.out.print(((ElementNode) root).getTagName());
        }

        for (Node child : root.getChildren()) {
            printTree(child);
        }
        System.out.print(")");
    }
}
