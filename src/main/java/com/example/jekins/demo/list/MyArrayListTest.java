package com.example.jekins.demo.list;

import java.lang.reflect.Array;

public class MyArrayListTest {
    public static void main(String[] args){
        String[] srcArr = {"1","2","3","4"};
        String[] destArr = {"a","b","c","d","b","d"};
        System.arraycopy(srcArr,1,destArr,3,2);
        for(int i =0;i<destArr.length;i++){
            System.out.println(destArr[i]);
        }


        MyList<String> myList = new MyArrayList();
        myList.add("张三");
        myList.add("李四");
        myList.add("王五");
        System.out.println(myList.get(0)+" "+myList.get(1)+" "+myList.get(2)+" size: "+myList.size());
        myList.add(1,"大哥");
        System.out.println(myList.get(0)+" "+myList.get(1)+" "+myList.get(2)+" "+myList.get(3)+" size: "+myList.size());

    }

}
