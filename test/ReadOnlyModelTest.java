import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides a class for testing {@link cs3500.animator.model.ReadOnlyModel}.
 * Unit tests to ensure model state can be obtained.
 */
public class ReadOnlyModelTest {

  IShape rectangle;
  IShape circle;

  IColor red;
  IColor green;
  IColor blue;

  IPosition origin;
  IPosition position1;


  @Before
  public void setData() {
    origin = new Position(0, 0);
    position1 = new Position(2, 4);

    red = new Color(255, 0, 0);
    green = new Color(0, 255, 0);
    blue = new Color(0, 0, 255);

    circle = new Ellipse(10, 10, red, origin, 1);
    rectangle = new Rectangle(5, 2, red, origin, 1);
  }

  // Makes sure that when animation is first initialized
  // instances variable are empty.
  @Test
  public void testEmptyAnimation() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);

    assertEquals(0, model.getAllShapeNames().size());
    assertEquals(0, model.getAllShapes().size());
  }





  // testing hasShape

  @Test(expected = IllegalArgumentException.class)
  public void testHasShapeNullName() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("circle", circle);
    model.hasShape(null);
  }

  @Test
  public void testHasShape() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("circle", circle);

    assertTrue(model.hasShape("circle"));
    assertFalse(model.hasShape("rec"));
  }

  // testing getShape

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeNullName() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("circle", circle);
    model.getShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeInvalid() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("circle", circle);
    model.getShape("rec");
  }

  @Test
  public void testGetShape() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("circle", circle);
    assertEquals(circle, model.getShape("circle"));
  }


  // testing getMotions

  @Test
  public void testGetMotions() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getMotions("cir").size());
    assertEquals(0, model.getMotions("cir").get(0).getStartTick());
    assertEquals(10, model.getMotions("cir").get(0).getLastTick());
    assertEquals(45, model.getMotions("cir").get(0).getFinalHeight());
    assertEquals(30, model.getMotions("cir").get(0).getFinalWidth());
    assertEquals(red, model.getMotions("cir").get(0).getFinalColor());
    assertEquals(origin, model.getMotions("cir").get(0).getFinalPosition());

    mm.addMotion("cir", position1, 23, 100, 30, 45, red);

    assertEquals(2, model.getMotions("cir").size());
    assertEquals(23, model.getMotions("cir").get(1).getStartTick());
    assertEquals(100, model.getMotions("cir").get(1).getLastTick());
    assertEquals(45, model.getMotions("cir").get(1).getFinalHeight());
    assertEquals(30, model.getMotions("cir").get(1).getFinalWidth());
    assertEquals(red, model.getMotions("cir").get(1).getFinalColor());
    assertEquals(position1, model.getMotions("cir").get(1).getFinalPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionsNullName() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    model.getMotions(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionsInvalidName() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    model.getMotions("rec");
  }

  // testing getAllShapes

  @Test
  public void getAllShapes() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);

    assertEquals(0, model.getAllShapes().size());

    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getAllShapes().size());
    assertEquals(circle, model.getAllShapes().get(0));

    mm.addShape("rec", rectangle);

    assertEquals(2, model.getAllShapes().size());
    assertEquals(rectangle, model.getAllShapes().get(1));
  }

  // testing getAllShapeNames

  @Test
  public void getAllShapesNames() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);

    assertEquals(0, model.getAllShapes().size());

    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getAllShapeNames().size());
    assertEquals("cir", model.getAllShapeNames().get(0));

    mm.addShape("rec", rectangle);

    assertEquals(2, model.getAllShapeNames().size());
    assertEquals("rec", model.getAllShapeNames().get(1));
  }

  // testing calculateLastTick

  @Test
  public void calculateLastTick() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);

    assertEquals(0, model.calculateLastTick());

    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(10, model.calculateLastTick());

    mm.addMotion("cir", origin, 20, 100, 30, 45, red);

    assertEquals(100, model.calculateLastTick());

    mm.addShape("rec", rectangle);
    mm.addMotion("rec", position1, 32, 234, 33, 34, red);

    assertEquals(234, model.calculateLastTick());
  }

  // test shapeAtTick

  @Test(expected = IllegalArgumentException.class)
  public void testShapeAtTickNullName() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);

    model.shapeAtTick(null, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapeAtTickInvalidName() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", origin, 0, 10, 30, 45, red);
    model.shapeAtTick("rec", 9);
  }


  @Test
  public void testShapeAtTick() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", position1, 1, 10, 30, 45, red);
    mm.addMotion("cir", origin, 20, 40, 100, 100, green);

    //before motion
    assertEquals(circle, model.shapeAtTick("cir", 1));

    // should be false because a clone is made
    assertFalse(model.shapeAtTick("cir", 2).equals(circle));

    assertEquals(1, model.shapeAtTick("cir", 5).getCurrentPosition().getX());
    assertEquals(2, model.shapeAtTick("cir", 5).getCurrentPosition().getY());
    assertEquals(19, model.shapeAtTick("cir", 5).getWidth());
    assertEquals(26, model.shapeAtTick("cir", 5).getHeight());
    assertEquals(0, model.shapeAtTick("cir", 5).getColor().getGreen());
    assertEquals(255, model.shapeAtTick("cir", 5).getColor().getRed());
    assertEquals(0, model.shapeAtTick("cir", 5).getColor().getBlue());

    assertEquals(2, model.shapeAtTick("cir", 11).getCurrentPosition().getX());
    assertEquals(4, model.shapeAtTick("cir", 11).getCurrentPosition().getY());
    assertEquals(30, model.shapeAtTick("cir", 11).getWidth());
    assertEquals(45, model.shapeAtTick("cir", 11).getHeight());
    assertEquals(0, model.shapeAtTick("cir", 11).getColor().getGreen());
    assertEquals(255, model.shapeAtTick("cir", 11).getColor().getRed());
    assertEquals(0, model.shapeAtTick("cir", 11).getColor().getBlue());

    assertEquals(1, model.shapeAtTick("cir", 30).getCurrentPosition().getX());
    assertEquals(2, model.shapeAtTick("cir", 30).getCurrentPosition().getY());
    assertEquals(65, model.shapeAtTick("cir", 30).getWidth());
    assertEquals(73, model.shapeAtTick("cir", 30).getHeight());
    assertEquals(128, model.shapeAtTick("cir", 30).getColor().getGreen());
    assertEquals(128, model.shapeAtTick("cir", 30).getColor().getRed());
    assertEquals(0, model.shapeAtTick("cir", 30).getColor().getBlue());
  }


  // testing matching start and end states of consecutive motions
  @Test
  public void testMatchingStatesOfStartAndEnd() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", position1, 1, 10, 30, 45, red);
    mm.addMotion("cir", origin, 10, 20, 100, 100, green);

    //before motion
    assertEquals(circle, model.shapeAtTick("cir", 1));

    // should be false because a clone is made
    assertFalse(model.shapeAtTick("cir", 2).equals(circle));

    assertEquals(2, model.shapeAtTick("cir", 10).getCurrentPosition().getX());
    assertEquals(4, model.shapeAtTick("cir", 10).getCurrentPosition().getY());
    assertEquals(30, model.shapeAtTick("cir", 10).getWidth());
    assertEquals(45, model.shapeAtTick("cir", 10).getHeight());
    assertEquals(0, model.shapeAtTick("cir", 10).getColor().getGreen());
    assertEquals(255, model.shapeAtTick("cir", 10).getColor().getRed());
    assertEquals(0, model.shapeAtTick("cir", 10).getColor().getBlue());
  }

  @Test
  public void testMatchingStatesOfStartAndEnd2() {
    setData();
    IAnimationModel mm = new AnimationModel();
    IReadOnlyModel model = new ReadOnlyModel(mm);
    mm.addShape("cir", circle);
    mm.addMotion("cir", position1, 1, 10, 30, 45, red);
    mm.addMotion("cir", origin, 20, 40, 100, 100, green);

    //before motion
    assertEquals(circle, model.shapeAtTick("cir", 1));

    // should be false because a clone is made
    assertFalse(model.shapeAtTick("cir", 2).equals(circle));

    assertEquals(2, model.shapeAtTick("cir", 10).getCurrentPosition().getX());
    assertEquals(4, model.shapeAtTick("cir", 10).getCurrentPosition().getY());
    assertEquals(30, model.shapeAtTick("cir", 10).getWidth());
    assertEquals(45, model.shapeAtTick("cir", 10).getHeight());
    assertEquals(0, model.shapeAtTick("cir", 10).getColor().getGreen());
    assertEquals(255, model.shapeAtTick("cir", 10).getColor().getRed());
    assertEquals(0, model.shapeAtTick("cir", 10).getColor().getBlue());

    assertEquals(2, model.shapeAtTick("cir", 20).getCurrentPosition().getX());
    assertEquals(4, model.shapeAtTick("cir", 20).getCurrentPosition().getY());
    assertEquals(30, model.shapeAtTick("cir", 20).getWidth());
    assertEquals(45, model.shapeAtTick("cir", 20).getHeight());
    assertEquals(0, model.shapeAtTick("cir", 20).getColor().getGreen());
    assertEquals(255, model.shapeAtTick("cir", 20).getColor().getRed());
    assertEquals(0, model.shapeAtTick("cir", 20).getColor().getBlue());

  }

}
