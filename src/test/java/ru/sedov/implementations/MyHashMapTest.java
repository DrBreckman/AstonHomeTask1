package ru.sedov.implementations;

import org.junit.jupiter.api.Test;
import ru.sedov.MyMap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTest {

    /**
     * @return пустая коллекция MyHashMap<Integer, Integer>.
     */
    public MyMap<String, Integer> getEmptyMap(){
        return new MyHashMap<>();
    }

    /**
     * @return не пустая коллекция MyHashMap<Integer, Integer> заполненная пятью элементами.
     */
    public MyMap<String, Integer> getMap(){
        return new MyHashMap<>(){{
            put("One", 1);
            put("Two", 2);
            put("Three", 3);
            put("Four", 4);
            put("Five", 5);
        }};
    }

    /**
     * Генератор для создания не пустой коллекции MyHashMap<Integer, Integer> состоящей с
     * ограничениями на ключ от 0 до 999.
     * @return не пустая коллекция MyHashMap<Integer, Integer>
     */
    public MyMap<Integer, Integer> getBigMap(){
        long seed = 154852600264L;
        Random rnd = new Random(seed);
        return new MyHashMap<>(){{
            for(int i = 0; i < 100000; i++)
                put(rnd.nextInt() % 1000, rnd.nextInt());
        }};
    }

    @Test
    void toKeyArray(){
        assertEquals(0, Arrays.compare(getEmptyMap().toKeyArray(String.class), new String[] { }));

        String[] keyArr = getMap().toKeyArray(String.class),
                arr = new String[] { "One", "Two", "Three", "Four", "Five" };
        Arrays.sort(keyArr);
        Arrays.sort(arr);
        assertEquals(0, Arrays.compare(keyArr, arr));
    }

    @Test
    void toValueArray(){
        assertEquals(0, Arrays.compare(getEmptyMap().toValueArray(Integer.class), new Integer[] { }));

        Integer[] valueArr = getMap().toValueArray(Integer.class),
                arr = new Integer[] { 1,2,3,4,5 };
        Arrays.sort(valueArr);
        Arrays.sort(arr);
        assertEquals(0, Arrays.compare(valueArr, arr));
    }

    @Test
    void size(){
        assertEquals(0, getEmptyMap().size());
        assertEquals(5, getMap().size());
    }

    @Test
    void isEmpty(){
        assertTrue(getEmptyMap().isEmpty());
        assertFalse(getMap().isEmpty());
    }

    @Test
    void put(){
        long seed = 154852600264L;
        Random rnd = new Random(seed);
        int size = 100000;
        MyMap<Integer, Integer> map = new MyHashMap<>();
        Set<Integer> keys  = new HashSet<>(),
                values  = new HashSet<>();
        for(int i = 0; i < size; i++){
            int key = rnd.nextInt() % 10000;
            int value = rnd.nextInt();

            if (map.containsKey(key))
                values.remove(map.get(key));

            map.put(key, value);
            keys.add(key);
            values.add(value);
        }

        Integer[] k = keys.toArray(new Integer[]{});
        Integer[] v = values.toArray(new Integer[]{});
        Arrays.sort(k);
        Arrays.sort(v);

        Integer[] entryKeys = map.toKeyArray(Integer.class);
        Arrays.sort(entryKeys);
        assertEquals(0, Arrays.compare(entryKeys, k));

        Integer[] entryValues = map.toValueArray(Integer.class);
        Arrays.sort(entryValues);
        assertEquals(0, Arrays.compare(entryValues, v));
    }

    @Test
    void get(){
        MyMap<Integer, Integer> map = getBigMap();
        map.put(767, 202);

        assertEquals(202, map.get(767));
        assertNull(map.get(2002));
    }

    @Test
    void remove(){
        MyMap<Integer, Integer> map = getBigMap();
        assertNull(map.remove(2002));

        map.put(767, 202);
        map.remove(767);
        assertNull(map.get(767));
    }

    @Test
    void containsKey(){
        MyMap<String, Integer> map = getEmptyMap();
        assertFalse(map.containsKey("One"));

        map = getMap();
        assertTrue(map.containsKey("One"));
        assertFalse(map.containsKey("Zero"));
    }

    @Test
    void containsValue(){
        MyMap<String, Integer> map = getEmptyMap();
        assertFalse(map.containsValue(1));

        map = getMap();
        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(0));
    }


}
