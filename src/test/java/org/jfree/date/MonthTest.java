package org.jfree.date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;

import static org.jfree.date.Month.*;

import static org.junit.jupiter.api.Assertions.*;

public class MonthTest extends TestCase {

    public MonthTest(final String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(MonthTest.class);
    }

    public void testMonthParse() {

        assertEquals(JANUARY, Month.parse("January"));
        assertEquals(FEBRUARY, Month.parse("February"));
        assertEquals(MARCH, Month.parse("March"));
        assertEquals(APRIL, Month.parse("April"));
        assertEquals(MAY, Month.parse("May"));
        assertEquals(JUNE, Month.parse("June"));
        assertEquals(JULY, Month.parse("July"));
        assertEquals(AUGUST, Month.parse("August"));
        assertEquals(SEPTEMBER, Month.parse("September"));
        assertEquals(OCTOBER, Month.parse("October"));
        assertEquals(NOVEMBER, Month.parse("November"));
        assertEquals(DECEMBER, Month.parse("December"));

        assertEquals(JANUARY, Month.parse("Jan"));
        assertEquals(FEBRUARY, Month.parse("Feb"));
        assertEquals(MARCH, Month.parse("Mar"));
        assertEquals(APRIL, Month.parse("Apr"));
        assertEquals(MAY, Month.parse("May"));
        assertEquals(JUNE, Month.parse("Jun"));
        assertEquals(JULY, Month.parse("Jul"));
        assertEquals(AUGUST, Month.parse("Aug"));
        assertEquals(SEPTEMBER, Month.parse("Sep"));
        assertEquals(OCTOBER, Month.parse("Oct"));
        assertEquals(NOVEMBER, Month.parse("Nov"));
        assertEquals(DECEMBER, Month.parse("Dec"));
    }

    public void testMonthCodeToString() {

        assertEquals("January", JANUARY.toString());
        assertEquals("February", FEBRUARY.toString());
        assertEquals("March", MARCH.toString());
        assertEquals("April", APRIL.toString());
        assertEquals("May", MAY.toString());
        assertEquals("June", JUNE.toString());
        assertEquals("July", JULY.toString());
        assertEquals("August", AUGUST.toString());
        assertEquals("September", SEPTEMBER.toString());
        assertEquals("October", OCTOBER.toString());
        assertEquals("November", NOVEMBER.toString());
        assertEquals("December", DECEMBER.toString());
    }

    public void testMonthCodeToStringShort() {
        assertEquals("Jan", JANUARY.toStringShort());
        assertEquals("Feb", FEBRUARY.toStringShort());
        assertEquals("Mar", MARCH.toStringShort());
        assertEquals("Apr", APRIL.toStringShort());
        assertEquals("May", MAY.toStringShort());
        assertEquals("Jun", JUNE.toStringShort());
        assertEquals("Jul", JULY.toStringShort());
        assertEquals("Aug", AUGUST.toStringShort());
        assertEquals("Sep", SEPTEMBER.toStringShort());
        assertEquals("Oct", OCTOBER.toStringShort());
        assertEquals("Nov", NOVEMBER.toStringShort());
        assertEquals("Dec", DECEMBER.toStringShort());
    }

    public void testMake() {

        assertEquals(JANUARY, Month.make(1));
        assertEquals(FEBRUARY, Month.make(2));
        assertEquals(MARCH, Month.make(3));
        assertEquals(APRIL, Month.make(4));
        assertEquals(MAY, Month.make(5));
        assertEquals(JUNE, Month.make(6));
        assertEquals(JULY, Month.make(7));
        assertEquals(AUGUST, Month.make(8));
        assertEquals(SEPTEMBER, Month.make(9));
        assertEquals(OCTOBER, Month.make(10));
        assertEquals(NOVEMBER, Month.make(11));
        assertEquals(DECEMBER, Month.make(12));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Month.make(14));

    }

    public void testQuarter() {
        assertEquals(1, JANUARY.quarter());
        assertEquals(1, FEBRUARY.quarter());
        assertEquals(1, MARCH.quarter());
        assertEquals(2, APRIL.quarter());
        assertEquals(2, MAY.quarter());
        assertEquals(2, JUNE.quarter());
        assertEquals(3, JULY.quarter());
        assertEquals(3, AUGUST.quarter());
        assertEquals(3, SEPTEMBER.quarter());
        assertEquals(4, OCTOBER.quarter());
        assertEquals(4, NOVEMBER.quarter());
        assertEquals(4, DECEMBER.quarter());
    }

    public void testGetMonthNames() {

        String[] expectedMonthNames = {"January" , "February" , "March", "April", "May", "June", "July",
                                            "August" , "September", "October", "November" , "December" , ""};
        String[] actualMonthNames = getMonthNames();

        for(int i = 0; i <= 12; i++) {
            assertEquals(expectedMonthNames[i], actualMonthNames[i]);
        }
        assertEquals(expectedMonthNames.length, actualMonthNames.length);
    }

}