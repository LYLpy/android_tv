package com.geling.view.gelingtv_XX_tongbu.model.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/8------更新------
 */

public class RvAdapterLetter extends RecyclerView.Adapter<RvAdapterLetter.ViewHolder>{

    private List<String> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public RvAdapterLetter(List<String> data, Context context) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_letter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String str = mData.get(position);
        holder.tv.setText(String.valueOf(str));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
        holder.tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && mOnItemClickListener != null){
                    mOnItemClickListener.onItemFocusChange(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemFocusChange(int position);
    }
}
