package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.HttpMsg;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.PayQrCodeModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;

import com.geling.view.gelingtv_XX_tongbu.R;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.QrCodeOrderModel;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/27------更新------
 * 支付二维码Dia
 */

public class PayQrCodeDia implements BaseHelper.IHttpCallbackWithHttpMsg {

    private Context mContext;
    private Dialog dialog;
    private LinearLayout mLlWechatPay;
    private LinearLayout mLlAliPay;
    private LinearLayout mLlCs;//客服
    private ImageView mIvQrCode;
    private ImageView mIvCs;
    private TextView mTvPayPrice;
    private TextView mTvMealName;
    private TextView mTvTips;
    private TextView mTvPayAmount;
    private TextView mTvPriceUnit;
    private String mPrice;//价格
    private String mMealName;//套餐名称
    //微信支付的二维码bean
    private QrCodeOrderModel mWechatPayQrCodeModel;
    //支付宝支付的二维码bean
    private QrCodeOrderModel mAlipayQrCodeModel;
    private HttpHelper mHttpHelper;
    private int mFocusOn = 0;
    private Map<String,String> mQrCodeList = new HashMap<>();//此处key是支付的Url，value是通过url到后台接口获取的二维码连接
    public PayQrCodeDia(Context context) {
        mContext = context;
    }
    public InputStream gifImage;

    public PayQrCodeDia builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_pay_qr_code, null);
        dialog = new Dialog(mContext,R.style.new_task_style);
        dialog.setContentView(view);
        mLlWechatPay = view.findViewById(R.id.ll_wechat_pay);
        mLlAliPay = view.findViewById(R.id.ll_alipay);
        mLlCs = view.findViewById(R.id.ll_customer_service);
        mIvQrCode = view.findViewById(R.id.iv_qr_code);
        mIvCs = view.findViewById(R.id.iv_cs);
        mTvPayPrice = view.findViewById(R.id.tv_pay_price);
        mTvMealName = view.findViewById(R.id.tv_meal_name);
        mTvTips = view.findViewById(R.id.tv_tips);
        mTvPayAmount = view.findViewById(R.id.tv_pay_amount);
        mTvPriceUnit = view.findViewById(R.id.tv_price_unit);
        initListener();
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                //数据都重置一下，因为待会会复用这个dialog,否则再次打开dia的时候，显示的还是之前的数据
                try{
                    mLlWechatPay.requestFocus();
                    mIvQrCode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.loading_anim));


                    //mIvQrCode.setI
                    mWechatPayQrCodeModel = null;
                    mAlipayQrCodeModel = null;
                }catch (Exception e){

                }
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_BACK:
                        LogUtil.e("__setOnKeyListener","onKey","返回");
