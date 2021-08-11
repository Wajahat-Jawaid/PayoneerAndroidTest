package com.wajahat.payoneerandroidtest.ui.paymentform;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.wajahat.payoneerandroidtest.R;
import com.wajahat.payoneerandroidtest.data.model.InputElement;
import com.wajahat.payoneerandroidtest.databinding.FragmentPaymentFormBinding;
import com.wajahat.payoneerandroidtest.injection.Injectable;
import com.wajahat.payoneerandroidtest.ui.shared.BaseFragment;
import com.wajahat.payoneerandroidtest.util.AppConstants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PaymentFormFragment
        extends BaseFragment<FragmentPaymentFormBinding, PaymentFormViewModel> {

    private List<InputElement> inputElements;

    public static PaymentFormFragment getInstance(ArrayList<InputElement> inputElements) {
        PaymentFormFragment fragment = new PaymentFormFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AppConstants.INPUT_ELEMENTS, inputElements);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNavArguments();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (inputElements != null)
            setupForm();
        else {
            showToast(getString(R.string.error_input_elements));
        }
    }

    private void getNavArguments() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (bundle.containsKey(AppConstants.INPUT_ELEMENTS)) {
                Timber.d("inputElements: %s", new Gson().toJson(inputElements));
                inputElements = bundle.getParcelableArrayList(AppConstants.INPUT_ELEMENTS);
            } else {
                Timber.e("InputElements is not valid");
            }
        }
    }

    private void setupForm() {
        final LinearLayout cardFormLayout = getViewDataBinding().cardFormLayout;
        for (InputElement element : inputElements) {
            EditText editText = new EditText(getContext());
            editText.setHint(element.getName());
            editText.setLayoutParams(getEditTextLayoutParams(editText));
            cardFormLayout.addView(editText);
        }
    }

    private LinearLayout.LayoutParams getEditTextLayoutParams(EditText editText) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) editText.getLayoutParams();
        params.setMargins(0, 0, 0,
                (int) getResources().getDimension(R.dimen.payment_form_fields_bottom_margin)
        );

        return params;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_payment_form;
    }

    @Override
    public Class<PaymentFormViewModel> findViewModel() {
        return PaymentFormViewModel.class;
    }
}