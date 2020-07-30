package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/7------更新------
 * 课程dialog
 */

public class CourseIntroduceDialog {
    private Context mContext;
    private Dialog dialog;


    public CourseIntroduceDialog builder(Context context,String introduce) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_course_introduce, null);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        TextView tvIntroduce = view.findViewById(R.id.tv_introduce);
        tvIntroduce.setText(String.valueOf(introduce));

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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



    public Dialog getDialog() {
        return dialog;
    }
}
