package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private final int capacity = 8;
    private int size = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (size / capacity >= LOAD_FACTOR) {
            expand();
        }
        for (MapEntry<K, V> obj : table) {
            if (obj.key.hashCode() != key.hashCode()) {
                table[indexFor(key.hashCode())] = obj;
                size++;
                modCount++;
                return true;
            } else if (obj.key.hashCode() == key.hashCode()) {
                if (obj.key.equals(key)) {
                    table[indexFor(key.hashCode())].value = value;
                    return true;
                } else {
                    table[indexFor(key.hashCode())] = obj;
                    size++;
                    modCount++;
                    return true;
                }
            }
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        if (table[hash % capacity] == null) {
            return hash % capacity;
        } else {
            return (hash % capacity) + 1;
        }
    }

    private void expand() {
        MapEntry<K, V>[] map = new MapEntry[capacity * 2];
        for (MapEntry<K, V> f : table) {
            put(f.key, f.value);
        }
        table = map;
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
                return valueIt < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (table[valueIt++] == null) {
                    return null;
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