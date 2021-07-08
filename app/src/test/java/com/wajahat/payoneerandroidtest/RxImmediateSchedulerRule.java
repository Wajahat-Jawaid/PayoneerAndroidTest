package com.wajahat.payoneerandroidtest;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 * This class handles the threads scheduling while we are making the Rx calls
 */
class RxImmediateSchedulerRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());

        try {
            base.evaluate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            RxJavaPlugins.reset();
            RxAndroidPlugins.reset();
        }
        return base;
    }
}