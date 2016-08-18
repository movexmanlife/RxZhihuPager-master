package com.diffey.view.rxzhihu.ui.setting;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.base.BaseNewFragemnt;
import com.diffey.view.rxzhihu.contant.UrlContant;
import com.diffey.view.rxzhihu.util.AppInfoUtils;
import com.diffey.view.rxzhihu.util.IntentUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class SettingFragment extends BaseNewFragemnt implements SettingContract.View {

    @Bind(R.id.tv_developer)
    TextView mTvDeveloper;
    @Bind(R.id.tv_version)
    TextView mTvVersion;

    @Inject
    SettingPresenter mPresenter;
    @Inject
    Activity mActivity;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initInjector() {
        // SettingActivity中SettingComponent进行build，在SettingFragment中进行inject()。
        getComponent(SettingComponent.class).inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews(View view) {
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        // 设置版本号
        mPresenter.setVersion();
    }

    @Override
    public void setListeners() {

    }

    @OnClick(R.id.tv_developer)
    public void showDeveloperInfoDialog() {
        mPresenter.onDeveloperInfoClick();
    }

    @Override
    public void showDeveloperInfo() {
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title(getString(R.string.app_name))
                .backgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .contentColor(Color.WHITE)
                .positiveColor(Color.WHITE)
                .negativeColor(Color.WHITE)
                .neutralColor(Color.WHITE)
                .titleColor(Color.WHITE)
                .content(R.string.setting_developer_info)
                .positiveText(R.string.github)
                .negativeText(R.string.CSDN)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        IntentUtils.toBrowserView(getActivity(), UrlContant.URL_DEV_GITHUB);
                        dialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        IntentUtils.toBrowserView(getActivity(), UrlContant.URL_DEV_CSDN);
                    }
                })
                .build();
        dialog.show();
    }

    @Override
    public void showVersion(String version) {
        mTvVersion.setText(version);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mPresenter.detachView();
    }
}
