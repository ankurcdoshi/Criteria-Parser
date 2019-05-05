package com.mp.criteriaparser.network;

import com.mp.criteriaparser.model.Scan;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkInterface {
    @GET("data")
    Observable<List<Scan>> getScanList();
}
