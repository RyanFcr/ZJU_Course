package chapter35;

import java.awt.event.*;
import java.util.*;

public class CircleModel {
  /** Property radius. */
  private double radius = 20;

  /** Property filled. */
  private boolean filled;

  /** Property color. */
  private java.awt.Color color;

  /** Utility field used by event firing mechanism. */
  private ArrayList<ActionListener> actionListenerList;

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;

    // Notify the listener for the change on radius
    processEvent(
      new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "radius"));
  }

  public boolean isFilled() {
    return filled;
  }

  public void setFilled(boolean filled) {
    this.filled = filled;

    // Notify the listener for the change on filled
    processEvent(
      new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "filled"));
  }

  public java.awt.Color getColor() {
    return color;
  }

  public void setColor(java.awt.Color color) {
    this.color = color;

    // Notify the listener for the change on color
    processEvent(
      new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "color"));
  }

  /** Register an action event listener */
  public synchronized void addActionListener(ActionListener l) {
    if (actionListenerList == null)
      actionListenerList = new ArrayList<ActionListener>();

    actionListenerList.add(l);
  }

  /** Remove an action event listener */
  public synchronized void removeActionListener(ActionListener l) {
    if (actionListenerList != null && actionListenerList.contains(l))
      actionListenerList.remove(l);
  }

  /** Fire TickEvent */
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
