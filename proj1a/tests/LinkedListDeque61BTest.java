import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDeque61BTest {

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    //Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.isEmpty()).isTrue();

        lst.addFirst(1);
        lst.addLast(2);
        assertThat(lst.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.size()).isEqualTo(0);

        lst.addLast(2);
        assertThat(lst.size()).isEqualTo(1);

        lst.addFirst(1);
        assertThat(lst.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.get(0)).isEqualTo(null);
        assertThat(lst.get(-1)).isEqualTo(null);
        assertThat(lst.get(1)).isEqualTo(null);


        lst.addFirst(1);
        assertThat(lst.get(0)).isEqualTo(1);
        assertThat(lst.get(1)).isEqualTo(null);

        lst.addLast(3);
        lst.addLast(7);

        assertThat(lst.get(0)).isEqualTo(1);
        assertThat(lst.get(1)).isEqualTo(3);
        assertThat(lst.get(2)).isEqualTo(7);
        assertThat(lst.get(3)).isEqualTo(null);
        assertThat(lst.get(-1)).isEqualTo(null);
        assertThat(lst.get(-7)).isEqualTo(null);
        assertThat(lst.get(42)).isEqualTo(null);
        assertThat(lst.get(43)).isEqualTo(null);
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.getRecursive(0)).isEqualTo(null);
        assertThat(lst.getRecursive(-1)).isEqualTo(null);
        assertThat(lst.getRecursive(1)).isEqualTo(null);


        lst.addFirst(1);
        lst.addLast(3);
        lst.addLast(7);

        assertThat(lst.getRecursive(0)).isEqualTo(1);
        assertThat(lst.getRecursive(1)).isEqualTo(3);
        assertThat(lst.getRecursive(2)).isEqualTo(7);
        assertThat(lst.getRecursive(3)).isEqualTo(null);
        assertThat(lst.getRecursive(4)).isEqualTo(null);
        assertThat(lst.getRecursive(-1)).isEqualTo(null);
        assertThat(lst.getRecursive(-7)).isEqualTo(null);
        assertThat(lst.getRecursive(42)).isEqualTo(null);
        assertThat(lst.getRecursive(43)).isEqualTo(null);
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.toList()).containsExactly().inOrder();

        lst.addLast(1);
        assertThat(lst.toList()).containsExactly(1).inOrder();

        lst.addLast(2);
        assertThat(lst.toList()).containsExactly(1, 2).inOrder();

    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.removeFirst()).isEqualTo(null);

        lst.addLast(1);
        assertThat(lst.removeFirst()).isEqualTo(1);
        assertThat(lst.removeFirst()).isEqualTo(null);

        lst.addFirst(1);
        assertThat(lst.removeFirst()).isEqualTo(1);
        assertThat(lst.removeFirst()).isEqualTo(null);

        lst.addFirst(1);
        lst.addLast(2);
        lst.addLast(3);
        lst.addFirst(0);
        assertThat(lst.toList()).containsExactly(0, 1, 2, 3).inOrder();
        assertThat(lst.removeFirst()).isEqualTo(0);
        assertThat(lst.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> lst = new LinkedListDeque61B<>();
        assertThat(lst.removeLast()).isEqualTo(null);

        lst.addLast(1);
        assertThat(lst.removeLast()).isEqualTo(1);
        assertThat(lst.removeLast()).isEqualTo(null);

        lst.addFirst(1);
        assertThat(lst.removeLast()).isEqualTo(1);
        assertThat(lst.removeLast()).isEqualTo(null);

        lst.addFirst(1);
        lst.addLast(2);
        lst.addLast(3);
        lst.addFirst(0);
        assertThat(lst.toList()).containsExactly(0, 1, 2, 3).inOrder();
        assertThat(lst.removeLast()).isEqualTo(3);
        assertThat(lst.toList()).containsExactly(0, 1, 2).inOrder();
    }
}