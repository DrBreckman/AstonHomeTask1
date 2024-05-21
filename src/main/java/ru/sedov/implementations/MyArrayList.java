package ru.sedov.implementations;

import ru.sedov.MyList;
import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * <p> Реализация интерфейса MyList, представлюящая собой коллекцию для хранения элементов одного типа.
 * Коллекция представляет из себя динамически расширямый массив данных. </p>
 * @param <T> тип хранимых элементов в коллекции.
 */
public class MyArrayList<T> implements MyList<T>{
    /**
     * <p> Стандартный фактический размер коллекции используемый в конструкторе по умолчанию. </p>
     */
    private final int DEFAULT_CAPACITY = 16;

    /**
     * <p> Количество элементов в коллекции. </p>
     */
    private int size = 0;

    /**
     * <p> Массив для хранения элементов коллекции. </p>
     */
    private Object[] items = new Object[DEFAULT_CAPACITY];

    /**
     * <p> Увеличивает размер массива и копирует все элементы из текущего массива в новый с сохранением индексов. </p>
     */
    private void resize(){
        Object[] newItems = new Object[items.length * 2];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    /**
     * <p> Рекурсивно осуществляет быструю сортировку массива </p>
     * @param left левый индекс сортируемого подмассива
     * @param right правый индекс сортируемого подмассива
     * @param comparator компаратор для сравнения элементов массива
     */
    private void quickSort(int left, int right, Comparator<T> comparator){
        if (left >= right)
            return;

        int mid = partition(left, right, comparator);

        quickSort(left, mid - 1, comparator);
        quickSort(mid + 1, right, comparator);
    }

    /**
     * Сортирует подмассив от левого до правого индекса путем перемещения элементов относительно опорного элемента
     * в соответствии с компаратором.
     * @param left левый индекс сортируемого подмассива.
     * @param right правый индекс сортируемого подмассива.
     * @param comparator комапратор для сравнения элементов коллекции.
     * @return индекс опорного элемента
     */
    @SuppressWarnings("unchecked")
    private int partition(int left, int right, Comparator<T> comparator){
        int mid = (left + right) / 2,
                i = left;

        Object value = items[mid];
        items[mid] = items[i];
        items[i] = value;

        for(int j = left + 1; j <= right; j++)
            if (comparator.compare((T) items[left], (T) items[j]) > 0){
                i++;
                Object tmp = items[j];
                items[j] = items[i];
                items[i] = tmp;
            }

        items[left] = items[i];
        items[i] = value;

        return i;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        if (size == items.length)
            resize();
        items[size++] = element;
    }

    @Override
    public void add(T element, int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        if (size == items.length)
            resize();

        for(int i = size - 1; i >= index; i--)
            items[i + 1] = items[i];
        items[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return (T) items[index];
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        for(int i = index + 1; i < size; i++)
            items[i - 1] = items[i];
        items[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++)
            items[i] = null;
        size = 0;
    }

    @Override
    public void replace(T element, int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        items[index] = element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> clazz){
        T[] arr = (T[]) Array.newInstance(clazz, size);
        for(int i = 0; i < size; i++)
            arr[i] = (T) items[i];
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("[");

        for(int i = 0; i < size - 1; i++)
            sb.append(items[i])
                    .append(", ");

        if (size > 0)
            sb.append(items[size - 1]);

        sb.append("]");
        return sb.toString();
    }
}
