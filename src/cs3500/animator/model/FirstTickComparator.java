package cs3500.animator.model;

import java.util.Comparator;

/**
 * Class which represents the comparator for IMotions based on their start ticks.
 */
public class FirstTickComparator implements Comparator<IMotion> {


  /**
   * Compares two IMotions based on their start ticks.
   *
   * @param o1 first motion to be compared
   * @param o2 second motion to be compared
   * @return negative if the first motion starts before the second motion.
   */
  @Override
  public int compare(IMotion o1, IMotion o2) {
    return o1.getStartTick() - o2.getStartTick();
  }
}
