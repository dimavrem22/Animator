package cs3500.animator.model;

/**
 * Class representing an implementation of {@link IColor}. The class allows its users to manage
 * cs3500.animator.model.Color objects by getting rgb values.
 */
public class Color implements IColor {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructs a color object given red, green, blue values.
   *
   * @param red   red value
   * @param green green value
   * @param blue  blue value
   */
  public Color(int red, int green, int blue) {

    if (!inColorRange(red) || (!inColorRange(green)) || (!inColorRange(blue))) {
      throw new IllegalArgumentException("Invalid color values.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Constructs color object given array of rgb values.
   *
   * @param rgb array of rgb values
   * @throws IllegalArgumentException if the array of values is null, the length is not 3, or the
   *                                  values themselves are not in the proper color range
   */
  public Color(int[] rgb) {
    if (rgb == null || rgb.length != 3) {
      throw new IllegalArgumentException("Invalid rgb array");
    }

    if (!inColorRange(rgb[0]) || (!inColorRange(rgb[1])) || (!inColorRange(rgb[2]))) {
      throw new IllegalArgumentException("Invalid color values.");
    }

    this.red = rgb[0];
    this.green = rgb[1];
    this.blue = rgb[2];
  }

  /**
   * Determines if a given value is within the rgb range (between 0 and 255 inclusive).
   *
   * @param colorValue the value of the color
   * @return true if in range, false otherwise
   */
  private boolean inColorRange(int colorValue) {
    return 0 <= colorValue && colorValue <= 255;
  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }

  @Override
  public int[] getRGB() {
    int[] colors = new int[3];
    colors[0] = this.red;
    colors[1] = this.green;
    colors[2] = this.blue;
    return colors;
  }

  @Override
  public IColor clone() {
    return new Color(this.getRGB());
  }


  @Override
  public int hashCode() {
    return Integer.hashCode(this.red) * 10000 + Integer.hashCode(this.blue) * 100
        + Integer.hashCode(this.green);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Color)) {
      return false;
    } else {
      Color other = (Color) obj;
      return this.getBlue() == other.getBlue() && this.getRed() == other.getRed() &&
          this.getGreen() == other.getGreen();
    }
  }
}
