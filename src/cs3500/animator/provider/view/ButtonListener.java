package cs3500.animator.provider.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The class that gives the buttons functionalities once they are pressed.
 * This class contains an action performed method which finds the corresponding
 * button that has been pressed and then runs the method that is linked with that method
 * in the map. These buttons are placed in a map with it's
 * method in key value pairs.
 */
public class ButtonListener implements ActionListener {
  Map<String, Runnable> buttonClickedActions;


  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }

  /**
   * Initializes the buttonClickedActions Field with  a table of actions and their
   * corresponding buttons.
   *
   * @param map map of events and their corresponding button
   */

  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    if (map == null) {
      throw new IllegalArgumentException("map cannot be null");
    }
    buttonClickedActions = map;
  }

}



