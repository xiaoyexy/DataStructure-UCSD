// Queue.java
package L4_Stacks_Queues_Sets;

import L2_LinkedList.SinglyLinkedList;
import java.util.NoSuchElementException;


public class Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<E>();

    public void enqueue(E data) {
        list.insertTail(data);
    }

    public E dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.removeHead();
    }

    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.getHead().getData();
    }

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
