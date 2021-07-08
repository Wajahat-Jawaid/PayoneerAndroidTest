package com.wajahat.payoneerandroidtest.api;

import com.wajahat.payoneerandroidtest.data.response.GetPaymentMethodsResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Singleton
public class PaymentMethodsRepository {

    private final WebService service;

    @Inject
    public PaymentMethodsRepository(WebService service) {
        this.service = service;
    }

    /**
     * I follow the pagination approach for every kind of list. Since the API does not have
     * any mechanism of following the pagination, so I've just fetched the whole networks object.
     * But since I've used Jetpack's Pagination library in other projects, so we can easily
     * integrate the DataSource and DataSourceFactory classes
     */
    public Observable<GetPaymentMethodsResponse> getPaymentMethods() {
        return service.getPaymentMethods();
    }
}