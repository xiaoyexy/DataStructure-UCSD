// ExpressionParser.java
package L6_Trees.Example;

import L6_Trees.BinaryTree;

/**
 * Parses prefix expressions into an ExpressionTree.  The supported operators
 * are +, -, *, and /.
 */
public class PrefixExpressionParser {
    private String[] tokens;
    private int tokenIndex = 0;
    private BinaryTree<String> parseTree = new BinaryTree<String>();

    public static ExpressionTree parse(String expression) {
        PrefixExpressionParser parser = new PrefixExpressionParser(expression);
        return new ExpressionTree(parser.parseTree);
    }

    // Build the parse tree
    private PrefixExpressionParser(String expression) {
        if (expression.equals("")) {
            throw new IllegalArgumentException(
                    "Expression malformed: Empty expression");
        }

        tokens = expression.split(" ");

        // Insert first token as root
        String token = getNextToken();
        BinaryTree<String>.Node tokenNode = parseTree.insertRoot(token);

        // Build rest of tree recursively
        if (isOperator(token)) {
            insertLeftOperand(tokenNode);
            insertRightOperand(tokenNode);
        }

        if (tokenIndex != tokens.length) {
            throw new IllegalArgumentException(
                    "Expression malformed: Too many tokens");
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/");
    }

    private void insertLeftOperand(BinaryTree<String>.Node operatorNode) {
        // Insert token as left child
        String token = getNextToken();
        BinaryTree<String>.Node tokenNode = operatorNode.insertLeft(token);

        if (isOperator(token)) {
            insertLeftOperand(tokenNode);
            insertRightOperand(tokenNode);
        }
    }

    private void insertRightOperand(BinaryTree<String>.Node operatorNode) {
        // Insert token as right child
        String token = getNextToken();
        BinaryTree<String>.Node tokenNode = operatorNode.insertRight(token);

        if (isOperator(token)) {
            insertLeftOperand(tokenNode);
            insertRightOperand(tokenNode);
        }
    }

    private String getNextToken() {
        if (tokenIndex == tokens.length) {
            throw new IllegalArgumentException(
                    "Expression malformed: Not enough tokens");
        }

        return tokens[tokenIndex++];
    }
}
