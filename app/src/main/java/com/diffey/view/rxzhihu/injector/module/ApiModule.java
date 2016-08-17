package com.diffey.view.rxzhihu.injector.module;

import android.content.Context;

import com.diffey.view.rxzhihu.api.TRApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 *
 */
@Module
public class ApiModule {
    @Provides @Singleton
    public TRApi provideTRApi(OkHttpClient okHttpClient, Context context) {
        return new TRApi(okHttpClient, context);
    }

//    @Provides @Singleton
//    public ZhihuAPi provideZhihuApi(OkHttpClient okHttpClient, Context context) {
//        return new ZhihuAPi(okHttpClient, context);
//    }
}
