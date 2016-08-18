package com.diffey.view.rxzhihu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diffey.view.rxzhihu.injector.HasComponent;

public abstract class BaseNewFragemnt extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInjector();
        initData();
        initViews(view);
        setListeners();
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract int getContentView();

    public abstract void initInjector();

    public abstract void initData();

    public abstract void initViews(View view);

    public abstract void setListeners();

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> clazz) {
        if (getActivity() instanceof HasComponent<?>) {
            C component = ((HasComponent<C>) getActivity()).getComponent();
            return clazz.cast(component);
        }

        return null;
    }
}
