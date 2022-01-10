package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Mock view that is used to test that proper methods are called and that inputs are not
 * manipulated. Method return values do not matter. Input commands are appended to the Appendable.
 */
public class MockView implements IView {

  private final Appendable out;

  /**
   * Constructs a mock view.
   *
   * @param out the Appendable which is used to confirm inputs and proper method calls.
   */
  public MockView(Appendable out) {
    this.out = out;
  }


  @Override
  public void render() throws IOException {
    out.append("render");
  }

  @Override
  public void play() {
    try {
      out.append("play");
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void restart() {
    try {
      out.append("restart");
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void exit() {
    try {
      out.append("exit");
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void increaseTempo(int amount) {
    try {
      out.append("increaseTempo: " + amount);
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void decreaseTempo(int amount) {
    try {
      out.append("decreaseTempo: " + amount);
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void stop() {
    try {
      out.append("stop");
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    try {
      out.append("listeners added\n");
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void toggleLooping() {
    try {
      out.append("toggleLooping");
    } catch (IOException e) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void advance(ActionEvent e) {
    try {
      out.append("advance");
    } catch (IOException ioe) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void toggleOutline() {
    try {
      out.append("toggleOutline");
    } catch (IOException ioe) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

  @Override
  public void toggleDiscrete() {
    try {
      out.append("toggleDiscrete");
    } catch (IOException ioe) {
      throw new IllegalStateException("CAN NOT APPEND");
    }
  }

}
