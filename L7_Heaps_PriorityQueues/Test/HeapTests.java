// HeapTests.java
package L7_Heaps_PriorityQueues.Test;

import L7_Heaps_PriorityQueues.Heap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.testng.Assert.*;

public class HeapTests {
    private Heap<String> heap;
    private Heap<String> bottomHeavyHeap;

    @BeforeMethod
    public void beforeMethod() {
        heap = new Heap<String>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        bottomHeavyHeap = new Heap<String>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return -s1.compareTo(s2);
            }
        });
    }

    @Test
    public void getSize_EmptyHeap() {
        assertEquals(heap.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyHeap() {
        heap.insert("foo");

        assertEquals(heap.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyHeap() {
        assertTrue(heap.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyHeap() {
        heap.insert("foo");

        assertFalse(heap.isEmpty());
    }

    @Test
    public void insert_Once() {
        heap.insert("foo");

        assertFalse(heap.isEmpty());
        assertEquals(heap.getSize(), 1);
    }

    @Test
    public void insert_Twice() {
        heap.insert("foo");
        heap.insert("bar");

        assertFalse(heap.isEmpty());
        assertEquals(heap.getSize(), 2);
    }

    @Test
    public void insert_Thrice() {
        heap.insert("foo");
        heap.insert("bar");
        heap.insert("baz");

        assertFalse(heap.isEmpty());
        assertEquals(heap.getSize(), 3);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void extract_EmptyHeap() {
        heap.extract();
    }

    @Test
    public void extract_OneElementHeap() {
        heap.insert("foo");

        String extracted = heap.extract();
        assertEquals(extracted, "foo");
        assertEquals(heap.getSize(), 0);
        assertTrue(heap.isEmpty());
    }

    @Test
    public void extract_TwoElementHeap() {
        heap.insert("foo");
        heap.insert("bar");

        String extracted = heap.extract();
        assertEquals(extracted, "foo");
        assertEquals(heap.getSize(), 1);
        assertFalse(heap.isEmpty());

        extracted = heap.extract();
        assertEquals(extracted, "bar");
        assertEquals(heap.getSize(), 0);
        assertTrue(heap.isEmpty());
    }

    @Test
    public void extract_ThreeElementHeap() {
        heap.insert("foo");
        heap.insert("bar");
        heap.insert("baz");

        String extracted = heap.extract();
        assertEquals(extracted, "foo");
        assertEquals(heap.getSize(), 2);
        assertFalse(heap.isEmpty());

        extracted = heap.extract();
        assertEquals(extracted, "baz");
        assertEquals(heap.getSize(), 1);
        assertFalse(heap.isEmpty());

        extracted = heap.extract();
        assertEquals(extracted, "bar");
        assertEquals(heap.getSize(), 0);
        assertTrue(heap.isEmpty());
    }

    @Test
    public void extract_OneElementBottomHeavyHeap() {
        bottomHeavyHeap.insert("foo");

        String extracted = bottomHeavyHeap.extract();
        assertEquals(extracted, "foo");
        assertEquals(bottomHeavyHeap.getSize(), 0);
        assertTrue(bottomHeavyHeap.isEmpty());
    }

    @Test
    public void extract_TwoElementBottomHeavyHeap() {
        bottomHeavyHeap.insert("foo");
        bottomHeavyHeap.insert("bar");

        String extracted = bottomHeavyHeap.extract();
        assertEquals(extracted, "bar");
        assertEquals(bottomHeavyHeap.getSize(), 1);
        assertFalse(bottomHeavyHeap.isEmpty());

        extracted = bottomHeavyHeap.extract();
        assertEquals(extracted, "foo");
        assertEquals(bottomHeavyHeap.getSize(), 0);
        assertTrue(bottomHeavyHeap.isEmpty());
    }

    @Test
    public void extract_ThreeElementBottomHeavyHeap() {
        bottomHeavyHeap.insert("foo");
        bottomHeavyHeap.insert("bar");
        bottomHeavyHeap.insert("baz");

        String extracted = bottomHeavyHeap.extract();
        assertEquals(extracted, "bar");
        assertEquals(bottomHeavyHeap.getSize(), 2);
        assertFalse(bottomHeavyHeap.isEmpty());

        extracted = bottomHeavyHeap.extract();
        assertEquals(extracted, "baz");
        assertEquals(bottomHeavyHeap.getSize(), 1);
        assertFalse(bottomHeavyHeap.isEmpty());

        extracted = bottomHeavyHeap.extract();
        assertEquals(extracted, "foo");
        assertEquals(bottomHeavyHeap.getSize(), 0);
        assertTrue(bottomHeavyHeap.isEmpty());
    }
}
