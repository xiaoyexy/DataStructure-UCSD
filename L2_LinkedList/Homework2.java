package L2_LinkedList;


public class Homework2 {

    public static void appendTerm(SinglyLinkedList<Double> polynomial, Double coefficient) {

        polynomial.insertTail(coefficient);

    }

    public static void display(SinglyLinkedList<Double> polynomial) {
        int size = polynomial.getSize();

        // if the polynomial is empty, print Empty Value
        if (polynomial.isEmpty()) {
            System.out.println("Empty Value");
        } else {

            String outputResult = "";        // display final output
            String sign;                     // define "-" and "+" of each term
            double coefficient;              // define the coefficient of each term
            SinglyLinkedList<Double>.Element elem = polynomial.getHead();

            while (elem != null) {
                coefficient = elem.getData();

                // skip the term with zero coefficient
                if (coefficient != 0.0) {
                    sign = coefficient < 0 ? "-" : "+";
                    outputResult += (sign + " " + Math.abs(coefficient) + "x^" + (size - 1) + " ");
                }
                size--;
                elem = elem.getNext();
            }


            outputResult = outputResult.replace("x^1 ", "x ");   //change x^1 to x
            outputResult = outputResult.replace("x^0 ", " ");    //change x^0 to constant term
            outputResult = outputResult.replace(" 1.0x", " x");  //change terms whose coefficient == 1

            if (outputResult.substring(0, 2).equals("+ ")) {
                outputResult = outputResult.substring(2);      // remove "+ " at the beginning
            }

            if (outputResult.substring(0, 2).equals("- ")) {
                outputResult = "-" + outputResult.substring(2);  // adjust  "- " at the beginning
            }

            System.out.println(outputResult);

        }

    }

    public static double evaluate(SinglyLinkedList<Double> polynomial, double x) {
        int size = polynomial.getSize();

        // if the polynomial is empty, return 0
        if (size == 0) {
            return 0;
        }

        double result = 0;
        double coefficient;

        SinglyLinkedList<Double>.Element ele1 = polynomial.getHead();
        while (ele1 != null) {
            coefficient = ele1.getData();
            result += (coefficient * Math.pow(x, (size - 1)));
            size--;
            ele1 = ele1.getNext();
        }

        return result;
    }


    public static void testProgram(double[] constantList, double x) {
        SinglyLinkedList<Double> list = new SinglyLinkedList<>();

        // test appendTerm
        for (int i = 0; i < constantList.length; i++) {
            appendTerm(list, constantList[i]);
        }

        // test display
        display(list);


        // test evaluate
        double value = evaluate(list, x);


        System.out.println("The result is " + value + "， with x = " + x + "\n");

    }

    public static void main(String[] args) {

        double[] test1 = {1.0, 1.0};
        testProgram(test1, 1.0);

        double[] test2 = {1.0, 0.0, -1.0};
        testProgram(test2, 2.03);

        double[] test3 = {-3.0, 0.5, -2.0, 0.0};
        testProgram(test3, 05.0);

        double[] test4 = {-0.3125, 0.0, -9.915, -7.75, -40.0};
        testProgram(test4, 123.45);

        // test reverseList
        SinglyLinkedList list = new SinglyLinkedList();
        for (int i = 0; i<5;i++){
            list.insertTail(i);
        }
        list.outputList();
        list.reverseList();
        list.outputList();
        System.out.println(list.getHead().getData());
        System.out.println(list.getTail().getData());

    }

}

