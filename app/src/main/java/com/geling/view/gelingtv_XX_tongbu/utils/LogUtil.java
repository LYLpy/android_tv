package com.geling.view.gelingtv_XX_tongbu.utils;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.geling.view.gelingtv_XX_tongbu.ui.activity.BaseActivity;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/17------更新------
 * 日志打印util
 */

public class LogUtil {


        private static String TAG = "__Iptv3.0";
        private static String DIVIDE = "_";
        // 开关
        private static boolean debug = false;

        private LogUtil() {

        }
        public static void setTAG(String TAG) {
            LogUtil.TAG = TAG;
        }

        public static void setDebug(boolean debug) {
            LogUtil.debug = debug;
        }

        public static void v(String msg) {
            if (debug)
                Log.v(TAG, String.valueOf(msg));
        }

        public static void d(String msg) {
            if (debug)
                Log.d(TAG, String.valueOf(msg));
        }

        public static void i(String msg) {
            if (debug)
                Log.i(TAG, String.valueOf(msg));
        }

        public static void w(String msg) {
            if (debug)
                Log.w(TAG, String.valueOf(msg));
        }

        public static void e(String msg) {
            if (debug)
                Log.e(TAG, String.valueOf(msg));
        }

    //自己传入tag
    public static void v(String tag, String msg) {
        if (debug)
            Log.v(String.valueOf(tag), String.valueOf(msg));
    }

    public static void d(String tag, String msg) {
        if (debug)
            Log.d(String.valueOf(tag), String.valueOf(msg));
    }

    public static void d(String tag1,String tag2, String msg) {
        if (debug)
            Log.d(String.valueOf(tag1 + DIVIDE + tag2), String.valueOf(msg));
    }

    public static void d(String tag1,String tag2,String tag3, String msg) {
        if (debug)
            Log.d(String.valueOf(tag1 + DIVIDE + tag2 + DIVIDE + tag3), String.valueOf(msg));
    }

    public static void d(String tag1,String tag2,String tag3,String tag4, String msg) {
        if (debug)
            Log.d(String.valueOf(tag1 + DIVIDE + tag2 + DIVIDE + tag3 + DIVIDE + tag4), String.valueOf(msg));
    }

    public static void i(String tag, String msg) {
        if (debug)
            Log.i(String.valueOf(tag), String.valueOf(msg));
    }

    public static void w(String tag, String msg) {
        if (debug)
            Log.w(String.valueOf(tag), String.valueOf(msg));
    }

    public static void e(String tag, String msg) {
        if (debug)
            Log.e(String.valueOf(tag), String.valueOf(msg));
    }

    public static void e(String tag1,String tag2, String msg) {
        if (debug)
            Log.e(String.valueOf(tag1 + DIVIDE + tag2), String.valueOf(msg));
    }

    public static void e(String tag1,String tag2,String tag3, String msg) {
        if (debug)
            Log.e(String.valueOf(tag1 + DIVIDE + tag2 + DIVIDE + tag3), String.valueOf(msg));
    }

    public static void e(String tag1,String tag2,String tag3,String tag4, String msg) {
        if (debug)
            Log.e(String.valueOf(tag1 + DIVIDE + tag2 + DIVIDE + tag3 + DIVIDE + tag4), String.valueOf(msg));
    }

    /**
     *初始化app是正式还是测试状态
     * @param application
     */
    public static void init(Activity application){
        debug = GetAppMessUtils.isApkInDebug(application);
    }

}
