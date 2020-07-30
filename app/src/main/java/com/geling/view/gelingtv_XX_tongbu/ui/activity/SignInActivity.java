package com.geling.view.gelingtv_XX_tongbu.ui.activity;
/**
 * 登录页面
 **/
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SignInDataBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SignVerificationCodeBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ColorButtonLayout;
import com.geling.view.gelingtv_XX_tongbu.ui.view.KeyboardView;
import com.geling.view.gelingtv_XX_tongbu.ui.view.MaxLinearLayout;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.CountDownTimerUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.tencent.mm.opensdk.diffdev.DiffDevOAuthFactory;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import okhttp3.Call;

public class SignInActivity extends BaseActivity implements OAuthListener {
    @BindView(R.id.activity_sign_in_return)
    LinearLayout activitySignInReturn;
    @BindView(R.id.dialog_sign_in_verification_code)
    TextView dialogSignInVerificationCode;
    @BindView(R.id.dialog_sign_in_verification_code_but)
    LinearLayout vLayoutSignCodeBut;
    @BindView(R.id.dialog_sign_in_verification_code_button)
    ColorButtonLayout dialogSignInVerificationCodeButton;
    @BindView(R.id.dialog_sign_in_phone)
    TextView dialogSignInPhone;
    @BindView(R.id.activity_sign_in_phone_layout_02)
    LinearLayout vLayoutSignPhoneBut;
    @BindView(R.id.dialog_sign_in_verification_code_text)
    TextView dialogSignInVerificationCodeText;
    @BindView(R.id.check_agreement)
    CheckBox checkAgreement;
    @BindView(R.id.btn_agreement_user)
    Button btn_agreement_user;
    @BindView(R.id.btn_agreement_Confidentiality)
    Button btn_Protocol;
    @BindView(R.id.activity_sign_in_phone_layout)
    LinearLayout vLayoutPhoneSign;
    @BindView(R.id.activity_sign_in_wx_qr_code)
    ImageView vImgQRCode;
    @BindView(R.id.activity_sign_in_bg)
    ImageView vImgActivityBg;
    @BindView(R.id.activity_sign_in_wx_qr_code_bg)
    ImageView vImgQRCodeBG;
    @BindView(R.id.activity_sign_in_wx_qr_code_text)
    TextView vTextWxQrCodeText;
    @BindView(R.id.activity_sign_in_list_key)
    KeyboardView vRecKeyList;
    private int nameActivity;//接收跳转到不同页面
    private String verify_id;//用户请求发送验证码时返回的verify_id
    private CountDownTimerUtils mCountDownTimerUtils;
    private List<String> listKeyData = new ArrayList<>();
    private int mSelectionControl=0;//0= phone输入框   1= 验证码输入框


