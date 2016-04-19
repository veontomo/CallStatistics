package com.veontomo.callstatistics;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class MainView extends AppCompatActivity implements MVPView {

    private DiagramView mView;

    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        mPresenter = Presenter.create(this);
        mPresenter.setAppContext(getApplicationContext());
        mView = (DiagramView) findViewById(R.id.diagramView);
    }


    /**
     * Loads data into the diagram view
     *
     * @param v
     */
    public void onDataRequested(View v) {
        if (mPresenter != null){
            mPresenter.requestData();
        }
    }

    public void loadData(PhoneNumberStat data) {
        Log.i(Config.appName, "the view has received the data");
        if (mView != null) {
            mView.loadData(data);
        }

    }





}
