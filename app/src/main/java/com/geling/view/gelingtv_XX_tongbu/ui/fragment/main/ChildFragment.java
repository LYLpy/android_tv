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
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeOtherModel;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.WebViewActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterChildNav;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterChildSubject;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseForbiddenDialog;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 幼儿、专题、初中
 */
public class ChildFragment extends BaseFragment {
    
    public static ChildFragment newInstance(String content) {
        ChildFragment f = new ChildFragment();
        Bundle b = new Bundle();
        b.putString("content", content);
        f.setArguments(b);
        return f;
    }
    private int mFragmentType = 2;
    // 幼儿、专题、初中相关控件
//    TvRecyclerViewTiemrUtils mRvNav;
    private RecyclerView mRvNav;//导航
    private RecyclerView mRvSubject;//课程页内容
    private HomeOtherModel mModel;//页面数据
    private List<HomeOtherModel.Databean.RecommendationDatabean> mRecData;//推荐位数据
    //推荐
    LinearLayout mLlRecommend;
    ImageView mSiv1Recommend;
    ImageView mSiv2Recommend;
    ImageView mSiv3Recommend;
    ImageView mSiv4Recommend;
    ImageView mSiv5Recommend;
    ImageView mSiv6Recommend;
    ImageView mSiv7Recommend;
    ImageView mSiv8Recommend;
    ImageView mIvNext;
    //课程
//    RelativeLayout mRlContentSubject;//课程父view
    RelativeLayout mRlContentSubjectRv;//rv的课程父view
    ImageView mSiv1Subject;
    ImageView mSiv2Subject;
    ImageView mSiv3Subject;
    ImageView mSiv4Subject;
    ImageView mSiv5Subject;
    ImageView mSiv6Subject;
    ImageView mSiv7Subject;
    ImageView mSiv8Subject;
    List<ImageView> mIvSubjectList = new ArrayList<>();//幼儿课程iv集合

    private List<ImageView> mIvList = new ArrayList<>();//幼儿等ImageView集合
    private RvAdapterChildNav mAdapterNav;
//    private List<ChildNavModel> mDataNav = new ArrayList<>();
    private RecyclerView.LayoutManager mManager;
    private int mNavSelectedPosition;//选中位置
    private int mNavCurrentSelectedPosition;//复用之后，目前的位置
    private RvAdapterChildSubject mAdapterSubject;
    List<HomeOtherModel.Databean.CourseListDatabean.CourseNavbean> dataNav;//左边导航栏数据
    private int mRvHeight;//rv的高
    private int mRvNeedHeight;//实际需要的高
    private int mScrollY;//rv在y轴总共滑动的距离
    private int mItemHeightPx;
    private int mItemHeightDp;//item高度+marginTop
    private int mRvSubjectSelectedPosition;
    private int mShowPage;
    private boolean mIsChangeSubject = true;//是否切换课程
    private int mRecommendFocusPosition;//当前在推荐页选中的位置，为了从首页向下翻页的，设置选中位置
    @Override
    protected void initData() {

//        Bundle bundle = getArguments();
//        String content = bundle.getString("content");
//        LogUtil.e("__initData",String.valueOf(content));
        mItemHeightDp = getActivity().getResources().getDimensionPixelOffset(R.dimen.sw_75dp);
        mItemHeightPx = GroceryStoreUtils.dip2px(mItemHeightDp);//3是随便加的，不然移动一次不够一个item
        if (mModel != null){
            try {
                //设置推荐位数据
                mRecData = mModel.getData().getRecommendation_data();
                LogUtil.e("__initData_child_mModel != null",String.valueOf(mModel != null));
                LogUtil.e("__initData_child_recData.size",String.valueOf(mRecData.size()));
                LogUtil.e("__initData_child_iv.size",String.valueOf(mIvList.size()));
                for (int i = 0; i < mIvList.size(); i++) {
//              GroceryStoreUtils.GlideImag(MainActivity3.this,data.get(i).getRecommendation_img().replace("ipti3.0.com","192.168.18.105"), mIvListChild.get(i));
//                    String imgUrl = MainApp.imgUrl;
                    String imgUrl = mRecData.get(i).getRecommendation_img();
                    LogUtil.e("__initData_child_i",String.valueOf(i));
//                    GroceryStoreUtils.GlideImag(getActivity(),imgUrl, mIvList.get(i));
                    LogUtil.e("__initData_child_i",imgUrl);
                    GroceryStoreUtils.GlideImagHasPlaceHolder(getActivity(),imgUrl, mIvList.get(i),R.drawable.placeholder_course);
                }
                //设置左边导航栏数据
                if (mModel.getData().getCourse_list_data() == null){
                    //课程没有数据，给个空集合，让Adapter展示推荐位
                    dataNav = new ArrayList<>();
                }else {
                    //课程有数据，正常处理
                    dataNav = mModel.getData().getCourse_list_data().getCourse_nav();
                }
                LogUtil.e("__onGlobalLayout_height_dip2px_size",dataNav.size() + "__AAAA");
                mRvNav.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mRvHeight = mRvNav.getHeight();
                        mRvNeedHeight = GroceryStoreUtils.dip2px(75f * (dataNav.size() + 1));
//                        LogUtil.e("__onGlobalLayout_mRvHeight",mRvHeight + "__HHH");
//                        LogUtil.e("__onGlobalLayout_mRvNeedHeight",mRvNeedHeight + "__HHH");
//                        LogUtil.e("__onGlobalLayout_itemHeight_px",mItemHeightPx + "__HHH");
//                        LogUtil.e("__onGlobalLayout_height_dip2px",GroceryStoreUtils.dip2px(75f * (dataNav.size() + 1)) + "__HHH");
//                        LogUtil.e("__onGlobalLayout_height_dip2px_75",GroceryStoreUtils.dip2px(75f) + "__HHH");
                        mRvNav.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
//                mRvNav.setItemViewCacheSize(0);
                mAdapterNav = new RvAdapterChildNav(dataNav,getActivity());
                mAdapterNav.setHasStableIds(true);
                mRvNav.setItemAnimator(null);
                mRvNav.setAdapter(mAdapterNav);
                mRvNav.setLayoutManager(new LinearLayoutManager(getActivity()));
                mAdapterNav.setOnItemSelectedListener(new RvAdapterChildNav.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int position, View view) {
//                        mIsOnRecommendBottom = false;
//                        mIsChildSubject = false;
//                        mIsPrimarySchoolSubject = false;
//                        mIsLeftToNavChild = false;
//                        mIsLeftToNavPrimarySchool = false;
//                        mIsFocusOnNavLeft = true;
                        LogUtil.e("__onItemSelected",position + "");
                        LogUtil.e("__onItemSelected_getChildCount",mRvNav.getChildCount() + "");
//                        LogUtil.e("__onItemSelected_getScrollY",mRvNav.() + "");
                        mFmFocusPart = FOCUS_ON_PART_NAV;
                        mFmFocusTypeHorizontal = FOCUS_ON_NAV;
                        mFmFocusTypeVertical = FOCUS_ON_TOP;
                        mAdapterNav.setFocusOn(true);
                        mAdapterNav.setSelectedPosition(position);
                        mNavSelectedPosition = position;
                        if (position == dataNav.size()){
//                            mIvNext.setVisibility(View.GONE);
                        }else {
                            mIvNext.setVisibility(View.VISIBLE);
                        }
                        if (position == 0){
//                          mIsUpToNav = true;
//                          mRvAdapterMainNav.notifyDataSetChanged();
//                          mTvRvSubjectChild.setVisibility(View.GONE);
                            mRlContentSubjectRv.setVisibility(View.GONE);
                            mLlRecommend.setVisibility(View.VISIBLE);
                            LogUtil.e("__mFmFocusTypeVertical_mAdapterNav","FOCUS_ON_TOP");
                            LogUtil.e("__activity_mAdapterNav__" + position,"onRecommendFocusTop");
                            if (mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onNavTop();
                            }
//                            mFlNotData.setVisibility(View.GONE);
                        }else {
//                            mRlContentSubject.setVisibility(View.GONE);
//                            mLlRecommend.setVisibility(View.VISIBLE);
//                            mIsUpToNav = false;
                            LogUtil.e("__activity_mAdapterNav__" + position,"onRecommendFocusBottom");
                            if (mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onNavBottom();
                            }
                            if (mIsChangeSubject){
                                //0是推荐位，不在List里面，要拿到实际数据需要减1
                                showSubject(position - 1);
                            }
                        }
                        mIsChangeSubject = true;
                        mRvNav.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                LogUtil.e("__onScrolled_dy",dy + "");
                                LogUtil.e("__onScrolled_dx",dx + "");
                                //监听滑动完毕之后，获取选中位置
                                for (int i = 0; i < mRvNav.getChildCount(); i++) {
                                    if (mRvNav.getChildAt(i).hasFocus()){
                                        mNavCurrentSelectedPosition = i;
                                        LogUtil.e("__NavPosition_mNavCurrentSelectedPosition_onScrolled",mNavCurrentSelectedPosition + "");
                                    }
                                }
                            }
                        });
                        //当处在开头或者结尾的时候，左右移动并不会使Rv移动，此时就需要在这里得到最后获取焦点的实际位置
                        for (int i = 0; i < mRvNav.getChildCount(); i++) {
                            if (mRvNav.getChildAt(i).hasFocus()){
                                mNavCurrentSelectedPosition = i;
                                LogUtil.e("__NavPosition_mNavCurrentSelectedPosition",mNavCurrentSelectedPosition + "");
                            }
                        }
                    }
                });
