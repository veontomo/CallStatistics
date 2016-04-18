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
    private String mText;

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

        try {
            mText = a.getString(R.styleable.DiagramView_name);
        } finally {
            a.recycle();
        }

    }


    public void setText(String text) {
        this.mText = text;
        Log.i(Config.appName, "set text to \"" + text + "\".");
        invalidate();
        requestLayout();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0f, 0f, 20f, 200f, new Paint(1));
        canvas.drawRect(0f, 30f, 50f, 100f, new Paint(1));

        canvas.drawText("aaaaaa ]]]]]]]]]]]] ]]] ]] ] ] ] ] ] ] ]", 10, 200, new Paint(0));

        invalidate();
        requestLayout();
    }
}
