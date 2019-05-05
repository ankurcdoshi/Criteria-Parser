package com.mp.criteriaparser.interfaces.mvp;

import com.mp.criteriaparser.model.Scan;

import java.util.List;

public interface ScanListDataSource {

    interface LoadScansCallback {
        void onScansLoaded(List<Scan> scans);
        void onError();
        void onComplete();
    }

    void loadScans(LoadScansCallback loadScansCallback);
}
