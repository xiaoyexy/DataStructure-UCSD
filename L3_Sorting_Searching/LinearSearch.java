// LinearSearch.java
package L3_Sorting_Searching;

public class LinearSearch {
    public static <T> int linearSearch(T[] array, T key) {

        // Scan through all elements of the array
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(key)) {
                // Key found
                return i;
            }
        }

        // Key not found
        return -1;
    }
}
