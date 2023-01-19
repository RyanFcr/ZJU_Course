package chapter36;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import chapter15.ImageViewer;

public class MyImageCellRenderer extends DefaultTableCellRenderer {
  /** Override this method in DefaultTableCellRenderer */
  public Component getTableCellRendererComponent
      (JTable table, Object value, boolean isSelected,
       boolean isFocused, int row, int column) {
    Image image = ((ImageIcon)value).getImage();
    ImageViewer imageViewer = new ImageViewer(image);

    return imageViewer;
  }
}
