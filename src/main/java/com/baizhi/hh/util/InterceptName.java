package com.baizhi.hh.util;

public class InterceptName {

    public static String getName(String name) {
        int i = name.lastIndexOf("\\");
        String substring = name.substring(i + 1);
        return substring;
    }

    public static void main(String[] args) {
        String s = "aaa.mp";
//        System.out.println(s.length());
        String[] split = s.split("\\.");
        System.out.println(split[0]);
    }
}