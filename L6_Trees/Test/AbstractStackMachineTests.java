// AbstractStackMachineTests.java
package L6_Trees.Test;

import L6_Trees.Example.ExpressionTree;
import L6_Trees.Example.PrefixExpressionParser;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AbstractStackMachineTests {
    // Prefix tests
    @Test
    public void toPrefixNotation_Literal() {
        ExpressionTree tree = PrefixExpressionParser.parse("50");

        assertEquals(tree.toPrefixNotation(), "50");
    }

    @Test
    public void toPrefixNotation_Addition() {
        ExpressionTree tree = PrefixExpressionParser.parse("+ 50 25");

        assertEquals(tree.toPrefixNotation(), "+ 50 25");
    }

    @Test
    public void toPrefixNotation_Subtraction() {
        ExpressionTree tree = PrefixExpressionParser.parse("- 50 25");

        assertEquals(tree.toPrefixNotation(), "- 50 25");
    }

    @Test
    public void toPrefixNotation_Multiplication() {
        ExpressionTree tree = PrefixExpressionParser.parse("* 50 25");

        assertEquals(tree.toPrefixNotation(), "* 50 25");
    }

    @Test
    public void toPrefixNotation_Division() {
        ExpressionTree tree = PrefixExpressionParser.parse("/ 50 25");

        assertEquals(tree.toPrefixNotation(), "/ 50 25");
    }

    @Test
    public void toPrefixNotation_ComplexExpression() {
        ExpressionTree tree = PrefixExpressionParser.parse(
                "* / - 74 10 32 + 23 17");

        assertEquals(tree.toPrefixNotation(), "* / - 74 10 32 + 23 17");
    }

    // Postfix tests
    @Test
    public void toPostfixNotation_Literal() {
        ExpressionTree tree = PrefixExpressionParser.parse("50");

        assertEquals(tree.toPostfixNotation(), "50");
    }

    @Test
    public void toPostfixNotation_Addition() {
        ExpressionTree tree = PrefixExpressionParser.parse("+ 50 25");

        assertEquals(tree.toPostfixNotation(), "50 25 +");
    }

    @Test
    public void toPostfixNotation_Subtraction() {
        ExpressionTree tree = PrefixExpressionParser.parse("- 50 25");

        assertEquals(tree.toPostfixNotation(), "50 25 -");
    }

    @Test
    public void toPostfixNotation_Multiplication() {
        ExpressionTree tree = PrefixExpressionParser.parse("* 50 25");

        assertEquals(tree.toPostfixNotation(), "50 25 *");
    }

    @Test
    public void toPostfixNotation_Division() {
        ExpressionTree tree = PrefixExpressionParser.parse("/ 50 25");

        assertEquals(tree.toPostfixNotation(), "50 25 /");
    }

    @Test
    public void toPostfixNotation_ComplexExpression() {
        ExpressionTree tree = PrefixExpressionParser.parse(
                "* / - 74 10 32 + 23 17");

        assertEquals(tree.toPostfixNotation(), "74 10 - 32 / 23 17 + *");
    }

    // Infix tests
    @Test
    public void toInfixNotation_Literal() {
        ExpressionTree tree = PrefixExpressionParser.parse("50");

        assertEquals(tree.toInfixNotation(), "50");
    }

    @Test
    public void toInfixNotation_Addition() {
        ExpressionTree tree = PrefixExpressionParser.parse("+ 50 25");

        assertEquals(tree.toInfixNotation(), "(50 + 25)");
    }

    @Test
    public void toInfixNotation_Subtraction() {
        ExpressionTree tree = PrefixExpressionParser.parse("- 50 25");

        assertEquals(tree.toInfixNotation(), "(50 - 25)");
    }

    @Test
    public void toInfixNotation_Multiplication() {
        ExpressionTree tree = PrefixExpressionParser.parse("* 50 25");

        assertEquals(tree.toInfixNotation(), "(50 * 25)");
    }

    @Test
    public void toInfixNotation_Division() {
        ExpressionTree tree = PrefixExpressionParser.parse("/ 50 25");

        assertEquals(tree.toInfixNotation(), "(50 / 25)");
    }

    @Test
    public void toInfixNotation_ComplexExpression() {
        ExpressionTree tree = PrefixExpressionParser.parse(
                "* / - 74 10 32 + 23 17");

        assertEquals(tree.toInfixNotation(), "(((74 - 10) / 32) * (23 + 17))");
    }

    // Evaluate tests
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void evaluate_Malformed_Empty() {
        PrefixExpressionParser.parse("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void evaluate_Malformed_NotEnoughTokens() {
        PrefixExpressionParser.parse("+ 1");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void evaluate_Malformed_TooManyTokens() {
        PrefixExpressionParser.parse("+ 1 1 1");
    }

    @Test
    public void evaluate_Literal() {
        ExpressionTree tree = PrefixExpressionParser.parse("50");

        assertEquals(tree.evaluate(), 50);
    }

    @Test
    public void evaluate_Addition() {
        ExpressionTree tree = PrefixExpressionParser.parse("+ 50 25");

        assertEquals(tree.evaluate(), 75);
    }

    @Test
    public void evaluate_Subtraction() {
        ExpressionTree tree = PrefixExpressionParser.parse("- 50 14");

        assertEquals(tree.evaluate(), 36);
    }

    @Test
    public void evaluate_Multiplication() {
        ExpressionTree tree = PrefixExpressionParser.parse("* 5 25");

        assertEquals(tree.evaluate(), 125);
    }

    @Test
    public void evaluate_Division() {
        ExpressionTree tree = PrefixExpressionParser.parse("/ 36 12");

        assertEquals(tree.evaluate(), 3);
    }

    @Test
    public void evaluate_ComplexExpression() {
        ExpressionTree tree =
                PrefixExpressionParser.parse("* / - 74 10 32 + 23 17");

        assertEquals(tree.evaluate(), 80);
    }
}
