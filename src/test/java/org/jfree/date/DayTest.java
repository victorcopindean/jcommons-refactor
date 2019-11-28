package org.jfree.date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;

/**
 * Some JUnit tests for the {@link Day} class.
 */
public class DayTest extends TestCase {

    public DayTest(final String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(DayTest.class);
    }

    /**
     * Problem that the conversion of days to strings returns the right result.  Actually, this
     * result depends on the Locale so this test needs to be modified.
     */
    public void testDayToString() {

        assertEquals("Monday", Day.MONDAY.toString());
        assertEquals("Tuesday", Day.TUESDAY.toString());
        assertEquals("Wednesday", Day.WEDNESDAY.toString());
        assertEquals("Thursday", Day.THURSDAY.toString());
        assertEquals("Friday", Day.FRIDAY.toString());
        assertEquals("Saturday", Day.SATURDAY.toString());
        assertEquals("Sunday", Day.SUNDAY.toString());
    }

    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the
     * default locale doesn't use English weekday names...devise a better test!
     */
    public void testParse() {

        assertEquals(Day.MONDAY, Day.parse("Monday"));
        assertEquals(Day.TUESDAY, Day.parse("Tuesday"));
        assertEquals(Day.WEDNESDAY, Day.parse("Wednesday"));
        assertEquals(Day.THURSDAY, Day.parse("Thursday"));
        assertEquals(Day.FRIDAY, Day.parse("Friday"));
        assertEquals(Day.SATURDAY, Day.parse("Saturday"));
        assertEquals(Day.SUNDAY, Day.parse("Sunday"));

        assertEquals(Day.MONDAY, Day.parse("Mon"));
        assertEquals(Day.TUESDAY, Day.parse("Tue"));
        assertEquals(Day.WEDNESDAY, Day.parse("Wed"));
        assertEquals(Day.THURSDAY, Day.parse("Thu"));
        assertEquals(Day.FRIDAY, Day.parse("Fri"));
        assertEquals(Day.SATURDAY, Day.parse("Sat"));
        assertEquals(Day.SUNDAY, Day.parse("Sun"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Day.parse("random input"));

    }

    public void testMake() {

        assertEquals(Day.SUNDAY, Day.dayFromInt(1));
        assertEquals(Day.MONDAY, Day.dayFromInt(2));
        assertEquals(Day.TUESDAY, Day.dayFromInt(3));
        assertEquals(Day.WEDNESDAY, Day.dayFromInt(4));
        assertEquals(Day.THURSDAY, Day.dayFromInt(5));
        assertEquals(Day.FRIDAY, Day.dayFromInt(6));
        assertEquals(Day.SATURDAY, Day.dayFromInt(7));

        Assertions.assertThrows(IllegalArgumentException.class, () -> Day.dayFromInt(8));

    }

}