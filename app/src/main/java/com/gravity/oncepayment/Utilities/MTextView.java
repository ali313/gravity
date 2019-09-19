package com.gravity.oncepayment.Utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

public class MTextView extends AppCompatTextView
{
    public MTextView(Context context)
    {
        super(context);
        init();
    }

    public MTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public MTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf");
        setTypeface(tf);
    }
}
