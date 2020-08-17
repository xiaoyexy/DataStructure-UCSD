// TreeAlgorithms.java
package L6_Trees;

import L4_Stacks_Queues_Sets.Queue;
import interfaces.Visitor;

public final class TreeAlgorithms {
    // Pre-order traversal
    public static <E> void traversePreOrder(
            BinaryTree<E> tree,
            Visitor<E> visitor) { // 这里Visitor是一个SSL，定义在Test里面
        doTraversePreOrder(tree.getRoot(), visitor);
    }

    private static <E> void doTraversePreOrder(
            BinaryTree<E>.Node node,
            Visitor<E> visitor) {
        if (node == null) {
            return;
        }

        visitor.visit(node.getData()); // visit method：是把这个data写入SLL
        doTraversePreOrder(node.getLeft(), visitor);
        doTraversePreOrder(node.getRight(), visitor);
    }


    // In-order traversal
    public static <E> void traverseInOrder(
            BinaryTree<E> tree,
            Visitor<E> visitor) {
        doTraverseInOrder(tree.getRoot(), visitor);
    }

    private static <E> void doTraverseInOrder(
            BinaryTree<E>.Node node,
            Visitor<E> visitor) {
        if (node == null) {
            return;
        }

        doTraverseInOrder(node.getLeft(), visitor);
        visitor.visit(node.getData());
        doTraverseInOrder(node.getRight(), visitor);
    }

    // Post-order traversal
    public static <E> void traversePostOrder(
            BinaryTree<E> tree,
            Visitor<E> visitor) {
        doTraversePostOrder(tree.getRoot(), visitor);
    }

    private static <E> void doTraversePostOrder(
            BinaryTree<E>.Node node,
            Visitor<E> visitor) {
        if (node == null) {
            return;
        }

        doTraversePostOrder(node.getLeft(), visitor);
        doTraversePostOrder(node.getRight(), visitor);
        visitor.visit(node.getData());
    }

    // Level-order traversal
    public static <E> void traverseLevelOrder(
            BinaryTree<E> tree,
            Visitor<E> visitor) {
        // Queue holds nodes that have been discovered and must be visited
        Queue<BinaryTree<E>.Node> queue = new Queue<BinaryTree<E>.Node>();

        // Start off with only root in queue
        if (!tree.isEmpty()) {
            queue.enqueue(tree.getRoot());
        }

        // While nodes remain to be visited in the queue
        while (!queue.isEmpty()) {
            // Visit the front node
            BinaryTree<E>.Node node = queue.dequeue();
            visitor.visit(node.getData());

            // Enqueue front node's children
            if (node.hasLeft()) {
                queue.enqueue(node.getLeft());
            }
            if (node.hasRight()) {
                queue.enqueue(node.getRight());
            }
        }
    }
}
