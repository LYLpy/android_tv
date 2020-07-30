package com.geling.view.gelingtv_XX_tongbu.model;

import android.os.Message;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.HttpMsg;
import com.geling.view.gelingtv_XX_tongbu.utils.AES128;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.SPUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONObject;

import okhttp3.Call;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/31------更新------
 */

public class BaseHelper {
    //    RequestCall
    public static final int ERROR_NOT_VIP = 50001;//请求课程时，提示您还不是VIP会员
    public static final int ERROR_NOT_COUPON = 10021;//通过价格获取优惠券集合时，没有合适的优惠券

    public <T> void enqueue(final RequestCall call,
                            final IHttpCallback callback,
                            final int reqType,
                            final Class<T> clazz,
                            final int what,
                            final int arg1) {
        LogUtil.e("__basehelper", "enqueue");
//        call.execute(new MyStringCallback() {
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e("__basehelper_enqueue_onError", e.getMessage());
                if (callback != null) {
//                    Message message = new Message();
//                    message.what = what;
//                    message.obj = e;
                    callback.onHttpError(reqType, e.getMessage());
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    LogUtil.e("__basehelper_enqueue_onResponse",response.toString());
                    JSONObject jsonObject1111 = new JSONObject(response.toString());
//                    LogUtil.e("__BaseHelper_response_" + reqType,response.toString() + "__");
                    //转化成JSONObject，对象，取出data
                    String dataSrc = jsonObject1111.getString("content");
                    LogUtil.e("__basehelper_response_content" + reqType,dataSrc);
                    //解密获取data
                    String data = AES128.Decrypt(dataSrc, CommonUtils.PY_KEY);
                    LogUtil.e("__basehelper_response_Decrypt" + reqType,data);
                    JSONObject jsonObject = new JSONObject(data);
//                    LogUtil.e("__BaseHelper_response_data_" + reqType,data + "__");
                    int status = jsonObject.getInt("status");
                    int error = jsonObject.getInt("error");
                    //请求成功
                    if (status == 200) {
                        if (error == 10000){//请求成功
                            Gson gson = new Gson();
                            T bean = null;
                            if (clazz != null){
                                bean = gson.fromJson(data, clazz);
                                Message msg = new Message();
                                msg.what = what;//之前传进来的what放进去
                                msg.arg1 = arg1;//之前传进来的arg1放进去
                                msg.obj = bean;//把解密之后的数据放进去
//                              msg.arg1 = code;//把获取到的code放进去
                                if (callback != null){
                                    callback.onHttpSuccess(reqType,msg);
                                }
                            }else {//clazz为null,则返回JsonObject给调用者
                                Message msg = new Message();
                                msg.what = what;//之前传进来的what放进去
                                msg.arg1 = arg1;//之前传进来的arg1放进去
                                msg.obj = jsonObject;//把解密之后的数据放进去
//                              msg.arg1 = code;//把获取到的code放进去
                                if (callback != null){
                                    callback.onHttpSuccess(reqType,msg);
                                }
                            }
                        }else {
                            if (error == 10003){//表示token错误，清空缓存，跳转到登陆界面
                                SPUtil.clear();
//                                callback.onTokenError(reqType, "登录信息失效，请重新登录");
                                callback.onTokenError(reqType, "登录信息失效，请重新登录",String.valueOf(error));
                            }else if (error == ERROR_NOT_VIP || error == ERROR_NOT_COUPON){
                                //获取播放url时提示不是vip会员,或者通过价格获取优惠券时，没有合适的优惠券
                                if (callback instanceof NewIHttpCallback){
                                    Message message = new Message();
                                    message.what = what;
                                    message.arg1 = error;
                                    String msg = jsonObject.getString("msg");
//                                    HttpMsg httpErrorMsg = new HttpMsg();需要时可携带该对象传数据
                                    if (callback != null) {   // 请求失败的回调
                                        ((NewIHttpCallback)callback).onHttpErrorMsg(reqType,message,msg);
                                    }
                                }else {
                                    String msg = jsonObject.getString("msg");
                                    if (callback != null) {   // 请求失败的回调
                                        callback.onHttpError(reqType, String.valueOf(msg));
                                    }
                                }
                            }else {
                                String msg = jsonObject.getString("msg");
                                if (callback != null) {   // 请求失败的回调
                                    callback.onHttpError(reqType, String.valueOf(msg));
                                }
                            }
                        }
                    }else {//status非200，表示未成功访问到服务器
                        if (callback != null) {   // 请求失败的回调
                            callback.onHttpError(reqType, "请求错误");
                        }
                    }
                }catch (Exception e){
                    LogUtil.e("__basehelper_enqueue_Exception",e.getMessage());
                    if (callback != null){
                        callback.onHttpError(reqType,e.getMessage());
                    }
                }
            }
        });
    }


    public interface IHttpCallback {
        /**
         * 请求成功
         *
         * @param reqType 区分调用的是哪一个接口
         * @param msg 请求成功后返回的数据
         */
        void onHttpSuccess(int reqType,Message msg);
        /**
         * 请求错误
         *
         * @param reqType 区分调用的是哪一个接口
         * @param error 请求失败的原因
         */
        void onHttpError(int reqType, String error);

        /**
         * 请求错误,需要返回时Message
         *
         * @param reqType 区分调用的是哪一个接口
         * @param error 请求失败的原因
         */
        void onTokenError(int reqType,String error,String errorCode);
    }

    public <T> void enqueue(RequestCall call,
                            final IHttpCallback callback,
                            final int reqType,
                            final Class<T> clazz) {
        enqueue(call,callback,reqType,clazz,-1);
    }

    public <T> void enqueue(RequestCall call,
                            final IHttpCallback callback,
                            final int reqType,
                            final Class<T> clazz,
                            final int what) {
        enqueue(call,callback,reqType,clazz,what,-1);
    }

    /**
     * 新定义的回调接口，增加了Message回调,Message可以携带HttpErrorMsg数据
     */
    public interface NewIHttpCallback extends IHttpCallback{
        /**
         * 请求错误,需要返回Message时
         *
         * @param reqType 区分调用的是哪一个接口
         * @param error 请求失败的原因
         */
        void onHttpErrorMsg(int reqType, Message message, String error);
    }
