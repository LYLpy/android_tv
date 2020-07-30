package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * Created by zlw on 2018/11/10.
 * 比较大的图片使用
 */

@SuppressLint("AppCompatCustomView")
public class ImageViewBgUtilsBig extends ImageView {
    public ImageViewBgUtilsBig(Context context) {
        super(context);
    }

    public ImageViewBgUtilsBig(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewBgUtilsBig(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            scaleUp();
        } else {
            scaleDown();
        }
    }

    //1.08表示放大倍数,可以随便改
    private void scaleUp() {
        ViewCompat.animate(this)
                .setDuration(200)
                .scaleX(1.01f)
                .scaleY(1.01f)
                .start();
//        this.setBackgroundResource(R.drawable.bg_border2);
        this.setBackgroundResource(R.drawable.bg_border6);

    }

    private void scaleDown() {
        ViewCompat.animate(this)
                .setDuration(200)
                .scaleX(1f)
                .scaleY(1f)
                .start();
        this.setBackground(null);
    }
}