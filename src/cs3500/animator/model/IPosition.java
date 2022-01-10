package cs3500.animator.model;

/**
 * Represents a 2D cartesian coordinate with integer precision with its common functionality.
 */
public interface IPosition {

  /**
   * Returns the x-coordinate of this cs3500.animator.model.IPosition.
   */
  int getX();

  /**
   * Returns the y-coordinate of this cs3500.animator.model.IPosition.
   */
  int getY();

  /**
   * Sets the location of this point to the specified position.
   *
   * @param position the cs3500.animator.model.IPosition to change to
   * @throws IllegalArgumentException if position is null
   */
  void setPosition(IPosition position) throws IllegalArgumentException;


  /**
   * Changes the point to have the specified x and y coordinates.
   *
   * @param x the x coordinate to change to.
   * @param y the y coordinate to change to.
   */
  void setPosition(int x, int y);

  /**
   * Creates a new cs3500.animator.model.IPosition with the same contents as this
   * cs3500.animator.model.IPosition.
   *
   * @return a copy of this cs3500.animator.model.IPosition
   */
  IPosition clone();


}
