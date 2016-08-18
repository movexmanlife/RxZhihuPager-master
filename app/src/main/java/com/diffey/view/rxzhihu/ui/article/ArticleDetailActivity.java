package com.diffey.view.rxzhihu.ui.article;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.adapter.StoryAdapter;
import com.diffey.view.rxzhihu.base.BaseNewActivity;
import com.diffey.view.rxzhihu.bean.StoriesEntity;
import com.diffey.view.rxzhihu.injector.HasComponent;
import com.diffey.view.rxzhihu.ui.setting.DaggerSettingComponent;
import com.diffey.view.rxzhihu.ui.setting.SettingComponent;
import com.diffey.view.rxzhihu.ui.setting.SettingModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends BaseNewActivity implements HasComponent<ArticleDetailComponent> {
    public static final String KEY_PARAM_POS = "key_param_pos";
    public static final String KEY_PARAM_DATA_LIST = "key_param_data_list";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.article_detail_viewpager)
    ViewPager mArticleDetailViewpager;

    private int mCurPosition = -1;
    private List<StoriesEntity> mStoriesEntityList;
    private ArticleDetailComponent mArticleDetailComponent;

    public static void start(Context context, int pos, ArrayList<StoriesEntity> storiesEntityList) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.KEY_PARAM_POS, pos);
        intent.putParcelableArrayListExtra(ArticleDetailActivity.KEY_PARAM_DATA_LIST, storiesEntityList);
        context.startActivity(intent);
    }
    
    @Override
    public int getContentView() {
        return R.layout.activity_article_detail;
    }

    @Override
    public void initInjector() {
        mArticleDetailComponent = DaggerArticleDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .articleDetailModule(new ArticleDetailModule())
                .build();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mCurPosition = intent.getIntExtra(KEY_PARAM_POS, -1);
            mStoriesEntityList = intent.getParcelableArrayListExtra(KEY_PARAM_DATA_LIST);
        }
    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
        initToolBar(mToolbar);
        setTitle(getString(R.string.article_detail));
        mArticleDetailViewpager.setAdapter(new StoryAdapter(getSupportFragmentManager(), 0, mStoriesEntityList));
        mArticleDetailViewpager.setCurrentItem(mCurPosition);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public ArticleDetailComponent getComponent() {
        return mArticleDetailComponent;
    }
}
