package com.mp.criteriaparser.interfaces.mvp;

import com.mp.criteriaparser.model.Scan;

import java.util.List;

public interface ScanListContract {

    interface View extends BasView {
        void displayScans(List<Scan> scans);
        void hideProgressBar();
    }

    interface Presenter extends BasePresenter<View> {
        void loadScans();
    }
}
