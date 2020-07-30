package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/11------更新------
 */

public class CustomVideoView extends VideoView {

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        //我们重新计算高度
//        int width = getDefaultSize(0, widthMeasureSpec);
//        int height = getDefaultSize(0, heightMeasureSpec);
//        setMeasuredDimension(width, height);
    }
}
