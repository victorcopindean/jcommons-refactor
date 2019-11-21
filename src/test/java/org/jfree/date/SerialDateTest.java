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
 * Some JUnit tests for the {@link SerialDate} class.
 */
public class SerialDateTest extends TestCase {

    /** Date representing November 9. */
    private SerialDate nov9Y2001;

    /**
     * Creates a new test case.
     *
     * @param name  the name.
     */
    public SerialDateTest(final String name) {
        super(name);
    }

    /**
     * Returns a test suite for the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test suite() {
        return new TestSuite(SerialDateTest.class);
    }

    /**
     * Problem set up.
     */
    protected void setUp() {
        this.nov9Y2001 = SerialDate.createInstance(9, MonthConstants.NOVEMBER, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    public void testAddMonthsTo9Nov2001() {
        final SerialDate jan9Y2002 = SerialDate.addMonths(2, this.nov9Y2001);
        final SerialDate answer = SerialDate.createInstance(9, 1, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo5Oct2003() {
        final SerialDate d1 = SerialDate.createInstance(5, MonthConstants.OCTOBER, 2003);
        final SerialDate d2 = SerialDate.addMonths(2, d1);
        assertEquals(d2, SerialDate.createInstance(5, MonthConstants.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo1Jan2003() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.JANUARY, 2003);
        final SerialDate d2 = SerialDate.addMonths(0, d1);
        assertEquals(d2, d1);
    }

    /**
     * Problem that the conversion of days to strings returns the right result.  Actually, this 
     * result depends on the Locale so this test needs to be modified.
     */
    public void testWeekdayCodeToString() {

        assertEquals("Monday", SerialDate.weekdayCodeToString(SerialDate.MONDAY));
        assertEquals("Tuesday", SerialDate.weekdayCodeToString(SerialDate.TUESDAY));
        assertEquals("Wednesday", SerialDate.weekdayCodeToString(SerialDate.WEDNESDAY));
        assertEquals("Thursday", SerialDate.weekdayCodeToString(SerialDate.THURSDAY));
        assertEquals("Friday", SerialDate.weekdayCodeToString(SerialDate.FRIDAY));
        assertEquals("Saturday", SerialDate.weekdayCodeToString(SerialDate.SATURDAY));
        assertEquals("Sunday", SerialDate.weekdayCodeToString(SerialDate.SUNDAY));
    }

    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the 
     * default locale doesn't use English weekday names...devise a better test!
     */
    public void testStringToWeekday() {

        assertEquals(SerialDate.MONDAY, SerialDate.stringToWeekdayCode("Monday"));
        assertEquals(SerialDate.TUESDAY, SerialDate.stringToWeekdayCode("Tuesday"));
        assertEquals(SerialDate.WEDNESDAY, SerialDate.stringToWeekdayCode("Wednesday"));
        assertEquals(SerialDate.THURSDAY, SerialDate.stringToWeekdayCode("Thursday"));
        assertEquals(SerialDate.FRIDAY, SerialDate.stringToWeekdayCode("Friday"));
        assertEquals(SerialDate.SATURDAY, SerialDate.stringToWeekdayCode("Saturday"));
        assertEquals(SerialDate.SUNDAY, SerialDate.stringToWeekdayCode("Sunday"));

        assertEquals(SerialDate.MONDAY, SerialDate.stringToWeekdayCode("Mon"));
        assertEquals(SerialDate.TUESDAY, SerialDate.stringToWeekdayCode("Tue"));
        assertEquals(SerialDate.WEDNESDAY, SerialDate.stringToWeekdayCode("Wed"));
        assertEquals(SerialDate.THURSDAY, SerialDate.stringToWeekdayCode("Thu"));
        assertEquals(SerialDate.FRIDAY, SerialDate.stringToWeekdayCode("Fri"));
        assertEquals(SerialDate.SATURDAY, SerialDate.stringToWeekdayCode("Sat"));
        assertEquals(SerialDate.SUNDAY, SerialDate.stringToWeekdayCode("Sun"));

    }

    /**
     * Test the conversion of a string to a month.  Note that this test will fail if the default
     * locale doesn't use English month names...devise a better test!
     */
    public void testStringToMonthCode() {

        assertEquals(MonthConstants.JANUARY, SerialDate.stringToMonthCode("January"));
        assertEquals(MonthConstants.FEBRUARY, SerialDate.stringToMonthCode("February"));
        assertEquals(MonthConstants.MARCH, SerialDate.stringToMonthCode("March"));
        assertEquals(MonthConstants.APRIL, SerialDate.stringToMonthCode("April"));
        assertEquals(MonthConstants.MAY, SerialDate.stringToMonthCode("May"));
        assertEquals(MonthConstants.JUNE, SerialDate.stringToMonthCode("June"));
        assertEquals(MonthConstants.JULY, SerialDate.stringToMonthCode("July"));
        assertEquals(MonthConstants.AUGUST, SerialDate.stringToMonthCode("August"));
        assertEquals(MonthConstants.SEPTEMBER, SerialDate.stringToMonthCode("September"));
        assertEquals(MonthConstants.OCTOBER, SerialDate.stringToMonthCode("October"));
        assertEquals(MonthConstants.NOVEMBER, SerialDate.stringToMonthCode("November"));
        assertEquals(MonthConstants.DECEMBER, SerialDate.stringToMonthCode("December"));

        assertEquals(MonthConstants.JANUARY, SerialDate.stringToMonthCode("Jan"));
        assertEquals(MonthConstants.FEBRUARY, SerialDate.stringToMonthCode("Feb"));
        assertEquals(MonthConstants.MARCH, SerialDate.stringToMonthCode("Mar"));
        assertEquals(MonthConstants.APRIL, SerialDate.stringToMonthCode("Apr"));
        assertEquals(MonthConstants.MAY, SerialDate.stringToMonthCode("May"));
        assertEquals(MonthConstants.JUNE, SerialDate.stringToMonthCode("Jun"));
        assertEquals(MonthConstants.JULY, SerialDate.stringToMonthCode("Jul"));
        assertEquals(MonthConstants.AUGUST, SerialDate.stringToMonthCode("Aug"));
        assertEquals(MonthConstants.SEPTEMBER, SerialDate.stringToMonthCode("Sep"));
        assertEquals(MonthConstants.OCTOBER, SerialDate.stringToMonthCode("Oct"));
        assertEquals(MonthConstants.NOVEMBER, SerialDate.stringToMonthCode("Nov"));
        assertEquals(MonthConstants.DECEMBER, SerialDate.stringToMonthCode("Dec"));
    }

    public void testMonthCodeToString() {

        assertEquals("January", SerialDate.monthCodeToString(MonthConstants.JANUARY));
        assertEquals("February", SerialDate.monthCodeToString(MonthConstants.FEBRUARY));
        assertEquals("March", SerialDate.monthCodeToString(MonthConstants.MARCH));
        assertEquals("April", SerialDate.monthCodeToString(MonthConstants.APRIL));
        assertEquals("May", SerialDate.monthCodeToString(MonthConstants.MAY));
        assertEquals("June", SerialDate.monthCodeToString(MonthConstants.JUNE));
        assertEquals("July", SerialDate.monthCodeToString(MonthConstants.JULY));
        assertEquals("August", SerialDate.monthCodeToString(MonthConstants.AUGUST));
        assertEquals("September", SerialDate.monthCodeToString(MonthConstants.SEPTEMBER));
        assertEquals("October", SerialDate.monthCodeToString(MonthConstants.OCTOBER));
        assertEquals("November", SerialDate.monthCodeToString(MonthConstants.NOVEMBER));
        assertEquals("December", SerialDate.monthCodeToString(MonthConstants.DECEMBER));

        assertEquals("Jan", SerialDate.monthCodeToString(MonthConstants.JANUARY, true));
        assertEquals("Feb", SerialDate.monthCodeToString(MonthConstants.FEBRUARY, true));
        assertEquals("Mar", SerialDate.monthCodeToString(MonthConstants.MARCH, true));
        assertEquals("Apr", SerialDate.monthCodeToString(MonthConstants.APRIL, true));
        assertEquals("May", SerialDate.monthCodeToString(MonthConstants.MAY, true));
        assertEquals("Jun", SerialDate.monthCodeToString(MonthConstants.JUNE, true));
        assertEquals("Jul", SerialDate.monthCodeToString(MonthConstants.JULY, true));
        assertEquals("Aug", SerialDate.monthCodeToString(MonthConstants.AUGUST, true));
        assertEquals("Sep", SerialDate.monthCodeToString(MonthConstants.SEPTEMBER, true));
        assertEquals("Oct", SerialDate.monthCodeToString(MonthConstants.OCTOBER, true));
        assertEquals("Nov", SerialDate.monthCodeToString(MonthConstants.NOVEMBER, true));
        assertEquals("Dec", SerialDate.monthCodeToString(MonthConstants.DECEMBER, true));

        Assertions.assertThrows(IllegalArgumentException.class, () -> SerialDate.monthCodeToString(15,true));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SerialDate.monthCodeToString(0));
    }

    /**
     * 1900 is not a leap year.
     */
    public void testIsNotLeapYear1900() {
        assertTrue(!SerialDate.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year.
     */
    public void testIsLeapYear2000() {
        assertTrue(SerialDate.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0.
     */
    public void testLeapYearCount1899() {
        assertEquals(SerialDate.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0.
     */
    public void testLeapYearCount1903() {
        assertEquals(SerialDate.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1.
     */
    public void testLeapYearCount1904() {
        assertEquals(SerialDate.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24.
     */
    public void testLeapYearCount1999() {
        assertEquals(SerialDate.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25.
     */
    public void testLeapYearCount2000() {
        assertEquals(SerialDate.leapYearCount(2000), 25);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        SerialDate d1 = SerialDate.createInstance(15, 4, 2000);
        SerialDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (SerialDate) in.readObject();
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
        SerialDate d = SerialDate.createInstance(29, 2, 2004);
        d = SerialDate.addYears(1, d);
        SerialDate expected = SerialDate.createInstance(28, 2, 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for the addMonths() method.
     */
    public void testAddMonths() {
        SerialDate d1 = SerialDate.createInstance(31, 5, 2004);
        
        SerialDate d2 = SerialDate.addMonths(1, d1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(6, d2.getMonth());
        assertEquals(2004, d2.getYYYY());
        
        SerialDate d3 = SerialDate.addMonths(2, d1);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth());
        assertEquals(2004, d3.getYYYY());
        
        SerialDate d4 = SerialDate.addMonths(1, SerialDate.addMonths(1, d1));
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(7, d4.getMonth());
        assertEquals(2004, d4.getYYYY());
    }

    public void testIsValidWeekInMonthCode() {
        for(int i=0; i<= 4; i++){
            assertTrue(SerialDate.isValidWeekInMonthCode(i));
        }
        assertFalse(SerialDate.isValidWeekInMonthCode(5));
    }

    public void testIsValidMonth() {
        for(int i = 1; i <= 12; i++){
            assertTrue(SerialDate.isValidMonthCode(i));
        }
        assertFalse(SerialDate.isValidMonthCode(13));
    }

    public void testMonthCodeToQuarter() {
        assertEquals(1, SerialDate.monthCodeToQuarter(1));
        assertEquals(1, SerialDate.monthCodeToQuarter(2));
        assertEquals(1, SerialDate.monthCodeToQuarter(3));
        assertEquals(2, SerialDate.monthCodeToQuarter(4));
        assertEquals(2, SerialDate.monthCodeToQuarter(5));
        assertEquals(2, SerialDate.monthCodeToQuarter(6));
        assertEquals(3, SerialDate.monthCodeToQuarter(7));
        assertEquals(3, SerialDate.monthCodeToQuarter(8));
        assertEquals(3, SerialDate.monthCodeToQuarter(9));
        assertEquals(4, SerialDate.monthCodeToQuarter(10));
        assertEquals(4, SerialDate.monthCodeToQuarter(11));
        assertEquals(4, SerialDate.monthCodeToQuarter(12));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                SerialDate.monthCodeToQuarter(14));
    }

    private static SpreadsheetDate d(int day, int month, int year){
        return new SpreadsheetDate(day, month, year);
    }

    public void testGetFollowingDayOfTheWeek() {

        assertEquals(d(25,11,2019), d(21,11,2019).getFollowingDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(26,11,2019), d(21, 11, 2019).getFollowingDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(27,11,2019), d(21,11,2019).getFollowingDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(28,11,2019), d(21,11,2019).getFollowingDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(22,11,2019), d(21,11,2019).getFollowingDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(23,11,2019), d(21,11,2019).getFollowingDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(24,11,2019), d(21,11,2019).getFollowingDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(1, 1,2020), d(25,12,2019).getFollowingDayOfWeek(SerialDate.WEDNESDAY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SerialDate.getFollowingDayOfWeek(8,nov9Y2001));

    }

    public void testGetNearestDayOfTheWeek(){

        assertEquals(d(24,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(24,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(24,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(24,11,2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(1,12,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(1, 12,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(1, 12, 2019), d(30, 11, 2019).getNearestDayOfWeek(SerialDate.SUNDAY));

        assertEquals(d(25,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(25,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(25,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(25, 11, 2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(25,11,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(2,12,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(2,12,2019), d(30,11,2019).getNearestDayOfWeek(SerialDate.MONDAY));

        assertEquals(d(26,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(26,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(26,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(26, 11, 2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(26,11,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(26,11,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(3,12,2019), d(30,11,2019).getNearestDayOfWeek(SerialDate.TUESDAY));

        assertEquals(d(27,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(27,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(27,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(27, 11, 2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(27,11,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(27,11,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(27,11,2019), d(30,11,2019).getNearestDayOfWeek(SerialDate.WEDNESDAY));

        assertEquals(d(21,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(28,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(28,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(28, 11, 2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(28,11,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(28,11,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(28,11,2019), d(30,11,2019).getNearestDayOfWeek(SerialDate.THURSDAY));

        assertEquals(d(22,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(22,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(29,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(29, 11, 2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(29,11,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(29,11,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(29,11,2019), d(30,11,2019).getNearestDayOfWeek(SerialDate.FRIDAY));

        assertEquals(d(23,11,2019), d(24,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(23,11,2019), d(25,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(23,11,2019), d(26,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(30, 11, 2019), d(27,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(30,11,2019), d(28,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(30,11,2019), d(29,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));
        assertEquals(d(30,11,2019), d(30,11,2019).getNearestDayOfWeek(SerialDate.SATURDAY));

        Assertions.assertThrows(IllegalArgumentException.class, () -> SerialDate.getNearestDayOfWeek(8, nov9Y2001));
    }

    public void testGetEndOfCurrentMonth() {
        SerialDate serialDate = SerialDate.createInstance(19,11,2019);
        assertEquals(d(30,11,2019), serialDate.getEndOfCurrentMonth(serialDate));
    }

    public void testWeekInMonthToString() {
        assertEquals("First",SerialDate.weekInMonthToString(1));
        assertEquals("Second",SerialDate.weekInMonthToString(2));
        assertEquals("Third",SerialDate.weekInMonthToString(3));
        assertEquals("Fourth",SerialDate.weekInMonthToString(4));
        assertEquals("Last",SerialDate.weekInMonthToString(0));
        assertEquals("SerialDate.weekInMonthToString(): invalid code.", SerialDate.weekInMonthToString(8));
    }


    public void testGetPreviousDayOfTheWeek() {

        assertEquals(d(17,11,2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.SUNDAY));
        assertEquals(d(18,11,2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.MONDAY));
        assertEquals(d(19, 11, 2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.TUESDAY));
        assertEquals(d(20,11,2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.WEDNESDAY));
        assertEquals(d(14,11,2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.THURSDAY));
        assertEquals(d(15,11,2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.FRIDAY));
        assertEquals(d(16,11,2019), d(21,11,2019).getPreviousDayOfWeek(SerialDate.SATURDAY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> SerialDate.getPreviousDayOfWeek(8,nov9Y2001));
    }

    public void testGetMonths() {
        String[] monthNames = SerialDate.getMonths();
        String[] monthNamesExpected = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
          "October", "November" , "December" , ""};
        assertEquals(monthNamesExpected.length, monthNames.length);
        assertEquals(monthNamesExpected[0], monthNamesExpected[0]);
    }

    public void testGetMonthsShortened() {
        String[] monthNames = SerialDate.getMonths(true);
        String[] monthNamesExpected = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                "Oct", "Nov" , "Dec" , ""};
        assertEquals(monthNamesExpected.length, monthNames.length);
        assertEquals(monthNamesExpected[0], monthNamesExpected[0]);
    }

    public void testRelativeToString() {
        assertEquals("Preceding", SerialDate.relativeToString(SerialDate.PRECEDING));
        assertEquals("Nearest", SerialDate.relativeToString(SerialDate.NEAREST));
        assertEquals("Following", SerialDate.relativeToString(SerialDate.FOLLOWING));
        assertEquals("ERROR : Relative To String", SerialDate.relativeToString(3));
    }

    public void testCreateInstance() {
        assertEquals(d(21,11,2019), SerialDate.createInstance(new GregorianCalendar(2019, Calendar.NOVEMBER,21).getTime()));
    }

    public void testDescription() {

        SerialDate date = SerialDate.createInstance(21,11,2019);
        date.setDescription("Date of current test");

        assertEquals("Date of current test", date.getDescription());
    }

    public void testToString() {
        SerialDate date = SerialDate.createInstance(21,11,2019);
        assertEquals("21-November-2019", date.toString());
    }

}
