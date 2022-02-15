package com.company;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Main {
    public static String InsertDash(String str){
        String finalstr = "";
        for (String word: str.trim().split(" "))
        {
            finalstr += word.toUpperCase() + '-';
        }

        return finalstr.substring(0,finalstr.length()-1);
    }

    public static String capitalize(String str) {
       return str.trim().substring(0,1).toUpperCase()+str.trim().substring(1);
    }

    public static String concatenation(String[] strings) {

    }


    public static void main(String[] args) {
        String str = " London is the capital of Great Britain ";
        System.out.println(InsertDash(str));

        String str1 = "каждый охотник желает знать где сидит фазан";
        System.out.println(capitalize(str1));

        String[] strings = {"abc", "bca"};
        System.out.println(concatenation(strings));
    }
}
