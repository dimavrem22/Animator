package cs3500.animator.provider.model;

import java.util.Map;

public interface IReadAnimationModel {

  /**
   * Displays the shape and motion combined to represent them together and connect them to
   * the corresponding shape depending on the string inputted.
   * @return the shape and motion drawings of the string shape
   */
  public Map<String, IShapeAndMotion> getDrawings();


  /**
   * Indicates when the animation should be over which is when the animation model not started.
   * is greater than t max time.
   * @return true if animation is over and false if not over
   *
   */
  public boolean isAnimationOver() throws IllegalStateException;

  /**
   * All of the motions that are for a particular shape.
   * @return the motions in a treemap form
   */
  public Map<String, IShapeAndMotion> getInstructions();

  /**
   * Displays the shape and motion for the inputted shape.
   * @param name the shape of which we want to know the shape and motion about
   * @return the shapeAndMotion of a particular shape
   */
  public IShapeAndMotion getImageAt(String name);


  /**
   * Returns the shape at a given period of time.
   * @param time details about the shape at the inputted time
   */
  public IShape2D getFrame(String name, int time) throws IllegalStateException;


  /**
   * If the animation has started or not.
   * @return true or false depending on if the animation has been started or not
   */
  public boolean animationStarted();


  /**
   * Gets the x value of the canvas to use in the to string.
   * @return the x value of canvas as int
   */
  public int getXCanvas();

  /**
   * Gets the y value of the canvas to use in the to string.
   * @return the y value of canvas as int
   */
  public int getYCanvas();

  /**
   * Gets the width of the canvas to use in the to string.
   * @return the width value of canvas as int
   */
  public int getWidthCanvas();

  /**
   * Gets the height of the canvas to use in the to string.
   * @return the height value of canvas as int
   */
  public int getHeightCanvas();

  /**
   * Gets the end time of animation.
   * @return the end time of animation in int
   */
  public int getEndTime();

  /**
   * Updates the shape using the move, resize, colorize method with the given parameters
   * accordingly.
   * @param name name of shape
   * @param pos position of shape (x,y)
   * @param size size of shape (width, height)
   * @param color color of shape (r,g,b)
   * @throws IllegalArgumentException if the inputted shape does not exist
   * @throws IllegalStateException if animation has not started
   */
  public void updateShape(String name, Position2D pos, Size2D size, Rgb color)
      throws IllegalArgumentException, IllegalStateException;


}



