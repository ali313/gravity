package com.gravity.oncepayment.Utilities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

public class VertTextView extends AppCompatTextView
{
    public VertTextView(Context context)
    {
        this(context, null);
    }

    public VertTextView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public VertTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr)
    {
        if (attrs != null)
        {
            //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VertTextView, defStyleAttr, 0);
            //a.recycle();
        }

        setTypeface(FontCache.get(getContext(), FontCache.NASIM));
    }

    private int measureDimension(int desiredSize, int measureSpec)
    {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        }
        else
        {
            result = desiredSize;
            if (specMode == MeasureSpec.AT_MOST)
            {
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize)
        {
            Log.e("ChartView", "The view is too small, the content might get cut");
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        TextPaint p = getPaint();
        float width = p.measureText(getText().toString());
        float height = p.descent() - p.ascent();

        int desiredWidth = (int)height + getPaddingLeft() + getPaddingRight();
        int desiredHeight = (int)width + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec),
                measureDimension(desiredHeight, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.rotate(90, getWidth()/ 2, getHeight()/ 2);

        float cy = 0;
        float cx = 0;
        canvas.drawText(getText().toString(), cx, cy, getPaint());
    }
}
