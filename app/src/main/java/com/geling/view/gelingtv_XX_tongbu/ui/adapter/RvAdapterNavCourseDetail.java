package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.CourseDetailModel;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/4------更新------
 */

public class RvAdapterNavCourseDetail extends RecyclerView.Adapter<RvAdapterNavCourseDetail.ViewHolder> {

    private List<CourseDetailModel.Databean.VideoListDatabean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int selectedPosition;

    public RvAdapterNavCourseDetail(List<CourseDetailModel.Databean.VideoListDatabean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_nav_course_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        CourseDetailModel.Databean.VideoListDatabean databean = mData.get(position);
        holder.tvTextView.setText(databean.getVideo_list_tag());
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
        LogUtil.e("__onFocusChange_onBindViewHolder_selectedPosition", selectedPosition + "");
        if (position == selectedPosition) {
            holder.tvTextView.setTextColor(mContext.getResources().getColor(R.color.color_blue));
        } else {
            holder.tvTextView.setTextColor(mContext.getResources().getColor(R.color.color_white));
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null){
            return mData.size();
        }else {
            return 0;
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTextView = itemView.findViewById(R.id.tv);
        }
    }

    public void setItemFocusChangeListener(OnItemFocusChangeListener listenter) {
        mFocusChangeListener = listenter;
    }

    private OnItemFocusChangeListener mFocusChangeListener;

    public interface OnItemFocusChangeListener {
        void onItemFocusChange(int position);
    }
}
