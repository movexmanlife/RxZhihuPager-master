package com.diffey.view.rxzhihu.api;

import android.content.Context;

import com.diffey.view.rxzhihu.bean.TREntity;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 *
 */
public class TRApi {

    public static final String BASE_URL = "http://www.tuling123.com/openapi/";
    private TRService mTRService;
    private Context mContext;

    public TRApi(OkHttpClient okHttpClient, Context context) {
        this.mContext = context;
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mTRService = retrofit.create(TRService.class);
    }

    /**
     * 可以看到这里应该是post传递的参数。
     * @param key
     * @param info
     * @param userid
     * @return
     */
    public Observable<TREntity> getTRResponse(String key, String info, String userid) {
        return mTRService.getTRResponse(key, info, userid).subscribeOn(Schedulers.io());
    }
}
