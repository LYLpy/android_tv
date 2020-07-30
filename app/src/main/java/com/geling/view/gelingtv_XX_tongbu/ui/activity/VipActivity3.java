package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.OhterModel1;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CouponByPriceModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MealModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.OrderStatusModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.QrCodeOrderModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.UserInfoModel;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis.IntentsUtlis;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterChildNav;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterMainNav;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterMeal3;
import com.geling.view.gelingtv_XX_tongbu.ui.view.MyTvRecyclerView;
import com.geling.view.gelingtv_XX_tongbu.ui.view.PayQrCodeDia;
import com.geling.view.gelingtv_XX_tongbu.ui.view.PaySuccessDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.SelectCouponDialog;
import com.geling.view.gelingtv_XX_tongbu.utils.GetAppMessUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/18------更新-----
 * vip购买界面3,TV版格灵同步培优
 *
 */

public class VipActivity3 extends BaseActivity implements BaseHelper.NewIHttpCallback {

    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.activity_bg_vip)
    ImageView mActivityBgVip;
    @BindView(R.id.tv_vip_info)
    TextView mTvVipInfo;
    @BindView(R.id.ll_vip_info)
    LinearLayout mLlVipInfo;
    @BindView(R.id.rv_meal)
    MyTvRecyclerView mRvMeal;
    @BindView(R.id.tv_coupon_value)
    TextView mTvCouponValue;
    @BindView(R.id.tv_coupon_rmb_sign)
    TextView mTvCouponRmbSign;
    @BindView(R.id.tv_change_coupon)
    TextView mTvChangeCoupon;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    @BindView(R.id.tv_pay_now)
    TextView mTvPayNow;
    @BindView(R.id.cb_isContract)
    Checkable mCBisContract;
    @BindView(R.id.ll_ck)
    LinearLayout ll_ck;//用于动态隐藏Checkable控件

    //选中的优惠券
    private CouponByPriceModel.DatabeanX.AppGiftListbean.Databean mSelectCouponModel;
    //key是套餐的id，vaule是这个套餐能使用的优惠券集合，避免每次选择套餐之后，都会去后台拿优惠券集合，反应速度太慢
    private Map<String,List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean>> mMapCouponData
            = new HashMap<>();
    private RvAdapterMeal3 mRvAdapterMeal3;
    //套餐列表
    private List<MealModel.DatabeanX.Databean> mMealData;
    //记录焦点所在位置
    private int mFocusPosition = -1;
    public static final int FOCUS_ON_BACK = 0;
    public static final int FOCUS_ON_RV = 1;
    public static final int FOCUS_ON_CHANGE_COUPON = 2;
    public static final int FOCUS_ON_PAY_NOW = 3;
    //微信支付的二维码bean
    private QrCodeOrderModel mWechatPayQrCodeModel;
    //支付宝支付的二维码bean
    private QrCodeOrderModel mAlipayQrCodeModel;
    //存放微信支付二维码Model的MAP，key是meal_id拼接coupon_id
    private Map<String,QrCodeOrderModel> mMapWechatpayQrCodeModel = new HashMap<>();
    //存放支付宝支付二维码Model的MAP，key同微信
    private Map<String,QrCodeOrderModel> mMapAlipayQrCodeModel = new HashMap<>();
    private String order_no; //订单号
    private String Pname;//商品名
    private String Pdesc;//商品描述
    private String Pprice;//订单金额
    private String isContract;//当贝自动支付

