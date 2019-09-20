package com.gravity.oncepayment.Utilities.datetime;

/**
 * Created by ali on 9/18/2016.
 */
public class MiladiDate extends MDate
{
    public static final int MONTH_JANUARY = 0;
    public static final int MONTH_FEVRUARY = 1;
    public static final int MONTH_MARCH = 2;
    public static final int MONTH_APRIL = 3;
    public static final int MONTH_MAY = 4;
    public static final int MONTH_JUNE = 5;
    public static final int MONTH_JULY = 6;
    public static final int MONTH_AUGUST = 7;
    public static final int MONTH_SEPTEMBER = 8;
    public static final int MONTH_OCTOBER = 9;
    public static final int MONTH_NOVEMBER = 10;
    public static final int MONTH_DECEMBER = 11;

    public MiladiDate(int year, int month, int day, int weekDay)
    {
        super(year, month, day, weekDay);
    }

    @Override
    public String[] getWeekDays()
    {
        return new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    }

    @Override
    public String[] getMonthNames()
    {
        return new String[] { "ژانویه", "فوریه", "مارس", "آوریل", "مه", "ژوئن", "ژوئیه", "اوت", "سپتامبر", "اکتبر", "نوامبر", "دسامبر" };
    }

    @Override
    public boolean getIsLeap()
    {
        return ((Year % 4) == 0) && (!(((Year % 100) == 0) && ((Year % 400) != 0)));
    }

    @Override
    public int getCurrentMonthDays()
    {
        return new int[]{31, 28, 31,30,31,30,31,31,30,31,30,31}[Month - 1];
    }
}
