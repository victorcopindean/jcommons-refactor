package org.jfree.date;

public enum WeekdayRange {
    PRECEDING(-1),
    NEAREST(0),
    FOLLOWING(1);

    final int index;

    WeekdayRange(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        switch(this) {
            case PRECEDING : return "Preceding";
            case NEAREST : return "Nearest";
            case FOLLOWING: return "Following";
            default : return "ERROR : Relative To String";
        }
    }
}
