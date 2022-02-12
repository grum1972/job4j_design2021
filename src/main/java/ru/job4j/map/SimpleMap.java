package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        return 37 * 7 + hashCode;
    }

    private int indexFor(int hash) {
        return hash % 3;
    }

    private void expand() {
        table = Arrays.copyOf(table, table.length * 2);
        capacity *= 2;
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
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (table[index] != null) ? table[index++].key : null;
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