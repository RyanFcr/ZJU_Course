package chapter32;

import java.util.*;
import java.awt.event.*;

public class CourseWithActionEvent {
  private String courseName = "default name";
  private ArrayList<String> students = new ArrayList<String>();
  private int enrollmentCap = 10;

  private ArrayList<ActionListener> actionListenerList;

  public CourseWithActionEvent() {
  }

  public CourseWithActionEvent(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void addStudent(String student) {
    students.add(student);

    if (students.size() > enrollmentCap) {
      // Fire ActionEvent
      processEvent(new ActionEvent(this,
        ActionEvent.ACTION_PERFORMED, null));
    }
  }

  public String[] getStudents() {
    return (String[])students.toArray();
  }

  public int getNumberOfStudents() {
    return students.size();
  }

  public int getEnrollmentCap() {
    return enrollmentCap;
  }

  public void setEnrollmentCap(int enrollmentCap) {
    this.enrollmentCap = enrollmentCap;
  }

  /** Register an action event listener */
  public synchronized void addActionListener
      (ActionListener listener) {
    if (actionListenerList == null) {
      actionListenerList = new ArrayList<ActionListener>(2);
    }

    if (!actionListenerList.contains(listener)) {
      actionListenerList.add(listener);
    }
  }

  /** Remove an action event listener */
  public synchronized void removeActionListener
      (ActionListener listener) {
    if (actionListenerList !=
        null && actionListenerList.contains(listener)) {
      actionListenerList.remove(listener);
    }
  }

  /** Fire ActionEvent */
  private void processEvent(ActionEvent e) {
    ArrayList list;

    synchronized (this) {
      if (actionListenerList == null) return;
      list = (ArrayList)actionListenerList.clone();
    }

    for (int i = 0; i < list.size(); i++) {
      ActionListener listener = (ActionListener)list.get(i);
      listener.actionPerformed(e);
    }
  }
}
