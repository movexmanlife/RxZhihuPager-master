package com.diffey.view.rxzhihu.injector.component;

import android.app.Service;

import com.diffey.view.rxzhihu.injector.PerService;
import com.diffey.view.rxzhihu.injector.module.ServiceModule;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/17.
 */

/**
 * TODO!!!   Error:(18, 12) 错误: com.diffey.view.rxzhihu.injector.component.ActivityComponent scoped with @Singleton may not reference bindings with different scopes:
 * @Provides @com.diffey.view.rxzhihu.injector.PerActivity android.app.Activity com.diffey.view.rxzhihu.injector.module.ActivityModule.provideActivity()
 *
 * @Singleton @Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class})
    public interface ServiceComponent {
    Service getServiceContext();
    }
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class})
public interface ServiceComponent {
    Service getServiceContext();
}
