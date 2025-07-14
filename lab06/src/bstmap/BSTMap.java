package bstmap;

import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    @Override
    public V get(K key) {
        if (search(root, key) == null) {
            return null;
        }
        return search(root, key).value;
    }

    @Override
    public boolean containsKey(K key) {
        return search(root, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();

        for (K k : this) {
            set.add(k);
        }

        return set;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<Node> nodes;

        public BSTMapIterator() {
            nodes = new Stack<>();

            Node p = root;
            while (p != null) {
                nodes.push(p);
                p = p.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public K next() {
            Node cur = nodes.pop();
            if (cur.right != null) {
                Node p = cur.right;
                while (p != null) {
                    nodes.push(p);
                    p = p.left;
                }
            }
            return cur.key;
        }
    }

    private Node search(Node cur, K key) {
        if (cur == null) {
            return null;
        }

        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            return search(cur.left, key);
        } else if (cmp > 0) {
            return search(cur.right, key);
        } else {
            return cur;
        }
    }

    private Node insert(Node cur, K key, V value) {
        if (cur == null) {
            return new Node(key, value);
        }

        if (key.compareTo(cur.key) < 0) {
            cur.left = insert(cur.left, key, value);
        } else if (key.compareTo(cur.key) > 0) {
            cur.right = insert(cur.right, key, value);
        } else {
            cur.value = value;
        }

        return cur;
    }


    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            size++;
            this.key = key;
            this.value = value;
        }
    }
}
