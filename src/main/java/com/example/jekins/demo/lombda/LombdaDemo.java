package com.example.jekins.demo.lombda;

import java.util.Arrays;
import java.util.List;

public class LombdaDemo {
    public static void main(String[] args){
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro","1"};
        List<String> players =  Arrays.asList(atp);

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> {
            if(players.equals("1")){
                System.out.println("hello world !");
            }else {
                System.out.println(player);
            }
        });

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

    }
}
