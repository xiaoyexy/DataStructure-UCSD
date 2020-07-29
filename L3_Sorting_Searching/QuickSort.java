// QuickSort.java
package L3_Sorting_Searching;

import java.util.Comparator;
import java.util.Random;

public class QuickSort {
    public static <T> void quickSort(
            T[] array,
            Comparator<? super T> comparator) {     // 参数是 内置接口Compartor的 实现类的 new object

        quickSortRecursive(array, 0, array.length - 1, comparator);
    }

    private static <T> void quickSortRecursive(
            T[] array,
            int i,
            int k,
            Comparator<? super T> comparator) {

        // Stop the recursion when it is not possible to partition further
        if (i >= k) {
            return;
        }

        // Determine where to partition the elements
        int j = partition(array, i, k, comparator);

        // Sort the left partition
        quickSortRecursive(array, i, j, comparator);

        // Sort the right partition
        quickSortRecursive(array, j + 1, k, comparator);
    }

    private static <T> int partition(
            T[] array,
            int i,
            int k,
            Comparator<? super T> comparator) {

        // Use the median-of-three method to find the partition value
        T p = medianOfThree(array, i, k, comparator);

        // Create two partitions around the partition value
        --i;
        ++k;
        while (true) {
            // Move left until an element is found in the wrong partition
            do {
                --k;
            } while (comparator.compare(array[k], p) > 0);         // 从小到大排序

            // Move right until an element is found in the wrong partition
            do {
                ++i;
            } while (comparator.compare(array[i], p) < 0);        // 从小到大排序

            if (i >= k) {
                // Stop partitioning when the left and right indices cross
                break;
            } else {
                // Swap the elements at the indices
                T temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }

        return k;
    }

    private static <T> T medianOfThree(
            T[] array,
            int i,
            int k,
            Comparator<? super T> comparator) {

        // Get 3 random values from the array
        Random random = new Random();
        T a = array[random.nextInt(k - i + 1) + i];
        T b = array[random.nextInt(k - i + 1) + i];
        T c = array[random.nextInt(k - i + 1) + i];

        // Return the median of the 3 values
        if (comparator.compare(a, b) > 0) {             // a > b
            if (comparator.compare(b, c) > 0) {         // a > b > c
                return b;
            } else if (comparator.compare(a, c) > 0) {  // a > c > b
                return c;
            } else {                                    // c > a > b
                return a;
            }
        } else {                                        // b > a
            if (comparator.compare(a, c) > 0) {         // b > a > c
                return a;
            } else if (comparator.compare(b, c) > 0) {  // b > c > a
                return c;
            } else {                                    // c > b > a
                return b;
            }
        }
    }
}
