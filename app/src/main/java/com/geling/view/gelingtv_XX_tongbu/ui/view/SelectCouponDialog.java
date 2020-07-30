package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.OhterModel1;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CouponByPriceModel;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterSelectCoupon;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/9/17------更新------
 * 选择优惠券的dialog
 */

public class SelectCouponDialog {

    private Context mContext;
    private Dialog dialog;
    private List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> mData;
    private RecyclerView mRv;
    private ImageView mIvBg;

    public SelectCouponDialog(Context context, List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> data) {
        mContext = context;
        mData = data;
    }
    public SelectCouponDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_coupon, null);
        mRv = view.findViewById(R.id.rv);
        mIvBg = view.findViewById(R.id.iv_bg);
        OhterModel1 ohterModel1 = MainApp.getInstance().getOhterModel();
        if (ohterModel1 != null){
            GroceryStoreUtils.GlideImagHasPlaceHolder(mContext,ohterModel1.getData().getCoupon_background(),mIvBg,R.drawable.bg_select_coupon);
        }
        initRv();
        dialog = new Dialog(mContext,R.style.new_task_style);
        dialog.setContentView(view);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_BACK:
                        LogUtil.e("__setOnKeyListener","onKey","返回");
//                        监听用户按下返回键时，关闭dialog
                        if (dialog != null){
                            dialog.cancel();
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
        return this;
    }

    private void initRv(){
        if (mData == null){
            return;
        }
        RvAdapterSelectCoupon adapter = new RvAdapterSelectCoupon(mContext, mData);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        adapter.setItemClickListener(new RvAdapterSelectCoupon.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mListener != null){
                    if (position > 0){
                        LogUtil.d("__selectCouponDialog","onItemClick",String.valueOf("> 0"));
                        mListener.onPopUpItemClick(mData.get(position - 1));
                    }else {
                        LogUtil.d("__selectCouponDialog","onItemClick",String.valueOf("null"));
                        //表示不选择优惠券，返回null
                        mListener.onPopUpItemClick(null);
                    }
                }
                cancle();
            }
        });
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
    }


    public void show() {
        if(dialog!=null)dialog.show();
    }
    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }


    public void setOnPopupItemClickListener(OnPopupItemClickListener listener) {
        mListener = listener;
    }
    private OnPopupItemClickListener mListener;
    public interface OnPopupItemClickListener{
        /**
         * @param couponBean
         */
        void onPopUpItemClick(CouponByPriceModel.DatabeanX.AppGiftListbean.Databean couponBean);
    }
    public void setNewData(List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> data){
        mData = data;
        initRv();
    }

    public Dialog getDialog() {
        return dialog;
    }
}
