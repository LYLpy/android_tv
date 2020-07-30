package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeOtherModel;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/29------更新------
 * 首页数据adapter
 */

public class RvAdapterMain extends RecyclerView.Adapter<RvAdapterMain.ViewHolder>{

    private List<HomeOtherModel.Databean.CourseListDatabean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public RvAdapterMain(List<HomeOtherModel.Databean.CourseListDatabean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeOtherModel.Databean.CourseListDatabean databean = mData.get(position);
//        GroceryStoreUtils.GlideImag(mContext,databean.get,mHomeIvList.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mItemView;
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv);
        }
    }
}