//                mLlRootViewHome.setVisibility(View.GONE);
//                mLlRootViewPrimarySchool.setVisibility(View.GONE);
//                mLlRootViewChild.setVisibility(View.VISIBLE);
//                mRlContentSubjectChild.setVisibility(View.GONE);
//                mLlRecommendChild.setVisibility(View.VISIBLE);
//                mFlNotData.setVisibility(View.GONE);
            }catch (Exception e){

            }

        }
    }
    private int mCurrentPage;//儿童界当前页码
    private int mTotalPage;//总页数
    private int mTotalCount;//总数量
    List<List<HomeOtherModel.Databean.CourseListDatabean.CourseDatabean.PageDatabean>> mTotalSubject;//选中科目总数据
    List<HomeOtherModel.Databean.CourseListDatabean.CourseDatabean.PageDatabean> mCurrentSubject;//选中科目当前数据

    private void showSubject(int position) {
        LogUtil.e("__showSubject_position",position + "");
        LogUtil.e("__showSubject_dataNav.size()",dataNav.size() + "");
        mCurrentPage = 0;
        //先获取选中科目的数据
        HomeOtherModel.Databean.CourseListDatabean.CourseDatabean courseListDatabean = mModel.getData().getCourse_list_data().getCourse_data().get(position);
//        mTotalSubject = courseListDatabean.getPage_data();
        mTotalSubject = courseListDatabean.getPage_data();
        mCurrentSubject = mTotalSubject.get(mCurrentPage);
        mTotalPage = courseListDatabean.getTotal_pages();//总页数
        mTotalCount = courseListDatabean.getTotal_count();//总条目数
        LogUtil.e("__showSubject_mTotalPage",mTotalPage + "");
        if (position == dataNav.size() - 1){//选中导航栏最后一个item
            //表示是最后一页
            if (mTotalPage == 1){
                mIvNext.setVisibility(View.GONE);
            }
        }
        //设置课程内容
        mAdapterSubject = new RvAdapterChildSubject(mCurrentSubject,getActivity());
        mRvSubject.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRvSubject.setAdapter(mAdapterSubject);
        LogUtil.e("__showSubject_mCurrentSubject_" + position,mCurrentSubject.size() + "");
        LogUtil.e("__showSubject_getChildCount_" + position,mRvSubject.getChildCount() + "");
        mAdapterSubject.setOnItemSelectedListener(new RvAdapterChildSubject.OnItemStateChangeListener() {
            @Override
            public void onItemClidkListener(int position) {
                LogUtil.e("__subject_onItemClid_" + position,mCurrentSubject.get(position).getCourse_id());
                isCourseForbidden(mCurrentSubject.get(position).getCourse_id(),null);
//                Intent intent = new Intent(getActivity(),CourseDetailActivity.class);
//                intent.putExtra("courseId",mCurrentSubject.get(position).getCourse_id());
//                startActivity(intent);
            }

            @Override
            public void onItemFocusChange(int position, boolean isFocus) {
                if(isFocus){
                    mFmFocusPart = FOCUS_ON_PART_SUBJECT;
                    mIsChangeSubject = false;
                    mRvSubjectSelectedPosition = position;
                    LogUtil.e("__mRvSubjectSelectedPosition_111",mRvSubjectSelectedPosition + "");
                        if(position < 4){
                            LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_TOP");
                            mFmFocusTypeVertical = FOCUS_ON_TOP;
                            if (mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onSubjectFocusTop();
                            }
                        }else {
                            LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_BOTTOM");
                            mFmFocusTypeVertical = FOCUS_ON_BOTTOM;
                            mOnFocusChangeLiestener.onSubjectFocusBottom();
                        }
                        if (position == 0 || position == 4){
                            if (mAdapterNav != null){
                                mAdapterNav.setFocusOn(false);
                                mAdapterNav.notifyDataSetChanged();
                            }
                            LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_LEFT");
                            mFmFocusTypeHorizontal = FOCUS_ON_LEFT;
                        }else {
                            LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_RIGHT");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                        }
                }
            }
        });
        //设置图片
//        for (int j = 0; j < mIvChildSubjectList.size(); j++) {
//            LogUtil.e("__setIvChild_showChildSubject_j",j + "");
//            LogUtil.e("__setIvChild_showChildSubject_mChildPageDataCurrent.size()", mCurrentSubject.size() + "");
//            if(j < mCurrentSubject.size()){
//                mIvChildSubjectList.get(j).setVisibility(View.VISIBLE);
//                HomeOtherModel.Databean.CourseListDatabean.CourseDatabean.PageDatabean databean = mCurrentSubject.get(j);
//                GroceryStoreUtils.GlideImag(MainActivity3.this,databean.getCourse_img(),mIvListHome.get(j));
//                String imgUrl = "http://139.9.7.208:8989/Public/Uploads/images/2019-11-16/5dcf62d68233a.jpg";
//                GroceryStoreUtils.GlideImag(getActivity(),imgUrl, mIvChildSubjectList.get(j));
//            }else {
//                mIvChildSubjectList.get(j).setVisibility(View.GONE);
//            }
//        }

//        mRvAdapterChild = new RvAdapterChildSubject(mChildSubjectTotalCurrent,this);
////        mRvAdapterChild = new RvAdapterChildSubject(mTotalSubject,this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
//        mTvRvSubjectChild.setLayoutManager(layoutManager);
//        mTvRvSubjectChild.setAdapter(mRvAdapterChild);
//        mRvAdapterChild.setOnItemSelectedListener(new RvAdapterChildSubject.OnItemStateChangeListener() {
//            @Override
//            public void onItemClidkListener(int position) {
//                LogUtil.e("__child_click", mChildSubjectTotalCurrent.get(position).getcourseId());
//                Intent intent = new Intent();
//                intent.putExtra("courseId", mChildSubjectTotalCurrent.get(position).getcourseId());
//            }
//
//            @Override
//            public void onItemFocusChange(int position, boolean isFocus) {
//                if (isFocus){
//                    mChildFocusPosition = position;
//                    mIsChildSubject = true;
//                    mIsPrimarySchoolSubject = true;
//                }
//            }
//        });
        //隐藏推荐位，展示课程位
//        mRlContentSubject.setVisibility(View.VISIBLE);
        mRlContentSubjectRv.setVisibility(View.VISIBLE);
        mLlRecommend.setVisibility(View.GONE);
//        mFlNotData.setVisibility(View.GONE);
//        if (mChildTotalPage == 1){
//            mSivNextChild.setVisibility(View.GONE);
//        }else {
//            mSivNextChild.setVisibility(View.VISIBLE);
//        }
    }

    private void pageDownSubject(){
        mCurrentPage ++;
        //先获取选中科目的数据
//        HomeOtherModel.Databean.CourseListDatabean.CourseDatabean courseListDatabean = mModel.getData().getCourse_list_data().getCourse_data().get(position);
//        mTotalSubject = courseListDatabean.getPage_data();
//        mTotalSubject = courseListDatabean.getPage_data();
          mCurrentSubject = mTotalSubject.get(mCurrentPage);
//        mTotalPage = courseListDatabean.getTotal_pages();//总页数
//        mTotalCount = courseListDatabean.getTotal_count();//总条目数
        calculateFlipSelectedPosition();
        rvSubjectPositionViewGetFocus();
        LogUtil.e("__mGetFocusPosition_pageDownSubject",mGetFocusPosition + "");
        if (mNavSelectedPosition == dataNav.size() && mCurrentPage == mTotalPage - 1){
            //左边导航栏已经选中最后一个，并且已经是最后一页，下一页按钮去掉
            mIvNext.setVisibility(View.GONE);
        }
        //设置课程内容
        mAdapterSubject = new RvAdapterChildSubject(mCurrentSubject,getActivity());
        mRvSubject.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRvSubject.setAdapter(mAdapterSubject);
        mAdapterSubject.setOnItemSelectedListener(new RvAdapterChildSubject.OnItemStateChangeListener() {
            @Override
            public void onItemClidkListener(int position) {
                LogUtil.e("__subject_onItemClid_" + position,mCurrentSubject.get(position).getCourse_id());
                isCourseForbidden(mCurrentSubject.get(position).getCourse_id(),null);
            }

            @Override
            public void onItemFocusChange(int position, boolean isFocus) {
                if(isFocus){
                    mFmFocusPart = FOCUS_ON_PART_SUBJECT;
                    mRvSubjectSelectedPosition = position;
                    LogUtil.e("__mRvSubjectSelectedPosition_222",mRvSubjectSelectedPosition + "");
                    mIsChangeSubject = false;
                    if(position < 4){
                        LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_TOP");
                        mFmFocusTypeVertical = FOCUS_ON_TOP;
                        if (mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onSubjectFocusTop();
                        }
                    }else {
                        LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_BOTTOM");
                        mFmFocusTypeVertical = FOCUS_ON_BOTTOM;
                        mOnFocusChangeLiestener.onSubjectFocusBottom();
                    }
                    if (position == 0 || position == 4){
                        if (mAdapterNav != null){
                            mAdapterNav.setFocusOn(false);
                            mAdapterNav.notifyDataSetChanged();
                        }
                        LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_LEFT");
                        mFmFocusTypeHorizontal = FOCUS_ON_LEFT;
                    }else {
                        LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_RIGHT");
                        mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                    }
                }
            }
        });
    }

    private void pageUpSubject(){
        mCurrentPage --;
        //先获取选中科目的数据
//        HomeOtherModel.Databean.CourseListDatabean.CourseDatabean courseListDatabean = mModel.getData().getCourse_list_data().getCourse_data().get(position);
//        mTotalSubject = courseListDatabean.getPage_data();
//        mTotalSubject = courseListDatabean.getPage_data();
        mCurrentSubject = mTotalSubject.get(mCurrentPage);
//        mTotalPage = courseListDatabean.getTotal_pages();//总页数
//        mTotalCount = courseListDatabean.getTotal_count();//总条目数
            //左边导航栏已经选中最后一个，并且已经是最后一页，下一页按钮去掉
            mIvNext.setVisibility(View.VISIBLE);
        LogUtil.e("__mRvSubjectSelectedPosition_pageUpSubject111",mRvSubjectSelectedPosition + "");
//        mGetFocusPosition = mRvSubjectSelectedPosition;
        int pageSelectedPosition = mRvSubjectSelectedPosition + 4;
        int mCurrentSubjectSize = mCurrentSubject.size();
        if (mCurrentSubjectSize >= pageSelectedPosition){
            mGetFocusPosition = pageSelectedPosition;
        }else {
            if (mCurrentSubjectSize > 4){
                mGetFocusPosition = mCurrentSubjectSize - 1;
            }else {
                if (mCurrentSubjectSize - 1 >= mRvSubjectSelectedPosition){
                    mGetFocusPosition = mRvSubjectSelectedPosition;
                }else {
                    mGetFocusPosition = mCurrentSubjectSize - 1;
                }
            }
        }
        LogUtil.e("__mGetFocusPosition_pageUpSubject",mGetFocusPosition + "");
        rvSubjectPositionViewGetFocus();
        //设置课程内容
        mAdapterSubject = new RvAdapterChildSubject(mCurrentSubject,getActivity());
        mRvSubject.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRvSubject.setAdapter(mAdapterSubject);
        mAdapterSubject.setOnItemSelectedListener(new RvAdapterChildSubject.OnItemStateChangeListener() {
            @Override
            public void onItemClidkListener(int position) {
                LogUtil.e("__subject_onItemClid_" + position,mCurrentSubject.get(position).getCourse_id());
                isCourseForbidden(mCurrentSubject.get(position).getCourse_id(),null);
            }

            @Override
            public void onItemFocusChange(int position, boolean isFocus) {
                if(isFocus){
                    mFmFocusPart = FOCUS_ON_PART_SUBJECT;
                    mIsChangeSubject = false;
                    mRvSubjectSelectedPosition = position;
                    LogUtil.e("__mRvSubjectSelectedPosition_333",mRvSubjectSelectedPosition + "");
                    if(position < 4){
                        LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_TOP");
                        mFmFocusTypeVertical = FOCUS_ON_TOP;
                        if (mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onSubjectFocusTop();
                        }
                    }else {
                        LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_BOTTOM");
                        mFmFocusTypeVertical = FOCUS_ON_BOTTOM;
                        mOnFocusChangeLiestener.onSubjectFocusBottom();
                    }
                    if (position == 0 || position == 4){
                        if (mAdapterNav != null){
                            mAdapterNav.setFocusOn(false);
                            mAdapterNav.notifyDataSetChanged();
                        }
                        LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_LEFT");
                        mFmFocusTypeHorizontal = FOCUS_ON_LEFT;
                    }else {
                        LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_RIGHT");
                        mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                    }
                }
            }
        });
        mIsConsume = true;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_child;
    }

    @Override
    protected void initView(View view) {
        LogUtil.e("__initData_view_iv_size",String.valueOf(mIvList.size()));
                mIvList = new ArrayList<>();
                mRvNav =  view.findViewById(R.id.tv_rv_nav_child);
//                mRlContentSubject =  view.findViewById(R.id.rl_content_subject_child);
                mRlContentSubjectRv =  view.findViewById(R.id.rl_content_subject);
                mLlRecommend =  view.findViewById(R.id.ll_recommend_child);
                mSiv1Recommend = view.findViewById(R.id.siv1_child_recommend);
                mSiv2Recommend = view.findViewById(R.id.siv2_child_recommend);
                mSiv3Recommend = view.findViewById(R.id.siv3_child_recommend);
                mSiv4Recommend = view.findViewById(R.id.siv4_child_recommend);
                mSiv5Recommend = view.findViewById(R.id.siv5_child_recommend);
                mSiv6Recommend = view.findViewById(R.id.siv6_child_recommend);
                mSiv7Recommend = view.findViewById(R.id.siv7_child_recommend);
                mSiv8Recommend = view.findViewById(R.id.siv8_child_recommend);
                mRvSubject = view.findViewById(R.id.rv_content);
                mIvNext = view.findViewById(R.id.iv_next);

                GroceryStoreUtils.GlideGif(getActivity(),R.drawable.arrow_down, mIvNext);

//            mSiv1Subject = view.findViewById(R.id.siv1_child_subject);
//            mSiv2Subject = view.findViewById(R.id.siv2_child_subject);
//            mSiv3Subject = view.findViewById(R.id.siv3_child_subject);
//            mSiv4Subject = view.findViewById(R.id.siv4_child_subject);
//            mSiv5Subject = view.findViewById(R.id.siv5_child_subject);
//            mSiv6Subject = view.findViewById(R.id.siv6_child_subject);
//            mSiv7Subject = view.findViewById(R.id.siv7_child_subject);
//            mSiv8Subject = view.findViewById(R.id.siv8_child_subject);
//
//            mIvSubjectList.add(mSiv1Subject);
//            mIvSubjectList.add(mSiv2Subject);
//            mIvSubjectList.add(mSiv3Subject);
//            mIvSubjectList.add(mSiv4Subject);
//            mIvSubjectList.add(mSiv5Subject);
//            mIvSubjectList.add(mSiv6Subject);
//            mIvSubjectList.add(mSiv7Subject);
//            mIvSubjectList.add(mSiv8Subject);

//            mSiv1Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//                        if (mAdapterNav != null){
//                            mAdapterNav.notifyDataSetChanged();
//                        }
//                    }
//
//                }
//            });
//
//            mSiv2Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//
//                    }
//                }
//            });
//            mSiv3Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//
//                    }
//                }
//            });
//            mSiv4Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//
//                    }
//                }
//            });
//            mSiv5Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//                        if (mAdapterNav != null){
//                            mAdapterNav.notifyDataSetChanged();
//                        }
//
//                    }
//                }
//            });
//            mSiv6Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//
//                    }
//                }
//            });
//            mSiv7Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//
//                    }
//                }
//            });
//            mSiv8Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if (b){
//
//                    }
//                }
//            });
                mIvList.add(mSiv1Recommend);
                mIvList.add(mSiv2Recommend);
                mIvList.add(mSiv3Recommend);
                mIvList.add(mSiv4Recommend);
                mIvList.add(mSiv5Recommend);
                mIvList.add(mSiv6Recommend);
                mIvList.add(mSiv7Recommend);
                mIvList.add(mSiv8Recommend);

                mSiv1Recommend.setOnClickListener(this);
                mSiv2Recommend.setOnClickListener(this);
                mSiv3Recommend.setOnClickListener(this);
                mSiv4Recommend.setOnClickListener(this);
                mSiv5Recommend.setOnClickListener(this);
                mSiv6Recommend.setOnClickListener(this);
                mSiv7Recommend.setOnClickListener(this);
                mSiv8Recommend.setOnClickListener(this);
                mIvNext.setOnClickListener(this);

//            mSiv1Subject.setOnClickListener(this);
//            mSiv2Subject.setOnClickListener(this);
//            mSiv3Subject.setOnClickListener(this);
//            mSiv4Subject.setOnClickListener(this);
//            mSiv5Subject.setOnClickListener(this);
//            mSiv6Subject.setOnClickListener(this);
//            mSiv7Subject.setOnClickListener(this);
//            mSiv8Subject.setOnClickListener(this);

                mSiv1Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 1;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
//                            if (mAdapterNav != null){
//                                mAdapterNav.notifyDataSetChanged();
//                            }
                            LogUtil.e("__FOCUS_ON_LEFT","mSiv1Recommend");
                            LogUtil.e("__mFmFocusTypeVertical_mSiv1Recommend","FOCUS_ON_TOP");
                            LogUtil.e("__activity_mSiv1Recommend","onRecommendFocusTop");
                            mFmFocusTypeHorizontal = FOCUS_ON_LEFT;
                            mFmFocusTypeVertical = FOCUS_ON_TOP;
                            if(mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onRecommendFocusTop();
                            }
                        }
                        if (mAdapterNav != null){
                            mAdapterNav.setFocusOn(false);
                            mAdapterNav.notifyDataSetChanged();
                        }
                    }
                });
                mSiv2Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 2;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_RIGHT","mSiv2Recommend");
                            LogUtil.e("__mFmFocusTypeVertical_mSiv2Recommend","FOCUS_ON_TOP");
                            LogUtil.e("__activity_mSiv2Recommend","onRecommendFocusTop");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                            mFmFocusTypeVertical = FOCUS_ON_TOP;
                            if(mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onRecommendFocusTop();
                            }
                        }
                    }
                });
                mSiv3Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 3;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_RIGHT","mSiv3Recommend");
                            LogUtil.e("__mFmFocusTypeVertical_mSiv3Recommend","FOCUS_ON_TOP");
                            LogUtil.e("__activity_mSiv3Recommend","onRecommendFocusTop");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                            mFmFocusTypeVertical = FOCUS_ON_TOP;
                            if(mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onRecommendFocusTop();
                            }
                        }
                    }
                });
                mSiv4Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 4;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_RIGHT","mSiv4Recommend");
                            LogUtil.e("__mFmFocusTypeVertical_mSiv4Recommend","FOCUS_ON_TOP");
                            LogUtil.e("__activity_siv4","onRecommendFocusBottom");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                            mFmFocusTypeVertical = FOCUS_ON_TOP;
                            if(mOnFocusChangeLiestener != null){
                                mOnFocusChangeLiestener.onRecommendFocusCenter();
                            }
                        }
                    }
                });
                mSiv5Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 5;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_LEFT","mSiv5Recommend");
                            mFmFocusTypeHorizontal = FOCUS_ON_LEFT;
                            mFmFocusTypeVertical = FOCUS_ON_RECOMMEND_BOTTOM;
