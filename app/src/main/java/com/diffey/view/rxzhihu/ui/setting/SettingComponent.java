package com.diffey.view.rxzhihu.ui.setting;

import com.diffey.view.rxzhihu.injector.PerActivity;
import com.diffey.view.rxzhihu.injector.component.ApplicationComponent;
import com.diffey.view.rxzhihu.injector.module.ActivityModule;
import com.diffey.view.rxzhihu.ui.BasePresenter;
import com.diffey.view.rxzhihu.ui.BaseView;

import dagger.Component;

/**
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, SettingModule.class
})
public interface SettingComponent {
    void inject(SettingFragment settingFragment);
}
