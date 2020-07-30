package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeOtherModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/21------更新------
 * 幼儿内容Adpater
 */

public class RvAdapterChildSubject extends RecyclerView.Adapter<RvAdapterChildSubject.ViewHolder>{
    private List<HomeOtherModel.Databean.CourseListDatabean.CourseDatabean.PageDatabean> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    private int selecedPosition;//选中位置selectedPosition
    private ImageView mFirstItemView;
    public RvAdapterChildSubject(List<HomeOtherModel.Databean.CourseListDatabean.CourseDatabean.PageDatabean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_child,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (position == 0){
            mFirstItemView = holder.iv;
        }
        HomeOtherModel.Databean.CourseListDatabean.CourseDatabean.PageDatabean model = mData.get(position);
        holder.itemView.setFocusable(true);
//        GroceryStoreUtils.GlideImag(mContext,model.getCourse_img(),holder.iv);
//        String imgUrl = "http://139.9.7.208:8989//Public//Uploads//images//2019-11-26//5ddcd3d906771.jpg";
        String imgUrl = model.getCourse_img();
//        GroceryStoreUtils.GlideImag(mContext,imgUrl,holder.iv);
        GroceryStoreUtils.GlideImagHasPlaceHolder(mContext,imgUrl,holder.iv,R.drawable.placeholder_course);
//        holder.itemView.setTag(position);
//        LogUtil.e("__onBindViewHolder","getTag1:" + (int) holder.itemView.getTag());

//                  selectedPosition = position;
//                  selectedPosition = (int) holder.itemView.getTag();
//                    selectedPosition = position;
                    LogUtil.e("__onBindViewHolder","onFocusChange_selectedPosition2:" + String.valueOf(selecedPosition));
                    LogUtil.e("__onBindViewHolder","position2:" + String.valueOf(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onItemClidkListener(position);
                }
            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    if (mListener != null){
                        mListener.onItemFocusChange(position,b);
                    }
                }
            }
        });
    }

    public ImageView getFirstItemView() {
        return mFirstItemView;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = itemView.findViewById(R.id.siv);
        }
    }

    public int getSelectedPosition() {
        return selecedPosition;
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selecedPosition = selectedPosition;
        notifyDataSetChanged();
    }


    public void setOnItemSelectedListener(OnItemStateChangeListener listener) {
        mListener = listener;
    }
    private OnItemStateChangeListener mListener;
    /**
     * 条目状态变化监听
     */
    public interface OnItemStateChangeListener {
        /**
         * 点击监听
         * @param position
         */
        void onItemClidkListener(int position);

        /**焦点变化监听
         * @param position
         * @param isFocus
         */
        void onItemFocusChange(int position,boolean isFocus);
    }
}
