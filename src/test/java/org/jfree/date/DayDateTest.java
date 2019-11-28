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
        this.nov9Y2001 = DayDateFactory.makeDate(9, Month.NOVEMBER.toInt(), 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    public void testAddMonthsTo9Nov2001() {


        final DayDate jan9Y2002 = nov9Y2001.plusMonths(2);
        final DayDate answer = DayDateFactory.makeDate(9, 1, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo5Oct2003() {
        final DayDate d1 = DayDateFactory.makeDate(5, MonthConstants.OCTOBER, 2003);
        final DayDate d2 = d1.plusMonths(2);
        assertEquals(d2, DayDateFactory.makeDate(5, MonthConstants.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    public void testAddMonthsTo1Jan2003() {
        final DayDate d1 = DayDateFactory.makeDate(1, MonthConstants.JANUARY, 2003);
        final DayDate d2 = d1.plusMonths(0);
        assertEquals(d2, d1);
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
        DayDate expected = DayDateFactory.makeDate(28, 2, 2005);
        assertEquals(expected, d.plusYears(1));
    }

    /**
     * Miscellaneous tests for the addMonths() method.
     */
    public void testAddMonths() {
        DayDate d1 = DayDateFactory.makeDate(31, 5, 2004);
        
        DayDate d2 = d1.plusMonths(1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(6, d2.getMonth().toInt());
        assertEquals(2004, d2.getYear());
        
        DayDate d3 = d1.plusMonths(2);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(7, d3.getMonth().toInt());
        assertEquals(2004, d3.getYear());
        
        DayDate d4 = d2.plusMonths(1);
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(7, d4.getMonth().toInt());
        assertEquals(2004, d4.getYear());
    }

    public void testMonthCodeToQuarter() {
        assertEquals(1, Month.monthFromInt(1).quarter());
        assertEquals(1, Month.monthFromInt(2).quarter());
        assertEquals(1, Month.monthFromInt(3).quarter());
        assertEquals(2, Month.monthFromInt(4).quarter());
        assertEquals(2, Month.monthFromInt(5).quarter());
        assertEquals(2, Month.monthFromInt(6).quarter());
        assertEquals(3, Month.monthFromInt(7).quarter());
        assertEquals(3, Month.monthFromInt(8).quarter());
        assertEquals(3, Month.monthFromInt(9).quarter());
        assertEquals(4, Month.monthFromInt(10).quarter());
        assertEquals(4, Month.monthFromInt(11).quarter());
        assertEquals(4, Month.monthFromInt(12).quarter());
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

    }

    public void testGetEndOfCurrentMonth() {
        DayDate dayDate = DayDateFactory.makeDate(19,11,2019);
        assertEquals(d(30,11,2019), dayDate.getEndOfMonth());
    }

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
        String[] monthNames = Month.getMonthNames();
        String[] monthNamesExpected = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
          "October", "November" , "December" , ""};
        assertEquals(monthNamesExpected.length, monthNames.length);
        assertEquals(monthNamesExpected[0], monthNamesExpected[0]);
    }

    public void testCreateInstance() {
        assertEquals(d(21,11,2019), DayDateFactory.makeDate(new GregorianCalendar(2019, Calendar.NOVEMBER,21).getTime()));
    }

    public void testToString() {
        DayDate date = DayDateFactory.makeDate(21,11,2019);
        assertEquals("21-November-2019", date.toString());
    }

    public void testDaysSince() {
        DayDate date = DayDateFactory.makeDate(28, 11, 2019);
        DayDate previousToDate = DayDateFactory.makeDate(25, 11, 2019);
        DayDate afterDate = DayDateFactory.makeDate(30, 11, 2019);
        DayDate sameDate = DayDateFactory.makeDate(28, 11, 2019);

        assertEquals(3, date.daysSince(previousToDate));
        assertEquals(-2, date.daysSince(afterDate));
        assertEquals(0, date.daysSince(sameDate));
    }

    public void testIsOn() {
        DayDate date = DayDateFactory.makeDate(28, 11, 2019);
        DayDate anotherDate = DayDateFactory.makeDate(30, 11, 2019);
        DayDate sameDate = DayDateFactory.makeDate(28, 11, 2019);

        assertTrue(date.isOn(sameDate));
        assertFalse(date.isOn(anotherDate));
    }

}
