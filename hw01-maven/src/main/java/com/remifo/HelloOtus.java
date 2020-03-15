package com.remifo;

import com.google.common.collect.*;

public class HelloOtus {
    public static void main(String[] args) {
        Multimap<Integer, Integer> multimap = ArrayListMultimap.create();
        System.out.println(multimap);
        System.out.println("Hello, OTUS!");
    }
}
