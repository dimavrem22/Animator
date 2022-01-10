package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Interface which represents the common functionality for the view. Renders a {@code
 * IReadOnlyModel} in some manner.
 */
public interface IView {

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   *
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;

  /**
   * Handles starting the animation.
   */
  void play();

  /**
   * Restarts the animation.
   */
  void restart();


  /**
   * Exits the animation.
   */
  void exit();


  /**
   * Method that increases the speed of the animation.
   *
   * @param amount the amount by which the tempo should increase.
   */
  void increaseTempo(int amount);


  /**
   * Method that decreases the speed of the animation.
   *
   * @param amount the amount by which the tempo should decrease.
   */
  void decreaseTempo(int amount);


  /**
   * Stops the animation.
   */
  void stop();


  /**
   * Provide the view with an action listener for
   * the buttons that should cause the program to
   * process a command. This is so that when the button
   * is pressed, control goes to the action listener
   *
   * @param listener the listener to provide to the button
   */
  void setButtonListeners(ActionListener listener);

  /**
   * Toggles whether animation should restart upon
   * ending or not.
   */
  void toggleLooping();

  /**
   * Advances the animation by 1 tick.
   * @param e an ActionEvent for ticking from the timer
   */
  void advance(ActionEvent e);


  /**
   * Toggles outline mode.
   */
  void toggleOutline();

  /**
   * Toggles discrete mode.
   */
  void toggleDiscrete();
}
