package chapter31;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.*;
import java.text.*;

public class CalendarPanel extends JPanel {
  // The header label
  private JLabel jlblHeader = new JLabel(" ", JLabel.CENTER);

  // Maximun number of labels to display day names and days
  private JLabel[] jlblDay = new JLabel[49];

  private java.util.Calendar calendar;
  private int month;  // The specified month
  private int year;  // The specified year

  // Panel jpDays to hold day names and days
  private JPanel jpDays = new JPanel(new GridLayout(0, 7));

  public CalendarPanel() {
    // Create labels for displaying days
    for (int i = 0; i < 49; i++) {
      jlblDay[i] = new JLabel();
      jlblDay[i].setBorder(new LineBorder(Color.black, 1));
      jlblDay[i].setHorizontalAlignment(JLabel.RIGHT);
      jlblDay[i].setVerticalAlignment(JLabel.TOP);
    }

    // Place header and calendar body in the panel
    this.setLayout(new BorderLayout());
    this.add(jlblHeader, BorderLayout.NORTH);
    this.add(jpDays, BorderLayout.CENTER);

    // Set current month and year
    calendar = new GregorianCalendar();
    month = calendar.get(Calendar.MONTH);
    year = calendar.get(Calendar.YEAR);
    updateCalendar();

    // Show calendar
    showHeader();
    showDays();
  }

  /** Update the header based on locale */
  private void showHeader() {
    SimpleDateFormat sdf =
      new SimpleDateFormat("MMMM yyyy", getLocale());
    String header = sdf.format(calendar.getTime());
    jlblHeader.setText(header);
  }

  /** Update the day names based on locale */
  private void showDayNames() {
    DateFormatSymbols dfs = new DateFormatSymbols(getLocale());
    String dayNames[] = dfs.getWeekdays();

    // jlblDay[0], jlblDay[1], ..., jlblDay[6] for day names
    for (int i = 0; i < 7; i++) {
      jlblDay[i].setText(dayNames[i + 1]);
      jlblDay[i].setHorizontalAlignment(JLabel.CENTER);
      jpDays.add(jlblDay[i]); // Add to jpDays
    }
  }

  /** Display days */
  public void showDays() {
    jpDays.removeAll(); // Remove all labels from jpDays

    showDayNames(); // Display day names

    // Get the day of the first day in a month
    int startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

    // Fill the calendar with the days before this month
    Calendar cloneCalendar = (Calendar)calendar.clone();
    cloneCalendar.add(Calendar.DATE, -1); // Becomes preceding month
    int daysInPrecedingMonth = cloneCalendar.getActualMaximum(
          Calendar.DAY_OF_MONTH);

    for (int i = 0; i < startingDayOfMonth - 1; i++) {
      jlblDay[i + 7].setForeground(Color.LIGHT_GRAY);
      jlblDay[i + 7].setText(daysInPrecedingMonth -
        startingDayOfMonth + 2 + i + "");
      jpDays.add(jlblDay[i + 7]); // Add to jpDays
    }

    // Display days of this month
    int daysInCurrentMonth = calendar.getActualMaximum(
          Calendar.DAY_OF_MONTH);
    for (int i = 1; i <= daysInCurrentMonth; i++) {
      jlblDay[i - 2 + startingDayOfMonth + 7].
        setForeground(Color.black);
      jlblDay[i - 2 + startingDayOfMonth + 7].setText(i + "");
      jpDays.add(jlblDay[i - 2 + startingDayOfMonth + 7]);
    }

    // Fill the calendar with the days after this month
    int j = 1;
    for (int i = daysInCurrentMonth - 1 + startingDayOfMonth + 7;
        i % 7 != 0; i++) {
      jlblDay[i].setForeground(Color.LIGHT_GRAY);
      jlblDay[i].setText(j++ + "");
      jpDays.add(jlblDay[i]); // Add to jpDays
    }

    jpDays.repaint(); // Repaint the labels in jpDays
  }

  /** Set the calendar to the first day of the
    * specified month and year */
  private void updateCalendar() {
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DATE, 1);
  }

  /** Return month */
  public int getMonth() {
    return month;
  }

  /** Set a new month */
  public void setMonth(int newMonth) {
    month = newMonth;
    updateCalendar();
    showHeader();
    showDays();
  }

  /** Return year */
  public int getYear() {
    return year;
  }

  /** Set a new year */
  public void setYear(int newYear) {
    year = newYear;
    updateCalendar();
    showHeader();
    showDays();
  }

  /** Set a new locale */
  public void changeLocale(Locale newLocale) {
    setLocale(newLocale);
    showHeader();
    showDays();
  }
}
