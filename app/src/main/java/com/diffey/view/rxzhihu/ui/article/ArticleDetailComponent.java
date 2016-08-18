package com.diffey.view.rxzhihu.ui.article;

import com.diffey.view.rxzhihu.injector.PerActivity;
import com.diffey.view.rxzhihu.injector.component.ApplicationComponent;
import com.diffey.view.rxzhihu.injector.module.ActivityModule;

import dagger.Component;

/**
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, ArticleDetailModule.class
})
public interface ArticleDetailComponent {
    void inject(ArticleDetailFragment articleDetailFragment);
}
