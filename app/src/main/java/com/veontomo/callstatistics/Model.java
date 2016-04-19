package com.veontomo.callstatistics;

import android.util.Log;

/**
 * Model of MVP architecture
 */
public class Model {

    private final Presenter mPresenter;

    public Model(final Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * Prepares the data to dispaly
     */
    public void prepareData() {
        Log.i(Config.appName, "the model has received a request to prepare the data");
        PhoneNumberStat stat = new PhoneNumberStat();
        onDataPrepared(stat);

    }

    /**
     * Passes the prepared data to the presenter.
     *
     * @param stat
     */
    private void onDataPrepared(PhoneNumberStat stat) {
        if (mPresenter != null) {
            mPresenter.loadData(stat);
        }

    }
}