//    private String channel= GetAppMessUtils.getAppMetaData(this,"UMENG_CHANNEL");
    private String   channel;
    @Override
    protected void initData() {
        //获取套餐
        mHttpHelper.getMeal(this);
        initVipData();
    }

    //初始化vip数据
    private void initVipData() {
        UserInfoModel userInfoModel = UserManager.getUserInfo();
        if (userInfoModel != null){
            //        会员：0非会员，1会员
            if (userInfoModel.getData().getIsVip() == 1){
//            其中的vip时间是这个格式2020-01-11 14:41:49，需求是只到日期
                mLlVipInfo.setVisibility(View.VISIBLE);
                mTvVipInfo.setText("您已是VIP，" + userInfoModel.getData().getVip_time() + "到期");
            }
        }else {
            mHttpHelper.getUserInfo(this);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vip3;
    }

    @Override
    protected void initView() {
        if (channel=="danbei"){
//            mCBisContract.setV
           ll_ck.setVisibility(View.VISIBLE);
        }else {
            ll_ck.setVisibility(View.GONE);
        }
        initListener();
        loadLogo();
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        mTvBack.setOnClickListener(this);
        mTvChangeCoupon.setOnClickListener(this);
        mTvPayNow.setOnClickListener(this);
        mTvBack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    mFocusPosition = FOCUS_ON_BACK;
                    refreshRvMeal();
                    ViewCompat.animate(mTvBack)
                            .setDuration(200)
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .start();
                }else {
                    ViewCompat.animate(mTvBack)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }
        });
        mTvChangeCoupon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    mFocusPosition = FOCUS_ON_CHANGE_COUPON;
                    refreshRvMeal();
                    ViewCompat.animate(mTvChangeCoupon)
                            .setDuration(200)
                            .scaleX(1.13f)
                            .scaleY(1.13f)
                            .start();
                }else {
                    ViewCompat.animate(mTvChangeCoupon)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }
        });
        mTvPayNow.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    mFocusPosition = FOCUS_ON_PAY_NOW;
                    refreshRvMeal();
                    ViewCompat.animate(mTvPayNow)
                            .setDuration(200)
                            .scaleX(1.13f)
                            .scaleY(1.13f)
                            .start();
                }else {
                    ViewCompat.animate(mTvPayNow)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }
        });
    }

    /**
     * 其他控件获取焦点时，刷新价格rv
     */
    private void refreshRvMeal() {
        try {
            if (mRvAdapterMeal3 != null){
                mRvAdapterMeal3.notifyDataSetChanged();
            }
        }catch (Exception e){

        }
    }
    //当贝支付接收返回

    /**
     * 当贝支付接收返回
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            int back = bundle.getInt("back");//返回值1为支付成功
            String Out_trade_no = bundle.getString("out_trade_no");//返回回来的订单号
            if (back==1){
                Toast.makeText(this,"支付成功",Toast.LENGTH_LONG).show();
            }else if (back == 0){

            }else{
                Toast.makeText(this,"支付出错 请在我的界面中添加客服微信 错误码（back="+back+")",Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.tv_back:
                    finish();
                    break;
                case R.id.rl_refresh:
                    break;
                case R.id.tv_change_coupon:
                    showSelectCouponDia();
                    break;
                case R.id.tv_pay_now:
                    mWechatPayQrCodeModel = null;
                    mAlipayQrCodeModel = null;
                    if (mSelectMealModel != null){
//                        getQrCode(HttpHelper.PY_PAY_TYPE_WECHAT);
                        if (channel=="danbei"){
                            getQrCode(HttpHelper.PY_PAY_TYPE_DANGBEI);
                        }else{
                           showQrCodeDia();
                        }
                       

                    }else {
                        ToastUtil.showToast("没有套餐数据，请稍后重试");
                    }
                    break;
            }
        } catch (Exception e) {
            LogUtil.e("__onClick_Exception", e.getMessage() + "__");
        }
    }

    /**
     * 获取二维码
     * 订单号
     */
    private void getQrCode(int pay_type){
        String coupon_id = "0";
        String u_gift_id = "0";
        String couponCurrency = "0";
        String goods_id = String.valueOf(mSelectMealModel.getId());
        if (mSelectCouponModel != null){
            coupon_id = String.valueOf(mSelectCouponModel.getId());
            u_gift_id = String.valueOf(mSelectCouponModel.getU_gift_id());
            couponCurrency = String.valueOf(mSelectCouponModel.getCouponCurrency());

        }
        String key = goods_id + coupon_id;
        mHttpHelper.getPayQrCode(this,goods_id
                ,String.valueOf(mPayPrice),String.valueOf(mSelectMealModel.getDiscount())
                ,coupon_id,u_gift_id,couponCurrency,String.valueOf(pay_type),Integer.valueOf(key));
    }

    /**
     * 展示选择优惠券dia
     */
    private void showSelectCouponDia() {
        if (mSelectMealModel == null){
            ToastUtil.showToast("获取失败，请稍后重试");
            return;
        }
        //优惠券集合
        List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> couponList = mMapCouponData.get(String.valueOf(mSelectMealModel.getId()));
        if (couponList == null){
            couponList = new ArrayList<>();
        }
        SelectCouponDialog selectCouponDialog = new SelectCouponDialog(this, couponList).builder();
        selectCouponDialog.setOnPopupItemClickListener(new SelectCouponDialog.OnPopupItemClickListener() {
            @Override
            public void onPopUpItemClick(CouponByPriceModel.DatabeanX.AppGiftListbean.Databean couponBean) {
                mSelectCouponModel = couponBean;
                calculatePrice();
                LogUtil.d("__selectCouponDialog","onPopUpItemClick",String.valueOf(mSelectCouponModel));
            }
        });
        selectCouponDialog.show();
    }

    //查询订单的mRunnCheckOrder
    private Runnable mRunCheckOrder = new Runnable() {
        @Override
        public void run() {
            LogUtil.e("__mRunCheckOrder","run",isFinishing() +  "");
                if (isFinishing()){

                }else {
                    //查询微信支付订单
                    if (!mMapWechatpayQrCodeModel.isEmpty()){
                        Iterator it = mMapWechatpayQrCodeModel.keySet().iterator();
                        while (it.hasNext()) {
                            String key = it.next().toString();
                            LogUtil.e("__mRunCheckOrder","wechat","__" + key);
                            QrCodeOrderModel payQrCodeModel = mMapWechatpayQrCodeModel.get(key);
                            mHttpHelper.getOrderStatus(VipActivity3.this,String.valueOf(payQrCodeModel.getData().getOrder_id()));
                        }
                    }
                    //查询支付宝支付订单
                    if (!mMapAlipayQrCodeModel.isEmpty()){
                        Iterator it = mMapAlipayQrCodeModel.keySet().iterator();
                        while (it.hasNext()) {
                            String key = it.next().toString();
                            LogUtil.e("__mRunCheckOrder","ali","__" + key);
                            QrCodeOrderModel payQrCodeModel = mMapAlipayQrCodeModel.get(key);
                            mHttpHelper.getOrderStatus(VipActivity3.this,String.valueOf(payQrCodeModel.getData().getOrder_id()));
                        }
                    }
                    delayHandler.postDelayed(this,5000);
                }
        }
    };
    
    //二维码
    private PayQrCodeDia mPayQrCodeDia;
    /**
     * 展示支付dia
     */
    private void showQrCodeDia(){
        if (mPayQrCodeDia == null){
            mPayQrCodeDia = new PayQrCodeDia(this).builder();
            mPayQrCodeDia.setHttpHelper(mHttpHelper);
//            delayHandler.postDelayed(mRunCheckOrder,5000);
        }
        mPayQrCodeDia.setOnPayWaySelectedListener(new PayQrCodeDia.OnPayWaySelectedListener() {
            @Override
            public void onPayWaySelected(int pay_way) {
                //当dia需要二维码，其二维码model为空的时候，则会调用这个方法
                String key = getCurrentKey();
                QrCodeOrderModel model;
                switch (pay_way){
                    case HttpHelper.PY_PAY_TYPE_WECHAT:
                        model = mMapWechatpayQrCodeModel.get(key);
                        if (model == null){
                            getQrCode(HttpHelper.PY_PAY_TYPE_WECHAT);
                        }else {
                            mPayQrCodeDia.setWechatPayQrCodeModel(model);
                        }
                        break;
                    case HttpHelper.PY_PAY_TYPE_ALIPAY:
                        model = mMapAlipayQrCodeModel.get(key);
                        if (model == null){
                            getQrCode(HttpHelper.PY_PAY_TYPE_ALIPAY);
                        }else {
                            mPayQrCodeDia.setAlipayQrCodeModel(model);
                        }
                        break;

                }
            }
        });
        mPayQrCodeDia.setPrice(String.valueOf(mPayPrice));
        mPayQrCodeDia.setMealName(mSelectMealModel.getSet_meal_name());
        mPayQrCodeDia.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //模拟器测试时键盘中的的Enter键，模拟ok键（推荐TV开发中使用蓝叠模拟器）
            case KeyEvent.KEYCODE_ENTER:
                LogUtil.e("__onKeyDown_vip", "Enter键");
                break;
            case KeyEvent.KEYCODE_BACK:
                finish();
                LogUtil.e("__onKeyDown_vip", "返回键");
//                return false;
//                return true;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                LogUtil.e("__onKeyDown", "中间键");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                LogUtil.e("__onKeyDown左", "左方向键" + "FOCUS_ON:");
                if (mFocusPosition == FOCUS_ON_CHANGE_COUPON || mFocusPosition == FOCUS_ON_PAY_NOW
                        || mFocusPosition == FOCUS_ON_BACK ){
                        try {
                            mRvMeal.getChildAt(mMealRealPosition).requestFocus();
                        }catch (Exception e){

                        }
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                LogUtil.e("__onKeyDown右", "右方向键" + "FOCUS_ON:");
                if (mFocusPosition == FOCUS_ON_RV){
//                    mTvChangeCoupon.requestFocus();
                    mTvPayNow.requestFocus();
                    return true;
                }else if (mFocusPosition == FOCUS_ON_CHANGE_COUPON || mFocusPosition == FOCUS_ON_PAY_NOW){
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                LogUtil.e("__onKeyDown上", "上方向键");
//                if (mFocusPosition == FOCUS_ON_PAY_NOW){
//                    if (mSelectMealModel != null){
//                        String key = String.valueOf(mSelectMealModel.getId());
//                        List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> list = mMapCouponData.get(key);
//                        if (list == null){
//                            mTvBack.requestFocus();
//                            return true;
//                        }
//                    }
//                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (mFocusPosition == FOCUS_ON_BACK){
                    if (mSelectMealModel != null) {
                        String key = String.valueOf(mSelectMealModel.getId());
                        List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> list = mMapCouponData.get(key);
                        if (list == null) {
                            mTvPayNow.requestFocus();
                            return true;
                        }
                    }
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        recodeVisit(IHttp.VISIT_PAY);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType) {
            case HttpHelper.PY_HTTP_GET_MEAL://产品套餐

                MealModel mealModel = (MealModel) msg.obj;

                if (mealModel != null && mealModel.getData() != null && mealModel.getData().getData() != null
                        && mealModel.getData().getData().size() != 0) {
                    mMealData = mealModel.getData().getData();
                    initMealData();
                }
                break;
            case HttpHelper.PY_HTTP_GET_PAY_QR_CODE://创建订单号和二维码
                QrCodeOrderModel payQrCodeModel = (QrCodeOrderModel) msg.obj;
                int pay_type = payQrCodeModel.getData().getPay_type();
                order_no = payQrCodeModel.getData().getOrder_no();
                String key = String.valueOf(msg.what);
                order_no = payQrCodeModel.getData().getOrder_no();//获取订单号
                LogUtil.e("ssssssssssssssorder_no",order_no);
                if (pay_type == 0){//微信支付
                    mMapWechatpayQrCodeModel.put(key,payQrCodeModel);
                }else if (pay_type == 1){//支付宝支付
                    mMapAlipayQrCodeModel.put(key,payQrCodeModel);
                    //                    showQrCodeDia();//格灵支付弹窗
                }else if (pay_type== 100){
                    if (mCBisContract.isChecked()==true){
                        isContract = "1";
                    }else{
                        isContract = "0";
                    }

                    IntentsUtlis.DBshowSDK(this,Pprice,"测试11",order_no,isContract);//当贝支付

                }

                if (mPayQrCodeDia != null && mPayQrCodeDia.getDialog().isShowing()){
                    setQrCodeDiaData();
                }
                break;


            case HttpHelper.PY_HTTP_GET_MY_COUPON_LIST_SORT_BY_PRICE:
                LogUtil.e("__json1111",msg.toString());
                CouponByPriceModel couponModel = (CouponByPriceModel) msg.obj;

                int mealId = msg.what;
                if (couponModel != null && couponModel.getData() != null
                        && couponModel.getData().getAppGiftList() != null
                        && couponModel.getData().getAppGiftList().getData() != null
                        && couponModel.getData().getAppGiftList().getData().size() != 0) {
                    arrangeCoupon(mealId,couponModel);
                } else {
                    LogUtil.e("__ssss","获取优惠券成功，，，但是为空");
                    //当访问成功并且优惠券为空，也算做没有合适的优惠券
                    mMapCouponData.put(String.valueOf(mealId),null);
                    isCouponExist();
                }
                break;
            case HttpHelper.PY_HTTP_START:
                getOtherSuccess(msg);
                LogUtil.e("______msg",msg+"");
                break;
            case HttpHelper.PY_HTTP_GET_ORDER_STATUS:
                //order_status订单状态0待付款，1已付款，2订单失效
                OrderStatusModel orderStatusModel = (OrderStatusModel) msg.obj;
                int order_status = orderStatusModel.getData().getOrder_status();
                if (order_status == 1){
                    //已付款
                    showPaySuccessDia();
                }else if (order_status == 2){
                    //如果订单状态是失效的，则将二维码Model删除，前面的逻辑会自动去后台获取新的二维码放进去
                    removeQrCodeModel(orderStatusModel);
                }
                break;
            case HttpHelper.PY_HTTP_GET_USER_INFO:
                UserInfoModel userInfoModel = (UserInfoModel) msg.obj;
                UserManager.setUserInfo(userInfoModel);
                mTvVipInfo.setVisibility(View.VISIBLE);
                mTvVipInfo.setText("您已是VIP，" + userInfoModel.getData().getVip_time() + "到期");
                break;
        }
    }

    /**
     * 清除二维码对象
     */
    private void removeQrCodeModel(OrderStatusModel orderStatusModel){
        //查询微信支付订单
        if (!mMapWechatpayQrCodeModel.isEmpty()){
            Iterator it = mMapWechatpayQrCodeModel.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                LogUtil.e("__mRunCheckOrder","wechat","__" + key);
                QrCodeOrderModel payQrCodeModel = mMapWechatpayQrCodeModel.get(key);
                //二维码对象的订单id和订单号
                int order_id_qr_code = payQrCodeModel.getData().getOrder_id();
                String order_no_qr_code = payQrCodeModel.getData().getOrder_no();
                //订单状态的订单id和订单号
                int order_id_order = payQrCodeModel.getData().getOrder_id();
                String order_no_order = payQrCodeModel.getData().getOrder_no();
                //订单id和订单号都相同，则表示是同一个订单，将其对象删除
                if (order_id_qr_code == order_id_order && order_no_qr_code.equals(order_no_order)){
                    mMapWechatpayQrCodeModel.remove(key);
                }
            }
        }
        //查询支付宝支付订单
        if (!mMapAlipayQrCodeModel.isEmpty()){
            Iterator it = mMapAlipayQrCodeModel.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                LogUtil.e("__mRunCheckOrder","ali","__" + key);
                QrCodeOrderModel payQrCodeModel = mMapAlipayQrCodeModel.get(key);
                //二维码对象的订单id和订单号
                int order_id_qr_code = payQrCodeModel.getData().getOrder_id();
                String order_no_qr_code = payQrCodeModel.getData().getOrder_no();
                //订单状态的订单id和订单号
                int order_id_order = payQrCodeModel.getData().getOrder_id();
                String order_no_order = payQrCodeModel.getData().getOrder_no();
                //订单id和订单号都相同，则表示是同一个订单，将其对象删除
                if (order_id_qr_code == order_id_order && order_no_qr_code.equals(order_no_order)){
                    mMapWechatpayQrCodeModel.remove(key);
                }
            }
        }
        //为避免用户停留在二维码界面，界面每隔一段时间刷新二维码，造成大量订单，此方法先备注
