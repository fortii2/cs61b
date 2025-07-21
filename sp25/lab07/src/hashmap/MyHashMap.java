package hashmap;

import org.checkerframework.checker.units.qual.K;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A hash table-backed Map implementation.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Collection<Node>[] buckets;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int size;

    /**
     * Constructors
     */

    public MyHashMap() {
        generateBuckets();
    }


    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        generateBuckets();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor      maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        generateBuckets();
    }

    private void generateBuckets() {
        this.size = 0;
        buckets = new Collection[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * Note that that this is referring to the hash table bucket itself,
     * not the hash map itself.
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */

    @Override
    public void put(K key, V value) {
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), capacity)];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        if ((double) size / capacity > loadFactor) {
            resize();
        }

        bucket = buckets[Math.floorMod(key.hashCode(), capacity)];
        bucket.add(new Node(key, value));
        size++;
    }

    private void resize() {
        List<Node> lst = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            lst.addAll(buckets[i]);
        }

        capacity = capacity * 2;
        generateBuckets();

        for (Node node : lst) {
            put(node.key, node.value);
        }
    }

    @Override
    public V get(K key) {
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), capacity)];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), capacity)];
        for (Node node : bucket) {
            if (node.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        generateBuckets();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Arrays.stream(buckets).forEach(bucket -> bucket.forEach(node -> keys.add(node.key)));
        return keys;
    }

    @Override
    public V remove(K key) {
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), capacity)];
        V value = null;
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                value = node.value;
                bucket.remove(node);

            }
        }
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {

        private List<Node> nodes;

        public MyHashMapIterator() {
            nodes = new ArrayList<>();
            Arrays.stream(buckets).forEach(bucket -> nodes.addAll(bucket));
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public K next() {
            Node node = nodes.removeFirst();
            return node.key;
        }
    }
}
