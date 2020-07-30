package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCouponBean;

import com.geling.view.gelingtv_XX_tongbu.R;

import java.util.List;

/**
 * 我的优惠卷Adapter
 * */
public class MyCouponAdapter extends BaseQuickAdapter<MyCouponBean.DataBeanX.AppGiftListBean.DataBean, BaseViewHolder> {
    public MyCouponAdapter( @Nullable List<MyCouponBean.DataBeanX.AppGiftListBean.DataBean> data) {
        super(R.layout.adapter_my_coupon, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyCouponBean.DataBeanX.AppGiftListBean.DataBean item) {
        helper.setText(R.id.fragment_my_coupon_text_money,String.valueOf(item.getCouponCondition()));//优惠卷金额
        helper.setText(R.id.fragment_my_coupon_item_naem,item.getGiftName());//优惠卷名称
        helper.setText(R.id.fragment_my_coupon_time,item.getStartCouponDateTime()+"--"+item.getEndCouponDateTime());//优惠卷 日期时间
        setCouponUse(helper,item);
    }

    private void setCouponUse(BaseViewHolder helper, MyCouponBean.DataBeanX.AppGiftListBean.DataBean item){
//        优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
        switch (item.getCouponStatus()){
            case -1:
                helper.setText(R.id.fragment_my_coupon_use,"已过期");//优惠卷 使用状态
                break;
            case 0:
                helper.setText(R.id.fragment_my_coupon_use,"已使用");//优惠卷 使用状态
                break;
            case 1:
                helper.setText(R.id.fragment_my_coupon_use,"未使用");//优惠卷 使用状态
                break;
        }
    }

}
