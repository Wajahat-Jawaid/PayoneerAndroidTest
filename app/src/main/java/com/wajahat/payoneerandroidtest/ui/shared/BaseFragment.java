package com.wajahat.payoneerandroidtest.ui.shared;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public abstract class BaseFragment<VDB extends ViewDataBinding, VM extends ViewModel> extends Fragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private VDB viewDataBinding;
    private VM viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewId(), container, false);
        viewDataBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(findViewModel());
        navController = Navigation.findNavController(view);
    }

    // region Messages
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    // endregion

    // region Accessors
    public NavController getNavController() {
        return navController;
    }
    public VDB getViewDataBinding() {
        return viewDataBinding;
    }
    public VM getViewModel() {
        return viewModel;
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
    // endregion

    /**
     * Abstract methods
     */
    public abstract int getViewId();
    public abstract Class<VM> findViewModel();
}