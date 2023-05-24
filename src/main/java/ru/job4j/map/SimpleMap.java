package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int amount = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key), capacity);
        if (amount >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (key == null) {
            table[0] = new MapEntry<>(null, value);
            amount++;
            modCount++;
            return true;
        }
        if (key.equals(capacity)) {
            return false;
        } else if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            amount++;
            modCount++;
            return true;
        } else {
            if (Objects.equals(table[index].key, key)) {
                return false;
            } else {
                for (int i = 0; i < capacity; i++) {
                    if (table[i] == null) {
                        table[i] = new MapEntry<>(key, value);
                        amount++;
                        modCount++;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private int hash(K key) {
        return key == null ? 0 : key.hashCode() ^ (key.hashCode() >>> capacity);
    }

    private int indexFor(int hash, int capacity) {
        return hash & (capacity - 1);
    }

    public int size() {
        return amount;
    }

    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int index = indexFor(hash(entry.key), newCapacity);
                while (newTable[index] != null) {
                    index = (index + 1) % newCapacity;
                }
                newTable[index] = entry;
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        modCount++;
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int valueIt = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (valueIt < capacity && table[valueIt] == null) {
                    valueIt++;
                }
                return valueIt < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[valueIt++].key;
            }
        };
    }


    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}