package com.geling.view.gelingtv_XX_tongbu.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    private View mLinearLayout;

    public CountDownTimerUtils(View maxLinearLayout, TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.mLinearLayout = maxLinearLayout;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mLinearLayout.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "s后重新获取");  //设置倒计时时间
    }

    @Override
    public void onFinish() {
        mTextView.setText("发送验证码");
        mLinearLayout.setClickable(true);//重新获得点击
    }


}
