package deque;

import java.util.ArrayList;
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
        arr[first_index] = x;
        first_index--;
        size++;

        if (first_index < 0) {
            first_index = Math.floorMod(first_index, arr.length);
        }
    }

    @Override
    public void addLast(T x) {
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

        while (start != end_index) {
            returnList.add(arr[start]);
            start = Math.floorMod(++start, arr.length);
        }

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
        int target_index = Math.floorMod(first_index + 1, arr.length);
        T first = get(target_index);

        if (first != null){
            arr[target_index] = null;
            size--;
            first_index = target_index;
        }

        return first;
    }

    @Override
    public T removeLast() {
        int target_index = Math.floorMod(end_index - 1, arr.length);
        T last = get(target_index);

        if (last != null){
            arr[target_index] = null;
            size--;
            end_index = target_index;
        }

        return last;
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

}