//                            if (mAdapterNav != null){
//                                mAdapterNav.notifyDataSetChanged();
//                            }
                            LogUtil.e("__activity_siv5","onRecommendFocusBottom");
                                if(mOnFocusChangeLiestener != null){
                                    mOnFocusChangeLiestener.onRecommendFocusBottom();
                                }
                        }
                    }
                });
                mSiv6Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 6;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_RIGHT","mSiv6Recommend");
                            LogUtil.e("__activity_siv6","onRecommendFocusBottom");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                            mFmFocusTypeVertical = FOCUS_ON_RECOMMEND_BOTTOM;
                                if(mOnFocusChangeLiestener != null){
                                    mOnFocusChangeLiestener.onRecommendFocusBottom();
                                }
                        }
                    }
                });
                mSiv7Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 7;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_RIGHT","mSiv7Recommend");
                            LogUtil.e("__activity_siv7","onRecommendFocusBottom");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                            mFmFocusTypeVertical = FOCUS_ON_RECOMMEND_BOTTOM;
                                if(mOnFocusChangeLiestener != null){
                                    mOnFocusChangeLiestener.onRecommendFocusBottom();
                                }
                        }
                    }
                });
                mSiv8Recommend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            mRecommendFocusPosition = 8;
                            mFmFocusPart = FOCUS_ON_PART_RECOMMEND;
                            mIsChangeSubject = false;
                            LogUtil.e("__FOCUS_ON_RIGHT","mSiv8Recommend");
                            LogUtil.e("__activity_siv8","onRecommendFocusBottom");
                            mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                            mFmFocusTypeVertical = FOCUS_ON_RECOMMEND_BOTTOM;
                                if(mOnFocusChangeLiestener != null){
                                    mOnFocusChangeLiestener.onRecommendFocusBottom();
                                }
                        }
                    }
                });
    }

    @Override
    public View getFirstView() {
        return mSiv1Recommend;
    }

    @Override
    public void onClick(View view) {
        try {
            Intent intent = null;
            String type = "";
            switch (view.getId()) {
                case R.id.siv1_child_recommend:
                    type = mRecData.get(0).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(0).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(0).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(0).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(0).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(0));
                    break;
                case R.id.siv2_child_recommend:
                    type = mRecData.get(1).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(1).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(1).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(1).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(1).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(1));
                    break;
                case R.id.siv3_child_recommend:
                    type = mRecData.get(2).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(2).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(2).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(2).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(2).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(2));
                    break;
                case R.id.siv4_child_recommend:
                    type = mRecData.get(3).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(3).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(3).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(3).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(3).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(3));
                    break;
                case R.id.siv5_child_recommend:
                    type = mRecData.get(4).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(4).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(4).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(4).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(4).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(4));
                    break;
                case R.id.siv6_child_recommend:
                    type = mRecData.get(5).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(5).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(5).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(5).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(5).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(5));
                    break;
                case R.id.siv7_child_recommend:
                    type = mRecData.get(6).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(6).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(6).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(6).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(6).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(6));
                    break;
                case R.id.siv8_child_recommend:
                    type = mRecData.get(7).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",mRecData.get(7).getBind_url());
                        startActivity(intent);
                    }else if (type.equals("1")){//表示是课程
//                        intent.putExtra("courseId",mRecData.get(7).getBind_url());
//                        intent = new Intent(getActivity(),CourseDetailActivity.class);
                        isCourseForbidden(mRecData.get(7).getBind_url(),null);
                    }