//        if (mPayQrCodeDia.getDialog().isShowing()){
//            mPayQrCodeDia.clearData();
//        }
    }

    private CountDownTimer mCountDownTimer;
    private PaySuccessDialog mPaySuccessDia;
    private void showPaySuccessDia(){
        if (mPaySuccessDia == null){
            mPaySuccessDia = new PaySuccessDialog().builder(this,"")
                    .setClickListener(new PaySuccessDialog.ClickListener() {
                        @Override
                        public void onConfirmClick() {
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
        }
        if (!mPaySuccessDia.getDialog().isShowing()){
            mPaySuccessDia.show();
        }
        startCountDownTimer(3000);
    }

    /**
     * @param time 计时器时间，单位毫秒
     */
    private void startCountDownTimer(long time) {
        // +50是为了应对CountDownTimer的毫秒误差bug
        mCountDownTimer = new CountDownTimer(time + 50, 1000) {
            public void onTick(long millisUntilFinished) {
                if(mPaySuccessDia!=null){
                    mPaySuccessDia.setTvConfirmText("返回" + millisUntilFinished/1000);
                }
            }

            public void onFinish() {
                if(mPaySuccessDia!=null){
                    mPaySuccessDia.setTvConfirmText("返回0");
                    mPaySuccessDia.cancle();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }.start();
    }
    /**
     *
     * 获取当前选中的套餐和优惠券拼接起来的key
     * @return
     */
    private String getCurrentKey(){
        //拼接获取map的key
        String key = String.valueOf(mSelectMealModel.getId());
        if (mSelectCouponModel != null){
            key = key + mSelectCouponModel.getId();
        }else {
            key = key + "0";
        }
        return key;
    }
    /**
     * 设置qrCode数据
     */
    private void setQrCodeDiaData() {
        String key = getCurrentKey();
        QrCodeOrderModel modelWechat = mMapWechatpayQrCodeModel.get(key);
        QrCodeOrderModel modelAlipy = mMapAlipayQrCodeModel.get(key);
        //设置数据给dia
        mPayQrCodeDia.setAlipayQrCodeModel(modelAlipy);
        mPayQrCodeDia.setWechatPayQrCodeModel(modelWechat);
//       mWechatPayQrCodeModel
//                mPayQrCodeDia
    }

    /**
     * 整理优惠券
     */
    private void arrangeCoupon(int mealId,CouponByPriceModel couponModel) {
        LogUtil.e("__ssss","arrangeCoupon");
        //原始数据
        List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> oriList = couponModel.getData().getAppGiftList().getData();
        //整理之后的新数据
        List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> newList = new ArrayList<>();
        for (int i = 0; i < oriList.size(); i++) {
            CouponByPriceModel.DatabeanX.AppGiftListbean.Databean bean = oriList.get(i);
//            status等于0时，表示该优惠券不能用于当前套餐,status等于1时，表示当前套餐能用该优惠券
            int status = bean.getStatus();
            if (status == 1){
                newList.add(bean);
            }
        }
        if (newList.size() > 0){
            mMapCouponData.put(String.valueOf(mealId),newList);
        }else {
            mMapCouponData.put(String.valueOf(mealId),null);
        }
        isCouponExist();
    }

    //选中的套餐
    private MealModel.DatabeanX.Databean mSelectMealModel;
    private int mMealRealPosition;//价格选中的位置,复用之后的实际位置
    /**
     * 初始化套餐数据
     */
    private void initMealData() {
        mRvAdapterMeal3 = new RvAdapterMeal3(mMealData,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvAdapterMeal3.setOnItemStateChangeListener(new RvAdapterMeal3.OnItemStateChangeListener() {
            @Override
            public void onFocusGetted(int position) {
                mFocusPosition = FOCUS_ON_RV;
                mSelectMealModel = mMealData.get(position);
                //滚动结束之后，获取有焦点的item
                mRvMeal.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        for (int i = 0; i < mRvMeal.getChildCount(); i++) {
//                            LogUtil.e("__onFocusGetted_onGlobalLayout_hasFocus__" + i,mRvMeal.getChildAt(i).hasFocus() + "__");
                            if (mRvMeal.getChildAt(i).hasFocus()) {
                                //滚动完成之后，获得复用之后实际position
                                mMealRealPosition = i;
                                isCouponExist();//优惠券是否存在
//                                LogUtil.e("__onFocusGetted_onScrolled_hasFocus", mMealRealPosition + "__");
                            }
                        }
                    }
                });
                //如果焦点从其他控件转到rv，不会滚动，此时直接获取有焦点的item
                for (int i = 0; i < mRvMeal.getChildCount(); i++) {
                    if (mRvMeal.getChildAt(i).hasFocus()) {
                        //获得复用之后实际position
                        mMealRealPosition = i;
                        isCouponExist();//优惠券是否存在
                        LogUtil.e("__onFocusGetted_onScrolled_hasFocus", mMealRealPosition + "__");
                    }
                }
            }

            @Override
            public void onItemClick(int position) {
//                mWechatPayQrCodeModel = null;
//                mAlipayQrCodeModel = null;
//                if (mSelectMealModel != null){
//                    showQrCodeDia();
//                    getQrCode(HttpHelper.PY_PAY_TYPE_WECHAT);
//                }else {
//                    ToastUtil.showToast("没有套餐数据，请稍后重试");
//                }
            }
        });
        mRvMeal.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                try {
                    mRvMeal.getChildAt(0).requestFocus();
                    //千万记得移除监听，不然无法移动
                    mRvMeal.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }catch (Exception e){

                }
            }
        });
        mRvMeal.setLayoutManager(manager);
        mRvMeal.setAdapter(mRvAdapterMeal3);
//        mRvMeal.getLayoutManager().smoothScrollToPosition(mRvMeal, null, mRvAdapterMeal3.getItemCount() - 1);
    }
    boolean isExist;
    List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> couponList = null;
    /**
     * 当选中价格时，先判断优惠券是否已经获取过，如果没有获取过，则获取
     */
    private void isCouponExist() {
        LogUtil.e("__ssss","isCouponExist");
        mSelectCouponModel = null;//先置空，计算之后，有能用的优惠券再设置
        Iterator it = mMapCouponData.keySet().iterator();
        isExist = false;
        while (it.hasNext()) {
            String key = it.next().toString();
            LogUtil.e("__ssss","key",key);
            LogUtil.e("__ssss","key",mSelectMealModel.getId() + "");
            if (String.valueOf(mSelectMealModel.getId()).equals(key)){
                isExist = true;
                couponList = mMapCouponData.get(key);
            }
        }
        LogUtil.e("__ssss","isExist",isExist + "");
        if (isExist){//已经获取了这个套餐可以使用的优惠券,无需再后台获取
            if (couponList != null){
                mSelectCouponModel = couponList.get(0);
            }else {
                mSelectCouponModel = null;
            }
        }else {//未获取这个套餐可以使用的优惠券,后台获取
            //Discount为0时，非限时折扣价，为1时是限时折扣价
            LogUtil.e("__ssss","getDiscount()== 0",String.valueOf(mSelectMealModel.getDiscount()== 0));
            if (mSelectMealModel.getDiscount() == 0){
                mHttpHelper.getMyCouponListSortByPrice(this,String.valueOf(mSelectMealModel.getPrice()),mSelectMealModel.getId());
            }else {
                mHttpHelper.getMyCouponListSortByPrice(this,String.valueOf(mSelectMealModel.getDiscount_price()),mSelectMealModel.getId());
            }
        }
        calculatePrice();
    }

    //实际需要支付的价格
    private Double mPayPrice;
    /**
     * 计算价格
     */
    private void calculatePrice(){
        LogUtil.e("__ssss","calculatePrice");
        if (mSelectCouponModel == null){
            //套餐价格
            double price;
            //为0时，非限时折扣价，为1时,是限时折扣价
            if (mSelectMealModel.getDiscount() == 1){
                BigDecimal b2 = new BigDecimal(mSelectMealModel.getDiscount_price());
                price = b2.doubleValue();
//                price = Double.valueOf(mSelectMealModel.getDiscount_price());
            }else {
                BigDecimal b2 = new BigDecimal(mSelectMealModel.getPrice());
                price = b2.doubleValue();
//                price = Double.valueOf(mSelectMealModel.getPrice());
            }
            mPayPrice = price;
            if (isExist){//已经获取了这个套餐可以使用的优惠券,无需再后台获取
                if (couponList != null){
                    LogUtil.e("__ssss","couponList != null");
//                    mTvCouponValue.setTextSize(GroceryStoreUtils.dip2px(getResources().getDimension(R.dimen.sw_27dp)));
                    mTvCouponRmbSign.setVisibility(View.VISIBLE);
                    mTvCouponValue.setText(String.valueOf(0));
                }else {
                    LogUtil.e("__ssss","couponList == null");
//                    mTvCouponValue.setTextSize(GroceryStoreUtils.dip2px(getResources().getDimension(R.dimen.sw_27dp)));
                    mTvCouponRmbSign.setVisibility(View.INVISIBLE);
                    mTvCouponValue.setText("暂无");
                }
            }
        }else {//使用优惠券
            //套餐价格
            double price;
            //为0时，非限时折扣价，为1时,是限时折扣价
            if (mSelectMealModel.getDiscount() == 1){
                BigDecimal b2 = new BigDecimal(mSelectMealModel.getDiscount_price());
                price = b2.doubleValue();
//                price = Double.valueOf(mSelectMealModel.getDiscount_price());
            }else {
                BigDecimal b2 = new BigDecimal(mSelectMealModel.getPrice());
                price = b2.doubleValue();
//                price = Double.valueOf(mSelectMealModel.getPrice());
            }
            //优惠券价值
            double couponValue = mSelectCouponModel.getCouponCurrency();
            //价格差距,减去优惠券之后的价格
            BigDecimal b1 = new BigDecimal(Double.toString(couponValue));
            BigDecimal b2 = new BigDecimal(Double.toString(price));
            double diff = b2.subtract(b1).doubleValue();
            if (diff <= 0){
                mPayPrice = 0.0;
            }else {
                mPayPrice = diff;
            }
//            mTvCouponValue.setTextSize(GroceryStoreUtils.dip2px(getResources().getDimension(R.dimen.sw_42dp)));
            mTvCouponRmbSign.setVisibility(View.VISIBLE);
            mTvCouponValue.setText(String.valueOf(couponValue));
        }
        Pprice = String.valueOf(mPayPrice);
        mTvPayPrice.setText(String.valueOf(mPayPrice));

    }
    @Override
    public void onHttpError(int reqType, String error) {

    }

    @Override
    public void onTokenError(int reqType, String error, String errorCode) {

    }

    /**
     * 加载logo
     */
    private void loadLogo(){
        if (MainApp.getInstance().getOhterModel() == null
                || MainApp.getInstance().getOhterModel().getData() == null) {
            //如果没有logo的Url，就到后台获取
            mHttpHelper.getStart(this);
        } else {//如果有logo的Url，就直接加载
            GroceryStoreUtils.GlideImag(VipActivity3.this, MainApp.getInstance().getOhterModel().getData().getLogo(), mIvLogo);
        }
    }
    /**
     * 成功获取到“其他”数据
     * @param msg
     */
    private void getOtherSuccess(Message msg){
        try {
//                Gson gson = new Gson();
//                OhterModel1 ohterModel = gson.fromJson(data, OhterModel1.class);
            OhterModel1 ohterModel = (OhterModel1) msg.obj;
            MainApp.getInstance().setOhterModel(ohterModel);
            String norColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color();
            String focColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color_focus();
            String selColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color_selected();
            RvAdapterChildNav.mBgNorColor = MainApp.getInstance().getOhterModel().getData().getButton_color();
            RvAdapterChildNav.mBgFocColor = MainApp.getInstance().getOhterModel().getData().getButton_color_focus();
            RvAdapterChildNav.mTextNorColor = norColor;
            RvAdapterChildNav.mTextFocColor = focColor;
            RvAdapterChildNav.mTextSelColor = selColor;
            RvAdapterMainNav.mNormalTextColor = norColor;
            RvAdapterMainNav.mFocusTextColor = focColor;
            RvAdapterMainNav.mSelTextColor = selColor;
            RvAdapterMainNav.mFocBgColor = MainApp.getInstance().getOhterModel().getData().getButton_color_focus();
            GroceryStoreUtils.GlideImag(VipActivity3.this, MainApp.getInstance().getOhterModel().getData().getLogo(), mIvLogo);
        } catch (Exception e) {
        }
    }

    @Override
    public void onHttpErrorMsg(int reqType, Message message, String error) {
        switch (reqType) {
            case HttpHelper.PY_HTTP_GET_MEAL://产品套餐
                ToastUtil.showToast(error);
                break;
            case HttpHelper.PY_HTTP_GET_MY_COUPON:
                break;
            case HttpHelper.PY_HTTP_GET_MY_COUPON_LIST_SORT_BY_PRICE:
                int errorCode = message.arg1;
                if (errorCode == BaseHelper.ERROR_NOT_COUPON){
//                    没有合适的优惠券
                    int mealId = message.what;
                    //这个套餐没有合适的优惠券，就放null进去
                    mMapCouponData.put(String.valueOf(mealId),null);
                    LogUtil.e("__ssss","获取优惠券失败，onHttpErrorMsg");
                    isCouponExist();
                }else {
                    //获取优惠券失败则把选中优惠券变成Null
                    mSelectCouponModel = null;
                    ToastUtil.showToast(error);
                }
                break;
        }
    }
}