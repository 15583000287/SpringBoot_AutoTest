package com.example.jekins.demo.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 手写HashMap  参考博客：https://blog.csdn.net/huangshulang1234/article/details/79713303
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    /**
     * 初始容量 aka 16   00000001 --> 00010000
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 默认加载因子（构造函数为指定时使用）
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int defaultInitialCapacity;
    private float defaultLoadFactor;
    /**
     * Map中Entry数量
     */
    private int size;

    /**
     * 数组
     */
    private Entry<K, V>[] table = null;

    /**
     * 构造方法(“门面模式” 这里的2个构造方法其实指向的是同一个，但是对外却暴露了2个“门面”！)
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor) {
        if (defaultInitialCapacity < 0) {
            throw new IllegalArgumentException("Illegal inital capacity: " + defaultInitialCapacity);
        }
        if (defaultInitialCapacity <= 0 || Float.isNaN(defaultLoadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + defaultLoadFactor);
        }
        this.defaultInitialCapacity = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;
    }


    /**
     * HashMap的要素之一，单链表的体现就在这里
     */
    class Entry<K, V> implements MyMap.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry() {
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V put(K k, V v) {
        V oldValue = null;
        //判断是否需要扩容，若扩容肯定需重新散列
        if (size >= defaultInitialCapacity * defaultLoadFactor) {
            resize(2 * defaultInitialCapacity);
        }
        //TODO 得到Hash值，计算出数组中的位置
        int index = hash(k) & (defaultInitialCapacity - 1);
        if (table[index] == null) {
            table[index] = new Entry<K, V>(k, v, null);
            ++size;
        } else {//需要遍历单链表
            Entry<K, V> entry = table[index];
            Entry<K, V> e = entry;
            while (e != null) {
                if (k == e.getKey() || k.equals(e.getKey())) {
                    oldValue = e.value;
                    e.value = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] = new Entry<K, V>(k, v, entry);
            ++ size;
        }
        return oldValue;
    }

    @Override
    public V get(K k) {
        return null;
    }

    private void resize(int i) {
        Entry[] newTable = new Entry[i];
        defaultInitialCapacity = i;
        size = 0;
        rehash(newTable);
    }

    private void rehash(Entry<K, V>[] newTable) {
        //得到原来老的Entry集合,注意遍历单链表
        List<Entry<K, V>> entryList = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    entryList.add(entry);
                } while (entry != null);
            }
        }

        //覆盖旧的引用，赋值为扩容后的空table数组
        if (newTable.length > 0) {
            table = newTable;
        }

        //所谓重新HASH，就是重新put Entry 到 HashMap
        //这里可以看出，对于HashMap而言，如果频繁进行resize/rehash操作，是会影响性能的。
        //resize/rehash的过程，就是数组变大，原来数组中的entry元素一个个的put到新数组的过程，需要注意的是一些状态变量的改变。
        for (Entry<K, V> entry : entryList) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Hash函数
     */
    private int hash(K k) {
        int hashCode = k.hashCode();
        hashCode = (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }
}
