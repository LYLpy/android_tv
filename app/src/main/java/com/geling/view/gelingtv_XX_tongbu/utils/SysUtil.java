package com.geling.view.gelingtv_XX_tongbu.utils;

import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/5------更新------
 */

public class SysUtil {

    public static final String SYSKEY_JX_BOX_NO = "persist.sys.hwconfig.stb_id";
    public static final String SYSKEY_JX_EPG = "persist.sys.hwvod.epgurl";
    public static final String SYSKEY_JX_EDG_COOKIE = "persist.sys.hwvod.cookie";
    public static final String SYSKEY_JX_GROUP_NO = "persist.sys.hwvod.sgid";
    public static final String SYSKEY_JX_HARDWAVE_NO = "persist.sys.hwconfig.hw_ver";
    public static final String SYSKEY_JX_IP = "dhcp.wlan0.ipaddress";
    public static final String SYSKEY_JX_MAC = "persist.sys.net.mac";
    public static final String SYSKEY_JX_MODEL_NO = "ro.product.model";
    public static final String SYSKEY_JX_SMART_CARD = "persist.sys.cardSN";
    public static final String SYSKEY_JX_SOFTWAVE_NO = "persist.sys.hwconfig.soft_ver";

    public static String getSystemProperties(Context paramContext, String paramString) {
//        try {
//            paramContext = paramContext.getClassLoader().loadClass("android.os.SystemProperties");
//            paramContext = (String)paramContext.getMethod("get", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString });
//            return paramContext;
//        }
//        catch (Exception paramContext) {
//
//        }
        String value = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
//            value = (String)(get.invoke(c, key, defaultValue));
            value = (String)(get.invoke(c, paramString, ""));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("__getRtsp__getSystemProperties",e.getMessage() + "__");
        }finally {
            return value;
        }
    }
    /**
     * 获取安卓版本
     * @return
     */
    public static String getOsVersion(){
        String version = android.os.Build.VERSION.RELEASE;
        return version;
    }

    public static DisplayMetrics getDisplayMetrics(Context context){
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Service.WINDOW_SERVICE);
        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics;
    }

    /**
     * 获取当前app version code
     */
    public static int getVersionCode(Context context){
        int versioncode = 0;
        String versionname;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = packageInfo.versionCode;
            versionname = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versioncode;
    }
    /**
     * 获取当前app version code
     */
    public static String getVersionName(Context context){
        int versioncode;
        String versionname = "";
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = packageInfo.versionCode;
            versionname = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionname;
    }
}
