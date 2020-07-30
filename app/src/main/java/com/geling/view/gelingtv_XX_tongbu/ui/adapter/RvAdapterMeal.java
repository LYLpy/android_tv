package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MealModel;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/02/23------更新------
 * 价格adapter
 */

public class RvAdapterMeal extends RecyclerView.Adapter<RvAdapterMeal.ViewHolder>{

    private List<MealModel.DatabeanX.Databean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mSelectedPosition = 0;
    public RvAdapterMeal(List<MealModel.DatabeanX.Databean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_goods,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        MealModel.DatabeanX.Databean model = mData.get(position);
        holder.mTvAverage.setText("平均" + model.getDay_price() + "元/天");
        holder.mTvGoodsName.setText(model.getSet_meal_name());
        //为0时，非限时折扣价，为1时是限时折扣价
        if (model.getDiscount() == 0){
            holder.mTvPrice.setText(model.getPrice());
        }else {
            holder.mTvPrice.setText(model.getDiscount_price());
        }
        holder.mItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                mSelectedPosition = position;
                ViewCompat.animate(holder.mItemView)
                 .setDuration(200)
                 .scaleX(1.15f)
                 .scaleY(1.15f)
                 .start();
                if (mOnItemStateChangeListener != null){
                    mOnItemStateChangeListener.onFocusGetted(position);
                }
                }else {
                ViewCompat.animate(holder.mItemView)
                 .setDuration(200)
                 .scaleX(1f)
                 .scaleY(1f)
                 .start();
                }
            }
        });
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemStateChangeListener != null){
                    mOnItemStateChangeListener.onItemClick(position);
                }
            }
        });
        int bgType = position%3;//取余
        switch (bgType){
            case 0:
                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_month));
                break;
            case 1:
                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_quarter));
                break;
            case 2:
                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_year));
                break;
        }
        if (mSelectedPosition == position){
            ViewCompat.animate(holder.mItemView)
                    .setDuration(200)
                    .scaleX(1.15f)
                    .scaleY(1.15f)
                    .start();
        }else {
            ViewCompat.animate(holder.mItemView)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mItemView;
        View mIvBg;
        TextView mTvRmbCharacter;
        TextView mTvPrice;
        TextView mTvRmbUnit;
        TextView mTvAverage;
        TextView mTvGoodsName;
        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTvRmbCharacter = itemView.findViewById(R.id.tv_rmb_character);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvRmbUnit = itemView.findViewById(R.id.tv_rmb_unit);
            mTvAverage = itemView.findViewById(R.id.tv_average);
            mIvBg = itemView.findViewById(R.id.iv_bg);
            mTvGoodsName = itemView.findViewById(R.id.tv_goods_name);
        }
    }

    private OnItemStateChangeListener mOnItemStateChangeListener;

    public void setOnItemStateChangeListener(OnItemStateChangeListener onItemStateChangeListener) {
        mOnItemStateChangeListener = onItemStateChangeListener;
    }

    /**
     * 状态变化监听
     */
    public interface OnItemStateChangeListener{
        /**
         * @param position
         * 某个position获取焦点的时候
         */
        void onFocusGetted(int position);
        void onItemClick(int position);
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }
}
