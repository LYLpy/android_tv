package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseForbiddenDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.LoadingDialog;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import butterknife.ButterKnife;


/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/17------更新------
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{
    public String mTestUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    //网络请求
//    protected HttpHelper mHttpHelper = new HttpHelper();
    //一些延时操作，比如等待定位，延时，失败提示等
    protected Handler delayHandler = new Handler();
    protected static final int RESULT_COLLECTION_ACTIVITY = 1;
    protected static String VIDEO_URL = "";
    public final int OFFLINE_VIP = 1;//VIP类型，线下VIP
    public final int ONLINE_VIP = 2;//线上VIP
    protected HttpHelper mHttpHelper = new HttpHelper();//网络请求助手
    private boolean mIsClickAble;//是否允许点击,避免用户连续点击产生不必要的问题
    private final long mClickInterval = 1500;//点击间隔1.5秒
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initData();
        initView();
        LogUtil.init(this);
//        MainApp.getInstance().mActivityList.add(this);
    }

    private Dialog mLoadingDialog;
    /**
     * 显示加载提示框(可以在子线程调用)
     */
    public void showLoadingDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLoadingDialog = new LoadingDialog(BaseActivity.this).builder().setMsg(message).getDialog();
                mLoadingDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
                // activity如果正在销毁或已销毁，不能show Dialog，否则出错。
                if (!isFinishing())
                    mLoadingDialog.show();
            }
        });
        //3秒后自动取消
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        },3000);
    }

    /**
     * 展示自定义消失时间的等待框
     * @param message
     * @param delay
     */
    public void showLoadingDialog(final String message, int delay) {
        LogUtil.e("__showCustomProgressDialog",delay + "");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLoadingDialog = new LoadingDialog(BaseActivity.this).builder().setMsg(message).getDialog();
                // 点击外部时不销毁弹出窗
                mLoadingDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
                // activity如果正在销毁或已销毁，不能show Dialog，否则出错。
                if (!isFinishing())
                    mLoadingDialog.show();
            }
        });
        //延迟X毫秒后自动取消
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        },delay);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止waitProgress被线程调用时，导致Activity xxxx has leaked， that was originally added here异常
        if ( mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.cancel();
        }
        delayHandler.removeCallbacksAndMessages(null);
        MainApp.getInstance().mActivityList.remove(this);

    }
    protected synchronized void Destroy(){}
    /**
     * 销毁加载提示框
     */
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    /**
     * 检查课程是否禁用
     */
    protected synchronized void isCourseForbidden (final String courseId, final CourseForbiddenCallBack callBack){
        if (true){
            mHttpHelper.isCourseForbidden(courseId, new BaseHelper.IHttpCallback() {
                @Override
                public void onHttpSuccess(int reqType, Message msg) {
                    switch (reqType){
                        case HttpHelper.PY_HTTP_COURSE_FORBIDDEN:
                        Intent intent = new Intent(BaseActivity.this,CourseDetailActivity.class);
                        intent.putExtra("courseId",courseId);
                        startActivity(intent);
                            break;
                    }
                }

                @Override
                public void onHttpError(int reqType, String error) {
                    switch (reqType){
                        case HttpHelper.PY_HTTP_COURSE_FORBIDDEN:
                            new CourseForbiddenDialog().builder(BaseActivity.this,"").show();
                            break;
                    }
                }

                @Override
                public void onTokenError(int reqType, String error, String errorCode) {
                    toLogin();
                }
            });
            return;
        }
    }


    /**
     * Glide加载图片
     * @param imgUrl
     * @param iv
     */
    protected void loadImg(String imgUrl, ImageView iv){
        GroceryStoreUtils.GlideImag(this,imgUrl,iv);
    }
    /**
     * 后台返回的数据带了很多html的标签，比如<br />，然后才是数据，写个方法处理这些
     */
    protected String substringData(String dataOri){
        String data = dataOri.toString().substring(dataOri.toString().indexOf("{\"data\":"));
        return data;
    }

    public interface CourseForbiddenCallBack{
        /**
         * @param isForbbidden 是否禁用
         */
        void isForbbidden(boolean isForbbidden);

        /**
         * 请求错误
         */
        void onError();
    }


    /**
     * 是否允许点击，避免用户连续多次点击，造成不必要的问题
     * @return
     */
    protected boolean isClickAble(){
        if (mIsClickAble){
            mIsClickAble = false;
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsClickAble = true;
                }
            },mClickInterval);
            return true;
        }else {
            return false;
        }
    }
    /**
     * token错误时，前往登录界面
     */
    public void toLogin(){
        Intent intent = new Intent(this,SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }
    public interface HttpCallBack{
        /**
         * @param isVip 是否是vip
         * @param vipType vip类型，1是线下，2是线上
         * @param vipEndDate vip结束时间，格式是2020-03-11
         */
        void onSuccess(boolean isVip,int vipType,String vipEndDate);
        void onError(int vipType,String error);
    }

    protected abstract void initData();
    protected abstract int getLayoutRes();
    protected abstract void initView();


}
