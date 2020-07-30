package com.geling.view.gelingtv_XX_tongbu.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zlw on 2018/12/6.
 */

public class LinearLayoutManagerUtils extends LinearLayoutManager {
    public LinearLayoutManagerUtils(Context context) {
        super(context);
    }

    public LinearLayoutManagerUtils(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public LinearLayoutManagerUtils(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        if (direction == View.FOCUS_DOWN) {
            int pos = getPosition(focused);
            int size = getChildCount();
            int count = getItemCount();
            if (size > 0) {
                int startIndex = 0;
                if (size >= 6) {
                    startIndex = size - 6;
                }
                View view;
                for (int i = startIndex; i < size; i++) {
                    view = getChildAt(i);
                    if (view == focused) {
                        int lastVisibleItemPos = findLastCompletelyVisibleItemPosition();
                        if (pos > lastVisibleItemPos) { //lastVisibleItemPos==-1 ||
                            return focused;
                        } else {
                            int lastLineStartIndex = 0;
                            if (count >= 6) {
                                lastLineStartIndex = count - 6;
                            }
                            if (pos >= lastLineStartIndex && pos < count) { //最后一排的可见view时,返回当前view
                                return focused;
                            }
                            break;
                        }
                    }
                }
            } else {
                return focused;
            }
        }
        return super .onInterceptFocusSearch(focused, direction);
    }

}
