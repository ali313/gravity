package com.gravity.oncepayment.Utilities.datetime;

/**
 * Created by ali on 9/18/2016.
 */
public class JalaliDate extends MDate
{
    public static final int MONTH_FARVARDIN = 0;
    public static final int MONTH_ORDIBEHESHT = 1;
    public static final int MONTH_KHORDAD = 2;
    public static final int MONTH_TIR = 3;
    public static final int MONTH_MORDAD = 4;
    public static final int MONTH_SHAHRIVAR = 5;
    public static final int MONTH_MEHR = 6;
    public static final int MONTH_ABAN = 7;
    public static final int MONTH_AZAR = 8;
    public static final int MONTH_DEY = 9;
    public static final int MONTH_BAHMAN = 10;
    public static final int MONTH_ESFAND = 11;

    public JalaliDate(int year, int month, int day, int weekDay)
    {
        super(year, month, day, weekDay);
    }

    @Override
    public String[] getWeekDays()
    {
        return new String[] { "یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه" };
    }

    @Override
    public String[] getMonthNames()
    {
        return new String[] { "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند" };
    }

    @Override
    public boolean getIsLeap()
    {
        int rm;
        if (Year > 0) rm = 474;
        else rm = 473;
        return ((((((Year - rm) % 2820) + 474) + 38) * 682) % 2816) < 682;
    }

    @Override
    public int getCurrentMonthDays()
    {
        return Month < 7 ? 31 : (Month < 12 ? 30 : (isLeap ? 30 : 29));
    }
}
