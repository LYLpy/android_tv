package com.geling.view.gelingtv_XX_tongbu;

import android.app.Application;
import android.content.Context;

import com.geling.view.gelingtv_XX_tongbu.model.bean.OhterModel1;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.BaseActivity;
import com.geling.view.gelingtv_XX_tongbu.utils.SPUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/19------更新------
 */

public class MainApp extends Application{
    public static String imgUrl = "http://139.9.7.208:8989//Public//Uploads//images//2019-11-26//5ddcd3d906771.jpg";
    public List<BaseActivity> mActivityList;
    private static MainApp mainApp;
    private OhterModel1 mOhterModel;
    public static Context mAppContext ;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)//设置写入超时时间
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        mActivityList = new ArrayList<>();
        mainApp = this;
        initData();
    }

    private void initData() {
        SPUtil.init(mainApp);
    }

    /**获取上下文*/
    public static Context getContext()
    {
        return mainApp;
    }

    public static MainApp getInstance()
    {
        return mainApp;
    }

    public OhterModel1 getOhterModel() {
        return mOhterModel;
    }

    public void setOhterModel(OhterModel1 ohterModel) {
        mOhterModel = ohterModel;
    }
}
