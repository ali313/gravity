package com.gravity.oncepayment.Utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    Paint paint;
    int borderColor;
    int color;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setColor(int color){
        if(paint != null) {
            this.color = color;
            paint.setColor(color);
            borderColor = darkerColor(color, 0.1f);
            paint.setStrokeWidth(1);
            invalidate();
        }
    }

    public int getColor(){
        return color;
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        color = 0xFF000000;
        paint.setColor(color);
        paint.setStrokeWidth(1);
    }

    public int darkerColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 1, paint);

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 1, paint);
    }
}
