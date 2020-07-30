package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SignCodeUpdate;
import com.geling.view.gelingtv_XX_tongbu.ui.event.EventPlayReocrdMassge;
import com.geling.view.gelingtv_XX_tongbu.ui.event.EventSignUpdate;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SignInDataBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SignVerificationCodeBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ExitPageDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.KeyboardView;
import com.geling.view.gelingtv_XX_tongbu.ui.view.MaxLinearLayout;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.CountDownTimerUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogActivity extends Activity implements View.OnClickListener {
    private int typeDialog;
    //我的页面------兑换码子页面------兑换成功的弹出框
    @BindView(R.id.dialog_exchange_success_return)
    LinearLayout vDialogExchangeSuccessReturn;
    @BindView(R.id.fragment_me_exchange_dialog)
    LinearLayout layoutFragmentMeExchangeDialog;
    @BindView(R.id.fragment_me_exchange_dialog_text)
    TextView vDialogExchangNameText;
    @BindView(R.id.fragment_me_exchange_dialog_time)
    TextView vDialogExchangTimeText;

    //--我的页面-------个人信息------修改手机号
    @BindView(R.id.dialog_replace_phone_text_title)
    TextView vDialogReplacePhoneTitleText;
    @BindView(R.id.dialog_replace_phone_layout)
    LinearLayout layoutDialogReplacePhone;
    @BindView(R.id.dialog_replace_et_phone)
    TextView vDialogReplacePhoneEt;
    @BindView(R.id.dialog_replace_phone_verification_code)
    TextView vDialogReplacePhoneVerificationCodeEt;
    @BindView(R.id.dialog_replace_phone_verification_code_layout)
    LinearLayout vDialogReplacePhoneVerificationCodeEtLayout;
    @BindView(R.id.dialog_replace_phone_verification_code_button)
    LinearLayout vDialogReplacePhoneVerificationCodeButton;
    @BindView(R.id.dialog_replace_phone_verification_code_button_text)
    TextView vDialogReplacePhoneVerificationCodeButtonText;
    @BindView(R.id.dialog_replace_et_phone_layout)
    LinearLayout vDialogReplacePhoneEtLayout;
    @BindView(R.id.activity_list_key)
    KeyboardView activity_list_key;
    @BindView(R.id.activity_key_eixt)
    ImageView vImagEixt;

    private  SignCodeUpdate signCodeUpdate = new SignCodeUpdate();


    //我的页面-----兑换码---兑换成功
    public static void starExchangeDialogActivity(Activity activity,int typeDialog,String textName){
        Intent intent = new Intent(activity, DialogActivity.class);
        intent.putExtra("typeDialog",typeDialog);
        intent.putExtra("fragment_me_exchange_text",textName);
        activity.startActivity(intent);
    }

    //我的页面-----个人信息----更换手机号
    public static void starDialogReplacePhoneActivity(Activity activity,int typeDialog,String phone){
        Intent intent = new Intent(activity, DialogActivity.class);
        intent.putExtra("typeDialog",typeDialog);
        intent.putExtra("phone",phone);
        activity.startActivity(intent);
    }
    private String mModifyVerifyId,phone;
    private int phoneType=0;
    private CountDownTimerUtils mCountDownTimerUtils;
    private List<String> listKeyData = new ArrayList<>();
    private int mSelectionControl=0;//0= phone输入框   1= 验证码输入框


    /****
     *  1 = 我的页面------兑换码子页面------兑换成功的弹出框
     *  2=  我的页面-----个人信息----更换手机号
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        //选择展示哪一个 dialog
        initVisibilityView();
        //初始化点击事件
        initViewClick();
    }

    private void initVisibilityView() {
        Intent intent = getIntent();
        typeDialog=intent.getIntExtra("typeDialog",0);
        switch (typeDialog){
            case 1:
                initStarExchangeDialogActivity(intent);
                break;
            case 2:
                initStarDialogReplacePhoneActivity(intent);
                break;
        }
    }
    private String deleteEtView(TextView textView,String string){
        String s= string;
        if (s.length()>0){
            s = s.substring(0,s.length()-1);
            textView.setText(s);
        }
        return s;
    }

    String phoneA="";
    String code="";
    private void initStarDialogReplacePhoneActivity(Intent intent){
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
        listKeyData.add(11,"下一步");

        //进入页面 选择焦点

        activity_list_key.setSelection(0);
        vDialogReplacePhoneEtLayout.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);
        vDialogReplacePhoneVerificationCodeEtLayout.setBackgroundResource(R.drawable.layer_ac_sign_in_phone_et_bg_02);
        layoutDialogReplacePhone.setVisibility(View.VISIBLE);
        //初始化验证码倒计时

         phone=intent.getStringExtra("phone");
         phoneA = phone;
        vDialogReplacePhoneEt.setText(phoneA);
        //初始化键盘
        activity_list_key.getKeyBoardAdapter().setNewData(listKeyData);
        activity_list_key.getKeyBoardAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position)
                {
                    case 3://删除
                        if (mSelectionControl==0){
                            phoneA=deleteEtView(vDialogReplacePhoneEt,phoneA);
                        }else {
                            code=deleteEtView(vDialogReplacePhoneVerificationCodeEt,code);
                        }
                        break;
                    case 11://下一步，提交
                        if (listKeyData.get(position).equals("下一步")){

                            if (vDialogReplacePhoneVerificationCodeEt.getText().toString().trim().length()!=0) {
                                phoneType=1;
                                postModifyPhone();
                            }else {
                                Toast.makeText(DialogActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (listKeyData.get(position).equals("提交")){
                            phoneType=0;
                            if (vDialogReplacePhoneVerificationCodeEt.getText().toString().trim().length()!=0) {
                                postModifyPhone();
                            }else {
                                Toast.makeText(DialogActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default://输入
                        if (mSelectionControl==0){
                            if (vDialogReplacePhoneEt.getText().toString().length()<15){
//                                phoneA ="";
                                phoneA = phoneA+listKeyData.get(position);
                                vDialogReplacePhoneEt.setText(phoneA);
                                return;
                            }
                        }
                        if (mSelectionControl==1){
                            if (vDialogReplacePhoneVerificationCodeEt.getText().toString().length()<8){
                                code = code+listKeyData.get(position);
                                vDialogReplacePhoneVerificationCodeEt.setText(code);
                            }
                        }
                        break;
                }
            }
        });
        //点击 phone
        vDialogReplacePhoneEtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhone();
            }
        });
        // 验证码
        vDialogReplacePhoneVerificationCodeEtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCode();
            }
        });
        //退出
        vImagEixt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void choosePhone(){
        activity_list_key.setSelection(0);
        mSelectionControl=0;
        vDialogReplacePhoneEtLayout.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);

//        vDialogReplacePhoneVerificationCodeEtLayout.setBackgroundResource(R.drawable.layer_ac_sign_in_phone_et_bg_02);

    }
    private void chooseCode(){
        activity_list_key.setSelection(0);
        mSelectionControl=1;
//        vDialogReplacePhoneEtLayout.setBackgroundResource(R.drawable.layer_ac_sign_in_phone_et_bg_02);
        vDialogReplacePhoneVerificationCodeEtLayout.setBackgroundResource(R.drawable.shape_sign_et_blue_bg);
    }
    private void initStarExchangeDialogActivity(Intent intent){
        layoutFragmentMeExchangeDialog.setVisibility(View.VISIBLE);
        String textName=intent.getStringExtra("fragment_me_exchange_text");
        vDialogExchangNameText.setText("兑换内容:"+"   "+textName);
        vDialogExchangTimeText.setText("兑换时间:"+"   "+ GroceryStoreUtils.getSimpleDateFprmat("yyyy:MM:dd HH:mm:ss"));
    }
    private void initViewClick() {
//        我的页面------兑换码子页面------兑换成功的弹出框
        vDialogExchangeSuccessReturn.setOnClickListener(this);
//        我的页面-----个人信息----更换手机号
        vDialogReplacePhoneVerificationCodeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//          我的页面------兑换码子页面------兑换成功的弹出框
            case R.id.dialog_exchange_success_return://返回按钮
                finish();
                break;
//            我的页面-----个人信息----更换手机号
            case R.id.dialog_replace_phone_verification_code_button://请求 验证码

                    getVerificationCode();
                break;
        }
    }
    //获取验证码
    private void getVerificationCode() {
        XHttpUtils.postHttpGRModifyVerificationCodeJson(vDialogReplacePhoneEt.getText().toString(), new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                mCountDownTimerUtils = new CountDownTimerUtils(vDialogReplacePhoneVerificationCodeButton, vDialogReplacePhoneVerificationCodeButtonText, 60000, 1000);
                LogUtil.d("getModifyVerificationCodehttp" + json);
                chooseCode();
                SignVerificationCodeBean signVerificationCodeBean = SignVerificationCodeBean.gsonBean(json);
                mModifyVerifyId = signVerificationCodeBean.getData().getVerify_id();//用户请求发送验证码时返回的verify_id
                mCountDownTimerUtils.start();
            }
            @Override
            public void onFailed(String  json) {
                LogUtil.e("__json验证码发送失败",json);
                SignVerificationCodeBean signVerificationCodeBean = SignVerificationCodeBean.gsonBean(json);
                vDialogReplacePhoneVerificationCodeButtonText.setText("发送验证码发送失败:"+signVerificationCodeBean.getMsg());
                Toast.makeText(DialogActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //1=提交 要修改的 手机号    0 = 验证手机号
    private void postModifyPhone() {
        XHttpUtils.postHttpGRModifyPhoneJson(phoneType,vDialogReplacePhoneEt.getText().toString(),vDialogReplacePhoneVerificationCodeEt.getText().toString(),mModifyVerifyId, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("postHttpGRModifyPhoneJson_http" + json);
                if (phoneType==0){
                    Toast.makeText(DialogActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    phoneType = 1;
                    mCountDownTimerUtils.cancel();
                    mCountDownTimerUtils.onFinish();
                    vDialogReplacePhoneTitleText.setText("绑定新手机号");
                    activity_list_key.getKeyBoardAdapter().setData(11,"提交");
                    mModifyVerifyId="";
                    vDialogReplacePhoneEt.setText("");
                    vDialogReplacePhoneVerificationCodeEt.setText("");
                    choosePhone();
                }else {

                    Toast.makeText(DialogActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                     signCodeUpdate = SignCodeUpdate.gsonBean(json);
                    signCodeUpdate  = SignCodeUpdate.gsonBean(json);
                    String token= signCodeUpdate.getData().getUser().getToken();
                    String phone = signCodeUpdate.getData().getUser().getPhone();
                    UserManager.setUserphone(phone);
                    UserManager.setToken(token);//保存 Token
                    LogUtil.e("修改成功token",token);
                    EventBus.getDefault().postSticky(new EventSignUpdate("update_phone",phone));
                }
                ToastUtil.showToast("手机号码为"+ UserManager.getUserphone());
            }
            @Override
            public void onFailed(String  json) {
                Toast.makeText(DialogActivity.this, "该手机号码已存在", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimerUtils!=null){
            mCountDownTimerUtils.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
