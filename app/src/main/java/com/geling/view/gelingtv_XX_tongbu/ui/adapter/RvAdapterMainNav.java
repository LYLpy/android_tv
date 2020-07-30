package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.MainNavModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/21------更新------
 * 首页导航栏Rv的Adpater
 */

public class RvAdapterMainNav extends RecyclerView.Adapter<RvAdapterMainNav.ViewHolder>{

    private List<MainNavModel> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int selectedPosition;//选中位置
    private Drawable mBgDrawable;//选中时的背景shape
    public static String mFocBgColor;//选中时的背景颜色
    public static String mFocusTextColor;//聚焦时的字体颜色
    public static String mNormalTextColor;//选中时的字体颜色
    public static String mSelTextColor;//选中时的字体颜色
    public RvAdapterMainNav(List<MainNavModel> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_nav_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MainNavModel model = mData.get(position);
        if (mBgDrawable == null){
            mBgDrawable = getSelectdDrawble();
        }
        holder.itemView.setTag(position);
        holder.tv.setText(model.getName());
        holder.tv.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if (hasFocus){
                        selectedPosition = position;
                        LogUtil.e("__RvAdapterMainNav_hasFocus",String.valueOf(model.getFocusIconUrl()));
                        GroceryStoreUtils.GlideImag(mContext,model.getFocusIconUrl(),holder.iv);
//                    holder.iv.setImageDrawable(mContext.getResources().getDrawable(model.getFocusIconId()));
//                    holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_white));
                        holder.tv.setTextColor(Color.parseColor(mFocusTextColor));
                        if (mOnItemChangeListener != null){
                            mOnItemChangeListener.onItemFocusChange(hasFocus,position);
                        }
                        holder.itemView.setBackground(mBgDrawable);
//                        holder.tvDivider.setVisibility(View.VISIBLE);
                    }else {
                        //没有焦点全部设置为非选中状态，后续activity中其他控件获取焦点之后再刷新rv
//                    holder.iv.setImageDrawable(mContext.getResources().getDrawable(model.getNormalIconId()));
                        LogUtil.e("__RvAdapterMainNav_not_Focus",String.valueOf(model.getFocusIconUrl()));
                        GroceryStoreUtils.GlideImag(mContext,model.getNormalIconUrl(),holder.iv);
//                    GroceryStoreUtils.GlideDraw(mContext,model.getNormalIconId(),holder.iv);
//                    holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_white));
                        holder.tv.setTextColor(Color.parseColor(mNormalTextColor));
                        holder.tvDivider.setVisibility(View.GONE);
                        holder.itemView.setBackground(null);
                        holder.tvDivider.setVisibility(View.GONE);
                    }
                }catch (Exception e){

                }
            }
        });
        try {
            if (position == selectedPosition){
                holder.tvDivider.setVisibility(View.VISIBLE);
//            holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_blue));
                holder.tv.setTextColor(Color.parseColor(mSelTextColor));
//            GroceryStoreUtils.GlideDraw(mContext,model.getSelectedIconId(),holder.iv);
//            holder.iv.setImageDrawable(mContext.getResources().getDrawable(model.getSelectedIconId()));
                LogUtil.e("__RvAdapterMainNav_seleted",String.valueOf(model.getSelectedIconUrl()));
                GroceryStoreUtils.GlideImag(mContext,model.getSelectedIconUrl(),holder.iv);
            }else {
                LogUtil.e("__RvAdapterMainNav_normal",String.valueOf(model.getNormalIconUrl()));
                holder.tvDivider.setVisibility(View.GONE);
//            holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_white));
                holder.tv.setTextColor(Color.parseColor(mNormalTextColor));
//            GroceryStoreUtils.GlideDraw(mContext,model.getNormalIconId(),holder.iv);
//            holder.iv.setImageDrawable(mContext.getResources().getDrawable(model.getNormalIconId()));
                GroceryStoreUtils.GlideImag(mContext,model.getNormalIconUrl(),holder.iv);
            }
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView iv;
        TextView tv;
        TextView tvDivider;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);;
            tvDivider = itemView.findViewById(R.id.tv_divider);
            try {
                tvDivider.setBackgroundColor(Color.parseColor(mSelTextColor));
            }catch (Exception e){

            }
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void setOnItemChangeListener(OnItemChangeListener OnItemChangeListener) {
        mOnItemChangeListener = OnItemChangeListener;
    }

    private OnItemChangeListener mOnItemChangeListener;
    public interface OnItemChangeListener{
        /**
         * item焦点变化的时候
         * @param position
         */
        void onItemFocusChange(boolean hasFocus,int position);
    }
    public int getDataSize(){
        return mData.size();
    }

    private Drawable getSelectdDrawble(){
        GradientDrawable drawable = new GradientDrawable();
//        rectangle
        //设置圆角大小
//        drawable.setCornerRadius(GroceryStoreUtils.dip2px(30));
        drawable.setCornerRadius(40);
        //设置边缘线的宽以及颜色
//        drawable.setStroke(1, Color.parseColor(mFocBgColor));
        //设置shape背景色
//        drawable.setColor(Color.parseColor("#FFFFFF"));
        try {
            drawable.setColor(Color.parseColor(mFocBgColor));
        }catch (Exception e){

        }
        //设置到TextView中
//        tv_type.setBackgroundDrawable(drawable);
        return drawable;
    }
}

