package ru.job4j.linkedlist;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    private static class Node<E> {
        //private int index;
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        if (first == null) {
            Node<E> node = new Node(null, value, null);
            first = node;
            last = node;
        } else {
            Node<E> node = new Node(last, value, null);
            last.next = node;
            last = node;
        }
        size++;
        modCount++;
        //index++;
    }

    @Override
    public E get(int index) {
        Node<E> cursor = first;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return cursor.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> cursor = first;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The list has been modified");
                }
                return cursor != null;
            }

            @Override
            public E next() {
                E elem;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                elem = cursor.item;
                cursor = cursor.next;
                return elem;
            }
        };
    }
}
