import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for cs3500.animator.model.Ellipse: Unit tests to make sure cs3500.animator.model.
 * Ellipse can be properly created and modified.
 */
public class EllipseTest {

  IShape e1;
  IShape e2;

  @Before
  public void setUp() {
    e1 = new Ellipse(10, 20,
        new Color(0, 0, 255),
        new Position(10, 10),
        1);

    e2 = new Ellipse(60, 10,
        new Color(0, 255, 0),
        new Position(20, 10),
        0.8);
  }

  /**
   * Tests for shape movement.
   */
  @Test
  public void testMove() {

    assertTrue(e1.getCurrentPosition().equals(new Position(10, 10)));
    e1.move(new Position(40, 50));
    assertTrue(e1.getCurrentPosition().equals(new Position(40, 50)));

  }

  /**
   * Tests for changing shape's dimensions.
   */
  @Test
  public void testChangeDimensions() {
    assertEquals(e1.getHeight(), 20);
    assertEquals(e1.getWidth(), 10);
    e1.changeDimensions(10, -9);
    assertEquals(e1.getHeight(), 30);
    assertEquals(e1.getWidth(), 1);
    e1.changeDimensions(-1, 0);
    assertEquals(e1.getHeight(), 29);
    assertEquals(e1.getWidth(), 1);

  }

  /**
   * Tests for changing shape's dimensions throws IllegalArgumentException if change results in a
   * non-positive width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeDimensionsNonPositiveWidth() {
    assertEquals(e1.getHeight(), 20);
    assertEquals(e1.getWidth(), 10);
    e1.changeDimensions(10, -10);

  }

  /**
   * Tests for changing shape's dimensions throws IllegalArgumentException if change results in a
   * non-positive height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeDimensionsNonPositiveHeight() {
    assertEquals(e1.getHeight(), 20);
    assertEquals(e1.getWidth(), 10);
    e1.changeDimensions(-20, -1);
  }

  /**
   * Tests to check if current position of shape can be correctly obtained.
   */
  @Test
  public void testGetCurrPosition() {
    assertEquals(10, e1.getCurrentPosition().getX());
    assertEquals(10, e1.getCurrentPosition().getY());

    assertEquals(20, e2.getCurrentPosition().getX());
    assertEquals(10, e2.getCurrentPosition().getY());
  }

  /**
   * Tests to check if current color of shape can be correctly obtained.
   */
  @Test
  public void testGetColor() {
    assertEquals(0, e1.getColor().getRed());
    assertEquals(0, e1.getColor().getGreen());
    assertEquals(255, e1.getColor().getBlue());
  }

  /**
   * Tests to color of shape can be changed.
   */
  @Test
  public void testSetColor() {
    assertEquals(0, e1.getColor().getRed());
    assertEquals(0, e1.getColor().getGreen());
    assertEquals(255, e1.getColor().getBlue());

    e1.setColor(new Color(10, 10, 100));
    assertEquals(10, e1.getColor().getRed());
    assertEquals(10, e1.getColor().getGreen());
    assertEquals(100, e1.getColor().getBlue());

  }

  /**
   * Tests to ensure exception is thrown of provided cs3500.animator.model.Color is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetColorNull() {
    assertEquals(0, e1.getColor().getRed());
    assertEquals(0, e1.getColor().getGreen());
    assertEquals(255, e1.getColor().getBlue());
    e1.setColor(null);

  }

  /**
   * Tests to check if current width of shape can be correctly obtained.
   */
  @Test
  public void testGetWidth() {
    assertEquals(10, e1.getWidth());
    assertEquals(60, e2.getWidth());
  }

  /**
   * Tests to check if current height of shape can be correctly obtained.
   */
  @Test
  public void testGetHeight() {
    assertEquals(20, e1.getHeight());
    assertEquals(10, e2.getHeight());
  }

  /**
   * Tests to check if current opacity of shape can be correctly obtained.
   */
  @Test
  public void testGetOpacity() {
    assertEquals(1, e1.getOpacity(), 0.01);
    assertEquals(0.8, e2.getOpacity(), 0.01);
  }

  /**
   * Tests to check if we can change shape's opacity.
   */
  @Test
  public void testSetOpacity() {
    assertEquals(1, e1.getOpacity(), 0.01);
    e1.setOpacity(0.5);
    assertEquals(0.5, e1.getOpacity(), 0.01);
  }

  /**
   * Tests to check we cannot set a negative opacity.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetNegOpacity() {
    assertEquals(1, e1.getOpacity(), 0.01);
    e1.setOpacity(-0.1);
  }

  /**
   * Tests to check we cannot set a opacity greater than 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetGreaterThan1Opacity() {
    assertEquals(1, e1.getOpacity(), 0.01);
    e1.setOpacity(1.1);
  }

  /**
   * Test to make sure we can create a copy of this shape.
   */
  @Test
  public void testClone() {
    IShape e1Copy = e1.clone();
    assertEquals(e1Copy.getWidth(), 10);
    assertEquals(e1Copy.getHeight(), 20);
    assertEquals(0, e1Copy.getColor().getRed());
    assertEquals(0, e1Copy.getColor().getGreen());
    assertEquals(255, e1Copy.getColor().getBlue());
    assertEquals(e1Copy.getOpacity(), 1, 0.01);
    assertTrue(e1Copy.getCurrentPosition().equals(new Position(10, 10)));
    assertFalse(e1 == e1Copy);
  }

  // Testing equals method

  @Test
  public void testEquals() {
    setUp();
    assertEquals(true, this.e1.equals(this.e1.clone()));
    assertEquals(true, this.e1.clone().equals(this.e1));
  }

  @Test
  public void testEqualsColorOff() {
    setUp();
    IShape e4 = new Ellipse(60, 10,
        new Color(0, 25, 0),
        new Position(20, 10),
        0.8);
    assertFalse(this.e2.equals(e4));
  }

  @Test
  public void testWidthOff() {
    setUp();
    IShape e4 = new Ellipse(0, 10,
        new Color(0, 255, 0),
        new Position(20, 10),
        0.8);
    assertFalse(this.e2.equals(e4));
  }

  @Test
  public void testHeightOff() {
    setUp();
    IShape e4 = new Ellipse(60, 0,
        new Color(0, 255, 0),
        new Position(20, 10),
        0.8);
    assertFalse(this.e2.equals(e4));
  }

  @Test
  public void testPositionOff() {
    setUp();
    IShape e4 = new Ellipse(60, 10,
        new Color(0, 255, 0),
        new Position(3, 10),
        0.8);
    assertFalse(this.e2.equals(e4));
  }


  @Test
  public void testOpacityOff() {
    setUp();
    IShape e4 = new Ellipse(60, 10,
        new Color(0, 255, 0),
        new Position(20, 10),
        0.34);
    assertFalse(this.e2.equals(e4));
  }

  // test hashcode
  @Test
  public void testHashcode() {
    setUp();
    assertTrue(e1.hashCode() == e1.clone().hashCode());
    assertFalse(e1.hashCode() == e2.hashCode());
    assertFalse(e2.hashCode() == e1.hashCode());
  }


}
