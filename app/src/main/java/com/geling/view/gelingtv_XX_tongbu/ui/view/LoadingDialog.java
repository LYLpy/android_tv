package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;


/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/18------更新------
 *
 * 等待的Dialog弹窗
 */


public class LoadingDialog {
    private Context context;
    private Dialog dialog;
    private Display display;
    private TextView mTipTextView;

    public LoadingDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public LoadingDialog builder() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局  
        // main.xml中的ImageView  
//        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        // 提示文字  
        mTipTextView = (TextView) v.findViewById(R.id.tipTextView);
        dialog = new Dialog(context, R.style.sytle_my_dialog);// 创建自定义样式dialog
        dialog.setCancelable(false);// 不可以用“返回键”取消  
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局 
        return this;
    }

    /**
     * 数据加载中……
     * 设置Message
     * @param msg
     * @return
     */
    public LoadingDialog setMsg(String msg) {
        if (TextUtils.isEmpty(msg)) {
//            mTipTextView.setText("");
            mTipTextView.setVisibility(View.GONE);
        } else {
            mTipTextView.setText(msg);
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     * @param cancel
     * @return
     */
    public LoadingDialog setCancelable(boolean cancel) {
        if (dialog!=null){
            dialog.setCancelable(cancel);
        }
        return this;
    }

    public void show() {
        if (dialog!=null){
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }
    public Dialog getDialog() {
        return dialog;
    }
}
