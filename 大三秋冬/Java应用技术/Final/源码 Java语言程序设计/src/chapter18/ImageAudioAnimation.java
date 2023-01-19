package chapter18;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class ImageAudioAnimation extends JApplet {
  private final static int NUMBER_OF_NATIONS = 7;
  private int current = 0;
  private ImageIcon[] icons = new ImageIcon[NUMBER_OF_NATIONS];
  private AudioClip[] audioClips = new AudioClip[NUMBER_OF_NATIONS];
  private AudioClip currentAudioClip;

  private int[] delays =
    {48000, 54000, 59000, 54000, 59000, 31000, 68000};
  private Timer timer = new Timer(delays[0], new TimerListener());

  private JLabel jlblImageLabel = new JLabel();
  private JButton jbtResume = new JButton("Resume");
  private JButton jbtSuspend = new JButton("Suspend");
  private JComboBox jcboNations = new JComboBox(new Object[]
    {"Denmark", "Germany", "China", "India", "Norway", "UK", "US"});

  public ImageAudioAnimation() {
    // Load image icons and audio clips
    for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
      icons[i] = new ImageIcon(getClass().getResource(
        "/image/flag" + i + ".gif"));
      audioClips[i] = Applet.newAudioClip(
        getClass().getResource("/audio/anthem" + i + ".mid"));
    }

    JPanel panel = new JPanel();
    panel.add(jbtResume);
    panel.add(jbtSuspend);
    panel.add(new JLabel("Select"));
    panel.add(jcboNations);
    add(jlblImageLabel, BorderLayout.CENTER);
    add(panel, BorderLayout.SOUTH);

    jbtResume.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        start();
      }
    });
    jbtSuspend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stop();
      }
    });
    jcboNations.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stop();
        current = jcboNations.getSelectedIndex();
        presentNation(current);
        timer.start();
      }
    });

    timer.start();
    jlblImageLabel.setIcon(icons[0]);
    jlblImageLabel.setHorizontalAlignment(JLabel.CENTER);
    currentAudioClip = audioClips[0];
    currentAudioClip.play();
  }

  private class TimerListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      current = (current + 1) % NUMBER_OF_NATIONS;
      presentNation(current);
    }
  }

  private void presentNation(int index) {
    jlblImageLabel.setIcon(icons[index]);
    jcboNations.setSelectedIndex(index);
    currentAudioClip = audioClips[index];
    currentAudioClip.play();
    timer.setDelay(delays[index]);
  }

  public void start() {
    timer.start();
    currentAudioClip.play();
  }

  public void stop() {
    timer.stop();
    currentAudioClip.stop();
  }

  /** Main method */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("ImageAudioAnimation");

    // Create an instance of the applet
    ImageAudioAnimation applet = new ImageAudioAnimation();
    applet.init();

    // Add the applet instance to the frame
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the frame
    frame.setSize(400, 300);
    frame.setVisible(true);
  }
}
