package com.example.jekins.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class EasyList {
    public static void main(String[] args) {
//        String[] arry = {"1","2"};
//        String[] newArray = Arrays.copyOf(arry,4);
//        for(int i = 0;i<newArray.length;i++){
//            System.out.println(newArray[i]);
//        }
        System.out.println(20>>1); //00010100   00001010 0

        ArrayList list = new ArrayList();
        System.out.println("capacity: " + getCapacity(list) + " size: " + list.size());
        for (int i = 0; i < 11; i++) {
            list.add("1");
            System.out.println("capacity: " + getCapacity(list) + " size: " + list.size());
        }

    }

    public static int getCapacity(ArrayList arrayList) {
        try {
            Field elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            return ((Object[]) elementDataField.get(arrayList)).length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
