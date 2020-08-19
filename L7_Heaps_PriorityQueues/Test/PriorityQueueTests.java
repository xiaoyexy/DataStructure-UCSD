// PriorityQueueTests.java
package L7_Heaps_PriorityQueues.Test;

import L7_Heaps_PriorityQueues.PriorityQueue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.testng.Assert.*;

public class PriorityQueueTests {
    private PriorityQueue<String> priorityQueue;
    private PriorityQueue<String> bottomHeavyPriorityQueue;

    @BeforeMethod
    public void beforeMethod() {
        priorityQueue = new PriorityQueue<String>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        bottomHeavyPriorityQueue = new PriorityQueue<String>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                int result = s1.compareTo(s2);
                return result == 0 ? 0 : result < 0 ? 1 : -1;
            }
        });
    }

    @Test
    public void getSize_EmptyPriorityQueue() {
        assertEquals(priorityQueue.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyPriorityQueue() {
        priorityQueue.insert("foo");

        assertEquals(priorityQueue.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyPriorityQueue() {
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyPriorityQueue() {
        priorityQueue.insert("foo");

        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void insert_Once() {
        priorityQueue.insert("foo");

        assertFalse(priorityQueue.isEmpty());
        assertEquals(priorityQueue.getSize(), 1);
    }

    @Test
    public void insert_Twice() {
        priorityQueue.insert("foo");
        priorityQueue.insert("bar");

        assertFalse(priorityQueue.isEmpty());
        assertEquals(priorityQueue.getSize(), 2);
    }

    @Test
    public void insert_Thrice() {
        priorityQueue.insert("foo");
        priorityQueue.insert("bar");
        priorityQueue.insert("baz");

        assertFalse(priorityQueue.isEmpty());
        assertEquals(priorityQueue.getSize(), 3);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void extract_EmptyPriorityQueue() {
        priorityQueue.extract();
    }

    @Test
    public void extract_OneElementPriorityQueue() {
        priorityQueue.insert("foo");

        String extracted = priorityQueue.extract();
        assertEquals(extracted, "foo");
        assertEquals(priorityQueue.getSize(), 0);
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void extract_TwoElementPriorityQueue() {
        priorityQueue.insert("foo");
        priorityQueue.insert("bar");

        String extracted = priorityQueue.extract();
        assertEquals(extracted, "foo");
        assertEquals(priorityQueue.getSize(), 1);
        assertFalse(priorityQueue.isEmpty());

        extracted = priorityQueue.extract();
        assertEquals(extracted, "bar");
        assertEquals(priorityQueue.getSize(), 0);
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void extract_ThreeElementPriorityQueue() {
        priorityQueue.insert("foo");
        priorityQueue.insert("bar");
        priorityQueue.insert("baz");

        String extracted = priorityQueue.extract();
        assertEquals(extracted, "foo");
        assertEquals(priorityQueue.getSize(), 2);
        assertFalse(priorityQueue.isEmpty());

        extracted = priorityQueue.extract();
        assertEquals(extracted, "baz");
        assertEquals(priorityQueue.getSize(), 1);
        assertFalse(priorityQueue.isEmpty());

        extracted = priorityQueue.extract();
        assertEquals(extracted, "bar");
        assertEquals(priorityQueue.getSize(), 0);
        assertTrue(priorityQueue.isEmpty());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void peek_EmptyPriorityQueue() {
        priorityQueue.peek();
    }

    @Test
    public void peek_OneElementPriorityQueue() {
        priorityQueue.insert("foo");

        assertEquals(priorityQueue.peek(), "foo");
        assertEquals(priorityQueue.getSize(), 1);
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void peek_twoElementPriorityQueue() {
        priorityQueue.insert("foo");
        priorityQueue.insert("bar");

        assertEquals(priorityQueue.peek(), "foo");
        assertEquals(priorityQueue.getSize(), 2);
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void peek_threeElementPriorityQueue() {
        priorityQueue.insert("foo");
        priorityQueue.insert("bar");
        priorityQueue.insert("qux");

        assertEquals(priorityQueue.peek(), "qux");
        assertEquals(priorityQueue.getSize(), 3);
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void extract_OneElementBottomHeavyPriorityQueue() {
        bottomHeavyPriorityQueue.insert("foo");

        String extracted = bottomHeavyPriorityQueue.extract();
        assertEquals(extracted, "foo");
        assertEquals(bottomHeavyPriorityQueue.getSize(), 0);
        assertTrue(bottomHeavyPriorityQueue.isEmpty());
    }

    @Test
    public void extract_TwoElementBottomHeavyPriorityQueue() {
        bottomHeavyPriorityQueue.insert("foo");
        bottomHeavyPriorityQueue.insert("bar");

        String extracted = bottomHeavyPriorityQueue.extract();
        assertEquals(extracted, "bar");
        assertEquals(bottomHeavyPriorityQueue.getSize(), 1);
        assertFalse(bottomHeavyPriorityQueue.isEmpty());

        extracted = bottomHeavyPriorityQueue.extract();
        assertEquals(extracted, "foo");
        assertEquals(bottomHeavyPriorityQueue.getSize(), 0);
        assertTrue(bottomHeavyPriorityQueue.isEmpty());
    }

    @Test
    public void extract_ThreeElementBottomHeavyPriorityQueue() {
        bottomHeavyPriorityQueue.insert("foo");
        bottomHeavyPriorityQueue.insert("bar");
        bottomHeavyPriorityQueue.insert("baz");

        String extracted = bottomHeavyPriorityQueue.extract();
        assertEquals(extracted, "bar");
        assertEquals(bottomHeavyPriorityQueue.getSize(), 2);
        assertFalse(bottomHeavyPriorityQueue.isEmpty());

        extracted = bottomHeavyPriorityQueue.extract();
        assertEquals(extracted, "baz");
        assertEquals(bottomHeavyPriorityQueue.getSize(), 1);
        assertFalse(bottomHeavyPriorityQueue.isEmpty());

        extracted = bottomHeavyPriorityQueue.extract();
        assertEquals(extracted, "foo");
        assertEquals(bottomHeavyPriorityQueue.getSize(), 0);
        assertTrue(bottomHeavyPriorityQueue.isEmpty());
    }
}
