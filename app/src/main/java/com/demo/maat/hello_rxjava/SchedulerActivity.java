package com.demo.maat.hello_rxjava;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.demo.maat.hello_rxjava.common.activities.SampleActivityBase;
import com.demo.maat.hello_rxjava.common.logger.Log;
import com.demo.maat.hello_rxjava.common.logger.LogFragment;
import com.demo.maat.hello_rxjava.common.logger.LogWrapper;
import com.demo.maat.hello_rxjava.common.logger.MessageOnlyLogFilter;


public class SchedulerActivity extends SampleActivityBase {

    public static final String TAG = "SchedulerActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler_activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SchedulerFragment fragment = new SchedulerFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }


    @Override
    public void initializeLogging() {
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i(TAG, "Ready");
    }
}
