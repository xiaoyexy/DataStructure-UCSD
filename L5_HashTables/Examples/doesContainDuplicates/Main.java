// Main.java
package L5_HashTables.Examples.doesContainDuplicates;

import L5_HashTables.ChainedHashTable;
import exceptions.DuplicateKeyException;

/**
 * Program determines whether an unsorted array contains duplicates.
 *
 * Our first approach might be to loop over array and for each element
 * loop over remaining elements to see if they match.  This would
 * be O(n^2).
 *
 * A better approach would be to sort the array (O(n lg n)) then
 * loop over the array to see if there are consecutive duplicates
 * O(n).  Overall time is O(n lg n).
 *
 * The solution we implement here loops over the elements in the
 * array, inserting each into a hashtable (O(n)).  If any duplicates
 * are detected we have our answer.  Worst case there are no duplicates
 * and we've inserted everything into the hashtable.  Overall time
 * is O(n).
 */
public class Main {
    public static void main(String args[]) {
        // A large array
        Integer data[] = { 1236, 1265, -1262, 32, /* ... */ 1662 };

        // Load array into hashtable O(n)
        ChainedHashTable<Integer, Integer> table =
                new ChainedHashTable<Integer, Integer>(data.length);
        boolean hasDuplicates = false;
        for (Integer i : data) {
            try {
                table.insert(i, i);
            } catch (DuplicateKeyException ex) {
                hasDuplicates = true;
                break;
            }
        }

        if (hasDuplicates) {
            System.out.println("Duplicates found");
        } else {
            System.out.println("No duplicates found");
        }
    }
}
