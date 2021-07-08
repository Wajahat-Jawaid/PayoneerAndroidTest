package com.wajahat.payoneerandroidtest.injection;

import android.app.Application;

import com.wajahat.payoneerandroidtest.PayoneerApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class})
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(PayoneerApplication application);
}