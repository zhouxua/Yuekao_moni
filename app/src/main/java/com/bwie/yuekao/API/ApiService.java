package com.bwie.yuekao.API;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 周旋
 * 2017/11/23  08:54
 */

public interface ApiService {
    //网络请求
    @FormUrlEncoded
    @POST("columns/getVideoList.do")//分类
            Observable<Kbean> postpage(@Field("catalogId") String catalogId , @Field("pnum") String pnum);

}
