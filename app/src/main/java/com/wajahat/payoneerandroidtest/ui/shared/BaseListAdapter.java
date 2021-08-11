package com.wajahat.payoneerandroidtest.ui.shared;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 * Base adapter which every list adapter should inherit. It handles the common operations that
 * usually all the adapters handle like the click listeners using RxJava's PublishSubject etc.
 */
public abstract class BaseListAdapter<M> extends RecyclerView.Adapter<BaseListAdapter.ViewHolder> {

    private List<M> items = new ArrayList<>();
    private PublishSubject<M> itemClickSubject = PublishSubject.create();

    @Override
    public void onBindViewHolder(@NonNull BaseListAdapter.ViewHolder holder, int position) {
        holder.getViewDataBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateContent(List<M> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public PublishSubject<M> getItemClickSubject() {
        return itemClickSubject;
    }

    public List<M> getItems() {
        return items;
    }

    public abstract int getLayoutId();

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding viewDataBinding;

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }

        public ViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }
    }
}