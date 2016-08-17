package com.diffey.view.rxzhihu.injector.component;

import android.content.Context;

import com.diffey.view.rxzhihu.api.TRApi;
import com.diffey.view.rxzhihu.api.ZhihuApi;
import com.diffey.view.rxzhihu.injector.module.ApiModule;
import com.diffey.view.rxzhihu.injector.module.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/17.
 */
@Singleton @Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    Context getContext();
    Bus getBus();
    TRApi getTRApi();
//    ZhihuApi getZhihuApi();
}