//    =========================================以下是携带HttpMsg的网络访问============================================

    public interface IHttpCallbackWithHttpMsg {
    /**
     * 请求成功
     *
     * @param reqType 区分调用的是哪一个接口
     * @param httpMsg
     */
    void onHttpSuccess(int reqType,HttpMsg httpMsg);
    /**
     * 请求错误
     *
     * @param reqType 区分调用的是哪一个接口
     * @param error 请求失败的原因
     */
    void onHttpError(int reqType,String error,HttpMsg httpMsg);

}

    /**
     * 携带HttpMsg的网络访问
     * @param call
     * @param callback
     * @param reqType
     * @param clazz
     * @param httpMsg
     * @param <T>
     */
    public <T> void enqueueWithHttpMsg(final RequestCall call,
                                       final IHttpCallbackWithHttpMsg callback,
                                       final int reqType,
                                       final Class<T> clazz,
                                       final HttpMsg httpMsg) {
    LogUtil.e("__basehelper","enqueue");
//        call.execute(new MyStringCallback() {
    call.execute(new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("__basehelper_enqueue_onError",e.getMessage());
            if(callback != null){
//                    Message message = new Message();
//                    message.what = what;
//                    message.obj = e;
                callback.onHttpError(reqType,e.getMessage(),null);
            }
        }

        @Override
        public void onResponse(String response, int id) {
            try {
                LogUtil.e("__basehelper_enqueue_onResponse",response.toString());
                JSONObject jsonObject1111 = new JSONObject(response.toString());
//                    LogUtil.e("__BaseHelper_response_" + reqType,response.toString() + "__");
                //转化成JSONObject，对象，取出data
                String dataSrc = jsonObject1111.getString("content");
                LogUtil.e("__basehelper_response_content" + reqType,dataSrc);
                //解密获取data
                String data = AES128.Decrypt(dataSrc, CommonUtils.PY_KEY);
                LogUtil.e("__basehelper_response_Decrypt" + reqType,data);
                JSONObject jsonObject = new JSONObject(data);
//                    LogUtil.e("__BaseHelper_response_data_" + reqType,data + "__");
                int status = jsonObject.getInt("status");
                int error = jsonObject.getInt("error");
                //请求成功
                if (status == 200) {
                    if (error == 10000){//请求成功
                        Gson gson = new Gson();
                        T bean = null;
                        if (clazz != null){
                            bean = gson.fromJson(data, clazz);
//                            HttpMsg msg = new HttpMsg();
                            httpMsg.obj1 = bean;//把解密之后的数据放进去
//                              msg.arg1 = code;//把获取到的code放进去
                            if (callback != null){
                                callback.onHttpSuccess(reqType,httpMsg);
                            }
                        }else {//clazz为null,则返回JsonObject给调用者
//                            HttpMsg msg = new HttpMsg();
                            httpMsg.obj1 = jsonObject;//把解密之后的数据放进去
//                              msg.arg1 = code;//把获取到的code放进去
                            if (callback != null){
                                callback.onHttpSuccess(reqType,httpMsg);
                            }
                        }
                    }else {
                        if (error == 10003){//表示token错误，清空缓存，跳转到登陆界面
//                                SPUtil.clear();
//                                callback.onTokenError(reqType, "登录信息失效，请重新登录",String.valueOf(error));
                        }else {
//                                HttpMsg msg = new HttpMsg();
                            httpMsg.error = error;
                                String requestMsg = jsonObject.getString("msg");
                            if (callback != null) {
                                callback.onHttpError(reqType,requestMsg,httpMsg);
                            }
                        }
                    }
                }else {//status非200，表示未成功访问到服务器
                    if (callback != null) {   // 请求失败的回调
                        callback.onHttpError(reqType, "请求错误",httpMsg);
                    }
                }
            }catch (Exception e){
                LogUtil.e("__basehelper_enqueue_Exception",e.getMessage());
                if (callback != null){
                    callback.onHttpError(reqType,e.getMessage(),httpMsg);
                }
            }
        }
    });
}
}
