package org.jfree.date;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public enum Day {
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY),
    SUNDAY(Calendar.SUNDAY);

    public final int index;
    private static DateFormatSymbols dateSymbols = new DateFormatSymbols();

    Day(int day) {
        index = day;
    }

    public static Day make(int index) throws IllegalArgumentException {
        for(Day d : Day.values())
            if(d.index == index)
                return d;
        throw new IllegalArgumentException(
                String.format("Illegal day index: %d." , index));
    }

    /**
     * Converts the supplied string to a day of the week.
     *
     * @param s  a string representing the day of the week.
     *
     */
    public static Day parse(String s) throws IllegalArgumentException {

        final String[] shortWeekdayNames
                = dateSymbols.getShortWeekdays();
        final String[] weekDayNames
                = dateSymbols.getWeekdays();
        s = s.trim();
        for (Day day : Day.values()) {
            if (s.equalsIgnoreCase(shortWeekdayNames[day.index]) || s.equalsIgnoreCase(weekDayNames[day.index])) {
                return day;
            }
        }
        throw new IllegalArgumentException(
                String.format("%s is not a valid weekday string", s));
    }
    public String toString() {
        return  dateSymbols.getWeekdays()[index];
    }
}