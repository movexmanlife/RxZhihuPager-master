package com.diffey.view.rxzhihu.ui.article;

import com.diffey.view.rxzhihu.ui.BasePresenter;
import com.diffey.view.rxzhihu.ui.BaseView;

/**
 *
 */
public interface ArticleDetailContract {
    interface View extends BaseView {
        void showContent(String htmlContent);
        void showFailureView();
    }
    interface Presenter extends BasePresenter<View> {
        void getContentDetail(int id);
    }
}
