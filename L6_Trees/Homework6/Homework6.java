package L6_Trees.Homework6;

import L6_Trees.BinaryTree;


public class Homework6 {

    // a) countLeaves
    private static int doCountLeaves(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        return doCountLeaves(node.getRight()) + doCountLeaves(node.getLeft());
    }


    public static int countLeaves(BinaryTree<Integer> tree) {
        return doCountLeaves(tree.getRoot());
    }


    // b) countNonLeaves
    private static int doCountNonLeaves(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return 0;
        }
        return 1 + doCountNonLeaves(node.getRight()) + doCountNonLeaves(node.getLeft());
    }


    public static int countNonLeaves(BinaryTree tree) {
        //return doCountNonLeaves(tree.getRoot());
        return tree.getSize() - countLeaves(tree);
    }


    // c) getHeight
    private static int doGetHeight(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(doGetHeight(node.getLeft()), doGetHeight(node.getRight()));

    }

    public static int getHeight(BinaryTree<Integer> tree) {
        return doGetHeight(tree.getRoot());

    }

    // d) printPreOrder
    private static void doPreOrder(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getData());
        doPreOrder(node.getLeft());
        doPreOrder(node.getRight());
    }

    public static void printPreOrder(BinaryTree<Integer> tree) {
        doPreOrder(tree.getRoot());

    }

    // e) printInOrder
    private static void doInOrder(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return;
        }
        doInOrder(node.getLeft());
        System.out.println(node.getData());
        doInOrder(node.getRight());
    }

    public static void printInOrder(BinaryTree<Integer> tree) {
        doInOrder(tree.getRoot());

    }


    // f) printPostOrder
    private static void doPostOrder(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return;
        }
        doPostOrder(node.getLeft());
        doPostOrder(node.getRight());
        System.out.println(node.getData());

    }

    public static void printPostOrder(BinaryTree<Integer> tree) {
        doPostOrder(tree.getRoot());

    }


    // g) removeLeaves
    private static void doRemoveLeaves(BinaryTree<Integer>.Node node) {
        if (node == null) {
            return;
        }

        if (node.hasRight() && node.getRight().isLeaf()) {
            node.removeRight();
        }

        if (node.hasLeft() && node.getLeft().isLeaf()) {
            node.removeLeft();
        }

        doRemoveLeaves(node.getRight());
        doRemoveLeaves(node.getLeft());
    }

    public static void removeLeaves(BinaryTree tree) {
        if (getHeight(tree) == 1) { // if tree only has a node
            tree.removeRoot();
        } else {
            doRemoveLeaves(tree.getRoot());
        }

        printPostOrder(tree);
    }

}
