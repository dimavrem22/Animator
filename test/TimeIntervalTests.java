import cs3500.animator.model.ITimeInterval;
import cs3500.animator.model.TimeInterval;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class containing tests for {@link TimeInterval}.
 */
public class TimeIntervalTests {

    // testing constructor

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTimeInterval() {
        ITimeInterval ti = new TimeInterval(9, 2, 4);
    }

    // testing getStartTick

    @Test
    public void testGetStartTick() {
        ITimeInterval ti1 = new TimeInterval(1, 2, 4);
        ITimeInterval ti2 = new TimeInterval(3, 678, 2);
        assertEquals(1, ti1.getStartTick());
        assertEquals(3, ti2.getStartTick());
    }

    // testing getENdTick

    @Test
    public void testGetEndTick() {
        ITimeInterval ti1 = new TimeInterval(1, 2, 4);
        ITimeInterval ti2 = new TimeInterval(3, 678, 2);
        assertEquals(2, ti1.getEndTick());
        assertEquals(678, ti2.getEndTick());
    }

    // testing getTempo

    @Test
    public void testGetTempo() {
        ITimeInterval ti1 = new TimeInterval(1, 2, 4);
        ITimeInterval ti2 = new TimeInterval(3, 678, 2);
        assertEquals(4, ti1.getTempo());
        assertEquals(2, ti2.getTempo());
    }


}
