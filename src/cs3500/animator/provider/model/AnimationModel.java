package cs3500.animator.provider.model;


import cs3500.animator.model.IReadOnlyModel;
import java.util.Map;

/**
 * Represents the model that contains the methods required in order to begin the animation.
 * These methods allow us to firstly create the shape that is inputted, then add motions that
 * the user wants for that shape (two methods one to represent actual motion and the other if the
 * shape does nothing), apply the motions to the shape,
 * then start the animation and also can reset the animation.
 */
public interface AnimationModel {

  /**
   * Displays the shape and motion combined to represent them together and connect them to
   * the corresponding shape depending on the string inputted.
   * @return the shape and motion drawings of the string shape
   */
  public Map<String, IShapeAndMotion> getDrawings();


  /**
   * Begins the animation and then pushes the final drawings to the stack and modifies the
   * fields in simple animation accordingly.
   * First of all, ensures that the drawings in stack motions are valid (ie. not empty, coherent,
   * there are no gaps, there are no overlaps) and then adds them to the stack motions after
   * it's been validate (throws an exception if invalid)
   * Next, sets the other simple animation fields to default values and then starts the game.
   */
  public void startAnimation();


  /**
   * Created a supported object with a name, and no operations yet.
   * @param obj nature of the geometric object to be displayed
   * @param name name to be associated with this shape
   * @throws IllegalArgumentException if the desired obj is not supported by the implementation
   */
  public void createShape(String obj, String name) throws IllegalArgumentException;

  /**
   * Constructs a new motion from the provided parameters and adds it to its corresponding shape.
   * @param name name of the shape associated with this motion
   * @param start starting time for this motion
   * @param end end time for this motion
   * @throws IllegalArgumentException if the motion cannot be constructed from the given parameters
   */
  public void addOperation(String name, int start, int end) throws IllegalArgumentException;

  /**
  /**
   * Constructs a new motion using the new parameters and adds it to its corresponding shape.
   * @param name name of the shape associated with this motion
   * @param start starting time for this motion
   * @param end end time for this motion
   * @param x x-coordinate of the shape at the end of the motion
   * @param y y-coordinate of the shape at the end of the motion
   * @param w width of the shape at the end of the motion
   * @param h height of the shape at the end of the motion
   * @param r r of rgb color of shape at the end of the motion
   * @param g g of rgb color of shape at the end of the motion
   * @param b b of rgb color of shape at the end of the motion
   * @throws IllegalArgumentException if motion has invalid values
   */
  public void addOperation(String name, int start, int end, int x, int y,
      int w, int h, int r, int g, int b) throws IllegalArgumentException;

  /**
   * Constructs a new motion with the start and end parameters.
   * @param name name of shape
   * @param t1 starting time for this motion
   * @param x1 x-coordinate of the shape at the start of the motion
   * @param y1 y-coordinate of the shape at the start of the motion
   * @param w1 width of the shape at the start of the motion
   * @param h1 height of the shape at the start of the motion
   * @param r1 r of rgb color of shape at the start of the motion
   * @param g1 g of rgb color of shape at the start of the motion
   * @param b1 b of rgb color of shape at the start of the motion
   * @param t2 ending time for this motion
   * @param x2 x-coordinate of the shape at the end of the motion
   * @param y2 y-coordinate of the shape at the end of the motion
   * @param w2 width of the shape at the end of the motion
   * @param h2 height of the shape at the end of the motion
   * @param r2 r of rgb color of shape at the end of the motion
   * @param g2 g of rgb color of shape at the end of the motion
   * @param b2 b of rgb color of shape at the end of the motion
   */
  public void addOperation(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

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
   * Resets the animation back to beginning.
   */
  public void resetAnimation();

  /**
   * Returns the shape at a given period of time.
   * @param time details about the shape at the inputted time
   */
  public IShape2D getFrame(String name, int time) throws IllegalStateException;


  /**
   * Retrieves the last saved animation from the drawings stack. This is a feature that
   * can be used if we would like to undo an animation.
   * Also throws an illegal state exception if the drawings are empty since
   * there is no motion that can be retrieved.
   */
  public void retrieve() throws IllegalStateException;

  /**
   * If the animation has started or not.
   * @return true or false depending on if the animation has been started or not
   */
  public boolean animationStarted();


  /**
   * This method is used to set the bounds of the canvas accordingly.
   * @param x the leftmost x value
   * @param y the leftmost y value
   * @param width width of canvas
   * @param height height of canvas
   */
  public void setBounds(int x, int y, int width, int height);


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
