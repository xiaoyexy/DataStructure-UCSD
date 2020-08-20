package L7_Heaps_PriorityQueues.Homework7;


import L7_Heaps_PriorityQueues.Heap;

import java.util.Comparator;

public class Homework7 {

    class Person {
        String name;
        int age;
        double height;

        public Person(String name, int age, double height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }
    }


    public static void displayPerson(Person person) {
        System.out.println("Name:" + person.name +
                "   Age:" + person.age +
                "   Height:" + person.height);
    }


    public static void outputSorted(Person[] person, Comparator<Person> comparator) {
        Heap<Person> heap = new Heap<Person>(comparator);
        for (Person p : person) {
            heap.insert(p);
        }
        while (!heap.isEmpty()) {
            Person extracted = heap.extract();
            displayPerson(extracted);
        }
    }


    public static void main(String[] args) {
        Person[] testArray = new Person[6];
        testArray[0] = new Homework7().new Person("Tim", 23, 172.2);
        testArray[1] = new Homework7().new Person("Mike", 14, 156.0);
        testArray[2] = new Homework7().new Person("Jason", 56, 178);
        testArray[3] = new Homework7().new Person("Will", 20, 188.5);
        testArray[4] = new Homework7().new Person("Jose", 30, 175);
        testArray[5] = new Homework7().new Person("John", 19, 190);

        // by ascending name
        System.out.println("====================");
        System.out.println("by ascending name");
        outputSorted(testArray, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return -o1.name.compareTo(o2.name);
            }
        });

        // by ascending age
        System.out.println("====================");
        System.out.println("by ascending age");
        outputSorted(testArray, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.age - o1.age;
            }
        });

        // by ascending height
        System.out.println("====================");
        System.out.println("by ascending height");
        outputSorted(testArray, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (int) (o2.height - o1.height);
            }
        });

    }

}
