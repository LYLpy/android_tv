package com.geling.view.gelingtv_XX_tongbu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SP简单数据保存工具类
 */
public class SPUtil {

    private static Context mContext;
    /**
     * @param context
     */
    public static void init(Context context) {
        if (null == context) {
            throw new IllegalArgumentException("mContext cannot be null.");
        }
        mContext = context.getApplicationContext();
    }
    // 参数存放的文件名 .xml
    private static final String CONFIG_FILE_NAME = "config";

    public static String getString(String key, String defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_FILE_NAME, //
                Context.MODE_PRIVATE);// 文件模式
        return sp.getString(key, defValue);
    }

    public static void saveString(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_FILE_NAME, Context
				.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void saveBoolean(String key, Boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(//
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_FILE_NAME, Context
				.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void saveInt(String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static Long getLong(String key, Long defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void saveLong(String key, Long value) {
        SharedPreferences sp = mContext.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void clear() {
        SharedPreferences sp = mContext.getSharedPreferences(//
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