//                        监听用户按下返回键时，关闭dialog
                        if (dialog != null){
                            dialog.cancel();
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
        return this;
    }

    public void setWechatPayQrCodeModel(QrCodeOrderModel wechatPayQrCodeModel) {
        mWechatPayQrCodeModel = wechatPayQrCodeModel;
        LogUtil.e("__loadQrcode","setWechatPayQrCodeModel");
        loadQrcode(true);
//        loadQrcode(false);
    }

    public void setAlipayQrCodeModel(QrCodeOrderModel alipayQrCodeModel) {
        mAlipayQrCodeModel = alipayQrCodeModel;
        LogUtil.e("__loadQrcode","setAlipayQrCodeModel");
        loadQrcode(true);
//        loadQrcode(false);
    }
    /**
     * 加载二维码
     * @param isRequest 是否做网络请求，避免死循环一直做网络请求
     */
    private void loadQrcode(boolean isRequest){
        gifImage = mContext.getClassLoader().getResourceAsStream("assset/"+"load");
        gifImage = mContext.getClassLoader().getResourceAsStream("assset/"+"load");
        String qrCodeUrl = null;
        String key = null;
        switch (mFocusOn){
            case HttpHelper.PY_PAY_TYPE_WECHAT:
                mTvPayPrice.setText(String.valueOf(mPrice));
                mTvPayAmount.setText("支付金额:");
                mTvPriceUnit.setText("元");
                mTvTips.setText(String.valueOf("如遇到支付问题，请联系客服"));
                if (mWechatPayQrCodeModel != null) {
                    key = mWechatPayQrCodeModel.getData().getPay_info().getPay_url();
                    GroceryStoreUtils.GlideImagHasPlaceHolder(mContext,key,mIvQrCode,R.drawable.loading_anim);
                    LogUtil.e("__url_wechat",key);
                }
                 break;
            case HttpHelper.PY_PAY_TYPE_ALIPAY:
                mTvPayPrice.setText(String.valueOf(mPrice));
                mTvPayAmount.setText("支付金额:");
                mTvPriceUnit.setText("元");
                mTvTips.setText(String.valueOf("如遇到支付问题，请联系客服"));
                if (mAlipayQrCodeModel != null){
                     key = mAlipayQrCodeModel.getData().getPay_info().getPay_url();
                    GroceryStoreUtils.GlideImagHasPlaceHolder(mContext,key,mIvQrCode,R.drawable.loading_anim);
                }
                break;
            case HttpHelper.PY_WECHAT_CS:
                mTvPayPrice.setText(String.valueOf("微信客服"));
                mTvPayAmount.setText("");
                mTvPriceUnit.setText("");
                mTvTips.setText(String.valueOf("扫一扫添加微信客服"));
                if (MainApp.getInstance().getOhterModel()!=null){
                    qrCodeUrl = MainApp.getInstance().getOhterModel().getData().getCustomer_service_code();
                    GroceryStoreUtils.GlideImagHasPlaceHolder(mContext,qrCodeUrl,mIvQrCode,R.drawable.loading_anim);
//                    loadQrcode(true);
                }
                break;
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mLlWechatPay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    LogUtil.e("__onFocusChange",String.valueOf(mWechatPayQrCodeModel == null));
                    mFocusOn = HttpHelper.PY_PAY_TYPE_WECHAT;
                    if (mWechatPayQrCodeModel == null ){
                        if (mListener != null){
                            mListener.onPayWaySelected(mFocusOn);
                        }
                    }else {
                        loadQrcode(true);
                    }
                }
            }
        });
        mLlAliPay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    LogUtil.e("__onFocusChange",String.valueOf(mAlipayQrCodeModel == null));
                    mFocusOn = HttpHelper.PY_PAY_TYPE_ALIPAY;
                    //表示没有这个二维码，通知activity获取二维码
                    if (mAlipayQrCodeModel == null){
                        if (mListener != null){
                            mListener.onPayWaySelected(mFocusOn);
                        }
                    }else {
                        loadQrcode(true);
                    }
                }
            }
        });

        mLlCs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    mFocusOn = HttpHelper.PY_WECHAT_CS;
                    if (MainApp.getInstance().getOhterModel()!=null){
                        loadQrcode(false);
                    }else {
                        mIvQrCode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.loading_anim));
                    }
                    mIvCs.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cs_focus));
                }else {
                    mIvCs.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cs_normal));
                }
            }
        });

    }

    public void setPrice(String price) {
        mPrice = price;
        mTvPayPrice.setText(mPrice);
    }

    public void setMealName(String mealName) {
        mMealName = mealName;
        mTvMealName.setText(mMealName);
    }

    public QrCodeOrderModel getWechatPayQrCodeModel() {
        return mWechatPayQrCodeModel;
    }

    public QrCodeOrderModel getAlipayQrCodeModel() {
        return mAlipayQrCodeModel;
    }

    public void show() {
        if(dialog!=null)dialog.show();
    }
    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }


    public void setOnPayWaySelectedListener(OnPayWaySelectedListener listener) {
        mListener = listener;
    }

    private OnPayWaySelectedListener mListener;

    @Override
    public void onHttpSuccess(int reqType, HttpMsg httpMsg) {
        switch (reqType){
            case HttpHelper.PY_HTTP_GET_QRCODE_BY_URL:
                PayQrCodeModel payQrCodeModel = (PayQrCodeModel) httpMsg.obj1;
                mQrCodeList.put(httpMsg.msg1,payQrCodeModel.getData().getQrcodeUrl());
               loadQrcode(false);
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error, HttpMsg httpMsg) {
        switch (reqType){
            case HttpHelper.PY_HTTP_GET_QRCODE_BY_URL:
                ToastUtil.showToast(error);
                break;
        }
    }


    public interface OnPayWaySelectedListener {
        /**
         * @param pay_way 支付方式，0表示微信，1表示支付宝
         */
        void onPayWaySelected(int pay_way);
    }
    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 清除数据，将两个Model清空，将二维码置空
     */
    public void clearData(){
        mWechatPayQrCodeModel = null;
        mAlipayQrCodeModel = null;
        mIvQrCode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.loading_anim));
        //清空数据之后，让支付方式获取焦点，此时会调用onPayWaySelected让activity重新获取二维码model
        switch (mFocusOn){
            case HttpHelper.PY_PAY_TYPE_WECHAT:
                mLlWechatPay.requestFocus();
                break;
            case HttpHelper.PY_PAY_TYPE_ALIPAY:
                mLlAliPay.requestFocus();
                break;
        }
    }

    public void setHttpHelper(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }
}
