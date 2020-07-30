package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.ExchangeAdaoter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KeyboardView extends RelativeLayout {
    @BindView(R.id.view_keyboard_rec)
    RecyclerView vRecKeyboard;
    private List<String> mListKey = new ArrayList<>();
    private View mView;
    private Context mContext;

    private KeyBoardAdapter keyBoardAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    public KeyboardView(Context context) {
        super(context);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = ViewUtils.setInitView(context, R.layout.view_keyboard, KeyboardView.this);
        mContext = context;
        initView();
        addView(mView);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {
        keyBoardAdapter = new KeyBoardAdapter(mListKey);
        keyBoardAdapter.setHasStableIds(true);
        vRecKeyboard.setItemAnimator(null);
        staggeredGridLayoutManager= ViewUtils.setStaggeredGridLayoutManager(mContext,keyBoardAdapter,vRecKeyboard,4);
    }

    public void setData(List<String> list){
        mListKey = list;
        keyBoardAdapter.setNewData(mListKey);
    }


    public void setOnItemListener(TvRecyclerView.OnItemListener onItemListener){
//        vRecKeyboard.setOnItemListener(onItemListener);
    }

    public void setOnInBorderKeyEventListener(TvRecyclerView.OnInBorderKeyEventListener onInBorderKeyEventListener){
//        vRecKeyboard.setOnInBorderKeyEventListener(onInBorderKeyEventListener);
    }

    public void setSelection(int position){
        View view=null;
        if(null != staggeredGridLayoutManager) {
            view = staggeredGridLayoutManager.findViewByPosition(position);
        }
        if(null != view) {
            if(!hasFocus()) {
                //模拟TvRecyclerView获取焦点
                onFocusChanged(true, FOCUS_DOWN, null);
            }
            view.requestFocus();
        }
    }



    public KeyBoardAdapter getKeyBoardAdapter(){
        return keyBoardAdapter;
    }
    public RecyclerView getTvRecyclerView(){
        return vRecKeyboard;
    }
    public void notifyItemChanged(int p){
         keyBoardAdapter.notifyItemChanged(p);
    }

    //适配器
    public class KeyBoardAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private  int mPosition;
        public void setmPosition(int mPosition) {
            this.mPosition = mPosition;
        }
        public KeyBoardAdapter( @Nullable List<String> data) {
            super(R.layout.adapter_view_keyboard, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, String item) {
            helper.itemView.setFocusable(true);
           helper.setText(R.id.adapter_view_keyboard_key_text,item.toString());
           helper.itemView.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b){
                        helper.setTextColor(R.id.adapter_view_keyboard_key_text,helper.itemView.getResources().getColor(R.color.color_white));
                    }else {
                        helper.setTextColor(R.id.adapter_view_keyboard_key_text,helper.itemView.getResources().getColor(R.color.color_view_key_text_color));
                    }
                }
            });

        }
    }

}
