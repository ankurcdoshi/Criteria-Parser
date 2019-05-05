package com.mp.criteriaparser.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mp.criteriaparser.R;
import com.mp.criteriaparser.adapter.ScanListAdapter;
import com.mp.criteriaparser.interfaces.mvp.ScanListContract;
import com.mp.criteriaparser.model.Scan;
import com.mp.criteriaparser.presenter.ScanListPresenter;
import com.mp.criteriaparser.source.ScanListRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ScanListContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rvScans)
    RecyclerView rvScans;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        ScanListPresenter presenter = new ScanListPresenter(this, ScanListRepository.getInstance());
        presenter.loadScans();
    }

    private void initViews() {
        rvScans.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayScans(List<Scan> scans) {
        if (scans != null) {
            ScanListAdapter scanListAdapter = new ScanListAdapter(scans);
            rvScans.setAdapter(scanListAdapter);
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
