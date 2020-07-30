package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MealModel;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/18------更新------
 * 价格adapter
 */

public class RvAdapterMeal3 extends RecyclerView.Adapter<RvAdapterMeal3.ViewHolder>{

    private List<MealModel.DatabeanX.Databean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mSelectedPosition =-1;
    public RvAdapterMeal3(List<MealModel.DatabeanX.Databean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_meal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        MealModel.DatabeanX.Databean model = mData.get(position);
        holder.mTvAverage.setText("平均" + model.getDay_price() + "元/天");
        holder.mTvGoodsName.setText(model.getSet_meal_name());
        holder.mTvOriPrice.setText("原价" + model.getVm_price());
        //为0时，非限时折扣价，为1时是限时折扣价
        if (model.getDiscount() == 0){
            holder.mTvPrice.setText(model.getPrice());
            holder.mIvDiscount.setVisibility(View.INVISIBLE);

        }else {
            holder.mTvPrice.setText(model.getDiscount_price());
            holder.mIvDiscount.setVisibility(View.VISIBLE);

        }
        holder.mItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus){
                    mSelectedPosition = position;
                    ViewCompat.animate(holder.mItemView)
                            .setDuration(200)
                            .scaleX(1.18f)
                            .scaleY(1.09f)
                            .start();
                    if (mOnItemStateChangeListener != null){
                        mOnItemStateChangeListener.onFocusGetted(position);
                    }
                    holder.mTvGoodsName.setTextColor(mContext.getResources().getColor(R.color.black_gold));
                    holder.mTvAverage.setTextColor(mContext.getResources().getColor(R.color.black_gold));
                    holder.mTvRmbSign.setTextColor(mContext.getResources().getColor(R.color.black_gold));
                    holder.mTvOriPrice.setTextColor(mContext.getResources().getColor(R.color.black_gold));
                    holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.black_gold));
                    holder.mIvBg.setVisibility(View.GONE);
                    holder.mItemView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_meal_selected));
//                    holder.mItemView.setBackgroundResource(R.drawable.bg_border6);
                }else {
                    ViewCompat.animate(holder.mItemView)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                    holder.mTvGoodsName.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.mTvAverage.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.mTvRmbSign.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.mTvOriPrice.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.mItemView.setBackground(null);
                    holder.mIvBg.setVisibility(View.VISIBLE);
//                    holder.mItemView.setBackground(null);
                }
            }
        });
//        holder.mItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemStateChangeListener != null){
//                    mOnItemStateChangeListener.onItemClick(position);
//                }
//            }
//        });

        if (mSelectedPosition == position){
            ViewCompat.animate(holder.mItemView)
                    .setDuration(200)
                    .scaleX(1.15f)
                    .scaleY(1.15f)
                    .start();
            holder.mTvGoodsName.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            holder.mTvAverage.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            holder.mTvRmbSign.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            holder.mTvOriPrice.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.black_gold));
            holder.mIvBg.setVisibility(View.GONE);
            holder.mItemView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_meal_selected));
//            holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.bg_meal_selected));
            } else {
            ViewCompat.animate(holder.mItemView)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .start();
            holder.mItemView.setBackground(null);
            holder.mIvBg.setVisibility(View.VISIBLE);
            holder.mTvAverage.setTextColor(mContext.getResources().getColor(R.color.color_white));
            holder.mTvGoodsName.setTextColor(mContext.getResources().getColor(R.color.color_white));
            holder.mTvRmbSign.setTextColor(mContext.getResources().getColor(R.color.color_white));
            holder.mTvOriPrice.setTextColor(mContext.getResources().getColor(R.color.color_white));
            holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_white));
//            holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.bg_meal_normal));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mItemView;
        ImageView mIvBg;
        ImageView mIvDiscount;
        TextView mTvPrice;
        TextView mTvGoodsName;
        TextView mTvAverage;
        TextView mTvOriPrice;//原价
        TextView mTvRmbSign;//人民币符号
        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvOriPrice = itemView.findViewById(R.id.tv_ori_price);
            mTvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            mTvAverage = itemView.findViewById(R.id.tv_average);
            mIvBg = itemView.findViewById(R.id.iv_bg);
            mIvDiscount = itemView.findViewById(R.id.iv_discount);
            mTvRmbSign = itemView.findViewById(R.id.tv_rmb_sign);
            mTvOriPrice.setPaintFlags(mTvOriPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }


    private RvAdapterMeal3.OnItemStateChangeListener mOnItemStateChangeListener;

    public void setOnItemStateChangeListener(RvAdapterMeal3.OnItemStateChangeListener onItemStateChangeListener) {
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
