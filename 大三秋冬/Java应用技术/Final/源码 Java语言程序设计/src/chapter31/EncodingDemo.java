package chapter31;

import java.util.*;
import java.io.*;
import javax.swing.*;

public class EncodingDemo {
  public static void main(String[] args)
      throws IOException, FileNotFoundException {
    PrintWriter output = new PrintWriter("temp.txt", "GB18030");
    output.print("\u6B22\u8FCE Welcome \u03b1\u03b2\u03b3");
    output.close();

    Scanner input = new Scanner(new File("temp.txt"), "GB18030");
    JOptionPane.showMessageDialog(null, input.nextLine());
  }
}
