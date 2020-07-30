package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.geling.view.gelingtv_XX_tongbu.model.IHttp;
import com.geling.view.gelingtv_XX_tongbu.utils.Base64Util;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.SysUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import com.geling.view.gelingtv_XX_tongbu.R;

import com.geling.view.gelingtv_XX_tongbu.model.bean.VideoUrlBean;

import org.json.JSONObject;

import java.util.TreeMap;

import butterknife.BindView;
import okhttp3.Call;

/**
 * ------author----------日期--------改动-------
 * 加载webView的activity
 */

public class WebViewActivity extends BaseActivity {
//    字符串常量，作为android与js通信的接口，即字符串映射对象
    public static final String JAVAINTERFACE = "appObject";
//    @BindView(R.id.fl_back)
//    FrameLayout mFlBack;
    @BindView(R.id.webview)
    WebView mWebview;
    private String mUrl = "";
    private WebSettings mWebSettings;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        String userId = SysUtil.getSystemProperties(this,SysUtil.SYSKEY_JX_SMART_CARD);
        if(mUrl.contains("?")){
            mUrl = mUrl + "&userId=" + userId;
        }else {
            mUrl = mUrl + "?userId=" + userId;
        }
//        mUrl = "http://10.60.33.178:8081/Public/Activity/20yd_qa/index.html";
        LogUtil.e("__mUrl",mUrl);
        mWebSettings = mWebview.getSettings();
        mWebSettings.setDomStorageEnabled(true);//主要是这句,适配H5
        mWebSettings.setJavaScriptEnabled(true);//启用js
        mWebSettings.setBlockNetworkImage(false);//解决图片不显示
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                LogUtil.e("__onJsAlert","message:" + message);
                return super.onJsAlert(view, url, message, result);
            }
        });
//        mWebview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
//                LogUtil.e("__onReceivedSslError",error.toString() + "__");
                //1.用户选择继续加载
//                 handler.proceed();
                //2.用户取消
                //handler.cancel()
