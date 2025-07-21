
public class SLList {

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            this.item = i;
            this.next = n;
        }
    }

    // use sentinel's item to save size
    private IntNode sentinel;

    public SLList() {
        this.sentinel = new IntNode(0, null);
    }

    public SLList(int x) {
        this();
        this.sentinel.item += 1;
        this.sentinel.next = new IntNode(x, null);
    }

    /* add to 1st place */
    public void addFirst(int x) {
        this.sentinel.item += 1;
        this.sentinel.next = new IntNode(x, this.sentinel.next);
    }

    public int getFirst() {
        return this.sentinel.next.item;
    }

    public void addLast(int x) {
        this.sentinel.item += 1;
        IntNode p = this.sentinel;

        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    public int getLast() {
        IntNode p = this.sentinel.next;

        while (p.next != null) {
            p = p.next;
        }

        return p.item;
    }

    /**
     * size helper, but can be replaced by providing an int size variable in this class.
     * private static int size(IntNode p){
     * if (p.next == null){
     * return 1;
     * }
     * return 1 + size(p.next);
     * }
     * <p>
     * public int size(){
     * return size(this.first);
     * }
     */

    public int size() {
        return this.sentinel.item;
    }

    public static void main(String[] args) {
        SLList slList = new SLList(1);
        System.out.println(slList.getFirst());
    }

}