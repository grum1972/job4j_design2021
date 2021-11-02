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
        T oldValue;
        Objects.checkIndex(index, container.length);
        oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T rem;
        Objects.checkIndex(index, container.length);
        rem = container[index];
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
        return container[Objects.checkIndex(index, container.length)];
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
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The list has been modified");
                }
                return container[index++];
            }
        };
    }
}
