package ru.sedov.implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyEntryTest {
    @Test
    void getKey(){
        MyEntry<String, Integer> entry = new MyEntry<>("One", 1);
        assertEquals("One", entry.getKey());
    }

    @Test
    void getValue(){
        MyEntry<String, Integer> entry = new MyEntry<>("Two", 2);
        assertEquals(2, entry.getValue());
    }

    @Test
    void setValue(){
        MyEntry<String, Integer> entry = new MyEntry<>("Three", 3);
        entry.setValue(4);
        assertEquals(4, entry.getValue());
    }
}
