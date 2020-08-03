// AutoGrowingChainedHashTable.java
package L5_HashTables.Homework5;

import L2_LinkedList.SinglyLinkedList;
import L5_HashTables.KeyValuePair;
import exceptions.DuplicateKeyException;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class AutoGrowingChainedHashTable<K, V> {
    // Table of buckets
    private SinglyLinkedList<KeyValuePair<K, V>>[] table;
    private double maxLoadFactor;
    private double resizeMultiplier;
    private int size;
    private int buckets;


    public AutoGrowingChainedHashTable() {
        this(997, 1.5, 2);  // A prime number of buckets
    }

    @SuppressWarnings("unchecked")
    public AutoGrowingChainedHashTable(int buckets, double maxLoadFactor, double resizeMultiplier) {
        // Create table of empty buckets
        table = new SinglyLinkedList[buckets];
        for (int i = 0; i < table.length; ++i) {
            table[i] = new SinglyLinkedList<KeyValuePair<K, V>>();
        }

        size = 0;
        this.maxLoadFactor = maxLoadFactor;
        this.resizeMultiplier = resizeMultiplier;
        this.buckets = buckets;

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getBucketsNum() {
        return buckets;
    }

    public double getMaxLoadFactor() {
        return maxLoadFactor;
    }

    public double getResizeMultiplier() {
        return resizeMultiplier;
    }

    public double getLoadFactor() {
        return ((double) size) / table.length;
    }

    private SinglyLinkedList<KeyValuePair<K, V>> getBucket(K key, SinglyLinkedList<KeyValuePair<K, V>>[] targetTable) {
        // Multiplication method
        // hash codes are mapped to buckets of the target table
        double A = (Math.sqrt(5.0) - 1) / 2;
        int index = (int) Math.floor((key.hashCode() * A) % 1 * targetTable.length);
        return targetTable[index];
    }

    private SinglyLinkedList<KeyValuePair<K, V>> getBucket(K key) {
        // Multiplication method
        // hash codes are mapped to buckets of table
        double A = (Math.sqrt(5.0) - 1) / 2;
        int index = (int) Math.floor((key.hashCode() * A) % 1 * table.length);
        return table[index];
    }


    public void insert(K key, V value) throws
            IllegalArgumentException,
            DuplicateKeyException {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        if (contains(key)) {                                                                // O(Load Factor)
            throw new DuplicateKeyException();
        }

        getBucket(key).insertHead(new KeyValuePair<K, V>(key, value));
        ++size;

        resizeHashTable(); //if load factor exceeds the maximum load factor                 // O(k+n)
    }


    private void resizeHashTable() {
        //if load factor exceeds the maximum load factor
        if (getLoadFactor() >= getMaxLoadFactor()) {

            // create a new table
            SinglyLinkedList<KeyValuePair<K, V>>[] newTable =
                    new SinglyLinkedList[(table.length * (int) resizeMultiplier)];
            for (int i = 0; i < newTable.length; ++i) {                                     // O(k), k= number of buckets in resized hash table
                newTable[i] = new SinglyLinkedList<KeyValuePair<K, V>>();
            }

            // rehash all elements to new table
            for (K oldKey : this.keys()) {                                                  // O(n), n= number of elements stored in hash table
                V oldValue = lookup(oldKey);
                getBucket(oldKey, newTable).insertTail(new KeyValuePair<K, V>(oldKey, oldValue));
            } // Use insertTail() to exactly copy the old table to new table including the order of SLL

            table = newTable;
            buckets = table.length;

        }

    }

    public V remove(K key) throws
            IllegalArgumentException,
            NoSuchElementException {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        // If empty bucket
        SinglyLinkedList<KeyValuePair<K, V>> bucket = getBucket(key);
        if (bucket.isEmpty()) {
            throw new NoSuchElementException();
        }

        // If at head of bucket
        SinglyLinkedList<KeyValuePair<K, V>>.Element elem = bucket.getHead();
        if (key.equals(elem.getData().getKey())) {
            --size;
            return bucket.removeHead().getValue();
        }

        // Scan rest of bucket
        SinglyLinkedList<KeyValuePair<K, V>>.Element prev = elem;
        elem = elem.getNext();
        while (elem != null) {
            if (key.equals(elem.getData().getKey())) {
                --size;
                return bucket.removeAfter(prev).getValue();
            }
            prev = elem;
            elem = elem.getNext();
        }

        throw new NoSuchElementException();
    }


    public V lookup(K key) throws
            IllegalArgumentException,
            NoSuchElementException {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        // Scan bucket for key
        SinglyLinkedList<KeyValuePair<K, V>>.Element elem = getBucket(key).getHead();
        while (elem != null) {
            if (key.equals(elem.getData().getKey())) {
                return elem.getData().getValue();
            }
            elem = elem.getNext();
        }

        throw new NoSuchElementException();
    }

    public boolean contains(K key) {
        try {
            lookup(key);
        } catch (IllegalArgumentException ex) {
            return false;
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    private class KeysIterator implements Iterator<K> {
        private int remaining;  // Number of keys remaining to iterate
        private int bucket;     // Position in bucket we're iterating
        private SinglyLinkedList<KeyValuePair<K, V>>.Element elem;
        // Bucket we're iterating

        public KeysIterator() {
            remaining = AutoGrowingChainedHashTable.this.size;
            bucket = 0;
            elem = AutoGrowingChainedHashTable.this.table[bucket].getHead();
        }

        public boolean hasNext() {
            return remaining > 0;
        }

        public K next() {
            if (hasNext()) {
                // If we've hit end of bucket, move to next non-empty bucket
                while (elem == null) {
                    elem = AutoGrowingChainedHashTable.this.table[++bucket].getHead();
                }

                // Get key
                K key = elem.getData().getKey();

                // Move to next element and decrement entries remaining
                elem = elem.getNext();
                --remaining;

                return key;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public Iterable<K> keys() {
        return new Iterable<K>() {
            public Iterator<K> iterator() {
                return new KeysIterator();
            }
        };
    }

}
