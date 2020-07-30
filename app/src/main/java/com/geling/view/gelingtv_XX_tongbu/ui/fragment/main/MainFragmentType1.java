/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.geling.view.gelingtv_XX_tongbu.ui.fragment.main;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeModel;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.CourseDetailActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.WebViewActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseForbiddenDialog;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import com.geling.view.gelingtv_XX_tongbu.R;

/**
 * 首页
 */
public class MainFragmentType1 extends BaseFragment {

    public LinearLayout mLLRoot;
    public ImageView mSiv1;
    public ImageView mSiv2;
    public ImageView mSiv3;
    public ImageView mSiv4;
    public ImageView mSiv5;
    public ImageView mSiv6;
    public ImageView mSiv7;
    private int mFragmentType = 1;
    private HomeModel mModel;//首页模块数据
    private LinearLayout mLlRootView;
    private List<ImageView> mIvList = new ArrayList<>();//首页ImageView集合
    private XBanner mXBanner;
    private LinearLayout mLlXBannerParent;
    private List<HomeModel.Databean.RecommendationDatabean> mHomeModelList = new ArrayList<>();//首页非Banner数据
    private List<HomeModel.Databean.RecommendationDatabean.LoopDatabean>  mHomeBannerList;//首页Banner数据
    private int positionX;//banner图位置当前
    private final String indexModuleId = "1";
    @Override
    protected void initData() {
        if (mModel != null){
            /**
             * 当首页使用1、4、5模板时，有Banner图，没有视频
             */
                LogUtil.e("__initBannerModeData","initBannerModeData");
                List<HomeModel.Databean.RecommendationDatabean> list = mModel.getData().getRecommendation_data();
                int position = 0;
                for (int i = 0; i < list.size(); i++) {
                    HomeModel.Databean.RecommendationDatabean databean = list.get(i);
                    LogUtil.e("__initBannerModeData_getIs_loop" + i,databean.getIs_loop());
                    if (databean.getIs_loop().equals("1")){
                        //表示是轮播图
                        mHomeBannerList = databean.getLoop_data();
                        initBannerPage();
                    } else {
                        //不是Banner则添加到非Banner的List里面
                        mHomeModelList.add(databean);
                        position ++;
                        LogUtil.e("__initBannerModeData_Is_loop_position+ i",position + "");
                        LogUtil.e("__initBannerModeData_Is_loop_position+ i",position + ":" + databean.getBind_url());
                        LogUtil.e("__initBannerModeData_Is_loop_position+ i",position + ":" + databean.getRecommendation_img());
                    }
                }
                LogUtil.e("__initBannerModeData",mHomeModelList.size() + "");
                for (int i = 0; i < mHomeModelList.size(); i++) {
                    HomeModel.Databean.RecommendationDatabean databean = mHomeModelList.get(i);
                    LogUtil.e("__initBannerModeData_img",databean.getRecommendation_img());
                    LogUtil.e("__initBannerModeData_img_replace",databean.getRecommendation_img().replace("iptv3.com","192.168.18.115"));
                    if (i < mIvList.size()){
//                                String imgUrl = "http://139.9.7.208:8989/Public/Uploads/images/2019-11-16/5dcf62d68233a.jpg";
//                GroceryStoreUtils.GlideImag(MainActivity3.this,imgUrl, mIvListHome.get(i));
                        GroceryStoreUtils.GlideImag(getActivity(),databean.getRecommendation_img(),mIvList.get(i));
                    }
                }
        }
    }
    private void initBannerPage() {
        // 初始化XBanner中展示的数据
        // 为XBanner绑定数据
        LogUtil.e("__initVivoPage",mHomeBannerList.size() + "");
        mXBanner.setData(mHomeBannerList, null);
        // XBanner适配数据
        mXBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                LogUtil.e("__initVivoPage_" + position,mHomeBannerList.get(position).getRecommendation_img());
                LogUtil.e("__initVivoPage_" + position,mHomeBannerList.get(position).getRecommendation_img().replace("iptv3.com","192.168.18.115"));
//                String imgUrl = MainApp.imgUrl;
                String imgUrl = mHomeBannerList.get(position).getRecommendation_img();
                GroceryStoreUtils.GlideImag(getActivity(),imgUrl,(ImageView) view);
//                GroceryStoreUtils.GlideImag(MainActivity3.this, mHomeBannerList.get(position).getRecommendation_img(),(ImageView) view);
            }
        });
        mXBanner.setAllowUserScrollable(false);
        mXBanner.setmAutoPlayAble(true);
        // 设置XBanner的页面切换特效
        mXBanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        mXBanner.setPageChangeDuration(1000);
        mXBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                LogUtil.e("__onPageSelected",position + "");
                // 把当前显示的position传递出去
                positionX = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home_1;
    }

    @Override
    protected void initView(View view) {
        mIvList = new ArrayList<>();
        mLlRootView = view.findViewById(R.id.ll_root_view_home_1);
//        mLLRoot.setVisibility(View.VISIBLE);
        mSiv1 = view.findViewById(R.id.siv1_home_1);
        mSiv2 = view.findViewById(R.id.siv2_home_1);
        mSiv3 = view.findViewById(R.id.siv3_home_1);
        mSiv4 = view.findViewById(R.id.siv4_home_1);
        mSiv5 = view.findViewById(R.id.siv5_home_1);
        mSiv6 = view.findViewById(R.id.siv6_home_1);
        mSiv7 = view.findViewById(R.id.siv7_home_1);

        mIvList.add(mSiv1);
        mIvList.add(mSiv2);
        mIvList.add(mSiv3);
        mIvList.add(mSiv4);
        mIvList.add(mSiv5);
        mIvList.add(mSiv6);
        mIvList.add(mSiv7);
        mXBanner = view.findViewById(R.id.xbanner_home_1);
        mLlXBannerParent = view.findViewById(R.id.ll_xbanner_home_parent_1);

        mLlXBannerParent.setOnClickListener(this);
        mSiv1.setOnClickListener(this);
        mSiv2.setOnClickListener(this);
        mSiv3.setOnClickListener(this);
        mSiv4.setOnClickListener(this);
        mSiv5.setOnClickListener(this);
        mSiv6.setOnClickListener(this);
        mSiv7.setOnClickListener(this);

        mLlXBannerParent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
//                    mIsFocusOnNavLeft = false;
//                    mIsFocusOnMainNav = false;
//                    mIsUpToNav = true;
//                    mIsDownToNav = false;
                    if(mOnFocusChangeLiestener != null){
                        mOnFocusChangeLiestener.onRecommendFocusTop();
                    }
                    ViewCompat.animate(mXBanner)
                            .setDuration(200)
                            .scaleX(1.02f)
                            .scaleY(1.02f)
                            .start();
                    ViewCompat.animate(mLlXBannerParent)
                            .setDuration(200)
                            .scaleX(1.02f)
                            .scaleY(1.02f)
                            .start();
                }else {
                    ViewCompat.animate(mXBanner)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                    ViewCompat.animate(mLlXBannerParent)
                            .setDuration(200)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }
        });
        mSiv1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
