package com.gravity.oncepayment.Utilities.datetime;

/**
 * Created by ali on 9/18/2016.
 */
public class HijriDate extends MDate
{
    public static final int MONTH_MOHARRAM = 0;
    public static final int MONTH_SAFAR = 1;
    public static final int MONTH_RABI_AL_AVVAL = 2;
    public static final int MONTH_RABI_AL_THANI = 3;
    public static final int MONTH_JAMADI_AL_AVVAL = 4;
    public static final int MONTH_JAVADI_AL_THANI = 5;
    public static final int MONTH_RAJAB = 6;
    public static final int MONTH_SHABAN = 7;
    public static final int MONTH_RAMADUAN = 8;
    public static final int MONTH_SHAVVAL = 9;
    public static final int MONTH_ZIGHADEH = 10;
    public static final int MONTH_ZIHAJJEH = 11;

    public HijriDate(int year, int month, int day, int weekDay)
    {
        super(year, month, day, weekDay);
    }

    @Override
    public String[] getWeekDays()
    {
        return new String[] { "al-ahad", "al-'ithnayn", "ath-thalatha'", "al-arbia`aa'", "al-khamis", "al-jumu`a", "as-sabt" };
    }

    @Override
    public String[] getMonthNames()
    {
        return new String[] { "محرم", "صفر", "ربیع الاول", "ربیع الثانی", "جمادی الاول", "جمادی الثانی", "رجب", "شعبان", "رمضان", "شوال", "ذی القعده", "ذی الحجه" };
    }

    @Override
    public boolean getIsLeap()
    {
        return (((Year * 11) + 14) % 30) < 11;
    }

    @Override
    public int getCurrentMonthDays()
    {
        return 30;
    }
}
