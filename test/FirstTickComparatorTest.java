
import cs3500.animator.model.Color;
import cs3500.animator.model.FirstTickComparator;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for FirstTickComparator: The class provides tests for the methods implemented in the
 * {@link cs3500.animator.model.FirstTickComparator} class.
 */
public class FirstTickComparatorTest {


  //testing compare method
  @Test
  public void testingCompare() {

    IShape r1 = new Rectangle(10, 10, new
        Color(10, 10, 10), new Position(0, 0), 0.1);

    IMotion m1 = new Motion("r1", r1, new Position(0, 0), 10, 15,
        20, 30, new Color(10, 10, 10));
    IMotion m2 = new Motion("r1", r1, new Position(0, 0), 20, 40,
        20, 30, new Color(10, 10, 10));
    IMotion m3 = new Motion("r1", r1, new Position(0, 0), 1, 7,
        20, 30, new Color(10, 10, 10));
    IMotion m4 = new Motion("r1", r1, new Position(0, 0), 20, 79,
        20, 30, new Color(10, 10, 10));

    FirstTickComparator f = new FirstTickComparator();

    assertEquals(-10, f.compare(m1, m2));
    assertEquals(10, f.compare(m2, m1));
    assertEquals(-19, f.compare(m3, m2));
    assertEquals(19, f.compare(m2, m3));
    assertEquals(-9, f.compare(m3, m1));
    assertEquals(9, f.compare(m1, m3));
    assertEquals(0, f.compare(m4, m2));
    assertEquals(0, f.compare(m2, m4));

  }
}

