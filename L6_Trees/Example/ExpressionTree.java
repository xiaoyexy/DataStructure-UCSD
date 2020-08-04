// ExpressionTree.java
package L6_Trees.Example;

import L6_Trees.BinaryTree;
import L4_Stacks_Queues_Sets.Stack;

public class ExpressionTree {
    BinaryTree<String> parseTree;

    public ExpressionTree(BinaryTree<String> parseTree) {
        this.parseTree = parseTree;
    }

    public String toPrefixNotation() {
        StringBuffer buffer = new StringBuffer();
        doToPrefixNotation(parseTree.getRoot(), buffer);
        return buffer.toString().trim();
    }

    private void doToPrefixNotation(
            BinaryTree<String>.Node node,
            StringBuffer buffer) {
        if (node == null) {
            return;
        }

        buffer.append(node.getData()).append(' ');
        doToPrefixNotation(node.getLeft(), buffer);
        doToPrefixNotation(node.getRight(), buffer);
    }

    public String toPostfixNotation() {
        StringBuffer buffer = new StringBuffer();
        doToPostfixNotation(parseTree.getRoot(), buffer);
        return buffer.toString().trim();
    }

    private void doToPostfixNotation(
            BinaryTree<String>.Node node,
            StringBuffer buffer) {
        if (node == null) {
            return;
        }

        doToPostfixNotation(node.getLeft(), buffer);
        doToPostfixNotation(node.getRight(), buffer);
        buffer.append(node.getData()).append(' ');
    }

    public String toInfixNotation() {
        StringBuffer buffer = new StringBuffer();
        doToInfixNotation(parseTree.getRoot(), buffer);
        return buffer.toString().trim();
    }

    private void doToInfixNotation(
            BinaryTree<String>.Node node,
            StringBuffer buffer) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            buffer.append(node.getData());
        } else {
            // Parentheses required to enforce precedence
            buffer.append("(");
            doToInfixNotation(node.getLeft(), buffer);
            buffer.append(" ").append(node.getData()).append(" ");
            doToInfixNotation(node.getRight(), buffer);
            buffer.append(")");
        }
    }

    public int evaluate() {
        Stack<Integer> stack = new Stack<Integer>();
        doEvaluate(parseTree.getRoot(), stack);
        return stack.pop();
    }

    /**
     * Execute a post-order traversal.  When a node is visited its value will
     * be computed and placed on the stack.  Leaf nodes will have their integer
     * value pushed directly on the stack.  Non-leaf nodes will be evaluated
     * by popping the top two values from the stack, applying the operator held
     * by the non-leaf node, then pushing the result onto the
     * stack.  When the traversal is finished the stack will contain one value
     * which is the result of the overall expression.
     */
    private void doEvaluate(
            BinaryTree<String>.Node node,
            Stack<Integer> stack) {
        if (node == null) {
            return;
        }

        // Evaluate parent
        if (node.isLeaf()) {
            // Leaf: Push value
            stack.push(Integer.parseInt(node.getData()));
        } else {
            doEvaluate(node.getLeft(), stack);
            doEvaluate(node.getRight(), stack);

            // Non-leaf: Pop two values, apply operator, push result
            int op2 = stack.pop();
            int op1 = stack.pop();

            switch (node.getData().charAt(0)) {
                case '+':
                    stack.push(op1 + op2);
                    break;
                case '-':
                    stack.push(op1 - op2);
                    break;
                case '*':
                    stack.push(op1 * op2);
                    break;
                case '/':
                    stack.push(op1 / op2);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown operator '" + node.getData() + "'");
            }
        }
    }
}
