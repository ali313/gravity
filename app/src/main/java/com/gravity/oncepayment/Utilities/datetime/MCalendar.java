package com.gravity.oncepayment.Utilities.datetime;

import java.util.Calendar;



public class MCalendar
{
    private static final double GREGORIAN_EPOCH = 1721425.5, ISLAMIC_EPOCH = 1948439.5, JALALI_EPOCH = 1948320.5;

    private double julianDay = 0;

    public static MCalendar getToday()
    {
    	return new MCalendar(Calendar.getInstance());
    }

    private MDate hijri;
    private MDate jalali;
    private MDate miladi;

    public int hour;
    public int minute;
    public int second;


    public static final int HijriType = 0;

    public static final int JalaliType = 1;
    public static final int MiladiType = 2;
    public MCalendar(Calendar datetime)
    {
        init(MiladiType, datetime.get(Calendar.DATE), datetime.get(Calendar.MONTH) + 1, datetime.get(Calendar.YEAR));
        hour = datetime.get(Calendar.HOUR_OF_DAY);
        minute = datetime.get(Calendar.MINUTE);
        second = datetime.get(Calendar.SECOND);
    }

    public MCalendar(int calendartype, int day, int month, int year)
    {
        init(calendartype, day, month, year);
        hour = 0;
        minute = 0;
        second = 0;
    }

    private void setJulianDay(double julianDay)
    {
        this.julianDay = julianDay;

        miladi = null;
        hijri = null;
        jalali = null;
    }

    private void init(int calendartype, int day, int month, int year)
    {
        setJulianDay(getJulianDay(calendartype, year, month, day));
    }

    private static int getWeakDay(double j)
    {
        return ((int)(Math.floor((j + 1.5))) % 7);
    }

    private double getJulianDay(int type, int year, int month, int day)
    {
        double jd = 0;
        switch (type)
        {
            case HijriType:
                jd = (day + Math.ceil(29.5 * (month - 1)) + (year - 1) * 354 + Math.floor((3 + (11 * year)) / 30.0) + ISLAMIC_EPOCH) - 1;
                break;

            case JalaliType:
                int rm = year >= 0 ? 474 : 473;
                int epbase = year - rm;

                int epyear = 474 + (epbase % 2820);

                int mm = month <= 7 ? (month - 1) * 31 : ((month - 1) * 30) + 6;

                jd = day + mm + Math.floor(((epyear * 682) - 110) / 2816.0) +
                       (epyear - 1) * 365 + Math.floor(epbase / 2820.0) * 1029983 + (JALALI_EPOCH - 1);
                break;

            case MiladiType:
                boolean isLeap = ((year % 4) == 0) && (!(((year % 100) == 0) && ((year % 400) != 0)));
                int tm = month <= 2 ? 0 : (isLeap ? -1 : -2);

                jd = (GREGORIAN_EPOCH - 1) + (365 * (year - 1)) + Math.floor((year - 1) / 4.0) + (-Math.floor((year - 1) / 100.0)) +
                        Math.floor((year - 1) / 400.0) + Math.floor((((367 * month) - 362) / 12.0) + tm + day);
                break;
        }
        return jd;
    }

    public MDate getMiladi()
    {
        if(miladi == null)
        {
            // "JD_TO_GREGORIAN: Calculate Gregorian calendar date from Julian day"
            double wjd = Math.floor(julianDay - 0.5) + 0.5;
            double depoch = wjd - GREGORIAN_EPOCH;
            double quadricent = Math.floor(depoch / 146097.0);
            double dqc = depoch % 146097;
            double cent = Math.floor(dqc / 36524.0);
            double dcent = dqc % 36524;
            double quad = Math.floor(dcent / 1461.0);
            double dquad = dcent % 1461;
            double yindex = Math.floor(dquad / 365.0);
            int year = (int) ((quadricent * 400) + (cent * 100) + (quad * 4) + yindex);
            if (!((cent == 4) || (yindex == 4)))
                year += 1;

            double yearday = wjd - getJulianDay(MiladiType, year, 1, 1);
            boolean isLeap = ((year % 4) == 0) && (!(((year % 100) == 0) && ((year % 400) != 0)));

            int leapadj;
            if (wjd < getJulianDay(MiladiType, year, 3, 1))
                leapadj = 0;
            else if (isLeap)
                leapadj = 1;
            else
                leapadj = 2;

            // Python 2.5
            //leapadj = 0 if wjd < this.gregorian_to_jd(year, 3, 1) else (1 if this.leap_gregorian(year) else 2)

            int month = (int) (Math.floor((((yearday + leapadj) * 12) + 373) / 367.0));
            int day = (int) (wjd - getJulianDay(MiladiType, year, month, 1)) + 1;

            miladi = new MiladiDate(year, month, day, getWeakDay(wjd));
        }
        return miladi;
    }

    public MDate getHijri()
    {
        //"JD_TO_ISLAMIC: Calculate Islamic date from Julian day"

        if(hijri == null)
        {
            double jd1 = Math.floor(julianDay) + 0.5;
            int year = (int) (Math.floor(((30 * (jd1 - ISLAMIC_EPOCH)) + 10646) / 10631.0));
            int month = (int) (Math.min(12, Math.ceil((jd1 - (29 + getJulianDay(HijriType, year, 1, 1))) / 29.5) + 1));
            int day = (int) (jd1 - getJulianDay(HijriType, year, month, 1)) + 1;

            hijri = new HijriDate(year, month, day, getWeakDay(jd1));
        }
        return hijri;
    }

