package org.jfree.date;

import java.text.DateFormatSymbols;

public class DateUtil {
    private static final DateFormatSymbols
            dateFormatSymbols = new DateFormatSymbols();

    public static boolean isLeapYear(final int year) {
        boolean fourth = year % 4 == 0;
        boolean hundredth = year % 100 == 0;
        boolean fourHundredth = year % 400 == 0;

        return fourth && (!hundredth || fourHundredth);
    }

    public static int lastDayOfMonth(Month month, final int year) {

        if(month == Month.FEBRUARY && DateUtil.isLeapYear(year)) {
            return month.lastDay() + 1;
        }
        else return month.lastDay();
    }
}
