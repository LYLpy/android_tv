package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.geling.view.gelingtv_XX_tongbu.R;


public class ColorButtonLayout extends LinearLayout {
    private Context mContext;
    private int nSelectionColor =  Color.WHITE;
    private int mNoSelectionColor =Color.BLACK;
    private Drawable mNoSelectionColorDrawable;

    public ColorButtonLayout(Context context) {
        super(context);
        this.mContext =context;

    }

    public ColorButtonLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext =context;
        initView(context,attrs);
    }

    public ColorButtonLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext =context;
        initView(context,attrs);
    }

    private void initView(Context context ,AttributeSet attrs){

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorButtonLayout, 0, 0);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.ColorButtonLayout_v_selection_color) {
                nSelectionColor = ta.getColor(attr,nSelectionColor);
            } else if (attr == R.styleable.ColorButtonLayout_v_no_focus_color) {
                mNoSelectionColor = ta.getColor(attr, mNoSelectionColor);
            }else if (attr == R.styleable.ColorButtonLayout_v_selection_drawable) {
                mNoSelectionColorDrawable = ta.getDrawable(attr);
            }
        }
        ta.recycle();
        this.setBackground(mNoSelectionColorDrawable);
        if (mNoSelectionColorDrawable==null){
            this.setBackgroundColor(mNoSelectionColor);
        }


    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (mNoSelectionColorDrawable==null) {
            if (gainFocus) {
                scaleUp();
            } else {
                scaleDown();
            }
        }
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {

//        Path path = new Path();
//        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), 60, 60, Path.Direction.CW);
//        canvas.clipPath(path, Region.Op.REPLACE);
        super.dispatchDraw(canvas);
    }



    //1.08表示放大倍数,可以随便改
    private void scaleUp() {
//        ViewCompat.animate(this)
//                .setDuration(200)
//                .scaleX(1.08f)
//                .scaleY(1.08f)
//                .start();
        this.setBackgroundColor(nSelectionColor);
    }

    private void scaleDown() {
//        ViewCompat.animate(this)
//                .setDuration(200)
//                .scaleX(1f)
//                .scaleY(1f)
//                .start();
        this.setBackgroundColor(mNoSelectionColor);
    }
}
