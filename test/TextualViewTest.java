import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link TextualView}: Units tests to make sure
 * the view offers the proper String representation of the animation state.
 */
public class TextualViewTest {

  IReadOnlyModel rm;
  IAnimationModel model;
  IView view;
  IShape rectangle;
  IShape ellipse;
  IColor red;
  IColor purple;
  IColor lightcyan;
  String canvasSize;
  Appendable out;

  @Before
  public void setUp() {
    model = new AnimationModel();
    rm = new ReadOnlyModel(model);
    out = new StringBuilder();
    view = new TextualView(rm,out);
    red = new Color(255,0,0);
    lightcyan = new Color(224,255,255);
    purple = new Color(128,0,128);
    rectangle = new Rectangle(10,20,red, new Position(10,10),1);
    ellipse = new Ellipse(5,5,red, new Position(10,10),1);
    canvasSize = "canvas 0 0 1000 1000\n";
  }

  /**
   * Test that empty model is represented by just the canvas size.
   */
  @Test
  public void toStringEmptyModelTests() {
    assertEquals("canvas 0 0 1000 1000\n",view.toString());
  }

  /**
   * Test that an animation with no motions but a shape
   * represents the shape addition.
   */
  @Test
  public void toStringNoMotionTests() {
    model.addShape("r1", rectangle);
    String result = canvasSize;
    result += "shape r1 rectangle\n";
    assertEquals(result,view.toString());
  }

