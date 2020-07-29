// CircularDoublyLinkedList.java
package L2_LinkedList;
import java.util.NoSuchElementException;


public class CircularDoublyLinkedList<E> {
    // An element in a linked list
    public class Element {
        private E data;
        private Element next;
        private Element previous;

        // Only allow CircularDoublyLinkedList to construct Elements
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

        public CircularDoublyLinkedList getOwner() {
            return CircularDoublyLinkedList.this;
        }
    }

    private Element head;
    private int size;

    public Element getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Element insertHead(E data) {
        return insertBeforeImpl(head, data);
    }

    public Element insertBefore(Element element, E data)
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

        return insertBeforeImpl(element, data);
    }

    public Element insertAfter(Element element, E data)
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

        return insertBeforeImpl(element.next, data);
    }

    public E removeHead() throws NoSuchElementException {
        // Check pre-conditions
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty list");
        }

        return removeImpl(head);
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

        return removeImpl(element);
    }

    private Element insertBeforeImpl(Element element, E data) {
        // Insert new element
        Element newElement = new Element(data);
        if (element == null) {
            // Insert into empty list
            newElement.next = newElement;
            newElement.previous = newElement;
            head = newElement;
        }
        else if (element == head) {
            // Insert new head
            newElement.next = element;
            newElement.previous = element.previous;
            element.previous.next = newElement;
            element.previous = newElement;
            head = newElement;
        }
        else {
            newElement.next = element;
            newElement.previous = element.previous;
            element.previous.next = newElement;
            element.previous = newElement;
        }

        ++size;

        return newElement;
    }

    private E removeImpl(Element element) {
        // Remove element
        if (size == 1) {
            // Handle removal of last element
            head = null;
        }
        else if (element == head) {
            // Handle removal of head
            element.previous.next = element.next;
            element.next.previous = element.previous;
            head = head.next;
        }
        else {
            element.previous.next = element.next;
            element.next.previous = element.previous;
        }

        --size;

        return element.data;
    }
}