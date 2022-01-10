package cs3500.animator.model;

/**
 * Represents a motion within the animation that acts upon a {@code cs3500.animator.model.IShape}.
 */
public interface IMotion {

  /**
   * Determines if the given {@code cs3500.animator.model.IMotion} overlaps with this
   * cs3500.animator.model.IMotion.
   *
   * @return true if the IMotions overlap, false otherwise
   */
  boolean overlaps(IMotion other);

  /**
   * Takes the cs3500.animator.model.IMotion's shape to its final state in the motion.
   */
  void goToFinalState();


  /**
   * Gets the name of the {@code cs3500.animator.model.IShape} of this
   * cs3500.animator.model.IMotion.
   *
   * @return name of the cs3500.animator.model.IShape associated with this
   *         cs3500.animator.model.IMotion.
   */
  String getShapeName();

  /**
   * Gets the {@code cs3500.animator.model.IShape} of this cs3500.animator.model.IMotion.
   *
   * @return the cs3500.animator.model.IShape involved with the cs3500.animator.model.IMotion.
   */
  IShape getShape();

  /**
   * Gets the final position value associated with this cs3500.animator.model.IMotion.
   *
   * @return the final cs3500.animator.model.IPosition of this motion.
   */
  IPosition getFinalPosition();

  /**
   * Gets the starting tick value of the cs3500.animator.model.IMotion.
   *
   * @return the starting tick value
   */
  int getStartTick();

  /**
   * Gets the last tick value for this cs3500.animator.model.IMotion.
   *
   * @return the last tick value
   */
  int getLastTick();

  /**
   * Gets the final width of the {@code cs3500.animator.model.IShape} once this
   * cs3500.animator.model.IMotion is completed.
   *
   * @return the final width of the {@code cs3500.animator.model.IShape} part of the
   *        cs3500.animator.model.IMotion
   */
  int getFinalWidth();

  /**
   * Gets the final height of the {@code cs3500.animator.model.IShape} once this
   * cs3500.animator.model.IMotion is completed.
   *
   * @return the final height of the {@code cs3500.animator.model.IShape} part of the
   *        cs3500.animator.model.IMotion
   */
  int getFinalHeight();


  /**
   * Gets the final {@code cs3500.animator.model.IColor} for this cs3500.animator.model.IMotion.
   *
   * @return the {@code cs3500.animator.model.IColor} at the end of the
   *        cs3500.animator.model.IMotion
   */
  IColor getFinalColor();

  /**
   * Determines if this {@code cs3500.animator.model.IMotion} causes a change in position.
   *
   * @return true if causes a change in position, false otherwise
   */
  boolean causesChangeInPosition();

  /**
   * Determines if this {@code cs3500.animator.model.IMotion} causes a change in color.
   *
   * @return true if causes a change in color, false otherwise
   */
  boolean causesChangeInColor();

  /**
   * Determines if this {@code cs3500.animator.model.IMotion} causes a change in dimensions.
   *
   * @return true if causes a change in dimensions, false otherwise
   */
  boolean causesChangeInDimensions();

  /**
   * Creates a copy of this IMotion.
   *
   * @return a copy of this IMotion.
   */
  IMotion clone();


}
