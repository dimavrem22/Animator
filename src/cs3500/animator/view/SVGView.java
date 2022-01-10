package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents the SVG view for the animation. The model's information is used to display the state
 * of the animation in an SVG format.
 */
public class SVGView implements IView {

  private final IReadOnlyModel model;
  private int tempo; // tempo is ticks per second
  private final Appendable out;

  /**
   * Constructs a SVG view with tempo of 1 tick per second.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public SVGView(IReadOnlyModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Argument should not be null.");
    }
    this.model = model;
    this.tempo = 1;
    this.out = new StringBuilder();
  }


  /**
   * Constructs a SVG view with tempo of 1 tick per second and a provided Appendable.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public SVGView(IReadOnlyModel model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Argument should not be null.");
    }
    this.model = model;
    this.tempo = 1;
    this.out = out;
  }

  /**
   * Constructs a SVG view with a given tempo and a provided Appendable.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public SVGView(IReadOnlyModel model, Appendable out, int tempo) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Argument should not be null.");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("Tempo cannot be negative");
    }
    this.model = model;
    this.tempo = tempo;
    this.out = out;
  }

  /**
   * Constructs a SVG view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @param tempo for the animation representing the number of ticks per second
   * @throws IllegalArgumentException if given model is null
   */
  public SVGView(IReadOnlyModel model, int tempo) {
    if (model == null) {
      throw new IllegalArgumentException("Argument should not be null.");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("Tempo cannot be negative");
    }
    this.model = model;
    this.tempo = tempo;
    this.out = new StringBuilder();
  }


