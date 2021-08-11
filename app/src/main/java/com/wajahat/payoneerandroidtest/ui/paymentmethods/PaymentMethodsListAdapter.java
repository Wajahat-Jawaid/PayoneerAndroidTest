package com.wajahat.payoneerandroidtest.ui.paymentmethods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.jakewharton.rxbinding4.view.RxView;
import com.wajahat.payoneerandroidtest.R;
import com.wajahat.payoneerandroidtest.data.model.ApplicableNetwork;
import com.wajahat.payoneerandroidtest.databinding.ListItemPaymentMethodBinding;
import com.wajahat.payoneerandroidtest.ui.shared.BaseListAdapter;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class PaymentMethodsListAdapter extends BaseListAdapter<ApplicableNetwork> {

    private static final int SCALE_ANIMATION_DURATION = 250;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.
                inflate(
                        LayoutInflater.from(parent.getContext()),
                        getLayoutId(),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItemPaymentMethodBinding binding = (ListItemPaymentMethodBinding) holder.getViewDataBinding();
        bind(binding, position);
        super.onBindViewHolder(holder, position);
        setScaleAnimation(binding.getRoot());
    }

    private void bind(ListItemPaymentMethodBinding binding, int position) {
        binding.setModel(getItems().get(position));
        // To make User Interface more attractive, I've set 3 different payment card background
        // colors which are repeating each after 3
        @DrawableRes int background;
        if (position % 3 == 0)
            background = R.drawable.bg_credit_card_blue;
        else if (position % 3 == 1)
            background = R.drawable.bg_credit_card_yellow;
        else
            background = R.drawable.bg_credit_card_green;
        binding.setBackground(ContextCompat.getDrawable(binding.getRoot().getContext(), background));
        RxView.clicks(binding.getRoot())
                .subscribe(__ -> {
                    getItemClickSubject().onNext(getItems().get(position));
                    getItemClickSubject().onComplete();
                });
    }

    /**
     * Animate the payment card views when they show up
     *
     * @param view Item view of the payment card
     */
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(
                0.7f, 1.0f, 0.7f, 1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(SCALE_ANIMATION_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_item_payment_method;
    }
}