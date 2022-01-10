package cs3500.animator.model;

/**
 * Abstract class which implements {@code cs3500.animator.model.IShape}. Represents a geometric
 * shape and the implementation of the common functionalities for shapes.
 */
abstract class AShape implements IShape {

  private int width;
  private int height;
  private IColor color;
  private final IPosition position;
  private double opacity;

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
  public AShape(int width, int height, IColor color, IPosition position, double opacity)
      throws IllegalArgumentException {
    if (color == null || position == null) {
      throw new IllegalArgumentException("Null arguments");
    }

    this.width = width;
    this.height = height;
    this.color = color;
    this.position = position;
    this.opacity = opacity;
  }


  @Override
  public void move(IPosition endPosition) throws IllegalArgumentException {
    if (endPosition == null) {
      throw new IllegalArgumentException("Null arguments");
    }
    this.position.setPosition(endPosition);
  }

  @Override
  public void changeDimensions(int changeInHeight, int changeInWidth)
      throws IllegalArgumentException {

    if (-1 * changeInWidth >= this.width || -1 * changeInHeight >= this.height) {
      throw new IllegalArgumentException("Dimensions must be positive.");
    }

    this.width += changeInWidth;
    this.height += changeInHeight;

  }

  @Override
  public IPosition getCurrentPosition() {
    return this.position.clone();
  }

  @Override
  public void setColor(IColor color) throws IllegalArgumentException {

    if (color == null) {
      throw new IllegalArgumentException("cs3500.animator.model.Color should not be null");
    }

    this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());

  }

  @Override
  public IColor getColor() {
    return this.color.clone();
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public double getOpacity() {
    return this.opacity;
  }

  // Use opacity 0 for a non-visible shape and 1 for visible shape.
  // Do not currently provide any in-between values while we are
  // waiting for more definite assignment instructions.
  @Override
  public void setOpacity(double opacity) throws IllegalArgumentException {

    if (opacity < 0 || opacity > 1) {
      throw new IllegalArgumentException("Opacity should be between 0 and 1 inclusive.");
    }

    this.opacity = opacity;
  }

  public abstract IShape clone();

  public abstract IShape create(int width, int height, IColor color,
      IPosition position, double opacity);

}
