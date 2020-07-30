package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.GoodsModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/18------更新------
 * 价格adapter
 */

public class RvAdapterGoods extends RecyclerView.Adapter<RvAdapterGoods.ViewHolder>{

    private List<GoodsModel> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mSelectedPosition = 0;
    public RvAdapterGoods(List<GoodsModel> data, Context context) {
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        GoodsModel model = mData.get(position);
        int price = Integer.valueOf(model.getTotalFee())/100;
        holder.mTvPrice.setText(String.valueOf(price));
        holder.mTvAverage.setText(model.getGoodsText());
        //抗锯齿
        holder.mTvPrice.getPaint().setAntiAlias(true);
        holder.mTvAverage.getPaint().setAntiAlias(true);
        holder.mTvRmbCharacter.getPaint().setAntiAlias(true);
        holder.mTvRmbUnit.getPaint().setAntiAlias(true);
        holder.mTvGoodsName.getPaint().setAntiAlias(true);

        holder.mItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
//                ViewCompat.animate(holder.mItemView)
//                 .setDuration(200)
//                 .scaleX(1.1f)
//                 .scaleY(1.1f)
//                 .start();
                    mSelectedPosition = position;
                    LogUtil.e("__RvAdapterPrice2_mSelectedPosition",mSelectedPosition + "__");

                }else {
//                ViewCompat.animate(holder.mItemView)
//                 .setDuration(0)
//                 .scaleX(1f)
//                 .scaleY(1f)
//                 .start();
                }
                if (mOnItemStateChangeListener != null){
                    mOnItemStateChangeListener.onFocusChange(position,hasFocus,holder.mItemView);
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
        holder.mTvGoodsName.setText(model.getGoodsName());
//        holder.mTvGoodsName.setText(model.getGoodsName() + model.getGoodsName());
        int bgType = position%3;
        switch (bgType){
            case 0:
                //加载后台设置的背景图片，如果没有，则按照正常背景设置
                GroceryStoreUtils.GlideImag(mContext,model.getGoodsPic(),holder.mIvBg,R.drawable.vip_price_bg_month);
//                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_month));
                break;
            case 1:
                GroceryStoreUtils.GlideImag(mContext,model.getGoodsPic(),holder.mIvBg,R.drawable.vip_price_bg_quarter);
//                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_quarter));
                break;
            case 2:
                GroceryStoreUtils.GlideImag(mContext,model.getGoodsPic(),holder.mIvBg,R.drawable.vip_price_bg_year);
//                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_year));
                break;
        }
        if (position == mSelectedPosition){
                 ViewCompat.animate(holder.mItemView)
                 .setDuration(200)
                 .scaleX(1.25f)
                 .scaleY(1.25f)
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
        ImageView mIvBg;
        TextView mTvRmbCharacter;
        TextView mTvPrice;
        TextView mTvGoodsName;
        TextView mTvRmbUnit;
        TextView mTvAverage;
        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTvRmbCharacter = itemView.findViewById(R.id.tv_rmb_character);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            mTvRmbUnit = itemView.findViewById(R.id.tv_rmb_unit);
            mTvAverage = itemView.findViewById(R.id.tv_average);
            mIvBg = itemView.findViewById(R.id.iv_bg);
        }
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
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
         * 某个position获取焦点改变的时候
         */
        void onFocusChange(int position,boolean hasFocus,View itemView);
        void onItemClick(int position);
    }

}
