package com.diffey.view.rxzhihu.ui.setting;

import android.content.Context;
import android.support.annotation.NonNull;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.injector.PerActivity;
import com.diffey.view.rxzhihu.ui.BasePresenter;
import com.diffey.view.rxzhihu.ui.BaseView;
import com.diffey.view.rxzhihu.util.AppInfoUtils;

import javax.inject.Inject;

import rx.Subscription;

/**
 *
 */
@PerActivity
public class SettingPresenter implements SettingContract.Presenter {

    private SettingContract.View mSettingView;
    private Subscription mSubscription;
    private Context mContext;

    @Inject
    public SettingPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(@NonNull SettingContract.View view) {
        mSettingView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mSettingView = null;
    }

    @Override
    public void setVersion() {
        String version = mContext.getString(R.string.setting_version_format, AppInfoUtils.getVersionName(mContext));
        mSettingView.showVersion(version);
    }

    @Override
    public void onDeveloperInfoClick() {
        mSettingView.showDeveloperInfo();
    }
}
