package com.diffey.view.rxzhihu.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.ZhihuApplication;
import com.diffey.view.rxzhihu.injector.component.ActivityComponent;
import com.diffey.view.rxzhihu.injector.component.ApplicationComponent;
import com.diffey.view.rxzhihu.injector.module.ActivityModule;

/**
 *
 */
public abstract class BaseNewActivity extends AppCompatActivity {

    protected ActivityComponent mActivityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // inject(BaseNewActivity baseActivity)
        getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initInjector();
        initData();
        initViews();
        setListeners();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ZhihuApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public abstract int getContentView();

    public abstract void initInjector();

    public abstract void initData();

    public abstract void initViews();

    public abstract void setListeners();

    public void initToolBar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            // 设置Toolbar的返回按钮图片
            toolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
        }
    }

    /**
     * 设置Toolbar返回事件，结束Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
