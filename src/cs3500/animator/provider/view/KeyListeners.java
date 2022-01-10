package cs3500.animator.provider.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Implements the key listener which allows us to give features and ability to
 * the keys that are either typed, pressed or released. Since our implementation
 * only works with the pressed, we made the other two just simply return. It then checks
 * if the pressed key is one of the keys that are already existing in the
 * map and then runs the key listener associated with that key.
 */
public class KeyListeners implements KeyListener {
  private Map<Integer, Runnable> keyPressedMap;

  @Override
  public void keyTyped(KeyEvent e) {
    return;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    return;
  }

  /**
   * Initializes the keyPressedMap field with a table of actions and their
   * corresponding keys.
   *
   * @param map map of events and their corresponding keys
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    if (map == null) {
      throw new IllegalArgumentException("null argument");
    }
    keyPressedMap = map;
  }
}
