package cs3500.animator.controller;

/**
 * Interface which represents common functionality for all
 * controllers.
 */
public interface IController {

  /**
   * Runs the animation with the use of
   * the view for rendering and model for animation state.
   */
  void execute();

}
