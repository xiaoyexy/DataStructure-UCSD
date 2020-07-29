// SetTests.java
package L4_Stacks_Queues_Sets.Test;

import exceptions.DuplicateElementException;
import L4_Stacks_Queues_Sets.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;


public class SetTests {
    private Set<String> set;

    @BeforeMethod
    public void beforeMethod() {
        set = new Set<String>();
    }

    @Test
    public void getSize_EmptySet() {
        assertEquals(set.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptySet() {
        try {
            set.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertEquals(set.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptySet() {
        assertTrue(set.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptySet() {
        try {
            set.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set.isEmpty());
    }

    @Test
    public void insert_Once() {
        try {
            set.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertEquals(set.getSize(), 1);
        assertFalse(set.isEmpty());
    }

    @Test
    public void insert_Twice() {
        try {
            set.insert("foo");
            set.insert("bar");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertEquals(set.getSize(), 2);
        assertFalse(set.isEmpty());
    }

    @Test
    public void insert_Thrice() {
        try {
            set.insert("foo");
            set.insert("bar");
            set.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertEquals(set.getSize(), 3);
        assertFalse(set.isEmpty());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void remove_EmptySet() {
        set.remove("foo");
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void remove_NoSuchElementFromNonEmptySet() {
        try {
            set.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        set.remove("bar");
    }

    @Test
    public void remove_OneElementSet() {
        try {
            set.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        String removed = set.remove("foo");
        assertEquals(removed, "foo");
        assertEquals(set.getSize(), 0);
        assertTrue(set.isEmpty());
    }

    @Test
    public void remove_TwoElementSet() {
        try {
            set.insert("foo");
            set.insert("bar");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        String removed = set.remove("bar");
        assertEquals(removed, "bar");
        assertEquals(set.getSize(), 1);
        assertFalse(set.isEmpty());

        removed = set.remove("foo");
        assertEquals(removed, "foo");
        assertEquals(set.getSize(), 0);
        assertTrue(set.isEmpty());
    }

    @Test
    public void remove_ThreeElementSet() {
        try {
            set.insert("foo");
            set.insert("bar");
            set.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        String removed = set.remove("baz");
        assertEquals(removed, "baz");
        assertEquals(set.getSize(), 2);
        assertFalse(set.isEmpty());

        removed = set.remove("bar");
        assertEquals(removed, "bar");
        assertEquals(set.getSize(), 1);
        assertFalse(set.isEmpty());

        removed = set.remove("foo");
        assertEquals(removed, "foo");
        assertEquals(set.getSize(), 0);
        assertTrue(set.isEmpty());
    }

    @Test
    public void isMember_EmptySet() {
        assertFalse(set.isMember("foo"));
    }

    @Test
    public void isMember_OneElementSet() {
        try {
            set.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set.isMember("foo"));
        assertFalse(set.isMember("bar"));
        assertFalse(set.isMember("baz"));
    }

    @Test
    public void isMember_TwoElementSet() {
        try {
            set.insert("foo");
            set.insert("bar");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set.isMember("foo"));
        assertTrue(set.isMember("bar"));
        assertFalse(set.isMember("baz"));
    }

    @Test
    public void isMember_ThreeElementSet() {
        try {
            set.insert("foo");
            set.insert("bar");
            set.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set.isMember("foo"));
        assertTrue(set.isMember("bar"));
        assertTrue(set.isMember("baz"));
    }

    @Test
    public void union_EmptySets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        Set<String> set3 = set1.union(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
    }

    @Test
    public void union_EmptySetWithNonEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.union(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 3);
        assertTrue(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertTrue(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
    }

    @Test
    public void union_NonEmptySetWithEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.union(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 3);
        assertTrue(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertTrue(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
    }

    @Test
    public void union_NonIntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.union(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 4);
        assertTrue(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertTrue(set3.isMember("baz"));
        assertTrue(set3.isMember("qux"));
        assertFalse(set3.isMember("quux"));
    }

    @Test
    public void union_IntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.union(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 4);
        assertTrue(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertTrue(set3.isMember("baz"));
        assertTrue(set3.isMember("qux"));
        assertFalse(set3.isMember("quux"));
    }

    @Test
    public void intersection_EmptySets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        Set<String> set3 = set1.intersection(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
    }

    @Test
    public void intersection_EmptySetWithNonEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.intersection(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
        assertFalse(set3.isMember("foo"));
        assertFalse(set3.isMember("bar"));
        assertFalse(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
    }

    @Test
    public void intersection_NonEmptySetWithEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.intersection(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
        assertFalse(set3.isMember("foo"));
        assertFalse(set3.isMember("bar"));
        assertFalse(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
    }

    @Test
    public void intersection_NonIntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.intersection(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
        assertFalse(set3.isMember("foo"));
        assertFalse(set3.isMember("bar"));
        assertFalse(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
        assertFalse(set3.isMember("quux"));
    }

    @Test
    public void intersection_IntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.intersection(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 2);
        assertFalse(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertTrue(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
        assertFalse(set3.isMember("quux"));
    }

    @Test
    public void difference_EmptySets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        Set<String> set3 = set1.difference(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
    }

    @Test
    public void difference_EmptySetWithNonEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.difference(set2);

        assertTrue(set3.isEmpty());
        assertEquals(set3.getSize(), 0);
        assertFalse(set3.isMember("foo"));
        assertFalse(set3.isMember("bar"));
        assertFalse(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
    }

    @Test
    public void difference_NonEmptySetWithEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.difference(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 3);
        assertTrue(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertTrue(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
    }

    @Test
    public void difference_NonIntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.difference(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 2);
        assertTrue(set3.isMember("foo"));
        assertTrue(set3.isMember("bar"));
        assertFalse(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
        assertFalse(set3.isMember("quux"));
    }

    @Test
    public void difference_IntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        Set<String> set3 = set1.difference(set2);

        assertFalse(set3.isEmpty());
        assertEquals(set3.getSize(), 1);
        assertTrue(set3.isMember("foo"));
        assertFalse(set3.isMember("bar"));
        assertFalse(set3.isMember("baz"));
        assertFalse(set3.isMember("qux"));
        assertFalse(set3.isMember("quux"));
    }

    @Test
    public void isSubset_EmptySets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        assertTrue(set1.isSubset(set2));
    }

    @Test
    public void isSubset_EmptySetWithNonEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set1.isSubset(set2));
    }

    @Test
    public void isSubset_NonEmptySetWithEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.isSubset(set2));
    }

    @Test
    public void isSubset_NonIntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.isSubset(set2));
    }

    @Test
    public void isSubset_IntersectingNonSubsetSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.isSubset(set2));
    }

    @Test
    public void isSubset_EqualSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("baz");
            set2.insert("bar");
            set2.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set1.isSubset(set2));
    }

    @Test
    public void isSubset_TrueSubsetSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("baz");
            set2.insert("bar");
            set2.insert("foo");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set1.isSubset(set2));
    }

    @Test
    public void equals_EmptySets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        assertTrue(set1.equals(set2));
    }

    @Test
    public void equals_EmptySetWithNonEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.equals(set2));
    }

    @Test
    public void equals_NonEmptySetWithEmptySet() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.equals(set2));
    }

    @Test
    public void equals_NonIntersectingSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.equals(set2));
    }

    @Test
    public void equals_IntersectingNonSubsetSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("bar");
            set2.insert("baz");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.equals(set2));
    }

    @Test
    public void equals_EqualSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("baz");
            set2.insert("bar");
            set2.insert("foo");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertTrue(set1.equals(set2));
    }

    @Test
    public void equals_TrueSubsetSets() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("baz");
            set2.insert("bar");
            set2.insert("foo");
            set2.insert("qux");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        assertFalse(set1.equals(set2));
    }

    @Test
    public void iterator_Manual() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        // Verify all expected elements seen when iterating through set1
        Iterator<String> iterator = set1.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
            assertTrue(set2.isMember(data));
            set2.remove(data);
        }
        assertTrue(set2.isEmpty());
    }

    @Test
    public void iterator_ForEach() {
        Set<String> set1 = new Set<String>();
        Set<String> set2 = new Set<String>();

        try {
            set1.insert("foo");
            set1.insert("bar");
            set1.insert("baz");
            set2.insert("foo");
            set2.insert("bar");
            set2.insert("baz");
        } catch (DuplicateElementException ex) {
            fail("Failed to insert", ex);
        }

        // Verify all expected elements seen when iterating through set1
        for (String data : set1) {
            assertTrue(set2.isMember(data));
            set2.remove(data);
        }
        assertTrue(set2.isEmpty());
    }
}
