package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by zlw on 2019/1/19.
 */

public class TvRecyclerViewTiemrUtils extends app.com.tvrecyclerview.TvRecyclerView {
    private  long mTimeDelay,mTimeLast,mTimeSpace;
    public TvRecyclerViewTiemrUtils(Context context) {
        super(context);
    }

    public TvRecyclerViewTiemrUtils(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvRecyclerViewTiemrUtils(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

        public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == 0) {
            long nowTime = SystemClock.elapsedRealtime();
            this.mTimeDelay = nowTime - this.mTimeLast;
            this.mTimeLast = nowTime;
            if(this.mTimeSpace <= 180L && this.mTimeDelay <= 180L) {
                this.mTimeSpace += this.mTimeDelay;
                return true;
            }
            this.mTimeSpace = 0L;
        }
        return super.dispatchKeyEvent(event);
    }
}
