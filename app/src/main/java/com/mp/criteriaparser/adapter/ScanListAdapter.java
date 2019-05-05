package com.mp.criteriaparser.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mp.criteriaparser.Constants;
import com.mp.criteriaparser.R;
import com.mp.criteriaparser.activity.CriteriaActivity;
import com.mp.criteriaparser.model.Scan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanListAdapter extends RecyclerView.Adapter<ScanListAdapter.ScanViewHolder> implements View.OnClickListener {

    private List<Scan> scanList;

    public ScanListAdapter(List<Scan> scanList) {
        this.scanList = scanList;
    }

    @NonNull
    @Override
    public ScanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scan_item, viewGroup, false);
        ScanViewHolder scanViewHolder = new ScanViewHolder(v);
        return scanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScanViewHolder scanViewHolder, int i) {
        Scan scan = scanList.get(i);
        scanViewHolder.tvName.setText(scan.getName());
        scanViewHolder.tvTag.setText(scan.getTag());
        if (scan.getColor().equals("green")) {
            scanViewHolder.tvTag.setTextColor(Color.GREEN);

        } else if (scan.getColor().equals("red")) {
            scanViewHolder.tvTag.setTextColor(Color.RED);
        }

        scanViewHolder.itemView.setOnClickListener(this);
        scanViewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return scanList.size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), CriteriaActivity.class);
        intent.putExtra(Constants.XTRA_SCAN, scanList.get((Integer) v.getTag()));
        v.getContext().startActivity(intent);
    }

    public static class ScanViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvTag)
        TextView tvTag;

        public ScanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
