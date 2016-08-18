package com.diffey.view.rxzhihu.ui.article;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class ArticleDetailModule {
    private int id;
    public ArticleDetailModule(int id){
        this.id = id;
    }

    @Provides
    int provideArticleDetailId() {
        return this.id;
    }
}
