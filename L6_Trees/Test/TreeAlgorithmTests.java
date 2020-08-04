// TreeAlgorithmTests.java
package L6_Trees.Test;

import L2_LinkedList.SinglyLinkedList;
import L6_Trees.BinaryTree;
import L6_Trees.TreeAlgorithms;
import interfaces.Visitor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TreeAlgorithmTests {
    /**
     * Visitor implementation used by the tests.  Adds each visited
     * datum to the end of a list.  The list can retrieve this list to verify
     * the order data was visited matches expectations.
     */
    private class TestVisitor implements Visitor<Integer> {
        private SinglyLinkedList<Integer> visitedData =
                new SinglyLinkedList<Integer>();

        public SinglyLinkedList<Integer> getVisitedData() {
            return visitedData;
        }

        public void visit(Integer data) {
            visitedData.insertTail(data);
        }
    }

    private BinaryTree<Integer> tree;
    private TestVisitor visitor;

    @BeforeMethod
    public void beforeMethod() {
        // Build tree:
        //             20
        //            /  \
        //           9    53
        //          / \     \
        //         5  15    79
        //            /
        //           11
        tree = new BinaryTree<Integer>();
        tree.insertRoot(20);
        tree.getRoot().insertLeft(9);
        tree.getRoot().insertRight(53);
        tree.getRoot().getLeft().insertLeft(5);
        tree.getRoot().getLeft().insertRight(15);
        tree.getRoot().getRight().insertRight(79);
        tree.getRoot().getLeft().getRight().insertLeft(11);

        visitor = new TestVisitor();
    }

    @Test
    public void traversePreOrder() {
        SinglyLinkedList<Integer> expected = new SinglyLinkedList<Integer>();
        expected.insertTail(20);
        expected.insertTail(9);
        expected.insertTail(5);
        expected.insertTail(15);
        expected.insertTail(11);
        expected.insertTail(53);
        expected.insertTail(79);

        TreeAlgorithms.traversePreOrder(tree, visitor);

        assertEquals(visitor.getVisitedData(), expected);
    }

    @Test
    public void traverseInOrder() {
        SinglyLinkedList<Integer> expected = new SinglyLinkedList<Integer>();
        expected.insertTail(5);
        expected.insertTail(9);
        expected.insertTail(11);
        expected.insertTail(15);
        expected.insertTail(20);
        expected.insertTail(53);
        expected.insertTail(79);

        TreeAlgorithms.traverseInOrder(tree, visitor);

        assertEquals(visitor.getVisitedData(), expected);
    }

    @Test
    public void traversePostOrder() {
        SinglyLinkedList<Integer> expected = new SinglyLinkedList<Integer>();
        expected.insertTail(5);
        expected.insertTail(11);
        expected.insertTail(15);
        expected.insertTail(9);
        expected.insertTail(79);
        expected.insertTail(53);
        expected.insertTail(20);

        TreeAlgorithms.traversePostOrder(tree, visitor);

        assertEquals(visitor.getVisitedData(), expected);
    }

    @Test
    public void traverseLevelOrder() {
        SinglyLinkedList<Integer> expected = new SinglyLinkedList<Integer>();
        expected.insertTail(20);
        expected.insertTail(9);
        expected.insertTail(53);
        expected.insertTail(5);
        expected.insertTail(15);
        expected.insertTail(79);
        expected.insertTail(11);

        TreeAlgorithms.traverseLevelOrder(tree, visitor);

        assertEquals(visitor.getVisitedData(), expected);
    }
}
