package com.parth.list;

import java.util.Iterator;

public class OurGenericList<T> implements Iterable<T> {
    private final T[] items;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 100;

    @SuppressWarnings("unchecked")
    public OurGenericList() {
        items = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    public void add(T item) {
        items[size++] = item;
    }

    public T getItemAtIndex(int index) {
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericListIterator(this);
    }

    private class GenericListIterator implements Iterator<T> {
        private final OurGenericList<T> list;
        private int index = 0;

        public GenericListIterator(OurGenericList<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return index < list.size;
        }

        @Override
        public T next() {
            return list.items[index++];
        }
    }
}
