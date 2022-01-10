package cs3500.animator.provider.view;


//import cs3500.animator.controller.Features;


import cs3500.animator.provider.controller.Features;

/**
 * This is where we would put in all the methods that would be required
 * for each of various visual animation
 * views. In our case, we have two types of visual animation views,
 * we have the normal visual view and then the interactive visual view. We then make
 * both of those classes implement this interface so that
 * if we want our visual view to also have interactive features, we can easily do
 * that but for now, it throws
 * an unsupported exception.
 */
public interface IVisualAnimationView extends IAnimationView {
  /**
   * Begins the animation by starting the timer on the panel.
   */
  public void startAnimation();

  /**
   * Resumes the animation by starting the timer again on the panel.
   */
  public void resumeAnimation();

  /**
   * pauses the animation by calling stop panel which stops the timer.
   */
  public void pauseAnimation();

  /**
   * restarts the animation by calling the rewind method which sets
   * the tick back to 1 and starts timer.
   */
  public void restartAnimation();

  /**
   * Fast forwards the animation depending on the speed that is given.
   * @param speedUp takes in the speed at which the user specified and then increasing
   *                speed of that
   */
  public void fastForward(int speedUp);

  /**
   * Decreases the speed of animation depending on the speed that is given.
   * @param slowDown slows down the speed at which the user specifies the speed
   */
  public void fastBackward(int slowDown);

  /**
   * Uses the features interface which contains all the features of our animation and then we add
   * those features to the view.
   * @param feat features object desired to be operational in this view
   */
  public void addFeatures(Features feat);

  /**
   * Allows the animation to enable or disable looping which means that either the animation will
   * begin playing after the animation has ended or not.
   */
  public void loop();
}
