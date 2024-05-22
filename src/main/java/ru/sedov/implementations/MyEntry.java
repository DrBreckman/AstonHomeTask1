package ru.sedov.implementations;

import ru.sedov.MyMap;

/**
 * Класс, объекты которого используются в реализации MyHashMap. Объекты класса хранят
 * пары ключ/значение, где ключ является константным и не может быть изменен. Класс
 * предоставляет следующий набор методов для работы с его объектами:
 * <ul>
 *     <li>getKey() - получить ключ объекта</li>
 *     <li>getValue() - получить значение объекта</li>
 *     <li>setValue(key) - изменить значение объекта по заданному ключу</li>
 * </ul>
 * @param <K> тип данных ключа объекта
 * @param <V> тип данных значения объекта
 */
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
