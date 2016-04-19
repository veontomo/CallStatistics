package com.veontomo.callstatistics;

import android.view.View;

/**
 * View interface of MVP architecture
 */
public interface MVPView {

    void onDataRequested(View v);

    void loadData(StatisticsData data);

}
