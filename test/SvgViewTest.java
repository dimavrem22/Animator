import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Plus;
import cs3500.animator.model.Position;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SVGView: Units tests to make sure the view offers the proper SVG
 * representation of the animation state.
 */
public class SvgViewTest {

  IReadOnlyModel rm;
  IAnimationModel model;
  IView view;
  IShape rectangle;
  IShape ellipse;
  IShape plus;
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
    view = new SVGView(rm, out);
    red = new Color(255, 0, 0);
    lightcyan = new Color(224, 255, 255);
    purple = new Color(128, 0, 128);
    rectangle = new Rectangle(10, 20, red, new Position(10, 10), 1);
    ellipse = new Ellipse(5, 5, red, new Position(10, 10), 1);
    plus = new Plus(10,10,red, new Position(10, 10), 1);
    canvasSize = "canvas 0 0 1000 1000\n";
  }

  /**
   * Test that empty model is represented by just empty svg tags.
   */
  @Test
  public void renderEmptyModelTests() throws IOException {
    assertEquals("", out.toString());
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "</svg>";
    view.render();
    assertEquals(result, out.toString());
  }

  // test rendering of 1 rectangle without motions
  @Test
  public void renderWith1RectNoMotionsTests() throws IOException {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\""
                    + " visibility=\"visible\">\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    view.render();
    assertEquals(result, out.toString());
  }

  // test rendering of 1 plus without motions
  @Test
  public void renderWith1PlusNoMotionsTests() throws IOException {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "<rect id=\"p1\" x=\"10\" y=\"10\" width=\"5\" height=\"10\" fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
                    + "</rect>\n"
                    + "<rect id=\"p1\" x=\"10\" y=\"10\" width=\"10\" height=\"5\" fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("p1", plus);
    view.render();
    assertEquals(result, out.toString());
  }

  // test rendering of 1 shape without motions
  @Test
  public void renderWith1EllipseNoMotionsTests() throws IOException {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<ellipse id=\"c1\" cx=\"10\" cy=\"10\" rx=\"2\" ry=\"2\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "</ellipse>\n"
                    + "</svg>";

    model.addShape("c1", ellipse);
    view.render();
    assertEquals(result, out.toString());
  }

  // test rendering of 1 shape with motions
  @Test
  public void renderWith1ShapeTests() throws IOException {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName="
                    + "\"width\" from=\"10\" to=\"50\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName="
                    + "\"height\" from=\"20\" to=\"30\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName="
                    + "\"x\" from=\"10\" to=\"40\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName="
                    + "\"y\" from=\"10\" to=\"60\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(40, 60), 1, 10, 50,
            30, red);
    view.render();
    assertEquals(result, out.toString());
  }

  // test rendering with color change
  @Test
  public void renderColorChangeTests() throws IOException {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\""
                    + ">\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\""
                    + " visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(10, 10), 1, 10, 10,
            20, purple);
    view.render();
    assertEquals(result, out.toString());
  }

  // render with multiple shapes
  @Test
  public void renderMultipleShapesTests() throws IOException {
    assertEquals("", out.toString());
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "<ellipse id=\"c1\" cx=\"10\" cy=\"10\" rx=\"2\" ry=\"2\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "rx\" from=\"2\" to=\"5\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "ry\" from=\"2\" to=\"5\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "cx\" from=\"10\" to=\"50\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "cy\" from=\"10\" to=\"60\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "</ellipse>\n";

    result += "<rect id=\"p1\" x=\"10\" y=\"10\" width=\"5\" height=\"10\" fill=\"rgb(2" +
            "55,0,0)\" visibility=\"visible\">\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attr" +
            "ibuteName=\"width\" from=\"5\" to=\"10\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attr" +
            "ibuteName=\"y\" from=\"10\" to=\"58\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attr" +
            "ibuteName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attri" +
            "buteName=\"x\" from=\"12\" to=\"52\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attri" +
            "buteName=\"y\" from=\"10\" to=\"60\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"4000ms\" attrib" +
            "uteName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect id=\"p1\" x=\"10\" y=\"10\" width=\"10\" height=\"5\" fill=\"rgb" +
            "(255,0,0)\" visibility=\"visible\">\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attrib" +
            "uteName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attri" +
            "buteName=\"height\" from=\"5\" to=\"10\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attrib" +
            "uteName=\"x\" from=\"10\" to=\"48\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attri" +
            "buteName=\"x\" from=\"10\" to=\"50\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attrib" +
            "uteName=\"y\" from=\"12\" to=\"62\" fill=\"freeze\"/>\n"
            + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"4000ms\" attrib" +
            "uteName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "</svg>";


    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(10, 10), 1, 10, 10,
            20, purple);
    model.addShape("c1", ellipse);
    model.addMotion("c1", new Position(50, 60), 1, 5, 10,
            10, purple);
    model.addShape("p1",plus);
    model.addMotion("p1", new Position(50, 60), 1, 5, 20,
            20, purple);
    view.render();
    assertEquals(result, out.toString());
  }

  // testing toString

  @Test
  public void toStringEmptyModelTests() {
    assertEquals("", out.toString());
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "</svg>";

    assertEquals(result, view.toString());
  }

  // test toString of 1 rectangle without motions
  @Test
  public void toStringWith1RectNoMotionsTests() {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\""
                    + " visibility=\"visible\">\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    assertEquals(result, view.toString());
  }

  // test toString of 1 shape without motions
  @Test
  public void toStringWith1EllipseNoMotionsTests() {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<ellipse id=\"c1\" cx=\"10\" cy=\"10\" rx=\"2\" ry=\"2\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "</ellipse>\n"
                    + "</svg>";

    model.addShape("c1", ellipse);
    assertEquals(result, view.toString());
  }

  // test toString of 1 shape with motions
  @Test
  public void toStringWith1ShapeTests() {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\""
                    + " visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "width\" from=\"10\" to=\"50\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "height\" from=\"20\" to=\"30\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "x\" from=\"10\" to=\"40\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\"y\""
                    + " from=\"10\" to=\"60\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(40, 60), 1, 10, 50,
            30, red);
    assertEquals(result, view.toString());
  }

  // test toString with color change
  @Test
  public void toStringColorChangeTests() {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(10, 10), 1, 10, 10, 20, purple);
    assertEquals(result, view.toString());
  }

  // toString with multiple shapes
  @Test
  public void toStringMultipleShapesTests() {
    assertEquals("", out.toString());
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"
                    + "\n"
                    + "<rect id=\"r1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"rgb(255,0,0)\""
                    + " visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"9000ms\" attributeName=\""
                    + "fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "<ellipse id=\"c1\" cx=\"10\" cy=\"10\" rx=\"2\" ry=\"2\" fill=\"rgb(255,0,0)\" "
                    + "visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "rx\" from=\"2\" to=\"5\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "ry\" from=\"2\" to=\"5\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "cx\" from=\"10\" to=\"50\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\"cy"
                    + "\" from=\"10\" to=\"60\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\""
                    + "fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "</ellipse>\n"
                    + "</svg>";

    model.addShape("r1", rectangle);
    model.addMotion("r1", new Position(10, 10), 1, 10, 10,
            20, purple);
    model.addShape("c1", ellipse);
    model.addMotion("c1", new Position(50, 60), 1, 5, 10,
            10, purple);

    assertEquals(result, view.toString());
  }

  // testing constructors

  @Test(expected = IllegalArgumentException.class)
  public void NullModelConstructorTest() {
    IView view = new SVGView(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void NullAppendableConstructorTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NullAppendableNullModelConstructorTest() {
    IView view = new SVGView(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeTempoTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), -3);
  }

  // testing unsupported operations
  @Test(expected = UnsupportedOperationException.class)
  public void CantPlayTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.play();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantPauseTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.stop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantIncreaseTempoTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.increaseTempo(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantDecreaseTempoTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.decreaseTempo(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantRestartTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.restart();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantExitTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.exit();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantToggleLoopingTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.toggleLooping();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantSetListenersTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.setButtonListeners(new Controller(view));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void CantAdvanceTest() {
    IView view = new SVGView(new ReadOnlyModel(new AnimationModel()), 10);
    view.advance(new ActionEvent(view,1,"dummy"));
  }


  // test rendering of 1 plus with motions
  @Test
  public void renderWith1PlusWithMotionsTests() throws IOException {
    String result =
            "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http:/" +
                    "/www.w3.org/2000/svg\">\n"
                    + "<rect id=\"p1\" x=\"10\" y=\"10\" width=\"5\" height=\"10\" fill=" +
                    "\"rgb(255,0,0)\" visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" a" +
                    "ttributeName=\"x\" from=\"12\" to=\"42\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attr" +
                    "ibuteName=\"y\" from=\"10\" to=\"40\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"4000ms\" attri" +
                    "buteName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attri" +
                    "buteName=\"width\" from=\"5\" to=\"15\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attri" +
                    "buteName=\"y\" from=\"40\" to=\"35\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attrib" +
                    "uteName=\"height\" from=\"10\" to=\"30\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attribu" +
                    "teName=\"x\" from=\"42\" to=\"42\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"css\" begin=\"5000ms\" dur=\"5000ms\" attrib" +
                    "uteName=\"fill\" from=\"rgb(128,0,128)\" to=\"rgb(255,0,0)\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "<rect id=\"p1\" x=\"10\" y=\"10\" width=\"10\" height=\"5\" fill=\"rgb(" +
                    "255,0,0)\" visibility=\"visible\">\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" att" +
                    "ributeName=\"x\" from=\"10\" to=\"40\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" att" +
                    "ributeName=\"y\" from=\"12\" to=\"42\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"css\" begin=\"1000ms\" dur=\"4000ms\" attr" +
                    "ibuteName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(128,0,128)\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attr" +
                    "ibuteName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attrib" +
                    "uteName=\"height\" from=\"5\" to=\"15\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attri" +
                    "buteName=\"x\" from=\"40\" to=\"35\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attrib" +
                    "uteName=\"y\" from=\"42\" to=\"42\" fill=\"freeze\"/>\n"
                    + "\t<animate attributeType=\"css\" begin=\"5000ms\" dur=\"5000ms\" attrib" +
                    "uteName=\"fill\" from=\"rgb(128,0,128)\" to=\"rgb(255,0,0)\" fill=\"freeze\"/>\n"
                    + "</rect>\n"
                    + "</svg>";

    model.addShape("p1", plus);
    model.addMotion("p1", new Position(40, 40), 1, 5, 10,
            10, purple);
    model.addMotion("p1", new Position(40, 40), 5, 10, 30,
            30, red);
    view.render();
    assertEquals(result, out.toString());


  }

}
