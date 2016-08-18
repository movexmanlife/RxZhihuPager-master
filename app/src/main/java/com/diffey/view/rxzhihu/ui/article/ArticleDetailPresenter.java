package com.diffey.view.rxzhihu.ui.article;

import android.content.Context;
import android.support.annotation.NonNull;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.api.ZhihuApi;
import com.diffey.view.rxzhihu.bean.StoryDetailsEntity;
import com.diffey.view.rxzhihu.injector.PerActivity;
import com.diffey.view.rxzhihu.util.AppInfoUtils;
import com.diffey.view.rxzhihu.util.HtmlUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 */
@PerActivity
public class ArticleDetailPresenter implements ArticleDetailContract.Presenter {

    private ArticleDetailContract.View mArticleDetailView;
    private Subscription mSubscription;
    private Context mContext;
    private ZhihuApi mZhihuApi;
    private int mArticleDetailId;

    /**
     *
     * @param context
     * @param id 在ArticleDetailModule中提供了
     */
    @Inject
    public ArticleDetailPresenter(Context context, ZhihuApi zhihuApi, int id) {
        this.mContext = context;
        this.mZhihuApi = zhihuApi;
        this.mArticleDetailId = id;
    }

    /**
     * Activity中的Fragment，这里不要在ArticleDetailPresenter的构造函数中调用getContentDetail()函数。
     * 应该在attachView()函数中调用getContentDetail()函数，attachView()在Fragment中的onViewCreated()方法中调用的。
     */
    @Override
    public void attachView(@NonNull ArticleDetailContract.View view) {
        mArticleDetailView = view;
        getContentDetail(mArticleDetailId);
    }

    @Override
    public void getContentDetail(int id) {
        mSubscription = mZhihuApi.getNewsDetails(mArticleDetailId).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Func1<StoryDetailsEntity, String>() {
                    @Override
                    public String call(StoryDetailsEntity storyDetailsEntity) {
                        return HtmlUtils.structHtml(storyDetailsEntity.getBody(), storyDetailsEntity.getCss());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String htmlContent) {
                        mArticleDetailView.showContent(htmlContent);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mArticleDetailView.showFailure();
                    }
                });
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mArticleDetailView = null;
    }
}
