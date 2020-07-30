// Main.java
package L5_HashTables.Examples.hashFunctions;


/**
 * Program output:
    Axel's hash code: 1586346684
    Kaipo's hash code: 1490009985
    point1's hash code: 33
    point2's hash code: 63
 */
public class Main {
    public static void main(String args[]) {
        Person person1 = new Person("Axel", "Wil", 64.32, 122.39, 24.78);
        Person person2 = new Person("Kaipo", "Valens", 64.32, 122.39, 24.78);

        System.out.println("Axel's hash code: " + person1.hashCode());
        System.out.println("Kaipo's hash code: " + person2.hashCode());

        Point point1 = new Point((short)1, (short)2);
        Point point2 = new Point((short)2, (short)1);

        System.out.println("point1's hash code: " + point1.hashCode());
        System.out.println("point2's hash code: " + point2.hashCode());
    }
}
