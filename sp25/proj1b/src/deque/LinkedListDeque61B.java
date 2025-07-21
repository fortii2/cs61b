package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private final Node sentinel;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque61BIterator();
    }

    private class LinkedListDeque61BIterator implements Iterator<T> {

        public int index;

        public LinkedListDeque61BIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T value = get(index);
            index++;
            return value;
        }
    }

    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node() {
        }

        public Node(T value) {
            this.item = value;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node node = new Node(x);
        node.prev = sentinel;
        node.next = sentinel.next;

        node.next.prev = node;
        sentinel.next = node;

        size++;
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(x);
        node.prev = sentinel.prev;
        node.next = sentinel;

        sentinel.prev.next = node;
        sentinel.prev = node;

        size++;
    }

    @Override
    public List<T> toList() {
        ArrayList<T> lst = new ArrayList<>();
        Node p = sentinel.next;

        while (p != sentinel) {
            lst.add(p.item);
            p = p.next;
        }

        return lst;
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
        T first = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        return first;
    }

    @Override
    public T removeLast() {
        T last = sentinel.prev.item;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        return last;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;

        if (index >= 0 && p.next != p) {
            while (p != sentinel) {
                if (index == 0) {
                    return p.item;
                }

                p = p.next;
                index--;
            }
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        }

        return getRecursiveHelper(index - 1, p.next);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedListDeque61B<?> lld) {
            if (lld.size != this.size) {
                return false;
            }

            if (lld == this) {
                return true;
            }

            for (T t : this) {
                if (!lld.toList().contains(t)) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
