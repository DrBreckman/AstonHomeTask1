package ru.sedov.implementations;

import ru.sedov.MyMap;

public class MyEntry<K, V> implements MyMap.Entry<K, V> {
    private final K key;
    private V value;

    public MyEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }
}
