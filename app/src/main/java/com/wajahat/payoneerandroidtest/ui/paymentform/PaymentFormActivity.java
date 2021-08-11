package com.wajahat.payoneerandroidtest.ui.paymentform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.wajahat.payoneerandroidtest.R;
import com.wajahat.payoneerandroidtest.data.model.InputElement;
import com.wajahat.payoneerandroidtest.util.AppConstants;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PaymentFormActivity extends AppCompatActivity {

    private List<InputElement> inputElements;

    public static Intent createPaymentFormIntent(Context context, ArrayList<InputElement> inputElements) {
        Intent intent = new Intent(context, PaymentFormActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AppConstants.INPUT_ELEMENTS, inputElements);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_payment_form);
        getArguments();
        if (inputElements != null) {
            Timber.d("inputElements: %s", new Gson().toJson(inputElements));
            setupForm();
        } else {
            Toast.makeText(this, getString(R.string.error_input_elements), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void getArguments() {
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey(AppConstants.INPUT_ELEMENTS)) {
                inputElements = bundle.getParcelableArrayList(AppConstants.INPUT_ELEMENTS);
            }
        }
    }

    private void setupForm() {
        final LinearLayout cardFormLayout = findViewById(R.id.card_form_layout);
        for (InputElement element : inputElements) {
            EditText editText = new EditText(this);
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
}