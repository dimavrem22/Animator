package cs3500.animator.provider.view;

import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.Position;
import cs3500.animator.provider.model.IReadAnimationModel;
import cs3500.animator.provider.model.Shape2DImpl;
import java.awt.Dimension;
import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.IShapeAndMotion;
import cs3500.animator.provider.model.ViewModel;
import cs3500.animator.provider.model.IShape2D;
import cs3500.animator.provider.model.Position2D;
import cs3500.animator.provider.model.Rgb;
import cs3500.animator.provider.model.Size2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * This is the main visual panel which contains all the features and methods needed to
 * construct the panel for the visual view. It contains the height, width, corner
 * values, speed, tick and timer which is used in order to paint the animation that is for
 * the visual view at various times for the different shapes.
 */
public class VisualPanel extends JPanel implements IAnimationPanels {
  protected IReadAnimationModel viewModel;
  protected final int cornerX;
  protected final int cornerY;
  protected final int canvasWidth;
  protected final int canvasHeight;
  protected int speed; /* will have to remove final tag in the subsequent implementation*/
  protected final int endTime;
  protected Timer timer;
  protected int currentTick;
  protected boolean looping;

  /**
   * This constructor simply takes in the view model and speed which allows us to access
   * the various features of the view model (height/width of canvas and corners) in order
   * to place the shapes accordingly on our panel. Additionally, it also constructs the timer
   * which is what allows us to refresh the panel accordingly which essentially produces the
   * animation (ie. shows the movement of shapes etc).
   * @param speed the speed at which we want our animation to render at
   * @param viewModel the view model from where we access all the information and render
   *                  accordingly
   */
  public VisualPanel(int speed, IReadAnimationModel viewModel) {
    this.viewModel = Objects.requireNonNull(viewModel);
    this.cornerX = this.viewModel.getXCanvas();
    this.cornerY = this.viewModel.getYCanvas();
    this.canvasWidth = this.viewModel.getWidthCanvas();
    this.canvasHeight = this.viewModel.getHeightCanvas();
    this.speed = speed;
    this.endTime = this.viewModel.getEndTime();
    this.timer = new Timer((int) ((double)1000 / (double) speed), new Animate(this));
    this.currentTick = 1;
    this.looping = false;
  }

  @Override
  public void startTimerRender() {
    this.timer.start();
  }

  @Override
  public void startTimer() {
    throw new UnsupportedOperationException("Operation not supported in this panel");
  }

  @Override
  public void rewind() {
    throw new UnsupportedOperationException("Operation not supported in this panel");
  }

  @Override
  public void stopPanel() {
    throw new UnsupportedOperationException("Operation not supported in this panel");
  }

  @Override
  public void fastForward(int speedUp) {
    throw new UnsupportedOperationException("Operation not supported in this panel");
  }

  @Override
  public void fastBackward(int slowDown) {
    throw new UnsupportedOperationException("Operation not supported in this panel");
  }

  @Override
  public void looping() {
    throw new UnsupportedOperationException("Operation not supported in this panel");
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(3000,3000);
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Map<String, IShapeAndMotion> allSM = this.viewModel.getInstructions();

    for (String name : allSM.keySet()) {
      IShapeAndMotion sm = allSM.get(name);

      IShape2D shape = sm.getShape();

      switch (shape.shapeName()) {
        case "rectangle":
          if ((sm.getStartTime() <= this.currentTick) && (sm.getEndTime() >= this.currentTick)) {
            Rectangle2D rect = new Rectangle2D.Double((shape.getPos().getX()
                    - (double)this.cornerX),
                ((shape.getPos().getY() - (double)this.cornerY)),
                shape.getSize().getWidth(), shape.getSize().getHeight());
            g2.setColor(new Color(shape.getRgb().getR(), shape.getRgb().getG(),
                    shape.getRgb().getB()));
            g2.fill(rect);
          }
          break;
        case "ellipse":
          if ((sm.getStartTime() <= this.currentTick) && (sm.getEndTime() >= this.currentTick)) {
            Ellipse2D elli = new Ellipse2D.Double(shape.getPos().getX() - (double)this.cornerX,
                ((shape.getPos().getY() - (double)this.cornerY)), shape.getSize().getWidth(),
                    shape.getSize().getHeight());
            g2.setColor(new Color(shape.getRgb().getR(), shape.getRgb().getG(),
                    shape.getRgb().getB()));
            g2.fill(elli);
          }
          break;
        default:
          JFrame frame = new JFrame();
          JOptionPane.showInternalMessageDialog(frame,
              String.format("%s is not a supported shape type!", shape.shapeName()),
              "Error Message", JOptionPane.ERROR_MESSAGE);
          System.exit(0);
      }
    }
  }

