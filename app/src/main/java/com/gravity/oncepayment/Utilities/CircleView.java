package com.gravity.oncepayment.Utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    Paint paint;

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
            paint.setColor(color);
            invalidate();
        }
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF000000);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
    }
}
