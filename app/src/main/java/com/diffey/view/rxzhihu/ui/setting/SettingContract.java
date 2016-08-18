package com.diffey.view.rxzhihu.ui.setting;

import com.diffey.view.rxzhihu.ui.BasePresenter;
import com.diffey.view.rxzhihu.ui.BaseView;

/**
 *
 */
public interface SettingContract {
    interface View extends BaseView {
        void showDeveloperInfo();
        void showVersion(String version);
    }
    interface Presenter extends BasePresenter<View> {
        void setVersion();
        void onDeveloperInfoClick();
    }
}
