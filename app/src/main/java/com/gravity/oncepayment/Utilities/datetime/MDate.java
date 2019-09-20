package com.gravity.oncepayment.Utilities.datetime;

public abstract class MDate
{
    public int Year;
    public int Month;
    public int Day;
    public int WeekDay;
    public boolean isLeap;
    public String weekDayString;
    public String monthString;
    public int monthDays;

    protected String[] WeekDays;
    protected String[] Months;

    public abstract String[] getWeekDays();

    public abstract String[] getMonthNames();

    public abstract boolean getIsLeap();

    public abstract int getCurrentMonthDays();

    public static void getToday()
    {

    }

    public MDate(int year, int month, int day, int weekDay)
    {
        this.Year = year;
        this.Month = month;
        this.Day = day;
        this.WeekDay = weekDay;
        this.isLeap = getIsLeap();

        this.WeekDays = getWeekDays();
        this.Months = getMonthNames();

        this.monthDays = getCurrentMonthDays();
        this.monthString = this.Months[this.Month - 1];
        this.weekDayString = this.WeekDays[WeekDay];
    }

    public String toString(String pattern)
    {
        String output = pattern;
        output = output.replace("%ms", monthString);
        output = output.replace("%w", weekDayString);
        output = output.replace("%Y", String.format("%04d", Year));
        output = output.replace("%y", String.format("%02d", Year));
        output = output.replace("%M", String.format("%02d", Month));
        output = output.replace("%m", String.format("%d", Month));
        output = output.replace("%D", String.format("%02d", Day));
        output = output.replace("%d", String.format("%d", Day));

        return output;
    }

    @Override
    public String toString()
    {
        return toString("%Y/%M/%D");
    }

    @Override
    public boolean equals(Object o)
    {
    	if(o instanceof MDate)
        {
            boolean isSameType = (o instanceof HijriDate && this instanceof HijriDate)
                    || (o instanceof JalaliDate && this instanceof JalaliDate)
                    || (o instanceof MiladiDate && this instanceof MiladiDate);
            MDate inp = (MDate)o;

            return isSameType && (inp.Day == Day && inp.Year == Year && inp.Month == Month);
        }
        return super.equals(o);
    }
}
