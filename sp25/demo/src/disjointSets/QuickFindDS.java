package disjointSets;

/**
 * 保存每个元素从属于哪个集合 ->
 * if 2 objs in a same set, isConnected() = O(1)
 */
public class QuickFindDS implements DisjointSets {

    private final int[] arr;

    public QuickFindDS(int i) {
        arr = new int[i];

        for (int j = 0; j < i; j++) {
            arr[j] = j;
        }
    }

    @Override
    public boolean isConnected(int ds1, int ds2) {
        return arr[ds1] == arr[ds2];
    }

    @Override
    public void connect(int ds1, int ds2) {
        int ds1in = arr[ds1];
        int ds2in = arr[ds2];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ds1in) {
                arr[i] = ds2in;
            }
        }
    }
}
