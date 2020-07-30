package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class MaxLinearLayout extends LinearLayout {
    public MaxLinearLayout(Context context) {
        super(context);
    }

    public MaxLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            Log.i("dwadawda","1");
            scaleUp();
        } else {
            Log.i("dwadawda","2");
            scaleDown();
        }
    }

    //1.08表示放大倍数,可以随便改
    private void scaleUp() {
        this.setScaleX(1.20f);
        this.setScaleY(1.20f);
//        ViewCompat.animate(this)
//                .setDuration(200)
//                .scaleX(1.08f)
//                .scaleY(1.08f)
//                .start();

    }

    private void scaleDown() {
        this.setScaleX(1.0f);
        this.setScaleY(1.0f);
//        ViewCompat.animate(this)
//                .setDuration(200)
//                .scaleX(1f)
//                .scaleY(1f)
//                .start();
//        this.setBackground(null);
    }
}
