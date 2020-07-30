package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.ExitPageModel;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/20------更新------
 * 清除收藏的dialog
 */

public class ClearCollectionDialog {

    private Context mContext;
    private Dialog dialog;
    private TextView mTvConfirm;
    private TextView mTvCancle;
    private TextView mTvTile;
    private boolean isFocusOnCancleBtn = true;
    private List<ExitPageModel.Databean.PageDatabean> mData;

    public ClearCollectionDialog builder(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_clear_collection, null);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mTvCancle = view.findViewById(R.id.tv_cancle);
        mTvTile = view.findViewById(R.id.tv_title);

        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onRightClick();
                }
                cancle();
            }
        });
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onLeftClick();
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
        if (isFocusOnCancleBtn){
            mTvCancle.requestFocus();
        }else {
            mTvConfirm.requestFocus();
        }
    }

    /**
     * @param focusOnCancleBtn
     * 是否默认取消按钮获取焦点，默认是
     */
    public void setFocusOnCancleBtnR(boolean focusOnCancleBtn) {
        isFocusOnCancleBtn = focusOnCancleBtn;
    }

    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }



    public Dialog getDialog() {
        return dialog;
    }


    public ClearCollectionDialog setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    private OnClickListener mOnClickListener;
    public interface OnClickListener{
        void onRightClick();
        void onLeftClick();
    }

    /**
     * 设置标题
     * @param title 标题
     * @return
     */
    public ClearCollectionDialog setTitle(String title){
        mTvTile.setText(title);
        return this;
    }

    /**
     * 设置标题字体尺寸
     * @param size
     * @return
     */
    public ClearCollectionDialog setTitleSize(float size){
        mTvTile.setTextSize(size);
        return this;
    }

    /**
     * 设置确定按钮文字，也就是左边按钮
     * @param confirmText
     * @return
     */
    public ClearCollectionDialog setLeftText(String confirmText){
        mTvConfirm.setText(confirmText);
        return this;
    }

    /**
     * 设置取消按钮文字，也就是右边按钮
     * @param text
     * @return
     */
    public ClearCollectionDialog setRightText(String text){
        mTvCancle.setText(text);
        return this;
    }
}
