package chapter29;

import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import chapter15.StillClock;

public class ClockWithAudio extends JApplet {
  protected AudioClip[] hourAudio = new AudioClip[12];
  protected AudioClip[] minuteAudio = new AudioClip[60];

  // Create audio clips for pronouncing am and pm
  protected AudioClip amAudio =
    Applet.newAudioClip(this.getClass().getResource("audio/am.au"));
  protected AudioClip pmAudio =
    Applet.newAudioClip(this.getClass().getResource("audio/pm.au"));

  // Create a clock
  private StillClock clock = new StillClock();

  // Create a timer
  private Timer timer = new Timer(1000, new TimerListener());

  // Create a label to display time
  private JLabel jlblDigitTime = new JLabel("", JLabel.CENTER);

  /** Initialize the applet */
  public void init() {
    // Create audio clips for pronouncing hours
    for (int i = 0; i < 12; i++)
      hourAudio[i] = Applet.newAudioClip(
        this.getClass().getResource("audio/hour" + i + ".au"));

    // Create audio clips for pronouncing minutes
    for (int i = 0; i < 60; i++)
      minuteAudio[i] = Applet.newAudioClip(
        this.getClass().getResource("audio/minute" + i + ".au"));

    // Add clock and time label to the content pane of the applet
    add(clock, BorderLayout.CENTER);
    add(jlblDigitTime, BorderLayout.SOUTH);
  }

  /** Override the applet's start method */
  public void start() {
    timer.start(); // Resume clock
  }

  /** Override the applet's stop method */
  public void stop() {
    timer.stop(); // Suspend clock
  }

  private class TimerListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      clock.setCurrentTime();
      clock.repaint();
      jlblDigitTime.setText(clock.getHour() + ":" + clock.getMinute()
        + ":" + clock.getSecond());
      if (clock.getSecond() == 0)
        announceTime(clock.getHour(), clock.getMinute());
    }
  }

  /** Announce the current time at every minute */
  public void announceTime(int hour, int minute) {
    // Announce hour
    hourAudio[hour % 12].play();

    try {
      // Time delay to allow hourAudio play to finish
      Thread.sleep(1500);

      // Announce minute
      minuteAudio[minute].play();

      // Time delay to allow minuteAudio play to finish
      Thread.sleep(1500);
    }
    catch(InterruptedException ex) {
    }

    // Announce am or pm
    if (hour < 12)
      amAudio.play();
    else
      pmAudio.play();
  }

  public static void main(String[] args) {
    ClockWithAudio applet = new ClockWithAudio();
    applet.init();
    applet.start();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("ClockWithAudio");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setSize(400, 320);
    frame.setVisible(true);
  }
}
