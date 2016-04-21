package com.veontomo.callstatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainView extends AppCompatActivity implements MVPView {

    private DiagramView mView;

    private Presenter mPresenter;

    private Spinner mTruncations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        mPresenter = Presenter.create(this);
        mPresenter.setAppContext(getApplicationContext());
        mView = (DiagramView) findViewById(R.id.diagramView);
        mTruncations = (Spinner) findViewById(R.id.truncations);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.truncations, R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner_item);

//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        mTruncations.setPrompt("cutoff");
        mTruncations.setAdapter(adapter);
    }


    /**
     * Loads data into the diagram view
     *
     * @param v
     */
    public void onDataRequested(View v) {
        if (mPresenter != null) {
            mPresenter.requestData();
        }
    }

    public void loadData(DiagramData data) {
        Log.i(Config.appName, "the view has received the data");
        if (mView != null) {
            mView.loadData(data);
        }


    }


}
