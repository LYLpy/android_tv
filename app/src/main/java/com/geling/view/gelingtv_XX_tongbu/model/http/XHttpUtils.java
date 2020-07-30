package com.geling.view.gelingtv_XX_tongbu.model.http;

import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.IHttp;
import com.geling.view.gelingtv_XX_tongbu.utils.AES128;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;

public class XHttpUtils {
    //公共方法
    private static Callback stringCallback ( final HttpCallback httpCallback){
        Callback stringCallback = new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.d("post_XHTTPUTILX_OK"+response);
                try {
                    JSONObject AES128jsonObject = new JSONObject(response);
                    String contentSrc = AES128jsonObject.getString("content");
                    String json = AES128.Decrypt(contentSrc, CommonUtils.PY_KEY);
                    LogUtil.d("post_XHTTPUTILX_OK"+json);
                    JSONObject jsonObject = new JSONObject(json);
                    int status = jsonObject.getInt("status");
                    int error = jsonObject.getInt("error");

                    if (status==200&&error==10000){
                        httpCallback.onSuccess(json);
                    }else {
                        if (error==10003){
                            UserManager.setToken(null);
                            httpCallback.onSuccess(json);
                        }
                        LogUtil.e("ssssssssssss",response);
                        httpCallback.onFailed(json);
                        LogUtil.d("post_XHTTPUTILX_OK"+"请求错误"+"status"+String.valueOf(status)+"error"+String.valueOf(error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
       return stringCallback;
    }
    private static void mapSign1( int getORpostType,String url, Map<String, String> treeMap , HttpCallback httpCallback){
        Map<String, String> parameters = new TreeMap<>();
        parameters.putAll(treeMap);
        parameters.put("sign",CommonUtils.getSignPYString(treeMap));
        LogUtil.e("__http",parameters+"");
        LogUtil.e("__用户");
        //0 = get    1 = post
        if (getORpostType==0){
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(parameters)
                    .build()
                    .execute(stringCallback(httpCallback));
        }else {
            OkHttpUtils
                    .post()
                    .url(url)
                    .params(parameters)
                    .build()
                    .execute(stringCallback(httpCallback));
            LogUtil.e("__用户idpar",parameters+"");
        }
    }
    //公共方法
    private static void mapSign( int getORpostType,String userToken,String url, Map<String, String> treeMap , HttpCallback httpCallback){
        //获取  sign  已经全部参数     选择 get 或者post
        Map<String, String> parameters = new TreeMap<>();
        parameters.putAll(treeMap);
        parameters.put("sign",CommonUtils.getSignPYString(treeMap));
        if (userToken!=null&& !userToken.equals("")){
         parameters.put("token",userToken);
        }
        //0 = get    1 = post
        if (getORpostType==0){
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(parameters)
                    .build()
                    .execute(stringCallback(httpCallback));


        }else {
            OkHttpUtils
                    .post()
                    .url(url)
                    .params(parameters)
                    .build()
                    .execute(stringCallback(httpCallback));

        }

    }

    //一  获取验证码
    public static void getHttpSignVerificationCodeJson(String phone,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("phone", phone);
        mapSign(0,null, IHttp.LOGIN_VerificationCode,treeMap,httpCallback);
    }


    //一 验证码登录
    public static void postHttpSignDataJson(String verify_code,String verify_id,String invitation_code,String phone,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("verify_code", verify_code);
        treeMap.put("verify_id", verify_id);
        treeMap.put("invitation_code",invitation_code );
        treeMap.put("phone", phone);
        mapSign(1,null,IHttp.LOGIN_DATA,treeMap,httpCallback);
    }
    //获取个人信息
    public static void getHttpPersonalinformationJson(HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        mapSign(0, UserManager.getToken(),IHttp.PERSONAL_INFORMATION_DATA,treeMap,httpCallback);
    }
    //获取个人信息
    public static void postHttpUserOutJson(HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        mapSign(1, UserManager.getToken(),IHttp.PERSONAL_USEROUT_DATA,treeMap,httpCallback);
    }
    //我的订单
    public static void getHttpMyOrderJson(int status  ,int page,HttpCallback httpCallback){
        //订单状态0待付款，1已付款，2订单失效
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("limit","6");
        treeMap.put("page", String.valueOf(page));
        if (status!=99){
            treeMap.put("status", String.valueOf(status));
        }
        mapSign(0, UserManager.getToken(),IHttp.MY_ORDER_DATA,treeMap,httpCallback);
    }

    //我的收藏
    public static void getHttpMyCollectionJson(int subject  ,int page,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("page", String.valueOf(page));
        treeMap.put("per_page", "6");
        if (subject!=0){
            treeMap.put("subject", String.valueOf(subject));
        }
        mapSign(0, UserManager.getToken(),IHttp.MY_COLLECTION_DATAS,treeMap,httpCallback);
    }
    //我的收藏 -- title
    public static void getHttpMyCollectionTitleJson(HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        mapSign(0, UserManager.getToken(),IHttp.MY_COLLECTION_TITLE_DATAS,treeMap,httpCallback);
    }

    //我的收藏
    public static void getHttpEmpty(HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        mapSign(0, UserManager.getToken(),IHttp.MY_EMPTY_COLLECTION_DATAS,treeMap,httpCallback);
    }

    //我的优惠卷
    public static void getHttpMyCouponJson(int couponStatus  ,int page,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("page", String.valueOf(page));
        treeMap.put("per_page", "6");
        treeMap.put("couponStatus", String.valueOf(couponStatus));
        mapSign(0, UserManager.getToken(),IHttp.MY_COUPON_DATAS,treeMap,httpCallback);
    }
    //我的兑换码
    public static void getHttpExchangeJson(int page,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("page", String.valueOf(page));
        treeMap.put("per_page", "6");
        mapSign(0, UserManager.getToken(),IHttp.MY_EXCHANGE_DATAS,treeMap,httpCallback);
    }
    //我的兑换码---- 提交兑换码 兑换
    public static void postSubmitExchangeCodeJson(String code,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("code", code);
        mapSign(1, UserManager.getToken(),IHttp.MY_SUBMIT_EXCHANGE_CODE_DATAS,treeMap,httpCallback);
    }

    //启动页
    public static  void  getHttpSignSplash(int getORpostType, HttpCallback httpCallback){
        String  timestamp = String.valueOf(System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", timestamp);
        mapSign1(getORpostType,IHttp.START,treeMap,httpCallback);
    }
    //个人信息--更换手机号---获取修改验证码
    public static void postHttpGRModifyVerificationCodeJson(String phone,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("phone", phone);
        mapSign(1, UserManager.getToken(),IHttp.GR_MODIFY_VERIFICATION_Code,treeMap,httpCallback);
    }
    //个人信息--更换手机号---获取修改验证码--修改手机号
    public static void postHttpGRModifyPhoneJson(int type,String phone,String code,String verify_id,HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("phone", phone);
        treeMap.put("code", code);
        treeMap.put("verify_id", verify_id);
        treeMap.put("type", String.valueOf(type));
        mapSign(1, UserManager.getToken(),IHttp.GR_MODIFY_SUBMISSION_PHONE,treeMap,httpCallback);
    }
    //兑换码页面--兑换码二维码
    public static void getHttpCodeImagJson(HttpCallback httpCallback){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        mapSign(0, UserManager.getToken(),IHttp.MY_CODE_IMG,treeMap,httpCallback);
    }
    //播放记录
    public static void getHttpMyClassList(int getORpostType,TreeMap<String, String> treeMap , HttpCallback httpCallback) {
        mapSign(getORpostType,  UserManager.getToken(), IHttp.MY_ClASS_LIST,  treeMap, httpCallback);
    }
    //获取本地用户
    public  static void getUserMass(int getORpostType,int Userid,HttpCallback httpCallback ){
        String  timestamp = String.valueOf(System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", timestamp);
        treeMap.put("user_id",Userid+"");
        mapSign1(getORpostType,IHttp.BASE_URL,treeMap,httpCallback);
    }
    public  static void getFeedBackUrl(int getORpostType,HttpCallback httpCallback){
        String  timestamp = String.valueOf(System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", timestamp);
        mapSign(getORpostType,UserManager.getToken(),IHttp.FEED_BACK_URL,treeMap,httpCallback);
    }

    public  static void getProtocol(int getORpostType,String channel,HttpCallback httpCallback ){
        String  timestamp = String.valueOf(System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", timestamp);
        treeMap.put("channel",channel);
        mapSign1(getORpostType,IHttp.USER_PROTOCOL,treeMap,httpCallback);
    }
    public  static void getSecrecy(int getORpostType,String channel,HttpCallback httpCallback ){
        String  timestamp = String.valueOf(System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", timestamp);
        treeMap.put("channel",channel);
        mapSign1(getORpostType,IHttp.USER_Secrecy,treeMap,httpCallback);
    }

}
