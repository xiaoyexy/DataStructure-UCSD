// RadixSort.java
package L3_Sorting_Searching;

public class RadixSort {
    public static void radixSort(
            int[] array,
            int numPositions,
            int radix) {

        // Create array to hold the counts
        int[] counts = new int[radix + 1];

        // Create array to hold the sorted elements
        int[] sorted = new int[array.length];

        // Sort once for each position from least to most significant
        for (int position = 0; position < numPositions; ++position) {
            // Initialize the counts
            for (int i = 0; i <= radix; ++i) {
                counts[i] = 0;
            }

            // Calculate the position's power (e.g. 10^0, 10^1, 10^2)
            int positionValue = (int)Math.pow(radix, position);

            // Count the occurrences of each digit in that position
            for (int value : array) {
                int index = (value / positionValue) % radix;
                ++counts[index];
            }

            // Adjust each count to reflect the counts before it
            for (int i = 1; i <= radix; ++i) {
                counts[i] = counts[i] + counts[i - 1];
            }

            // Use the counts to position each element where it belongs
            for (int i = array.length - 1; i >= 0; --i) {
                int index = (array[i] / positionValue) % radix;
                sorted[counts[index] - 1] = array[i];
                --counts[index];
            }

            // Replace the original array with the sorted array
            System.arraycopy(sorted, 0, array, 0, array.length);
        }
    }
}
