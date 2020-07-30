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

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;

/**
 * 首页
 */
public class MainFragmentType2 extends BaseFragment {
    public LinearLayout mLLRoot;
    public ImageView mFirstView;
    private int mFragmentType = 1;
    private VideoView mVideoView;
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home_2;
    }

    @Override
    protected void initView(View view) {
        mFirstView = view.findViewById(R.id.siv1_home_1);
        mLLRoot = view.findViewById(R.id.ll_root_view_home_1);
        mVideoView = view.findViewById(R.id.video_view_home_2);
//        mLLRoot.setVisibility(View.VISIBLE);
    }

    @Override
    public View getFirstView() {
        return mFirstView;
    }

    @Override
    public void onClick(View view) {

    }

    public int getFragmentType() {
        return mFragmentType;
    }



    public VideoView getVideoView() {
        return mVideoView;
    }
    @Override
    public void setData(Object data) {
//        mModel = (HomeOtherModel) data;
    }
}
