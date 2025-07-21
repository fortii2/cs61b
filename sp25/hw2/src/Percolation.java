import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF union;
    private final boolean[][] opened;
    private int openSite;
    private final int virtual_top;
    private final int virtual_bottom;

    public Percolation(int N) {
        opened = new boolean[N][N];
        openSite = 0;

        union = new WeightedQuickUnionUF(N * N + 2);
        virtual_top = N * N;
        virtual_bottom = N * N + 1;
    }

    public void open(int row, int col) {
        if (opened[row][col]) return;

        //  virtual top
        if (row == 0) {
            union.union(virtual_top, xyTo1D(row, col));
        }

        //  virtual bottom
        if (row == opened.length - 1) {
            union.union(virtual_bottom, xyTo1D(row, col));
        }

        opened[row][col] = true;
        openSite++;

        unionAround(row, col);
    }

    private void unionAround(int row, int col) {
        tryUnion(row, col, row + 1, col);
        tryUnion(row, col, row - 1, col);
        tryUnion(row, col, row, col + 1);
        tryUnion(row, col, row, col - 1);
    }

    private void tryUnion(int row, int col, int near_row, int near_col) {
        if (near_row < 0 || near_col < 0) return;
        if (near_row >= opened.length || near_col >= opened.length) return;

        if (isOpen(near_row, near_col)) {
            union.union(xyTo1D(row, col), xyTo1D(near_row, near_col));
        }
    }


    public boolean isOpen(int row, int col) {
        return opened[row][col];
    }

    public boolean isFull(int row, int col) {
        return union.connected(xyTo1D(row, col), virtual_top);
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        return union.connected(virtual_top, virtual_bottom);
    }

    public int xyTo1D(int x, int y) {
        return x * opened.length + y;
    }
}
