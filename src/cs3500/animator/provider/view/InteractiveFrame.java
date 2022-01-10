package cs3500.animator.provider.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.Objects;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Contains all the buttons that will be present in this interactive frame as well as applies the
 * corresponding action/event listeners to each of the buttons based on their action command.
 */
public class InteractiveFrame extends VisualFrame {
  private JButton start = new JButton();
  private JButton pause = new JButton("Pause");
  private JButton restart = new JButton("Restart");
  private JButton resume = new JButton("Resume");
  private JButton fastForward = new JButton("x2");
  private JButton fastBackward = new JButton("x0.5");
  private JButton looper = new JButton("loop");


  /**
   * The frame constructor that is being extended by the visual frame which is why it
   * contains a super with the visual frame fields. We need the panel in order to create
   * the scrollable pane and then
   * we add the various buttons on the panel which is then added to the frame.
   * @param panel the panel where we want to place the interactive features
   * @param width the width of the panel
   * @param height the height of the panel
   */
  public InteractiveFrame(InteractivePanel panel, int width, int height) {
    super(Objects.requireNonNull(panel), width, height);
  }

  @Override
  public void createFrame() {
    JScrollPane scroll = new JScrollPane(this.panel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.add(scroll, BorderLayout.CENTER);
    start.setUI(new PlayButtonUI());
    JPanel panel = new JPanel();

    // set action command
    this.start.setActionCommand("start");
    panel.add(start);

    // set action command
    this.fastBackward.setActionCommand("fast backward");
    panel.add(this.fastBackward);

    // set action command
    this.resume.setActionCommand("resume");
    panel.add(resume);

    // set action command
    this.pause.setActionCommand("pause");
    panel.add(pause);

    // set action command
    this.fastForward.setActionCommand("fast forward");
    panel.add(this.fastForward);

    // set action command
    this.restart.setActionCommand("restart");
    panel.add(restart);

    // set action command
    this.looper.setActionCommand("loop");
    panel.add(this.looper);

    Dimension d = panel.getPreferredSize();

    this.add(panel, BorderLayout.SOUTH);
    System.out.println("ADDED PANEL");

    // title
    this.setTitle("Adam & Mirana's Animation");
    // frame size

    int ww = Math.max(this.width, (int)d.getWidth());
    if (ww == (int)d.getWidth()) {
      ww += 50;
    }

    this.setSize(ww, this.height + (int)d.getHeight());
    // frame close
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // so that the keys are able to work
    start.setFocusable(false);
    restart.setFocusable(false);
    resume.setFocusable(false);
    pause.setFocusable(false);
    fastForward.setFocusable(false);
    fastBackward.setFocusable(false);
    this.looper.setFocusable(false);
  }

  /**
   * Produces the play button design UI which is placed in the bottom left of our animation.
   */
  public class PlayButtonUI extends BasicButtonUI {

    @Override
    public Dimension getPreferredSize(JComponent c) {
      return new Dimension(30, 30);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
      Graphics2D g2 = (Graphics2D) g;
      AbstractButton b = (AbstractButton) c;
      ButtonModel model = b.getModel();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);//smoth borders
      if (model.isArmed()) {
        g2.setColor(Color.BLACK);
      } else {
        g2.setColor(Color.GRAY);
      }

      float thinkness = Math.min(c.getWidth(), c.getHeight()) * 0.1f;

      Shape shape = shapeFor(c, thinkness);
      g2.setStroke(new BasicStroke(thinkness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      g2.fill(shape);
      g2.draw(shape);

      super.paint(g2, c);
    }

    private Shape shapeFor(JComponent c, float thickness) {
      GeneralPath gp = new GeneralPath();

      double width = c.getWidth();
      double height = c.getHeight();
      double vPos = height / 2.0;
      double hPos = width - thickness;

      gp.moveTo(0.0 + thickness, 0.0 + thickness);
      gp.lineTo(hPos, vPos);
      gp.lineTo(0.0 + thickness, height - thickness);
      gp.closePath();

      return gp;
    }
  }

  /**
   * Adds the corresponding action listener to each of the buttons based on the key value pair.
   * The "value" in the pair is the method and so we pass in this as an action listener in
   * the configureButtonListener method in interactive visual animation view and then in this
   * method we just set that button to that action listener.
   * @param actionListener the action listener that we want to add to the button based on the
   *                       buttons' action command
   */
  public void addActions(ActionListener actionListener) {
    this.start.addActionListener(actionListener);
    this.restart.addActionListener(actionListener);
    this.pause.addActionListener(actionListener);
    this.resume.addActionListener(actionListener);
    this.fastBackward.addActionListener(actionListener);
    this.fastForward.addActionListener(actionListener);
    this.looper.addActionListener(actionListener);
  }
}
