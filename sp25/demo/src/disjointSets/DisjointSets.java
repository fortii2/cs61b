package disjointSets;

public interface DisjointSets {

    boolean isConnected(int ds1, int ds2);

    void connect(int ds1, int ds2);
}
