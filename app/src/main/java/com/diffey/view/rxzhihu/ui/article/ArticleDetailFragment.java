package com.diffey.view.rxzhihu.ui.article;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.base.BaseNewFragemnt;
import com.diffey.view.rxzhihu.util.ViewUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class ArticleDetailFragment extends BaseNewFragemnt implements ArticleDetailContract.View {
    public static final String KEY_PARAM_ID = "key_param_id";

    @Bind(R.id.article_detail_webview)
    WebView mWebView;

    @Bind(R.id.common_error)
    RelativeLayout mCommonError;

    @Bind(R.id.common_loading)
    FrameLayout mCommonLoading;

    @Inject
    ArticleDetailPresenter mPresenter;
    @Inject
    Activity mActivity;

    private int mCurId = -1;

    public static ArticleDetailFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PARAM_ID, id);

        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_article_detail;
    }

    @Override
    public void initInjector() {
        // SettingActivity中SettingComponent进行build，在SettingFragment中进行inject()。
        getComponent(ArticleDetailComponent.class).inject(this);
    }

    @Override
    public void initData() {
        mCurId = getArguments().getInt(KEY_PARAM_ID, -1);
    }

    @Override
    public void initViews(View view) {
        ButterKnife.bind(this, view);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mPresenter.attachView(this);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void showContent(String htmlContent) {
        if (mWebView != null) {
            ViewUtils.setViewVisibility(mCommonLoading, false);
            ViewUtils.setViewVisibility(mCommonError, false);
            mWebView.loadData(htmlContent, "text/html; charset=UTF-8", null);
        }
    }

    @Override
    public void showFailure() {
        ViewUtils.setViewVisibility(mCommonError, true);
        ViewUtils.setViewVisibility(mCommonLoading, false);
        ViewUtils.setViewVisibility(mWebView, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mPresenter.detachView();
    }
}
