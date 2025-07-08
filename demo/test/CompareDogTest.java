import comparator.Dog;
import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class CompareDogTest {
    @Test
    public void compareDog() {
        Dog john = new Dog("John", 10);
        Dog mew = new Dog("Mew", 20);
        Dog vivian = new Dog("Vivian", 10);

        assertThat(john.compareTo(vivian)).isEqualTo(0);
        assertThat(john.compareTo(mew)).isEqualTo(-10);
        assertThat(mew.compareTo(john)).isEqualTo(10);

    }
}
