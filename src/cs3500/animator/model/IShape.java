package cs3500.animator.model;

/**
 * Interface which represents the common functionality for a shape.
 */
public interface IShape {


  /**
   * Move this shape to the given position.
   *
   * @param endPosition the cs3500.animator.model.IPosition to be moved to
   * @throws IllegalArgumentException if the given position is null.
   */
  void move(IPosition endPosition) throws IllegalArgumentException;

  /**
   * Changes the dimensions of the shape by the specified change in height and change in width.
   *
   * @param changeInHeight the change in height of this shape.
   * @param changeInWidth  the change in width of this shape.
   * @throws IllegalArgumentException if any dimension changes cause cause any of the dimensions of
   *                                  the shape to be less than or equal to 0.
   */
  void changeDimensions(int changeInHeight, int changeInWidth)
      throws IllegalArgumentException;


  /**
   * Gets the current position of this shape.
   *
   * @return the current cs3500.animator.model.IPosition of this shape
   */
  IPosition getCurrentPosition();

  /**
   * Sets the color of this shape to the specified one.
   *
   * @param color the color to change this shape to.
   * @throws IllegalArgumentException if color is null
   */
  void setColor(IColor color) throws IllegalArgumentException;


  /**
   * Gets the current cs3500.animator.model.IColor of this shape.
   *
   * @return the current cs3500.animator.model.IColor of this shape
   */
  IColor getColor();


  /**
   * Gets the width of this shape.
   *
   * @return the width of this shape
   */
  int getWidth();

  /**
   * Gets the height of this shape.
   *
   * @return the width of this shape.
   */
  int getHeight();

  /**
   * Gets the opacity of this shape.
   *
   * @return the opacity of this shape.
   */
  double getOpacity();

  /**
   * Sets the opacity of this shape to the specified value. Note: An opacity of 0 represents an
   * invisible shape.
   *
   * @param opacity opacity value ranging from 0 to 1 inclusive.
   * @throws IllegalArgumentException if opacity is out of range bounds.
   */
  void setOpacity(double opacity) throws IllegalArgumentException;

  /**
   * Creates a new cs3500.animator.model.IShape with the same contents as this
   * cs3500.animator.model.IShape.
   *
   * @return a copy of this cs3500.animator.model.IShape
   */
  IShape clone();

  /**
   * Creates shape of the same type as implementing class with the given parameters.
   *
   * @param width    the width of the new shape
   * @param height   the height of the new shape
   * @param color    the color of the new shape
   * @param position the position of the new shape
   * @param opacity  the opacity of the new shape
   * @return the cs3500.animator.model.IShape with the given arguments
   */
  IShape create(int width, int height, IColor color, IPosition position, double opacity);

}
