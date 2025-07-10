import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     * (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    @Test
    public void xyTo1DTest() {
        Percolation percolation = new Percolation(4);
        Truth.assertThat(percolation.xyTo1D(0, 0)).isEqualTo(0);
        Truth.assertThat(percolation.xyTo1D(0, 1)).isEqualTo(1);
        Truth.assertThat(percolation.xyTo1D(0, 2)).isEqualTo(2);
        Truth.assertThat(percolation.xyTo1D(0, 3)).isEqualTo(3);

        Truth.assertThat(percolation.xyTo1D(1, 0)).isEqualTo(4);
        Truth.assertThat(percolation.xyTo1D(1, 1)).isEqualTo(5);
        Truth.assertThat(percolation.xyTo1D(1, 2)).isEqualTo(6);
        Truth.assertThat(percolation.xyTo1D(1, 3)).isEqualTo(7);

        Truth.assertThat(percolation.xyTo1D(2, 0)).isEqualTo(8);
        Truth.assertThat(percolation.xyTo1D(2, 1)).isEqualTo(9);
        Truth.assertThat(percolation.xyTo1D(2, 2)).isEqualTo(10);
        Truth.assertThat(percolation.xyTo1D(2, 3)).isEqualTo(11);

        Truth.assertThat(percolation.xyTo1D(3, 0)).isEqualTo(12);
        Truth.assertThat(percolation.xyTo1D(3, 1)).isEqualTo(13);
        Truth.assertThat(percolation.xyTo1D(3, 2)).isEqualTo(14);
        Truth.assertThat(percolation.xyTo1D(3, 3)).isEqualTo(15);
    }

    @Test
    public void openTest() {
        Percolation percolation = new Percolation(3);
        Truth.assertThat(percolation.isOpen(0, 0)).isFalse();
        Truth.assertThat(percolation.isOpen(1, 1)).isFalse();
        percolation.open(0, 0);
        Truth.assertThat(percolation.isOpen(0, 0)).isTrue();
        Truth.assertThat(percolation.isOpen(1, 1)).isFalse();

        percolation.open(1, 1);
        Truth.assertThat(percolation.isOpen(1, 1)).isTrue();
    }

    /**
     * see by visualizer
     */
    @Test
    public void unionAroundTest() {
        Percolation percolation = new Percolation(3);
        percolation.open(0, 0);
        percolation.open(1, 1);
        percolation.open(0, 1);
        percolation.open(2, 1);
    }

    @Test
    public void isFullTest() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(2, 1);
        Truth.assertThat(percolation.isFull(0, 1)).isFalse();
        Truth.assertThat(percolation.isFull(1, 1)).isFalse();
        Truth.assertThat(percolation.isFull(2, 1)).isFalse();

        percolation.open(0, 1);
        Truth.assertThat(percolation.isFull(0, 1)).isTrue();
        Truth.assertThat(percolation.isFull(1, 1)).isTrue();
        Truth.assertThat(percolation.isFull(2, 1)).isTrue();

        percolation.open(2, 2);
        Truth.assertThat(percolation.isFull(2, 2)).isTrue();
    }

    @Test
    public void numberOfOpenSitesTest() {
        Percolation percolation = new Percolation(3);
        Truth.assertThat(percolation.numberOfOpenSites()).isEqualTo(0);

        percolation.open(0, 0);
        Truth.assertThat(percolation.numberOfOpenSites()).isEqualTo(1);

        percolation.open(1, 1);
        Truth.assertThat(percolation.numberOfOpenSites()).isEqualTo(2);
    }

    @Test
    public void percolatesTest() {
        Percolation percolation = new Percolation(3);
        Truth.assertThat(percolation.percolates()).isFalse();

        percolation.open(1,1);
        percolation.open(0,1);
        Truth.assertThat(percolation.percolates()).isFalse();

        percolation.open(0,0);
        percolation.open(0,2);
        percolation.open(1,0);
        percolation.open(1,2);
        Truth.assertThat(percolation.percolates()).isFalse();

        percolation.open(2,0);
        Truth.assertThat(percolation.percolates()).isTrue();

        percolation.open(2,1);
        Truth.assertThat(percolation.percolates()).isTrue();

        percolation.open(2,2);
        Truth.assertThat(percolation.percolates()).isTrue();
    }
}
