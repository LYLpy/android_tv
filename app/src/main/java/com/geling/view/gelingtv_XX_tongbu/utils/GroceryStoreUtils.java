package com.geling.view.gelingtv_XX_tongbu.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.geling.view.gelingtv_XX_tongbu.MainApp;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 大杂烩
 * Created by zlw on 2018/11/10.
 */

public class GroceryStoreUtils {



    // 秒数转换成时分秒 xx:xx:xx
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        try {
            if (i >= 0 && i < 10)
                retStr = "0" + Integer.toString(i);
            else
                retStr = "" + i;
        }catch (Exception e){

        }

        return retStr;
    }
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    //获取时间  日期等
    public   static String  getSimpleDateFprmat(String yyyymmdd){
        String Date = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyymmdd);//yyyy:MM:dd HH:mm:ss
        java.util.Date date = new Date(System.currentTimeMillis());
        return Date =simpleDateFormat.format(date);
    }

        //获取星期  yyyy-MM-dd
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek=c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return Week;
    }

    //Glide为layout添加背景色
    public static void GlideBG(Activity activity,String iamg,ImageView view){
        Glide.with(activity)
                .load(iamg)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }
    //Glide为imag添加图片
    public static void GlideImag(Context context,String iamg,ImageView view){
        Glide.with(context)
                .load(iamg)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }

    //Glide为imag添加图片,有占位图
    public static void GlideImagHasPlaceHolder(Context context,String iamg,ImageView view,int drawableId){
        Glide.with(context)
                .load(iamg)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(drawableId)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }

    //Glide为imag添加背景
    public static void GlideImagToBg(Context context, String iamg, final ImageView view){
        Glide.with(context)
                .load(iamg)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(resource);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(drawable);    //设置背景
                        }
                    }
                });
//                .format(DecodeFormat.PREFER_ARGB_8888)
//                .dontAnimate()
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(view);
    }
    //Glide为imag添加图片,有占位图
    public static void GlideImag(Context context,String iamg,ImageView view,int placeholder){
        Glide.with(context)
                .load(iamg)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }
    //Glide加载资源图片
    public static void GlideGif(Context context, int iamg, ImageView view){
        Glide.with(context)
                .load(iamg)
                .asGif()
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }
    //Glide加载资源图片
    public static void GlideDraw(Context context, int iamg, ImageView view){
        Glide.with(context)
                .load(iamg)
                .asBitmap()
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }
    //UTF-8
    public static String toUtf8(String str) {
        String result = null;
        try {
            result = new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    //截取之后的字符
    public   static String  getIntercept(String s,String name){
        String Date = "";
        String userIdJiequ = "";
        if (s.indexOf("id=")!= -1){
            String userId = s;
            userIdJiequ = userId.substring(userId.indexOf(name));
        }
        return Date =userIdJiequ;
    }
    //删除字符
    public   static String  getDeleText(String s,String name){
        String Date = "";
        String newStr = "";
        if (s.indexOf("id=")!= -1){
            newStr = s.replace(name,""); //得到新的字符串
        }
        return Date =newStr;
    }

//    //首页字符串截取
//    public static String TextIntercept (String text){
//        String name = "";
//        String userId =text;
//        String userIdJiequ = userId.substring(userId.indexOf("a="));
//        String str =userIdJiequ;
//        String a = str.substring(0, str.indexOf("&"));
//        return name = a;
//    }

    //首页字符串截取
    public static String TextIntercept (String text){
        String name = "";
        String a = "";
        String userId =text;
        String userIdJiequ = userId.substring(userId.indexOf("a="));
        String str =userIdJiequ;
        if (str.indexOf("&") != -1){
            a = str.substring(0, str.indexOf("&"));
            //有
        }else {
            //没有
            a=str;
        }
        return name = a;
    }

//    用来判断字符里有没有当前内容
    public static boolean setTextIndexOf(String str,String text){
        if (str.indexOf(text) != -1){
            return  true;
        }else {
            return  false;
        }
    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    public static Integer toPageInt(int Count,int page){
        int p=0;
        int totalPageNum = (Count  +  page  - 1) / page;
        return p = totalPageNum;
    }

    //判断是否启动服务
    public static boolean isWorked(String className,Context context) {
        ActivityManager myManager = (ActivityManager) context
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(className)) {
                return true;
            }
        }
        return false;
    }

    //dp转px
    public static int dip2px( float dpValue) {
        final float scale = MainApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //px转dp
    public static int px2dip(int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, MainApp.getContext().getResources().getDisplayMetrics()));
    }
}
