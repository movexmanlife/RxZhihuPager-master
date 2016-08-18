package com.diffey.view.rxzhihu.injector.component;

import android.app.Activity;

import com.diffey.view.rxzhihu.injector.PerActivity;
import com.diffey.view.rxzhihu.injector.module.ActivityModule;

import dagger.Component;

/**
 * TODO!!!Error:(18, 1) 错误: com.diffey.view.rxzhihu.injector.component.ActivityComponent (unscoped) may not reference scoped bindings:
 * @Provides @com.diffey.view.rxzhihu.injector.PerActivity android.app.Activity com.diffey.view.rxzhihu.injector.module.ActivityModule.provideActivity()
 *
 * @Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
    public interface ActivityComponent {
    Activity getActivity();
    }
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();
}
