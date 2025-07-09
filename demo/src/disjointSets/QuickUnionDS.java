package disjointSets;

/**
 * 将 disjoint set 视作一棵树，每个元素在 List 中保存其 parent height
 * 2 sets' union = 1 element links to the other.
 */
public class QuickUnionDS implements DisjointSets {

    private int[] parents;

    public QuickUnionDS(int object_nums) {
        parents = new int[object_nums];

        for (int i = 0; i < object_nums; i++) {
            parents[i] = -1;
        }
    }

    @Override
    public boolean isConnected(int ds1, int ds2) {
        return root(ds1) == root(ds2);
    }

    @Override
    public void connect(int ds1, int ds2) {
        int target_root = root(ds1);
        int current_root = root(ds2);

        parents[current_root] = target_root;
    }

    public int root(int index) {
        while (parents[index] >= 0) {
            index = parents[index];
        }

        return index;
    }
}
