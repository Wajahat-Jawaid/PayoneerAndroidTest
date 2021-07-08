package com.wajahat.payoneerandroidtest.injection;

import com.wajahat.payoneerandroidtest.ui.SplashFragment;
import com.wajahat.payoneerandroidtest.ui.paymentmethods.PaymentMethodsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    public abstract SplashFragment contributeSplashFragment();

    @ContributesAndroidInjector
    public abstract PaymentMethodsListFragment contributePaymentMethodsListFragment();
}