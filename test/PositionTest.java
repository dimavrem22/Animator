import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.IPosition;
import cs3500.animator.model.Position;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Position: unit tests to ensure that Positions can be created with an x and y
 * coordinate and be manipulated properly.
 */
public class PositionTest {

  IPosition pos1;
  IPosition pos2;
  IPosition pos3;

  @Before
  public void setUp() {
    pos1 = new Position(1, 2);
    pos2 = new Position(2, 1);
    pos3 = new Position(1, 2);
  }

  /**
   * Test that getX() returns proper x-coordinate.
   */
  @Test
  public void testGetX() {

    assertEquals(1, pos1.getX());
    assertEquals(2, pos2.getX());

  }

  /**
   * Test that getY() returns proper y-coordinate.
   */
  @Test
  public void testGetY() {

    assertEquals(2, pos1.getY());
    assertEquals(1, pos2.getY());

  }

  /**
   * Check setPosition() changes the position provide x and y coords.
   */
  @Test
  public void testSetPositionCoords() {
    assertEquals(1, pos1.getX());
    assertEquals(2, pos1.getY());
    pos1.setPosition(10, 20);
    assertEquals(10, pos1.getX());
    assertEquals(20, pos1.getY());
  }

  /**
   * Check setPosition() changes the position provide cs3500.animator.model.IPosition.
   */
  @Test
  public void testSetPositionGivenPosition() {
    assertEquals(1, pos1.getX());
    assertEquals(2, pos1.getY());
    pos1.setPosition(new Position(5, 4));
    assertEquals(5, pos1.getX());
    assertEquals(4, pos1.getY());
  }

  /**
   * Test that clone() method clones the position's coordinates and returns a new position.
   */
  @Test
  public void testClone() {
    assertEquals(1, pos1.getX());
    assertEquals(2, pos1.getY());
    IPosition copy = pos1.clone();
    assertEquals(1, copy.getX());
    assertEquals(2, copy.getY());
    assertFalse(pos1 == copy);
  }

  /**
   * Tests to make sure equals() properly checks for equality between two positions.
   */
  @Test
  public void testEquals() {
    assertTrue(pos1.equals(pos1));
    assertTrue(pos1.equals(pos3));
    assertTrue(pos3.equals(pos1));
    assertFalse(pos1.equals(pos2));
    assertFalse(pos2.equals(pos1));
  }

  /**
   * Tests to make sure hashcode() returns same hashcode for same positions and different hashcodes
   * for different positions.
   */
  @Test
  public void testHashcode() {
    assertEquals(pos1.hashCode(), pos1.hashCode());
    assertEquals(pos1.hashCode(), pos3.hashCode());
    assertEquals(pos3.hashCode(), pos1.hashCode());
    assertNotEquals(pos2.hashCode(), pos1.hashCode());
    assertNotEquals(pos1.hashCode(), pos2.hashCode());
  }


}
