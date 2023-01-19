package chapter31;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class WorldClockControl extends JPanel {
  // Obtain all available locales and time zone ids
  private Locale[] availableLocales = Locale.getAvailableLocales();
  private String[] availableTimeZones = TimeZone.getAvailableIDs();

  // Comboxes to display available locales and time zones
  private JComboBox jcbLocales = new JComboBox();
  private JComboBox jcbTimeZones = new JComboBox();

  // Create a clock
  private WorldClock clock = new WorldClock();

  public WorldClockControl() {
    // Initialize jcbLocales with all available locales
    setAvailableLocales();

    // Initialize jcbTimeZones with all available time zones
    setAvailableTimeZones();

    // Initialize locale and time zone
    clock.setLocale(
      availableLocales[jcbLocales.getSelectedIndex()]);
    clock.setTimeZone(TimeZone.getTimeZone(
      availableTimeZones[jcbTimeZones.getSelectedIndex()]));

    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 1));
    panel1.add(new JLabel("Locale"));
    panel1.add(new JLabel("Time Zone"));
    JPanel panel2 = new JPanel();

    panel2.setLayout(new GridLayout(2, 1));
    panel2.add(jcbLocales, BorderLayout.CENTER);
    panel2.add(jcbTimeZones, BorderLayout.CENTER);

    JPanel panel3 = new JPanel();
    panel3.setLayout(new BorderLayout());
    panel3.add(panel1, BorderLayout.WEST);
    panel3.add(panel2, BorderLayout.CENTER);

    setLayout(new BorderLayout());
    add(panel3, BorderLayout.NORTH);
    add(clock, BorderLayout.CENTER);

    jcbLocales.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clock.setLocale(
          availableLocales[jcbLocales.getSelectedIndex()]);
      }
    });
    jcbTimeZones.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clock.setTimeZone(TimeZone.getTimeZone(
          availableTimeZones[jcbTimeZones.getSelectedIndex()]));
      }
    });
  }

  private void setAvailableLocales() {
    for (int i = 0; i < availableLocales.length; i++) {
      jcbLocales.addItem(availableLocales[i].getDisplayName() + " "
        + availableLocales[i].toString());
    }
  }

  private void setAvailableTimeZones() {
    // Sort time zones
    Arrays.sort(availableTimeZones);
    for (int i = 0; i < availableTimeZones.length; i++) {
      jcbTimeZones.addItem(availableTimeZones[i]);
    }
  }
}
