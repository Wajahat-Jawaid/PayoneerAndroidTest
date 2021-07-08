package com.wajahat.payoneerandroidtest.api;

import com.wajahat.payoneerandroidtest.BuildConfig;
import com.wajahat.payoneerandroidtest.data.response.GetPaymentMethodsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public interface WebService {

    @GET(BuildConfig.BASE_URL + "listresult.json")
    Observable<GetPaymentMethodsResponse> getPaymentMethods();
}