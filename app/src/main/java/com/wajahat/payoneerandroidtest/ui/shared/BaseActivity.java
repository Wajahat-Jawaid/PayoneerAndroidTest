package com.wajahat.payoneerandroidtest.ui.shared;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 * Base activity which every activity must inherit to share the common elements and behaviors
 */
public abstract class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
    }

    @Inject
    protected DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    // region Messages
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    // endregion

    /**
     * Abstract methods
     */
    public abstract int getViewId();
}