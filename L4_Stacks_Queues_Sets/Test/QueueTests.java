// QueueTests.java
package L4_Stacks_Queues_Sets.Test;

import L4_Stacks_Queues_Sets.*;;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.*;


public class QueueTests {
    private Queue<String> queue;

    @BeforeMethod
    public void beforeMethod() {
        queue = new Queue<String>();
    }

    @Test
    public void getSize_EmptyQueue() {
        assertEquals(queue.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyQueue() {
        queue.enqueue("foo");

        assertEquals(queue.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyQueue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyQueue() {
        queue.enqueue("foo");

        assertFalse(queue.isEmpty());
    }

    @Test
    public void enqueue_Once() {
        queue.enqueue("foo");

        assertEquals(queue.getSize(), 1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void enqueue_Twice() {
        queue.enqueue("foo");
        queue.enqueue("bar");

        assertEquals(queue.getSize(), 2);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void enqueue_Thrice() {
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");

        assertEquals(queue.getSize(), 3);
        assertFalse(queue.isEmpty());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void dequeue_EmptyQueue() {
        queue.dequeue();
    }

    @Test
    public void dequeue_OneElementQueue() {
        queue.enqueue("foo");

        String dequeued = queue.dequeue();

        assertEquals(dequeued, "foo");
        assertEquals(queue.getSize(), 0);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void dequeue_TwoElementQueue() {
        queue.enqueue("foo");
        queue.enqueue("bar");

        String dequeued = queue.dequeue();
        assertEquals(dequeued, "foo");
        assertEquals(queue.getSize(), 1);
        assertFalse(queue.isEmpty());

        dequeued = queue.dequeue();
        assertEquals(dequeued, "bar");
        assertEquals(queue.getSize(), 0);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void dequeue_ThreeElementQueue() {
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");

        String dequeued = queue.dequeue();
        assertEquals(dequeued, "foo");
        assertEquals(queue.getSize(), 2);
        assertFalse(queue.isEmpty());

        dequeued = queue.dequeue();
        assertEquals(dequeued, "bar");
        assertEquals(queue.getSize(), 1);
        assertFalse(queue.isEmpty());

        dequeued = queue.dequeue();
        assertEquals(dequeued, "baz");
        assertEquals(queue.getSize(), 0);
        assertTrue(queue.isEmpty());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void peek_EmptyQueue() {
        queue.peek();
    }

    @Test
    public void peek_OneElementQueue() {
        queue.enqueue("foo");

        assertEquals(queue.peek(), "foo");
        assertEquals(queue.getSize(), 1);
        assertFalse(queue.isEmpty());

        assertEquals(queue.peek(), "foo");
        assertEquals(queue.getSize(), 1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void peek_TwoElementQueue() {
        queue.enqueue("foo");
        queue.enqueue("bar");

        assertEquals(queue.peek(), "foo");
        assertEquals(queue.getSize(), 2);
        assertFalse(queue.isEmpty());

        assertEquals(queue.peek(), "foo");
        assertEquals(queue.getSize(), 2);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void peek_ThreeElementQueue() {
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");

        assertEquals(queue.peek(), "foo");
        assertEquals(queue.getSize(), 3);
        assertFalse(queue.isEmpty());

        assertEquals(queue.peek(), "foo");
        assertEquals(queue.getSize(), 3);
        assertFalse(queue.isEmpty());
    }
}
