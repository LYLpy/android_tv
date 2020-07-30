package com.geling.view.gelingtv_XX_tongbu.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * 获取应用的相关信息
 */
 public class GetAppMessUtils {
    //获取渠道
    public static  String getAppMetaData(Context context,String key){
        if (context==null|| TextUtils.isEmpty(key)){
            return null;
        }
        //返回的值
        String resultData= null;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager!= null){
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
                    if (applicationInfo!=null){
                        if (applicationInfo.metaData!= null){
                            //key要与manifest中的配置文件标识一致
                            resultData  =applicationInfo.metaData.getString(key);
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        return resultData;
    }


    /**判断当前应用是否是debug状态
     * @param context
     * @return
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
