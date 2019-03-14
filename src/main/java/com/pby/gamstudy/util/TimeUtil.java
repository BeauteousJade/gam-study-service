package com.pby.gamstudy.util;

import java.util.Calendar;

public class TimeUtil {

    public static long getOffsetTimeForNextDay() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }
}