//                    if (mAdapterNav != null){
//                        mAdapterNav.notifyDataSetChanged();
//                    }
                    if(mOnFocusChangeLiestener != null){
                        mOnFocusChangeLiestener.onRecommendFocusTop();
                    }
                }
            }
        });
        mSiv2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if(mOnFocusChangeLiestener != null){
                        mOnFocusChangeLiestener.onRecommendFocusTop();
                    }
                }
            }
        });
        mSiv3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if(mOnFocusChangeLiestener != null){
                        mOnFocusChangeLiestener.onRecommendFocusBottom();
                    }
                }
            }
        });
        mSiv4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if(mOnFocusChangeLiestener != null){
                        mOnFocusChangeLiestener.onRecommendFocusBottom();
                    }
                }
            }
        });
        mSiv5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
//                    if (mAdapterNav != null){
//                        mAdapterNav.notifyDataSetChanged();
//                    }
                    if (hasFocus){
                        if(mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onRecommendFocusBottom();
                        }
                    }
                }

            }
        });
        mSiv6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (hasFocus){
                        if(mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onRecommendFocusBottom();
                        }
                    }
                }

            }
        });
        mSiv7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (hasFocus){
                        if(mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onRecommendFocusBottom();
                        }
                    }
                }
            }
        });
    }

    @Override
    public View getFirstView() {
        return mSiv1;
    }

    @Override
    public void onClick(View view) {
        try {
            Intent intent = null;
            String type = "";
            switch (view.getId()){
                case R.id.siv1_home_1:
                    type = mHomeModelList.get(0).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(0).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(0).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(0).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(0).getCourse_id());
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(0).getCourse_id() + "");
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,1);
                    break;
                case R.id.siv2_home_1:
                    type = mHomeModelList.get(1).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(1).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(1).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(1).getBind_url(),null);
                    }
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,3);
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(1).getCourse_id());
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(1).getCourse_id() + "");
                    break;
                case R.id.siv3_home_1:
                    type = mHomeModelList.get(2).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(2).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(2).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(2).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(2).getCourse_id());
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,4);
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(2).getCourse_id() + "");
                    break;
                case R.id.siv4_home_1:
                    type = mHomeModelList.get(3).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(3).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(3).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(3).getBind_url(),null);
                    }
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,5);
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(3).getCourse_id());
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(3).getCourse_id() + "");
                    break;
                case R.id.siv5_home_1:
                    type = mHomeModelList.get(4).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(4).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(4).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(4).getBind_url(),null);
                    }
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,6);
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(4).getCourse_id());
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(4).getCourse_id() + "");
                    break;
                case R.id.siv6_home_1:
                    type = mHomeModelList.get(5).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(5).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(5).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(5).getBind_url(),null);
                    }
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,7);
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(5).getCourse_id());
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(5).getCourse_id() + "");
                    break;
                case R.id.siv7_home_1:
                    type = mHomeModelList.get(6).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeModelList.get(6).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mHomeModelList.get(6).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(6).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(),CourseDetailActivity.class);
//                    intent.putExtra("courseId",mHomeModelList.get(6).getCourse_id());
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_POSITION,8);
                    LogUtil.e("__onClick","getCourse_id:" + mHomeModelList.get(6).getCourse_id() + "");
                    break;
                case R.id.ll_xbanner_home_parent_1:
                    type = mHomeBannerList.get(positionX).getBind_type();
                    if (type.equals("3")){//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mHomeBannerList.get(positionX).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        intent.putExtra("courseId",mHomeBannerList.get(positionX).getBind_url());
                        isCourseForbidden(mHomeBannerList.get(positionX).getBind_url(),null);
                    }
                    mHttpHelper.recommendClick(indexModuleId, HttpHelper.RECOMMEND_CLICK_TYPE_ORDER,positionX + 1);
                    LogUtil.e("__onClick_courseId","getCourse_id:" + mHomeBannerList.get(positionX).getBind_url() + "");
                    break;
            }
        }catch (Exception e){
//            ToastUtil.showToast("该课程正在上传，请稍候重试！");
//            LogUtil.e("__Exception","该课程正在上传，请稍候重试！");
            new CourseForbiddenDialog().builder(getActivity(),"").show();
        }
    }

    public int getFragmentType() {
        return mFragmentType;
    }
    @Override
    public void setData(Object data) {
        mModel = (HomeModel) data;
    }
}
