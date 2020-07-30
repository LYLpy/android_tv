package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.ExitPageModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/19------更新------
 * 退出页面dialog
 */

public class ExitPageDialogBackup {

    private Context mContext;
    private Dialog dialog;
    private TextView mTvExit;
    private TextView mTvSeeAgain;
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private ImageView mIv4;
    private ImageView mIvPageUp;
    private ImageView mIvPageDown;
    private List<ExitPageModel.Databean.PageDatabean> mData;

    public ExitPageDialogBackup builder(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_exit_page, null);
        mTvExit = view.findViewById(R.id.tv_exit);
        mTvSeeAgain = view.findViewById(R.id.tv_see_again);
        mIv1 = view.findViewById(R.id.iv_1);
        mIv2 = view.findViewById(R.id.iv_2);
        mIv3 = view.findViewById(R.id.iv_3);
        mIv4 = view.findViewById(R.id.iv_4);
        mIvPageUp = view.findViewById(R.id.iv_page_up);
        mIvPageDown = view.findViewById(R.id.iv_page_down);

        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onExitClick();
                }
                cancle();
            }
        });
        mTvSeeAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onSeeAgainClick();
                }
                cancle();
            }
        });
        mIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onItemClick(0);
                }
                cancle();
            }
        });
        mIv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onItemClick(1);
                }
                cancle();
            }
        });
        mIv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onItemClick(2);
                }
                cancle();
            }
        });
        mIv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onItemClick(3);
                }
                cancle();
            }
        });

        mIvPageUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onPageUpClick();
                }
            }
        });
        mIvPageDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onPageDownClick();
                }
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
        mTvSeeAgain.requestFocus();
    }

    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }



    public Dialog getDialog() {
        return dialog;
    }


    public ExitPageDialogBackup setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    private OnClickListener mOnClickListener;
    public interface OnClickListener{
        void onExitClick();
        void onSeeAgainClick();
        void onPageUpClick();
        void onPageDownClick();
        void onItemClick(int position);
    }
    String imgUrl = MainApp.imgUrl;
    public ExitPageDialogBackup setData(List<ExitPageModel.Databean.PageDatabean> data) {
        mData = data;
        String imgUrl1 = "";
        String imgUrl2 = "";
        String imgUrl3 = "";
        String imgUrl4 = "";
        switch (mData.size()){
            case 1:
                mIv1.setVisibility(View.VISIBLE);
                mIv2.setVisibility(View.GONE);
                mIv3.setVisibility(View.GONE);
                mIv4.setVisibility(View.GONE);
//                String imgUrl1 = mData.get(0).getCourse_img();
                imgUrl1 = imgUrl;
                GroceryStoreUtils.GlideImag(mContext, imgUrl1,mIv1);
                break;
            case 2:
                mIv1.setVisibility(View.VISIBLE);
                mIv2.setVisibility(View.VISIBLE);
                mIv3.setVisibility(View.GONE);
                mIv4.setVisibility(View.GONE);
                imgUrl1 = imgUrl;
                imgUrl2 = imgUrl;
                GroceryStoreUtils.GlideImag(mContext, imgUrl1,mIv1);
                GroceryStoreUtils.GlideImag(mContext, imgUrl2,mIv2);
                break;
            case 3:
                mIv1.setVisibility(View.VISIBLE);
                mIv2.setVisibility(View.VISIBLE);
                mIv3.setVisibility(View.VISIBLE);
                mIv4.setVisibility(View.GONE);
                imgUrl1 = imgUrl;
                imgUrl2 = imgUrl;
                imgUrl3 = imgUrl;
                GroceryStoreUtils.GlideImag(mContext, imgUrl1,mIv1);
                GroceryStoreUtils.GlideImag(mContext, imgUrl2,mIv2);
                GroceryStoreUtils.GlideImag(mContext, imgUrl3,mIv3);
                break;
            case 4:
                mIv1.setVisibility(View.VISIBLE);
                mIv2.setVisibility(View.VISIBLE);
                mIv3.setVisibility(View.VISIBLE);
                mIv4.setVisibility(View.VISIBLE);
                imgUrl1 = imgUrl;
                imgUrl2 = imgUrl;
                imgUrl3 = imgUrl;
                imgUrl4 = imgUrl;
                GroceryStoreUtils.GlideImag(mContext, imgUrl1,mIv1);
                GroceryStoreUtils.GlideImag(mContext, imgUrl2,mIv2);
                GroceryStoreUtils.GlideImag(mContext, imgUrl3,mIv3);
                GroceryStoreUtils.GlideImag(mContext, imgUrl4,mIv4);
                break;
        }
        return this;
    }

}
