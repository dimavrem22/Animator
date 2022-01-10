package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;

/**
 * Represents the textual view for the animation. The model's information is used to display the
 * state of the animation in a textual format.
 */
public class TextualView implements IView {

  private final IReadOnlyModel model;
  private final Appendable out;
  private final int tempo; // tempo is ticks per second

  /**
   * Constructs a textual view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if given model is null
   */
  public TextualView(IReadOnlyModel model) {
    this(model, 1);
  }

  /**
   * Constructs a textual view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if given model is null
   */
  public TextualView(IReadOnlyModel model, int tempo) {
    if (model == null || tempo < 0) {
      throw new IllegalArgumentException("Invalid Args");
    }
    this.model = model;
    this.out = new StringBuilder();
    this.tempo = tempo;
  }

  /**
   * Constructs a textual view with a given tempo
   * and a provided Appendable.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @throws IllegalArgumentException if given model is null
   */
  public TextualView(IReadOnlyModel model, Appendable out, int tempo) {
    if (model == null || out == null || tempo < 0) {
      throw new IllegalArgumentException("Invalid Arguments");
    }
    this.model = model;
    this.out = out;
    this.tempo = tempo;
  }

  /**
   * Constructs a textual view.
   *
   * @param model an {@code IReadOnlyModel} whose information is used to get animation state
   * @param out   the Appendable that will hold render output
   * @throws IllegalArgumentException if given model is null
   */
  public TextualView(IReadOnlyModel model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Argument should not be null.");
    }
    this.model = model;
    this.out = out;
    this.tempo = 1;
  }

  @Override
  public String toString() {
    String result = "";
    result += String.format("canvas %d %d %d %d\n", model.getLeftmostX(),
        model.getTopmostY(), model.getWidthBound(), model.getHeightBound());

    List<String> shapeNames = model.getAllShapeNames();
    List<IShape> shapes = model.getAllShapes();
    for (int i = 0; i < shapes.size(); i++) {

      result += "shape " + shapeNames.get(i) + " " + shapes.get(i).toString() + "\n";

      List<IMotion> motionsForShape = model.getMotions(shapeNames.get(i));
      for (int j = 0; j < motionsForShape.size(); j++) {
        result += this.getFrameInfo(shapeNames.get(i), motionsForShape.get(j));
        result += "\n";
      }


    }

    return result;

  }

  @Override
  public void render() throws IOException {
    out.append(this.toString());
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  @Override
  public void restart() {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  @Override
  public void exit() {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  @Override
  public void increaseTempo(int amount) {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  @Override
  public void decreaseTempo(int amount) {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  @Override
  public void stop() {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  /**
   * Gets the information regarding the provided shape's motion that includes the starting tick, x
   * coordinate, y coordinate, width, height, red color, green color, blue color of the shape
   * respectively then the same with the ending state with spacing in between. Example:
   * motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0
   * Pre-condition: The motion is for the associated shape with the given shape name
   * @param shapeName the name of the cs3500.animator.model.IShape in the model
   * @param motion    the cs3500.animator.model.IMotion associated with the shape
   * @return a text description of the provided animation regarding the provided shape name.
   * @throws IllegalArgumentException if the motion is not associated with the shape with the
   *                                  provided name, or any of the arguments are null.
   */
  private String getFrameInfo(String shapeName, IMotion motion) {
    if (shapeName == null || motion == null) {
      throw new IllegalArgumentException("Arguments should be non-null.");
    }

    String result = "motion " + shapeName + " ";
    IShape shape = model.shapeAtTick(shapeName, motion.getStartTick());
    double start = motion.getStartTick() * 1.0 / tempo;
    double end = motion.getLastTick() * 1.0 / tempo;

    result += String.format("%.2f %3d %3d %3d %3d %3d %3d %3d    %.2f %3d %3d %3d %3d %3d %3d %3d",
        start, shape.getCurrentPosition().getX(), shape.getCurrentPosition().getY(),
        shape.getWidth(), shape.getHeight(), shape.getColor().getRed(), shape.getColor().getGreen()
        , shape.getColor().getBlue(), end, motion.getFinalPosition().getX(),
        motion.getFinalPosition().getY(),
        motion.getFinalWidth(), motion.getFinalHeight(), motion.getFinalColor().getRed(),
        motion.getFinalColor().getGreen()
        , motion.getFinalColor().getBlue());
    return result;
  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    throw new UnsupportedOperationException("Can't perform this operation");
  }

  @Override
  public void toggleLooping() {
    throw new UnsupportedOperationException("Can't perform this operation");

  }

  @Override
  public void advance(ActionEvent e) {
    throw new UnsupportedOperationException("Cant perform operation in this view");
  }

  @Override
  public void toggleOutline() {
    throw new UnsupportedOperationException("Cant perform operation in this view");

  }

  @Override
  public void toggleDiscrete() {
    throw new UnsupportedOperationException("Can't perform operation");
  }

}
