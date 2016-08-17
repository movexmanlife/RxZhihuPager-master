package com.diffey.view.rxzhihu.ui;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(@NonNull T view);

    void detachView();
}
