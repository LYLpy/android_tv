package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.PersonalInformationBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.DialogActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MeActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.event.EventSignUpdate;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/****
 *  个人信息页面
 */

public class PersonalInformationFragment extends BaseFragment {
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.fragment_personal_information_replace_phone)
    LinearLayout vButtonReplacePhone;
    @BindView(R.id.fragment_personal_information_replace_wx)
    LinearLayout vButtonReplaceWx;
    @BindView(R.id.fragment_presonal_information_out_or_sign_text)
    TextView mTextOutOrSign;
    @BindView(R.id.fragment_presonal_information_out_or_sign)
    LinearLayout mButtonOutOrSign;
    @BindView(R.id.fragment_personal_information_img_head_portrait)
    ImageView vImgHeadPortrait;
    @BindView(R.id.fragment_personal_information_text_name)
    TextView vTextUserName;
    @BindView(R.id.fragment_personal_information_text_view_date)
    TextView vTextVipTime;
    @BindView(R.id.fragment_presonal_information_text_wx_id)
    TextView vTextWxId;
    @BindView(R.id.fragment_presonal_information_text_phone)
    TextView vTextPhone;
    private String mHeadimgurl,mPhone,mWechatnickname,mVipTime,mOpenid;
    private int mIsVip;
    @Override
    protected void initData() {
        starData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_personal_information;
    }

    private void starData(){
        XHttpUtils.getHttpPersonalinformationJson(new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("--getHttpPersonalinformationJson_json-json"+json);
                PersonalInformationBean personalInformationBean = PersonalInformationBean.gsonBean(json);
                mHeadimgurl =  personalInformationBean.getData().getHeadimgurl();//头像
                mWechatnickname=personalInformationBean.getData().getWechatnickname();//昵称
                mVipTime=personalInformationBean.getData().getVip_time();//vip时间
                mPhone= personalInformationBean.getData().getPhone();//手机号
                mOpenid=personalInformationBean.getData().getOpenid();//微信ID
                mIsVip=personalInformationBean.getData().getIsVip();//是否是vip
                starView(mHeadimgurl,mWechatnickname,mVipTime,mPhone,mOpenid,mIsVip);
            }

            @Override
            public void onFailed(String  json) {

            }
        });
    }

    private void outData(){
        XHttpUtils.postHttpUserOutJson(new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                UserManager.setToken("");
                starView(null,null,null,null,null,0);
//                EventBus.getDefault().postSticky(new EventSignUpdate("NOT_USER","已退出登录"));
                Toast.makeText(getActivity(), "您已经退出", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

            @Override
            public void onFailed(String  json) {

            }
        });
    }
    private void starView(String headimgurl,String wechatnickname,String vipTime,String phone,String openid,int isVip){
        //初始化 登录 按钮的状态
        isTextSignOrOut();
        //添加头像
        GroceryStoreUtils.GlideImag(getActivity(),headimgurl,vImgHeadPortrait);
        //用户昵称
        if (wechatnickname ==null ){
            vTextUserName.setText("未设置昵称");
        }else {
            vTextUserName.setText(wechatnickname);
        }
        //VIP 时间
        if (isVip ==0){
            vTextVipTime.setText("您还不是VIP");
        }else {
            vTextVipTime.setText("VIP会员"+vipTime+"到期");
        }
        //微信ID
        if (openid ==null ){
            vTextWxId.setText("微信：未绑定");
            vButtonReplaceWx.setFocusable(true);
        }else {
            vTextWxId.setText("微信："+openid);
            vButtonReplaceWx.setBackgroundResource(R.drawable.shape_selector_no);
            vButtonReplaceWx.setFocusable(false);
        }
        //手机号
        if (phone ==null ){
            vTextPhone.setText("手机：未绑定");
        }else {
            vTextPhone.setText("手机："+phone);
        }
    }
    @Override
    protected void initView(View view) {
        //初始化点击事件
        mButtonOutOrSign.setOnClickListener(this);
        vButtonReturn.setOnClickListener(this);
        vButtonReplacePhone.setOnClickListener(this);
        //焦点处理
        initViewListener();

    }
    private void initViewListener() {
        //返回按钮
        vButtonReturn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT){
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                    }
                }
                return false;
            }
        });
        vButtonReturn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    float scale = 1.08f;
                    ScaleUtil.scale(vButtonReturn,scale);
//                    mAdapterFocusPosition = -1;
                }else {
                    float scale = 1f;
                    ScaleUtil.scale(vButtonReturn,scale);
                }
            }
        });
    }

    @Override
    public View getFirstView() {
        return null;
    }

    @Override
    public void setData(Object data) {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_presonal_information_out_or_sign://登录 and 退出按钮
                if (UserManager.isSignTokenNULL()){
                    // 退出
                    outData();
                }else {
//                    登录
                    //UserManager.isSignTokenIntentActivity(getActivity());//跳转到登录页面
                }
                break;
            case R.id.fragment_personal_information_replace_phone:// 更换手机号
                DialogActivity.starDialogReplacePhoneActivity(getActivity(),2,mPhone);

                break;
            case R.id.view_titile_me_fragment_but_return:
                getActivity().finish();
                break;
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        starData();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdatePhone(EventSignUpdate eventSignUpdate){
        if (eventSignUpdate.getTAG()=="update_phone"){
            vTextPhone.setText("手机："+UserManager.getUserphone());
        }
    }

    //0 = 退出  1= 登录
    private void isTextSignOrOut(){
        if (UserManager.isSignTokenNULL()==true){
            mTextOutOrSign.setText("退出");
        }else {
            mTextOutOrSign.setText("登录");
        }
    }


}
