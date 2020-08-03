// HashSet.java
package L5_HashTables;

import exceptions.DuplicateElementException;
import exceptions.DuplicateKeyException;

import java.util.Iterator;
import java.util.NoSuchElementException;

// HashSet中, key = value, 类型也相同
public class HashSet<E> implements Iterable<E> {
    private ChainedHashTable<E, E> table;

    public HashSet() {
        this(997);  // A prime number of buckets
    }

    public HashSet(int buckets) {
        table = new ChainedHashTable<E, E>(buckets);
    }

    public int getSize() {
        return table.getSize();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public void insert(E data) throws
            IllegalArgumentException,
            DuplicateElementException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        // Insert data into the table
        try {
            table.insert(data, data);
        } catch (DuplicateKeyException ex) {    // 这里没有try catch也可以，因为ChainedHashTable.insert()，已经考虑duplicate
            throw new DuplicateElementException();
        }
    }

    public E remove(E data) throws
            IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        return table.remove(data);
    }

    public boolean isMember(E data) throws
            IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        return table.contains(data);
    }

    public E getMember(E data) throws
            IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        return table.lookup(data);  // 返回最后的value，即data本身，若没有则返回 new NoSuchElementException()
    }


    public HashSet<E> union(HashSet<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        HashSet<E> result = new HashSet<E>(getSize() + other.getSize()); // 最大的可能的array长度

        // Add all items from this
        for (E data : this) {   // HashSet.iterator中定义的, 遍历每个element，返回的是element的data的key (ChainedHashTable.KeysIterator())
            try {
                result.insert(data);
            } catch (DuplicateElementException ex) {
                // Ignore duplicate failures
            }
        }

        // Add all items from other
        for (E data : other) {
            try {
                result.insert(data);
            } catch (DuplicateElementException ex) {
                // Ignore duplicate failures
            }
        }

        return result;
    }

    public HashSet<E> intersection(HashSet<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        HashSet<E> result = new HashSet<E>(getSize() + other.getSize());

        // Add elements from this that are in other
        for (E data : this) {
            if (other.isMember(data)) {
                try {
                    result.insert(data);
                } catch (DuplicateElementException ex) {
                    // Ignore duplicate failures
                }
            }
        }

        return result;
    }

    public HashSet<E> difference(HashSet<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        HashSet<E> result = new HashSet<E>(getSize() + other.getSize());

        // Add elements from this that are not in other
        for (E data : this) {
            if (!other.isMember(data)) {
                try {
                    result.insert(data);
                } catch (DuplicateElementException ex) {
                    // Ignore duplicate failures
                }
            }
        }

        return result;
    }

    public boolean isSubset(HashSet<E> other) throws // if this是other的subset
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        // If this has more elements than other, this can't be a subset
        if (getSize() > other.getSize()) {
            return false;
        }

        // If any member in this is not in other, this is not a subset
        for (E data : this) {
            if (!other.isMember(data)) {
                return false;
            }
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof HashSet))
            return false;

        HashSet<E> other = (HashSet<E>) o;

        // If sets equal size and one is subset of other, then they're equal
        return getSize() == other.getSize()
                && isSubset(other);
    }

    public Iterator<E> iterator() {
        return table.keys().iterator(); // 得到一个ChainedHashTable.KeysIterator的Obj
    }
}
