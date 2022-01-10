package cs3500.animator.provider.view;

/**
 *  Contains all the methods that are implemented by all of the panels in the animation.
 *  By doing so, we can easily provide these features to the different panels if we want
 *  as well. For example, we can easily make the visual panel also interactive by implementing
 *  these methods, but for now they just throw unsupported exceptions.
 */
public interface IAnimationPanels {

  /**
   * Starts the timer which begins the animation.
   */
  public void startTimerRender();

  /**
   * Starts the timer from its latest value.
   */
  public void startTimer();

  /**
   * Sets the timer counter back to its initial value of 1.
   */
  public void rewind();

  /**
   * Stops the panel by stopping the timer.
   */
  public void stopPanel();

  /**
   * Fast forwards the panel by increasing the speed.
   * @param speedUp the value at which the speed is increased by
   */
  public void fastForward(int speedUp);

  /**
   * Slows down the panel by decreasing speed.
   * @param slowDown the value at which the speed is slowed down by
   */
  public void fastBackward(int slowDown);

  /**
   * Gives the panel the ability to loop even after the animation has ended.
   */
  public void looping();
}

