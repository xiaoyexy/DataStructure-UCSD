// Circle.java
package L3_Sorting_Searching.Examples;

// No longer implement Comparable
public class Circle {
    private double radius;
    private double x;
    private double y;

    public Circle(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public double getDistanceFromOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
