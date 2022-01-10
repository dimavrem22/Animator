import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for cs3500.animator.model.Rectangle: Unit tests to make sure cs3500.animator.model.
 * Rectangle can be properly created and modified.
 */
public class RectangleTest {

  IShape r1;
  IShape r2;

  @Before
  public void setUp() {
    r1 = new Rectangle(10,20,
        new Color(0,0,255),
        new Position(10,10),
        1);

    r2 = new Rectangle(60,10,
        new Color(0,255,0),
        new Position(20,10),
        0.8);
  }

  /**
   * Tests for shape movement.
   */
  @Test
  public void testMove() {

    assertTrue(r1.getCurrentPosition().equals(new Position(10,10)));
    r1.move(new Position(40,50));
    assertTrue(r1.getCurrentPosition().equals(new Position(40,50)));

  }

  /**
   * Tests for changing shape's dimensions.
   */
  @Test
  public void testChangeDimensions() {
    assertEquals(r1.getHeight(),20);
    assertEquals(r1.getWidth(),10);
    r1.changeDimensions(10,-9);
    assertEquals(r1.getHeight(),30);
    assertEquals(r1.getWidth(),1);
    r1.changeDimensions(-1,0);
    assertEquals(r1.getHeight(),29);
    assertEquals(r1.getWidth(),1);

  }

  /**
   * Tests for changing shape's dimensions throws IllegalArgumentException
   * if change results in a non-positive width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeDimensionsNonPositiveWidth() {
    assertEquals(r1.getHeight(),20);
    assertEquals(r1.getWidth(),10);
    r1.changeDimensions(10,-10);

  }

  /**
   * Tests for changing shape's dimensions throws IllegalArgumentException
   * if change results in a non-positive height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeDimensionsNonPositiveHeight() {
    assertEquals(r1.getHeight(),20);
    assertEquals(r1.getWidth(),10);
    r1.changeDimensions(-20,-1);
  }

  /**
   * Tests to check if current position of shape
   * can be correctly obtained.
   */
  @Test
  public void testGetCurrPosition() {
    assertEquals(10,r1.getCurrentPosition().getX());
    assertEquals(10,r1.getCurrentPosition().getY());

    assertEquals(20,r2.getCurrentPosition().getX());
    assertEquals(10,r2.getCurrentPosition().getY());
  }

  /**
   * Tests to check if current color of shape
   * can be correctly obtained.
   */
  @Test
  public void testGetColor() {
    assertEquals(0,r1.getColor().getRed());
    assertEquals(0,r1.getColor().getGreen());
    assertEquals(255,r1.getColor().getBlue());
  }

  /**
   * Tests to color of shape can be changed.
   */
  @Test
  public void testSetColor() {
    assertEquals(0,r1.getColor().getRed());
    assertEquals(0,r1.getColor().getGreen());
    assertEquals(255,r1.getColor().getBlue());

    r1.setColor(new Color(10,10,100));
    assertEquals(10,r1.getColor().getRed());
    assertEquals(10,r1.getColor().getGreen());
    assertEquals(100,r1.getColor().getBlue());

  }

  /**
   * Tests to ensure exception is thrown of provided cs3500.animator.model.Color
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetColorNull() {
    assertEquals(0,r1.getColor().getRed());
    assertEquals(0,r1.getColor().getGreen());
    assertEquals(255,r1.getColor().getBlue());
    r1.setColor(null);

  }

  /**
   * Tests to check if current width of shape
   * can be correctly obtained.
   */
  @Test
  public void testGetWidth() {
    assertEquals(10,r1.getWidth());
    assertEquals(60,r2.getWidth());
  }

  /**
   * Tests to check if current height of shape
   * can be correctly obtained.
   */
  @Test
  public void testGetHeight() {
    assertEquals(20,r1.getHeight());
    assertEquals(10,r2.getHeight());
  }

  /**
   * Tests to check if current opacity of shape
   * can be correctly obtained.
   */
  @Test
  public void testGetOpacity() {
    assertEquals(1,r1.getOpacity(),0.01);
    assertEquals(0.8,r2.getOpacity(),0.01);
  }

  /**
   * Tests to check if we can change shape's opacity.
   */
  @Test
  public void testSetOpacity() {
    assertEquals(1,r1.getOpacity(),0.01);
    r1.setOpacity(0.5);
    assertEquals(0.5,r1.getOpacity(),0.01);
  }

  /**
   * Tests to check we cannot set a negative opacity.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetNegOpacity() {
    assertEquals(1,r1.getOpacity(),0.01);
    r1.setOpacity(-0.1);
  }

  /**
   * Tests to check we cannot set a opacity greater than 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetGreaterThan1Opacity() {
    assertEquals(1,r1.getOpacity(),0.01);
    r1.setOpacity(1.1);
  }

  /**
   * Test to make sure we can create a copy of this shape.
   */
  @Test
  public void testClone() {
    IShape r1Copy = r1.clone();
    assertEquals(r1Copy.getWidth(),10);
    assertEquals(r1Copy.getHeight(),20);
    assertEquals(0,r1Copy.getColor().getRed());
    assertEquals(0,r1Copy.getColor().getGreen());
    assertEquals(255,r1Copy.getColor().getBlue());
    assertEquals(r1Copy.getOpacity(),1,0.01);
    assertTrue(r1Copy.getCurrentPosition().equals(new Position(10,10)));
    assertFalse(r1 == r1Copy);
  }


  // Testing equals

  @Test
  public void testEquals() {
    setUp();
    assertEquals(true, this.r1.equals(this.r1.clone()));
    assertEquals(true, this.r1.clone().equals(this.r1));
  }

  @Test
  public void testEqualsColorOff() {
    setUp();
    IShape r4 = new Rectangle(60,10,
            new Color(0,25,0),
            new Position(20,10),
            0.8);
    assertFalse(this.r1.equals(r4));
    assertFalse(r1.equals(r4));
  }

  @Test
  public void testWidthOff() {
    setUp();
    IShape r4 = new Rectangle(0,10,
            new Color(0,255,0),
            new Position(20,10),
            0.8);
    assertFalse(this.r1.equals(r4));
    assertFalse(r4.equals(r1));
  }

  @Test
  public void testHeightOff() {
    setUp();
    IShape r4 = new Rectangle(60,0,
            new Color(0,255,0),
            new Position(20,10),
            0.8);
    assertFalse(this.r1.equals(r4));
    assertFalse(r4.equals(r1));
  }

  @Test
  public void testPositionOff() {
    setUp();
    IShape r4 = new Rectangle(60,10,
            new Color(0,255,0),
            new Position(3,10),
            0.8);
    assertFalse(this.r1.equals(r4));
  }


  @Test
  public void testOpacityOff() {
    setUp();
    IShape r4 = new Rectangle(60,10,
            new Color(0,255,0),
            new Position(20,10),
            0.34);
    assertFalse(this.r1.equals(r4));
    assertFalse(r4.equals(r1));
  }

  @Test
  public void testEqualsEllipse() {
    setUp();

    IShape rec = new Rectangle(60,10,
            new Color(0,255,0),
            new Position(20,10),
            0.34);

    IShape ell = new Ellipse(60,10,
            new Color(0,255,0),
            new Position(20,10),
            0.34);

    assertFalse(rec.equals(ell));
  }

  // test hashcode
  @Test
  public void testHashcode() {
    setUp();
    assertTrue(r1.hashCode() == r1.clone().hashCode());
    assertFalse(r1.hashCode() == r2.hashCode());
    assertFalse(r2.hashCode() == r1.hashCode());
  }



}
