package com.geling.view.gelingtv_XX_tongbu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/23------更新------
 */

public class ExitPagerAdapter extends PagerAdapter{

    private List<LinearLayout> mData;

    public ExitPagerAdapter(List<LinearLayout> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;//为了实现左右无限循环,数量写多一些
    }
    /**
     * 确定页视图是否与特定键对象关联
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;//基本固定这个写法
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = mInflater.inflate(R.layout.layout_exit_viewpager,false);
//        return super.instantiateItem(container, position);
        final int newPosition = position % mData.size();
//        LinearLayout imageView = mData.get(newPosition);
        View view = mData.get(newPosition);
        if (view.getParent() == container) {
            container.removeView(view);
        }
        ImageView imageView1 = view.findViewById(R.id.iv_1);
        ImageView imageView2 = view.findViewById(R.id.iv_2);
        ImageView imageView3 = view.findViewById(R.id.iv_3);
        ImageView imageView4 = view.findViewById(R.id.iv_4);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onPageItemViewClick(newPosition,0);
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onPageItemViewClick(newPosition,1);
                }
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onPageItemViewClick(newPosition,2);
                }
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onPageItemViewClick(newPosition,3);
                }
            }
        });
        // a. 把View对象添加到container中
        container.addView(view);
        // b. 把View对象返回给框架, 适配器
        return view;
    }

    public void setListener(OnPageItemViewClickListener listener) {
        mListener = listener;
    }

    private OnPageItemViewClickListener mListener;
    public interface OnPageItemViewClickListener {
        /**
         * @param positionPage 第几页
         * @param positionView 第几页的第几个view被点击
         */
        void onPageItemViewClick(int positionPage,int positionView);
    }
}
