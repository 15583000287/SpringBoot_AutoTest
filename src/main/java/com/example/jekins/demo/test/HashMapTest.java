package com.example.jekins.demo.test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args){
        Map<String,String> map = new HashMap();
        map.put("a","A");
        System.out.println(map.size());
    }

}