  /**
   * This class represents what should be done to each feature of the animation as the
   * time progresses.
   */
  protected static class Animate implements ActionListener {
    VisualPanel panel;

    Animate(VisualPanel panel) {
      this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      Map<String, IShapeAndMotion> allSM = this.panel.viewModel.getInstructions();
      for (String name : allSM.keySet()) {
        IShapeAndMotion sm = allSM.get(name);
        //System.out.println(name);

        IMotion m = null;

        try {
          m = sm.getMotionAtTime(this.panel.currentTick);
        }
        catch (IllegalArgumentException exception) {
          // don't do anything

        }
        if (m != null) {

          Position2D startPos = m.getStartPos();
          Position2D endPos = m.getEndPos();

          Size2D startSize = m.getStartSize();
          Size2D endSize = m.getEndSize();

          Rgb startColor = m.getStartColor();
          Rgb endColor = m.getEndColor();
          //System.out.println(endColor.getR());
          //System.out.println("PLEASe");


          Position2D newPos = new Position2D(this.interpolate(m.getStartTime(), m.getEndTime(),
              this.panel.currentTick, startPos.getX(), endPos.getX()),
              this.interpolate(m.getStartTime(), m.getEndTime(),
                  this.panel.currentTick, startPos.getY(), endPos.getY()));

          Size2D newSize = new Size2D((int) this.interpolate(m.getStartTime(),
              m.getEndTime(), this.panel.currentTick, startSize.getWidth(), endSize.getWidth()),
              (int) this.interpolate(m.getStartTime(),
                  m.getEndTime(), this.panel.currentTick, startSize.getHeight(),
                  endSize.getHeight()));

          Rgb newColor = new Rgb((int)this.interpolate(m.getStartTime(), m.getEndTime(),
              this.panel.currentTick, startColor.getR(), endColor.getR()),
              (int)this.interpolate(m.getStartTime(), m.getEndTime(),
                  this.panel.currentTick, startColor.getG(), endColor.getG()),
              (int)this.interpolate(m.getStartTime(), m.getEndTime(),
                  this.panel.currentTick, startColor.getB(), endColor.getB()));
          this.panel.viewModel.updateShape(name, newPos, newSize, newColor);

        }
      }


      this.panel.repaint();
      //System.out.println("PAINT");

      this.panel.currentTick += 1;

      if (this.panel.currentTick > this.panel.endTime) {
        if (this.panel.looping) {
          this.panel.currentTick = 1;
        }
        else {
          this.panel.timer.stop();
        }
      }
    }

    /**
     * interpolates each of the shape features.
     * @param ti initial time of animation
     * @param tf final time of animation
     * @param tc current time of animation
     * @param start start time of animation
     * @param end end time of animation
     * @return the interpolated value to find the values of each feature at a time
     */
    protected static double interpolate(int ti, int tf, int tc, double start, double end) {
      return (start * ((double)(tf - tc) / (double)(tf - ti)) +
          end * ((double)(tc - ti) / (double)(tf - ti)));
    }
  }

  protected void updateTimer() {
    System.out.println(String.format("delay: %d", this.timer.getInitialDelay()));
    this.timer.setDelay((int) ((double)1000 / (double) this.speed));
    //this.timer.restart();
    System.out.println(String.format("delay: %d", this.timer.getInitialDelay()));
  }
}
