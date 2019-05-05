package com.mp.criteriaparser.source;

import android.util.Log;

import com.mp.criteriaparser.CriteriaParserApp;
import com.mp.criteriaparser.interfaces.mvp.ScanListDataSource;
import com.mp.criteriaparser.model.Scan;
import com.mp.criteriaparser.network.NetworkInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ScanListRepository implements ScanListDataSource {

    private static final String TAG = ScanListRepository.class.getSimpleName();

    private static ScanListRepository scanListRepository;

    private List<Scan> scanList;

    private ScanListRepository() {

    }

    public static ScanListRepository getInstance() {
        if (scanListRepository == null) {
            synchronized (ScanListRepository.class) {
                if (scanListRepository == null) {
                    scanListRepository = new ScanListRepository();
                }
            }
        }
        return scanListRepository;
    }

    @Override
    public void loadScans(LoadScansCallback loadScansCallback) {
        getObservable().subscribeWith(getObserver(loadScansCallback));
    }

    private Observable<List<Scan>> getObservable() {
        return CriteriaParserApp.getRetrofitInstance().create(NetworkInterface.class)
                .getScanList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observer<List<Scan>> getObserver(final LoadScansCallback loadScansCallback) {

        return new DisposableObserver<List<Scan>>() {

            @Override
            public void onNext(List<Scan> scans) {
                ScanListRepository.this.scanList = scans;
                loadScansCallback.onScansLoaded(scanList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                loadScansCallback.onError();
            }

            @Override
            public void onComplete() {
                loadScansCallback.onComplete();
            }
        };
    }
}
