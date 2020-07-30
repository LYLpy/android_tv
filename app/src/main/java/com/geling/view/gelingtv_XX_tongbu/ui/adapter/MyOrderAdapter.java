package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyOrderBean;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;

import java.util.List;
/**
 * 我的优惠卷Adapter
 * */
public class MyOrderAdapter extends BaseQuickAdapter<MyOrderBean.DataBeanX.DataBean, BaseViewHolder> {
    public MyOrderAdapter( @Nullable List<MyOrderBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_my_oreder_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyOrderBean.DataBeanX.DataBean item) {
        helper.itemView.setFocusable(true);
        helper.setText(R.id.fragment_my_order_text_order_number,"订单号："+item.getOrder_no());//订单号
        helper.setText(R.id.fragment_my_text_order_card,item.getSet_meal_name());//套餐名称
        helper.setText(R.id.fragment_my_order_text_amount_of_money,item.getOrder_amount());//订单价格
        helper.setText(R.id.adapter_my_order_text_payment,"￥"+item.getPay_amount());//实付金额
        helper.setText(R.id.adapter_my_order_text_time,item.getCreate_time());//订单时间
        getOrderState(helper,item);//是否已付款
        GroceryStoreUtils.GlideImag(mContext,item.getIcon(),helper.getView(R.id.adapter_my_order_img));//图片

    }

    private void getOrderState(BaseViewHolder helper,MyOrderBean.DataBeanX.DataBean item){
        //	订单状态0待付款，1已付款，2订单失效（该状态预留）
        switch (item.getOrder_status()){
            case 0:
                helper.setText(R.id.fragment_my_order_text_whether_to_pay,"待付款");
                break;
            case 1:
                helper.setText(R.id.fragment_my_order_text_whether_to_pay,"已付款");
                break;
            case 2:
                helper.setText(R.id.fragment_my_order_text_whether_to_pay,"订单失效");
                break;
        }
    }
}
