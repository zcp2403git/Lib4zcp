package com.netease.commonlibrary.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LineView extends View {
    private int mWidth;

    private int mHeight;

    private int mColor;

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mColor);
        paint.setAlpha(28);
        paint.setStrokeWidth(2);
        mWidth = getWidth();
        mHeight = getHeight();
        if (mWidth > mHeight) {
            canvas.drawLine(0, 0, mWidth, 0, paint);
        } else {
            canvas.drawLine(0, 0, 0, mHeight, paint);
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }

}
