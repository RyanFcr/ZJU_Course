package chapter19;

import java.io.*;

public class TestObjectInputStream {
  public static void main(String[] args)
    throws ClassNotFoundException, IOException {
    // Create an input stream for file object.dat
    ObjectInputStream input =
      new ObjectInputStream(new FileInputStream("object.dat"));

    // Write a string, double value, and object to the file
    String name = input.readUTF();
    double score = input.readDouble();
    java.util.Date date = (java.util.Date)(input.readObject());
    System.out.println(name + " " + score + " " + date);

    // Close output stream
    input.close();
  }
}
