package com.veontomo.callstatistics;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * A list adapter that displays a legend for the diagram.
 */
public class DiagramDataAdapter extends BaseAdapter {
    private static final String TAG = Config.appName;
    private final Context mContext;
    private String[] mDataX;
    private int[] mDataY;

    public DiagramDataAdapter(final Context context) {
        mContext = context;
    }

    /**
     * the number of items that the adapter is to display
     */
    private int mCount = 0;

    /**
     * The number of different row layouts that this adapter handles.
     */
    private final short LAYOUT_TYPES_NUM = 2;

    /**
     * number assigned to the layouts of the first type
     */
    private final short LAYOUT_TYPE_1 = 0;

    /**
     * number assigned to the layouts of the second type
     */
    private final short LAYOUT_TYPE_2 = 1;

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mCount;
    }


    @Override
    public int getViewTypeCount() {
        return LAYOUT_TYPES_NUM;
    }


    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public String getItem(int position) {
        return mDataX[position];
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        int itemType = position % LAYOUT_TYPES_NUM;
        switch (itemType) {
            case LAYOUT_TYPE_1:
                if (row == null) {
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.row_type_1, parent, false);
                    Holder1 holder1 = new Holder1();
                    holder1.number = (TextView) row.findViewById(R.id.phoneNumber1);
                    holder1.frequency = (TextView) row.findViewById(R.id.phoneFrequency1);
                    row.setTag(holder1);
                }
                Holder1 holder1 = (Holder1) row.getTag();
                holder1.number.setText(getItem(position));
                holder1.frequency.setText(String.valueOf(mDataY[position]));
                break;
            case LAYOUT_TYPE_2:
            default:
                if (row == null) {
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.row_type_2, parent, false);
                    Holder2 holder2 = new Holder2();
                    holder2.number = (TextView) row.findViewById(R.id.phoneNumber2);
                    holder2.frequency = (TextView) row.findViewById(R.id.phoneFrequency2);
                    row.setTag(holder2);
                }
                Holder2 holder2 = (Holder2) row.getTag();
                holder2.number.setText(getItem(position));
                holder2.frequency.setText(String.valueOf(mDataY[position]));

        }
        return row;

    }

    /**
     * @param data
     */
    public void loadData(final CallHistogram data) {
        mCount = data.getSize();
        Log.i(TAG, "loadData: loading " + mCount + " items to the list adapter");
        mDataX = new String[mCount];
        mDataY = new int[mCount];
        for (int i = 0;  i < mCount; i++){
            mDataX[i] = data.getX(i);
            mDataY[i] = data.getY(i);
        }
        notifyDataSetChanged();
    }

    private static class Holder1 {
        public TextView number;
        public TextView frequency;

    }

    private static class Holder2 {
        public TextView number;
        public TextView frequency;
    }

}
