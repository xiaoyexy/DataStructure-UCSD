package L6_Trees.Homework6;

import L6_Trees.BinaryTree;

public class Main_HW6 {
    public static void main(String[] args) {
        BinaryTree<Integer> tree1 = new BinaryTree<>();
        tree1.insertRoot(1);
        tree1.getRoot().insertLeft(2);
        tree1.getRoot().getLeft().insertLeft(4);
        tree1.getRoot().getLeft().getLeft().insertLeft(7);
        tree1.getRoot().insertRight(3);
        tree1.getRoot().getRight().insertLeft(5);
        tree1.getRoot().getRight().insertRight(6);
        tree1.getRoot().getRight().getRight().insertRight(8);
        tree1.getRoot().getRight().getRight().getRight().insertRight(9);


        BinaryTree<Integer> tree2 = new BinaryTree<>();
        tree2.insertRoot(6);
        tree2.getRoot().insertLeft(4);
        tree2.getRoot().getLeft().insertLeft(2);
        tree2.getRoot().getLeft().insertRight(5);
        tree2.getRoot().getLeft().getLeft().insertLeft(1);
        tree2.getRoot().getLeft().getLeft().insertRight(3);
        tree2.getRoot().insertRight(8);
        tree2.getRoot().getRight().insertLeft(7);
        tree2.getRoot().getRight().insertRight(9);


        System.out.println("==================");
        System.out.println("countLeaves");
        System.out.println("Tree1: " + Homework6.countLeaves(tree1));
        System.out.println("Tree2: " + Homework6.countLeaves(tree2));
        System.out.println("");


        System.out.println("==================");
        System.out.println("countNonLeaves");
        System.out.println("Tree1: " + Homework6.countNonLeaves(tree1));
        System.out.println("Tree2: " + Homework6.countNonLeaves(tree2));
        System.out.println("");


        System.out.println("==================");
        System.out.println("getHeight");
        System.out.println("Tree1: " + Homework6.getHeight(tree1));
        System.out.println("Tree2: " + Homework6.getHeight(tree2));
        System.out.println("");


        System.out.println("==================");
        System.out.println("printPreOrder Tree1: ");
        Homework6.printPreOrder(tree1);
        System.out.println("printPreOrder Tree2: ");
        Homework6.printPreOrder(tree2);
        System.out.println("");


        System.out.println("==================");
        System.out.println("printInOrder Tree1: ");
        Homework6.printInOrder(tree1);
        System.out.println("printInOrder Tree2: ");
        Homework6.printInOrder(tree2);
        System.out.println("");


        System.out.println("==================");
        System.out.println("printPostOrder Tree1: ");
        Homework6.printPostOrder(tree1);
        System.out.println("");
        System.out.println("printPostOrder Tree2: ");
        Homework6.printPostOrder(tree2);
        System.out.println("");


        System.out.println("==================");
        System.out.println("removeLeaves Tree1: ");
        Homework6.removeLeaves(tree1);
        System.out.println("removeLeaves Tree2: ");
        Homework6.removeLeaves(tree2);
        System.out.println("");


//        BinaryTree<Integer> tree3 = new BinaryTree<>();
//        tree3.insertRoot(5);
//        Homework6.removeLeaves(tree3);

    }
}
