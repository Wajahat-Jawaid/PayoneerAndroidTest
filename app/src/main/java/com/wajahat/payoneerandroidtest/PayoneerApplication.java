package com.wajahat.payoneerandroidtest;

import android.app.Activity;
import android.app.Application;

import com.wajahat.payoneerandroidtest.injection.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PayoneerApplication extends Application implements HasActivityInjector {

    @Inject
    protected DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}