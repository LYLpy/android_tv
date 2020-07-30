package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SearchModelPy;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/8------更新------
 * 搜索界面adpater
 */

public class RvAdapterSearchPy extends RecyclerView.Adapter<RvAdapterSearchPy.ViewHolder>{

    private List<SearchModelPy.DatabeanX.Databean> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public RvAdapterSearchPy(List<SearchModelPy.DatabeanX.Databean> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
//        LogUtil.e("__SearchPy","构造" ,mData.size() + "");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_search,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        SearchModelPy.DatabeanX.Databean databean = mData.get(position);
//        String imgUrl = MainApp.imgUrl;
        String imgUrl = databean.getIcon();
//        LogUtil.e("__SearchPy","onBindViewHolder","imgUrl" + position,imgUrl);
//        GroceryStoreUtils.GlideImag(mContext,imgUrl,holder.iv);
        GroceryStoreUtils.GlideImag(mContext,imgUrl,holder.iv,R.drawable.placeholder_course);
//        GroceryStoreUtils.GlideImag(mContext,databean.getCourse_img(),holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
        holder.iv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && mOnItemClickListener != null){
                    mOnItemClickListener.onItemPisitionFocusGetted(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.siv);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);

        /**
         * item获取到焦点的时候回调
         * @param position
         */
        void onItemPisitionFocusGetted(int position);
    }
}
