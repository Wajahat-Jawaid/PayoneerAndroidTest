package com.wajahat.payoneerandroidtest.injection;

import com.wajahat.payoneerandroidtest.BuildConfig;
import com.wajahat.payoneerandroidtest.api.WebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module(includes = {ViewModelModule.class, NetworkModule.class})
public class AppModule {

    @Singleton
    @Provides
    public WebService provideWebService(@PayoneerAPI OkHttpClient okHttpClient,
                                        GsonConverterFactory factory) {
        return createWebService(okHttpClient, factory);
    }

    private WebService createWebService(OkHttpClient okHttpClient,
                                        GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(WebService.class);
    }
}