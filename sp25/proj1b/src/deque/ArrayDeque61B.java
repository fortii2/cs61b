package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] arr;
    private int size;
    private int first_index;
    private int end_index;


    public ArrayDeque61B() {
        arr = (T[]) new Object[8];
        size = 0;
        first_index = 0;
        end_index = 1;
    }

    @Override
    public void addFirst(T x) {
        resizing();
        arr[first_index] = x;
        first_index--;
        size++;

        if (first_index < 0) {
            first_index = Math.floorMod(first_index, arr.length);
        }
    }

    @Override
    public void addLast(T x) {
        resizing();
        arr[end_index] = x;
        end_index++;
        size++;

        if (end_index >= arr.length) {
            end_index = Math.floorMod(end_index, arr.length);
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        int start = Math.floorMod(first_index + 1, arr.length);
        int i = 0;

        do {
            returnList.add(arr[start]);
            start = Math.floorMod(++start, arr.length);
            i++;
        } while (i < size);

        return returnList.stream().toList();
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
        resizing();
        int target_index = Math.floorMod(first_index + 1, arr.length);
        T first = get(target_index);

        if (first != null) {
            arr[target_index] = null;
            size--;
            first_index = target_index;
        }

        return first;
    }

    @Override
    public T removeLast() {
        resizing();
        int target_index = Math.floorMod(end_index - 1, arr.length);
        T last = get(target_index);

        if (last != null) {
            arr[target_index] = null;
            size--;
            end_index = target_index;
        }

        return last;
    }

    private void resizing() {
        if (size == arr.length) {
            biggerArr();
        }

        if (arr.length >= 16 && size < (arr.length * 0.25)) {
            smallerArr();
        }
    }

    private void smallerArr() {
        T[] target = (T[]) new Object[(int) (arr.length * 0.5)];
        int start = Math.floorMod(first_index + 1, arr.length);
        int i = 0;

        while (i < size) {
            target[i++] = arr[start];
            start = Math.floorMod(start + 1, arr.length);
        }

        arr = target;
        first_index = arr.length - 1;
        end_index = size;
    }

    private void biggerArr() {
        T[] target = (T[]) new Object[(int) (arr.length * 1.5)];

        int start = Math.floorMod(first_index + 1, arr.length);
        int i = 0;

        while (i < size) {
            target[i++] = arr[start];
            start = Math.floorMod(start + 1, arr.length);
        }

        arr = target;

        first_index = arr.length - 1;
        end_index = size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= arr.length) {
            return null;
        }

        return arr[index];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        public int count;
        public int index;

        public ArrayDeque61BIterator() {
            count = 0;
            index = Math.floorMod(first_index + 1, arr.length);
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T value = arr[index];
            index = Math.floorMod(index + 1, arr.length);
            count++;
            return value;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayDeque61B<?> ad) {
            if (ad.size != this.size) return false;

            if (ad == this) return true;

            for (T t : this) {
                if (!ad.toList().contains(t)) return false;
            }

            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
