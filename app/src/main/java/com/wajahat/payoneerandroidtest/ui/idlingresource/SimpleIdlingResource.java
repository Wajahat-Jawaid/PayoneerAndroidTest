package com.wajahat.payoneerandroidtest.ui.idlingresource;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */

import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A very simple implementation of {@link IdlingResource}.
 */
public class SimpleIdlingResource implements IdlingResource {

    private volatile ResourceCallback callback;
    private final AtomicBoolean isIdleNow = new AtomicBoolean(false);
    private final String name;

    public SimpleIdlingResource(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void reset() {
        this.isIdleNow.set(false);
    }

    public void setIdleState(boolean isIdleNow) {
        this.isIdleNow.set(isIdleNow);
        if (isIdleNow && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}