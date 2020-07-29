// MergeSort.java
package L3_Sorting_Searching;

import java.util.Comparator;

public class MergeSort {
    public static <T> void mergeSort(
            T[] array,
            Comparator<? super T> comparator) {

        mergeSortRecursive(array, 0, array.length - 1, comparator);
    }

    private static <T> void mergeSortRecursive(
            T[] array,
            int i,
            int k,
            Comparator<? super T> comparator) {

        // Stop the recursion when no more divisions can be made
        if (i >= k) {
            return;
        }

        // Determine where to divide the elements
        int j = (i + k - 1) / 2;

        // Sort the left division
        mergeSortRecursive(array, i, j, comparator);

        // Sort the right division
        mergeSortRecursive(array, j + 1, k, comparator);

        // Merge the two sorted divisions
        merge(array, i, j, k, comparator);
    }

    // See the method implementation for an explanation as to why we're using
    // the SuppressedWarnings annotation.
    @SuppressWarnings("unchecked")
    private static <T> void merge(
            T[] array,
            int i,
            int j,
            int k,
            Comparator<? super T> comparator) {

        // Create array to hold merged results.  In java we cannot
        // create an array of generic objects (e.g. new T[]) so we must
        // create an array of Objects and cast the elements later when
        // we're ready to use them.
        Object[] merged = new Object[k - i + 1];

        // Continue while either sorted sequence has elements to merge
        int pos1 = i;       // Position in first sequence
        int pos2 = j + 1;   // Position in second sequence
        int posM = 0;       // Position in merged sequence
        while (pos1 <= j || pos2 <= k) {
            if (pos1 > j) {
                // The first sequence has no more elements to merge,
                // merge all remaining elements from the second sequence
                while (pos2 <= k) {
                    merged[posM] = array[pos2];
                    ++posM;
                    ++pos2;
                }
            } else if (pos2 > k) {
                // The second sequence has no more elements to merge,
                // merge all remaining elements from the first sequence
                while (pos1 <= j) {
                    merged[posM] = array[pos1];
                    ++posM;
                    ++pos1;
                }
            } else {
                // Both sequences have more elements to merge
                if (comparator.compare(array[pos1], array[pos2]) < 0) {
                    // Next element from sequence 1 is smaller, merge it
                    merged[posM] = array[pos1];
                    ++posM;
                    ++pos1;
                } else {
                    // Next element from sequence 2 is smaller, merge it
                    merged[posM] = array[pos2];
                    ++posM;
                    ++pos2;
                }
            }
        }

        // Copy the merged sequence into the original array
        for (int index = 0; index < merged.length; ++index) {
            // This cast from Object to T results in an unchecked warning.
            // We know this is safe since we stored T's in the merged
            // array.  Therefore we suppress this warning with the
            // @SuppressWarning annotation at the method level.
            array[i + index] = (T)merged[index];
        }
    }
}
