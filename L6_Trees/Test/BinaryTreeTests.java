// BinaryTreeTests.java
package L6_Trees.Test;

import L6_Trees.BinaryTree;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BinaryTreeTests {
    private BinaryTree<String> tree;

    @BeforeMethod
    public void beforeMethod() {
        tree = new BinaryTree<String>();
    }

    @Test
    public void getSize_EmptyTree() {
        assertEquals(tree.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyTree() {
        tree.insertRoot("foo");

        assertEquals(tree.getSize(), 1);
    }

    @Test
    public void isEmpty_EmptyTree() {
        assertTrue(tree.isEmpty());
    }

    @Test
    public void isEmpty_NonEmptyTree() {
        tree.insertRoot("foo");

        assertFalse(tree.isEmpty());
    }

    @Test
    public void getRoot_EmptyTree() {
        assertNull(tree.getRoot());
    }

    @Test
    public void getRoot_NonEmptyTree() {
        BinaryTree<String>.Node node = tree.insertRoot("foo");

        assertNotNull(node);
        assertNotNull(tree.getRoot());
        assertEquals(tree.getRoot().getData(), "foo");
    }

    @Test
    public void insertRoot_EmptyTree() {
        BinaryTree<String>.Node node = tree.insertRoot("foo");

        assertNotNull(node);
        assertSame(tree.getRoot(), node);
        assertNotNull(tree.getRoot());
        assertEquals(tree.getRoot().getData(), "foo");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertRoot_NonEmptyTree() {
        tree.insertRoot("foo");
        tree.insertRoot("bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeRoot_EmptyTree() {
        tree.removeRoot();
    }

    @Test
    public void removeRoot_SingleNodeTree() {
        tree.insertRoot("foo");
        String data = tree.removeRoot();

        assertTrue(tree.isEmpty());
        assertEquals(tree.getSize(), 0);
        assertEquals(data, "foo");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeRoot_MultiNodeTree() {
        tree.insertRoot("foo");
        tree.getRoot().insertLeft("bar");
        tree.removeRoot();
    }

    @Test
    public void insertLeft_LeftChildDoesNotExist() {
        tree.insertRoot("foo").insertLeft("bar");

        assertFalse(tree.isEmpty());
        assertEquals(tree.getSize(), 2);
        assertEquals(tree.getRoot().getData(), "foo");
        assertEquals(tree.getRoot().getLeft().getData(), "bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertLeft_LeftChildDoesExist() {
        tree.insertRoot("foo");
        tree.getRoot().insertLeft("bar");
        tree.getRoot().insertLeft("baz");
    }

    @Test
    public void insertRight_RightChildDoesNotExist() {
        tree.insertRoot("foo");
        tree.getRoot().insertRight("bar");

        assertFalse(tree.isEmpty());
        assertEquals(tree.getSize(), 2);
        assertEquals(tree.getRoot().getData(), "foo");
        assertEquals(tree.getRoot().getRight().getData(), "bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void insertRight_RightChildDoesExist() {
        tree.insertRoot("foo");
        tree.getRoot().insertRight("bar");
        tree.getRoot().insertRight("baz");
    }

    @Test
    public void isLeaf_Leaf() {
        tree.insertRoot("foo");

        assertTrue(tree.getRoot().isLeaf());
    }

    @Test
    public void isLeaf_NonLeaf_HasLeftChild() {
        tree.insertRoot("foo");
        tree.getRoot().insertLeft("bar");

        assertFalse(tree.getRoot().isLeaf());
    }

    @Test
    public void isLeaf_NonLeaf_HasRightChild() {
        tree.insertRoot("foo");
        tree.getRoot().insertRight("bar");

        assertFalse(tree.getRoot().isLeaf());
    }

    @Test
    public void isLeaf_NonLeaf_HasBothChildren() {
        tree.insertRoot("foo");
        tree.getRoot().insertLeft("bar");
        tree.getRoot().insertRight("baz");

        assertFalse(tree.getRoot().isLeaf());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeLeft_DoesNotHaveLeftChild() {
        tree.insertRoot("foo");
        tree.getRoot().removeLeft();
    }

    @Test
    public void removeLeft_HasLeftChild() {
        tree.insertRoot("foo");
        tree.getRoot().insertLeft("bar");
        String data = tree.getRoot().removeLeft();

        assertFalse(tree.isEmpty());
        assertEquals(tree.getSize(), 1);
        assertEquals(data, "bar");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void removeRight_DoesNotHaveRightChild() {
        tree.insertRoot("foo");
        tree.getRoot().removeRight();
    }

    @Test
    public void removeRight_HasRightChild() {
        tree.insertRoot("foo");
        tree.getRoot().insertRight("bar");
        String data = tree.getRoot().removeRight();

        assertFalse(tree.isEmpty());
        assertEquals(tree.getSize(), 1);
        assertEquals(data, "bar");
    }

    @Test
    public void merge_EmptyTrees() {
        BinaryTree<String> tree1 = new BinaryTree<String>();
        BinaryTree<String> tree2 = new BinaryTree<String>();
        BinaryTree<String> merged = tree1.merge(tree2, "foo");

        assertTrue(tree1.isEmpty());
        assertEquals(tree1.getSize(), 0);
        assertTrue(tree2.isEmpty());
        assertEquals(tree2.getSize(), 0);
        assertFalse(merged.isEmpty());
        assertEquals(merged.getSize(), 1);
        assertEquals(merged.getRoot().getData(), "foo");
    }

    @Test
    public void merge_LeftTreeEmpty_RightTreeNonEmpty() {
        BinaryTree<String> tree1 = new BinaryTree<String>();
        tree1.insertRoot("foo");
        tree1.getRoot().insertLeft("bar");
        BinaryTree<String> tree2 = new BinaryTree<String>();
        BinaryTree<String> merged = tree1.merge(tree2, "baz");

        assertTrue(tree1.isEmpty());
        assertEquals(tree1.getSize(), 0);
        assertTrue(tree2.isEmpty());
        assertEquals(tree2.getSize(), 0);
        assertFalse(merged.isEmpty());
        assertEquals(merged.getSize(), 3);
        assertEquals(merged.getRoot().getData(), "baz");
        assertEquals(merged.getRoot().getLeft().getData(), "foo");
        assertEquals(merged.getRoot().getLeft().getLeft().getData(), "bar");
    }

    @Test
    public void merge_LeftTreeNonEmpty_RightTreeEmpty() {
        BinaryTree<String> tree1 = new BinaryTree<String>();
        BinaryTree<String> tree2 = new BinaryTree<String>();
        tree2.insertRoot("foo");
        tree2.getRoot().insertLeft("bar");
        BinaryTree<String> merged = tree1.merge(tree2, "baz");

        assertTrue(tree1.isEmpty());
        assertEquals(tree1.getSize(), 0);
        assertTrue(tree2.isEmpty());
        assertEquals(tree2.getSize(), 0);
        assertFalse(merged.isEmpty());
        assertEquals(merged.getSize(), 3);
        assertEquals(merged.getRoot().getData(), "baz");
        assertEquals(merged.getRoot().getRight().getData(), "foo");
        assertEquals(merged.getRoot().getRight().getLeft().getData(), "bar");
    }

    @Test
    public void merge_LeftTreeNonEmpty_RightTreeNonEmpty() {
        BinaryTree<String> tree1 = new BinaryTree<String>();
        tree1.insertRoot("foo1");
        tree1.getRoot().insertLeft("bar1");
        BinaryTree<String> tree2 = new BinaryTree<String>();
        tree2.insertRoot("foo2");
        tree2.getRoot().insertLeft("bar2");
        BinaryTree<String> merged = tree1.merge(tree2, "baz");

        assertTrue(tree1.isEmpty());
        assertEquals(tree1.getSize(), 0);
        assertTrue(tree2.isEmpty());
        assertEquals(tree2.getSize(), 0);
        assertFalse(merged.isEmpty());
        assertEquals(merged.getSize(), 5);
        assertEquals(merged.getRoot().getData(), "baz");
        assertEquals(merged.getRoot().getLeft().getData(), "foo1");
        assertEquals(merged.getRoot().getLeft().getLeft().getData(), "bar1");
        assertEquals(merged.getRoot().getRight().getData(), "foo2");
        assertEquals(merged.getRoot().getRight().getLeft().getData(), "bar2");
    }
}
