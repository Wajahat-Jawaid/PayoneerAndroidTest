package com.wajahat.payoneerandroidtest.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wajahat.payoneerandroidtest.ui.paymentmethods.PaymentMethodsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaymentMethodsListViewModel.class)
    abstract ViewModel bindPaymentMethodsListViewModel(PaymentMethodsListViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}