package L4_Stacks_Queues_Sets.Homework4;

import L4_Stacks_Queues_Sets.Stack;

public class Homework4 {

    public static void addLargeNumbers(String num1, String num2) {
        Stack<Integer> num1Stack = new Stack<>();
        Stack<Integer> num2Stack = new Stack<>();
        Stack<Integer> resultStack = new Stack<>();

        // store num1 on the stack
        for (int i = 0; i < num1.length(); i++) {
            // if num1 has invalid characters
            if (num1.charAt(i) < '0' || num1.charAt(i) > '9') {
                System.out.println("Invalid num1");
                return;
            }
            num1Stack.push(Integer.valueOf(num1.charAt(i) + "")); //Integer.ValueOf requires a string here
        }


        // store num2 on the stack
        for (int i = 0; i < num2.length(); i++) {
            // if num2 has invalid characters
            if (num2.charAt(i) < '0' || num2.charAt(i) > '9') {
                System.out.println("Invalid num2");
                return;
            }
            num2Stack.push(Integer.valueOf(num2.charAt(i) + ""));
        }


        // add numbers
        int result = 0;
        while ((!num1Stack.isEmpty()) || (!num2Stack.isEmpty())) {
            // if the lengths of num1 and num2 are different
            int num1Digit = num1Stack.isEmpty() ? 0 : num1Stack.pop();
            int num2Digit = num2Stack.isEmpty() ? 0 : num2Stack.pop();

            int tempSum = num1Digit + num2Digit + result;
            resultStack.push(tempSum % 10);
            result = tempSum / 10;
        }

        if (result != 0) {
            resultStack.push(result);
        }


        // display results
        String output = "";
        while (!resultStack.isEmpty()) {
            output += String.valueOf(resultStack.pop());
        }

        System.out.println(String.format("Input: %s, %s", num1, num2));
        System.out.println("Output result: " + output + "\n");

    }


    public static void main(String[] args) {
        addLargeNumbers("9623567954036854775807", "120623372036854775807");
        addLargeNumbers("12657637203685477580745", "35696957372036854775807");
        addLargeNumbers("1034545837568335555775807", "10623568393755555775807");
    }
}
