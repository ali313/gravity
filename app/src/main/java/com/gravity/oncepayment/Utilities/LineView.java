package com.gravity.oncepayment.Utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.gravity.oncepayment.R;


public class LineView extends View
{
    static public int ORIENTATION_HORIZONTAL = 0;
    static public int ORIENTATION_VERTICAL = 1;
    private Paint mPaint;
    private int orientation;

    int dashGap = 5, dashLength = 5, dashThickness = 3;
    int color = 0xff000000;

    public LineView(Context context)
    {
        this(context, null);
    }

    public LineView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);

    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if(attrs != null)
        {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LineView, defStyleAttr, 0);

            try
            {
                dashGap = a.getDimensionPixelSize(R.styleable.LineView_dashGap, dashGap);
                dashLength = a.getDimensionPixelSize(R.styleable.LineView_dashLength, dashLength);
                dashThickness = a.getDimensionPixelSize(R.styleable.LineView_dashThickness, dashThickness);
                color = a.getColor(R.styleable.LineView_color, color);
                orientation = a.getInt(R.styleable.LineView_orientation, ORIENTATION_HORIZONTAL);
            }
            finally
            {
                a.recycle();
            }
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dashThickness);
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashLength, dashGap,}, 0));
    }

    //<editor-fold desc="Setter Getter"/>
    public void setColor(int color)
    {
        this.color = color;
        mPaint.setColor(color);
        invalidate();
    }

    public int getColor()
    {
        return this.color;
    }

    public int getDashGap()
    {
        return dashGap;
    }

    public void setDashGap(int dashGap)
    {
        this.dashGap = dashGap;
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashLength, dashGap,}, 0));
        requestLayout();
    }

    public int getDashLength()
    {
        return dashLength;
    }

    public void setDashLength(int dashLength)
    {
        this.dashLength = dashLength;
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashLength, dashGap,}, 0));
        requestLayout();
    }

    public int getOrientation()
    {
        return orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = orientation;
        requestLayout();
    }

    public int getDashThickness()
    {
        return dashThickness;
    }

    public void setDashThickness(int dashThickness)
    {
        this.dashThickness = dashThickness;
    }
    //</editor-fold/>

    //<editor-fold desc="measerment"/>
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int desiredWidth = (orientation == ORIENTATION_HORIZONTAL ? dashLength : dashThickness) + getPaddingLeft() + getPaddingRight();
        int desiredHeight = (orientation == ORIENTATION_VERTICAL ? dashLength : dashThickness) + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec), measureDimension(desiredHeight, heightMeasureSpec));

    }

    private int measureDimension(int desiredSize, int measureSpec)
    {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
        } else
        {
            result = desiredSize;
            if (specMode == MeasureSpec.AT_MOST)
            {
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize){
            //MLog.e("The view is too small, the content might get cut");
        }
        return result;
    }
    //</editor-fold>

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (orientation == ORIENTATION_HORIZONTAL)
        {
            float center = getHeight() * .5f;
            canvas.drawLine(getPaddingLeft(), center, getWidth() - getPaddingRight(), center, mPaint);
        }
        else
        {
            float center = getWidth() * .5f;
            canvas.drawLine(center, getPaddingTop(), center, getHeight() - getPaddingBottom(), mPaint);
        }
    }
}