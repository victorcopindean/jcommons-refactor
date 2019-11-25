/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------
 * SerialDateTest.java
 * -------------------
 * (C) Copyright 2001-2014, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: SerialDateTest.java,v 1.7 2007/11/02 17:50:35 taqua Exp $
 *
 * Changes
 * -------
 * 15-Nov-2001 : Version 1 (DG);
 * 25-Jun-2002 : Removed unnecessary import (DG);
 * 24-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Added serialization test (DG);
 * 05-Jan-2005 : Added test for bug report 1096282 (DG);
 *
 */

package org.jfree.date;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.*;
import org.junit.jupiter.api.Assertions;


/**
 * Some JUnit tests for the {@link DayDate} class.
 */
public class DayDateTest extends TestCase {

    /** Date representing November 9. */
    private DayDate nov9Y2001;

    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public DayDateTest(final String name) {
        super(name);
    }

    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(DayDateTest.class);
    }

    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = DayDateFactory.makeDate(9, DayDate.Month.NOVEMBER, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final DayDate jan9Y2002 = DayDate.addMonths(2, this.nov9Y2001);
        final DayDate answer = DayDateFactory.makeDate(9, 1, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo5Oct2003() {
        final DayDate d1 = DayDateFactory.makeDate(5, MonthConstants.OCTOBER, 2003);
        final DayDate d2 = DayDate.addMonths(2, d1);
        assertEquals(d2, DayDateFactory.makeDate(5, MonthConstants.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo1Jan2003() {
        final DayDate d1 = DayDateFactory.makeDate(1, MonthConstants.JANUARY, 2003);
        final DayDate d2 = DayDate.addMonths(0, d1);
        assertEquals(d2, d1);
    }


    /**
     * Test the conversion of a string to a month.  Note that this test will fail if the default
     * locale doesn't use English month names...devise a better test!
     */
    public void testStringToMonthCode() {

        assertEquals(MonthConstants.JANUARY, DayDate.stringToMonthCode("January"));
        assertEquals(MonthConstants.FEBRUARY, DayDate.stringToMonthCode("February"));
        assertEquals(MonthConstants.MARCH, DayDate.stringToMonthCode("March"));
        assertEquals(MonthConstants.APRIL, DayDate.stringToMonthCode("April"));
        assertEquals(MonthConstants.MAY, DayDate.stringToMonthCode("May"));
        assertEquals(MonthConstants.JUNE, DayDate.stringToMonthCode("June"));
        assertEquals(MonthConstants.JULY, DayDate.stringToMonthCode("July"));
        assertEquals(MonthConstants.AUGUST, DayDate.stringToMonthCode("August"));
        assertEquals(MonthConstants.SEPTEMBER, DayDate.stringToMonthCode("September"));
        assertEquals(MonthConstants.OCTOBER, DayDate.stringToMonthCode("October"));
        assertEquals(MonthConstants.NOVEMBER, DayDate.stringToMonthCode("November"));
        assertEquals(MonthConstants.DECEMBER, DayDate.stringToMonthCode("December"));

        assertEquals(MonthConstants.JANUARY, DayDate.stringToMonthCode("Jan"));
        assertEquals(MonthConstants.FEBRUARY, DayDate.stringToMonthCode("Feb"));
        assertEquals(MonthConstants.MARCH, DayDate.stringToMonthCode("Mar"));
        assertEquals(MonthConstants.APRIL, DayDate.stringToMonthCode("Apr"));
        assertEquals(MonthConstants.MAY, DayDate.stringToMonthCode("May"));
        assertEquals(MonthConstants.JUNE, DayDate.stringToMonthCode("Jun"));
        assertEquals(MonthConstants.JULY, DayDate.stringToMonthCode("Jul"));
        assertEquals(MonthConstants.AUGUST, DayDate.stringToMonthCode("Aug"));
        assertEquals(MonthConstants.SEPTEMBER, DayDate.stringToMonthCode("Sep"));
        assertEquals(MonthConstants.OCTOBER, DayDate.stringToMonthCode("Oct"));
        assertEquals(MonthConstants.NOVEMBER, DayDate.stringToMonthCode("Nov"));
        assertEquals(MonthConstants.DECEMBER, DayDate.stringToMonthCode("Dec"));
    }

    public void testMonthCodeToString() {

        assertEquals("January", DayDate.monthCodeToString(DayDate.Month.JANUARY));
        assertEquals("February", DayDate.monthCodeToString(DayDate.Month.FEBRUARY));
        assertEquals("March", DayDate.monthCodeToString(DayDate.Month.MARCH));
        assertEquals("April", DayDate.monthCodeToString(DayDate.Month.APRIL));
        assertEquals("May", DayDate.monthCodeToString(DayDate.Month.MAY));
        assertEquals("June", DayDate.monthCodeToString(DayDate.Month.JUNE));
        assertEquals("July", DayDate.monthCodeToString(DayDate.Month.JULY));
        assertEquals("August", DayDate.monthCodeToString(DayDate.Month.AUGUST));
        assertEquals("September", DayDate.monthCodeToString(DayDate.Month.SEPTEMBER));
        assertEquals("October", DayDate.monthCodeToString(DayDate.Month.OCTOBER));
        assertEquals("November", DayDate.monthCodeToString(DayDate.Month.NOVEMBER));
        assertEquals("December", DayDate.monthCodeToString(DayDate.Month.DECEMBER));
    }

    public void testMonthCodeToStringShort() {
        assertEquals("Jan", DayDate.monthCodeToStringShort(DayDate.Month.JANUARY));
        assertEquals("Feb", DayDate.monthCodeToStringShort(DayDate.Month.FEBRUARY));
        assertEquals("Mar", DayDate.monthCodeToStringShort(DayDate.Month.MARCH));
        assertEquals("Apr", DayDate.monthCodeToStringShort(DayDate.Month.APRIL));
        assertEquals("May", DayDate.monthCodeToStringShort(DayDate.Month.MAY));
        assertEquals("Jun", DayDate.monthCodeToStringShort(DayDate.Month.JUNE));
        assertEquals("Jul", DayDate.monthCodeToStringShort(DayDate.Month.JULY));
        assertEquals("Aug", DayDate.monthCodeToStringShort(DayDate.Month.AUGUST));
        assertEquals("Sep", DayDate.monthCodeToStringShort(DayDate.Month.SEPTEMBER));
        assertEquals("Oct", DayDate.monthCodeToStringShort(DayDate.Month.OCTOBER));
        assertEquals("Nov", DayDate.monthCodeToStringShort(DayDate.Month.NOVEMBER));
        assertEquals("Dec", DayDate.monthCodeToStringShort(DayDate.Month.DECEMBER));
    }

    /**
     * 1900 is not a leap year.
     */
    public void testIsNotLeapYear1900() {
        assertTrue(!DayDate.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year.
     */
    public void testIsLeapYear2000() {
        assertTrue(DayDate.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0.
     */
    public void testLeapYearCount1899() {
        assertEquals(DayDate.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0.
     */
    public void testLeapYearCount1903() {
        assertEquals(DayDate.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1.
     */
    public void testLeapYearCount1904() {
        assertEquals(DayDate.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24.
     */
    public void testLeapYearCount1999() {
        assertEquals(DayDate.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25.
     */
    public void testLeapYearCount2000() {
        assertEquals(DayDate.leapYearCount(2000), 25);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        DayDate d1 = DayDateFactory.makeDate(15, 4, 2000);
        DayDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (DayDate) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(d1, d2);

    }
    
    /**
     * A test for bug report 1096282 (now fixed).
     */
    public void test1096282() {
        DayDate d = DayDateFactory.makeDate(29, 2, 2004);
        d = DayDate.addYears(1, d);
        DayDate expected = DayDateFactory.makeDate(28, 2, 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for the addMonths() method.
     */
    public void testAddMonths() {
        DayDate d1 = DayDateFactory.makeDate(31, 5, 2004);
        
        DayDate d2 = DayDate.addMonths(1, d1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(6, d2.getMonth());
        assertEquals(2004, d2.getYYYY());
        
        DayDate d3 = DayDate.addMonths(2, d1);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth());
        assertEquals(2004, d3.getYYYY());
        
        DayDate d4 = DayDate.addMonths(1, DayDate.addMonths(1, d1));
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(7, d4.getMonth());
        assertEquals(2004, d4.getYYYY());
    }

    public void testIsValidWeekInMonthCode() {
        for(int i=0; i<= 4; i++){
            assertTrue(DayDate.isValidWeekInMonthCode(i));
        }
        assertFalse(DayDate.isValidWeekInMonthCode(5));
    }


    public void testMonthCodeToQuarter() {
        assertEquals(1, DayDate.monthCodeToQuarter(DayDate.Month.make(1)));
        assertEquals(1, DayDate.monthCodeToQuarter(DayDate.Month.make(2)));
        assertEquals(1, DayDate.monthCodeToQuarter(DayDate.Month.make(3)));
        assertEquals(2, DayDate.monthCodeToQuarter(DayDate.Month.make(4)));
        assertEquals(2, DayDate.monthCodeToQuarter(DayDate.Month.make(5)));
        assertEquals(2, DayDate.monthCodeToQuarter(DayDate.Month.make(6)));
        assertEquals(3, DayDate.monthCodeToQuarter(DayDate.Month.make(7)));
        assertEquals(3, DayDate.monthCodeToQuarter(DayDate.Month.make(8)));
        assertEquals(3, DayDate.monthCodeToQuarter(DayDate.Month.make(9)));
        assertEquals(4, DayDate.monthCodeToQuarter(DayDate.Month.make(10)));
        assertEquals(4, DayDate.monthCodeToQuarter(DayDate.Month.make(11)));
        assertEquals(4, DayDate.monthCodeToQuarter(DayDate.Month.make(12)));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                DayDate.monthCodeToQuarter(DayDate.Month.make(14)));
    }

    private static SpreadsheetDate d(int day, int month, int year){
        return new SpreadsheetDate(day, month, year);
    }

    public void testGetFollowingDayOfTheWeek() {

        assertEquals(d(25,11,2019), d(21,11,2019).getFollowingDayOfWeek(Day.MONDAY));
        assertEquals(d(26,11,2019), d(21, 11, 2019).getFollowingDayOfWeek(Day.TUESDAY));
        assertEquals(d(27,11,2019), d(21,11,2019).getFollowingDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(28,11,2019), d(21,11,2019).getFollowingDayOfWeek(Day.THURSDAY));
        assertEquals(d(22,11,2019), d(21,11,2019).getFollowingDayOfWeek(Day.FRIDAY));
        assertEquals(d(23,11,2019), d(21,11,2019).getFollowingDayOfWeek(Day.SATURDAY));
        assertEquals(d(24,11,2019), d(21,11,2019).getFollowingDayOfWeek(Day.SUNDAY));
        assertEquals(d(1, 1,2020), d(25,12,2019).getFollowingDayOfWeek(Day.WEDNESDAY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> DayDate.getFollowingDayOfWeek(Day.make(8),nov9Y2001));

    }

    public void testGetNearestDayOfTheWeek(){

        assertEquals(d(24,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.SUNDAY));
        assertEquals(d(24,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.SUNDAY));
        assertEquals(d(24,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.SUNDAY));
        assertEquals(d(24,11,2019), d(27,11,2019).getNearestDayOfWeek(Day.SUNDAY));
        assertEquals(d(1,12,2019), d(28,11,2019).getNearestDayOfWeek(Day.SUNDAY));
        assertEquals(d(1, 12,2019), d(29,11,2019).getNearestDayOfWeek(Day.SUNDAY));
        assertEquals(d(1, 12, 2019), d(30, 11, 2019).getNearestDayOfWeek(Day.SUNDAY));

        assertEquals(d(25,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.MONDAY));
        assertEquals(d(25,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.MONDAY));
        assertEquals(d(25,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.MONDAY));
        assertEquals(d(25, 11, 2019), d(27,11,2019).getNearestDayOfWeek(Day.MONDAY));
        assertEquals(d(25,11,2019), d(28,11,2019).getNearestDayOfWeek(Day.MONDAY));
        assertEquals(d(2,12,2019), d(29,11,2019).getNearestDayOfWeek(Day.MONDAY));
        assertEquals(d(2,12,2019), d(30,11,2019).getNearestDayOfWeek(Day.MONDAY));

        assertEquals(d(26,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.TUESDAY));
        assertEquals(d(26,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.TUESDAY));
        assertEquals(d(26,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.TUESDAY));
        assertEquals(d(26, 11, 2019), d(27,11,2019).getNearestDayOfWeek(Day.TUESDAY));
        assertEquals(d(26,11,2019), d(28,11,2019).getNearestDayOfWeek(Day.TUESDAY));
        assertEquals(d(26,11,2019), d(29,11,2019).getNearestDayOfWeek(Day.TUESDAY));
        assertEquals(d(3,12,2019), d(30,11,2019).getNearestDayOfWeek(Day.TUESDAY));

        assertEquals(d(27,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(27,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(27,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(27, 11, 2019), d(27,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(27,11,2019), d(28,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(27,11,2019), d(29,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(27,11,2019), d(30,11,2019).getNearestDayOfWeek(Day.WEDNESDAY));

        assertEquals(d(21,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.THURSDAY));
        assertEquals(d(28,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.THURSDAY));
        assertEquals(d(28,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.THURSDAY));
        assertEquals(d(28, 11, 2019), d(27,11,2019).getNearestDayOfWeek(Day.THURSDAY));
        assertEquals(d(28,11,2019), d(28,11,2019).getNearestDayOfWeek(Day.THURSDAY));
        assertEquals(d(28,11,2019), d(29,11,2019).getNearestDayOfWeek(Day.THURSDAY));
        assertEquals(d(28,11,2019), d(30,11,2019).getNearestDayOfWeek(Day.THURSDAY));

        assertEquals(d(22,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.FRIDAY));
        assertEquals(d(22,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.FRIDAY));
        assertEquals(d(29,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.FRIDAY));
        assertEquals(d(29, 11, 2019), d(27,11,2019).getNearestDayOfWeek(Day.FRIDAY));
        assertEquals(d(29,11,2019), d(28,11,2019).getNearestDayOfWeek(Day.FRIDAY));
        assertEquals(d(29,11,2019), d(29,11,2019).getNearestDayOfWeek(Day.FRIDAY));
        assertEquals(d(29,11,2019), d(30,11,2019).getNearestDayOfWeek(Day.FRIDAY));

        assertEquals(d(23,11,2019), d(24,11,2019).getNearestDayOfWeek(Day.SATURDAY));
        assertEquals(d(23,11,2019), d(25,11,2019).getNearestDayOfWeek(Day.SATURDAY));
        assertEquals(d(23,11,2019), d(26,11,2019).getNearestDayOfWeek(Day.SATURDAY));
        assertEquals(d(30, 11, 2019), d(27,11,2019).getNearestDayOfWeek(Day.SATURDAY));
        assertEquals(d(30,11,2019), d(28,11,2019).getNearestDayOfWeek(Day.SATURDAY));
        assertEquals(d(30,11,2019), d(29,11,2019).getNearestDayOfWeek(Day.SATURDAY));
        assertEquals(d(30,11,2019), d(30,11,2019).getNearestDayOfWeek(Day.SATURDAY));

        Assertions.assertThrows(IllegalArgumentException.class, () -> DayDate.getNearestDayOfWeek(Day.make(8), nov9Y2001));
    }

    public void testGetEndOfCurrentMonth() {
        DayDate dayDate = DayDateFactory.makeDate(19,11,2019);
        assertEquals(d(30,11,2019), dayDate.getEndOfCurrentMonth(dayDate));
    }

    /*public void testWeekInMonthToString() {
        assertEquals("First", DayDate.WeekInMonth.toString(1));
        assertEquals("Second", DayDate.WeekInMonth.toString(2));
        assertEquals("Third", DayDate.WeekInMonth.toString(3));
        assertEquals("Fourth", DayDate.WeekInMonth.toString(4));
        assertEquals("Last", DayDate.WeekInMonth.toString(0));
        assertEquals("SerialDate.weekInMonthToString(): invalid code.", DayDate.WeekInMonth.toString(8));
    }*/


    public void testGetPreviousDayOfTheWeek() {

        assertEquals(d(17,11,2019), d(21,11,2019).getPreviousDayOfWeek(Day.SUNDAY));
        assertEquals(d(18,11,2019), d(21,11,2019).getPreviousDayOfWeek(Day.MONDAY));
        assertEquals(d(19, 11, 2019), d(21,11,2019).getPreviousDayOfWeek(Day.TUESDAY));
        assertEquals(d(20,11,2019), d(21,11,2019).getPreviousDayOfWeek(Day.WEDNESDAY));
        assertEquals(d(14,11,2019), d(21,11,2019).getPreviousDayOfWeek(Day.THURSDAY));
        assertEquals(d(15,11,2019), d(21,11,2019).getPreviousDayOfWeek(Day.FRIDAY));
        assertEquals(d(16,11,2019), d(21,11,2019).getPreviousDayOfWeek(Day.SATURDAY));

    }

    public void testGetMonths() {
        String[] monthNames = DayDate.getMonths();
        String[] monthNamesExpected = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
          "October", "November" , "December" , ""};
        assertEquals(monthNamesExpected.length, monthNames.length);
        assertEquals(monthNamesExpected[0], monthNamesExpected[0]);
    }

    public void testGetMonthsShortened() {
        String[] monthNames = DayDate.getMonths(true);
        String[] monthNamesExpected = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                "Oct", "Nov" , "Dec" , ""};
        assertEquals(monthNamesExpected.length, monthNames.length);
        assertEquals(monthNamesExpected[0], monthNamesExpected[0]);
    }

    public void testRelativeToString() {
        assertEquals("Preceding", DayDate.relativeToString(DayDate.PRECEDING));
        assertEquals("Nearest", DayDate.relativeToString(DayDate.NEAREST));
        assertEquals("Following", DayDate.relativeToString(DayDate.FOLLOWING));
        assertEquals("ERROR : Relative To String", DayDate.relativeToString(3));
    }

    public void testCreateInstance() {
        assertEquals(d(21,11,2019), DayDateFactory.makeDate(new GregorianCalendar(2019, Calendar.NOVEMBER,21).getTime()));
    }

    public void testToString() {
        DayDate date = DayDateFactory.makeDate(21,11,2019);
        assertEquals("21-November-2019", date.toString());
    }

}
