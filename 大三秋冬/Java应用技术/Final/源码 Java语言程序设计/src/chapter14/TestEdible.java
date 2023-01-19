package chapter14;

public class TestEdible {
  public static void main(String[] args) {
    Object[] objects = {new Tiger(), new Chicken(), new Apple()};
    for (int i = 0; i < objects.length; i++)
      if (objects[i] instanceof Edible)
        System.out.println(((Edible)objects[i]).howToEat());
  }
}

class Animal {
  // Data fields, constructors, and methods omitted here
}

class Chicken extends Animal implements Edible {
  public String howToEat() {
    return "Chicken: Fry it";
  }
}

class Tiger extends Animal {
}

abstract class Fruit implements Edible {
  // Data fields, constructors, and methods omitted here
}

class Apple extends Fruit {
  public String howToEat() {
    return "Apple: Make apple cider";
  }
}

class Orange extends Fruit {
  public String howToEat() {
    return "Orange: Make orange juice";
  }
}
