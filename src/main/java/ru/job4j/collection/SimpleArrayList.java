package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;



    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            grow();
        }
        Objects.checkIndex(size, container.length);
            container[size++] = value;
            modCount++;

    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T rsl = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        modCount++;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
            return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    public void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 1);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {


        return new Iterator<>() {
            int valueIt = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return valueIt < container.length && container[valueIt] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[valueIt++];
            }
        };
    }
}
