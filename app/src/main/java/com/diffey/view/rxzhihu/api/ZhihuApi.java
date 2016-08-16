package com.diffey.view.rxzhihu.api;

import com.diffey.view.rxzhihu.bean.NewsEntity;
import com.diffey.view.rxzhihu.bean.StoryDetailsEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by diff on 2016/2/16.
 */

/**
 * （1）可以看到使用的注解非常多，参数，地址，网络请求的方式等等，都可以通过注解来实现。
 * （2）这里还可以看到有返回值，这代表着这个是同步的方法。
 * （3）还有这里返回值，需要好好注意一下，是Observable，这个类。
 */
public interface ZhihuApi {

    @GET("api/4/news/latest")
    Observable<NewsEntity> getLastestNews();

    /**
     * 这里的Path的含义应该是将此值放进去
     * @param id
     * @return
     */
    @GET("api/4/news/before/{id}")
    Observable<NewsEntity> getBeforeNews(@Path("id") String id);

    @GET("api/4/news/{id}")
    Observable<StoryDetailsEntity> getNewsDetails(@Path("id") int id);
}
