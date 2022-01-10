package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * This class represents the 2-dimension size of the shape.
 */
public class Size2D {
  private final int width;
  private final int height;

  /**
   * The given shape will have a specific width and height which will be represented in this class.
   * @param width represents the width of the shape.
   * @param height represents the height of the shape.
   */
  public Size2D(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width or height can't be negative.");
    }

    this.width = width;
    this.height = height;
  }

  /**
   * Give the width value of this size2D.
   * @return immutable width field of this Size2D
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Give the height value of this size2D.
   * @return immutable height field of this Size2D
   */
  public int getHeight() {
    return this.height;
  }

  @Override
  public String toString() {
    return String.format("Width = %d, Height = %d", this.width, this.height);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Size2D)) {
      return false;
    }

    Size2D that = (Size2D) a;

    return ((Math.abs(this.width - that.getWidth()) < 0.01)
        && (Math.abs(this.height - that.getHeight()) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height);
  }
}
