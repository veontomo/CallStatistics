package com.veontomo.callstatistics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * A view to show a diagram
 * http://developer.android.com/intl/ru/training/custom-views/index.html
 */
public class DiagramView extends View {
    private final int[] colorCodes = new int[]{0xffd32f2f, 0xff388e3c, 0xff512da8, 0xfffbc02d, 0xff00796b, 0xffafb42b,  0xfff57c00, 0xff303f9f, 0xff455a64};

    private DiagramData mData;

    private float width = 0;
    private float height = 0;
    private Paint painter = new Paint();


    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DiagramView,
                0, 0);

        try {
            /// TODO: find out whether I need the typed array.
        } finally {
            a.recycle();
        }

    }


    public void loadData(DiagramData data) {
        Log.i(Config.appName, "loading data");
        this.mData = data;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.i(Config.appName, "new width: " + w + ", new height: " + h + ", old width: " + oldw + ", old height: " + oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(Config.appName, "drawing");
        if (mData == null) {
            return;
        }
        Paint p;

        final int mDataSize = mData.getSize();
        if (mDataSize == 0) {
            return;
        }
        float barWidth = width / mDataSize;
        float scale = height / mData.getMax();
        float h;
        int colorCodesSize = colorCodes.length;
        Log.i(Config.appName, "max: " + mData.getMax() + ", min: " + mData.getMin());
        for (int i = 0; i < mDataSize; i++) {
            h = scale * mData.getY(i);
            painter.setColor(colorCodes[i % colorCodesSize]);
            canvas.drawRect(i * barWidth, height, (i + 1) * barWidth - 1, height - h, painter);
        }

    }
}
