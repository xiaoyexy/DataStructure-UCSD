// PriorityQueue.java
package L7_Heaps_PriorityQueues;

import java.util.Comparator;

public class PriorityQueue<E> {
    Heap<E> heap;

    public PriorityQueue(Comparator<? super E> comparator) {
        this.heap = new Heap<E>(comparator);
    }

    public int getSize() {
        return heap.getSize();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public void insert(E data) {
        heap.insert(data);
    }

    @SuppressWarnings("unchecked")
    public E extract() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Cannot extract from empty priority queue");
        }

        return heap.extract();
    }

    @SuppressWarnings("unchecked")
    public E peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Cannot peek into empty priority queue");
        }

        // Default package-internal visibility of tree allows access here
        return (E)heap.tree[0];
    }
}
