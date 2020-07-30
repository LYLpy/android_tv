package com.geling.view.gelingtv_XX_tongbu.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.BaseActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.CourseDetailActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseForbiddenDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/21------更新------
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected HttpHelper mHttpHelper = new HttpHelper();//网络请求助手

    //水平方向的焦点所在位置，导航栏，左半边，右半边
    public int mFmFocusTypeHorizontal = 0;
    public static final int FOCUS_ON_LEFT = 1;
    public static final int FOCUS_ON_RIGHT = 2;
    public static final int FOCUS_ON_NAV = 3;
    //垂直方向的焦点所在位置，上半边，下半边，其他
    public int mFmFocusTypeVertical = 0;
    public static final int FOCUS_ON_TOP = 4;
    public static final int FOCUS_ON_BOTTOM = 5;
    public static final int FOCUS_ON_RECOMMEND_BOTTOM = 6;

    //焦点所在位置在哪一部分，7导航栏，8推荐，9课程
    public int mFmFocusPart = 8;
    public static final int FOCUS_ON_PART_NAV = 7;
    public static final int FOCUS_ON_PART_RECOMMEND = 8;
    public static final int FOCUS_ON_PART_SUBJECT = 9;

//    protected HttpHelper mHttpHelper = new HttpHelper();
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract void initData();

    protected abstract int getLayoutRes();

    protected abstract void initView(View view);
    public abstract View getFirstView();

    public OnFocusChangeLiestener mOnFocusChangeLiestener;
    /**
     * 状态变化监听
     */
    public interface OnFocusChangeLiestener{
        /**
         * 在推荐页顶部
         */
        void onRecommendFocusTop();

        /**
         * 推荐页中间
         */
        void onRecommendFocusCenter();


        /**
         * 推荐页底部
         */
        void onRecommendFocusBottom();


        /**
         * 课程页顶部
         */
        void onSubjectFocusTop();

        /**
         * 课程页底部
         */
        void onSubjectFocusBottom();

        void onNavTop();
        void onNavBottom();
    }

    public void setOnFocusChangeLiestener(OnFocusChangeLiestener onFocusChangeLiestener) {
        mOnFocusChangeLiestener = onFocusChangeLiestener;
    }
    public abstract void setData(Object data);
    public boolean onKeyDownFm(int keyCode){
        return true;
    }

    public int getKeyDownFmInt( ){
        return 0;
    }
    /**
     * 检查课程是否禁用
     */
    protected synchronized void isCourseForbidden (final String courseId, final BaseActivity.CourseForbiddenCallBack callBack){
            mHttpHelper.isCourseForbidden(courseId, new BaseHelper.IHttpCallback() {
                @Override
                public void onHttpSuccess(int reqType, Message msg) {
                    switch (reqType){
                        case HttpHelper.PY_HTTP_COURSE_FORBIDDEN:
                            Intent intent = new Intent(getActivity(),CourseDetailActivity.class);
                            intent.putExtra("courseId",courseId);
                            startActivity(intent);
                            break;
                    }
                }

                @Override
                public void onHttpError(int reqType, String error) {
                    switch (reqType){
                        case HttpHelper.PY_HTTP_COURSE_FORBIDDEN:
                            new CourseForbiddenDialog().builder(getActivity(),"").show();
                            break;
                    }
                }

                @Override
                public void onTokenError(int reqType, String error, String errorCode) {
                    ((BaseActivity)getActivity()).toLogin();
                }
            });
            return;

    }
    public void notificationNav(){
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
