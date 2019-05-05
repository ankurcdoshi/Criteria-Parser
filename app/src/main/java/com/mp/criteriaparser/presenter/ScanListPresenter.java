package com.mp.criteriaparser.presenter;

import android.util.Log;

import com.mp.criteriaparser.interfaces.mvp.ScanListContract;
import com.mp.criteriaparser.interfaces.mvp.ScanListDataSource;
import com.mp.criteriaparser.model.Scan;

import java.util.List;

public class ScanListPresenter implements ScanListContract.Presenter {
    
    private static final String TAG = ScanListPresenter.class.getSimpleName();

    private ScanListContract.View view;
    private ScanListDataSource dataSource;

    public ScanListPresenter(ScanListContract.View view, ScanListDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    @Override
    public void loadScans() {
        dataSource.loadScans(new ScanListDataSource.LoadScansCallback() {

            @Override
            public void onScansLoaded(List<Scan> scans) {
                view.displayScans(scans);
            }

            @Override
            public void onError() {
                view.hideProgressBar();
            }

            @Override
            public void onComplete() {
                view.hideProgressBar();
            }
        });
    }

    @Override
    public void setView(ScanListContract.View view) {
        this.view = view;
    }
}
