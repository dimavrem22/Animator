package cs3500.animator.model;

/**
 * Represents the common functionality for TimeIntervals.
 */
public interface ITimeInterval {

  /**
   * Gets the start tick of this time interval.
   * @return the start tick
   */
  int getStartTick();

  /**
   * Gets the end tick of this time interval.
   * @return the end tick
   */
  int getEndTick();

  /**
   * Gets the tempo of this time interval.
   * @return the tempo
   */
  int getTempo();

}
