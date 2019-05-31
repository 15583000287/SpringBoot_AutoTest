package com.example.jekins.demo.map;

public interface MyMap<K, V> {
    int size();

    boolean isEmpty();

    V put(K k, V v);

    V get(K k);

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
