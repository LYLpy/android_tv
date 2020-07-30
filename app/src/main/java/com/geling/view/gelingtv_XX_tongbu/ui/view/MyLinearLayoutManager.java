package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/26------更新------
 * 自定义的LinearLayoutManager，为了限定rv的高度
 */

public class MyLinearLayoutManager extends LinearLayoutManager{


    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setMeasuredDimension(Rect childrenBounds, int wSpec, int hSpec) {
        super.setMeasuredDimension(childrenBounds, wSpec, hSpec);

    }
}
