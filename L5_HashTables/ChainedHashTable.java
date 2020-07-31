// ChainedHashTable.java
package L5_HashTables;

import L2_LinkedList.SinglyLinkedList;
import exceptions.DuplicateKeyException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChainedHashTable<K, V> {
    // 建立array，每个元素类型是SLL，SLL里面data类型是keyValuePair object
    private SinglyLinkedList<KeyValuePair<K, V>>[] table;

    // 填充的elements个数
    private int size;


    public ChainedHashTable() {
        // this() 调用其他构造函数
        this(997);  // A prime number of buckets
    }


    @SuppressWarnings("unchecked")
    public ChainedHashTable(int buckets) {
        // Create table of empty buckets
        table = new SinglyLinkedList[buckets];  // new 长度为buckets，类型为SinglyLinkedList的array
        for (int i = 0; i < table.length; ++i) {
            table[i] = new SinglyLinkedList<KeyValuePair<K, V>>();
        }

        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }


    // given key, 取出bucket（即为SSL）
    private SinglyLinkedList<KeyValuePair<K, V>> getBucket(K key) {
        // Division method: map hash code to bucket
        return table[Math.abs(key.hashCode()) % table.length];
    }


    // given key, 得到最后的value
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


    public void insert(K key, V value) throws
            IllegalArgumentException,
            DuplicateKeyException {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        if (contains(key)) {
            throw new DuplicateKeyException();
        }

        getBucket(key).insertHead(new KeyValuePair<K, V>(key, value));
        ++size;
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
            return bucket.removeHead().getValue(); // removeHead：执行remove操作,又返回该element的data
        }

        // Scan rest of bucket
        SinglyLinkedList<KeyValuePair<K, V>>.Element prev = elem;
        elem = elem.getNext();
        while (elem != null) {
            if (key.equals(elem.getData().getKey())) {
                --size;
                return bucket.removeAfter(prev).getValue(); // removeAfter：执行remove操作,又返回被remove的element的data
            }
            prev = elem;
            elem = elem.getNext();
        }

        throw new NoSuchElementException();
    }


    // 内部类
    private class KeysIterator implements Iterator<K> {
        private int remaining;  // Number of keys remaining to iterate
        private int bucket;    // Position in bucket we're iterating
        private SinglyLinkedList<KeyValuePair<K, V>>.Element elem;
        // Bucket we're iterating

        public KeysIterator() {
            remaining = ChainedHashTable.this.size;
            bucket = 0;
            elem = ChainedHashTable.this.table[bucket].getHead();
        }

        public boolean hasNext() {
            return remaining > 0;
        }

        public K next() {
            if (hasNext()) {
                // If we've hit end of bucket, move to next non-empty bucket
                while (elem == null) {
                    elem = ChainedHashTable.this.table[++bucket].getHead();
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

    public Iterable<K> keys() {    // 使用内部类

        return new Iterable<K>() { // Iterable是内置接口，匿名内部类
            public Iterator<K> iterator() {
                return new KeysIterator(); // 前面定义的内置类
            }
        }; // return Iterable 的一个object

    }  // 使用：ChainedHashTable_Obj.keys().iterator() --> 得到一个KeysIterator的Obj
}
