package com.geling.view.gelingtv_XX_tongbu.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.bean.OhterModel1;
import com.geling.view.gelingtv_XX_tongbu.model.bean.UserTokenBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;

import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterChildNav;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterMainNav;
import com.geling.view.gelingtv_XX_tongbu.utils.SPUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;

import java.util.TreeMap;

import butterknife.BindView;



/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/19------更新------
 *
 * 判断两种用户形式
 * 如果本地有用户的user_id，username ，email，phone，integral（积分），vip，IsFrist(是否第一次启动) 为外语通用户
 * 如果是有没有是为新用户
 */



public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    private CountDownTimer mCountDownTimer;
    private OhterModel1 ohterModel1;
    private static final int GO_HOME = 1000;
    private int userid;
    private String username;
    private String phone;
    private String token;

    // 延迟2秒
    private static final long SPLASH_DELAY_MILLIS = 2000;
    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case GO_HOME:
//                    goHome();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void goHome(){
        startActivity(new Intent(SplashActivity.this, MainActivity3.class));
//        startActivity(new Intent(SplashActivity.this, MeActivity.class));
        finish();
    }
    @Override
    protected void initData() {
        LogUtil.e("__token",token+"");
        userid = UserManager.getUserid();
        username = UserManager.getUsername();
        phone = UserManager.getUserphone();
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("userid",userid+"");treeMap.put("username",username+"");treeMap.put("phone",phone+"");
        LogUtil.e("__用户id",treeMap+"");
        if (!UserManager.isSignTokenNULL()){
            if (userid != 0||!username.equals("")){
                XHttpUtils.getUserMass(1,userid, new HttpCallback() {
                    @Override
                    public void onSuccess(String json) {
                        LogUtil.e("__用户id",json);
                        UserTokenBean userTokenBean =  UserTokenBean.gsonBean(json);
                        token = userTokenBean.getData().getToken();
                        UserManager.setToken(token);
                    }

                    @Override
                    public void onFailed(String  json) {
                        ToastUtil.showToast("用户登录失败,请重新登录！");
                        LogUtil.e("用户登录失败"+treeMap);
                        token = UserManager.getToken();

                    }
                });
            }else {
                ToastUtil.showToast("用户已登录token");
            }
        }

        XHttpUtils.getHttpSignSplash(0, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.e("__1json", json);
                LogUtil.e("__1initData_response_闪屏", "闪屏_response:" + json);
//                try {
                ohterModel1 = OhterModel1.gsonBean(json);
//                        mModel = gson.fromJson(json, OhterModel1.class);
                        MainApp.getInstance().setOhterModel(ohterModel1);
                        int open_status= ohterModel1.getData().getOpen_status();
                String norColor =  ohterModel1.getData().getButton_font_color();
                String focColor =  ohterModel1.getData().getButton_font_color_focus();
                String selColor =  ohterModel1.getData().getButton_font_color_selected();
                String focBgColor =  ohterModel1.getData().getButton_color_focus();
                RvAdapterChildNav.mBgNorColor = ohterModel1.getData().getButton_color();
                RvAdapterChildNav.mBgFocColor = focBgColor;
                RvAdapterChildNav.mTextNorColor = norColor;
                RvAdapterChildNav.mTextFocColor = focColor;
                RvAdapterChildNav.mTextSelColor = selColor;
                RvAdapterMainNav.mNormalTextColor = norColor;
                RvAdapterMainNav.mFocusTextColor = focColor;
                RvAdapterMainNav.mSelTextColor = selColor;
                RvAdapterMainNav.mFocBgColor = focBgColor;
                        if (open_status==1) {//0无全屏图片 1有全屏图片
                            String imgUrl = "http://139.9.7.208:8989/Public/Uploads/images/2019-11-16/5dcf62d68233a.jpg";
                            LogUtil.e("__initData_闪屏", "闪屏_response_getOpen_time:" + ohterModel1.getData().getOpen_time());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //更新UI
//                                    RequestOptions options = new RequestOptions()
//                                            .placeholder(R.drawable.loading3)
//                                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//磁盘缓存
//                                            .skipMemoryCache(false);//内存缓存
                                    Glide.with(SplashActivity.this).load(ohterModel1.getData().getOpen_img()).into(mIv);
                                    //GroceryStoreUtils.GlideImag(SplashActivity.this, ohterModel1.getData().getOpen_img(), mIv);
                                    mIv.setImageURI(Uri.parse(ohterModel1.getData().getOpen_img()));
                                    LogUtil.e("__1img",ohterModel1.getData().getOpen_img());
                                    startCountDownTimer(ohterModel1.getData().getOpen_time() * 1000L);
                                }
                            });
                            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
//                            toNext();
                        } else {
                            toNext();
                        }
//                    } else {
//                        LogUtil.e("__initData_闪屏", "请求闪屏页面_错误码_response_code:" + code);
//                        LogUtil.e("__initData_闪屏", "请求闪屏页面_错误_数据_response_data:" + data);
////                                LogUtil.e("__initMainData","导航栏请求数据失败_response_msg:" + mMainNavData.getMsg());
//                        startActivity(new Intent(SplashActivity.this, MainActivity3.class));
//                        finish();
//                    }
//                } catch (Exception e) {
//                    startActivity(new Intent(SplashActivity.this, MainActivity3.class));
//                    finish();
//                    LogUtil.e("__1initData_闪屏", "请求求闪屏页面数据解析异常_response_Exception:" + e.getMessage());
//                }

            }

            @Override
            public void onFailed(String  json) {

            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    private void startCountDownTimer(long time) {
        // +50是为了应对CountDownTimer的毫秒误差bug
        mCountDownTimer = new CountDownTimer(time , 1000) {
            public void onTick(long millisUntilFinished) {
                mTv.setText("跳过" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTv.setText("跳过0");
                toNext();
            }
        }.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //这个记得改，现在是模拟器，所以只能用Enter键测试，实际应该是中间键
            case KeyEvent.KEYCODE_ENTER:
                LogUtil.e("__onKeyDown_splash", "Enter键");
                toNext();
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                LogUtil.e("__onKeyDown中间键", "中间键");
                toNext();
                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    /**
     *前往下一个节目，如果没有token则跳转登录界面
     */
    private void toNext(){
        LogUtil.e("__toNext",String.valueOf(UserManager.getToken()));
        Intent intent;
//        if (TextUtils.isEmpty(UserManager.getToken())){
//            intent = new Intent(SplashActivity.this, SignInActivity.class);
//        }else {
//            intent = new Intent(SplashActivity.this, MainActivity3.class);
//        }
        intent = new Intent(SplashActivity.this, MainActivity3.class);
        startActivity(intent);
        finish();
    }
//
}
