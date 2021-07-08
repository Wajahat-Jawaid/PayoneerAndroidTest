package com.wajahat.payoneerandroidtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.IdlingResource;

import com.wajahat.payoneerandroidtest.ui.idlingresource.SimpleIdlingResource;
import com.wajahat.payoneerandroidtest.ui.shared.BaseActivity;

public class MainActivity extends BaseActivity {

    @Nullable
    private SimpleIdlingResource mIdlingResource;
    private boolean resultHandled;

    @Override
    protected void onResume() {
        super.onResume();
        setResultHandledIdleState();
    }

    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource(getClass().getSimpleName() + "-resultHandledIdlingResource");
        }
        if (resultHandled) {
            mIdlingResource.setIdleState(true);
        } else {
            mIdlingResource.reset();
        }
        return mIdlingResource;
    }

    /**
     * Set the result handled idle state for the IdlingResource
     */
    private void setResultHandledIdleState() {
        resultHandled = true;
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }
    }
}