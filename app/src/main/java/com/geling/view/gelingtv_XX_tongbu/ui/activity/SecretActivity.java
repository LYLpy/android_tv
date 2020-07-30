package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.ProtocolModel;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GetAppMessUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import butterknife.BindView;
/**
 * 用户协议
 * */
public class SecretActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.iv_not_data)
    ImageView mIvNotData;
    String channel ;//获取apk渠道名

    @Override
    protected void initData() {


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_secret;
    }

    @Override
    protected void initView() {
        channel = GetAppMessUtils.getAppMetaData(this,"UMENG_CHANNEL");
        XHttpUtils.getSecrecy(0, "", new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                mIvNotData.setVisibility(View.GONE);
                LogUtil.e("___json_ssss:"+json);
                ProtocolModel protocolModel = ProtocolModel.gonsBean(json);
                mTvContent.setText(Html.fromHtml(protocolModel.getData().getProtocol()));
            }

            @Override
            public void onFailed(String json) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}