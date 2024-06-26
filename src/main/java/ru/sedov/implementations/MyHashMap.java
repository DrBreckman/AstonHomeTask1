package ru.sedov.implementations;

import ru.sedov.MyMap;

import java.lang.reflect.Array;
import java.util.*;

/**
 * <p>Реализация интерфейса MyMap представляющая собой коллекцию, хранящую в себе объекты MyEntry.
 * Хранение элементов происходит во внутреннем массиве из 10 элементов. Исходный размер массива можно изменить
 * при создании экземпляра класса передав целочисленное значение в параметре capacity конструктора.
 * Ячейка, в которой будет находиться объект определяется параметром key в объекте путем вычисления
 * хэш-значения. При добавлении объекта в пустую ячейку, в не создается стандартный java_LinkedList
 * для решения будущих коллизий. Если после удаления объекта из коллекции LinkedList остается пустым - он удаляется полностью.
 * Новые объекты всегда добавляются в конец соответственного списка. </p>
 * <ul> Для работы с коллекций были определены следующий методы:
 *     <li>containsKey(key) - проверка, находится ли в коллекции заданный ключ</li>
 *     <li>containsValue(value) - проверка, находится ли в коллекции заданное значение</li>
 *     <li>get(key) - получить значение по ключу</li>
 *     <li>put(key, value) - поместить пару ключ/значение в коллекцию</li>
 *     <li>remove(key) - удалить объект по ключу</li>
 *     <li>size() - получить количество объектов в коллекции</li>
 *     <li>isEmpty() - проверить пуста ли коллекция</li>
 *     <li>toKeyArray(clazz) - конвертировать все ключи в коллекции в массив</li>
 *     <li>toValueArray(clazz) - конвертировать все значения в коллекции в массив</li>
 * </ul>
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    /**
     * Стандартное количество списков для хранения объектов.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Текущее количество списков для хранения объектов.
     */
    private final int buckets;

    private int size = 0;

    /**
     * Массив списков для хранения объектов.
     */
    private final List<MyEntry<K, V>>[] entryLists;

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity){
        buckets = capacity;
        entryLists = new List[buckets];
    }
    public MyHashMap(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Высчитывает целочисленное значение для определения в каком из списков осуществлять хранения объекта.
     * @param key ключ объекта
     * @return номер списка
     */
    private int getHash(K key){
        return Math.abs(key.hashCode() % buckets);
    }

    @Override
    public boolean containsKey(K key) {
        int hash = getHash(key);
        if (entryLists[hash] == null)
            return false;

        for(var entry : entryLists[hash])
            if (entry.getKey().equals(key))
                return true;

        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(var list : entryLists)
            if (list != null)
                for(var entry : list)
                    if (entry.getValue().equals(value))
                        return true;
        return false;
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        if(entryLists[hash] == null)
            return null;

        for(var entry : entryLists[hash])
            if (entry.getKey().equals(key))
                return entry.getValue();

        return null;
    }

    @Override
    public void put(K key, V value) {
        int hash = getHash(key);
        if (entryLists[hash] == null)
            entryLists[hash] = new LinkedList<>();

        for(var entry : entryLists[hash])
            if (entry.getKey().equals(key)){
                entry.setValue(value);
                return;
            }

        entryLists[hash].addLast(new MyEntry<>(key, value));
        size++;
    }

    @Override
    public V remove(K key) {
        int hash = getHash(key);
        if (entryLists[hash] == null)
            return null;

        for(int i = 0; i < entryLists[hash].size(); i++){
            if (entryLists[hash].get(i).getKey().equals(key)){
                var entry = entryLists[hash].remove(i);
                if (entryLists[hash].isEmpty())
                    entryLists[hash] = null;
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public K[] toKeyArray(Class<K> clazz) {
        K[] keys = (K[]) Array.newInstance(clazz, size);
        int i = 0;
        for(var list : entryLists)
            if (list != null)
                for(var entry : list)
                    keys[i++] = entry.getKey();
        return keys;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V[] toValueArray(Class<V> clazz) {
        V[] keys = (V[]) Array.newInstance(clazz, size);
        int i = 0;
        for(var list : entryLists)
            if (list != null)
                for(var entry : list)
                    keys[i++] = entry.getValue();
        return keys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(var list : entryLists)
            if (list != null)
                for(var entry : list)
                    sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");

        return (sb.length() > 1 ? sb.substring(0, sb.length() - 2) : sb.toString()) + "]";
    }
}
