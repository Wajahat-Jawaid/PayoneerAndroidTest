package com.wajahat.payoneerandroidtest;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;

import org.junit.After;
import org.junit.Before;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AbstractTest {

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(activity -> {
            mIdlingResource = activity.getIdlingResource();
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(mIdlingResource);
        });
    }

    @After
    public void unregisterIdlingResource() {
//        if (mIdlingResource != null) {
//            IdlingRegistry.getInstance().unregister(mIdlingResource);
//        }
    }
}