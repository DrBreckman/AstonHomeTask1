package ru.sedov.implementations;

import org.junit.jupiter.api.Test;
import ru.sedov.MyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyArrayListTest {

    /**
     * Генератор для создания пустой коллекции MyArrayList
     * @return пустую коллекцию MyArrayList
     */
    private static MyList<Integer> getEmptyList(){
        return new MyArrayList<>();
    }

    /**
     * Генератор для создания не пустой коллекции MyArrayList заполненной тремя элементами.
     * @return не пустую коллекцию MyArrayList
     */
    public static MyList<Integer> getList() {
        return new MyArrayList<>(){{
            add(1);
            add(3);
            add(2);
        }};
    }

    @Test
    void toArray(){
        assertEquals(0, Arrays.compare(getEmptyList().toArray(Integer.class), new Integer[] { } ));
        assertEquals(0, Arrays.compare(getList().toArray(Integer.class), new Integer[] { 1, 3, 2 } ));
    }

    @Test
    void size(){
        assertEquals(0, getEmptyList().size());
        assertEquals(3, getList().size());
    }

    @Test
    void add(){
        MyList<Integer> list = getEmptyList();

        int size = 1500;
        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++){
            arr[i] = i;
            list.add(i);
        }

        assertEquals(size, list.size());
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), arr));
    }

    @Test
    void addByIndex(){
        MyList<Integer> list = getList();

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, -1));

        list.add(6, 0);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 6, 1, 3, 2 } ));

        list.add(7, 3);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 6, 1, 3, 7, 2 } ));


    }

    @Test
    void addByIndexMore(){
        MyList<Integer> list = getEmptyList();

        int size = 1500;
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < size; i++){
            arr.add(0, i);
            list.add(i, 0);
        }

        assertEquals(size, list.size());
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), arr.toArray(new Integer[]{})));
    }

    @Test
    void get(){
        MyList<Integer> list = getList();

        assertThrows(IndexOutOfBoundsException.class, () -> getEmptyList().get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    void remove(){
        assertThrows(IndexOutOfBoundsException.class, () -> getEmptyList().remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> getList().remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> getList().remove(3));

        MyList<Integer> list = getList();
        list.remove(2);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 1, 3 }));

        list.remove(0);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 3 } ));

        list.remove(0);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { }));

        for(int i = 0; i < 1500; i++)
            list.add(i);
        while(list.size() > 4)
            list.remove(2);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 0, 1, 1498, 1499}));
    }

    @Test
    void replace(){
        assertThrows(IndexOutOfBoundsException.class, () -> getEmptyList().replace(13, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> getList().replace(13, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> getList().replace(13, 3));

        MyList<Integer> list = getList();
        list.replace(11, 0);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 11, 3, 2 }));

        list.replace(13, 1);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 11, 13, 2 }));

        list.replace(12, 2);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { 11, 13, 12 }));
    }

    @Test
    void clear(){
        MyList<Integer> list = getEmptyList();
        list.clear();
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { }));

        list = getList();
        list.clear();
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { }));
    }

    @Test
    void sort(){
        MyList<Integer> list = getEmptyList();
        list.sort(Integer::compare);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), new Integer[] { }));

        Integer[] arr = new Integer[] { 0, 3, 1, -1, 6, 10, 9, 4, 5, 7, 16, -14, 2, 0 };
        for(var a : arr)
            list.add(a);

        list.sort(Integer::compare);
        Arrays.sort(arr);
        assertEquals(0, Arrays.compare(list.toArray(Integer.class), arr));
    }
}