    public MDate getJalali()
    {
        if(jalali == null)
        {
            double jd1 = Math.floor(julianDay) + 0.5;
            double depoch = julianDay - getJulianDay(JalaliType, 475, 1, 1);
            double cycle = Math.floor(depoch / 1029983);
            double cyear = depoch % 1029983;
            double ycycle, aux1, aux2;
            if (cyear == 1029982)
                ycycle = 2820;
            else
            {
                aux1 = Math.floor(cyear / 366);
                aux2 = cyear % 366;
                ycycle = Math.floor(((2134 * aux1) + (2816 * aux2) + 2815) / 1028522) + aux1 + 1;
            }

            int year = (int) (ycycle + (2820 * cycle) + 474);
            if (year <= 0)
                year -= 1;

            double yday = (julianDay - getJulianDay(JalaliType, year, 1, 1)) + 1;
            int month;
            if (yday <= 186)
                month = (int) (Math.ceil(yday / 31.0));
            else
                month = (int) (Math.ceil((yday - 6) / 30.0));

            int day = (int) (julianDay - getJulianDay(JalaliType, year, month, 1)) + 1;

            jalali = new JalaliDate(year, month, day, getWeakDay(jd1));
        }
        return jalali;
    }

    public MCalendar addDays(int days)
    {
        this.setJulianDay(this.julianDay + days);
        return this;
    }



    public static int getMonthDays(int CalendarType,int year, int month)
    {
    	int ret = -1;
    	if(ret < 0)
    	{
	    	if(CalendarType == MCalendar.JalaliType)
	    		ret  = month < 7 ? 31 : (month < 12 ? 30 : (isLeap(CalendarType, year) ? 30 : 29));
	    	else if(CalendarType == MCalendar.MiladiType)
	    		ret  = new int[]{31, (isLeap(CalendarType, year) ? 29 : 28), 31,30,31,30,31,31,30,31,30,31}[month];
	    	else
	    		ret  = 30;
    	}
    	return ret;
    }
    
    public static boolean isLeap(int calendarType, int year)
    {
        if (calendarType == MCalendar.HijriType) return (((year * 11) + 14) % 30) < 11;
        if(calendarType == MCalendar.JalaliType)
        {
            int rm;
            if (year > 0) rm = 474;
            else rm = 473;
            return ((((((year - rm) % 2820) + 474) + 38) * 682) % 2816) < 682;
        }
        else return ((year % 4) == 0) && (!(((year % 100) == 0) && ((year % 400) != 0)));
    }
    
    @Override
    public boolean equals(Object o)
    {
    	if(o instanceof MCalendar)
        {
            return ((MCalendar)o).julianDay == julianDay;
        }
    	return super.equals(o);
    }

    public String ToString(String pattern)
    {
        String output = pattern;

        //jalali
        output = output.replace("%jms", getJalali().monthString);
        output = output.replace("%jd", String.valueOf(getJalali().Day));
        output = output.replace("%jm", String.valueOf(getJalali().Month));
        output = output.replace("%jy", String.valueOf(getJalali().Year));
        output = output.replace("%jw", getJalali().weekDayString);

        output = output.replace("%hms", getHijri().monthString);
        output = output.replace("%hd", String.valueOf(getHijri().Day));
        output = output.replace("%hm", String.valueOf(getHijri().Month));
        output = output.replace("%hy", String.valueOf(getHijri().Year));
        output = output.replace("%hw", getHijri().weekDayString);

        output = output.replace("%mms", getMiladi().monthString);
        output = output.replace("%md", String.valueOf(getMiladi().Day));
        output = output.replace("%mm", String.valueOf(getMiladi().Month));
        output = output.replace("%my", String.valueOf(getMiladi().Year));
        output = output.replace("%mw", getMiladi().weekDayString);

        //with padding String.format("%d", Day)
        output = output.replace("%JD", String.format("%02d", getJalali().Day));
        output = output.replace("%JM", String.format("%02d", getJalali().Month));
        output = output.replace("%JY", String.format("%04d", getJalali().Year));

        output = output.replace("%HD", String.format("%02d", getHijri().Day));
        output = output.replace("%HM", String.format("%02d", getHijri().Month));
        output = output.replace("%HY", String.format("%04d", getHijri().Year));

        output = output.replace("%MD", String.format("%02d", getMiladi().Day));
        output = output.replace("%MM", String.format("%02d", getMiladi().Month));
        output = output.replace("%MY", String.format("%04d", getMiladi().Year));

        //time
        output = output.replace("%h", String.valueOf(hour));
        output = output.replace("%m", String.valueOf(minute));
        output = output.replace("%s", String.valueOf(second));

        output = output.replace("%H", String.format("%02d", hour));
        output = output.replace("%M", String.format("%02d", minute));
        output = output.replace("%S", String.format("%04d", second));
        return output;
    }

    @Override
    public String toString()
    {
        return toString(false);
    }

    public String toString(boolean withTime)
    {
        return ToString("%JY/%JM/%JD" + (withTime ? " - %H:%M" : ""));
    }
}