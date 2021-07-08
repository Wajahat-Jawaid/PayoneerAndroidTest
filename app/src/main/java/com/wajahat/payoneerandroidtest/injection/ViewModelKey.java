package com.wajahat.payoneerandroidtest.injection;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import dagger.MapKey;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}