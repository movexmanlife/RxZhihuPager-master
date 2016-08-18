package com.diffey.view.rxzhihu.api;

import android.content.Context;

import com.diffey.view.rxzhihu.bean.NewsEntity;
import com.diffey.view.rxzhihu.bean.StoryDetailsEntity;
import com.diffey.view.rxzhihu.bean.TREntity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 *
 */
public class ZhihuApi {
    public static final String BASE_URL = "http://news-at.zhihu.com/";
    private ZhihuService mZhihuService;
    private Context mContext;

    public ZhihuApi(OkHttpClient okHttpClient, Context context) {
        this.mContext = context;
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mZhihuService = retrofit.create(ZhihuService.class);
    }

    /**
     * 这里的Path的含义应该是将此值放进去
     * @param id
     * @return
     */
    public Observable<NewsEntity> getBeforeNews(String id) {
        return mZhihuService.getBeforeNews(id).subscribeOn(Schedulers.io());
    }

    public Observable<StoryDetailsEntity> getNewsDetails(int id) {
        return mZhihuService.getNewsDetails(id).subscribeOn(Schedulers.io());
    }
}
