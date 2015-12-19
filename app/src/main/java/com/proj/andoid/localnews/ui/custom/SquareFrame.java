package com.proj.andoid.localnews.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * created by Alex Ivanov on 11.10.15.
 */
public class SquareFrame extends FrameLayout {

    public SquareFrame(Context context) {
        super(context);
    }

    public SquareFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
