package L5_HashTables.Homework5;

public class Homework5 {
    public static void display(AutoGrowingChainedHashTable table) {
        System.out.println(String.format("buckets %d, elements %d, lf %.2f, max lf %.2f, resize multiplier %.1f",
                table.getBucketsNum(),
                table.getSize(),
                table.getLoadFactor(),
                table.getMaxLoadFactor(),
                table.getResizeMultiplier()
                )
        );

    }
    public static void main(String[] args) {
        AutoGrowingChainedHashTable<Integer, Integer> table =
                new AutoGrowingChainedHashTable<Integer, Integer>(5, 0.5, 2.0);

        table.insert(2,3);
        display(table);

        table.insert(3,6);
        display(table);

        table.insert(4,7);
        display(table);

        table.insert(5,7);
        display(table);

        table.insert(6,7);
        display(table);

        System.out.println(table.lookup(2)) ;  // look up a value that was inserted before the resize
        System.out.println(table.lookup(8)) ;  // look up a value that does not exist

    }
}
