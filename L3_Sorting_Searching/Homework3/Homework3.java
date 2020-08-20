package L3_Sorting_Searching.Homework3;

import java.util.Comparator;

import static L3_Sorting_Searching.QuickSort.quickSort;


public class Homework3 {

    public class compareCarsByMakeThenModel implements Comparator<Car> {
        @Override
        public int compare(Car c1, Car c2) {
            if (c1.make.equals(c2.make)) {           //  when two cars have the same make
                return c1.model.compareTo(c2.model); //  in ascending order by model
            } else {
                return c1.make.compareTo(c2.make);   //  in ascending order by make
            }
        }
    }


    public class compareCarsByDescendingMPG implements Comparator<Car> {
        @Override
        public int compare(Car c1, Car c2) {
            return -(c1.mpg - c2.mpg);              // in descending order by mpg
        }
    }


    public class compareCarsByMakeThenDescendingMPG implements Comparator<Car> {
        @Override
        public int compare(Car c1, Car c2) {
            if (c1.make.equals(c2.make)) {           //  when two cars have the same make
                return -(c1.mpg - c2.mpg);           //  in descending order by mpg
            } else {
                return c1.make.compareTo(c2.make);   //  in ascending order by make
            }
        }
    }


    public static void displayCars(Car[] obj) {  // Output Car[]
        for (int i = 0; i < obj.length; i++) {
            System.out.println(String.format("%-8s %-10s %d", obj[i].make, obj[i].model, obj[i].mpg)
            );
        }
    }

    public static void main(String[] args) {
        Car[] testObj = new Car[]{
                new Car("Toyota", "Camry", 33),
                new Car("Ford", "Focus", 40),
                new Car("Honda", "Accord", 34),
                new Car("Ford", "Mustang", 31),
                new Car("Honda", "Civic", 39),
                new Car("Toyota", "Prius", 48),
                new Car("Honda", "Fit", 35),
                new Car("Toyota", "Corolla", 35),
                new Car("Ford", "Taurus", 28)
        };

        // Output the cars in original unsorted order.
        displayCars(testObj);


        // Output the cars sorted by make then model.
        quickSort(testObj, new Homework3().new compareCarsByMakeThenModel()); // new inner class
        System.out.println("\n======by make then mode======");
        displayCars(testObj);


        // Output the cars sorted by descending MPG.
        quickSort(testObj, new Homework3().new compareCarsByDescendingMPG());
        System.out.println("\n======by descending MPG======");
        displayCars(testObj);


        // Output the cars sorted by make then descending MPG.
        quickSort(testObj, new Homework3().new compareCarsByMakeThenDescendingMPG());
        System.out.println("\n======by make then descending MPG======");
        displayCars(testObj);
    }


}