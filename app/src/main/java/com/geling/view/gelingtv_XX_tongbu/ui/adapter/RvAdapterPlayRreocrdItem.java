package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.PlayReocrdModel;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import java.util.List;

/*
* 播放记录内部Adapter
* */
public class RvAdapterPlayRreocrdItem extends RecyclerView.Adapter<RvAdapterPlayRreocrdItem.ViewHolder> {
    public Context mContext;
    public List<PlayReocrdModel.DataBeanX.DataBean.ListBean> data;
    private String  courseId;
    public RvAdapterPlayRreocrdItem(Context context, List<PlayReocrdModel.DataBeanX.DataBean.ListBean> data){
        this.mContext = context;
        this.data = data;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imag;
        ImageView icon;
        TextView tv_name;
        TextView tv_indat;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            imag= itemView.findViewById(R.id.image);
            icon  = itemView.findViewById(R.id.image_icon);
            tv_name  = itemView.findViewById(R.id.tv_name);
            tv_indat  = itemView.findViewById(R.id.tv_indata);
            linearLayout = itemView.findViewById(R.id.ll);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = mInflater.inflate(R.layout.item_play_reocrd_item,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_play_reocrd_item,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        PlayReocrdModel.DataBeanX.DataBean.ListBean  mdata = data.get(position);
        String imgUrl =mdata.getIcon();

        GroceryStoreUtils.GlideImagHasPlaceHolder(mContext,imgUrl,holder.imag,R.drawable.img_default);

        courseId = String.valueOf(mdata.getCourse_id());
        holder.icon.setImageResource(R.drawable.icon_stap);
        holder.tv_name.setText(mdata.getName());
        holder.tv_indat.setText("观看至"+mdata.getRate()+"%");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("__onClick","courseId",String.valueOf(mdata.getCourse_id()));
                if (mOnItemStateChangeListener != null){
                    mOnItemStateChangeListener.onItemClick(String.valueOf(mdata.getCourse_id()));
                }
            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    holder.linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.bg_border11));
                }else {
                    holder.linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.bg_border11_b));
                }
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return data.size();
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
         * @param courseId
         */
        void onItemClick(String courseId);
    }

}
