package cs3500.animator.model;

/**
 * Class representing 2D cartesian coordinates with integer precision. Coordinates can be retrieved
 * and moved.
 */
public class Position implements IPosition {

  private int x;
  private int y;

  /**
   * Constructs a cs3500.animator.model.Position with integer precision.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }


  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setPosition(IPosition position) {
    if (position == null) {
      throw new IllegalArgumentException("cs3500.animator.model.Position must be non-null");
    }

    this.x = position.getX();
    this.y = position.getY();

  }

  @Override
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public IPosition clone() {
    return new Position(this.x, this.y);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof IPosition)) {
      return false;
    }
    IPosition otherPosition = (IPosition) other;
    return this.x == otherPosition.getX() && this.y == otherPosition.getY();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(x) * 10 + Integer.hashCode(y);
  }
}
