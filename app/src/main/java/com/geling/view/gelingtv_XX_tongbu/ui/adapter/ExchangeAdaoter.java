package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.ExchangeBean;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

public class ExchangeAdaoter extends BaseQuickAdapter<ExchangeBean.DataBeanX.AppExchangeListBean.DataBean, BaseViewHolder> {
    public ExchangeAdaoter( @Nullable List<ExchangeBean.DataBeanX.AppExchangeListBean.DataBean> data) {
        super(R.layout.adapter_exchange_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ExchangeBean.DataBeanX.AppExchangeListBean.DataBean item) {
        helper.setText(R.id.fragment_exchange_success,"兑换成功");
        GroceryStoreUtils.GlideImag(mContext,item.getIcon(),helper.getView(R.id.adapter_exchange_img));
//        switch (item.getType()){
//            case 1:
//                break;
//        }

        helper.setText(R.id.fragment_exchange_text_naem,item.getName());
        helper.setText(R.id.adapter_exchange_time,item.getUse_time());

    }
}
