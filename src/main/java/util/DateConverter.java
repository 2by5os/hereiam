package util;

import domain.entity.HiaCourseTimeEntity;

import java.util.Calendar;

/**
 * Created by ohseoklee on 2018-12-04.
 *
 * date converter
 */
public class DateConverter {
    public static Calendar getCalendarFormCourseTime(int day, int time) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, day + 1);
        calendar.set(Calendar.HOUR_OF_DAY, time + 9);

        return calendar;
    }

    public static Calendar getCalendarFormCourseTime(HiaCourseTimeEntity timeEntity) {
        return getCalendarFormCourseTime(timeEntity.getDay(), timeEntity.getTime());
    }
}
