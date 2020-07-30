package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * Created by zlw on 2018/11/10.
 */

@SuppressLint("AppCompatCustomView")
public class ImageViewBgUtils extends ImageView {

    //放大倍数
    private float mMultiple;
    private float mMultipleX;
    private float mMultipleY;
    public ImageViewBgUtils(Context context) {
        this(context,null);
    }

    public ImageViewBgUtils(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageViewBgUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageViewBgUtils);
        mMultiple  = typedArray.getFloat(R.styleable.ImageViewBgUtils_multiple,1.03f);
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
        float x = mMultipleX;
        float y = mMultipleY;
        if (mMultipleX == 0){
            x = mMultiple;
        }
        if (mMultipleY == 0){
            y = mMultiple;
        }
        ViewCompat.animate(this)
                .setDuration(200)
                .scaleX(x)
                .scaleY(y)
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