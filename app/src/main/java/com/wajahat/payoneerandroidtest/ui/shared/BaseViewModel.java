package com.wajahat.payoneerandroidtest.ui.shared;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull @NotNull Application application) {
        super(application);
    }
}