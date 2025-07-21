package disjointSets;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

public class QuickUnionDSTest {

    @Test
    public void test1() {
        QuickUnionDS ds = new QuickUnionDS(7);
        Truth.assertThat(ds.isConnected(3, 4)).isFalse();

        ds.connect(0, 3);
        Truth.assertThat(ds.isConnected(0, 3)).isTrue();

        ds.connect(4, 5);
        Truth.assertThat(ds.isConnected(4, 5)).isTrue();

        ds.connect(3, 5);
        Truth.assertThat(ds.root(5)).isEqualTo(0);
        Truth.assertThat(ds.isConnected(0, 5)).isTrue();
        Truth.assertThat(ds.isConnected(1, 5)).isFalse();
        Truth.assertThat(ds.isConnected(2, 6)).isFalse();


    }
}
