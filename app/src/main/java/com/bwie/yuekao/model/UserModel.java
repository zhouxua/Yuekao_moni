package com.bwie.yuekao.model;

import android.util.Log;

import com.bwie.yuekao.API.ApiService;
import com.bwie.yuekao.API.Kbean;
import com.bwie.yuekao.model.Imodel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 周旋
 * 2017/11/23  09:06
 */

public class UserModel implements Imodel {
    Kbean kbean;
    private OnFinish onFinish;

    public interface OnFinish{
        void OnFinishListener(Kbean kbean);
    }
    public void setOnFinish(OnFinish finish){
        this.onFinish=finish;
    }
    @Override
    public void getJson(String url,String pnum) {
        Log.d("main","qingqiudejieguo"+pnum);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);

        final Observable<Kbean> home = apiService.postpage("402834815584e463015584e539330016",pnum);
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Kbean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("main","运行结果：onCompleted");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("main","运行结果：onError");
                    }
                    @Override
                    public void onNext(Kbean kbean) {
                        Log.d("main","运行结果：onNext");
                        onFinish.OnFinishListener(kbean);
                    }
                });
    }
}
