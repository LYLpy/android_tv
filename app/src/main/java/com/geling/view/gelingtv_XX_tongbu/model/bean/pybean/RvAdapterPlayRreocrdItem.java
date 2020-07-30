package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.ui.activity.CourseDetailActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.event.EventPlayReocrdMassge;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import com.geling.view.gelingtv_XX_tongbu.R;

import org.greenrobot.eventbus.EventBus;

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
        public ViewHolder(View itemView) {
            super(itemView);
            imag= itemView.findViewById(R.id.image);
            icon  = itemView.findViewById(R.id.image_icon);
            tv_name  = itemView.findViewById(R.id.tv_name);
            tv_indat  = itemView.findViewById(R.id.tv_indata);

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
        if (imgUrl == ""||imgUrl==null||imgUrl.equals("")){
            holder.imag.setImageResource(R.drawable.icon_stap);
        }else{
            GroceryStoreUtils.GlideImag(mContext,imgUrl,holder.imag);
        }
        courseId = String.valueOf(mdata.getCourse_id());
        holder.icon.setImageResource(R.drawable.icon_stap);
        holder.tv_name.setText(mdata.getName());
        holder.tv_indat.setText("观看至"+mdata.getRate()+"%");
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    ViewCompat.animate(holder.itemView)
                            .setDuration(200)
                            .scaleX(1.1f)
                            .scaleY(1.1f)
                            .start();
                    if (mOnItemStateChangeListener!=null){
                        mOnItemStateChangeListener.onFocusGetted(position);
                    }

                }else{
                    ViewCompat.animate(holder.itemView)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new EventPlayReocrdMassge("courseId",courseId));
                Intent intent = new Intent(mContext, CourseDetailActivity.class);
                mContext.startActivity(intent);

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
         * @param position
         * 某个position获取焦点的时候
         */
        void onFocusGetted(int position);
        void onItemClick(int position);
    }
}
