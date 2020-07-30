package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/7------更新------
 * 课程dialog
 */

public class PaySuccessDialog {
    private Context mContext;
    private Dialog dialog;
    private ImageView mIvPaySuccess;
    private TextView mTvConfirm;

    /**
     * @param context
     * @param content
     * @return
     */
    public PaySuccessDialog builder(Context context, String content) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_pay_success, null);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        TextView tvIntroduce = view.findViewById(R.id.tv_content);
        mIvPaySuccess = view.findViewById(R.id.iv_pay_success);
        if (!TextUtils.isEmpty(content)){
            tvIntroduce.setText(String.valueOf(content));
        }
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null){
                    mClickListener.onConfirmClick();
                }
                cancle();
            }
        });
        dialog = new Dialog(mContext,R.style.sytle_my_dialog);
//        //设置alterdialog全屏
        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width,  height);

        dialog.setContentView(view, layoutParams);
//        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        return this;
    }

    private PaySuccessDialog setIvPaySuccessVisible(boolean isVisible){
        if (isVisible){
            mIvPaySuccess.setVisibility(View.VISIBLE);
        }else {
            mIvPaySuccess.setVisibility(View.GONE);
        }
        return this;
    }
    public void show() {
        if(dialog!=null)dialog.show();
    }

    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }

    public Dialog getDialog() {
        return dialog;
    }
    public void setTvConfirmText(String text){
        mTvConfirm.setText(text);
    }
    private ClickListener mClickListener;
    public interface ClickListener{
        void onConfirmClick();
    }
    public PaySuccessDialog setClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
        return this;
    }
}
