package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * This class represents the color of the shapes.
 */
public class Rgb {

  private final int r;
  private final int g;
  private final int b;

  /**
   * Initializes each of the colors based on their r, g, b values.
   */
  public Rgb(int r, int g, int b) {
    if (r < 0 || r > 256 || g < 0 || g > 256 || b < 0 || b > 256) {
      throw new IllegalArgumentException("R,G,B values can't be negative.");
    }

    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets the "R" value of RGB color.
   * @return the int value that represents the R
   */
  public int getR() {
    return r;
  }

  /**
   * Gets the "G" value of RGB color.
   * @return the int value that represents the G
   */
  public int getG() {
    return g;
  }

  /**
   * Gets the "B" value of the RGB color.
   * @return the int value that represents the B
   */
  public int getB() {
    return b;
  }

  @Override
  public String toString() {
    return String.format("R: %d, G: %d, B: %d", this.r, this.g, this.b);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Rgb)) {
      return false;
    }

    Rgb that = (Rgb) a;

    return this.r == that.r && this.g == that.g && this.b == that.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }

}