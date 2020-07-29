// InsertionSort.java
package L3_Sorting_Searching;

import java.util.Comparator;

public class InsertionSort {
    public static <T> void insertionSort(
            T[] array,
            Comparator<? super T> comparator) {    // 参数是 内置接口Compartor的 实现类的 new object

        // Repeatedly insert the next unsorted element (the key) into the
        // sorted elements
        for (int j = 1; j < array.length; ++j) {
            T key = array[j];

            // Move backward through sorted elements, shifting each one up
            // until we find the location where the key will be inserted
            int i = j - 1;
            while (i >= 0 && comparator.compare(array[i], key) > 0) {
                array[i + 1] = array[i];
                --i;
            }

            // Insert the key
            array[i + 1] = key;
        }
    }
}
