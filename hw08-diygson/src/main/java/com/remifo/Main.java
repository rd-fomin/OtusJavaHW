package com.remifo;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String json = gson.toJson( new TestClass() );
        System.out.println(json);
//        TestClass obj2 = gson.fromJson(json, TestClass.class);
//        System.out.println(obj2);

        System.out.println("---------------------------");
        String json2 = JSONParser.toJson( new TestClass() );
        System.out.println(json2);
        TestClass obj = gson.fromJson(json2, TestClass.class);
        System.out.println(obj);
    }

}
