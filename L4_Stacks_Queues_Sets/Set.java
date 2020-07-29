// Set.java
package L4_Stacks_Queues_Sets;

import L2_LinkedList.SinglyLinkedList;
import exceptions.DuplicateElementException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Set<E> implements Iterable<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<E>();

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void insert(E data) throws
            IllegalArgumentException,
            DuplicateElementException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        if (isMember(data)) {
            throw new DuplicateElementException();
        }

        // Insert data into the list
        list.insertTail(data);
    }

    public E remove(E data) throws
            IllegalArgumentException,
            NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        // If empty list
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // Look for match at head
        if (list.getHead().getData().equals(data)) {
            return list.removeHead();
        }

        // Look for match after head
        SinglyLinkedList<E>.Element prevElem = list.getHead();
        while (prevElem.getNext() != null) {
            if (prevElem.getNext().getData().equals(data)) {
                return list.removeAfter(prevElem);
            }
            prevElem = prevElem.getNext();
        }

        throw new NoSuchElementException();
    }

    public boolean isMember(E data) throws
            IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        // Loop over all elems, return true if data found
        SinglyLinkedList<E>.Element elem = list.getHead();
        while (elem != null) {
            if (elem.getData().equals(data)) {
                return true;
            }
            elem = elem.getNext();
        }

        return false;
    }

    public E getMember(E data) throws
            IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        // Loop over all elems, return matching data
        SinglyLinkedList<E>.Element elem = list.getHead();
        while (elem != null) {
            if (elem.getData().equals(data)) {
                return elem.getData();
            }
            elem = elem.getNext();
        }

        throw new IllegalArgumentException("member does not exist");
    }

    public Set<E> union(Set<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        Set<E> result = new Set<E>();

        // Add all items from this
        SinglyLinkedList<E>.Element elem = list.getHead();
        while (elem != null) {
            result.insert(elem.getData());
            elem = elem.getNext();
        }

        // Add all items from other
        elem = other.list.getHead();
        while (elem != null) {
            if (!result.isMember(elem.getData())) {
                result.insert(elem.getData());
            }
            elem = elem.getNext();
        }

        return result;
    }

    public Set<E> intersection(Set<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        Set<E> result = new Set<E>();

        // For each element in this
        SinglyLinkedList<E>.Element elem = list.getHead();
        while (elem != null) {
            // If element also in other
            if (other.isMember(elem.getData())) {
                result.insert(elem.getData());
            }
            elem = elem.getNext();
        }

        return result;
    }

    public Set<E> difference(Set<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        Set<E> result = new Set<E>();

        // For each element in this
        SinglyLinkedList<E>.Element elem = list.getHead();
        while (elem != null) {
            // If element not in other
            if (!other.isMember(elem.getData())) {
                result.insert(elem.getData());
            }
            elem = elem.getNext();
        }

        return result;
    }

    public boolean isSubset(Set<E> other) throws
            IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        // If this has more elements than other, this can't be a subset
        if (getSize() > other.getSize()) {
            return false;
        }

        // If any member in this is not in other, this is not a subset
        SinglyLinkedList<E>.Element elem = list.getHead();
        while (elem != null) {
            // If element not in other
            if (!other.isMember(elem.getData())) {
                return false;
            }
            elem = elem.getNext();
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Set))
            return false;

        Set<E> other = (Set<E>)o;

        // If sets equal size and one is subset of other, then they're equal
        return getSize() == other.getSize()
                && isSubset(other);
    }

    private class SetIterator implements Iterator<E> {
        private SinglyLinkedList<E>.Element elem;

        public SetIterator() {
            elem = Set.this.list.getHead();
        }

        public boolean hasNext() {
            return elem != null;
        }

        public E next() {
            if (hasNext()) {
                E data = elem.getData();
                elem = elem.getNext();
                return data;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public Iterator<E> iterator() {
        return new SetIterator();
    }
}
