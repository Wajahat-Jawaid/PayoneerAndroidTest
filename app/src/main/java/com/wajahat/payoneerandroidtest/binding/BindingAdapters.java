package com.wajahat.payoneerandroidtest.binding;

import android.webkit.URLUtil;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class BindingAdapters {

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (URLUtil.isValidUrl(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }
}