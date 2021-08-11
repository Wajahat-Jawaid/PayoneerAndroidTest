package com.wajahat.payoneerandroidtest.injection;

import com.wajahat.payoneerandroidtest.MainActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}