import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int maxSize;
    private int nextFirst;
    private int nextLast;

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
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
