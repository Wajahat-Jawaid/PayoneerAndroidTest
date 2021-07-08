package com.wajahat.payoneerandroidtest.data;

import javax.annotation.Nullable;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public final class ApiResult<T> {

    private final Status status;
    @Nullable
    private final T data;
    @Nullable
    private final String message;

    enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    public ApiResult(Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(Status.SUCCESS, data, null);
    }

    public static <T> ApiResult<T> error(T data, String message) {
        return new ApiResult<>(Status.ERROR, data, message);
    }

    public static <T> ApiResult<T> loading(T data) {
        return new ApiResult<>(Status.LOADING, null, null);
    }
}