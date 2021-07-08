package com.wajahat.payoneerandroidtest.util;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.wajahat.payoneerandroidtest.R;
import com.wajahat.payoneerandroidtest.ui.shared.BaseActivity;

import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class DialogUtils {

    public static void showSimpleDialog(BaseActivity activity,
                                        @StringRes int title,
                                        @StringRes int message,
                                        @StringRes int positiveButtonText,
                                        @StringRes int negativeButtonText) {
        showSimpleDialog(activity, activity.getText(title), activity.getText(message),
                activity.getText(positiveButtonText), activity.getText(negativeButtonText));
    }

    public static void showSimpleDialog(BaseActivity activity,
                                        CharSequence title,
                                        CharSequence message,
                                        CharSequence positiveButtonText,
                                        CharSequence negativeButtonText) {
        PublishSubject<Boolean> publishSubject = PublishSubject.create();

        activity.runOnUiThread(() -> {
            Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.dialog_simple);
            if (dialog.getWindow() != null)
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView titleText = dialog.findViewById(R.id.title_text);
            titleText.setText(title);
            if (message != null) {
                TextView msgText = dialog.findViewById(R.id.msg_text);
                msgText.setText(message);
                msgText.setVisibility(View.VISIBLE);
            }
            Button positiveButton = dialog.findViewById(R.id.done_button);
            positiveButton.setText(positiveButtonText);
            positiveButton.setOnClickListener(v -> {
                dialog.dismiss();
                publishSubject.onNext(true);
                publishSubject.onComplete();
            });
            Button negativeButton = dialog.findViewById(R.id.cancel_button);
            negativeButton.setText(negativeButtonText);
            negativeButton.setOnClickListener(v -> {
                dialog.dismiss();
                publishSubject.onNext(false);
                publishSubject.onComplete();
            });

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });
    }
}