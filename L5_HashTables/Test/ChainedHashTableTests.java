// ChainedHashTableTests.java
package L5_HashTables.Test;

import L5_HashTables.*;
import L4_Stacks_Queues_Sets.*;

import exceptions.DuplicateElementException;
import exceptions.DuplicateKeyException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ChainedHashTableTests {
    private static final int NUM_BUCKETS = 3;

    private ChainedHashTable<Integer, String> table;

    @BeforeMethod
    public void beforeMethod() {
        table = new ChainedHashTable<Integer, String>(NUM_BUCKETS);
    }

    @Test
    public void getSize_EmptyTable() {
        assertEquals(table.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyTable() {
        assertTrue(table.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertFalse(table.isEmpty());
    }

    @Test
    public void insert_Once() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.getSize(), 1);
        assertFalse(table.isEmpty());
    }

    @Test
    public void insert_Twice() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.getSize(), 2);
        assertFalse(table.isEmpty());
    }

    @Test
    public void insert_Thrice() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
            table.insert(3, "baz");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.getSize(), 3);
        assertFalse(table.isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insert_Null() {
        try {
            table.insert(null, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }
    }

    @Test(expectedExceptions = DuplicateKeyException.class)
    public void insert_DuplicateKey() throws DuplicateKeyException {
        table.insert(1, "foo");
        table.insert(1, "bar");
    }

    @Test
    public void insert_DuplicateValue() {
        try {
            table.insert(1, "foo");
            table.insert(2, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.getSize(), 2);
        assertFalse(table.isEmpty());
    }

    @Test
    public void contains_Nothing() {
        assertFalse(table.contains(null));
        assertFalse(table.contains(1));
    }

    @Test
    public void contains_OneItem() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertTrue(table.contains(1));
        assertFalse(table.contains(2));
    }

    @Test
    public void contains_TwoItems() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertTrue(table.contains(1));
        assertTrue(table.contains(2));
        assertFalse(table.contains(3));
    }

    @Test
    public void contains_ThreeItems() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
            table.insert(3, "baz");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertTrue(table.contains(1));
        assertTrue(table.contains(2));
        assertTrue(table.contains(3));
        assertFalse(table.contains(4));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void lookup_Null_EmptyTable() {
        table.lookup(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void lookup_NoSuchElement_EmptyTable() {
        table.lookup(1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void lookup_Null_NonEmptyTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        table.lookup(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void lookup_NoSuchElement_NonEmptyTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        table.lookup(2);
    }

    @Test
    public void lookup_OneItemTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.lookup(1), "foo");
    }

    @Test
    public void lookup_TwoItemTable() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.lookup(1), "foo");
        assertEquals(table.lookup(2), "bar");
    }

    @Test
    public void lookup_ThreeItemTable() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
            table.insert(3, "baz");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.lookup(1), "foo");
        assertEquals(table.lookup(2), "bar");
        assertEquals(table.lookup(3), "baz");
    }

    @Test
    public void lookup_ManyItemTable() {
        try {
            for (int i = 0; i < NUM_BUCKETS * 10; ++i) {
                table.insert(i, "foo" + i);
            }
        } catch (DuplicateKeyException ex) {
            fail();
        }

        for (int i = 0; i < NUM_BUCKETS * 10; ++i) {
            assertEquals(table.lookup(i), "foo" + i);
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void remove_Null_EmptyTable() {
        table.lookup(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void remove_NoSuchElement_EmptyTable() {
        table.lookup(1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void remove_Null_NonEmptyTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        table.lookup(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void remove_NoSuchElement_NonEmptyTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        table.lookup(2);
    }

    @Test
    public void remove_OneItemTable() {
        try {
            table.insert(1, "foo");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.remove(1), "foo");
        assertTrue(table.isEmpty());
        assertEquals(table.getSize(), 0);
    }

    @Test
    public void remove_TwoItemTable() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.remove(2), "bar");
        assertFalse(table.isEmpty());
        assertEquals(table.getSize(), 1);
    }

    @Test
    public void remove_ThreeItemTable() {
        try {
            table.insert(1, "foo");
            table.insert(2, "bar");
            table.insert(3, "baz");
        } catch (DuplicateKeyException ex) {
            fail();
        }

        assertEquals(table.remove(2), "bar");
        assertFalse(table.isEmpty());
        assertEquals(table.getSize(), 2);
    }

    @Test
    public void remove_ManyItemTable() {
        try {
            for (int i = 0; i < NUM_BUCKETS * 10; ++i) {
                table.insert(i, "foo" + i);
            }
        } catch (DuplicateKeyException ex) {
            fail();
        }

        for (int i = 0; i < NUM_BUCKETS * 10; ++i) {
            assertEquals(table.remove(i), "foo" + i);
        }
        assertTrue(table.isEmpty());
        assertEquals(table.getSize(), 0);
    }

    @Test
    public void keysIterator() {
        try {
            for (int i = 0; i < NUM_BUCKETS * 10; ++i) {
                table.insert(i, "foo" + i);
            }
        } catch (DuplicateKeyException ex) {
            fail();
        }

        // Create set containing all keys in the hash table
        Set<Integer> expectedKeys = new Set<Integer>();
        try {
            for (int i = 0; i < NUM_BUCKETS * 10; ++i) {
                expectedKeys.insert(i);
            }
        } catch (DuplicateElementException ex) {
            fail();
        }

        // Iterate keys, verifying value and removing from the set
        for (Integer key : table.keys()) {
            assertEquals(table.lookup(key), "foo" + key);
            expectedKeys.remove(key);
        }

        // Verify set empty due to all keys having been iterated
        assertTrue(expectedKeys.isEmpty());
    }
}
