package com.veontomo.callstatistics;

import android.content.Context;

/**
 * Presenter of MVP architecture
 */
public class Presenter {

    private Model mModel;
    private final MVPView mView;
    /**
     * application context
     */
    private Context mAppContext;

    public Presenter(MVPView view) {
        mView = view;
    }

    public static Presenter create(MVPView view) {
        Presenter presenter = new Presenter(view);
        Model model = new Model(presenter);
        presenter.setModel(model);
        return presenter;
    }

    public void setModel(Model model) {
        mModel = model;
    }

    /**
     * Requests data from the model
     */
    public void requestData() {
        if (this.mModel != null) {
            mModel.prepareData();
        }

    }

    /**
     * Passes the data to the view
     *
     * @param stat
     */
    public void loadData(DiagramData stat) {
        if (this.mView != null) {
            mView.loadData(stat);
        }

    }

    /**
     * Application context setter
     *
     * @param appContext
     */
    public void setAppContext(final Context appContext) {
        mAppContext = appContext;
    }

    public Context getAppContext() {
        return mAppContext;
    }

    /**
     * Set the values of the view (radio buttons), request data from the model.
     */
    public void initView() {
        initializeRadioButtons();
        this.requestData();

    }

    /**
     * Check the radio button corresponding to total calls, in case none of the radio buttons is checked.
     */
    private void initializeRadioButtons() {
        if (!mView.isCallsADayChecked() && !mView.isTotalCallsChecked()) {
            mView.checkTotalCalls();
        }
    }

    public void onError(final String text){
        mView.onError(text);
    }
}
