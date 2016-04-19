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
    private String mText;

    private float width = 0;
    private float height = 0;
    private Paint painter1 = new Paint();
    private Paint painter2 = new Paint();

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(Context, AttributeSet, int)
     */
    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DiagramView,
                0, 0);
        painter1.setColor(Color.BLUE);
        painter2.setColor(Color.RED);

        try {
            mText = a.getString(R.styleable.DiagramView_name);
        } finally {
            a.recycle();
        }

    }


    public void loadData() {
        Log.i(Config.appName, "loading data");
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
        Paint p;
        int N = 100;
        float barWidth = width / N;
        Random rndGenerator = new Random();
        for (int i = 0; i < N; i++) {
            p = (rndGenerator.nextFloat() > 0.5f) ? painter1 : painter2;
            float h = height * rndGenerator.nextFloat();
            canvas.drawRect(i * barWidth, height, (i + 1) * barWidth - 1, height - h, p);
        }

    }
}
