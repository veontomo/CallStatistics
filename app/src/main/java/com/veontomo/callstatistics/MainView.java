package com.veontomo.callstatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainView extends AppCompatActivity implements MVPView {

    private DiagramView mView;

    private Presenter mPresenter;

    private Spinner mTruncations;

    private RadioButton mTotalCalls;

    private RadioButton mCallsADay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        mPresenter = Presenter.create(this);
        mPresenter.setAppContext(getApplicationContext());
        mView = (DiagramView) findViewById(R.id.diagramView);
        mTruncations = (Spinner) findViewById(R.id.truncations);
        mTotalCalls = (RadioButton) findViewById(R.id.totalCalls);
        mCallsADay = (RadioButton) findViewById(R.id.callsADay);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.truncations, R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        mTruncations.setAdapter(adapter);

        mPresenter.initView();


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

    @Override
    public boolean isTotalCallsChecked() {
        return (mTotalCalls != null) && mTotalCalls.isChecked();
    }

    @Override
    public boolean isCallsADayChecked() {
        return (mCallsADay != null) && mCallsADay.isChecked();
    }

    @Override
    public void checkTotalCalls() {
        if (mTotalCalls != null) {
            mTotalCalls.setChecked(true);
        }
    }

    @Override
    public void checkCallsADay() {
        if (mCallsADay != null) {
            mCallsADay.setChecked(true);
        }
    }

    @Override
    public void onError(String text) {
        Toast.makeText(MainView.this, text, Toast.LENGTH_SHORT).show();
    }


}
