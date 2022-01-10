package cs3500.animator.provider.controller;

/**
 * This interface contains a list of all the features/abilities that we could want
 * a particular class to have. In our case, we are using this interface to store all the
 * features that we want our interactive view to have. Doing so, makes it a lot easier for
 * us to have all the features stored in one place and then easily link these
 * features to whichever event listener that we want. For example, we can use this one
 * feature method and then link it up with a key listener as well as a button listener.
 */
public interface Features {

  /**
   * This method begins the animation by calling the start animation from the view.
   */
  public void beginAnimation();

  /**
   * This method resumes the animation by calling the resume animation from the view
   * (which starts the timer again).
   */
  public void resumeAnimation();

  /**
   * Restarts the animation by calling the restart method from the view by restarting the timer.
   */
  public void restartAnimation();

  /**
   * Pauses the animation by calling the pause method from the view which stops the timer.
   */
  public void pauseAnimation();

  /**
   * Fast forwards the animation by calling the fast forward method from the view which
   * speeds up the timer by
   * 2.
   * @param timeSpeedUp the speed at which we want the time to speed up by
   */
  public void fastForward(int timeSpeedUp);

  /**
   * Slows down the animation by calling the fast backward method from the view which
   * again slows down the
   * timer by 2.
   * @param timeSlowDown the speed at which we slow the time up by
   */
  public void fastBackward(int timeSlowDown);

  /**
   * Loops the animation by calling the loop method from the view.
   */
  public void loopAnimation();

}
