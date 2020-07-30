package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/5------更新------
 * 不需要焦点的跑马灯
 */

public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView{
    public MarqueeTextView(Context con) {
        super(con);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused){
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }
    /**
     * 一次移动完成后等待的时间(ms)
     */
    public static final int WAIT_TIME = 3000;
}
