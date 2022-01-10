package cs3500.animator.controller;

import cs3500.animator.view.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Class that implements the IController interface.
 * The class is able to take an IView (which already has access
 * to the animation model) and render the animation.
 * For static views (textual and SVG), the controller writes to a
 * file or System.out and for dynamic views (VisualView and VisualViewOld),
 * the controller displays a visual of the animation.
 */
public class Controller implements IController, ActionListener {

  private final IView view;

  /**
   * Constructs a Controller Object which is able to control animations.
   * @param view   the view to be passed in
   */
  public Controller(IView view) {
    this.view = view;
  }



  @Override
  public void execute() {

    try {
      view.setButtonListeners(this);
      view.render();
    }
    catch (UnsupportedOperationException | IOException e) {
      try {
        this.view.render();

      }
      catch (IOException uoe) {

        throw new IllegalStateException("Cannot append.");

      }
    }

  }


  /**
   * Method which taken in an ActionEvent and coordinates it to a
   * particular response for the dynamic animation views.
   * @param e  action event which is called
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "pauseButton":
        this.view.stop();
        break;
      case "playButton":
        this.view.play();
        break;

      case "restartButton":
        this.view.restart();
        break;

      case "exitButton":
        this.view.exit();
        break;

      case "fasterButton":
        this.view.increaseTempo(20);
        break;

      case "slowerButton":
        this.view.decreaseTempo(20);
        break;

      case "toggleButton":
        this.view.toggleLooping();
        break;

      case "outlineButton":
        this.view.toggleOutline();
        //System.out.println("Outline toggle");
        break;

      case "discreteButton":
        this.view.toggleDiscrete();
        break;

      default:
        this.view.stop();
        break;
    }

  }


}
