package com.geling.view.gelingtv_XX_tongbu;

import android.content.Context;
import android.content.SharedPreferences;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.UserInfoModel;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis.IntentsUtlis;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.SPUtil;

import static android.content.Context.MODE_PRIVATE;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/2------更新------
 * 用户管理
 */

public class UserManager {

    ///////////////////////////////////////////////////

    private static UserInfoModel mUserInfo;//用户信息的bean,其中isVip:0非会员，1会员

    /**
     * 获取用户ID
     * @param userId
     */
    public static void setUserId(String userId){
        SPUtil.saveString("userId",userId);
    }
    public static String getUserId(){
//        return SPUtil.getString("userId","");
        return "aaa";
    }
    public static boolean  mIsVip = false;

    /**
     * 是否vip
     * @return
     */
    public static boolean isVip(){
        return mIsVip;
    }

    public static String vipDate = "2019-12-19 15:59:00";
    /**
     * vip到期时间
     * @return
     */
    public static long getVipTimeStamp(){
        return CommonUtils.stringToDate(vipDate,"yyyy-MM-dd HH:mm:ss").getTime();
    }

    public static UserInfoModel getUserInfo() {
        return mUserInfo;
    }

    public static void setUserInfo(UserInfoModel userInfoModel) {
        mUserInfo = userInfoModel;
    }


    /**
     * @return integral 用户积分
     */
    public static int getIntegral(){
        return SPUtil.getInt("integral",0);
    }
    public static void setIntegral(int integral){
        SPUtil.saveInt("integral",integral);
    }
    /**
     * @return userid 用户id
     */
    public static int getUserid(){
        return SPUtil.getInt("user_id ",123213);
    }
    public static void setUserid(int Userid){
        SPUtil.saveInt("user_id ",Userid);
    }
    /**
     * @return userid 用户name
     */
    public static String getUsername(){
        return SPUtil.getString("username","sdsdafasd");
    }
    public static void setUsername(String Username){
        SPUtil.saveString("username ",Username);
    }
    /**
     * @return userid 用户phone
     */
    public static String getUserphone(){
        return SPUtil.getString("phone ","");
    }
    public static void setUserphone(String Userphone){
        SPUtil.saveString("phone ",Userphone);
    }




    /**
     * 获取token
     * @return
     */
    public static String getToken() {
        LogUtil.e("__getToken",SPUtil.getString("token","") + "__");
//        return "573cae356272a6948ab2e79a24db8a13";
//        return SPUtil.getString("token","9abe8874cff56e55beda6d24cdceb000");//非VIP的token
//        return SPUtil.getString("token","573cae356272a6948ab2e79a24db8a13");//vip的token
        return SPUtil.getString("token","");//vip的token


    }

    public static void setToken(String token) {
        LogUtil.e("__setToken",String.valueOf(token)+ "__");
        SPUtil.saveString("token", token);
    }

    //判断 token是否为空
    public static boolean isSignTokenNULL(){
        //true = 不为空   fales = 空
        boolean tokenNullBoolean =true;
        if ( (UserManager.getToken() == null||UserManager.getToken().equals(""))){
            tokenNullBoolean =false;
        }
        return tokenNullBoolean;
    }

    //是否需要登录
    public static void isSignTokenIntentActivity(Context context,int tapy){
        LogUtil.e("_________ss",isSignTokenNULL()+"拿到"+tapy);
    if (isSignTokenNULL()==false){
        IntentsUtlis.starSignInActivity(context,tapy);
    }
}

    public static void setVip(boolean isVip) {
        mIsVip = isVip;
    }

}