package com.diffey.view.rxzhihu.injector.module;

import android.app.Activity;

import com.diffey.view.rxzhihu.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

}
