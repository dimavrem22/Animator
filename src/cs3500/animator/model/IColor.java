package cs3500.animator.model;

/**
 * Interface which represents the common functionality for colors in the animation. Colors can be
 * represented as red, green, blue values each ranging from [0-255]. Note: Brackets are inclusive.
 */
public interface IColor {

  /**
   * Gets the value for the color red.
   *
   * @return the value for the color red ranging from [0-255]
   */
  int getRed();

  /**
   * Gets the value for the color green.
   *
   * @return the value for the color green ranging from [0-255]
   */
  int getGreen();

  /**
   * Gets the value for the color blue.
   *
   * @return the value for the color blue ranging from [0-255]
   */
  int getBlue();

  /**
   * Gets all three RGB values in this cs3500.animator.model.IColor object in an array with R,G,B as
   * ordering.
   *
   * @return an array of red,green, and blue values.
   */
  int[] getRGB();

  /**
   * Clones this cs3500.animator.model.IColor.
   *
   * @return a new cs3500.animator.model.IColor object with the same properties as this
   *          cs3500.animator.model.IColor.
   */
  IColor clone();


}
