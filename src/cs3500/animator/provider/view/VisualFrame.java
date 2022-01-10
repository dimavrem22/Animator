package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * The frame for the visual view where we simply create the frame itself and make it visible.
 * Contains fields width, height and panel so that we are able to set the dimensions as well
 * as pass in the panel which is used in order to create the scrollable panel for the frame.
 */
public class VisualFrame extends JFrame {
  protected final int width;
  protected final int height;
  protected JComponent panel;

  /**
   * Sets the dimensions of the frame as well as takes in the panel which is what creates
   * the scrollable in the panel in the frame.
   * @param panel the panel where we add the scroll features to
   * @param width the width of the frame
   * @param height the height of the frame
   */
  public VisualFrame(JComponent panel, int width, int height) {
    this.width = width;
    this.height = height;
    this.panel = Objects.requireNonNull(panel);
  }

  /**
   * Creates a frame of the provided width and height. The frame also supports scrolling bars,
   * with a title and exits on the exit button at the top left corner of the window.
   */
  public void createFrame() {
    JScrollPane scroll = new JScrollPane(this.panel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.add(scroll, BorderLayout.CENTER);
    // title
    this.setTitle("Adam & Mirana's Animation");
    // frame size
    this.setSize(width, height);
    // frame close
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Sets the frame's visibility on.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
