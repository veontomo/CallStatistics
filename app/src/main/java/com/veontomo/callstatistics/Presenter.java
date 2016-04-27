package com.veontomo.callstatistics;

import android.content.Context;

import java.util.List;

/**
 * Presenter of MVP architecture
 */
public class Presenter {

    private DiagramModel mDiagramModel;
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
        DiagramModel diagramModel = new DiagramModel(presenter);
        presenter.setDiagramModel(diagramModel);
        return presenter;
    }

    public void setDiagramModel(DiagramModel diagramModel) {
        mDiagramModel = diagramModel;
    }

    /**
     * Requests data from the model
     */
    public void requestData() {
        if (this.mDiagramModel != null) {
            mDiagramModel.prepareData();
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
        this.requestData();

    }



    public void onError(final String text){
        mView.onError(text);
    }

    /**
     * Passes the calls to the view
     */
    public void loadListData(CallHistogram calls) {
        if (mView != null){
            mView.loadListData(calls);
        }

    }
}
