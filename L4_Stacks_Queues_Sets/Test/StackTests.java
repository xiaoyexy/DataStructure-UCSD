// StackTests.java
package L4_Stacks_Queues_Sets.Test;

import L4_Stacks_Queues_Sets.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class StackTests {
    private Stack<String> stack;

    @BeforeMethod
    public void beforeMethod() {
        stack = new Stack<String>();
    }

    @Test
    public void getSize_EmptyStack() {
        assertEquals(stack.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyStack() {
        stack.push("foo");

        assertEquals(stack.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyStack() {
        stack.push("foo");

        assertFalse(stack.isEmpty());
    }

    @Test
    public void push_Once() {
        stack.push("foo");

        assertEquals(stack.getSize(), 1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void push_Twice() {
        stack.push("foo");
        stack.push("bar");

        assertEquals(stack.getSize(), 2);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void push_Thrice() {
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");

        assertEquals(stack.getSize(), 3);
        assertFalse(stack.isEmpty());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void pop_EmptyStack() {
        stack.pop();
    }

    @Test
    public void pop_OneElementStack() {
        stack.push("foo");

        String popped = stack.pop();

        assertEquals(popped, "foo");
        assertEquals(stack.getSize(), 0);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pop_TwoElementStack() {
        stack.push("foo");
        stack.push("bar");

        String popped = stack.pop();
        assertEquals(popped, "bar");
        assertEquals(stack.getSize(), 1);
        assertFalse(stack.isEmpty());

        popped = stack.pop();
        assertEquals(popped, "foo");
        assertEquals(stack.getSize(), 0);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pop_ThreeElementStack() {
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");

        String popped = stack.pop();
        assertEquals(popped, "baz");
        assertEquals(stack.getSize(), 2);
        assertFalse(stack.isEmpty());

        popped = stack.pop();
        assertEquals(popped, "bar");
        assertEquals(stack.getSize(), 1);
        assertFalse(stack.isEmpty());

        popped = stack.pop();
        assertEquals(popped, "foo");
        assertEquals(stack.getSize(), 0);
        assertTrue(stack.isEmpty());
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void peek_EmptyStack() {
        stack.peek();
    }

    @Test
    public void peek_OneElementStack() {
        stack.push("foo");

        assertEquals(stack.peek(), "foo");
        assertEquals(stack.getSize(), 1);
        assertFalse(stack.isEmpty());

        assertEquals(stack.peek(), "foo");
        assertEquals(stack.getSize(), 1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void peek_TwoElementStack() {
        stack.push("foo");
        stack.push("bar");

        assertEquals(stack.peek(), "bar");
        assertEquals(stack.getSize(), 2);
        assertFalse(stack.isEmpty());

        assertEquals(stack.peek(), "bar");
        assertEquals(stack.getSize(), 2);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void peek_ThreeElementStack() {
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");

        assertEquals(stack.peek(), "baz");
        assertEquals(stack.getSize(), 3);
        assertFalse(stack.isEmpty());

        assertEquals(stack.peek(), "baz");
        assertEquals(stack.getSize(), 3);
        assertFalse(stack.isEmpty());
    }
}
