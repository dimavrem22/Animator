package cs3500.animator.view;

import cs3500.animator.model.IColor;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Class representing a drawing panel. This is where all of the components of the visual animation
 * are added to. The class will help provide the user with a visual view of the animation.
 */
public class DrawingPanel extends JPanel {

  private final IReadOnlyModel model;
  private boolean outlined;
  private int tick;

  /**
   * Constructs a DrawingPanel.
   *
   * @param model the model to use.
   * @param tick  the current tick.
   */
  public DrawingPanel(IReadOnlyModel model, int tick) {
    if (model == null) {
      throw new IllegalArgumentException("Argument should eb non-null");
    } else if (tick < 0 ) {
      throw new IllegalArgumentException("Tick can not be negative");
    }
    this.tick = tick;
    this.model = model;
    this.outlined = false;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    for (String name : model.getAllShapeNames()) {
      IShape currShape = model.shapeAtTick(name, this.tick);
      String type = currShape.toString();
      IPosition currPosition = currShape.getCurrentPosition();
      int x = currPosition.getX();
      int y = currPosition.getY();
      IColor currColor = currShape.getColor();
      int red = currColor.getRed();
      int green = currColor.getGreen();
      int blue = currColor.getBlue();

      g.setColor(new Color(red, green, blue));

      if (!this.outlined) {
        if (type.equals("rectangle")) {
          g.fillRect(x, y, currShape.getWidth(), currShape.getHeight());
        } else if (type.equals("ellipse")) {
          g.fillOval(x, y, currShape.getWidth(), currShape.getHeight());
        }
        else {
          g.fillRect(x, y, currShape.getWidth(), currShape.getHeight() / 2);
          g.fillRect(x + currShape.getWidth() / 4,y - currShape.getHeight() / 4,
              currShape.getWidth() / 2,currShape.getHeight());
        }
      }

      else {
        if (type.equals("rectangle")) {
          g.drawRect(x, y, currShape.getWidth(), currShape.getHeight());

        } else if (type.equals("ellipse")) {
          g.drawOval(x, y, currShape.getWidth(), currShape.getHeight());
        }
        else {

          int currWidth = currShape.getWidth();
          int currHeight = currShape.getHeight();
          IPosition currPos = currShape.getCurrentPosition();
          int[] xCoords = new int[12];
          int [] yCoords = new int[12];
          xCoords[0] = (int) Math.round(currPos.getX() + 0.75 * currWidth);
          yCoords[0] = currPos.getY();

          xCoords[1] = (int) Math.round(currPos.getX() + 0.25 * currWidth);
          yCoords[1] = currPos.getY();

          xCoords[2] = (int) Math.round(currPos.getX() + 0.25 * currWidth);
          yCoords[2] = (int) Math.round(currPos.getY() + 0.25 * currHeight);

          xCoords[3] = currPos.getX();
          yCoords[3] = (int) Math.round(currPos.getY() + 0.25 * currHeight);

          xCoords[4] = currPos.getX();
          yCoords[4] = (int) Math.round(currPos.getY() + 0.75 * currHeight);

          xCoords[5] = (int) Math.round(currPos.getX() + 0.25 * currWidth);
          yCoords[5] = (int) Math.round(currPos.getY() + 0.75 * currHeight);

          xCoords[6] = (int) Math.round(currPos.getX() + 0.25 * currWidth);
          yCoords[6] = currPos.getY() + currHeight;

          xCoords[7] = (int) Math.round(currPos.getX() + 0.75 * currWidth);
          yCoords[7] = currPos.getY() + currHeight;

          xCoords[8] = (int) Math.round(currPos.getX() + 0.75 * currWidth);
          yCoords[8] = (int) Math.round(currPos.getY() + 0.75 * currHeight);

          xCoords[9] = currPos.getX() + currWidth;
          yCoords[9] = (int) Math.round(currPos.getY() + 0.75 * currHeight);

          xCoords[10] = currPos.getX() + currWidth;
          yCoords[10] = (int) Math.round(currPos.getY() + 0.25 * currHeight);

          xCoords[11] = (int) Math.round(currPos.getX() + 0.75 * currWidth);
          yCoords[11] = (int) Math.round(currPos.getY() + 0.25 * currHeight);


          g.drawPolygon(xCoords,yCoords,12);

        }
      }

    }

  }

  /**
   * Sets the tick of this DrawingPanel.
   * @param tick the tick to set the panel to.
   */
  public void setTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick cannot be negative");
    }
    this.tick = tick;
  }


  /**
   * Sets the outlined instance variable to the passed in boolean.
   * @param doOutlined  boolean which determines if outline mode is on or not
   */
  public void setOutlined(boolean doOutlined) {
    this.outlined = doOutlined;
  }
}
