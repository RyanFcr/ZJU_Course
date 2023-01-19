package chapter32;

import java.awt.event.*;

public class TestCourseWithActionEvent {
  CourseWithActionEvent course =
    new CourseWithActionEvent("Java Programming");

  public TestCourseWithActionEvent() {
    course.setEnrollmentCap(2);
    ActionListener listener = new Listener();
    course.addActionListener(listener);
    course.addStudent("John");
    System.out.println("John added");
    course.addStudent("Jim");
    System.out.println("Jim added");
    course.addStudent("Tim");
    System.out.println("Tim added");
  }

  public static void main(String[] args) {
    new TestCourseWithActionEvent();
  }

  private class Listener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("Enrollment cap exceeded");
    }
  }
}
