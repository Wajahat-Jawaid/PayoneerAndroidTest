package com.wajahat.payoneerandroidtest.ui.paymentmethods;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wajahat.payoneerandroidtest.R;
import com.wajahat.payoneerandroidtest.core.ErrorManager;
import com.wajahat.payoneerandroidtest.data.model.ApplicableNetwork;
import com.wajahat.payoneerandroidtest.databinding.FragmentListPaymentMethodsBinding;
import com.wajahat.payoneerandroidtest.injection.Injectable;
import com.wajahat.payoneerandroidtest.ui.shared.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PaymentMethodsListFragment
        extends BaseFragment<FragmentListPaymentMethodsBinding, PaymentMethodsListViewModel>
        implements Injectable {

    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void loadData() {
        disposables.add(getViewModel().getPaymentMethods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            hideLoadingAnimation();
                            // If response data has any issues, pass that to our ErrorManager
                            if (response.getNetworks() == null
                                    || response.getNetworks().getApplicable() == null
                                    || response.getNetworks().getApplicable().isEmpty()) {
                                ErrorManager.handleError(getBaseActivity(), new Throwable());
                                return;
                            }
                            handleLoadPaymentMethodsSuccess(response.getNetworks().getApplicable());
                        },
                        throwable -> {
                            hideLoadingAnimation();
                            ErrorManager.handleError(getBaseActivity(), throwable);
                        }
                )
        );
    }

    private void handleLoadPaymentMethodsSuccess(List<ApplicableNetwork> paymentMethods) {
        PaymentMethodsListAdapter adapter = new PaymentMethodsListAdapter();
        getViewDataBinding().recyclerviewPaymentMethods.setAdapter(adapter);
        adapter.updateContent(paymentMethods);
        // Handle the click listeners using adapter's PublishSubject here
    }

    private void hideLoadingAnimation() {
        getViewDataBinding().animView.pauseAnimation();
        getViewDataBinding().animView.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
            disposables = null;
        }
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_list_payment_methods;
    }

    @Override
    public Class<PaymentMethodsListViewModel> findViewModel() {
        return PaymentMethodsListViewModel.class;
    }
}