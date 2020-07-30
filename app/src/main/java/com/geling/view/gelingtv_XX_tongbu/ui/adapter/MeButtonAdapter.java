package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MeButtonBean;

import java.util.List;

public class MeButtonAdapter extends BaseQuickAdapter<MeButtonBean, BaseViewHolder> {
    public MeButtonAdapter( @Nullable List<MeButtonBean> data) {
        super(R.layout.adapter_me_button_item, data);
    }
    private  int mPosition;
    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, MeButtonBean item) {
        helper.setText(R.id.adapter_me_button_item_text,item.getButString());
        helper.setImageResource(R.id.adapter_me_button_item_img,item.getDrawable());
        if (mPosition == helper.getPosition()) {
            helper.getView(R.id.adapter_me_button_layout_but_bg).setBackgroundResource(R.color.color_blue);
        }else {
            helper.getView(R.id.adapter_me_button_layout_but_bg).setBackgroundResource(R.color.color_me_fragment_adapter_but_bg);
        }
    }

}
