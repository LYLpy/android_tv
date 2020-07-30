package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/1------更新------
 * 课程禁用dialog
 */

public class CourseForbiddenDialog {

    private Context mContext;
    private Dialog dialog;
    private TextView tvConfirm;
    /**
     * @param context 上下文
     * @param content  内容
     * @return
     */
    public CourseForbiddenDialog builder(Context context,String content) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_course_forbidden, null);
        tvConfirm = view.findViewById(R.id.tv_confirm);
        TextView tvContent = view.findViewById(R.id.tv_content);
        if (!TextUtils.isEmpty(content)){
            tvContent.setText(content);
        }
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null){
                    mClickListener.onBtnClick();
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

    public void show() {
        if(dialog!=null)dialog.show();
    }

    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }

    public CourseForbiddenDialog setConfirmText(String text) {
        if(tvConfirm != null){
            tvConfirm.setText(String.valueOf(text));
        }
        return this;
    }
    public Dialog getDialog() {
        return dialog;
    }

    public CourseForbiddenDialog setClickListener(OnBtnClickListener clickListener) {
        mClickListener = clickListener;
        return this;
    }
    private OnBtnClickListener mClickListener;
    public interface OnBtnClickListener{
        void onBtnClick();
    }
}
