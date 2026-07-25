package deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int maxSize;
    private int nextFirst;
    private int nextLast;

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size;
        }
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArrayDeque61B ad) {
            if (this.size != ad.size) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (this.get(i) != ad.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String toString(String seq) {
        if (isEmpty()) {
            return "";
        } else {
            StringBuilder returnSB = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                returnSB.append(items[i]);
                if (i != size - 1) {
                    returnSB.append(seq);
                    returnSB.append(" ");
                }
            }
            returnSB.append("]");
            return returnSB.toString();
        }
    }

    @Override
    public String toString() {
        return toString(",");
    }

    public ArrayDeque61B() {
        maxSize = 8;
        items = (T[]) new Object[maxSize];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < items.length; i++) {
            a[i] = items[i];
        }
        items = a;
        maxSize = capacity;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(maxSize * 2);
            nextFirst = maxSize - 1;
            nextLast = size;
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = Math.floorMod(nextFirst - 1, maxSize);
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(maxSize * 2);
            nextFirst = maxSize - 1;
            nextLast = size;
        }
        items[nextLast] = x;
        size += 1;
        nextLast = Math.floorMod(nextLast + 1, maxSize);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (isEmpty()) {
            return List.of();
        }
        for (int i = 0; i < items.length; i++) {
            returnList.add(items[i]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (items.length <= maxSize / 4) {
            resize(maxSize / 2);
            nextFirst = maxSize - 1;
        }

        T x = items[Math.floorMod(nextFirst + 1, maxSize)];
        size -= 1;
        items[Math.floorMod(nextFirst + 1, maxSize)] = null;
        nextFirst = Math.floorMod(nextFirst + 1, maxSize);
        return x;
    }

    @Override
    public T removeLast() {
        if (items.length <= maxSize / 4) {
            resize(maxSize / 2);
            nextFirst = maxSize - 1;
        }

        T x = items[Math.floorMod(nextLast - 1, maxSize)];
        size -= 1;
        items[Math.floorMod(nextLast - 1, maxSize)] = null;
        nextLast = Math.floorMod(nextLast - 1, maxSize);
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
