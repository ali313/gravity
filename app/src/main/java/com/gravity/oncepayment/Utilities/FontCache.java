package com.gravity.oncepayment.Utilities;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by ali on 9/18/2016.
 */
public class FontCache
{
    public static final String NASIM = "fonts/font.ttf";
    public static final String FONT_ICON = "fonts/fontawesome-webfont.ttf";

    private static HashMap<String, Typeface> fonts = new HashMap<String, Typeface>();

    public static Typeface get(Context context, String fontAddress)
    {
        if(fontAddress == null || fontAddress.isEmpty())
        {
            return get(context, NASIM);
        }
        Typeface font = fonts.get(fontAddress);
        if(font == null)
        {
            font = Typeface.createFromAsset(context.getAssets(), fontAddress);
            fonts.put(fontAddress, font);
        }

        return font;
    }
}
