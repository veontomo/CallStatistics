package com.veontomo.callstatistics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * A view to show a diagram
 * http://developer.android.com/intl/ru/training/custom-views/index.html
 */
public class DiagramView extends View {
    private StatisticsData mData;

    private float width = 0;
    private float height = 0;
    private Paint painter1 = new Paint();
    private Paint painter2 = new Paint();


    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DiagramView,
                0, 0);
        painter1.setColor(Color.BLUE);
        painter2.setColor(Color.RED);

        try {
            /// TODO: find out whether I need the typed array.
        } finally {
            a.recycle();
        }

    }


    public void loadData(StatisticsData data) {
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
        if (mData == null){
            return;
        }
        Paint p;

        int N = mData.getSize();
        if (N  == 0) {
            return;
        }
        float barWidth = width / N;
        Random rndGenerator = new Random();
        for (int i = 0; i < N; i++) {
            p = (rndGenerator.nextFloat() > 0.5f) ? painter1 : painter2;
            float h = (float) mData.getValue(i);
            canvas.drawRect(i * barWidth, height, (i + 1) * barWidth - 1, height - h, p);
        }

    }
}
