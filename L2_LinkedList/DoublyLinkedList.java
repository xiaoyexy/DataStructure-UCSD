// DoublyLinkedList.java
package L2_LinkedList;
import java.util.NoSuchElementException;


public class DoublyLinkedList<E> {
    // An element in a linked list
    public class Element {
        private E data;
        private Element next;
        private Element previous;

        // Only allow DoublyLinkedList to construct Elements
        private Element(E data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public E getData() {
            return data;
        }

        public Element getNext() {
            return next;
        }

        public Element getPrevious() {
            return previous;
        }

        public DoublyLinkedList getOwner() {
            return DoublyLinkedList.this;
        }
    }

    private Element head;
    private Element tail;
    private int size;

    public Element getHead() {
        return head;
    }

    public Element getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Element insertHead(E data) {
        Element newElement = new Element(data);

        if (isEmpty()) {
            // Insert into empty list
            head = newElement;
            tail = newElement;
        }
        else {
            // Insert into non-empty list
            newElement.next = head;
            head.previous = newElement;
            head = newElement;
        }

        ++size;

        return newElement;
    }

    public Element insertTail(E data) {
        Element newElement = new Element(data);

        if (isEmpty()) {
            // Insert into empty list
            head = newElement;
            tail = newElement;
        }
        else {
            // Insert into non-empty list
            newElement.previous = tail;
            tail.next = newElement;
            tail = newElement;
        }

        ++size;

        return newElement;
    }

    public Element insertBefore(Element element, E data)  // 插在此element 之前
            throws IllegalArgumentException {
        // Check pre-conditions
        if (element == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must not be null");
        }
        if (element.getOwner() != this) {
            throw new IllegalArgumentException(
                    "Argument 'element' does not belong to this list");
        }

        // Insert new element
        Element newElement = new Element(data);
        if (head == element) {
            // Insert new head
            newElement.next = element;
            element.previous = newElement;
            head = newElement;
        }
        else {
            // Insert into middle of list
            newElement.next = element;
            newElement.previous = element.previous;
            element.previous.next = newElement;
            element.previous = newElement;
        }

        ++size;

        return newElement;
    }

    public Element insertAfter(Element element, E data)  // 插在此element 之后
            throws IllegalArgumentException {
        // Check pre-conditions
        if (element == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must not be null");
        }
        if (element.getOwner() != this) {
            throw new IllegalArgumentException(
                    "Argument 'element' does not belong to this list");
        }

        // Insert new element
        Element newElement = new Element(data);
        if (tail == element) {
            // Insert new tail
            newElement.previous = element;
            element.next = newElement;
            tail = newElement;
        }
        else {
            // Insert into middle of list
            newElement.next = element.next;
            newElement.previous = element;
            element.next.previous = newElement;
            element.next = newElement;
        }

        ++size;

        return newElement;
    }

    public E removeHead() throws NoSuchElementException {
        // Check pre-conditions
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty list");
        }

        // Remove the head
        Element oldHead = head;
        if (size == 1) {
            // Handle removal of the last element
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.previous = null;
            if (head.next != null) {
                head.next.previous = head;
            }
        }

        --size;

        return oldHead.data;
    }

    public E removeTail() throws NoSuchElementException {
        // Check pre-conditions
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty list");
        }

        // Remove the tail
        Element oldTail = tail;
        if (size == 1) {
            // Handle removal of the last element
            head = null;
            tail = null;
        }
        else {
            tail = tail.previous;
            tail.next = null;
            if (tail.previous != null) {
                tail.previous.next = tail;
            }
        }

        --size;

        return oldTail.data;
    }

    public E remove(Element element)
            throws IllegalArgumentException, NoSuchElementException {
        // Check pre-conditions
        if (element == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must not be null");
        }
        if (element.getOwner() != this) {
            throw new IllegalArgumentException(
                    "Argument 'element' does not belong to this list");
        }

        // Remove element
        if (size == 1) {
            // Remove the last element
            head = null;
        }
        else if (element == head) {
            // Remove the head
            element.next.previous = null;
            head = element.next;
        }
        else if (element == tail) {
            // Remove the tail
            element.previous.next = null;
            tail = element.previous;
        }
        else {
            // Remove from middle of list
            element.previous.next = element.next;
            element.next.previous = element.previous;
        }

        --size;

        return element.data;
    }
}