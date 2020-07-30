package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CourseListModel;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.CourseDetailActivity;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/20------更新------
 * 课程列表Adapter
 */

public class RvAdapterCourse extends RecyclerView.Adapter<RvAdapterCourse.ViewHolder>{

    private List<CourseListModel.Databean.ClassListbean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public RvAdapterCourse(List<CourseListModel.Databean.ClassListbean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RvAdapterCourse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_course, parent, false);
        return new RvAdapterCourse.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterCourse.ViewHolder holder, final int position) {

        CourseListModel.Databean.ClassListbean bean = mData.get(position);
        if (CourseDetailActivity.mPlayingBean != null){
//            LogUtil.e("__onBindViewHolder","bean.getCourseId()",bean.getCourseId() + "");
//            LogUtil.e("__onBindViewHolder","PlayBean.getCourseId()",CourseDetailActivity.mPlayingBean.getCourseId() + "");
            if (bean.getId() == CourseDetailActivity.mPlayingBean.getId()){
                holder.mIvIsPlay.setVisibility(View.VISIBLE);
                GroceryStoreUtils.GlideGif(mContext,R.drawable.course_info_playing,holder.mIvIsPlay);
            }else {
                holder.mIvIsPlay.setVisibility(View.GONE);
            }
        }
        holder.mTvName.setText(bean.getName());
//        0为免费，1为收费
        if (bean.getIs_free() == 0){
            holder.mIvIsFree.setImageDrawable(mContext.getResources().getDrawable(R.drawable.course_info_icon_free));
        }else {
            holder.mIvIsFree.setImageDrawable(mContext.getResources().getDrawable(R.drawable.course_info_icon_vip));
        }
        holder.mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemEventListener != null){
                    mItemEventListener.onItemClick(position);
                }
            }
        });
        holder.mTvName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (mItemEventListener != null){
                        mItemEventListener.onItemFocusChange(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }else {
            return mData.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName;
        ImageView mIvIsFree;
        ImageView mIvIsPlay;
        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mIvIsFree = itemView.findViewById(R.id.iv_isfree);
            mIvIsPlay = itemView.findViewById(R.id.iv_playing);
        }
    }

    public void setItemEventListener(ItemEventListener itemEventListener) {
        mItemEventListener = itemEventListener;
    }

    private ItemEventListener mItemEventListener;
    public interface ItemEventListener {
        void onItemClick(int position);
        void onItemFocusChange(int position);
    }
}
