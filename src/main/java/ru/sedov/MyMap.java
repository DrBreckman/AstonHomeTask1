package ru.sedov;

/**
 * Интерфейс содержащий набор основных методов для реазизации коллекций хранящей данные в виде объекта MyMap
 * @param <K> тип ключа объекта коллекции
 * @param <V> тип значения объекта коллекции
 */
public interface MyMap<K, V> {

    /**
     * Интерфейс объекта содержащий в себе пары ключ/значение, который используется внутри коллекции MyMap
     * @param <K> тип ключа объекта коллекции
     * @param <V> тип значения объекта коллекции
     */
    interface Entry<K, V> {

        /**
         * @return ключ объекта
         */
        K getKey();

        /**
         * @return значение объекта
         */
        V getValue();

        /**
         * Меняет значение объекта на новое
         * @param value новое значение объекта
         */
        void setValue(V value);
    }

    /**
     * Находится ли в коллекции объект с таким ключом и возвращает соответственный логический результат.
     * @param key ключ объекта
     * @return true - если объект с таким ключом есть в коллекции, false - если нет
     */
    boolean containsKey(K key);

    /**
     * Находится ли в коллекции объект с таким значением и возвращает соответственный логический результат.
     * @param value значение объекта
     * @return true - если объект с таким значением есть в коллекции, false - если нет
     */
    boolean containsValue(V value);

    /**
     * Возвращает значение объекта ключ которого равен key. Если такого объекта нет, то возвращается значение null
     * @param key ключ объекта
     * @return значение объекта, если он есть, null - если нет
     */
    V get(K key);

    /**
     * Помещает в коллекцию объект с ключом key и значением value. Если объект с таким ключом уже существует
     * в коллекции, то у объекта с таким ключом будет заменено значение.
     * @param key ключ объекта
     * @param value значение объекта
     */
    void put(K key, V value);

    /**
     * Удаляет из коллекции объект с ключом key и возвращает значение удаленного объекта. Если такого объекта
     * не существует, будет возвращен null.
     * @param key ключ объекта
     * @return значение удаленно объекта или null, если объекта с таким ключом нет
     */
    V remove(K key);

    /**
     * @return количество элементов коллекции
     */
    int size();

    /**
     * @return true - если коллекция пустая, false - если нет
     */
    boolean isEmpty();

    /**
     * @param clazz тип ключа объекта
     * @return массив ключей объектов коллекции
     */
    K[] toKeyArray(Class<K> clazz);

    /**
     * @param clazz тип значения объекта
     * @return массив значений объектов коллекции
     */
    V[] toValueArray(Class<V> clazz);
}