//            }
//        });
//        mWebSettings = mWebview.getSettings();
//        mWebSettings.setDomStorageEnabled(true);//设置适应Html5 重点是这个设置
        mWebview.loadUrl(mUrl);
        // 将Android里面定义的类对象JAVAINTERFACE暴露给javascript
        mWebview.addJavascriptInterface(this, JAVAINTERFACE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
//        mFlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.fl_back:
//                finish();
//                break;
        }
    }
    /**
     * 给js调用
     * 前往vip订购界面
     */
    @JavascriptInterface
    public void toVip(){
        LogUtil.e("__toVip","toVip");
        Intent intent = new Intent(this,VipActivity3.class);
        startActivity(intent);
    }

    /**
     * 给js调用的，跳转到指定界面的函数
     */
    @JavascriptInterface
    public void goTo(String act,String content){
        LogUtil.e("__goTo_act",act + "__");
        LogUtil.e("__goTo_content",content + "__");
        try {
            Intent intent = null;
            switch (act){
                case "vip"://vip订购页面
                    intent = new Intent(this,VipActivity3.class);
                    startActivity(intent);
                    break;
                case "course_detail":
                    intent = new Intent(this,CourseDetailActivity.class);
                    intent.putExtra("courseId",content);
                    startActivity(intent);
                    break;
                case "history":
                    intent = new Intent(this, MeActivity.class);
                    intent.putExtra("newPlayReocrd",3);
                    startActivity(intent);
                    break;
                case "collect":
                    intent = new Intent(this,CollectionActivity.class);
                    startActivity(intent);
                    break;
                case "search":
                    intent = new Intent(this,SearchActivity.class);
                    startActivity(intent);
                    break;
                case "play_video":
//                    requestVideoUrl(content);
                    break;
                case "home":
                    intent = new Intent(this,MainActivity3.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
            }
        }catch (Exception e){
            LogUtil.e("__Exception",e.getMessage() + "__");
        }
    }
    private VideoUrlBean mVideoUrlBean;
    private String mSessionid;

    private void requestVideoUrl(String videoId){
        //一、升序排序的TreeMap；
        TreeMap<String,String> treeMap = new TreeMap<>();
        mSessionid = SysUtil.getSystemProperties(this, "persist.sys.hwvod.cookie");
        String epg = SysUtil.getSystemProperties(this, "persist.sys.hwvod.epgurl");
        LogUtil.e("__requestVideoUrl_course__epg111",epg + "__");
        if ((TextUtils.isEmpty(mSessionid)) || (TextUtils.isEmpty(epg))) {
            LogUtil.e("__requestVideoUrl_course__return_isEmpty(paramVarArgs)","isEmpty(paramVarArgs)" + "__");
            return;
        }
        if (!epg.contains("/EPG")) {
            LogUtil.e("__requestVideoUrl_course__return_!str.contains(EPG)",epg + "__");
            return ;
        }
        epg = epg.substring(7, epg.indexOf("/EPG"));
        LogUtil.e("__requestVideoUrl_course__sessionid",mSessionid + "__");
        LogUtil.e("__requestVideoUrl_course__epg222",epg + "__");
        LogUtil.e("__requestVideoUrl_course__videoId",videoId + "__");
        String userId = SysUtil.getSystemProperties(this,SysUtil.SYSKEY_JX_SMART_CARD);
        LogUtil.e("__requestVideoUrl_course__mSessionid",mSessionid + "__");
        LogUtil.e("__requestVideoUrl_course__epg",epg + "__");
        LogUtil.e("__requestVideoUrl_course__videoId",videoId + "__");
        LogUtil.e("__requestVideoUrl_course__userId",userId + "__");
        treeMap.put("sessionid", mSessionid);
        treeMap.put("epg", epg);
        treeMap.put("videoId", videoId);
        treeMap.put("userId", userId);
        String data = "";
        String sign = "";
        try {
            JSONObject jsonObject111 = CommonUtils.getRequestJsonObj(treeMap);
            data = jsonObject111.getString("data");
            sign = jsonObject111.getString("sign");
            LogUtil.e("__requestVideoUrl_course__course_getRe_data",jsonObject111.getString("data") + "__");
            LogUtil.e("__requestVideoUrl_course__course_getRe_sign",jsonObject111.getString("sign") + "__");
            LogUtil.e("__requestVideoUrl_course__course_getRe_json.toStr",jsonObject111.toString() + "__");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .get()
                .addParams("data",data)
                .addParams("sign", sign)
                .url(IHttp.GET_VIDEO_DATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("__requestVideoUrl_course_onError_paramMap",e.getMessage() + "__");
                    }
                    @Override
                    public void onResponse(String response0, int id) {
                        try{
                            LogUtil.e("__requestVideoUrl_course_onResponse_toString()",response0.toString() + "__");
                            //返回的数据前面加了标签，需要截取数据
//                            String response = response0.toString().substring(response0.toString().indexOf("{\"data\":"));
                            String response = response0.toString();
                            JSONObject jsonObject1111 = new JSONObject(response);
                            LogUtil.e("__requestVideoUrl_course_onResponse_response",jsonObject1111.toString() + "__");
                            String data = jsonObject1111.getString("data");
                            LogUtil.e("__requestVideoUrl_course_onResponse_data",data + "__");
                            String paramMap = Base64Util.decodeStr(data);
                            LogUtil.e("__requestVideoUrl_course_onResponse_decode_paramMap",paramMap + "__");
                            JSONObject jsonObject = new JSONObject(paramMap);
                            //我们自己3.0后台数据处理走这里
                            int code = jsonObject.getInt("code");
                            if (code == 10000){
                                String data111 = jsonObject.getString("data");
                                JSONObject jsonObject111 = new JSONObject(data111);
                                mVideoUrlBean = new VideoUrlBean();
                                mVideoUrlBean.setVodId(jsonObject111.getString("vodId"));
                                mVideoUrlBean.setVodName(jsonObject111.getString("vodName"));
                                mVideoUrlBean.setReportUrl(jsonObject111.getString("reportUrl"));
                                mVideoUrlBean.setUrl(jsonObject111.getString("url"));
                                startVideoActivity();
                            }else {
                                ToastUtil.showToast(jsonObject.getString("msg"));
                            }
                        }catch (Exception e){
                            LogUtil.e("__requestVideoUrl_course__onResponse_Exception",e.getMessage() + "__");
                            ToastUtil.showToast("获取播放地址失败，请稍后重试");
                        }
                    }
                });
    }

    /**
     * 开启局方点播播放器
     */
    private void startVideoActivity(){
        try {
            if (mVideoUrlBean == null){
                return;
            }
            Intent intent = new Intent();
//          paramInteger.addFlags(402653184);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            intent.setComponent(new ComponentName("com.skyworthdigital.app.VodBrowser", "com.skyworthdigital.app.VodBrowser.VodPlayActivity"));
            intent.putExtra("url", mVideoUrlBean.getUrl());
            intent.putExtra("reportUrl", mVideoUrlBean.getReportUrl());
            intent.putExtra("cookie", mSessionid);
            intent.putExtra("vodId", mVideoUrlBean.getVodId());
            intent.putExtra("breakTime", 0);
            intent.putExtra("vodName", mVideoUrlBean.getVodName());
//            intent.putExtra("playType", 2);
            intent.putExtra("playType", 2);//时移:1 vod点播:2 egp回看:3
            intent.putExtra("code", "CODE");
            intent.putExtra("programeType", 0);
            startActivityForResult(intent,11);
        }catch (Exception e){
            ToastUtil.showToast("无法打开播放界面");
        }
    }
    /**
     * 给Js调用的退出界面函数
     */
    @JavascriptInterface
    public void quit(){
        LogUtil.e("__toVip","toVip");
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //模拟器测试时键盘中的的Enter键，模拟ok键
            case KeyEvent.KEYCODE_ENTER:
                break;
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                LogUtil.e("__onKeyDown_his", "中间键");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                LogUtil.e("__onKeyDown", "左");
                LogUtil.e("__onKeyDown", "左");
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                LogUtil.e("__onKeyDown", "右");
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                LogUtil.e("__onKeyDown", "上方向键");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                LogUtil.e("__onKeyDown", "下方向键");
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
