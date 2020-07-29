// CountingSort.java
package L3_Sorting_Searching;


public class CountingSort {
    public static void countingSort(
            int[] array,
            int maxElementValue) {

        // Create array to hold the counts
        int[] counts = new int[maxElementValue + 1];
        for (int i = 0; i <= maxElementValue; ++i) {
            counts[i] = 0;
        }

        // Count the occurrences of each element
        for (int value : array) {
            ++counts[value];
        }

        // Adjust each count to reflect the counts before it
        for (int i = 1; i <= maxElementValue; ++i) {
            counts[i] = counts[i] + counts[i - 1];
        }

        // Use the counts to position each element where it belongs
        int[] sorted = new int[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            sorted[counts[array[i]] - 1] = array[i];
            --counts[array[i]];
        }

        // Replace the original array with the sorted array
        System.arraycopy(sorted, 0, array, 0, array.length);
    }
}
