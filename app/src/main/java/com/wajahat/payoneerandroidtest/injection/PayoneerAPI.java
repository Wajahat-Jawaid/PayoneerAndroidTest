package com.wajahat.payoneerandroidtest.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PayoneerAPI {
}