package cs3500.animator.model;

/**
 * Represents a Plus sign shape. Extends AShape to inherit common functionality of shapes.
 */
public class Plus extends AShape {

  /**
   * Constructs a shape with the provided settings.
   *
   * @param width    the width of the shape
   * @param height   the height of the shape
   * @param color    the color of the shape
   * @param position the position of the shape
   * @param opacity  the opacity of the shape
   * @throws IllegalArgumentException if any of the arguments provided are null
   */
  public Plus(int width, int height, IColor color, IPosition position, double opacity)
      throws IllegalArgumentException {
    super(width, height, color, position, opacity);
  }

  @Override
  public String toString() {
    return "plus";
  }

  @Override
  public IShape clone() {
    return new Plus(this.getWidth(), this.getHeight(),
        this.getColor(), this.getCurrentPosition(), this.getOpacity());
  }

  @Override
  public IShape create(int width, int height, IColor color, IPosition position, double opacity) {
    return new Plus(width, height, color, position.clone(), opacity);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.getHeight()) * 100 + Integer.hashCode(this.getWidth()) * 10
        + Double.hashCode(this.getOpacity()) * 10000 + this.getColor().hashCode() * 100000 +
        this.getCurrentPosition().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Plus)) {
      return false;
    } else {
      Plus other = (Plus) obj;

      return this.getColor().equals(other.getColor()) && this.getHeight() == other.getHeight() &&
          this.getWidth() == other.getWidth() && this.getOpacity() - other.getOpacity() < 0.001 &&
          this.getCurrentPosition().equals(other.getCurrentPosition());
    }
  }



}
