package com.wajahat.payoneerandroidtest.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.wajahat.payoneerandroidtest.R;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class SplashFragment extends Fragment {

    private static final int FAKE_LOADING_DURATION = 2000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Navigate to the PaymentMethodsListFragment after a delay
        new Handler().postDelayed(() -> NavHostFragment
                        .findNavController(SplashFragment.this)
                        .navigate(R.id.action_splash_fragment_to_photosList_fragment),
                FAKE_LOADING_DURATION);
    }
}