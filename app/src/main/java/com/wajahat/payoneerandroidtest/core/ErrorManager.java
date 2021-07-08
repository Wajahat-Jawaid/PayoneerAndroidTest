package com.wajahat.payoneerandroidtest.core;

import androidx.annotation.StringRes;

import com.wajahat.payoneerandroidtest.R;
import com.wajahat.payoneerandroidtest.ui.shared.BaseActivity;
import com.wajahat.payoneerandroidtest.util.DialogUtils;

import java.io.IOException;

import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
public class ErrorManager {

    public static void handleError(BaseActivity activity, Throwable error) {
        @StringRes int errorMessage = R.string.error_occurred;
        // HTTP Errors
        if (error instanceof HttpException) {
            HttpException httpException = (HttpException) error;
            errorMessage = handleNetworkError(httpException);
        } else if (error instanceof IOException) {
            errorMessage = handleIOException(error);
        } else {
            errorMessage = R.string.something_went_wrong;
        }
        DialogUtils.showSimpleDialog(
                activity,
                R.string.error_occurred,
                errorMessage,
                android.R.string.ok,
                android.R.string.cancel);
    }

    private static @StringRes
    int handleNetworkError(HttpException exception) {
        @StringRes int errorMessage = R.string.error_occurred;
        switch (exception.code()) {
            // Client-related errors
            case HTTP_NOT_FOUND:
                errorMessage = R.string.http_error_not_found;
                break;
            case HTTP_BAD_REQUEST:
                errorMessage = R.string.http_error_bad_request;
                break;
            case HTTP_FORBIDDEN:
                errorMessage = R.string.http_error_forbidden;
                break;
            case HTTP_CONFLICT:
                errorMessage = R.string.http_error_conflict;
                break;
            case HTTP_INTERNAL_ERROR:
                errorMessage = R.string.http_error_internal_error;
                break;
            // Server-related errors
            case HTTP_BAD_GATEWAY:
                errorMessage = R.string.http_error_bad_gateway;
                break;
        }

        return errorMessage;
    }

    /**
     * This is unlikely to happen for now. The only purpose of handling the IOException here is to
     * represent the approach about how the IOException will be handled whenever there is
     * the need to handle it
     */
    private static @StringRes
    int handleIOException(Throwable throwable) {
        @StringRes int errorMessage = R.string.ioexception_message;
        return errorMessage;
    }
}