// SinglyLinkedListTests.java
package L2_LinkedList.Test;

import L2_LinkedList.SinglyLinkedList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SinglyLinkedListTests {
    private SinglyLinkedList<String> list;

    @BeforeMethod
    public void beforeMethod() {
        list = new SinglyLinkedList<String>();
    }

    @Test
    public void getSize_EmptyList() {
        assertEquals(list.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyList() {
        list.insertHead("data1");

        assertEquals(list.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyList() {
        list.insertHead("data1");

        assertFalse(list.isEmpty());
    }

    @Test
    public void getHead_EmptyList() {
        assertNull(list.getHead());
    }

    @Test
    public void getHead_NonEmptyList() {
        String data1 = "data1";
        SinglyLinkedList<String>.Element inserted = list.insertHead(data1);
        SinglyLinkedList<String>.Element head = list.getHead();

        assertNotNull(head);
        assertSame(head, inserted);
        assertEquals(head.getData(), data1);
    }

    @Test
    public void getTail_EmptyList() {
        assertNull(list.getTail());
    }

    @Test
    public void getTail_NonEmptyList() {
        String data1 = "data1";
        SinglyLinkedList<String>.Element inserted = list.insertTail(data1);
        SinglyLinkedList<String>.Element tail = list.getTail();

        assertNotNull(tail);
        assertSame(tail, inserted);
        assertEquals(tail.getData(), data1);
    }

    @Test
    public void insertHead_EmptyList() {
        String data1 = "data1";
        SinglyLinkedList<String>.Element inserted1 = list.insertHead(data1);

        assertNotNull(inserted1);
        assertEquals(list.getSize(), 1);
        assertFalse(list.isEmpty());
        assertNotNull(list.getHead());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
    }

    @Test
    public void insertHead_NonEmptyList() {
        String data1 = "data1";
        String data2 = "data2";
        SinglyLinkedList<String>.Element inserted1 = list.insertHead(data1);
        SinglyLinkedList<String>.Element inserted2 = list.insertHead(data2);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted2);
        assertEquals(list.getHead().getData(), data2);
        assertSame(list.getHead().getNext(), inserted1);
        assertEquals(list.getHead().getNext().getData(), data1);
    }

    @Test
    public void insertTail_EmptyList() {
        String data1 = "data1";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);

        assertNotNull(inserted1);
        assertEquals(list.getSize(), 1);
        assertFalse(list.isEmpty());
        assertNotNull(list.getTail());
        assertSame(list.getTail(), inserted1);
        assertEquals(list.getTail().getData(), data1);
    }

    @Test
    public void insertTail_NonEmptyList() {
        String data1 = "data1";
        String data2 = "data2";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        SinglyLinkedList<String>.Element inserted2 = list.insertTail(data2);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(list.getHead().getNext(), inserted2);
        assertEquals(list.getHead().getNext().getData(), data2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insertAfter_NullElement() {
        list.insertAfter(null, "item");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insertAfter_ElementNotInList() {
        SinglyLinkedList<String> list2 = new SinglyLinkedList<String>();
        SinglyLinkedList<String>.Element list2Element =
                list2.insertHead("list2Item");

        list.insertAfter(list2Element, "item");
    }

    @Test
    public void insertAfter_Tail() {
        String data1 = "data1";
        String data2 = "data2";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        SinglyLinkedList<String>.Element inserted2 =
                list.insertAfter(inserted1, data2);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(list.getTail(), inserted2);
        assertEquals(list.getTail().getData(), data2);
    }

    @Test
    public void insertAfter_ElementBeforeTail() {
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        SinglyLinkedList<String>.Element inserted2 = list.insertTail(data2);
        SinglyLinkedList<String>.Element inserted3 =
                list.insertAfter(inserted1, data3);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertNotNull(inserted3);
        assertEquals(list.getSize(), 3);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(list.getTail(), inserted2);
        assertEquals(list.getTail().getData(), data2);
        assertSame(list.getHead().getNext(), inserted3);
        assertEquals(list.getHead().getNext().getData(), data3);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void removeHead_EmptyList() {
        list.removeHead();
    }

    @Test
    public void removeHead_SingleElementList() {
        String data1 = "data1";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        String removedData = list.removeHead();

        assertNotNull(inserted1);
        assertEquals(list.getSize(), 0);
        assertTrue(list.isEmpty());
        assertEquals(removedData, data1);
    }

    @Test
    public void removeHead_MultiElementList() {
        String data1 = "data1";
        String data2 = "data2";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        SinglyLinkedList<String>.Element inserted2 = list.insertTail(data2);
        String removedData = list.removeHead();

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 1);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted2);
        assertEquals(list.getHead().getData(), data2);
        assertEquals(removedData, data1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeAfter_NullElement() {
        list.removeAfter(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeAfter_ElementNotInList() {
        list.insertTail("data1");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<String>();
        SinglyLinkedList<String>.Element list2Element =
                list2.insertHead("list2Item");

        list.removeAfter(list2Element);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeAfter_Tail() {
        list.insertTail("data1");
        list.removeAfter(list.getTail());
    }

    @Test
    public void removeAfter_ElementImmediatelyBeforeTail() {
        String data1 = "data1";
        String data2 = "data2";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        SinglyLinkedList<String>.Element inserted2 = list.insertTail(data2);
        String removedData = list.removeAfter(list.getHead());

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 1);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertSame(list.getTail(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(removedData, data2);
    }

    @Test
    public void removeAfter_ElementNotImmediatelyBeforeTail() {
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        SinglyLinkedList<String>.Element inserted1 = list.insertTail(data1);
        SinglyLinkedList<String>.Element inserted2 = list.insertTail(data2);
        SinglyLinkedList<String>.Element inserted3 = list.insertTail(data3);
        String removedData = list.removeAfter(list.getHead());

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertNotNull(inserted3);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertSame(list.getTail(), inserted3);
        assertEquals(list.getHead().getData(), data1);
        assertEquals(list.getTail().getData(), data3);
        assertSame(removedData, data2);
    }
}