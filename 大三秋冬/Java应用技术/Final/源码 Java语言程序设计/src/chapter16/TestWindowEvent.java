package chapter16;

import java.awt.event.*;
import javax.swing.JFrame;

public class TestWindowEvent extends JFrame {
  public static void main(String[] args) {
    TestWindowEvent frame = new TestWindowEvent();
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("TestWindowEvent");
    frame.setSize(220, 80);
    frame.setVisible(true);
  }

  public TestWindowEvent() {
    addWindowListener(new WindowListener() {
      /**
       * Handler for window deiconified event
       * Invoked when a window is changed from a minimized
       * to a normal state.
       */
      public void windowDeiconified(WindowEvent event) {
        System.out.println("Window deiconified");
      }

      /**
       * Handler for window iconified event
       * Invoked when a window is changed from a normal to a
       * minimized state. For many platforms, a minimized window
       * is displayed as the icon specified in the window's
       * iconImage property.
       */
      public void windowIconified(WindowEvent event) {
        System.out.println("Window iconified");
      }

      /**
       * Handler for window activated event
       * Invoked when the window is set to be the user's
       * active window, which means the window (or one of its
       * subcomponents) will receive keyboard events.
       */
      public void windowActivated(WindowEvent event) {
        System.out.println("Window activated");
      }

      /**
       * Handler for window deactivated event
       * Invoked when a window is no longer the user's active
       * window, which means that keyboard events will no longer
       * be delivered to the window or its subcomponents.
       */
      public void windowDeactivated(WindowEvent event) {
        System.out.println("Window deactivated");
      }

      /**
       * Handler for window opened event
       * Invoked the first time a window is made visible.
       */
      public void windowOpened(WindowEvent event) {
        System.out.println("Window opened");
      }

      /**
       * Handler for window closing event
       * Invoked when the user attempts to close the window
       * from the window's system menu.  If the program does not
       * explicitly hide or dispose the window while processing
       * this event, the window close operation will be cancelled.
       */
      public void windowClosing(WindowEvent event) {
        System.out.println("Window closing");
      }

      /**
       * Handler for window closed event
       * Invoked when a window has been closed as the result
       * of calling dispose on the window.
       */
      public void windowClosed(WindowEvent event) {
        System.out.println("Window closed");
      }
    });
  }
}
