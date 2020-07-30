package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.PlayReocrdModel;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/*
* 播放记录adpter外层
* */
public class RvAdapterPlayReocrd extends RecyclerView.Adapter< RvAdapterPlayReocrd.ViewHolder>{
    private int lime;
    private int page;
    private PlayReocrdModel mModel ;
    private PlayReocrdModel.DataBeanX dataBean ;
    private List<PlayReocrdModel.DataBeanX.DataBean> dataBeanList  ;
    private Context mContext;
    private LayoutInflater mInflater;
    private RvAdapterPlayRreocrdItem Item;
    private List<PlayReocrdModel.DataBeanX.DataBean.ListBean> mDate ;
    public RvAdapterPlayReocrd(PlayReocrdModel data, Context context) {
        mModel = data;
//        dataBean = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_play_reocrd,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        PlayReocrdModel.DataBeanX.DataBean model = mModel.getData().getData().get(position);
        mDate = model.getList();
        holder.tvtime.setText(model.getTime()+"");
        holder.tvweek.setText(model.getWeek());
        holder.rvItem.setHasFixedSize(true);
        holder.rvItem.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        Item = new RvAdapterPlayRreocrdItem(mContext, mDate);
//        Item.setOnItemStateChangeListener(new RvAdapterPlayRreocrdItem.OnItemStateChangeListener() {
//            @Override
//            public void onItemClick(String courseId) {
//                if (mOnItemStateChangeListener != null){
//                    mOnItemStateChangeListener.onItemClick(courseId);
//                }
//            }
//        });
        Item.setOnItemStateChangeListener(new RvAdapterPlayRreocrdItem.OnItemStateChangeListener() {
            @Override
            public void onItemClick(String courseId) {
                if (mOnItemStateChangeListener != null){
                    mOnItemStateChangeListener.onItemClick(courseId);
                }
            }
        });
        holder.rvItem.setAdapter(Item);
    }

    @Override
    public int getItemCount() {
       return mModel.getData().getData().size();
//        return dataBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvtime;
        TextView tvweek;
        RecyclerView rvItem;
        //ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tvtime = itemView.findViewById(R.id.tv_time);
            tvweek = itemView.findViewById(R.id.tv_week);
            rvItem = itemView.findViewById(R.id.rv_item);

        }
    }

    public OnItemStateChangeListener mOnItemStateChangeListener;

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
//        void onFocusGetted(int position);
        void onItemClick(String courseId);
    }


}
