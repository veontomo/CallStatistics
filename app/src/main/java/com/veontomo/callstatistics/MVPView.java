package com.veontomo.callstatistics;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * View interface of MVP architecture
 */
public interface MVPView {

    void onDataRequested(View v);

    void loadData(DiagramData data);

    boolean isTotalCallsChecked();

    boolean isCallsADayChecked();

    void checkTotalCalls();

    void checkCallsADay();


    void onError(String text);

    void loadListData(final CallHistogram calls);
}
