package comparator;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ComparatorCatTest {
    @Test
    public void test() {
        ArrayList<Cat> cats = new ArrayList<>();
        Cat b = new Cat("b", 20);
        cats.add(new Cat("a",10));
        cats.add(b);
        cats.add(new Cat("c",-10));

        Truth.assertThat(Collections.max(cats, Cat.catComparator)).isEqualTo(b);
    }
}
