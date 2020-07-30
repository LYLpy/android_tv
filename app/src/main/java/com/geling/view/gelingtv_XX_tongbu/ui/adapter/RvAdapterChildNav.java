package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeOtherModel;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/21------更新------
 * 幼儿、专题、初中导航栏Adpater
 */

public class RvAdapterChildNav extends RecyclerView.Adapter<RvAdapterChildNav.ViewHolder>{

    private List<HomeOtherModel.Databean.CourseListDatabean.CourseNavbean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int selecedPosition;//选中位置selectedPosition
    private boolean mIsFocusOn;//当前是否有焦点
    public static String mBgNorColor;//正常背景
    public static String mBgFocColor;//聚焦时背景
    public static String mTextNorColor;//字体正常颜色
    public static String mTextSelColor;//字体选中是颜色
    public static String mTextFocColor;//字体聚焦时颜色
    public RvAdapterChildNav(List<HomeOtherModel.Databean.CourseListDatabean.CourseNavbean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public RvAdapterChildNav(List<HomeOtherModel.Databean.CourseListDatabean.CourseNavbean> data, Context context,String color) {
        mData = data;
        mContext = context;
        mBgNorColor = color;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_chlid_nav,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        LogUtil.e("__setSelectedPosition_onBindViewHolder",selecedPosition + "");
        if (position == 0){
            holder.tv.setText("推荐");
        }else {
            HomeOtherModel.Databean.CourseListDatabean.CourseNavbean model = mData.get(position - 1);
            holder.tv.setText(model.getLeft_nav_name());
        }
//        if (!mIsFocusOn){
//            try{
//                holder.tv.setBackgroundColor(Color.parseColor(mBgNorColor));
//            }catch (Exception e){
//                holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_top_tv_main_normal));
//            }
//        }
//        holder.itemView.setTag(position);
//        LogUtil.e("__onBindViewHolder","getTag1:" + (int) holder.itemView.getTag());
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange","焦点改变" + b);
                if (b){
                    selecedPosition = position;
                    holder.tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    if (mListener != null){
                        mListener.onItemSelected(position,view);
                    }
                    //稍微放大
                    ViewCompat.animate(holder.tv)
                            .setDuration(200)
                            .scaleX(1.05f)
                            .scaleY(1.15f)
                            .start();
                    try {
                        holder.tv.setTextColor(Color.parseColor(mTextFocColor));
                    }catch (Exception e){

                    }
                    try {
                        holder.tv.setBackgroundColor(Color.parseColor(mBgFocColor));
                    }catch (Exception e){

                    }
//                        holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_white));
//                        holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_blue));
                }else {
                    holder.tv.setEllipsize(TextUtils.TruncateAt.END);
//                        if (!TextUtils.isEmpty(mBgNorColor)){
                    try {
                        holder.tv.setTextColor(Color.parseColor(mTextNorColor));
                    }catch (Exception e){

                    }
                    try {
                        holder.tv.setBackgroundColor(Color.parseColor(mBgNorColor));
                    }catch (Exception e){

                    }
//                            try{
//                                holder.tv.setBackgroundColor(Color.parseColor(mBgNorColor));
//                            }catch (Exception e){
//                                holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_top_tv_main_normal));
//                            }
//                        }
                    //还原还来大小
                    ViewCompat.animate(holder.tv)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
//                        holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_white));
                }
            }
        });
        if (position == selecedPosition ){
//            holder.tv.setBackground(mContext.getResources().getDrawable(R.drawable.bg_tv_main_blue));
//            holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_blue));
            if (!mIsFocusOn){
                try{
                    holder.tv.setTextColor(Color.parseColor(mTextSelColor));
                }catch (Exception e){
//                tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_top_tv_main_normal));
                }
            }
        }else {
//            holder.tv.setBackground(mContext.getResources().getDrawable(R.drawable.bg_tv_main_normal));
//            holder.tv.setTextColor(mContext.getResources().getColor(R.color.color_white));
            try{
                holder.tv.setTextColor(Color.parseColor(mTextNorColor));
            }catch (Exception e){
//                tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_top_tv_main_normal));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;    //加推荐位
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv = itemView.findViewById(R.id.tv);
            try{
                tv.setBackgroundColor(Color.parseColor(mBgNorColor));
            }catch (Exception e){
//                tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_top_tv_main_normal));
            }
            try{
                tv.setTextColor(Color.parseColor(mTextNorColor));
            }catch (Exception e){
//                tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_top_tv_main_normal));
            }
        }
    }

    public int getSelectedPosition() {
        return selecedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selecedPosition = selectedPosition;
        LogUtil.e("__setSelectedPosition",selectedPosition + "");
//        notifyDataSetChanged();
    }

    public void setFocusOn(boolean focusOn) {
        mIsFocusOn = focusOn;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mListener = listener;
    }
    private OnItemSelectedListener mListener;
    /**
     * 选中监听
     */
    public interface OnItemSelectedListener{
        void onItemSelected(int position,View view);
    }
}
