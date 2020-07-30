package com.geling.view.gelingtv_XX_tongbu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/17------更新------
 * 视频播放相关工具
 */

public class VideoUtils {
    @SuppressLint("WrongConstant")
    public static long getUidRxBytes(Context context) { //获取总的接受字节数，包含Mobile和WiFi等
        PackageManager pm = context.getPackageManager();
        ApplicationInfo ai = null;
        try {
//            ai = pm.getApplicationInfo("com.nayun.framework", PackageManager.GET_ACTIVITIES);
            ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return TrafficStats.getUidRxBytes(ai.uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);
    }
}
