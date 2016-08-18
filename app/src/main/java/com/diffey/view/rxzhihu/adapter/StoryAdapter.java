package com.diffey.view.rxzhihu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.diffey.view.rxzhihu.bean.StoriesEntity;
import com.diffey.view.rxzhihu.ui.fragment.StoryFragment;

import java.util.List;

/**
 *
 */
public class StoryAdapter extends FragmentPagerAdapter {
    private int mCurPosition;
    private List<StoriesEntity> mStoriesEntityList;

    public StoryAdapter(FragmentManager fm, int position, List<StoriesEntity> storiesEntityList) {
        super(fm);
        this.mCurPosition = position;
        this.mStoriesEntityList = storiesEntityList;
    }

    @Override
    public Fragment getItem(int position) {
        StoryFragment storyFragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(StoryFragment.PARAM_ID, mStoriesEntityList.get(position).getId());
        storyFragment.setArguments(bundle);
        return storyFragment;
    }

    @Override
    public int getCount() {
        return mStoriesEntityList == null ? 0 : mStoriesEntityList.size();
    }
}
