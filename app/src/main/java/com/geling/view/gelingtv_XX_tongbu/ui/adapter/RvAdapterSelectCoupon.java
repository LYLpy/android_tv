package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CouponByPriceModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/9/17------更新------
 */

public class RvAdapterSelectCoupon extends RecyclerView.Adapter<RvAdapterSelectCoupon.ViewHolder>{

    private Context mContext;
    private List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> mData;
    private LayoutInflater mInflater;

    public RvAdapterSelectCoupon(Context context, List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.item_select_coupon,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (i == 0){
//            viewHolder.tv.setVisibility(View.VISIBLE);
            viewHolder.iv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.no_use_coupon));

        }else {
//            viewHolder.tv.setVisibility(View.GONE);
            int position = i - 1;
            CouponByPriceModel.DatabeanX.AppGiftListbean.Databean model = mData.get(position);
//        viewHolder.tvName.setText(model.getName());
//        viewHolder.tvTime.setText(model.getTime());
        String imgUrl = model.getIcon();
//            LogUtil.e("__RvAdapterSelectCoupon","onBindViewHolder" + i,imgUrl);
        GroceryStoreUtils.GlideImag(mContext,imgUrl,viewHolder.iv);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(i);
                }
            }
        });
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    ViewCompat.animate(viewHolder.itemView)
                            .setDuration(200)
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .start();
                }else {
                    ViewCompat.animate(viewHolder.itemView)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView iv;
//        TextView tv;
//        TextView tvName;
//        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv);
//            tv = itemView.findViewById(R.id.tv_no_use);
//            tvName = itemView.findViewById(R.id.tv_name);
//            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
    private OnItemClickListener mItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> getData() {
        return mData;
    }

    public void setData(List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> data) {
        mData = data;
    }
    public void removeData(){
            mData = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setNewData(List<CouponByPriceModel.DatabeanX.AppGiftListbean.Databean> data){
        mData = data;
        notifyDataSetChanged();
    }
}
