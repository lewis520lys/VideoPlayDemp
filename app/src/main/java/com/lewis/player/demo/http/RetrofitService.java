package com.lewis.player.demo.http;


import com.lewis.player.demo.entity.TypeModel;
import com.lewis.player.demo.entity.VideoModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface RetrofitService {
    @GET("getVideoList")
    Observable<VideoModel> getVideoListData(@Query("type") String type);
    //type

    @GET("getTypes")
    Observable<TypeModel> getTypeListData();

}
