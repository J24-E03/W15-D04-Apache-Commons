package com.omar;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        apacheLangMethods();

    }

    public static void apacheLangMethods(){
        String student1 = "Sumayya";
        String student2 = "Paul";


        System.out.println(StringUtils.equals(student1,student2));

//        isEmpty()
        System.out.println(StringUtils.isEmpty(student1));
        System.out.println(StringUtils.isBlank(student1));

//        trim()
        System.out.println(StringUtils.trim(student2));

//        endsWith()
        System.out.println(StringUtils.endsWith(student2,"auz"));
        System.out.println(StringUtils.startsWith(student2,"Pa"));


//        indexOf(), lastIndexOf()

        System.out.println(StringUtils.indexOf(student1,"y"));
        System.out.println(StringUtils.lastIndexOf(student1,"y"));

        System.out.println(StringUtils.indexOfAny(student1,"s","z","z"));


//        contains()

        System.out.println(StringUtils.contains(student1,"Sum"));
        System.out.println(StringUtils.containsAny(student1,"zzz","Suma"));
        System.out.println(StringUtils.containsNone(student1,"www"));


//        substring: left, right, middle
        System.out.println(StringUtils.left(student1,4));
        System.out.println(StringUtils.right(student1,4));
        System.out.println(StringUtils.mid(student1,1,3));

        String url = "www.google.com";

//        before, after, between
        System.out.println(StringUtils.substringAfter(url,"www."));
        System.out.println(StringUtils.substringBefore(url,".com"));
        System.out.println(StringUtils.substringBetween(url,".","."));


//        remove()
        System.out.println(StringUtils.remove(url,"google"));

//        replace():
        System.out.println(StringUtils.replace(url,".com",".org"));






    }
}