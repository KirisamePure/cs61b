package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private final Node sentinel;
    private int size;

    public class Node {
        public T item;
        public Node prev, next;
        public Node(T item, Node prev, Node next) {
            this.item= item;
            this.prev = prev;
            this.next = next;
        }
    }

    public Iterator<T> iterator()  {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node currNode;
        public LinkedListDequeIterator() {
            currNode = sentinel.next;
        }
        public boolean hasNext() {
            return currNode != sentinel;
        }
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T returnItem = currNode.item;
            currNode = currNode.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LinkedListDeque61B lld) {
            if (this.size != lld.size) {
                return false;
            }
            Node currNodeThis = this.sentinel.next;
            LinkedListDeque61B.Node currNodeLld = lld.sentinel.next;
            for (int i = 0; i < this.size; i++) {
                if (currNodeThis.item != currNodeLld.item) {
                    return false;
                }
                currNodeThis = currNodeThis.next;
                currNodeLld = currNodeLld.next;
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
            Node currNode = sentinel.next;
            for (int i = 0; i < size; i++) {
                returnSB.append(currNode.item);
                if (i != size - 1) {
                    returnSB.append(seq);
                    returnSB.append(" ");
                }
                currNode = currNode.next;
            }
            returnSB.append("]");
            return returnSB.toString();
        }
    }

    @Override
    public String toString() {
        return toString(",");
    }

    public LinkedListDeque61B() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;

        this.size = 0;
    }
    @Override
    public void addFirst(T x) {
        size++;
        Node first = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
    }

    @Override
    public void addLast(T x) {
        size++;
        Node last = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currNode = sentinel.next;

        while (currNode != sentinel) {
            returnList.add(currNode.item);
            currNode = currNode.next;
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
        if (size == 0) {
            return null;
        }

        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;

        T item = firstNode.item;

        firstNode.next = firstNode;
        firstNode.prev = firstNode;

        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node lastNode = sentinel.prev;
        lastNode.prev.next = sentinel;
        sentinel.prev= lastNode.prev;

        T item = lastNode.item;

        lastNode.next = lastNode;
        lastNode.prev = lastNode;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        Node currNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        return currNode.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node currNode, int index) {
        if (index == 0) {
            return currNode.item;
        }

        return getRecursiveHelper(currNode.next, index - 1);
    }
}
