/* SinglyLinkedList.java */
package L2_LinkedList;
import java.util.NoSuchElementException;


public class SinglyLinkedList<E> {
    // An element in a linked list
    public class Element {                // 内部类可以自由访问外部类成员和方法，不论是否private Element
        private E data;
        private Element next;

        // Only allow SinglyLinkedList to construct Elements
        private Element(E data) {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public Element getNext() {
            return next;
        }

        private SinglyLinkedList getOwner() { // 返回 SinglyLinkedList
            return SinglyLinkedList.this;     // 当成员名或方法名发生覆盖时，可以使用外部类的名字加.this指定访问外部类成员
            // 若仅仅为this 则访问内部类成员
            // this 运行时指该Object
        }
    }

    private Element head;    // 没有new,仅仅定义reference
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

    public Element insertHead(E data) {   // 插入head 或 head之前
        Element newElement = new Element(data);   //new了对象

        if (isEmpty()) {
            // Insert into empty list
            head = newElement;       // 头尾均为同一对象
            tail = newElement;
        } else {
            // Insert into non-empty list
            newElement.next = head;
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
        } else {
            // Insert into non-empty list
            tail.next = newElement;
            tail = newElement;
        }

        ++size;

        return newElement;
    }

    public Element insertAfter(Element element, E data)   // 插在此element 之后
            throws IllegalArgumentException {
        // Check pre-conditions
        if (element == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must not be null");
        }
        if (element.getOwner() != this) {                                 // 此处 this == SinglyLinkedList 指向外部类
            throw new IllegalArgumentException(                           // this 运行时指该 Object
                    "Argument 'element' does not belong to this list");   // 检查该 element 是否在这个 list 中
        }

        // Insert new element
        Element newElement = new Element(data);
        if (tail == element) {
            // Insert new tail
            element.next = newElement;
            tail = newElement;
        } else {
            // Insert into middle of list
            newElement.next = element.next;
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
        } else {
            head = head.next;
        }

        --size;

        return oldHead.data;
    }

    // Note that there is no removeTail.  This cannot be implemented
    // efficiently because it would require O(n) to scan from head until
    // reaching the item _before_ tail.

    public E removeAfter(Element element)    // 删除该element之后的一个element
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
        if (element == tail) {
            throw new IllegalArgumentException(
                    "Argument 'element' must have a non-null next element");
        }

        // Remove element
        Element elementToRemove = element.next;
        if (elementToRemove == tail) {
            // Remove the tail
            element.next = null;
            tail = element;
        } else {
            // Remove from middle of list
            element.next = elementToRemove.next;
        }

        --size;

        return elementToRemove.data;
    }


    public SinglyLinkedList reverseList() {
        if (getSize() >= 2) {

            Element prev = null;
            Element next = null;
            Element current = getHead();

            while (current != null) {
                next = current.getNext();
                current.next = prev;
                prev = current;
                current = next;
            }

            tail = head;
            head = prev;

        }
        return this;
    }


    public void outputList() {
        System.out.println("List");
        System.out.println("----");
        Element elem = getHead();

        while (elem != null) {
            System.out.println("\t" + elem.getData());
            elem = elem.getNext();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinglyLinkedList<?> that = (SinglyLinkedList<?>) o;

        if (this.size != that.size) return false;

        // Return whether all elements are the same
        SinglyLinkedList<?>.Element thisElem = this.getHead();
        SinglyLinkedList<?>.Element thatElem = that.getHead();
        while (thisElem != null && thatElem != null) {
            if (!thisElem.getData().equals(thatElem.getData())) {
                return false;
            }
            thisElem = thisElem.getNext();
            thatElem = thatElem.getNext();
        }

        return true;
    }
}
