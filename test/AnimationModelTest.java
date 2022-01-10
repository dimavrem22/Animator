import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for cs3500.animator.model.AnimationModel: Units tests to make sure the
 * cs3500.animator.model.AnimationModel class offers all
 * specified functionality such as adding Motions, adding shapes,
 * removing shapes, getting shape information at a specified tick,
 * and proving information about the current keyframe.
 */
public class AnimationModelTest {

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
    IAnimationModel model = new AnimationModel();

    assertEquals(0, model.getAllShapeNames().size());
    assertEquals(0, model.getAllShapes().size());
  }

  // test negative tick when getting the shape at the provided tick
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTick() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("c1", circle);
    model.shapeAtTick("c1",-1);
  }

  // test negative tick when adding motion
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTickAddMotionFirstTick() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("c1", circle);
    model.addMotion("c1", origin, -1, 10, 10, 10, red);
  }

  // test negative tick when adding motion
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTickAddMotionLastTick() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("c1", circle);
    model.addMotion("c1", origin, 1, -2, 10, 10, red);
  }


  // test last tick less than start tick when adding motion
  @Test(expected = IllegalArgumentException.class)
  public void testLastTickLessThanStartTick() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("c1", circle);
    model.addMotion("c1", origin, 2, 1, 10, 10, red);
  }

  // testing addShape

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape(null, circle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullShape() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circe", null);
  }

  @Test
  public void testAddShapeGood() {
    setData();
    IAnimationModel model = new AnimationModel();

    assertEquals(0, model.getAllShapeNames().size());
    assertEquals(0, model.getAllShapes().size());
    model.addShape("circle", circle);

    assertEquals(1, model.getAllShapeNames().size());
    assertEquals(1, model.getAllShapes().size());

    assertEquals("circle", model.getAllShapeNames().get(0));
    assertEquals(circle, model.getAllShapes().get(0));

    model.addShape("rec", rectangle);

    assertEquals(2, model.getAllShapeNames().size());
    assertEquals(2, model.getAllShapes().size());

    assertEquals("rec", model.getAllShapeNames().get(1));
    assertEquals(rectangle, model.getAllShapes().get(1));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddSameShapeName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);

    model.addShape("circle", rectangle);
  }

  // testing removeShape

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);
    model.removeShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvalidShapeName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);
    model.removeShape("rec");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveFromEmpty() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.removeShape("rec");
  }

  @Test
  public void testRemoveShape() {
    setData();
    IAnimationModel model = new AnimationModel();

    assertEquals(0, model.getAllShapeNames().size());
    assertEquals(0, model.getAllShapes().size());
    model.addShape("circle", circle);
    assertEquals(1, model.getAllShapeNames().size());
    assertEquals(1, model.getAllShapes().size());
    assertEquals("circle", model.getAllShapeNames().get(0));
    assertEquals(circle, model.getAllShapes().get(0));

    model.addMotion("circle", origin, 0, 10, 10, 10, red);
    assertEquals(1, model.getMotions("circle").size());

    model.removeShape("circle");

    assertEquals(0, model.getAllShapeNames().size());
    assertEquals(0, model.getAllShapes().size());
  }

  // testing hasShape

  @Test(expected = IllegalArgumentException.class)
  public void testHasShapeNullName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);
    model.hasShape(null);
  }

  @Test
  public void testHasShape() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);

    assertTrue(model.hasShape("circle"));
    assertFalse(model.hasShape("rec"));
  }

  // testing getShape

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeNullName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);
    model.getShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeInvalid() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);
    model.getShape("rec");
  }

  @Test
  public void testGetShape() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("circle", circle);
    assertEquals(circle, model.getShape("circle"));
  }

  // Testing addMotion

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNullName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion(null, origin, 10, 24, 30, 45, red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNulPosition() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", null, 10, 24, 30, 45, red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNulColor() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 10, 24, 30, 45, null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("rec", origin, 10, 24, 30, 45, red);
  }


  @Test
  public void testAddMotionGood() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 10, 24, 30, 45, red);

    assertEquals(1, model.getMotions("cir").size());
    assertEquals(10, model.getMotions("cir").get(0).getStartTick());
    assertEquals(24, model.getMotions("cir").get(0).getLastTick());
    assertEquals(45, model.getMotions("cir").get(0).getFinalHeight());
    assertEquals(30, model.getMotions("cir").get(0).getFinalWidth());
    assertEquals(red, model.getMotions("cir").get(0).getFinalColor());
    assertEquals(origin, model.getMotions("cir").get(0).getFinalPosition());
  }

  @Test
  public void testAddMotionNoOverlap() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getMotions("cir").size());
    assertEquals(0, model.getMotions("cir").get(0).getStartTick());
    assertEquals(10, model.getMotions("cir").get(0).getLastTick());
    assertEquals(45, model.getMotions("cir").get(0).getFinalHeight());
    assertEquals(30, model.getMotions("cir").get(0).getFinalWidth());
    assertEquals(red, model.getMotions("cir").get(0).getFinalColor());
    assertEquals(origin, model.getMotions("cir").get(0).getFinalPosition());

    model.addMotion("cir", position1, 23, 100, 30, 45, red);

    assertEquals(2, model.getMotions("cir").size());
    assertEquals(23, model.getMotions("cir").get(1).getStartTick());
    assertEquals(100, model.getMotions("cir").get(1).getLastTick());
    assertEquals(45, model.getMotions("cir").get(1).getFinalHeight());
    assertEquals(30, model.getMotions("cir").get(1).getFinalWidth());
    assertEquals(red, model.getMotions("cir").get(1).getFinalColor());
    assertEquals(position1, model.getMotions("cir").get(1).getFinalPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionOverlap() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);

    model.addMotion("cir", origin, 0, 10, 30, 45, blue);
    model.addMotion("cir", position1, 5, 100, 30, 45, green);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionLastLessThanFirst() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 123, 10, 30, 45, blue);
  }

  // testing getMotions

  @Test
  public void testGetMotions() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getMotions("cir").size());
    assertEquals(0, model.getMotions("cir").get(0).getStartTick());
    assertEquals(10, model.getMotions("cir").get(0).getLastTick());
    assertEquals(45, model.getMotions("cir").get(0).getFinalHeight());
    assertEquals(30, model.getMotions("cir").get(0).getFinalWidth());
    assertEquals(red, model.getMotions("cir").get(0).getFinalColor());
    assertEquals(origin, model.getMotions("cir").get(0).getFinalPosition());

    model.addMotion("cir", position1, 23, 100, 30, 45, red);

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
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    model.getMotions(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionsInvalidName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    model.getMotions("rec");
  }

  // testing getAllShapes

  @Test
  public void getAllShapes() {
    setData();
    IAnimationModel model = new AnimationModel();

    assertEquals(0, model.getAllShapes().size());

    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getAllShapes().size());
    assertEquals(circle, model.getAllShapes().get(0));

    model.addShape("rec", rectangle);

    assertEquals(2, model.getAllShapes().size());
    assertEquals(rectangle, model.getAllShapes().get(1));
  }

  // testing getAllShapeNames

  @Test
  public void getAllShapesNames() {
    setData();
    IAnimationModel model = new AnimationModel();

    assertEquals(0, model.getAllShapes().size());

    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(1, model.getAllShapeNames().size());
    assertEquals("cir", model.getAllShapeNames().get(0));

    model.addShape("rec", rectangle);

    assertEquals(2, model.getAllShapeNames().size());
    assertEquals("rec", model.getAllShapeNames().get(1));
  }

  // testing calculateLastTick

  @Test
  public void calculateLastTick() {
    setData();
    IAnimationModel model = new AnimationModel();

    assertEquals(0, model.calculateLastTick());

    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    assertEquals(10, model.calculateLastTick());

    model.addMotion("cir", origin, 20, 100, 30, 45, red);

    assertEquals(100, model.calculateLastTick());

    model.addShape("rec", rectangle);
    model.addMotion("rec", position1, 32, 234, 33, 34, red);

    assertEquals(234, model.calculateLastTick());
  }

  // test shapeAtTick

  @Test(expected = IllegalArgumentException.class)
  public void testShapeAtTickNullName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);

    model.shapeAtTick(null, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapeAtTickInvalidName() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", origin, 0, 10, 30, 45, red);
    model.shapeAtTick("rec", 9);
  }


  @Test
  public void testShapeAtTick() {
    setData();
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", position1, 1, 10, 30, 45, red);
    model.addMotion("cir", origin, 20, 40, 100, 100, green);

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
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", position1, 1, 10, 30, 45, red);
    model.addMotion("cir", origin, 10, 20, 100, 100, green);

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
    IAnimationModel model = new AnimationModel();
    model.addShape("cir", circle);
    model.addMotion("cir", position1, 1, 10, 30, 45, red);
    model.addMotion("cir", origin, 20, 40, 100, 100, green);

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

  // test add TimeInterval
  @Test
  public void testAddTimeInterval() {
    IAnimationModel model = new AnimationModel();
    assertEquals(-1,model.getTempoAtTick(1));
    model.addTimeInterval(1,10,10);
    model.addTimeInterval(10,20,5);
    assertEquals(10,model.getTempoAtTick(1));
    assertEquals(10,model.getTempoAtTick(2));
    assertEquals(10,model.getTempoAtTick(10));
    assertEquals(5,model.getTempoAtTick(11));
    assertEquals(-1,model.getTempoAtTick(41));
    model.addTimeInterval(40,50,1);
    assertEquals(1,model.getTempoAtTick(41));
  }

  // test adding time intervals overlap
  @Test(expected = IllegalArgumentException.class)
  public void testAddTimeIntervalsOverlap() {
    IAnimationModel model = new AnimationModel();
    model.addTimeInterval(1,10,10);
    model.addTimeInterval(2,11,10);
  }

  // test get tempo at tick
  @Test
  public void testGetTempoAtTick() {
    IAnimationModel model = new AnimationModel();
    model.addTimeInterval(1,10,10);
    model.addTimeInterval(10,20,5);
    assertEquals(10,model.getTempoAtTick(1));
    assertEquals(10,model.getTempoAtTick(2));
    assertEquals(10,model.getTempoAtTick(10));
    assertEquals(5,model.getTempoAtTick(11));
  }

}
