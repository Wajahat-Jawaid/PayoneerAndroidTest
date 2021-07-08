package com.wajahat.payoneerandroidtest.injection;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.wajahat.payoneerandroidtest.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module
public class NetworkModule {

    @PayoneerAPI
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return interceptor;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }
}