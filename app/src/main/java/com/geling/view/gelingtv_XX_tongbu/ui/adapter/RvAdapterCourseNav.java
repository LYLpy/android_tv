package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/20------更新------
 * 课程导航栏Adapter
 */

public class RvAdapterCourseNav extends RecyclerView.Adapter<RvAdapterCourseNav.ViewHolder>{

    private List<String> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int selectedPosition;

    public RvAdapterCourseNav(List<String> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_nav_course_detail, parent, false);
        return new RvAdapterCourseNav.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       String navTag = mData.get(position);
        holder.tvTextView.setText(navTag);
        holder.tvTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    selectedPosition = position;
                    if (mFocusChangeListener != null) {
                        mFocusChangeListener.onItemFocusChange(position);
                    }
//                    holder.tvTextView.setTextColor(mContext.getResources().getColor(R.color.color_white));
                } else {
                    LogUtil.e("__onFocusChange_false_position", position + "");
                    LogUtil.e("__onFocusChange_false_selectedPosition", selectedPosition + "");
                }
            }
        });
//        LogUtil.e("__onFocusChange_onBindViewHolder_selectedPosition", selectedPosition + "");
//        LogUtil.e("__onFocusChange_onBindViewHolder_position", position + "");
        if (position == selectedPosition) {
            holder.tvTextView.setTextColor(mContext.getResources().getColor(R.color.color_blue));
        } else {
            holder.tvTextView.setTextColor(mContext.getResources().getColor(R.color.color_white));
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }else {
            return mData.size();
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTextView = itemView.findViewById(R.id.tv);
        }
    }

    public void setItemFocusChangeListener(RvAdapterNavCourseDetail.OnItemFocusChangeListener listenter) {
        mFocusChangeListener = listenter;
    }
    private RvAdapterNavCourseDetail.OnItemFocusChangeListener mFocusChangeListener;

    public interface OnItemFocusChangeListener {
        void onItemFocusChange(int position);
    }
}
