import cs3500.animator.model.Color;
import cs3500.animator.model.IColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for Color to make sure
 * rgb values can be obtained and exceptions are thrown
 * in the creation of invalid Color Objects.
 */
public class ColorTest {


  IColor red;
  IColor green;
  IColor blue;

  IColor random;


  @Before
  public void setData() {
    red = new Color(255, 0, 0);
    green = new Color(0, 255, 0);
    blue = new Color(0, 0, 255);
    random = new Color(20, 40, 60);
  }

  // Testing constructor

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidColor() {
    IColor color = new Color(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidColor2() {
    IColor color = new Color(260, 0, 0);
  }

  @Test
  public void TestValidColor() {
    IColor color = new Color(30, 20, 10);

    // this test fails RN should be Override Equals method?

    assertEquals(new Color(30, 20, 10), color);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidConstructorArray() {
    int[] numbers = new int[4];
    numbers[0] = 0;
    numbers[1] = 0;
    numbers[2] = 0;
    numbers[3] = 0;

    IColor color = new Color(numbers);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidConstructorArray2() {
    int[] numbers = new int[3];
    numbers[0] = 0;
    numbers[1] = 0;
    numbers[2] = 343;

    IColor color = new Color(numbers);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidConstructorArray3() {
    int[] numbers = new int[3];
    numbers[0] = 0;
    numbers[1] = -34;
    numbers[2] = 0;

    IColor color = new Color(numbers);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidConstructorArray4() {
    int[] numbers = new int[3];
    numbers[0] = 287;
    numbers[1] = 0;
    numbers[2] = 0;

    IColor color = new Color(numbers);
  }

  // Testing getRed

  @Test
  public void testGetRed() {
    setData();
    assertEquals(red.getRed(), 255);
    assertEquals(green.getRed(), 0);
    assertEquals(blue.getGreen(), 0);
  }


  // Testing getBlue
  @Test
  public void testGetBlue() {
    setData();
    assertEquals(red.getBlue(), 0);
    assertEquals(green.getBlue(), 0);
    assertEquals(blue.getBlue(), 255);
  }

  // Testing getGreen

  @Test
  public void testGetGreen() {
    setData();
    assertEquals(red.getGreen(), 0);
    assertEquals(green.getGreen(), 255);
    assertEquals(blue.getGreen(), 0);
  }


  // Testing getRGB
  @Test
  public void testGetRGB() {
    setData();
    int[] result = random.getRGB();
    assertEquals(result[0], 20);
    assertEquals(result[1], 40);
    assertEquals(result[2], 60);
  }

  // Testing clone

  @Test
  public void testClone() {
    setData();
    IColor newColor = random.clone();

    int[] result = newColor.getRGB();
    assertEquals(result[0], 20);
    assertEquals(result[1], 40);
    assertEquals(result[2], 60);
  }

  // testing equals method
  @Test
  public void testEquals() {
    setData();
    assertTrue(red.equals(red));
    assertTrue(red.equals(red.clone()));
  }

  @Test
  public void testNotEquals() {
    setData();
    assertFalse(red.equals(blue));
    assertFalse(blue.equals(red));
  }

  // testing hashcode
  @Test
  public void testHashcode() {
    setData();
    assertTrue(red.hashCode() == red.hashCode());
    assertTrue(red.hashCode() == red.clone().hashCode());
    assertFalse(red.hashCode() == blue.hashCode());
  }



}
