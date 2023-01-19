package chapter8;

public class TestCircle1 {
  /** Main method */
  public static void main(String[] args) {
    // Create a circle with radius 5.0
    Circle1 myCircle = new Circle1(5.0);
    System.out.println("The area of the circle of radius "
      + myCircle.radius + " is " + myCircle.getArea());

    // Create a circle with radius 1
    Circle1 yourCircle = new Circle1();
    System.out.println("The area of the circle of radius "
      + yourCircle.radius + " is " + yourCircle.getArea());

    // Modify circle radius
    yourCircle.radius = 100;
    System.out.println("The area of the circle of radius "
      + yourCircle.radius + " is " + yourCircle.getArea());
  }
}

// Define the circle class with two constructors
class Circle1 {
  double radius;

  /** Construct a circle with radius 1 */
  Circle1() {
    radius = 1.0;
  }

  /** Construct a circle with a specified radius */
  Circle1(double newRadius) {
    radius = newRadius;
  }

  /** Return the area of this circle */
  double getArea() {
    return radius * radius * Math.PI;
  }
}
