package com.diffey.view.rxzhihu.injector.module;

import android.app.Activity;
import android.app.Service;

import com.diffey.view.rxzhihu.injector.PerActivity;
import com.diffey.view.rxzhihu.injector.PerService;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class ServiceModule {
    private final Service mService;

    public ServiceModule(Service service) {
        this.mService = service;
    }

    @Provides @PerService
    public Service provideService() {
        return mService;
    }

}
