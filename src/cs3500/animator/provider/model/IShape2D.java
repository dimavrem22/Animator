package cs3500.animator.provider.model;

/**
 * Represents a 2D shape on a 2D plane, with appropriate operations to effectuate on the shape.
 */
public interface IShape2D {

  /**
   * Returns the color of this shape.
   * @return the rgb color of this shape
   */
  public Rgb getRgb();

  /**
   * Returns the size of this shape.
   * @return the width and height of this shape
   */
  public Size2D getSize();

  /**
   * Returns the 2 dimensional position of this shape.
   * @return the x, y coordinates of this shape in a 2D plane
   */
  public Position2D getPos();

  /**
   * Moves this shape by the given coordinates.
   * @param newPos the x, y coordinates of the new position of the shape
   */
  public void move(Position2D newPos);

  /**
   * Resize this shape by the new width and height.
   * @param newSize the new width and height of this 2dShape
   */
  public void resize(Size2D newSize);

  /**
   * Change the color of this shape by the new red-green-blue color matrix.
   * @param newColor the r, g, and b values of the new color of this shape
   */
  public void colorize(Rgb newColor);

  /**
   * Copies this shapes and all its fields.
   * @return A new object copy of this shape
   */
  public IShape2D copyIShape2D();

  /**
   * Returns the geometric name of this shape.
   * @return a string of the name of the geometric shape
   */
  public String shapeName();
}