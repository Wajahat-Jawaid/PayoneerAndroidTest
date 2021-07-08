package com.wajahat.payoneerandroidtest.ui.paymentmethods;

import android.app.Application;

import com.wajahat.payoneerandroidtest.api.PaymentMethodsRepository;
import com.wajahat.payoneerandroidtest.data.response.GetPaymentMethodsResponse;
import com.wajahat.payoneerandroidtest.ui.shared.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PaymentMethodsListViewModel extends BaseViewModel {

    private final PaymentMethodsRepository repository;

    @Inject
    public PaymentMethodsListViewModel(Application application, PaymentMethodsRepository repository) {
        super(application);
        this.repository = repository;
    }

    public Observable<GetPaymentMethodsResponse> getPaymentMethods() {
        return repository.getPaymentMethods();
    }
}