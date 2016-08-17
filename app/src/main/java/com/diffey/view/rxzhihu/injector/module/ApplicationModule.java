package com.diffey.view.rxzhihu.injector.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;

import com.diffey.view.rxzhihu.api.TRApi;
import com.squareup.otto.Bus;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 */
@Module
public class ApplicationModule {
    private final Context mContext;
    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides @Singleton
    public Context provideApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    @Provides @Singleton
    public Bus provideBusEvent() {
        return new Bus();
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        builder.addInterceptor(logging);
        builder.interceptors().clear();
        return builder.build();
    }
}
