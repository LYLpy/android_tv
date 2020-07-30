package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.PriceModel;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/18------更新------
 * 价格adapter
 */

public class RvAdapterPrice extends RecyclerView.Adapter<RvAdapterPrice.ViewHolder>{

    private List<PriceModel> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public RvAdapterPrice(List<PriceModel> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_price,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        PriceModel model = mData.get(position);

        holder.mTvPrice.setText(model.getPrice());
        holder.mTvAverage.setText(model.getAverage());
        holder.mItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
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
        switch (model.getGoods_type()){
            case "1":
                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_month));
                break;
            case "2":
                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_quarter));
                break;
            case "3":
                holder.mIvBg.setBackground(mContext.getResources().getDrawable(R.drawable.vip_price_bg_year));
                break;
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
        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mTvRmbCharacter = itemView.findViewById(R.id.tv_rmb_character);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvRmbUnit = itemView.findViewById(R.id.tv_rmb_unit);
            mTvAverage = itemView.findViewById(R.id.tv_average);
            mIvBg = itemView.findViewById(R.id.iv_bg);
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

}
