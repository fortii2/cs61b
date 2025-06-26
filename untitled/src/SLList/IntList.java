package SLList;

/**
 * LinkedList
 */
public class IntList {

    public int first;

    public IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    public IntList addFirst(int x) {
        return new IntList(x, this);
    }

    public static void main(String[] args) {
        IntList list = new IntList(3, new IntList(5, new IntList(7, new IntList(9, null))));
        System.out.print(list.size() + "\t");
        System.out.print(list.iterativeSize() + "\t");
        System.out.println(list.joshSize());

        System.out.println("==========");

        System.out.print(list.get(0) + "\t");
        System.out.print(list.get(1) + "\t");
        System.out.println(list.get(2));

        System.out.println("==========");
        IntList incrList = incrList(list, 1);

        System.out.print(list.get(0) + "\t");
        System.out.print(list.get(1) + "\t");
        System.out.println(list.get(2));

        System.out.print(incrList.get(0) + "\t");
        System.out.print(incrList.get(1) + "\t");
        System.out.println(incrList.get(2));

        System.out.println("==========");
        System.out.print(list.get(0) + "\t");
        System.out.print(list.get(1) + "\t");
        System.out.println(list.get(2));

        dincrList(list, 1);

        System.out.print(list.get(0) + "\t");
        System.out.print(list.get(1) + "\t");
        System.out.println(list.get(2));

        System.out.println("==========");
        System.out.print(list.get(0) + "\t");
        System.out.print(list.get(1) + "\t");
        System.out.println(list.get(2));

        IntList addFirst = list.addFirst(2);

        System.out.print(addFirst.get(0) + "\t");
        System.out.print(addFirst.get(1) + "\t");
        System.out.print(addFirst.get(2) + "\t");
        System.out.println(addFirst.get(3));
    }

    /**
     * mine
     */
    public int size() {
        int count = 0;

        if (this.rest == null) {
            return count + 1;
        }

        return this.rest.size() + 1;
    }

    /**
     * from josh, save 1 variable.
     */
    public int joshSize() {
        if (this.rest == null) {
            return 1;
        }

        return 1 + this.rest.joshSize();
    }

    public int iterativeSize() {
        IntList list = this;
        int count = 0;

        while (list != null) {
            count++;
            list = list.rest;
        }

        return count;
    }

    public int get(int index) {
        if (index == 0) {
            return this.first;
        }

        return this.rest.get(index - 1);
    }

    // extra exercise:

    /**
     * returns an IntList identical to L. but with all values incremented by x.
     * values in L cannot change!
     */
    public static IntList incrList(IntList L, int x) {
        if (L.rest == null) {
            return new IntList(L.first, null);
        }

        return new IntList(L.first + x, incrList(L.rest, x));
    }

    /**
     * returns an IntList identical to L. but with all values incremented by x.
     * not allowed to use 'new' (to save memory).
     */
    public static IntList dincrList(IntList L, int x) {
        L.first += x;

        if (L.rest == null) {
            return L;
        }

        return dincrList(L.rest, x);
    }
}
