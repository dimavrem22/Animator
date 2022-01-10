package cs3500.animator.model;

import cs3500.animator.model.IColor;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IShape;

/**
 * Class which represents an {@code cs3500.animator.model.IMotion} which holds the end state for an
 * {@IShape} within a given time interval and could set the {@code cs3500.animator.model.IShape} to
 * its final state (state at the last tick of the interval).
 */
public class Motion implements IMotion {

  private final String nameOfShape;
  private final IShape shape;
  private final IPosition finalPosition;
  private final int firstTick;
  private final int lastTick;
  private final int finalWidth;
  private final int finalHeight;
  private final IColor finalColor;

  /**
   * Constructs a cs3500.animator.model.Motion.
   *
   * @param nameOfShape   the name of the shape
   * @param shape         the shape itself
   * @param finalPosition the position it shall end up at in the final tick
   * @param firstTick     the first tick in the motion interval
   * @param lastTick      the last tick in the motion interval
   * @param finalWidth    the final width of the shape
   * @param finalHeight   the final height of the shape
   * @param finalColor    the final color of the shape
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public Motion(String nameOfShape, IShape shape, IPosition finalPosition, int firstTick,
      int lastTick,
      int finalWidth, int finalHeight, IColor finalColor)
      throws IllegalArgumentException {

    if (finalColor == null || nameOfShape == null || finalPosition == null || shape == null) {
      throw new IllegalArgumentException("Arguments should not be null.");
    }

    if (firstTick > lastTick) {
      throw new IllegalArgumentException("Invalid Ticks.");
    }

    this.nameOfShape = nameOfShape;
    this.shape = shape;
    this.finalPosition = finalPosition;
    this.firstTick = firstTick;
    this.lastTick = lastTick;
    this.finalWidth = finalWidth;
    this.finalHeight = finalHeight;
    this.finalColor = finalColor;

  }


  @Override
  public boolean overlaps(IMotion other) {

    if (other == null) {
      throw new IllegalArgumentException("cs3500.animator.model.Motion should be non-null.");
    }

    // If the two shapes are not the same, then motions don't overlap.
    if (!this.nameOfShape.equals(other.getShapeName())) {
      return false;
    }

    // Check for time overlap
    return other.getStartTick() < this.lastTick &&
        this.firstTick < other.getLastTick();
  }

  @Override
  public void goToFinalState() {
    this.shape.setColor(this.finalColor);
    this.shape.move(finalPosition);
    this.shape.changeDimensions(finalHeight - this.shape.getHeight(),
        finalWidth - this.shape.getWidth());
  }

  /**
   * Checks if there is any overlap in movements between two IMotions.
   *
   * @param other an cs3500.animator.model.IMotion object to check for overlaps.
   * @return true if no overlaps, false otherwise.
   */
  private boolean anyOverlap(IMotion other) {
    // Check position change overlap
    if ((this.causesChangeInPosition())) {
      return true;
    }

    // Check color change overlap
    if (this.causesChangeInColor() && other.causesChangeInColor()) {
      return true;
    }

    // Check dimensions change overlap
    return this.causesChangeInDimensions() && other.causesChangeInDimensions();
  }


  @Override
  public String getShapeName() {
    return this.nameOfShape;
  }

  @Override
  public IShape getShape() {
    return this.shape.clone();
  }

  @Override
  public IPosition getFinalPosition() {
    return this.finalPosition.clone();
  }

  @Override
  public int getStartTick() {
    return this.firstTick;
  }

  @Override
  public int getLastTick() {
    return this.lastTick;
  }

  @Override
  public int getFinalWidth() {
    return this.finalWidth;
  }

  @Override
  public int getFinalHeight() {
    return this.finalHeight;
  }

  @Override
  public IColor getFinalColor() {
    return this.finalColor;
  }

  @Override
  public boolean causesChangeInPosition() {
    return this.shape.getCurrentPosition().getX() != this.getFinalPosition().getX()
        || this.shape.getCurrentPosition().getY() != this.getFinalPosition().getY();
  }


  @Override
  public boolean causesChangeInColor() {
    return !(this.finalColor.equals(this.shape.getColor()));
  }

  @Override
  public boolean causesChangeInDimensions() {
    return this.finalWidth != this.shape.getWidth() ||
        this.finalHeight != this.shape.getHeight();
  }

  @Override
  public IMotion clone() {
    return new Motion(this.nameOfShape, this.shape.clone(), this.finalPosition, this.firstTick,
    this.lastTick, this.finalWidth, this.finalHeight, this.finalColor);
  }
}
