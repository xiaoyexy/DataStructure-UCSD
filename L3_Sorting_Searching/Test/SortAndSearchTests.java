// SortTests.java
package L3_Sorting_Searching.Test;

import L3_Sorting_Searching.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.testng.Assert.assertEquals;

public class SortAndSearchTests {
    private class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Person person = (Person)o;

            return age == person.age
                    && firstName.equals(person.firstName)
                    && lastName.equals(person.lastName);
        }
    }

    private class FirstNameComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p1.firstName.compareTo(p2.firstName);
        }
    }

    private class LastNameComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p1.lastName.compareTo(p2.lastName);
        }
    }

    private class AgeComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p1.age - p2.age;
        }
    }

    // Unsorted arrays
    private Person[] arrayOf0;
    private Person[] arrayOf1;
    private Person[] arrayOf2;
    private Person[] arrayOfOdd;
    private Person[] arrayOfEven;

    // Sorted arrays
    private Person[] arrayOf0SortedByFirstName;
    private Person[] arrayOf0SortedByLastName;
    private Person[] arrayOf0SortedByAge;
    private Person[] arrayOf1SortedByFirstName;
    private Person[] arrayOf1SortedByLastName;
    private Person[] arrayOf1SortedByAge;
    private Person[] arrayOf2SortedByFirstName;
    private Person[] arrayOf2SortedByLastName;
    private Person[] arrayOf2SortedByAge;
    private Person[] arrayOfOddSortedByFirstName;
    private Person[] arrayOfOddSortedByLastName;
    private Person[] arrayOfOddSortedByAge;
    private Person[] arrayOfEvenSortedByFirstName;
    private Person[] arrayOfEvenSortedByLastName;
    private Person[] arrayOfEvenSortedByAge;

    // Persons
    private Person p1 = new Person("Alemayehu", "Ng?c", 36);
    private Person p2 = new Person("Guntur", "Yusuf", 5);
    private Person p3 = new Person("Gerhold", "Flamur", 14);
    private Person p4 = new Person("Steffan", "Rupert", 30);
    private Person p5 = new Person("Lievin", "Dumisani", 46);
    private Person p6 = new Person("Federigo", "Boinko", 7);

    @BeforeMethod
    public void beforeMethod() {
        // Populate unsorted arrays
        arrayOf0 = new Person[] {};
        arrayOf1 = new Person[] { p1 };
        arrayOf2 = new Person[] { p1, p2 };
        arrayOfOdd = new Person[] { p1, p2, p3, p4, p5 };
        arrayOfEven = new Person[] { p1, p2, p3, p4, p5, p6 };

        // Populate sorted arrays
        arrayOf0SortedByFirstName = new Person[] {};
        arrayOf0SortedByLastName = new Person[] {};
        arrayOf0SortedByAge = new Person[] {};
        arrayOf1SortedByFirstName = new Person[] { p1 };
        arrayOf1SortedByLastName = new Person[] { p1 };
        arrayOf1SortedByAge = new Person[] { p1 };
        arrayOf2SortedByFirstName = new Person[] { p1, p2 };
        arrayOf2SortedByLastName = new Person[] { p1, p2 };
        arrayOf2SortedByAge = new Person[] { p2, p1 };
        arrayOfOddSortedByFirstName = new Person[] { p1, p3, p2, p5, p4 };
        arrayOfOddSortedByLastName = new Person[] { p5, p3, p1, p4, p2 };
        arrayOfOddSortedByAge = new Person[] { p2, p3, p4, p1, p5 };
        arrayOfEvenSortedByFirstName = new Person[] { p1, p6, p3, p2, p5, p4 };
        arrayOfEvenSortedByLastName = new Person[] { p6, p5, p3, p1, p4, p2 };
        arrayOfEvenSortedByAge = new Person[] { p2, p6, p3, p4, p1, p5 };
    }

    // Test the built-in java Arrays.sort.  This is a stable sort.
    @Test
    public void javaSort() {
        // arrayOf0
        Arrays.sort(arrayOf0, new FirstNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByFirstName);
        Arrays.sort(arrayOf0, new LastNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByLastName);
        Arrays.sort(arrayOf0, new AgeComparator());
        assertEquals(arrayOf0, arrayOf0SortedByAge);

        // arrayOf1
        Arrays.sort(arrayOf1, new FirstNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByFirstName);
        Arrays.sort(arrayOf1, new LastNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByLastName);
        Arrays.sort(arrayOf1, new AgeComparator());
        assertEquals(arrayOf1, arrayOf1SortedByAge);

        // arrayOf2
        Arrays.sort(arrayOf2, new FirstNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByFirstName);
        Arrays.sort(arrayOf2, new LastNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByLastName);
        Arrays.sort(arrayOf2, new AgeComparator());
        assertEquals(arrayOf2, arrayOf2SortedByAge);

        // arrayOfOdd
        Arrays.sort(arrayOfOdd, new FirstNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByFirstName);
        Arrays.sort(arrayOfOdd, new LastNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByLastName);
        Arrays.sort(arrayOfOdd, new AgeComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByAge);

        // arrayOfEven
        Arrays.sort(arrayOfEven, new FirstNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByFirstName);
        Arrays.sort(arrayOfEven, new LastNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByLastName);
        Arrays.sort(arrayOfEven, new AgeComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByAge);
    }

    @Test
    public void insertionSort() {
        // arrayOf0
        InsertionSort.insertionSort(arrayOf0, new FirstNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByFirstName);
        InsertionSort.insertionSort(arrayOf0, new LastNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByLastName);
        InsertionSort.insertionSort(arrayOf0, new AgeComparator());
        assertEquals(arrayOf0, arrayOf0SortedByAge);

        // arrayOf1
        InsertionSort.insertionSort(arrayOf1, new FirstNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByFirstName);
        InsertionSort.insertionSort(arrayOf1, new LastNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByLastName);
        InsertionSort.insertionSort(arrayOf1, new AgeComparator());
        assertEquals(arrayOf1, arrayOf1SortedByAge);

        // arrayOf2
        InsertionSort.insertionSort(arrayOf2, new FirstNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByFirstName);
        InsertionSort.insertionSort(arrayOf2, new LastNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByLastName);
        InsertionSort.insertionSort(arrayOf2, new AgeComparator());
        assertEquals(arrayOf2, arrayOf2SortedByAge);

        // arrayOfOdd
        InsertionSort.insertionSort(arrayOfOdd, new FirstNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByFirstName);
        InsertionSort.insertionSort(arrayOfOdd, new LastNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByLastName);
        InsertionSort.insertionSort(arrayOfOdd, new AgeComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByAge);

        // arrayOfEven
        InsertionSort.insertionSort(arrayOfEven, new FirstNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByFirstName);
        InsertionSort.insertionSort(arrayOfEven, new LastNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByLastName);
        InsertionSort.insertionSort(arrayOfEven, new AgeComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByAge);
    }

    @Test
    public void quickSort() {
        // arrayOf0
        QuickSort.quickSort(arrayOf0, new FirstNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByFirstName);
        QuickSort.quickSort(arrayOf0, new LastNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByLastName);
        QuickSort.quickSort(arrayOf0, new AgeComparator());
        assertEquals(arrayOf0, arrayOf0SortedByAge);

        // arrayOf1
        QuickSort.quickSort(arrayOf1, new FirstNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByFirstName);
        QuickSort.quickSort(arrayOf1, new LastNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByLastName);
        QuickSort.quickSort(arrayOf1, new AgeComparator());
        assertEquals(arrayOf1, arrayOf1SortedByAge);

        // arrayOf2
        QuickSort.quickSort(arrayOf2, new FirstNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByFirstName);
        QuickSort.quickSort(arrayOf2, new LastNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByLastName);
        QuickSort.quickSort(arrayOf2, new AgeComparator());
        assertEquals(arrayOf2, arrayOf2SortedByAge);

        // arrayOfOdd
        QuickSort.quickSort(arrayOfOdd, new FirstNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByFirstName);
        QuickSort.quickSort(arrayOfOdd, new LastNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByLastName);
        QuickSort.quickSort(arrayOfOdd, new AgeComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByAge);

        // arrayOfEven
        QuickSort.quickSort(arrayOfEven, new FirstNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByFirstName);
        QuickSort.quickSort(arrayOfEven, new LastNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByLastName);
        QuickSort.quickSort(arrayOfEven, new AgeComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByAge);
    }

    @Test
    public void mergeSort() {
        // arrayOf0
        MergeSort.mergeSort(arrayOf0, new FirstNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByFirstName);
        MergeSort.mergeSort(arrayOf0, new LastNameComparator());
        assertEquals(arrayOf0, arrayOf0SortedByLastName);
        MergeSort.mergeSort(arrayOf0, new AgeComparator());
        assertEquals(arrayOf0, arrayOf0SortedByAge);

        // arrayOf1
        MergeSort.mergeSort(arrayOf1, new FirstNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByFirstName);
        MergeSort.mergeSort(arrayOf1, new LastNameComparator());
        assertEquals(arrayOf1, arrayOf1SortedByLastName);
        MergeSort.mergeSort(arrayOf1, new AgeComparator());
        assertEquals(arrayOf1, arrayOf1SortedByAge);

        // arrayOf2
        MergeSort.mergeSort(arrayOf2, new FirstNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByFirstName);
        MergeSort.mergeSort(arrayOf2, new LastNameComparator());
        assertEquals(arrayOf2, arrayOf2SortedByLastName);
        MergeSort.mergeSort(arrayOf2, new AgeComparator());
        assertEquals(arrayOf2, arrayOf2SortedByAge);

        // arrayOfOdd
        MergeSort.mergeSort(arrayOfOdd, new FirstNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByFirstName);
        MergeSort.mergeSort(arrayOfOdd, new LastNameComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByLastName);
        MergeSort.mergeSort(arrayOfOdd, new AgeComparator());
        assertEquals(arrayOfOdd, arrayOfOddSortedByAge);

        // arrayOfEven
        MergeSort.mergeSort(arrayOfEven, new FirstNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByFirstName);
        MergeSort.mergeSort(arrayOfEven, new LastNameComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByLastName);
        MergeSort.mergeSort(arrayOfEven, new AgeComparator());
        assertEquals(arrayOfEven, arrayOfEvenSortedByAge);
    }

    @Test
    public void countingSort() {
        int[] array = new int[] { 9, 10, 3, 0, 2, 6, 1 };
        int[] arraySorted = new int[] { 0, 1, 2, 3, 6, 9, 10 };

        CountingSort.countingSort(array, 10);

        assertEquals(array, arraySorted);
    }

    @Test
    public void radixSort() {
        int[] array = new int[] { 989, 109, 123, 422, 421, 102, 380 };
        int[] arraySorted = new int[] { 102, 109, 123, 380, 421, 422, 989 };

        RadixSort.radixSort(array, 3, 10);

        assertEquals(array, arraySorted);
    }

    @Test
    public void linearSearch() {
        Person[] array = new Person[] { p1, p2, p3, p4, p5, p6 };

        // Successful search
        assertEquals(LinearSearch.linearSearch(array, p6), 5);

        // Unsuccessful search
        Person p7 = new Person("Rinat", "Byelobog", 66);
        assertEquals(LinearSearch.linearSearch(array, p7), -1);
    }

    @Test
    public void binarySearch() {
        Person[] array = new Person[] { p1, p2, p3, p4, p5, p6 };

        // TODO: Implement
        // Successful search
        assertEquals(LinearSearch.linearSearch(array, p6), 5);

        // Unsuccessful search
        Person p7 = new Person("Rinat", "Byelobog", 66);
        assertEquals(LinearSearch.linearSearch(array, p7), -1);
    }
}
