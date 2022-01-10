package cs3500.animator.provider.model;

/**
 * Represents the motions that will be applied to shapes and the methods required for it.
 */
public interface IMotion {

  /**
   * To find out whether the motions are synchronized ie. no gaps or not
   * which would be true after calling link motions method
   * @return whether the motions are synchronized and have no gaps or not
   */
  public boolean getSynchro();

  /**
   * To find out whether a particular motion is changing or not doing anything over time.
   * @return whether the motion is changing or not
   */
  public boolean getDoNothing();

  /**
   * Copies the motion and returns a new motion so that the original can't be changed accidentally.
   * @return a copy of the motion
   */
  public IMotion copyMotion();

  /**
   * The name of the shape that we are working with.
   * @return the shape name in string form
   */
  public String getShapeName();

  /**
   * The end time.
   * @return the time in int.
   */
  public int getEndTime();

  /**
   * The start time.
   * @return the the time in int.
   */
  public int getStartTime();

  /**
   * The new position of the motion.
   * @return the position in 2d.
   */
  public Position2D getStartPos();

  /**
   * The new position of the motion.
   * @return the position in 2d.
   */
  public Position2D getEndPos();

  /**
   * The start size of the motion.
   * @return the size in 2d
   */
  public Size2D getStartSize();

  /**
   * The new size of the motion.
   * @return the size in 2d
   */
  public Size2D getEndSize();

  /** The start color of the motion.
   * @return the motion in rgb.
   */
  public Rgb getStartColor();

  /** The end color of the motion.
   * @return the motion in rgb.
   */
  public Rgb getEndColor();

  /**
   * Contains only the features we need in view because our constructor
   * has extra features that is not needed
   * to be represented in the view if we use toString.
   * @return a string version with only features required in view
   */
  public String toTextualString();
}
