// CircularDoublyLinkedListTests.java
package L2_LinkedList.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import L2_LinkedList.CircularDoublyLinkedList;

import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class CirularDoublyLinkedListTests {
    private CircularDoublyLinkedList<String> list;

    @BeforeMethod
    public void beforeMethod() {
        list = new CircularDoublyLinkedList<String>();
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
        CircularDoublyLinkedList<String>.Element inserted =
                list.insertHead(data1);
        CircularDoublyLinkedList<String>.Element head = list.getHead();

        assertNotNull(head);
        assertSame(head, inserted);
        assertEquals(head.getData(), data1);
    }

    @Test
    public void insertHead_EmptyList() {
        String data1 = "data1";
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);

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
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted2);
        assertEquals(list.getHead().getData(), data2);
        assertSame(list.getHead().getNext(), inserted1);
        assertEquals(list.getHead().getNext().getData(), data1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insertAfter_NullElement() {
        list.insertAfter(null, "item");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void insertAfter_ElementNotInList() {
        CircularDoublyLinkedList<String> list2 =
                new CircularDoublyLinkedList<String>();
        CircularDoublyLinkedList<String>.Element list2Element =
                list2.insertHead("list2Item");

        list.insertAfter(list2Element, "item");
    }

    @Test
    public void insertBefore_Head() {
        String data1 = "data1";
        String data2 = "data2";
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertBefore(inserted1, data2);

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
    public void insertBefore_ElementAfterHead() {
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);
        CircularDoublyLinkedList<String>.Element inserted3 =
                list.insertBefore(inserted1, data3);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertNotNull(inserted3);
        assertEquals(list.getSize(), 3);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted2);
        assertEquals(list.getHead().getData(), data2);
        assertSame(list.getHead().getNext(), inserted3);
        assertEquals(list.getHead().getNext().getData(), data3);
        assertSame(list.getHead().getNext().getNext(), inserted1);
        assertEquals(list.getHead().getNext().getNext().getData(), data1);
    }

    @Test
    public void insertAfter_Tail() {
        String data1 = "data1";
        String data2 = "data2";
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertAfter(inserted1, data2);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted2);
        assertEquals(list.getHead().getData(), data2);
        assertSame(list.getHead().getPrevious(), inserted1);
        assertEquals(list.getHead().getPrevious().getData(), data1);
    }

    @Test
    public void insertAfter_ElementBeforeTail() {
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        CircularDoublyLinkedList<String>.Element inserted3 =
                list.insertAfter(inserted1, data3);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertNotNull(inserted3);
        assertEquals(list.getSize(), 3);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(list.getHead().getPrevious(), inserted2);
        assertEquals(list.getHead().getPrevious().getData(), data2);
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
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
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
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);

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
    public void remove_NullElement() {
        list.remove(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void remove_ElementNotInList() {
        list.insertHead("data1");
        CircularDoublyLinkedList<String> list2 =
                new CircularDoublyLinkedList<String>();
        CircularDoublyLinkedList<String>.Element list2Element =
                list2.insertHead("list2Item");

        list.remove(list2Element);
    }

    @Test
    public void remove_LastItemFromList() {
        String data1 = "data1";
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        String removedData = list.remove(list.getHead());

        assertNotNull(inserted1);
        assertEquals(list.getSize(), 0);
        assertTrue(list.isEmpty());
        assertSame(removedData, data1);
    }

    @Test
    public void remove_HeadFromMultiItemList() {
        String data1 = "data1";
        String data2 = "data2";
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        String removedData = list.remove(list.getHead());

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 1);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted2);
        assertEquals(list.getHead().getData(), data2);
        assertSame(removedData, data1);
    }

    @Test
    public void remove_TailFromMultiItemList() {
        String data1 = "data1";
        String data2 = "data2";
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        String removedData = list.remove(list.getHead().getPrevious());

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertEquals(list.getSize(), 1);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(removedData, data2);
    }

    @Test
    public void remove_MiddleItemFromMultiItemList() {
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        CircularDoublyLinkedList<String>.Element inserted3 =
                list.insertHead(data3);
        CircularDoublyLinkedList<String>.Element inserted2 =
                list.insertHead(data2);
        CircularDoublyLinkedList<String>.Element inserted1 =
                list.insertHead(data1);
        String removedData = list.remove(inserted2);

        assertNotNull(inserted1);
        assertNotNull(inserted2);
        assertNotNull(inserted3);
        assertEquals(list.getSize(), 2);
        assertFalse(list.isEmpty());
        assertSame(list.getHead(), inserted1);
        assertEquals(list.getHead().getData(), data1);
        assertSame(list.getHead().getPrevious(), inserted3);
        assertEquals(list.getHead().getPrevious().getData(), data3);
        assertSame(removedData, data2);
    }
}