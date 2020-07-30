package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import com.geling.view.gelingtv_XX_tongbu.utils.GridLayoutManagerUtils;

import butterknife.ButterKnife;


/**
 * Created by zlw on 2019/11/19.
 */

public class ViewUtils {
    public static View setInitView(Context context, Integer layout , RelativeLayout viewContext) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null);
        ButterKnife.bind(viewContext, view);

        return view;
    }
    public static void setRecyclerViewAdapter(Context context, BaseQuickAdapter recyclerViewAdapter, RecyclerView view, int orientation){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(orientation);
        view.setLayoutManager(linearLayoutManager);
        view.setAdapter(recyclerViewAdapter);
    }

    public static void setReViewGridAdapter(Context context, BaseQuickAdapter recyclerViewAdapter, TvRecyclerView view, int spanCount){
        GridLayoutManagerUtils linearLayoutManager = new GridLayoutManagerUtils(context,spanCount);
        view.setLayoutManager(linearLayoutManager);
        view.setAdapter(recyclerViewAdapter);
    }
    public static StaggeredGridLayoutManager setStaggeredGridLayoutManager(Context context, BaseQuickAdapter recyclerViewAdapter, RecyclerView view, int spanCount){
        StaggeredGridLayoutManager horizontalManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        view.setLayoutManager(horizontalManager);
        view.setAdapter(recyclerViewAdapter);
        return horizontalManager;
    }


}
