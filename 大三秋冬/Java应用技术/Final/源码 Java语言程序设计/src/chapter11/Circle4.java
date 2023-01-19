package chapter11;

public class Circle4 extends GeometricObject1 {
  private double radius;

  public Circle4() {
  }

  public Circle4(double radius) {
	super();
    this.radius = radius;
  }

  public Circle4(double radius, String color, boolean filled) {
    super(color, filled);
    this.radius = radius;
    //setColor(color);
    //setFilled(filled);
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }
  
  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }
  
  /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /* Print the circle info */
  public void printCircle() {
    System.out.println(toString() + "The circle is created " + getDateCreated() +
      " and the radius is " + radius);
  }
  
  public String toString() {
    return "Circle WWWW " + getColor() + super.toString();
  }
}
