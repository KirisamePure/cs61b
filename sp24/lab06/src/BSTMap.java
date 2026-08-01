import org.antlr.v4.runtime.tree.Tree;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node tree;
    private int size = 0;

    private class Node {
        Node(K KEY, V VAL, Node LEFT, Node RIGHT) {
            key = KEY;
            val = VAL;
            this.left = LEFT;
            this.right = RIGHT;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }
        Node get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (k.compareTo(key) < 0) {
                if (this.left != null) {
                    return this.left.get(k);
                } else {
                    return null;
                }
            } else if (k.compareTo(key) > 0) {
                if (this.right != null) {
                    return this.right.get(k);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        private Node getLeftmost() {
            Node n = this;
            while (n.left != null) {
                n = n.left;
            }
            return n;
        }
        Node getNext() {
            if (right != null) {
                return right.getLeftmost();
            } else {
                Node n = this;
                while (n.parent != null && n == n.parent.right) {
                    n = n.parent;
                }
                return n.parent;
            }
        }
        K key;
        V val;
        Node left;
        Node right;
        Node parent;
    }

    @Override
    public void put(K key, V value) {
        if (tree != null) {
            Node lookup = tree.get(key);
            if (lookup == null) {
                insert(tree, key, value);
                size++;
            } else {
                lookup.val = value;
            }
        } else {
            tree = new Node(key, value, null, null);
            size++;
        }
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            return tree.get(key).val;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (tree != null) {
            if (key != null) {
                return tree.get(key) != null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        tree = null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> returnSet = new TreeSet<>();
        if (tree == null) {
            return returnSet;
        }
        Node currNode = tree.getLeftmost();
        while (currNode != null) {
            returnSet.add(currNode.key);
            currNode = currNode.getNext();
        }
        return returnSet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V returnVal = get(key);
        tree = remove(tree, key);
        size--;
        return returnVal;
    }

    private Node remove(Node n, K key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(n.left, key);
            if (n.left != null) {
                n.left.parent = n;
            }
        } else if (cmp > 0) {
            n.right = remove(n.right, key);
            if (n.right != null) {
                n.right.parent = n;
            }
        } else {
            if (n.left == null) {
                if (n.right != null) {
                    n.right.parent = n.parent;
                }
                return n.right;
            }
            if (n.right == null) {
                if (n.left != null) {
                    n.left.parent = n.parent;
                }
                return n.left;
            }
            Node successor = n.right.getLeftmost();
            n.key = successor.key;
            n.val = successor.val;
            n.right = remove(n.right, successor.key);
            if (n.right != null) {
                n.right.parent = n;
            }
        }
        return n;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private Node currNode = tree == null ? null : tree.getLeftmost();

        @Override
        public boolean hasNext() {
            return currNode != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            K returnKey = currNode.key;
            currNode = currNode.getNext();
            return returnKey;
        }
    }

    private Node insert(Node n, K k, V v) {
        if (n == null) {
            return new Node(k, v, null, null);
        }
        if (k.compareTo(n.key) < 0) {
            n.left = insert(n.left, k, v);
            n.left.parent = n;
        } else if (k.compareTo(n.key) > 0) {
            n.right = insert(n.right, k, v);
            n.right.parent = n;
        }
        return n;
    }
}
