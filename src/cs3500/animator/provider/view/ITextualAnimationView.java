package cs3500.animator.provider.view;

/**
 * This is where we would put in all the methods that would be required for
 * each of various textual animation views for the future. For example, if we wanted
 * to create an interactive textual we would make that class implement
 * this and then add the interactive methods for all the textual animation implementations to have.
 */
public interface ITextualAnimationView extends IAnimationView {

  /**
   * Converts the tick to seconds by passing in the tick and then dividing it by the speed to
   * convert it.
   * @param tick the tick for which we want to convert to seconds
   * @return the value of the tick in seconds
   */
  public double convertTickToSeconds(int tick);
}
