package cs3500.animator.provider.model;

import cs3500.animator.provider.model.IShape2D;
import java.util.List;

/**
 * Represents the shapes and motions together and the methods required for them to work together.
 */
public interface IShapeAndMotion {


  /**
   * Returns the motion at the inputted time of the particular shape and motion.
   * @param time the time at which we a motion for
   * @return motion in that particular time
   * @throws IllegalArgumentException if the shape does not have a motion at that time
   */
  public IMotion getMotionAtTime(int time) throws IllegalArgumentException;

  /**
   * The start time of shape and motion.
   */
  public int getStartTime();

  /**
   * The end time of shape and motion.
   */
  public int getEndTime();

  /**
   * A copy of the shape.
   * @return the copy of the shape
   */
  public IShape2D getShape();

  /**
   * get a copy of the motions.
   * @return a list of the copy of motions
   */
  public List<IMotion> getMotions();

  /**
   * Adds new motions to the list of existing motions.
   * @param motion new motion to be applied on the shape contained in this class
   * @throws IllegalArgumentException if the motion is null
   */
  public void addMotion(IMotion motion) throws IllegalArgumentException;

  /**
   * Chronologically sorts the motions of this object based on the smallest starting time.
   */
  public void sortMotionsOnStartTime();

  /**
   * Check if in the list of motions, chronologically sorted from earliest to latest, any motion
   * produces a gap or overlap.
   * @return false if any motion produces an overlap or an
   */
  public boolean checkMotionCoherence();


  /**
   * Displays the previous motion if the new motion is told to do nothing.
   * A motions is a do nothing motions if its instructions are null except
   * for the shape name, the start and end time
   */
  public void updateDoNothingMotion();


  /**
   * Links the motions together which makes the motions more synchronized and no gaps.
   */
  public void linkMotions();

  /**
   * Copies the shape and motion.
   * @return a copy of the shape and motion
   */
  public IShapeAndMotion copyShapeAndMotion();

  /**
   * Applies a particular motion at the tick which affects all of the shapes accordingly.
   * @param newTick the tick at which the animation progresses
   */
  public void applyMotion(int newTick);

  /**
   * Updates a shape using the colorize, resize and move methods of the shape using the
   * given parameters.
   * @param pos new position that we want to update the shape with
   * @param size new size that we want to update the shape with
   * @param color new color that we want to update the shape with
   */
  public void updateShape(Position2D pos, Size2D size, Rgb color);
}

