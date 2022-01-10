package cs3500.animator.model;

import java.util.List;


/**
 * The interface represents a read only version of the model. It allows for the user to see and
 * use the contents of an AnimationModel without permitting mutating it.
 */
public interface IReadOnlyModel {


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
   * @param tick tick of animation
   * @return tempo at that tick
   */
  int getTempoAtTick(int tick);

}
