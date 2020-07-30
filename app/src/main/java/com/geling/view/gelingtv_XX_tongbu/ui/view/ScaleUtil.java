package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/30------更新------
 * 放大缩小工具类
 */

public class ScaleUtil {

    public static void scale(View view,float scale){
        ViewCompat.animate(view)
                .setDuration(200)
                .scaleX(scale)
                .scaleY(scale)
                .start();
    }
    public static void scale(View view,float scaleX,float scaleY){
        ViewCompat.animate(view)
                .setDuration(200)
                .scaleX(scaleX)
                .scaleY(scaleY)
                .start();
    }
}
