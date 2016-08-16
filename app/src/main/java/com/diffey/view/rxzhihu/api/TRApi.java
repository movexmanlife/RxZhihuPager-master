package com.diffey.view.rxzhihu.api;

import com.diffey.view.rxzhihu.bean.TREntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by diff on 2016/2/16.
 */
public interface TRApi {

    /**
     * 可以看到这里应该是post传递的参数。
     * @param key
     * @param info
     * @param userid
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<TREntity> getTRResponse(@Field("key") String key, @Field("info") String info, @Field("userid") String userid);
}
