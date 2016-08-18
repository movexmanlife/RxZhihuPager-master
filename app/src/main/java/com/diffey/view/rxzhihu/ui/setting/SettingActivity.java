package com.diffey.view.rxzhihu.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.base.BaseNewActivity;
import com.diffey.view.rxzhihu.injector.HasComponent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class SettingActivity extends BaseNewActivity implements HasComponent<SettingComponent>{
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private SettingComponent mSettingComponent;

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentView() {
        return R.layout.base_content_toolbar_layout;
    }

    @Override
    public void initInjector() {
        mSettingComponent = DaggerSettingComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .settingModule(new SettingModule())
                .build();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
        initToolBar(mToolbar);
        setTitle(getString(R.string.setting_title));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, SettingFragment.newInstance())
                .commit();
    }

    @Override
    public void setListeners() {

    }

    @Override
    public SettingComponent getComponent() {
        return mSettingComponent;
    }
}
