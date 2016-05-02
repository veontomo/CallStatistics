package com.veontomo.callstatistics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * A view to show a diagram
 * http://developer.android.com/intl/ru/training/custom-views/index.html
 */
public class DiagramView extends View {
    private final int[] colorCodes = new int[]{0xffd32f2f, 0xff388e3c, 0xff512da8, 0xfffbc02d, 0xff00796b, 0xffafb42b, 0xfff57c00, 0xff303f9f, 0xff455a64};

    private DiagramData mData;

    private float width = 0;
    private float height = 0;
    private final Paint painter = new Paint();

    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Loads the data to visualize
     *
     * @param data
     */
    public void loadData(DiagramData data) {
        this.mData = data;
        invalidate();
        requestLayout();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) {
            return;
        }
        final int mDataSize = mData.getSize();
        if (mDataSize == 0) {
            return;
        }
        float barWidth = width / mDataSize;
        float scale = height / mData.getMax();
        float h;
        int colorCodesSize = colorCodes.length;
        for (int i = 0; i < mDataSize; i++) {
            h = scale * mData.getY(i);
            painter.setColor(colorCodes[i % colorCodesSize]);
            canvas.drawRect(i * barWidth, height - h, (i + 1) * barWidth - 1, height, painter);
        }
    }
}
