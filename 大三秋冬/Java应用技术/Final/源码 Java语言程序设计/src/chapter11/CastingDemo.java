package chapter11;

public class CastingDemo {
  /** Main method */
  public static void main(String[] args) {
    // Declare and initialize two objects
    Object object1 = new Circle4(1);
    Object object2 = new Rectangle1(1, 1);

    // Display circle and rectangle
    displayObject(object1);
    displayObject(object2);
  }

  /** A method for displaying an object */
  public static void displayObject(Object object) {
    if (object instanceof Circle4) {
      System.out.println("The circle area is " +
        ((Circle4)object).getArea());
      System.out.println("The circle diameter is " +
        ((Circle4)object).getDiameter());
    }
    else if (object instanceof Rectangle1) {
      System.out.println("The rectangle area is " +
        ((Rectangle1)object).getArea());
    }
  }
}
