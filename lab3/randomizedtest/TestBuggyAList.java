package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        b.addLast(4);
        b.addLast(5);
        b.addLast(6);

        Assert.assertEquals(a.removeLast(), b.removeLast());
        Assert.assertEquals(a.removeLast(), b.removeLast());
        Assert.assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);

            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                a.addLast(randVal);
                b.addLast(randVal);

                Assert.assertEquals(a.getLast(), b.getLast());
            } else if (operationNumber == 1) {
                // size
                int aSize = a.size();
                int bSize = b.size();

                Assert.assertEquals(aSize, bSize);
            } else if (a.size() > 0 && b.size() > 0) {
                Assert.assertEquals(a.getLast(), b.getLast());

                Assert.assertEquals(a.removeLast(), b.removeLast());
            }
        }
    }
}
