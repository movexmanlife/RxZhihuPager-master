package com.diffey.view.rxzhihu;

import android.app.Application;

import com.diffey.view.rxzhihu.injector.component.ApplicationComponent;
import com.diffey.view.rxzhihu.injector.component.DaggerActivityComponent;
import com.diffey.view.rxzhihu.injector.component.DaggerApplicationComponent;
import com.diffey.view.rxzhihu.injector.module.ApplicationModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


/**
 *
 */
public class ZhihuApplication extends Application {
    private static final String TAG = "difflog";
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG);
        initComponent();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        LeakCanary.install(this);
        Fresco.initialize(this);
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        // inject()定义在ApplicationComponent接口里面
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
