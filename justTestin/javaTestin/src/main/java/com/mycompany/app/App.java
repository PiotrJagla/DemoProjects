package com.mycompany.app;

import java.lang.Object;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("hello");
        StringBuilder ss = new StringBuilder();
        ss.append("aaa");


        StringBuilder bs= new StringBuilder();
        ss.append("aaa");

        System.out.println(bs.toString() == ss.toString());

    }
}
