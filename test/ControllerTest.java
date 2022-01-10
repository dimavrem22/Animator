import cs3500.animator.controller.Controller;
import cs3500.animator.view.IView;
import cs3500.animator.view.MockView;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;

/**
 * Class containing tests for the controller. The mock view is used to test.
 * Used to make sure that proper methods are called and inputs not manipulated.
 */
public class ControllerTest {


  // Test stop
  @Test
  public void testStop() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "pauseButton"));
    assertEquals("stop", out.toString());

  }

  // test resume
  @Test
  public void testResume() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "playButton"));
    assertEquals("play", out.toString());

  }

  // test restart
  @Test
  public void testRestart() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "restartButton"));
    assertEquals("restart", out.toString());
  }

  // test exit
  @Test
  public void testExit() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "exitButton"));
    assertEquals("exit", out.toString());
  }

  // test speeding up animation
  @Test
  public void testSpeedUp() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "fasterButton"));
    assertEquals("increaseTempo: 20", out.toString());
  }

  // test slowing down animation
  @Test
  public void testSlow() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "slowerButton"));
    assertEquals("decreaseTempo: 20", out.toString());
  }

  // test looping
  @Test
  public void testLoop() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "toggleButton"));
    assertEquals("toggleLooping", out.toString());
  }

  // test setButtonListeners and render
  @Test
  public void testSetButtonListenersAndRender() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.execute();
    assertEquals("listeners added\nrender", out.toString());
  }

  // test outlining
  @Test
  public void testOutline() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "outlineButton"));
    assertEquals("toggleOutline", out.toString());
  }

  // test discrete frames function
  @Test
  public void testDiscreteFrames() {
    Appendable out = new StringBuilder();
    IView mock = new MockView(out);
    Controller controller = new Controller(mock);
    controller.actionPerformed(new ActionEvent(mock, 1, "discreteButton"));
    assertEquals("toggleDiscrete", out.toString());
  }

}
