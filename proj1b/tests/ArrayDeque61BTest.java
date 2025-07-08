import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void addFirstTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        deque.addFirst(9);
        deque.addFirst(8);
        deque.addFirst(7);
        deque.addFirst(6);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
    }

    @Test
    public void addLastTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        deque.addLast(9);
        deque.addLast(8);
        deque.addLast(7);
        deque.addLast(6);
        deque.addLast(5);
        deque.addLast(4);
        deque.addLast(3);
        deque.addLast(2);
        deque.addLast(1);
    }

    @Test
    public void getTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        assertThat(deque.get(-1)).isEqualTo(null);
        assertThat(deque.get(0)).isEqualTo(null);
        assertThat(deque.get(1)).isEqualTo(null);
        assertThat(deque.get(2)).isEqualTo(null);
        assertThat(deque.get(3)).isEqualTo(null);
        assertThat(deque.get(4)).isEqualTo(null);
        assertThat(deque.get(5)).isEqualTo(null);
        assertThat(deque.get(6)).isEqualTo(null);
        assertThat(deque.get(7)).isEqualTo(null);
        assertThat(deque.get(8)).isEqualTo(null);
        assertThat(deque.get(9)).isEqualTo(null);
        assertThat(deque.get(10)).isEqualTo(null);

        deque.addFirst(0);
        assertThat(deque.get(-1)).isEqualTo(null);
        assertThat(deque.get(0)).isEqualTo(0);
        assertThat(deque.get(1)).isEqualTo(null);
        assertThat(deque.get(2)).isEqualTo(null);
        assertThat(deque.get(3)).isEqualTo(null);
        assertThat(deque.get(4)).isEqualTo(null);
        assertThat(deque.get(5)).isEqualTo(null);
        assertThat(deque.get(6)).isEqualTo(null);
        assertThat(deque.get(7)).isEqualTo(null);
        assertThat(deque.get(8)).isEqualTo(null);
        assertThat(deque.get(9)).isEqualTo(null);
        assertThat(deque.get(10)).isEqualTo(null);

        deque.addFirst(1);
        assertThat(deque.get(-1)).isEqualTo(null);
        assertThat(deque.get(0)).isEqualTo(0);
        assertThat(deque.get(1)).isEqualTo(null);
        assertThat(deque.get(2)).isEqualTo(null);
        assertThat(deque.get(3)).isEqualTo(null);
        assertThat(deque.get(4)).isEqualTo(null);
        assertThat(deque.get(5)).isEqualTo(null);
        assertThat(deque.get(6)).isEqualTo(null);
        assertThat(deque.get(7)).isEqualTo(1);
        assertThat(deque.get(8)).isEqualTo(null);
        assertThat(deque.get(9)).isEqualTo(null);
        assertThat(deque.get(10)).isEqualTo(null);

        deque.addLast(2);
        deque.addLast(4);
        assertThat(deque.get(-1)).isEqualTo(null);
        assertThat(deque.get(0)).isEqualTo(0);
        assertThat(deque.get(1)).isEqualTo(2);
        assertThat(deque.get(2)).isEqualTo(4);
        assertThat(deque.get(3)).isEqualTo(null);
        assertThat(deque.get(4)).isEqualTo(null);
        assertThat(deque.get(5)).isEqualTo(null);
        assertThat(deque.get(6)).isEqualTo(null);
        assertThat(deque.get(7)).isEqualTo(1);
        assertThat(deque.get(8)).isEqualTo(null);
        assertThat(deque.get(9)).isEqualTo(null);
        assertThat(deque.get(10)).isEqualTo(null);
    }

    @Test
    public void isEmptyTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
        deque.addLast(1);
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void toListTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);

        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        assertThat(deque.toList()).containsExactly(1, 2, 3, 4, 5, 6).inOrder();
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        assertThat(deque.removeFirst()).isEqualTo(null);

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        assertThat(deque.removeFirst()).isEqualTo(8);
        assertThat(deque.removeFirst()).isEqualTo(7);
        assertThat(deque.removeFirst()).isEqualTo(6);
        assertThat(deque.removeFirst()).isEqualTo(5);
        assertThat(deque.removeFirst()).isEqualTo(4);
        assertThat(deque.removeFirst()).isEqualTo(3);
        assertThat(deque.removeFirst()).isEqualTo(2);
        assertThat(deque.removeFirst()).isEqualTo(1);
    }

    @Test
    public void removeLastTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        assertThat(deque.removeLast()).isEqualTo(null);

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        assertThat(deque.removeLast()).isEqualTo(8);
        assertThat(deque.removeLast()).isEqualTo(7);
        assertThat(deque.removeLast()).isEqualTo(6);
        assertThat(deque.removeLast()).isEqualTo(5);
        assertThat(deque.removeLast()).isEqualTo(4);
        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.removeLast()).isEqualTo(2);
        assertThat(deque.removeLast()).isEqualTo(1);
    }

    @Test
    public void addFirstAndLastTogetherTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        assertThat(deque.removeFirst()).isEqualTo(1);
    }

    @Test
    public void sizeTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);

        deque.addLast(1);
        assertThat(deque.size()).isEqualTo(1);

        deque.addFirst(2);
        assertThat(deque.size()).isEqualTo(2);

        deque.removeFirst();
        assertThat(deque.size()).isEqualTo(1);

        deque.removeFirst();
        assertThat(deque.size()).isEqualTo(0);

        deque.addLast(1);
        assertThat(deque.size()).isEqualTo(1);

        deque.addFirst(2);
        assertThat(deque.size()).isEqualTo(2);

        deque.removeLast();
        assertThat(deque.size()).isEqualTo(1);

        deque.removeLast();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void getRecursiveTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        Assert.assertThrows(UnsupportedOperationException.class, () -> deque.getRecursive(0));
    }

    @Test
    public void biggerArrTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addLast(-1);
        deque.addFirst(8);
        deque.addFirst(9);
        deque.addFirst(10);
        deque.addLast(-2);
        deque.addFirst(11);
        deque.addFirst(12);
        deque.addFirst(13);
        deque.addFirst(14);
        deque.addFirst(15);
        deque.addLast(-3);

        assertThat(deque.size()).isEqualTo(18);
        assertThat(deque.toList()).containsExactly(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, -1, -2, -3).inOrder();
    }

    @Test
    public void smallerArrTest() {
        ArrayDeque61B<Object> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 19; i++) {
            deque.removeLast();
        }

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(0).inOrder();
    }

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");

        Deque61B<String> ad2 = new ArrayDeque61B<>();
        ad2.addLast("front"); // after this call we expect: ["front"]
        ad2.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad2.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad2).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);

        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad1).isEqualTo(ad2);
    }

    @Test
    public void testToString() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque61B<String> ad = new ArrayDeque61B<>();

        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        assertThat(ad.toString()).isEqualTo("[front, middle, back]");

    }
}
