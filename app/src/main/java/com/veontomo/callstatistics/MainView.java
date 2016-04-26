package com.veontomo.callstatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainView extends AppCompatActivity implements MVPView {

    private static final String TAG = Config.appName;
    private DiagramView mDiagramView;
    private ListView mListView;

    private Presenter mPresenter;

    private Spinner mTruncations;

    private RadioButton mTotalCalls;

    private RadioButton mCallsADay;

    private DiagramDataAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        mPresenter = Presenter.create(this);
        mPresenter.setAppContext(getApplicationContext());
        mDiagramView = (DiagramView) findViewById(R.id.diagramView);
        mTruncations = (Spinner) findViewById(R.id.truncations);
        mTotalCalls = (RadioButton) findViewById(R.id.totalCalls);
        mCallsADay = (RadioButton) findViewById(R.id.callsADay);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.truncations, R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        mTruncations.setAdapter(adapter);

        mListView = (ListView) findViewById(R.id.listView);
        mListAdapter = new DiagramDataAdapter(getApplicationContext());
        mListView.setAdapter(mListAdapter);

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
        if (mDiagramView != null) {
            mDiagramView.loadData(data);
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

    @Override
    public void loadListData(final CallHistogram calls) {
        if (mListAdapter != null) {
            mListAdapter.loadData(calls);
        } else {
            Log.i(TAG, "loadListData: the list adapter is not defined");
        }
    }


}
