package L1_Fundamentals;
import java.util.Random;


public class Homework1 {
    public static void main(String[] args) {
//        int[] myArray = new int[]{2,1,4,6,3,5,3};
//        printArray(insert(myArray,5,3));
        int NUM_READINGS = 60;                                                                          //O(1)
        int INSERTS_PER_READING = 2000;                                                                 //O(1)
        int[] myArray = {0};                                                                            //O(1)
        final double NANO_SECONDS_PER_SECOND = 1_000_000_000;                                           //O(1)

        System.out.println("Array Length\tSeconds per insert");                                         //O(1)

        //  Take NUM_READINGS readings
        for (int i = 0; i < NUM_READINGS; i++) {                                                        //O(NUM_READINGS)
            long start = System.nanoTime();                                                             //O(1)

            // Each reading will be taken after INSERTS_PER_READING inserts
            for (int j = 0; j < INSERTS_PER_READING; j++) {                                             //O(INSERTS_PER_READING)
                int index = new Random().nextInt(myArray.length);                                       //O(1)
                int value = new Random().nextInt();                                                     //O(1)
                myArray = insert(myArray, index, value);                                                //O(array.length)
            }

            long stop = System.nanoTime();                                                                //O(1)
            double duration = (double) ((stop - start) / (NANO_SECONDS_PER_SECOND * INSERTS_PER_READING));//O(1)

            //Output reading in tabular format
            System.out.println(String.format("%d\t\t\t%.6f", myArray.length, duration));                  //O(1)
        }


    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }

    /*
        Overall: O(n) (n=the length of array)
     */
    public static int[] insert(int[] array, int index, int value) {
        // return invalid array if index >= the length of original array
        if (index >= array.length) {                             // O(1)
            int[] invalidArray = new int[0];                     // O(1)
            return invalidArray;                                 // O(1)
        }

        // Create a new array
        int[] newArray = new int[array.length + 1];              // O(1)

        // Copy elements up to insert point from original array to new array
        for (int i = 0; i < index; i++) {                       // O(index)
            newArray[i] = array[i];                             // O(1)
        }

        // Place insert value into new array
        newArray[index] = value;                                // O(1)

        // Copy elements after insert point from original array to new array
        for (int i = index + 1; i < array.length + 1; i++) {    // O(array.length-index)
            newArray[i] = array[i - 1];                         // O(1)
        }

        return newArray;                                        // O(1)
    }


}
