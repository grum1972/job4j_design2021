package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void checkCapacity() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        }
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }

    }

    @Override
    public void add(T value) {
        checkCapacity();
        container[size] = value;
        modCount++;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T rem = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size - 1
                );
        container[size - 1] = null;
        size--;
        modCount++;
        return rem;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            private int index;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The list has been modified");
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