  /**
   * Test that a animation with one shape and motion
   * is properly represented.
   */
  @Test
  public void toStringWithOneMotionTests() {
    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(40,60),1,10,50,30,red);
    String intro = "shape r1 rectangle\n";
    String motionInfo = "motion r1 1.00  10  10  10  20 255   0   0    "
        + "10.00  40  60  50  30 255   0   0\n";
    assertEquals(canvasSize + intro + motionInfo,view.toString());
  }


  /**
   * Test that a animation with one shape and multiple motions
   * is properly represented.
   */
  @Test
  public void toStringWithMultipleMotionsOneShapeTests() {
    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(40,60),1,10,50,30,red);
    model.addMotion("r1", new Position(50,60),10,50,1,5,red);
    String intro = "shape r1 rectangle\n";
    String motionInfo1 = "motion r1 1.00  10  10  10  20 255   0   0    "
        + "10.00  40  60  50  30 255   0   0\n";
    String motionInfo2 = "motion r1 10.00  40  60  50  30 255   0   0    "
        + "50.00  50  60   1   5 255   0   0\n";
    assertEquals(canvasSize + intro + motionInfo1 + motionInfo2,view.toString());
  }


  /**
   * Test that a animation with multiple shapes and multiple motions
   * is properly represented.
   */
  @Test
  public void toStringWithMultipleShapesTests() {

    String introR1 = canvasSize + "shape r1 rectangle\n";
    String r1MotionInfo = "motion r1 1.00  10  10  10  20 255   0   0    10.00  40  60  50  30 255 "
        + "  0   0\n"
        + "motion r1 10.00  40  60  50  30 255   0   0    50.00  50  60   1   5 128   0 128\n";
    String introC1 = "shape c1 ellipse\n";
    String c1MotionInfo1 = "motion c1 1.00  10  10   5   5 255   0   0    "
        + "5.00  50  60  10  10 128   0 128\n";

    assertEquals(canvasSize,view.toString());
    model.addShape("r1", rectangle);
    assertEquals(introR1,view.toString());
    model.addShape("c1", ellipse);
    assertEquals(introR1 + introC1,view.toString());

    model.addMotion("r1", new Position(40,60),1,10,50,30,red);
    model.addMotion("r1", new Position(50,60),10,50,1,5,purple);
    model.addMotion("c1", new Position(50,60),1,5,10,10,purple);
    String result = introR1 + r1MotionInfo + introC1 + c1MotionInfo1;

    assertEquals(result,view.toString());
  }

  /**
   * Test that a animation with two different shapes performing
   * a motion on the same interval are properly represented.
   */
  @Test
  public void toStringWithMatchedIntervalsTests() {

    String introR1 = "shape r1 rectangle\n";
    String r1MotionInfo = "motion r1 1.00  10  10  10  20 255   0   0    10.00  40  60  50  30 255 "
        + "  0   0\n"
        + "motion r1 10.00  40  60  50  30 255   0   0    50.00  50  60   1   5 128   0 128\n";

    String introC1 = "shape c1 ellipse\n";
    String c1MotionInfo = "motion c1 10.00  10  10   5   5 255   0   0    "
        + "50.00  50  60  10  10 128   0 128\n";
    String introR2 = "shape r2 rectangle\n";
    String r2MotionInfo = "motion r2 49.00  10  10  10  20 255   0   0    "
        + "60.00  10  20   6   8 255   0   0\n";

    assertEquals(canvasSize,view.toString());
    model.addShape("r1", rectangle);
    assertEquals(canvasSize + introR1,view.toString());
    model.addShape("c1", ellipse);
    assertEquals(canvasSize + introR1 + introC1,view.toString());
    model.addShape("r2", rectangle);
    assertEquals(canvasSize + introR1 + introC1 + introR2,view.toString());

    model.addMotion("r1", new Position(40,60),1,10,50,30,red);
    model.addMotion("r1", new Position(50,60),10,50,1,5,purple);
    model.addMotion("c1", new Position(50,60),10,50,10,10,purple);
    model.addMotion("r2", new Position(10,20),49,60,6,8,red);

    String result =  canvasSize + introR1 + r1MotionInfo  +
        introC1 + c1MotionInfo + introR2 + r2MotionInfo;
    assertEquals(result,view.toString());
  }

  /**
   * Check if two consecutive color changes for the same shape
   * is properly represented.
   */
  @Test
  public void toStringChangeColorsConsecutive() {

    assertEquals(canvasSize,view.toString());
    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(40,60),1,10,50,30,purple);
    model.addMotion("r1", new Position(40,60),10,20,50,30,lightcyan);
    String shapeIntro = "shape r1 rectangle\n";
    String motion1 = "motion r1 1.00  10  10  10  20 255   0   0    "
        + "10.00  40  60  50  30 128   0 128\n";
    String motion2 = "motion r1 10.00  40  60  50  30 128   0 128    "
        + "20.00  40  60  50  30 224 255 255\n";
    assertEquals(canvasSize + shapeIntro + motion1 + motion2,view.toString());

  }

  /**
   * Check if two consecutive dimensions changes for the same shape
   * is properly represented.
   */
  @Test
  public void toStringChangeDimensionsConsecutive() {

    assertEquals(canvasSize,view.toString());
    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(40,60),1,10,50,30,purple);
    model.addMotion("r1", new Position(40,60),10,20,60,40,lightcyan);
    String shapeIntro = "shape r1 rectangle\n";
    String motions = "motion r1 1.00  10  10  10  20 255   0   0    10.00  40  60  50  30 128  "
        + " 0 128\n"
        + "motion r1 10.00  40  60  50  30 128   0 128    20.00  40  60  60  40 224 255 255\n";
    assertEquals(canvasSize + shapeIntro + motions,view.toString());

  }

  // testing constructors
  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    TextualView t = new TextualView(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeTempoModel() {
    TextualView t = new TextualView(new ReadOnlyModel(new AnimationModel()), -2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullOutModel() {
    TextualView t = new TextualView(new ReadOnlyModel(new AnimationModel()), null, 1);
  }

  // testing unsupported operations
  @Test(expected = UnsupportedOperationException.class)
  public void CantPlayTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.play();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantPauseTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.stop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantIncreaseTempoTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.increaseTempo(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantDecreaseTempoTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.decreaseTempo(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantRestartTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.restart();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantExitTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.exit();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantToggleLoopingTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.toggleLooping();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantSetListenersTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.setButtonListeners(new Controller(view));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantAdvanceTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.advance(new ActionEvent(view,1,"dummy"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantToggleFillTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.toggleOutline();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantToggleDiscreteTest() {
    IView view = new TextualView(new ReadOnlyModel(new AnimationModel()), 10);
    view.toggleDiscrete();
  }


}
