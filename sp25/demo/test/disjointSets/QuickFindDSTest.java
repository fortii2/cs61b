package disjointSets;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

public class QuickFindDSTest {

    @Test
    public void test1() {
        QuickFindDS ds = new QuickFindDS(7);
        ds.connect(0, 1);
        Truth.assertThat(ds.isConnected(0, 1)).isTrue();
        Truth.assertThat(ds.isConnected(1, 2)).isFalse();
        Truth.assertThat(ds.isConnected(2, 3)).isFalse();

        ds.connect(1, 2);
        Truth.assertThat(ds.isConnected(0, 1)).isTrue();
        Truth.assertThat(ds.isConnected(1, 2)).isTrue();
        Truth.assertThat(ds.isConnected(2, 3)).isFalse();
    }
}
