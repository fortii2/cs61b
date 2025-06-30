public class MyAList {

    private int size;
    private int[] arr;

    public MyAList() {
        arr = new int[100];
        size = 0;
    }

    /**
     * resizing is necessary, but I don't have time to write it.
     */
    public void addLast(int x) {
        arr[size] = x;
        size++;
    }

    public int getLast() {
        return arr[size - 1];
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return arr[index];
    }

    /**
     * arr[size-1] = null; is optional for gc to collect useless object.
     */
    public void removeLast() {
        size--;
    }
}
