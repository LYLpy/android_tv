package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionTitleBean;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;
/**
 * 我到收藏titleAdapter
 * */
public class MyCollectionTitleAdapter extends BaseQuickAdapter<MyCollectionTitleBean.DataBean, BaseViewHolder> {
    public MyCollectionTitleAdapter(@Nullable List<MyCollectionTitleBean.DataBean> data) {
        super(R.layout.adapter_my_collection_title, data);
    }
    private  int mPosition;
    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    private  boolean mTextColor;
    public void setmTextColor(boolean TextColor) {
        this.mTextColor = TextColor;
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyCollectionTitleBean.DataBean item) {
        helper.itemView.setFocusable(true);
        helper.setText(R.id.adapter_my_collection_title_name,item.getOption());
        if (mPosition == helper.getPosition()) {
            helper.setTextColor(R.id.adapter_my_collection_title_name,helper.itemView.getResources().getColor(R.color.color_blue));
            helper.setVisible(R.id.adapter_me_collection_bag,true);
        }else {
            helper.setVisible(R.id.adapter_me_collection_bag,false);
            helper.setTextColor(R.id.adapter_my_collection_title_name,helper.itemView.getResources().getColor(R.color.color_me_fragment_text));
        }
        if (mTextColor==false){
            helper.setTextColor(R.id.adapter_my_collection_title_name,helper.itemView.getResources().getColor(R.color.color_me_fragment_text));
        }
    }


}
