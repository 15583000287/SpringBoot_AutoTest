package com.example.jekins.demo.test;

import com.example.jekins.demo.map.MyHashMap;
import com.example.jekins.demo.map.MyMap;

/**
 * MyHashMap测试
 */
public class MyHashMapTest {
    public static void main(String[] args){
        MyMap<String,String> map = new MyHashMap();
        map.put("p1","张三");
        System.out.println(map.size());
    }
}
