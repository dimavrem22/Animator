package cs3500.animator.model;

import java.util.List;

/**
 * Interface for the animation models representing the common functionality for any implementing
 * models.
 */
public interface IAnimationModel {

  /**
   * Sets the shape for the shape with a given name
   * @param nameOfShape the name of the shape to set
   * @param shape the new shape it is associated with
   */
  void setShape(String nameOfShape, IShape shape);

  /**
   * Adds a given shape to the animation model.
   *
   * @param shape an cs3500.animator.model.IShape object to be added
   * @param name  the name of the shape
   * @throws IllegalArgumentException if shape is null or given shape's name is already used in the
   *                                  model.
   */
  void addShape(String name, IShape shape) throws IllegalArgumentException;


  /**
   * Removes a shape from the animation model.
   *
   * @param nameOfShape the name of the cs3500.animator.model.IShape in the model.
   * @throws IllegalArgumentException if nameOfShape is null or no IShapes in the model have the
   *                                  given name.
   */
  void removeShape(String nameOfShape) throws IllegalArgumentException;

  /**
   * Determines if the model has the given Shape.
   *
   * @param nameOfShape the name of the shape we want to check for
   * @return true if shape is present in the animation, false otherwise.
   */
  boolean hasShape(String nameOfShape) throws IllegalArgumentException;

  /**
   * Gets the shape in this animation with the given name.
   *
   * @param nameOfShape the name of the shape.
   * @return the cs3500.animator.model.IShape with the given name
   * @throws IllegalArgumentException if the name is null or no shape has that name.
   */
  IShape getShape(String nameOfShape) throws IllegalArgumentException;


  /**
   * Adds the described shape motion to the motions in the model.
   *
   * @param nameOfShape   the name of the shape
   * @param finalPosition the final position of the shape in the motion
   * @param firstTick     the starting tick value
   * @param lastTick      the ending tick value
   * @param finalWidth    the end width of the shape
   * @param finalHeight   the end height of the shape
   * @param finalColor    the end color of the shape
   * @throws IllegalArgumentException if name,endPosition, or finalColor is null
   * @throws IllegalArgumentException if this motion overlaps with an existing one for the given
   *                                  shape, or any of the provided ticks are negative
   */
  void addMotion(String nameOfShape, IPosition finalPosition, int firstTick,
      int lastTick, int finalWidth, int finalHeight, IColor finalColor)
      throws IllegalArgumentException;


  /**
   * Gets the list of {@code cs3500.animator.model.IMotion}'s in the model given the name of the
   * shape associated with those motions.
   *
   * @param nameOfShape the name of the shape whose motions we should obtain
   * @return the {@code List<cs3500.animator.model.IMotion>} that contains the motions for the given
   *         shape.
   */
  List<IMotion> getMotions(String nameOfShape);

  /**
   * Returns the state of the shape associated with the provided shape name at a given tick.
   *
   * @param name the name of the shape
   * @param tick the tick to look for the shape in
   * @return the cs3500.animator.model.IShape with the state at the provided tick
   * @throws IllegalArgumentException if the tick value is negative or name is null or name is not
   *                                  in the model.
   */
  IShape shapeAtTick(String name, int tick);

  /**
   * Gets a list of the shape names that have been added to this model.
   *
   * @return a list of the shape names added to the model
   */
  List<String> getAllShapeNames();

  /**
   * Gets a list of the shapes that have been added to this model.
   *
   * @return a list of the shapes added to the model
   */
  List<IShape> getAllShapes();

  /**
   * Calculates the last tick in the animation.
   *
   * @return the last tick where the final motion completes.
   */
  int calculateLastTick();

  /**
   * Gets the topmost y value in the animation.
   *
   * @return the topmost y value in the animation.
   */
  int getTopmostY();

  /**
   * Gets the leftmost x value in the animation.
   *
   * @return the leftmost x value in the animation.
   */
  int getLeftmostX();

  /**
   * Get the width of the animation box.
   *
   * @return the width of animation box.
   */
  int getWidthBound();

  /**
   * Get the height of the animation box.
   *
   * @return the height of animation box.
   */
  int getHeightBound();


  /**
   * Gets the tempo at a given tick.
   * @param tick the current tick
   * @return the tempo at the given tick, -1 if tick not part
   * of a time interval.
   */
  int getTempoAtTick(int tick);


  /**
   * Adds a TimeInterval to the model.
   * @param startTick the start tick of the interval
   * @param endTick the end tick of the interval
   * @param tempo the tempo during that interval
   * @throws IllegalStateException if overlap in time intervals
   */
  void addTimeInterval(int startTick, int endTick, int tempo);


}
