package com.gravity.oncepayment.Utilities;

/**
 * Created by ali on 9/11/2016.
 */
public class TextUtils
{
    public static String toPersianNumeric(int number)
    {
        return toPersianNumeric((long)number);
    }

    public static String toPersianNumeric(long number)
    {
        StringBuilder str = new StringBuilder();

        long n = number;
        while(n / 10 != 0)
        {
            str.insert(0, (char)(0x0660 + (n % 10)));
            n = n / 10;
        }
        str.insert(0, (char)(0x0660 + (n % 10)));

        return str.toString();
    }

    public static String fixKafYa(String input)
    {
        if(input == null) return null;
        return input.replace("ی", "ي").replace("ک", "ك");
    }

    public static String toPersianNumeric(CharSequence text)
    {
        StringBuilder str = new StringBuilder();

        int length = text.length();
        for(int i = 0; i < length; i++)
        {
            char chr = text.charAt(i);
            if(chr >= 0x0030 && chr <=0x0039)
            {
                str.append((char)((int)chr + 0x0630));
            }
            else
            {
                str.append(chr);
            }
        }
        return str.toString();
    }

    public static String toEnglishNumeric(String text)
    {
        StringBuilder str = new StringBuilder();

        for(char chr : text.toCharArray())
        {
            if(chr >= 0x0660 && chr <=0x0669)
            {
                str.append((char)((int)chr - 0x0630));
            }
            else if(chr >= 0x06F0 && chr <=0x06F9)
            {
                str.append((char)((int)chr - 0x06C0));
            }
            else
            {
                str.append(chr);
            }
        }
        return str.toString();
    }

    public static String highlight(String patternStr, String str, String color)
    {
        String[] patterns = patternStr.split("\\+");
        for (String pattern : patterns)
        {
            if (pattern.charAt(0) == '!') continue;
            str = str.replaceAll("(" + pattern + ")", "◄$1►");
            str = str.replaceAll("◄\\s", " ◄").replaceAll("\\s►", "► ");
            str = str.replaceAll("([^\\s]*)◄", "◄$1").replaceAll("►([^\\s]*)", "$1►");

            while (str.matches("◄[^\\s]*◄"))
            {
                str = str.replaceAll("(◄[^\\s]*)◄", "$1").replaceAll("►([^\\s]*►)", "$1");
            }
            str = str.replaceAll("◄", "<font color=\"" + color + "\">").replaceAll("►", "</font>");
        }
        return str;
    }

    public static String subString(String input, String keyword)
    {
        StringBuilder sb = new StringBuilder(input);
        int length = sb.length() - 1;
        int pos = sb.indexOf(keyword);
        int start = pos;
        int end = pos + keyword.length();
        while (--start > 0 && !((pos - start > 40 && sb.charAt(start) == ' ')));
        while (++end < length && !((end - pos > 40 && sb.charAt(end) == ' ')));

        if(start < 0) start = 0;
        if(end >= length) end = length - 1;
        if(end < start) end = start;

        input = sb.substring(start, end);
        if (start > 0)
        {
            input = "<b>...</b> " + input;
        }
        if (end < length)
        {
            input += " <b>...</b>";
        }
        return input;
    }
}
