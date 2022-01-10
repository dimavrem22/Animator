import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IColor;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Motion} to make sure motions can bring the shape
 * to their final state and check for overlaps with other Motions.
 */
public class MotionTest {

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

  // Test Constructor

  @Test(expected = IllegalArgumentException.class)
  public void testCOnstructorNullShape() {
    setData();
    IMotion m = new Motion("m", null, origin, 0, 10, 3, 4,
        red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCOnstructorNullName() {
    setData();
    IMotion m = new Motion(null, circle, origin, 0, 10, 3, 4,
        red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCOnstructorNullPosition() {
    setData();
    IMotion m = new Motion("df", circle, null, 0, 10,
        3, 4, red);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCOnstructorNullColor() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCOnstructorLastLessThanStart() {
    setData();
    IMotion m = new Motion("df", circle, origin, 123, 10, 3, 4,
        red);
  }

  // test getShapeName

  @Test
  public void testGetShapeName() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals("df", m.getShapeName());
  }

  // test getShape

  @Test
  public void testGetShape() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(circle, m.getShape());
  }

  // test getFinalPosition

  @Test
  public void testGetFinalPosition() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(new Position(0, 0), m.getFinalPosition());
  }

  // test getFinalWidth
  @Test
  public void testGetFinalWidth() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(3, m.getFinalWidth());
  }

  // test getFinalHeight
  @Test
  public void testGetFinalHeight() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(4, m.getFinalHeight());
  }

  // test getFinalColor
  @Test
  public void testGetFinalColor() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(new Color(255, 0, 0), m.getFinalColor());
  }

  // test getStartTick
  @Test
  public void testGetSTartTick() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(0, m.getStartTick());
  }


  // test getFinalTick
  @Test
  public void testGetFinalTick() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 3, 4,
        red);
    assertEquals(10, m.getLastTick());
  }

  // test causesChange methods
  @Test
  public void testNoChange() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 10, 10,
        red);
    assertFalse(m.causesChangeInColor());
    assertFalse(m.causesChangeInPosition());
    assertFalse(m.causesChangeInDimensions());
  }

  @Test
  public void testPositionChange() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 10, 10,
        red);
    assertFalse(m.causesChangeInColor());
    assertTrue(m.causesChangeInPosition());
    assertFalse(m.causesChangeInDimensions());
  }

  @Test
  public void testChangeColor() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 10, 10,
        blue);
    assertTrue(m.causesChangeInColor());
    assertFalse(m.causesChangeInPosition());
    assertFalse(m.causesChangeInDimensions());
  }

  @Test
  public void testChangeDimensions() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 34, 323,
        red);
    assertFalse(m.causesChangeInColor());
    assertFalse(m.causesChangeInPosition());
    assertTrue(m.causesChangeInDimensions());
  }

  @Test
  public void testChangeAll() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 13, 10,
        green);
    assertTrue(m.causesChangeInColor());
    assertTrue(m.causesChangeInPosition());
    assertTrue(m.causesChangeInDimensions());
  }

  // testing goToFinalState

  @Test
  public void testGotoFinalState() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 13, 10,
        green);

    // in initial condition of circle object
    assertEquals(red, circle.getColor());
    assertEquals(origin, circle.getCurrentPosition());
    assertEquals(10, circle.getHeight());
    assertEquals(10, circle.getWidth());

    m.goToFinalState();

    // final condition of circle object
    assertEquals(green, circle.getColor());
    assertEquals(position1, circle.getCurrentPosition());
    assertEquals(10, circle.getHeight());
    assertEquals(13, circle.getWidth());
  }

  // testing overlaps

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 13, 10,
        green);
    m.overlaps(null);
  }


  @Test
  public void testOverlapsNoOverlapTime() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 13, 10,
        green);
    IMotion m2 = new Motion("df", circle, position1, 11, 14, 13, 10,
        green);
    assertFalse(m.overlaps(m2));
    assertFalse(m2.overlaps(m));
  }

  @Test
  public void testOverlapsPositionColor() {
    setData();
    IMotion m = new Motion("df", circle, origin, 2, 10, 10, 10,
        green);
    IMotion m2 = new Motion("df", circle, position1, 1, 8, 10, 10,
        red);
    assertTrue(m.overlaps(m2));
    assertTrue(m2.overlaps(m));
  }

  @Test
  public void testOverlapsDimensionsColor() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 10, 10,
        green);
    IMotion m2 = new Motion("df", circle, origin, 5, 8, 13, 10,
        red);
    assertTrue(m.overlaps(m2));
    assertTrue(m2.overlaps(m));
  }

  @Test
  public void testOverlapsDimensionsPosition() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 10, 10,
        red);
    IMotion m2 = new Motion("df", circle, origin, 5, 8, 13, 10,
        red);
    assertTrue(m.overlaps(m2));
    assertTrue(m2.overlaps(m));
  }

  @Test
  public void testOverlapsPosition() {
    setData();
    IMotion m = new Motion("df", circle, position1, 0, 10, 10, 10,
        red);
    IMotion m2 = new Motion("df", circle, position1, 5, 10, 10, 10,
        red);
    assertTrue(m.overlaps(m2));
    assertTrue(m2.overlaps(m));
  }

  @Test
  public void testOverlapsColor() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 10, 10,
        blue);
    IMotion m2 = new Motion("df", circle, origin, 5, 10, 10, 10,
        green);
    assertTrue(m.overlaps(m2));
    assertTrue(m2.overlaps(m));
  }


  @Test
  public void testOverlapsDimensions() {
    setData();
    IMotion m = new Motion("df", circle, origin, 0, 10, 34, 34,
        red);
    IMotion m2 = new Motion("df", circle, origin, 5, 11, 139, 342,
        red);
    assertTrue(m.overlaps(m2));
    assertTrue(m2.overlaps(m));
  }


  // testing clone motion

  @Test
  public void testClone() {
    IMotion m = new Motion("df", circle, origin, 0, 10, 34, 34,
        red);
    IMotion m2 = m.clone();

    assertEquals(m.getShapeName(), m2.getShapeName());
    assertEquals(m.getShape(), m2.getShape());
    assertEquals(m.getFinalColor(), m2.getFinalColor());
    assertEquals(m.getFinalHeight(), m2.getFinalHeight());
    assertEquals(m.getFinalWidth(), m2.getFinalWidth());
    assertEquals(m.getFinalPosition(), m2.getFinalPosition());
    assertEquals(m.getStartTick(), m2.getStartTick());
    assertEquals(m.getLastTick(), m2.getLastTick());
  }

}
