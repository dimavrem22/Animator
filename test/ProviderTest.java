import static org.junit.Assert.assertEquals;


import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import cs3500.animator.provider.model.AnimationModel;
import cs3500.animator.provider.model.AnimationModelImpl;
import cs3500.animator.provider.model.ViewModel;
import cs3500.animator.provider.view.ITextualAnimationView;
import cs3500.animator.provider.view.TextualAnimationView;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link TextualAnimationView}: Units tests to make sure
 * the view offers the proper String representation of the animation state.
 */
public class ProviderTest {


  IAnimationModel ours;
  AnimationModel m;
  ViewModel vm;
  ITextualAnimationView view;
  IShape rectangle;
  IShape ellipse;
  IColor red;
  IColor lightcyan;
  String canvasSize;
  Appendable out;

  @Before
  public void setUp() {
    ours = new cs3500.animator.model.AnimationModel();
    m = new AnimationModelImpl(ours);
    vm = new ViewModel(m);
    out = new StringBuilder();
    view = new TextualAnimationView(vm, out, 1);
    red = new Color(255, 0, 0);
    lightcyan = new Color(224, 255, 255);
    rectangle = new Rectangle(10, 20, red, new Position(10, 10), 1);
    ellipse = new Ellipse(5, 5, red, new Position(10, 10), 1);
    canvasSize = "canvas 0 0 1000 1000\n";
  }

  /**
   * Test that empty model is represented by just the canvas size.
   */
  @Test
  public void toStringEmptyModelTests() {
    assertEquals("canvas 0 0 1000 1000", view.toString());
  }

  /**
   * Test that an animation with no motions but a shape
   * represents the shape addition.
   */
  @Test
  public void toStringNoMotionTests() {
    ours.addShape("r1", rectangle);
    String result = canvasSize;
    result += "shape r1 rectangle";
    assertEquals(result,view.toString());
  }

  /**
   * Test that a animation with one shape and motion
   * is properly represented.
   */
  @Test
  public void toStringWithOneMotionTests() {
    ours.addShape("r1", rectangle);
    ours.addMotion("r1", new Position(40,60),1,10,50,30,red);
    String intro = "shape r1 rectangle\n";
    String motionInfo = "motion r1 1.00 10 10 10 20 255 0 0   10.00 40 60 50 30 255 0 0";
    assertEquals(canvasSize + intro + motionInfo,view.toString());
  }

  /**
   * Test that a animation with multiple shapes and multiple motions
   * is properly represented.
   */
  @Test
  public void toStringWithMultipleShapesTests() {


    ours.addShape("r1", rectangle);
    ours.addShape("c1", ellipse);

    ours.addMotion("r1", new Position(40,60),1,10,50,30,red);
    ours.addMotion("r1", new Position(50,60),10,50,1,5,red);
    ours.addMotion("c1", new Position(50,60),1,5,10,10,lightcyan);
    String result = "canvas 0 0 1000 1000\n"
        + "shape r1 rectangle\n"
        + "motion r1 1.00 10 10 10 20 255 0 0   10.00 40 60 50 30 255 0 0\n"
        + "motion r1 10.00 10 10 10 20 255 0 0   50.00 50 60 1 5 255 0 0\n"
        + "shape c1 ellipse\n"
        + "motion c1 1.00 10 10 5 5 255 0 0   5.00 50 60 10 10 224 255 255";

    assertEquals(result,view.toString());
  }


}