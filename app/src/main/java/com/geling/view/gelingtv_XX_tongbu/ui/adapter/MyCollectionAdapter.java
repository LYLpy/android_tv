package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionBean;

import com.geling.view.gelingtv_XX_tongbu.R;

import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import java.util.List;

/**
 * 我的收藏Adapter
 */
public class MyCollectionAdapter extends BaseQuickAdapter<MyCollectionBean.DataBeanX.AppUserCollectionListBean.DataBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;// 声明自定义的接口

    public MyCollectionAdapter( @Nullable List<MyCollectionBean.DataBeanX.AppUserCollectionListBean.DataBean> data) {
        super(R.layout.adapter_my_collection, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyCollectionBean.DataBeanX.AppUserCollectionListBean.DataBean item) {
        helper.itemView.setFocusable(true);
        GroceryStoreUtils.GlideImag(mContext,item.getIcon(),helper.getView(R.id.adapter_my_collection_img));//缩略图
        helper.setText(R.id.adapter_my_collection_text_name,item.getName());//名字
        //添加点击事件


    }

//    private interface OnItemClickListener{
//        public void onItemClick(View view,int postion);
//    }

}