    private String APPID;
    private String SECRET;
    private String nonceStr;
    private String timestamp;
    private String signature;
    private String ticket;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initData() {
        Intent intentName = getIntent();
            nameActivity =intentName.getIntExtra("name",0);
//        getWxToken();
        //键盘数据
        listKeyData.add(0,"1");
        listKeyData.add(1,"2");
        listKeyData.add(2,"3");
        listKeyData.add(3,"删除");
        listKeyData.add(4,"4");
        listKeyData.add(5,"5");
        listKeyData.add(6,"6");
        listKeyData.add(7,"0");
        listKeyData.add(8,"7");
        listKeyData.add(9,"8");
        listKeyData.add(10,"9");
        listKeyData.add(11,"登录");
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    @Override
    protected void initView() {
        // 第一开始 选中的状态
        vRecKeyList.setSelection(0);
        vLayoutSignPhoneBut.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);
        vLayoutSignCodeBut.setBackgroundResource(R.drawable.layer_ac_sign_in_phone_et_bg_02);

        //初始化验证码倒计时
        mCountDownTimerUtils = new CountDownTimerUtils(dialogSignInVerificationCodeButton, dialogSignInVerificationCodeText, 60000, 1000);
        //初始化点击事件
        activitySignInReturn.setOnClickListener(this);
        dialogSignInVerificationCodeButton.setOnClickListener(this);

        btn_Protocol.setOnClickListener(this);
        btn_agreement_user.setOnClickListener(this);
//        btn_agreement_user.getBackground().setAlpha(1);
//        btn_Protocol.getBackground().setAlpha(1);

        btn_agreement_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    btn_agreement_user.setBackground(getResources().getDrawable(R.drawable.bg_border11));
                }else{

                    btn_agreement_user.setBackground(getResources().getDrawable(R.drawable.bg_border11_b));

                }
            }
        });
        btn_Protocol.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    btn_Protocol.setBackground(getResources().getDrawable(R.drawable.bg_border11));
                }else{

                    btn_Protocol.setBackground(getResources().getDrawable(R.drawable.bg_border11_b));

                }
            }
        });
        vLayoutSignPhoneBut.setOnClickListener(this);
        vLayoutSignCodeBut.setOnClickListener(this);
        //初始化键盘 数据
        vRecKeyList.setData(listKeyData);
        //初始化 事件交互
        initEvent();

    }

    String phone="";
    String code="";

    private String deleteEtView(TextView textView,String string){
        String s= string;
        if (s.length()>0){
            s = s.substring(0,s.length()-1);
            textView.setText(s);
        }
        return s;
    }

    private void initEvent() {
        //item选中、点击监听
        vRecKeyList.getKeyBoardAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position)
                {
                    case 3://删除
                        if (mSelectionControl==0){
                            phone=deleteEtView(dialogSignInPhone,phone);
                        }else {
                            code=deleteEtView(dialogSignInVerificationCode,code);
                        }
                        break;
                    case 11://登录
                        if (dialogSignInPhone.length()<11){
                            Toast.makeText(SignInActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (dialogSignInVerificationCode.length()<4){
                            Toast.makeText(SignInActivity.this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(verify_id)){
                            Toast.makeText(SignInActivity.this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (checkAgreement.isChecked()){
                            postSignData(verify_id, "");
                        }else {
                            Toast.makeText(SignInActivity.this, "请阅读并勾选《用户协议》和《保密协议》", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default://输入
                        if (mSelectionControl==0){
                            if (dialogSignInPhone.getText().toString().length()<15){
                                phone = phone+listKeyData.get(position);
                                dialogSignInPhone.setText(phone);
                                return;
                            }
                        }
                        if (mSelectionControl==1){
                            if (dialogSignInVerificationCode.getText().toString().length()<8){
                                code = code+listKeyData.get(position);
                                dialogSignInVerificationCode.setText(code);
                            }
                        }
                        break;
                }
            }
        });

        vRecKeyList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                switch (direction){
                    case View.FOCUS_UP:
                        dialogSignInVerificationCodeButton.requestFocus();
                        break;
                    case View.FOCUS_DOWN:
                        checkAgreement.requestFocus();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sign_in_return://退出当前页面
                finish();
                break;
            case R.id.activity_sign_in_phone_layout_02://pohne 输入选中
                vRecKeyList.setSelection(0);
                mSelectionControl=0;
                vLayoutSignPhoneBut.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);
//                vLayoutSignCodeBut.setBackgroundResource(R.drawable.layer_ac_sign_in_phone_et_bg_02);
                break;
            case R.id.dialog_sign_in_verification_code_but://验证码 输入选中
                vRecKeyList.setSelection(0);
                mSelectionControl=1;
//                vLayoutSignPhoneBut.setBackgroundResource(R.drawable.layer_ac_sign_in_phone_et_bg_02);
                vLayoutSignCodeBut.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);
                break;

            case R.id.dialog_sign_in_verification_code_button: //发送验证码
                if (dialogSignInPhone.length()<11){
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                getVerificationCode();
                vRecKeyList.setSelection(0);
                mSelectionControl=1;
                vLayoutSignCodeBut.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);
                break;
            case R.id.btn_agreement_Confidentiality:
                Intent intent1 = new Intent(this,SecretActivity.class);
                startActivity(intent1);
                LogUtil.e("点击2");
                break;
            case R.id.btn_agreement_user:
                LogUtil.e("点击1");
                Intent intent = new Intent(this,ProtocolActivity.class);
                startActivity(intent);
                break;
        }
    }


    //获取验证码
    private void getVerificationCode() {
        XHttpUtils.getHttpSignVerificationCodeJson(dialogSignInPhone.getText().toString(), new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                mCountDownTimerUtils.start();
                LogUtil.d("sign_http" + json);
                SignVerificationCodeBean signVerificationCodeBean = SignVerificationCodeBean.gsonBean(json);
                verify_id = signVerificationCodeBean.getData().getVerify_id();//用户请求发送验证码时返回的verify_id
            }

            @Override
            public void onFailed(String  json) {
                dialogSignInVerificationCodeText.setText("重新获取");
                LogUtil.e("__json验证码发送失败",json);
                Toast.makeText(SignInActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //提交登录
    private void postSignData(String verify_id, String invitation_code) {
        XHttpUtils.postHttpSignDataJson( dialogSignInVerificationCode.getText().toString(), verify_id, invitation_code, dialogSignInPhone.getText().toString(), new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("sign_http" + json);
                SignInDataBean signInDataBean = SignInDataBean.gsonBean(json);
                if (signInDataBean.getSuccess().equals("success")){
                    Toast.makeText(SignInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    UserManager.setToken(signInDataBean.getData().getToken());//保存 Token
//                    Intent intent = new Intent(SignInActivity.this,MainActivity3.class);
                  if (nameActivity ==1){
                      Intent  intent = new Intent(SignInActivity.this,MeActivity.class);
                      startActivity(intent);
                    }else if(nameActivity==2){
                      Intent intent = new Intent(SignInActivity.this,VideoPlayActivity.class);
                      startActivity(intent);
                  }else if (nameActivity ==3){
                      Intent  intent= new Intent(SignInActivity.this,VipActivity3.class);
                      startActivity(intent);
                  }else if (nameActivity == 4){
                      Intent intent=new Intent(SignInActivity.this, MeActivity.class);
                      intent.putExtra("newPlayReocrd",4);
                      startActivity(intent);
                  }
                    finish();//登录成功退出登录
                }else {
                    Toast.makeText(SignInActivity.this, signInDataBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String  json) {
                Toast.makeText(SignInActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    protected synchronized void Destroy() {
        super.Destroy();
        mCountDownTimerUtils.cancel();

    }

    /**
     * auth之后返回的二维码接口
     *
     * @param qrcodeImgPath 废弃
     * @param imgBuf 二维码图片数据
     */
    @Override
    public void onAuthGotQrcode(String qrcodeImgPath, byte[] imgBuf) {

    }
    /**
     * 用户扫描二维码之后，回调改接口
     */
    @Override
    public void onQrcodeScanned() {

    }
    /**
     * 用户点击授权后，回调改接口
     */
    @Override
    public void onAuthFinish(OAuthErrCode oAuthErrCode, String s) {

    }
    private void getWxToken(){
        OkHttpUtils.get().url("https://api.weixin.qq.com/cgi-bin/token")
                .addParams("grant_type","client_credential")
                .addParams("appid",APPID)
                .addParams("secret",SECRET)
                .build()
                .buildCall(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("dawhdkawhjdakwj",response);

                    }
                });
    }


    private void getWxAccessToken(String access_token){
        OkHttpUtils.get().url("https://api.weixin.qq.com/cgi-bin/ticket/getticket")
                .addParams("access_token",access_token)
                .addParams("type","2")
                .build()
                .buildCall(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("dawhdkawhjdakwj",response);
//                        ticket=
                        getWxSign();
                    }
                });
    }

    private void getWxSign(){
        nonceStr = getRandomString(10);
        timestamp = String.valueOf(CommonUtils.getCurrentTimeStampInt());
        signature = String.format("appid=%s&noncestr=%s&sdk_ticket=%s&timestamp=%s", APPID, nonceStr, ticket, timestamp);

        IDiffDevOAuth  oauth = DiffDevOAuthFactory.getDiffDevOAuth();
        oauth.auth(APPID, //应用唯一标识
                "snsapi_userinfo",
                nonceStr,//随机字符串
                timestamp,
                signature, //签名，步骤四生成的签名
                this); // 授权完成回调接口（OAuthListener）
    }


}