  @Override
  public String toString() {

    StringBuilder result = new StringBuilder();

    result.append("<svg width=\"" + (model.getWidthBound() + model.getLeftmostX())
        + "\" height=\"" + (model.getHeightBound() + model.getTopmostY()) + "\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    // make svg tags for the shapes
    for (String name : model.getAllShapeNames()) {

      IShape shape = model.getShape(name);

      if (shape.toString().equals("rectangle")) {
        result.append(makeShapeTag(name, shape));
        result.append(makeAnimateTags(name));
        result.append("</rect>\n");
      } else if (shape.toString().equals("ellipse")) {
        result.append(makeShapeTag(name, shape));
        result.append(makeAnimateTags(name));
        result.append("</ellipse>\n");
      }
      else {

        IShape rect1 = new Rectangle(shape.getWidth() / 2,
        shape.getHeight(), shape.getColor(), shape.getCurrentPosition(),
            shape.getOpacity());

        IShape rect2 = new Rectangle(shape.getWidth(),
            shape.getHeight() / 2, shape.getColor(), shape.getCurrentPosition(),
            shape.getOpacity());

        result.append(makeShapeTag(name, rect1));
        result.append(makeAnimateTags(name, 0, rect2.getWidth() / 4));
        result.append("</rect>\n");

        result.append(makeShapeTag(name, rect2));
        result.append(makeAnimateTags(name,rect1.getHeight() / 4,0));
        result.append("</rect>\n");



      }

    }

    result.append("</svg>");
    return result.toString();
  }

  @Override
  public void render() throws IOException {
    out.append(this.toString());
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void restart() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void exit() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void increaseTempo(int amount) {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void decreaseTempo(int amount) {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void stop() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void toggleLooping() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void advance(ActionEvent e) {
    throw new UnsupportedOperationException("Cant perform operation in this view");
  }

  @Override
  public void toggleOutline() {
    throw new UnsupportedOperationException("Can't perform operation");
  }

  @Override
  public void toggleDiscrete() {
    throw new UnsupportedOperationException("Can't perform operation");
  }

  /**
   * Creates the animate tags in SVG.
   *
   * @param name the name of the shape.
   * @return the animate tags represents motions for the shape with the particular name.
   */
  private String makeAnimateTags(String name, int shiftDown, int shiftRight) {

    if (name == null) {
      throw new IllegalArgumentException("Arguments should be non-null.");
    }

    String type = model.getShape(name).toString();

    String x;
    String y;
    String width;
    String height;

    switch (type) {

      case "rectangle":

        x = "x";
        y = "y";
        width = "width";
        height = "height";
        break;

      case "plus":

        x = "x";
        y = "y";
        width = "width";
        height = "height";
        break;

      case "ellipse":
        x = "cx";
        y = "cy";
        width = "rx";
        height = "ry";
        break;

      default:
        throw new IllegalArgumentException("Type of shape not found.");

    }

    StringBuilder result = new StringBuilder();
    // Go through the shape's motions
    for (IMotion motion : model.getMotions(name)) {

      //IShape initShape = motion.getShape();
      IShape initShape = model.shapeAtTick(name, motion.getStartTick());

      // Use default tick rate
      int begin = motion.getStartTick() * (1000 / tempo);
      int duration = (motion.getLastTick() - motion.getStartTick()) * (1000
          / tempo);

      // Check if there is width change
      if (initShape.getWidth() != motion.getFinalWidth()) {


        if ((shiftRight == 0 && shiftDown != 0) || (shiftRight == 0 && shiftDown == 0)) {
          result.append(
              generateAnimateTag(begin, duration, width, width.equals("width") ? initShape.getWidth()
                  : (initShape.getWidth() / 2), width.equals("width") ? motion.getFinalWidth()
                  : (motion.getFinalWidth() / 2), false));
        }
        else {
          result.append(
              generateAnimateTag(begin, duration, width, width.equals("width") ? initShape.getWidth() / 2
                  : (initShape.getWidth() / 2), width.equals("width") ? motion.getFinalWidth() / 2
                  : (motion.getFinalWidth() / 2), false));


          int change = motion.getFinalWidth() - initShape.getWidth();
          result.append(generateAnimateTag(begin, duration, y, initShape.getCurrentPosition().getY(),
              motion.getFinalPosition().getY() - change/4, false));

        }



      }

      if (initShape.getHeight() != motion.getFinalHeight()) {

        if ((shiftRight != 0 && shiftDown == 0) || (shiftRight == 0 && shiftDown == 0)) {

          result.append(generateAnimateTag(begin, duration, height,
              height.equals("height") ? initShape.getHeight()
                  : (initShape.getHeight() / 2), height.equals("height") ? motion.getFinalHeight()
                  : (motion.getFinalHeight() / 2), false));
        }
        else {
          result.append(generateAnimateTag(begin, duration, height,
              height.equals("height") ? initShape.getHeight() / 2
                  : (initShape.getHeight() / 2), height.equals("height") ? motion.getFinalHeight() / 2
                  : (motion.getFinalHeight() / 2), false));


          int change = motion.getFinalHeight() - initShape.getHeight();
          result.append(generateAnimateTag(begin, duration, x, initShape.getCurrentPosition().getX(),
              motion.getFinalPosition().getX() - change/4, false));

        }
      }

      if (initShape.getCurrentPosition().getX() != motion.getFinalPosition().getX() || shiftRight != 0) {

        result.append(generateAnimateTag(begin, duration, x, initShape.getCurrentPosition().getX() + shiftRight,
            motion.getFinalPosition().getX() + shiftRight, false));
      }

      if (initShape.getCurrentPosition().getY() != motion.getFinalPosition().getY() || shiftDown != 0) {

        result.append(generateAnimateTag(begin, duration, y, initShape.getCurrentPosition().getY() + shiftDown,
            motion.getFinalPosition().getY() + shiftDown, false));

      }

      if (initShape.getColor().getGreen() != motion.getFinalColor().getGreen() ||
          initShape.getColor().getRed() != motion.getFinalColor().getRed() ||
          initShape.getColor().getBlue() != motion.getFinalColor().getBlue()) {

        Color converter1 = new Color(initShape.getColor().getRed(), initShape.getColor().getGreen(),
            initShape.getColor().getBlue()
        );

        Color converter2 = new Color(motion.getFinalColor().getRed(),
            motion.getFinalColor().getGreen(),
            motion.getFinalColor().getBlue()
        );

        result.append(generateAnimateTag(begin, duration, "fill",
            converter1.getRGB(), converter2.getRGB(), true));

      }
    }

    return result.toString();

  }


  /**
   * Creates the animate tags in SVG.
   *
   * @param name the name of the shape.
   * @return the animate tags represents motions for the shape with the particular name.
   */
  private String makeAnimateTags(String name) {
    return makeAnimateTags(name,0,0);

  }

  /**
   * Generates the <animate /> tag.
   *
   * @param begin         the time to begin in milliseconds
   * @param duration      the duration of the animation
   * @param attributeName the name of the attribute to modify
   * @param from          the inital value of the attribute
   * @param to            the final value of the attribute
   * @param colors        true if colors are being modified, false otherwise
   * @return the String representation of the <animate /> tag
   *
   * @throws IllegalArgumentException if the attributeName is Null
   */
  private String generateAnimateTag(int begin, int duration,
      String attributeName, int from, int to, boolean colors) throws IllegalArgumentException {
    if (attributeName == null) {
      throw new IllegalArgumentException("Null arg");
    }

    if (!colors) {

      return String.format(
          "\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\""
              + " attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\"/>\n",
          begin, duration, attributeName, from, to);

    } else {
      Color fromColor = new Color(from);
      int fromRed = fromColor.getRed();
      int fromGreen = fromColor.getGreen();
      int fromBlue = fromColor.getBlue();

      Color toColor = new Color(to);
      int toRed = toColor.getRed();
      int toGreen = toColor.getGreen();
      int toBlue = toColor.getBlue();

      return String.format(
          "\t<animate attributeType=\"css\" begin=\"%dms\" dur=\"%dms\" "
              + "attributeName=\"%s\" from=\"rgb(%d,%d,%d)\" "
              + "to=\"rgb(%d,%d,%d)\" fill=\"freeze\"/>\n",
          begin, duration, "fill", fromRed,
          fromGreen, fromBlue,
          toRed, toGreen,
          toBlue);

    }

  }


  /**
   * Creates the SVG tag for a given shape.
   *
   * @param name  the name of the shape
   * @param shape the cs3500.animator.model.IShape whose attributes we need in the tag
   * @return an SVG tag representing a shape and its attributes constructed from the name and the
   *         shape provided.
   *
   * @throws IllegalArgumentException if arguments are null
   */
  private String makeShapeTag(String name, IShape shape) throws IllegalArgumentException {

    if (name == null || shape == null) {
      throw new IllegalArgumentException("Null Args");
    }

    StringBuilder result = new StringBuilder();
    String type = shape.toString();

    if (type.equals("rectangle")) {
      result.append(String.format(
          "<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill="
              + "\"rgb(%d,%d,%d)\" visibility=\"%s\">\n",
          name, shape.getCurrentPosition().getX(),
          shape.getCurrentPosition().getY(), shape.getWidth(),
          shape.getHeight(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue(), "visible"));
    } else if (type.equals("ellipse")) {
      result.append(String.format(
          "<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" "
              + "fill=\"rgb(%d,%d,%d)\" visibility=\"%s\">\n",
          name, shape.getCurrentPosition().getX(),
          shape.getCurrentPosition().getY(), (int) (shape.getWidth() * 0.5),
          (int) (shape.getHeight() * 0.5), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue(), "visible"));

    }
    else {
      result.append(String.format(
          "<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill="
              + "\"rgb(%d,%d,%d)\" visibility=\"%s\">\n",
          name, shape.getCurrentPosition().getX(),
          shape.getCurrentPosition().getY(), shape.getWidth(),
          shape.getHeight(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue(), "visible"));
    }

    return result.toString();

  }


}
