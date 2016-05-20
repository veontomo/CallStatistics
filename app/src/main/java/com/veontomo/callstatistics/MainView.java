package com.veontomo.callstatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainView extends AppCompatActivity implements MVPView {

    private static final String TAG = Config.appName;

    private DiagramView mDiagramView;

    private Presenter mPresenter;

    private DiagramDataAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        mPresenter = Presenter.create(this);
        mPresenter.setAppContext(getApplicationContext());
        mDiagramView = (DiagramView) findViewById(R.id.diagramView);

        final Spinner mTruncations = (Spinner) findViewById(R.id.cutoffValues);
        if (mTruncations != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.truncations, R.layout.spinner);
            adapter.setDropDownViewResource(R.layout.spinner_item);
            mTruncations.setAdapter(adapter);

            mTruncations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String value = parentView.getItemAtPosition(position).toString();
                    if (value != null) {
                        int cutoff = Integer.parseInt(value);
                        mPresenter.setCutoff(cutoff);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }
            });
        }


        final ListView mListView = (ListView) findViewById(R.id.listView);
        if (mListView != null) {
            mListAdapter = new DiagramDataAdapter(getApplicationContext());
            mListView.setAdapter(mListAdapter);
            mListView.setEmptyView(findViewById(android.R.id.empty));
        }

        mPresenter.initView();
    }


    public void loadData(DiagramData data) {
        if (mDiagramView != null) {
            mDiagramView.loadData(data);
        }
        if (mListAdapter != null) {
            mListAdapter.loadData(data);
        } else {
            Log.i(TAG, "loadData: the list adapter is not defined");
        }
    }


    @Override
    public void onError(String text) {
        Toast.makeText(MainView.this, text, Toast.LENGTH_SHORT).show();
    }

}