//                    intent = new Intent(getActivity(), CourseDetailActivity.class);
//                    intent.putExtra("courseId", mRecData.get(7).getBind_url());
                    LogUtil.e("__onClick", "推荐__" + mRecData.get(7));
                    break;
            }
        }catch (Exception e){
//            ToastUtil.showToast("该课程正在上传，请稍候重试！");
            LogUtil.e("__Exception","该课程正在上传，请稍候重试！");
            new CourseForbiddenDialog().builder(getActivity(),"").show();
        }
    }


    public int getFragmentType() {
        return mFragmentType;
    }

    @Override
    public void setData(Object data) {
        mModel = (HomeOtherModel) data;
        for (int i = 0; i < mModel.getData().getRecommendation_data().size(); i++) {
            LogUtil.e("__setData_child_getRecommendation_img",mModel.getData().getRecommendation_data().get(i).getRecommendation_img());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mModel != null){
            mRecData = mModel.getData().getRecommendation_data();
            LogUtil.e("__onResume_Child != null",String.valueOf(mModel != null));
            LogUtil.e("__onResume_Child_recData.size",String.valueOf(mRecData.size()));
            LogUtil.e("__onResume_Child_iv.size",String.valueOf(mIvList.size()));
        }else {
            LogUtil.e("__onResume_Child != null",String.valueOf(mModel != null));
        }
    }

    boolean mIsConsume;//是否消费事件
    @Override
    public boolean onKeyDownFm(int keyCode) {
        mIsConsume = false;
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                LogUtil.e("__onKeyDown_fm方向上","方向上");
                switch (mFmFocusTypeVertical){
                    case FOCUS_ON_TOP:
                        if (mCurrentPage != 0){
                            LogUtil.e("__onKeyDown_fm方向上","方向上_翻页");
                            pageUpSubject();
                        }else {
                            calculateNavMoveUp();
                        }
                        break;
                    case FOCUS_ON_BOTTOM:
                        mIsConsume = false;
                        break;
                    case FOCUS_ON_NAV:
                        mIsConsume = false;
                        break;

                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                LogUtil.e("__onKeyDown_fm方向下","方向下");
                switch (mFmFocusTypeVertical){
                    case FOCUS_ON_RECOMMEND_BOTTOM:
                        LogUtil.e("__onKeyDown_fm方向下","RECOMMEND_BOTTOM");
                        if (mRvNav.getChildCount() > 1){
                           mAdapterNav.setSelectedPosition(1);
                           mAdapterNav.notifyDataSetChanged();
                           mNavSelectedPosition = 1;
                           mNavCurrentSelectedPosition = 1;

                           showSubject(0);
                            switch (mRecommendFocusPosition){
                                case 5:
                                    mGetFocusPosition = 0;
                                    break;
                                case 6:
                                    if (mCurrentSubject.size() >= 2){
                                        mGetFocusPosition = 1;
                                    }else {
                                        mGetFocusPosition = 0;
                                    }
                                    break;
                                case 7:
                                    if (mCurrentSubject.size() >= 3){
                                        mGetFocusPosition = 2;
                                    }else {
                                        mGetFocusPosition = mCurrentSubject.size() - 1;
                                    }
                                    break;
                                case 8:
                                    if (mCurrentSubject.size() >= 4){
                                        mGetFocusPosition = 3;
                                    }else {
                                        mGetFocusPosition = mCurrentSubject.size() - 1;
                                    }
                                    break;
                            }
                            rvSubjectPositionViewGetFocus();
//                           mRvNav.scrollToPosition(7);
//                         mRvNav.scrollBy(0,GroceryStoreUtils.dip2px(75f));
//                            mRvNav.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                               @Override
//                               public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                                   super.onScrolled(recyclerView, dx, dy);
//                                   LogUtil.e("__onScrolled_dy",dy + "");
//                                   LogUtil.e("__onScrolled_dx",dx + "");
//                                   if (mRvSubject.getChildCount() > 0){
//                                       mRvSubject.getChildAt(0).requestFocus();
//                                   }
////                                   mRvSubject.removeOnScrollListener(this);
//                               }
//                           });
//                            mRvSubject.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                                @Override
//                                public void onGlobalLayout() {
//                                    if (mRvSubject.getChildCount() > 0){
//                                        mRvSubject.getChildAt(0).requestFocus();
//                                    }
//                                    mRvSubject.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                                }
//                            });
                        }
                        mIsConsume = true;
                        break;
                    case FOCUS_ON_TOP:
                        LogUtil.e("__onKeyDown_fm方向下","TOP");
                        if (mCurrentPage == mTotalPage - 1){//最后一页
                            LogUtil.e("__onKeyDown_fm方向下","最后一页");
                            LogUtil.e("__onKeyDown_fm方向下","最后一页_mCurrentSubject.size()" + mCurrentSubject.size());
                            LogUtil.e("__onKeyDown_fm方向下","最后一页_mRvSubject.getChildCount()" + mRvSubject.getChildCount());
                            if (mRvSubject.getChildCount() <= 4){//数量小于=4
                                LogUtil.e("__onKeyDown_fm方向下","最后一页_小于=4");
                                if (mNavSelectedPosition == dataNav.size()){//并且左边导航栏已经是选中最后一个
                                    LogUtil.e("__onKeyDown_fm方向下","最后一页_小于=4_左边导航栏最后一个");
                                    mIsConsume = true;
                                }else {
                                    calculateNavMoveDown();
                                    mIsConsume = true;
//                                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
//                                    mAdapterNav.notifyDataSetChanged();
//                                    showSubject(0);
//                                  mRvNav.scrollBy(0,GroceryStoreUtils.dip2px(75f));
//                                    mRvSubject.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                                        @Override
//                                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                                            super.onScrolled(recyclerView, dx, dy);
//                                            if (mRvSubject.getChildCount() > 0){
//                                                mRvSubject.getChildAt(0).requestFocus();
//                                            }
//                                            mRvSubject.removeOnScrollListener(this);
//                                        }
//                                    });
                                }
                                }else {
                                    //最后一页__数量大于4
                                LogUtil.e("__onKeyDown_fm方向下","最后一页__数量大于4");
                                mIsConsume = false;
                            }
                            }else {//不是最后一页
                            LogUtil.e("__onKeyDown_fm方向下","不是最后一页");
//                            pageDownSubject();
                            mIsConsume = false;
                        }
                        break;
                    case FOCUS_ON_BOTTOM:
                        LogUtil.e("__onKeyDown_fm方向下","FOCUS_ON_BOTTOM");
                        if (mCurrentPage == mTotalPage - 1) {//最后一页
                            LogUtil.e("__onKeyDown_fm方向下","最后一页");
                            LogUtil.e("__onKeyDown_fm方向下","最后一页" + mNavSelectedPosition + "__" + dataNav.size());
                            if (mNavSelectedPosition == dataNav.size()){//最后一页__左边导航栏已经选中最后一个
                                LogUtil.e("__onKeyDown_fm方向下","左边导航栏已经选中最后一个");
                                mIsConsume = true;
                            }else {//左边导航栏还没选中最后一个
                                LogUtil.e("__onKeyDown_fm方向下","左边不是最后一个");
                                mIsConsume = true;
                                calculateNavMoveDown();
                            }
                        }else {//不是最后一页，翻页
                            LogUtil.e("__onKeyDown_fm方向下","翻页");
                            pageDownSubject();
                            mIsConsume = true;
                        }
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                LogUtil.e("__onKeyDown_fm方向左","方向左");
                switch (mFmFocusTypeHorizontal){
                    case FOCUS_ON_LEFT:
                        LogUtil.e("__onKeyDown_fm方向左","FOCUS_ON_LEFT");
                        for (int i = 0; i < mRvNav.getChildCount(); i++) {
                            if (i == mNavCurrentSelectedPosition){
                                LogUtil.e("__onKeyDown_fm方向左 + i",i + "");
                                mRvNav.getChildAt(i).requestFocus();
                                mIsConsume = true;
                            }
                        }
                        break;
                    case FOCUS_ON_RIGHT:
                        LogUtil.e("__onKeyDown_fm方向左","FOCUS_ON_RIGHT");
                        mIsConsume = false;
                        break;
                    case FOCUS_ON_NAV:
                        LogUtil.e("__onKeyDown_fm方向左","FOCUS_ON_NAV");
                        mIsConsume = false;
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                LogUtil.e("__onKeyDown_fm方向右","方向右");
                LogUtil.e("__onKeyDown_fm方向右_mFmFocusTypeHorizontal",mFmFocusTypeHorizontal + "");
                LogUtil.e("__onKeyDown_fm方向右_mNavSelectedPosition",mNavSelectedPosition + "");
                switch (mFmFocusTypeHorizontal){
                    case FOCUS_ON_NAV:
                        mIsConsume = true;
                        if(mNavSelectedPosition == 0){
                            mSiv1Recommend.requestFocus();
                            mIsConsume = true;
                        }else {
                            if (mRvSubject != null){
                                if (mRvSubject.getChildCount() > 0){
                                    mRvSubject.getChildAt(0).requestFocus();
//                                    rvSubjectFirstViewGetFocus();
                                    LogUtil.e("__requestFocus",mRvSubject.getChildCount() + "");
                                    if (mAdapterNav != null){
                                        mAdapterNav.setFocusOn(false);
                                        mAdapterNav.notifyDataSetChanged();
                                    }
                                    mIsConsume = true;
                                }
                            }
                        }
                        break;
                }
                switch (mFmFocusPart){
                    case FOCUS_ON_PART_SUBJECT:
                        if (mRvSubjectSelectedPosition == 3
                                || mRvSubjectSelectedPosition == 7
                                || mRvSubjectSelectedPosition == mCurrentSubject.size() - 1){
                            mIsConsume = true;
                        }
                        break;
                }
                break;
                default:break;
        }
        LogUtil.e("__onKeyDown_fm_return",mIsConsume + "");
        return mIsConsume;
    }

    public void notificationNav(){
        if (mAdapterNav != null){
            mAdapterNav.setFocusOn(false);
            mAdapterNav.notifyDataSetChanged();
        }
    }

    /**
     * 按下时，计算导航栏移动
     */
    private void calculateNavMoveDown(){
        LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__current_position111__" + mNavCurrentSelectedPosition);
        LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__getChildCount__" + mRvNav.getChildCount());
        if (dataNav.size() + 1 <= 7){//一个页面能容纳7个item,如果小于等于7个，就不用滚动了
            LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__<=7_不滚只++");
            mNavCurrentSelectedPosition++;
            mNavSelectedPosition++;
            mAdapterNav.setSelectedPosition(mNavSelectedPosition);
            mAdapterNav.notifyDataSetChanged();
            showSubject(mNavSelectedPosition - 1);
            calculateFlipSelectedPosition();
            rvSubjectPositionViewGetFocus();
        }else{
            if (mNavSelectedPosition < 3){
                LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__>7_<5不滚动只++");
                mNavCurrentSelectedPosition++;
                mNavSelectedPosition++;
                mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                mAdapterNav.notifyDataSetChanged();
                showSubject(mNavSelectedPosition - 1);
                calculateFlipSelectedPosition();
                rvSubjectPositionViewGetFocus();
//                mRvNav.scrollToPosition(9);
            }else {
                if (dataNav.size() - mNavSelectedPosition < 4){
                    mRvNav.scrollToPosition(dataNav.size());
                    if (mRvNav.getChildCount() == 7){
                        LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__>7_离最后一个<4_==7,滚动也++");
                        mNavCurrentSelectedPosition++;
                    }else {
                        LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__>7_离最后一个<4_!=7,滚动不++");
                    }
                    mNavSelectedPosition++;
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    showSubject(mNavSelectedPosition - 1);
                    calculateFlipSelectedPosition();
                    rvSubjectPositionViewGetFocus();
                }else {
                    LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__>7_离最后一个>=4,滚动不++__" + mNavSelectedPosition);
                    mNavSelectedPosition++;
                    mNavCurrentSelectedPosition = 3;
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    showSubject(mNavSelectedPosition - 1);
                    calculateFlipSelectedPosition();
                    rvSubjectPositionViewGetFocus();
                    mRvNav.scrollToPosition(mNavSelectedPosition + 3);
                }
            }
        }
        LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveDown__current_position222__" + mNavCurrentSelectedPosition);
    }

    /**
     * 计算翻页之后选中位置
     */
    private void calculateFlipSelectedPosition(){
        int remainder = mRvSubjectSelectedPosition % 4;
        if (remainder == 0){
            mGetFocusPosition = 0;
        }else if (remainder == 1){
            if (mCurrentSubject.size() >= 2){
                mGetFocusPosition = 1;
            }else {
                mGetFocusPosition = 0;
            }
        } else if (remainder == 2){
            if (mCurrentSubject.size() >= 3){
                mGetFocusPosition = 2;
            }else {
                mGetFocusPosition = mCurrentSubject.size() - 1;
            }
        } else if (remainder == 3){
            if (mCurrentSubject.size() >= 4){
                mGetFocusPosition = 3;
            }else {
                mGetFocusPosition = mCurrentSubject.size() - 1;
            }
        }
    }
    /**
     * 按上时，计算导航栏移动
     */
    private void calculateNavMoveUp(){
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__current_position111__" + mNavCurrentSelectedPosition);
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__getChildCount__" + mRvNav.getChildCount());
        if (dataNav.size() + 1 <= 7){//一个页面能容纳7个item,如果小于等于7个，就不用滚动了
            if (mNavSelectedPosition > 0){
                LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__<=7_>0_不滚只--");
                mNavCurrentSelectedPosition--;
                mNavSelectedPosition--;
                if (mNavSelectedPosition > 0){
                    showSubjectLastPage(mNavSelectedPosition - 1,mRvSubjectSelectedPosition);
                    mIsConsume = true;
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
//                mAdapterNav.setSelectedPosition(mNavSelectedPosition);
//                mAdapterNav.notifyDataSetChanged();
//                mGetFocusPosition = mRvSubjectSelectedPosition;
//                LogUtil.e("__mGetFocusPosition_moveUP",mGetFocusPosition + "");
//                rvSubjectPositionViewGetFocus();
//                showSubject(mNavSelectedPosition - 1);
//                mIsConsume = true;
                }else {
                    mIsConsume = true;
                    LogUtil.e("__表示应该切换到推荐页了", mRvSubjectSelectedPosition + "");
                    //表示应该切换到推荐页了
                    mRlContentSubjectRv.setVisibility(View.GONE);
                    mLlRecommend.setVisibility(View.VISIBLE);
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    switch (mRvSubjectSelectedPosition){
                        case 0:
                            mSiv5Recommend.requestFocus();
                            break;
                        case 1:
                            mSiv6Recommend.requestFocus();
                            break;
                        case 2:
                            mSiv7Recommend.requestFocus();
                            break;
                        case 3:
                            mSiv8Recommend.requestFocus();
                            break;
                    }
                }
//                mAdapterNav.setSelectedPosition(mNavSelectedPosition);
//                mAdapterNav.notifyDataSetChanged();
//                mGetFocusPosition = mRvSubjectSelectedPosition;
//                LogUtil.e("__mGetFocusPosition_moveUP",mGetFocusPosition + "");
//                rvSubjectPositionViewGetFocus();
//                showSubject(mNavSelectedPosition - 1);
//                mIsConsume = true;
            }else {
                LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__<=7_==0_不处理");
                  //如果是0，不处理，让系统自动处理
                  mIsConsume = false;
            }
        }else{
            if (dataNav.size() - mNavSelectedPosition < 3 || mNavSelectedPosition < 4){
                LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7_选中position距离最后一个item_<3 ||<4_不滚动只--");
                mNavSelectedPosition--;
                mNavCurrentSelectedPosition--;
                if (mNavSelectedPosition > 0){
                    showSubjectLastPage(mNavSelectedPosition - 1,mRvSubjectSelectedPosition);
                    mIsConsume = true;
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                }else {
                    mIsConsume = true;
                    LogUtil.e("__表示应该切换到推荐页了222", mRvSubjectSelectedPosition + "");
                    //表示应该切换到推荐页了
                    mRlContentSubjectRv.setVisibility(View.GONE);
                    mLlRecommend.setVisibility(View.VISIBLE);
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    switch (mRvSubjectSelectedPosition){
                        case 0:
                            mSiv5Recommend.requestFocus();
                            break;
                        case 1:
                            mSiv6Recommend.requestFocus();
                            break;
                        case 2:
                            mSiv7Recommend.requestFocus();
                            break;
                        case 3:
                            mSiv8Recommend.requestFocus();
                            break;
                    }
                }
            }else {
                if (mRvNav.getChildCount() == 7){
                    LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7_选中position距离最后一个item_>=3_size==7滚动不--");
                }else {
                    LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7_选中position距离最后一个item_>=3_size!=7滚动--");
                  mNavCurrentSelectedPosition--;
                }
                mNavSelectedPosition--;
                showSubjectLastPage(mNavSelectedPosition - 1,mRvSubjectSelectedPosition);
                mIsConsume = true;
                mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                mAdapterNav.notifyDataSetChanged();
                mRvNav.scrollToPosition(mNavSelectedPosition - 3);
            }
        }
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__current_position222__" + mNavSelectedPosition);
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__current_position222__current" + mNavCurrentSelectedPosition);
    }
    /**
     * 按上时，计算导航栏移动
     */
    private void calculateNavMoveUp2(){
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__current_position111__" + mNavCurrentSelectedPosition);
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__getChildCount__" + mRvNav.getChildCount());
        if (dataNav.size() + 1 <= 7){//一个页面能容纳7个item,如果小于等于7个，就不用滚动了
            if (mNavSelectedPosition > 0){
                LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__<=7_>0_不滚只--");
                mNavCurrentSelectedPosition--;
                mNavSelectedPosition--;
                mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                mAdapterNav.notifyDataSetChanged();
                rvSubjectFirstViewGetFocus();
                showSubject(mNavSelectedPosition - 1);
                mIsConsume = true;
            }else {
                LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__<=7_==0_不处理");
                //如果是0，不处理，让系统自动处理
                mIsConsume = false;
            }
        }else{
            if (mNavSelectedPosition > 0){
                mIsConsume = true;
                if (mNavSelectedPosition < 3){
                    LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7_>0_< 4不滚动只--");
                    mNavCurrentSelectedPosition--;
                    mNavSelectedPosition--;
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    rvSubjectFirstViewGetFocus();
                    if (mNavSelectedPosition == 0){
                        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7_>0_< 4==0_推荐");
                        LogUtil.e("__activity___onKeyDown_fm方向上","_calculateNavMoveUp__>7_>0_< 4==0_推荐onRecommendFocusBottom");
                        mRlContentSubjectRv.setVisibility(View.GONE);
                        mLlRecommend.setVisibility(View.VISIBLE);
                        mSiv5Recommend.requestFocus();
                        if (mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onRecommendFocusBottom();
                        }
                    }else {
                        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7_>0_< 4!=0_课程");
                        showSubject(mNavSelectedPosition - 1);
                    }
//                mRvNav.scrollToPosition(9);
                }else {
//                if (dataNav.size() - mNavSelectedPosition < 4){
//                    LogUtil.e("__onKeyDown_fm方向下","calculateNavMoveUp__>7_离最后一个<4,不滚动只++");
//                    mRvNav.scrollToPosition(dataNav.size());
//                    mNavCurrentSelectedPosition++;
//                    mNavSelectedPosition++;
//                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
//                    mAdapterNav.notifyDataSetChanged();
//                    rvSubjectFirstViewGetFocus();
//                    showSubject(mNavSelectedPosition - 1);
//                }else {
                    LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7__>0_>=4,滚动不--__选中第3__" + mNavSelectedPosition);
                    mNavSelectedPosition--;
                    mNavCurrentSelectedPosition = 3;
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    rvSubjectFirstViewGetFocus();
                    showSubject(mNavSelectedPosition - 1);
                    mRvNav.scrollToPosition(mNavSelectedPosition + 3);
//                }
                }
            }else {
                LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__>7__==0,滚动不--__选中第3" + mNavSelectedPosition);
                LogUtil.e("__activity___onKeyDown_fm方向上","_calculateNavMoveUp__>7__==0,滚动不--__选中第3onRecommendFocusBottom");
                if (mOnFocusChangeLiestener != null){
                    mOnFocusChangeLiestener.onRecommendFocusBottom();
                }
                mIsConsume = false;
            }

        }
        LogUtil.e("__onKeyDown_fm方向上","calculateNavMoveUp__current_position222__" + mNavCurrentSelectedPosition);
    }
    /**
     * 计算导航栏移动
     */
    private void calculateNavMoveDown2(){
        mNavSelectedPosition++;
        LogUtil.e("__onKeyDown_fm方向下","calculateNavMove__" + mNavCurrentSelectedPosition);
        if (mRvNeedHeight <= mRvHeight){//需要的高度如果还没有rv的高度高，不用滑动导航栏
            mNavCurrentSelectedPosition++;
            LogUtil.e("__onKeyDown_fm方向下","不用滑动导航栏__++" + mNavCurrentSelectedPosition);
            mAdapterNav.setSelectedPosition(mNavSelectedPosition);
            mAdapterNav.notifyDataSetChanged();
            rvSubjectFirstViewGetFocus();
            showSubject(mNavSelectedPosition - 1);
        }else {
            if (mRvNeedHeight - mRvHeight <= mItemHeightPx){//距离不够一个Item的时候，直接移动到底部
                LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_不够一个item");
                mNavCurrentSelectedPosition++;
                LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_不够一个item__++" + mNavCurrentSelectedPosition);
                mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                mAdapterNav.notifyDataSetChanged();
                rvSubjectFirstViewGetFocus();
                showSubject(mNavSelectedPosition - 1);
                mRvNav.scrollBy(0,mRvNeedHeight - mRvHeight);
                mScrollY = mRvNeedHeight - mRvHeight;
            }else {//距离够一个item的时候
                LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_够一个item");
                LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_够一个item__" + mRvNeedHeight + "__" + mRvHeight + "__" + mScrollY + "__" + mItemHeightPx);
                if (mRvNeedHeight - mRvHeight - mScrollY >= mItemHeightPx){
                    LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_减移动距离_够一个item");
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    rvSubjectFirstViewGetFocus();
                    showSubject(mNavSelectedPosition - 1);
                    if (mNavCurrentSelectedPosition > 3){//大于3才滑动，小于3不动
                        LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_减移动距离_够一个item_>3滚动不++");
                        mRvNav.scrollBy(0,mItemHeightPx);
                        mScrollY = mScrollY + mItemHeightPx;
                    }else {
                        LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_减移动距离_够一个item_<=3++");
                        mNavCurrentSelectedPosition++;
                    }
                }else {
                    LogUtil.e("__onKeyDown_fm方向下","滑动导航栏_减移动距离_不够一个item_滚动且++");
                    mAdapterNav.setSelectedPosition(mNavSelectedPosition);
                    mAdapterNav.notifyDataSetChanged();
                    rvSubjectFirstViewGetFocus();
                    showSubject(mNavSelectedPosition - 1);
                    mRvNav.scrollBy(0,mItemHeightPx);
                    mScrollY = mScrollY + mItemHeightPx;
                    mNavCurrentSelectedPosition++;
                }
            }
        }
    }

    /**
     * 在加载完成之后首个view获取焦点
     */
    private void rvSubjectFirstViewGetFocus(){
        mRvSubject.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mRvSubject.getChildCount() > 0){
                    mRvSubject.getChildAt(0).requestFocus();
                }
                mRvSubject.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    private int mGetFocusPosition;
    /**
     * 在加载完成之后，某个view获取焦点
     */
    private void rvSubjectPositionViewGetFocus(){
        mRvSubject.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mRvSubject.getChildCount() > 0){
                    LogUtil.e("__showSubjectLastPage_rvSubjectPositionViewGetFocus", mGetFocusPosition + "");
                    mRvSubject.getChildAt(mGetFocusPosition).requestFocus();
                }
                mRvSubject.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    /**
     * 展示课程最后一页
     * @param positionData 数据的position
     * @param positionItem 上翻之前选中item的position
     */
    private void showSubjectLastPage(int positionData,int positionItem) {
        LogUtil.e("__showSubject",positionData + "");
        //先获取选中科目的数据
        HomeOtherModel.Databean.CourseListDatabean.CourseDatabean courseListDatabean = mModel.getData().getCourse_list_data().getCourse_data().get(positionData);
//        mTotalSubject = courseListDatabean.getPage_data();
        mTotalSubject = courseListDatabean.getPage_data();
        mTotalPage = courseListDatabean.getTotal_pages();//总页数
        mTotalCount = courseListDatabean.getTotal_count();//总条目数
        mCurrentPage = mTotalPage - 1;//向上翻的时候，选中最后一页
        mCurrentSubject = mTotalSubject.get(mCurrentPage);
        LogUtil.e("__showSubjectLastPage_size",mCurrentSubject.size() + "");
        LogUtil.e("__showSubjectLastPage_positionItem",positionItem + "");
        if (mCurrentSubject.size() < 4){//此页数量小于4
            LogUtil.e("__showSubjectLastPage_positionItem","< 4");
            if (mCurrentSubject.size() - 1 >= positionItem){//如果大于等于之前选中位置，则按照之前位置选中
                LogUtil.e("__showSubjectLastPage_positionItem","大于等于之前选中位置");
                mGetFocusPosition = positionItem;
                LogUtil.e("__mGetFocusPosition_大于等于之前选中位置",mGetFocusPosition + "");
                rvSubjectPositionViewGetFocus();
            }else {//小于之前选中位置选中最后一个
                LogUtil.e("__showSubjectLastPage_positionItem","小于等于之前选中位置");
                mGetFocusPosition = mCurrentSubject.size() - 1;
                LogUtil.e("__mGetFocusPosition_小于等于之前选中位置",mGetFocusPosition + "");
                rvSubjectPositionViewGetFocus();
            }
        }else {//此页数量大于4
            LogUtil.e("__showSubjectLastPage_positionItem",">4");
            if (mCurrentSubject.size() - 1 >= positionItem + 4){//大于等于之前选中位置+4，则按照之前选中位置+4选中
                mGetFocusPosition = positionItem + 4;
                LogUtil.e("__mGetFocusPosition_大于等于之前选中位置+4",mGetFocusPosition + "");
                rvSubjectPositionViewGetFocus();
            }else {//小于之前选中位置+4,则选中最后一个
                LogUtil.e("__showSubjectLastPage_positionItem","小于等于之前选中位置+4");
                mGetFocusPosition = mCurrentSubject.size() - 1;
                LogUtil.e("__mGetFocusPosition_小于等于之前选中位置+4",mGetFocusPosition + "");
                rvSubjectPositionViewGetFocus();
            }
        }
        //设置课程内容
        mAdapterSubject = new RvAdapterChildSubject(mCurrentSubject,getActivity());
        mRvSubject.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRvSubject.setAdapter(mAdapterSubject);
        LogUtil.e("__showSubject_mCurrentSubject_" + positionData,mCurrentSubject.size() + "");
        LogUtil.e("__showSubject_getChildCount_" + positionData,mRvSubject.getChildCount() + "");
        mAdapterSubject.setOnItemSelectedListener(new RvAdapterChildSubject.OnItemStateChangeListener() {
            @Override
            public void onItemClidkListener(int position) {
                LogUtil.e("__subject_onItemClid_" + position,mCurrentSubject.get(position).getCourse_id());
                isCourseForbidden(mCurrentSubject.get(position).getCourse_id(),null);
//                Intent intent = new Intent(getActivity(),CourseDetailActivity.class);
//                intent.putExtra("courseId",mCurrentSubject.get(position).getCourse_id());
//                startActivity(intent);
            }

            @Override
            public void onItemFocusChange(int position, boolean isFocus) {
                if(isFocus){
                    mFmFocusPart = FOCUS_ON_PART_SUBJECT;
                    mIsChangeSubject = false;
                    mRvSubjectSelectedPosition = position;
                    if(position < 4){
                        LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_TOP");
                        mFmFocusTypeVertical = FOCUS_ON_TOP;
                        if (mOnFocusChangeLiestener != null){
                            mOnFocusChangeLiestener.onSubjectFocusTop();
                        }
                    }else {
                        LogUtil.e("__mFmFocusTypeVertical_onItemFocusChange_" + position,"FOCUS_ON_BOTTOM");
                        mFmFocusTypeVertical = FOCUS_ON_BOTTOM;
                        mOnFocusChangeLiestener.onSubjectFocusBottom();
                    }
                    if (position == 0 || position == 4){
                        if (mAdapterNav != null){
                            mAdapterNav.setFocusOn(false);
                            mAdapterNav.notifyDataSetChanged();
                        }
                        LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_LEFT");
                        mFmFocusTypeHorizontal = FOCUS_ON_LEFT;
                    }else {
                        LogUtil.e("__mAdapterSubject",position + "__FOCUS_ON_RIGHT");
                        mFmFocusTypeHorizontal = FOCUS_ON_RIGHT;
                    }
                }
            }
        });

        //隐藏推荐位，展示课程位
//        mRlContentSubject.setVisibility(View.VISIBLE);
        mRlContentSubjectRv.setVisibility(View.VISIBLE);
        mLlRecommend.setVisibility(View.GONE);
    }

}
