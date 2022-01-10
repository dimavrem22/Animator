package cs3500.animator.provider.controller;

/**
 * The controller interface which can be implemented by the various controllers needed
 * for the different model and view pairings. Essentially, takes in the information
 * from the model and then passes it on to the view. Contains an execute method which,
 * in our case, renders the view that the method is called on.
 */
public interface IController {

  /**
   * The primary method where the view is being rendered so that once this method
   * is called on the view, the
   * view will render.
   */
  public void execute();

}
