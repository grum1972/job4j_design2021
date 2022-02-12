package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        int i = indexFor(hash(key.hashCode()));
        boolean rsl = (table[i] == null);
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int oldCapacity = capacity;
        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        table = newTable;
        for (int j = 0; j < oldCapacity; j++) {
            if (oldTable[j] != null) {
                int i = indexFor(hash(oldTable[j].key.hashCode()));
                table[i] = oldTable[j];
            }
        }
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        V result = null;
        if (table[i] != null) {
            result = table[i].key.equals(key) ? table[i].value : null;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(key.hashCode()));
        boolean rsl = false;
        if (table[i] != null) {
            rsl = table[i].key.equals(key);
            if (rsl) {
                table[i] = null;
                count--;
                modCount--;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The map has been modified");
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}