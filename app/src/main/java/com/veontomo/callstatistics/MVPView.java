package com.veontomo.callstatistics;

import android.view.View;

import java.util.ArrayList;

/**
 * View interface of MVP architecture
 */
public interface MVPView {

    void onDataRequested(View v);

    void loadData(ArrayList<Call> data);

}
