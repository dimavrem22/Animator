package cs3500.animator.model;

/**
 * Represents an elliptical shape. Note: A circle is an ellipse.
 */
public class Ellipse extends AShape {

  /**
   * Constructs a {@code cs3500.animator.model.Ellipse} with the provided settings.
   *
   * @param width    the width of the shape
   * @param height   the height of the shape
   * @param color    the color of the shape
   * @param position the position of the shape
   * @param opacity  the opacity of the shape
   * @throws IllegalArgumentException if any of the arguments provided are null
   */
  public Ellipse(int width, int height, IColor color, IPosition position, double opacity)
      throws IllegalArgumentException {
    super(width, height, color, position, opacity);
  }

  /**
   * Returns the type of shape.
   *
   * @return the String representation of the type of shape this is.
   */
  @Override
  public String toString() {
    return "ellipse";
  }

  @Override
  public IShape clone() {
    return new Ellipse(this.getWidth(), this.getHeight(),
        this.getColor(), this.getCurrentPosition(), this.getOpacity());
  }

  @Override
  public IShape create(int width, int height, IColor color, IPosition position, double opacity) {
    return new Ellipse(width, height, color, position, opacity);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.getHeight()) * 100 + Integer.hashCode(this.getWidth()) * 10
        + Double.hashCode(this.getOpacity()) * 10000 + this.getColor().hashCode() * 100000 +
        this.getCurrentPosition().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Ellipse)) {
      return false;
    } else {
      Ellipse other = (Ellipse) obj;

      return this.getColor().equals(other.getColor()) && this.getHeight() == other.getHeight() &&
          this.getWidth() == other.getWidth() && this.getOpacity() - other.getOpacity() < 0.001 &&
          this.getCurrentPosition().equals(other.getCurrentPosition());
    }
  }

}
