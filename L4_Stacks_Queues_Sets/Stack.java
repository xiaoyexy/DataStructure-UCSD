// Stack.java
package L4_Stacks_Queues_Sets;

import L2_LinkedList.SinglyLinkedList;

import java.util.NoSuchElementException;

public class Stack<E> {
    // 对于SLList: push,pop 都是对head的实现
    private SinglyLinkedList<E> list = new SinglyLinkedList<E>();

    public void push(E data) {
        list.insertHead(data);
    }

    public E pop() throws NoSuchElementException {
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
