package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CourseListModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.VideoUrlModel;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterNavCourseDetail;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterCourse;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterCourseNav;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ClearCollectionDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseIntroduceDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CustomRecycleView;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoViewSmall;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.HTTP_GET_VIDEO_DATA;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_ADD_COLLECT;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_DEL_COLLECT;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_GET_COURSE_LIST;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_GET_VIDEO_URL;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/4------更新------
 * 课程详情
 */

public class CourseDetailActivity extends BaseActivity implements BaseHelper.NewIHttpCallback, UniversalVideoViewSmall.VideoViewCallback {

    @BindView(R.id.ll_max)
    LinearLayout mLlMax;
    @BindView(R.id.ll_collect)
    LinearLayout mLlCollect;
    @BindView(R.id.ll_purchase_vip)
    LinearLayout mLlPurchase;

    @BindView(R.id.iv_max)
    ImageView mIvMax;
    @BindView(R.id.iv_mime)
    ImageView mIvCollect;
    @BindView(R.id.iv_purchase)
    ImageView mIvPurchase;

    @BindView(R.id.tv_max)
    TextView mTvMax;
    @BindView(R.id.tv_mine)
    TextView mTvCollect;
    @BindView(R.id.tv_purchase)
    TextView mTvPurchase;

    @BindView(R.id.rv_nav)
    CustomRecycleView mRvNav;
    //            MyTvRecyclerView mRvNav;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_click)
    TextView mTvClick;
    @BindView(R.id.tv_click_unit)
    TextView mTvClickUnit;
    @BindView(R.id.tv_teacher)
    TextView mTvTeacher;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    @BindView(R.id.tv_introduce)
    TextView mTvIntroduce;
    @BindView(R.id.video_view)
    UniversalVideoViewSmall mVideoView;
    @BindView(R.id.media_controller)
    UniversalMediaController mUniversalMediaController;
    private String mTestCourseId = "6023";//测试用的course_id
    @BindView(R.id.rv_content)
    RecyclerView mRvCourse;
    @BindView(R.id.fl_loading_layout)
    FrameLayout mFlLoadingLayout;
    //    ========================视频播放相关end===================
    private int mFocusPosition;//焦点所在位置,0表示在课程介绍，1表示在全屏，2表示在收藏，3表示在vip购买，4表示在导航栏，5以上表示在课程Item
    private String mCourseId;
    private int mResultCode = 0;//activity的返回结果码，如果取消了收藏，则返回Activity.RESULT_OK
    public CourseListModel mCourseListModel;
    public static final int FOCUS_ON_INTRODUCE = 0;
    public static final int FOCUS_ON_MAX = 1;
    public static final int FOCUS_ON_COLLECT = 2;
    public static final int FOCUS_ON_VIP = 3;
    public static final int FOCUS_ON_NAV = 4;
    //    public static final int FOCUS_ON_COURSE = 5;
    private String mToken = "dd5817b577512ee01d57dbb569bd723d";
    public static final int REQUEST_TYPE_PLAY_NEXT = 1;
    public static final int REQUEST_TYPE_PLAY_CLICK = 2;
    public static final int REQUEST_CODE_VIP = 1;
    public static final int REQUEST_CODE_PLAY_VIDEO = 2;
    private CourseListModel.Databean.ClassListbean mListbeanPrepare;//点击准备播放的视频，如果购买VIP回来，会播放他点击的这个

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mCourseId = intent.getStringExtra("courseId");
        LogUtil.e("__initData_mCourseId",mCourseId + "");
//        if (TextUtils.isEmpty(mCourseId)){
//            mCourseId = mTestCourseId;
//        }
        mHttpHelper.getCourseList(this,mCourseId,10000,1);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initView() {
        mVideoView = findViewById(R.id.video_view);
        mLlMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mFocusPosition = FOCUS_ON_MAX;
                    mIvMax.setImageDrawable(getResources().getDrawable(R.drawable.icon_max_select_video_activity));
                    mTvMax.setTextColor(getResources().getColor(R.color.color_white));
                    notifiAdapterChange();
                } else {
                    mIvMax.setImageDrawable(getResources().getDrawable(R.drawable.icon_max_normal_video_activity));
                    mTvMax.setTextColor(getResources().getColor(R.color.color_normal_text_color));
                }
            }
        });
        mLlCollect.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mFocusPosition = FOCUS_ON_COLLECT;
                    mTvCollect.setText("收藏");
                    mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collect_selected_video_activity));
                    mTvCollect.setTextColor(getResources().getColor(R.color.color_white));
                    try {
                        if (mCourseListModel.getData().getCourse_info().getCollection() == 1){
                            mTvCollect.setText("已收藏");
                            mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.collected_selected));
                        }else {
                            mTvCollect.setText("收藏");
                            mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collect_selected_video_activity));
                        }
                    }catch (Exception e){
                        LogUtil.e("__Exception_mLlCollect.setOnFocus","");
                        mTvCollect.setText("收藏");
                        mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collect_selected_video_activity));
                    }
                    notifiAdapterChange();
                } else {
                    try {
                        if (mCourseListModel.getData().getCourse_info().getCollection() == 1){
                            mTvCollect.setText("已收藏");
                            mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.collected_selected));
                        }else {
                            mTvCollect.setText("收藏");
                            mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collect_normal_video_activity));
                        }
                    }catch (Exception e){
                        LogUtil.e("__Exception_mLlCollect.setOnFocus","");
                        mTvCollect.setText("收藏");
                        mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collect_normal_video_activity));
                    }
                    mTvCollect.setTextColor(getResources().getColor(R.color.color_normal_text_color));
                }
            }
        });

        mLlPurchase.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mFocusPosition = FOCUS_ON_VIP;
                    mIvPurchase.setImageDrawable(getResources().getDrawable(R.drawable.vip_video_selected_activity));
                    mTvPurchase.setTextColor(getResources().getColor(R.color.color_white));
                    notifiAdapterChange();
                } else {
                    mTvPurchase.setTextColor(getResources().getColor(R.color.color_gold));
                    mIvPurchase.setImageDrawable(getResources().getDrawable(R.drawable.vip_video_normal_activity));
                }
            }
        });

        mTvIntroduce.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mFocusPosition = FOCUS_ON_INTRODUCE;
                }
            }
        });
        mLlMax.setOnClickListener(this);
        mLlPurchase.setOnClickListener(this);
        mLlCollect.setOnClickListener(this);
        mTvIntroduce.setOnClickListener(this);
        mLlMax.requestFocus();
//        mLlPurchase.requestFocus();
    }

    //限制3秒内vip按钮只能点击一次
    private boolean mIsVipClickable = true;

    @Override
    public void onClick(View view) {
        try{
            Intent intent;
            switch (view.getId()) {
                case R.id.tv_introduce:
//                mPlayingPage = mRvAdapter.getSelectedPosition();
                    if (mCourseListModel != null && mCourseListModel.getData()!= null
                            && mCourseListModel.getData().getCourse_info() != null
                            && mCourseListModel.getData().getCourse_info().getContent() != null){
                        new CourseIntroduceDialog().builder(this,mCourseListModel.getData().getCourse_info().getContent()).show();
                    }else {
                        new CourseIntroduceDialog().builder(this,"暂无课程介绍").show();
                    }
//                new CourseIntroduceDialog().builder(this,"呵呵呵呵呵呃呃呃呃呃呃呃呃呃呃呃呃呃").show();
                    break;
                case R.id.ll_max:
                    intent = new Intent(this,VideoPlayActivity.class);
                    intent.putExtra(VideoPlayActivity.KEY_SEEK,mVideoView.getCurrentPosition());
                    intent.putExtra(VideoPlayActivity.KEY_COURSE_ID,mCourseId);
                    startActivityForResult(intent,REQUEST_CODE_PLAY_VIDEO);
//                mVideoView.pause();
                    break;
                case R.id.ll_purchase_vip:
                    intent = new Intent(this,VipActivity3.class);
                    startActivityForResult(intent,REQUEST_CODE_VIP);
                    break;
                case R.id.ll_collect:
                    collectToggle();
                    break;
            }
        }catch (Exception e){
            LogUtil.e("__onClick_exception",String.valueOf(e.getMessage()));
        }
    }

    /**
     * 收藏操作，收藏/取消
     */
    private void collectToggle(){
        if (mCourseListModel != null && mCourseListModel.getData() != null && mCourseListModel.getData().getCourse_info()!=null){
            //        addCollect
            //是否收藏,0没收藏，1为收藏
            if (mCourseListModel.getData().getCourse_info().getCollection() == 1){
                mHttpHelper.delCollect(this,mCourseListModel.getData().getCourse_info().getId());
            }else {
                mHttpHelper.addCollect(this,mCourseListModel.getData().getCourse_info().getId());
            }
        }else {
            ToastUtil.showToast("课程数据为空");
        }

    }
    private void notifiAdapterChange() {
        if (mRvAdapterCourseNav == null) {
            return;
        }
//        LogUtil.e("__notifiAdapterChange","notifiAdapterChange");
        mRvAdapterCourseNav.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            switch (keyCode) {
                //模拟器测试时键盘中的的Enter键，模拟ok键（推荐TV开发中使用蓝叠模拟器）
                case KeyEvent.KEYCODE_ENTER:
                    break;
                case KeyEvent.KEYCODE_BACK:
                    LogUtil.e("__onKeyDown_courseAct返回键", "返回键");
                    Intent intent = new Intent();
                    intent.putExtra("courseId", mCourseId);
                    setResult(mResultCode, intent);
                    finish();
                    return true;
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    LogUtil.e("__onKeyDown_courseAct", "中间键");
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    LogUtil.e("__onKeyDown", "左方向键");
                    if (mFocusPosition == FOCUS_ON_MAX){//在全屏
                        if (mRvAdapterCourseNav != null) {
                            //遍历所有子view，设置字体颜色，相当于刷新adapter
                            for (int i = 0; i < mRvNav.getChildCount(); i++) {
                                TextView tv = (TextView) mRvNav.getChildAt(i);
                                LogUtil.e("__notifiAdapterChange_i", i + "");
                                LogUtil.e("__notifiAdapterChange", mRvAdapterCourseNav.getSelectedPosition() + "");
                                if (i == mRvAdapterCourseNav.getSelectedPosition()) {//
                                    LogUtil.e("__notifiAdapterChange", "true");
                                    tv.requestFocus();
                                } else {
                                }
                            }
                        }
                        return true;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    LogUtil.e("__onKeyDown", "右方向键");
                    LogUtil.e("__onKeyDown_mNavSelPosition", "右方向键", mNavSelPosition + "");
                    LogUtil.e("__onKeyDown_size", "右方向键", mCourseListModel.getData()+ "");
                    if (mFocusPosition - 4 == mCurrentClassList.size()){
                        return true;
                    }
                    if (mFocusPosition == FOCUS_ON_NAV && mNavSelPosition == mNavTags.size() - 1){
                        return true;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    LogUtil.e("__onKeyDown", "上方向键mFocusPosition：" + mFocusPosition);
                    LogUtil.e("__onKeyDown", "上方向键mRealPosition：" + mNavRealSelPosition);
                    if (mFocusPosition == 5 || mFocusPosition == 6 || mFocusPosition == 7 || mFocusPosition == 8) {
                        if (mRvAdapterCourseNav != null) {
                            //让复用之后实际item获取焦点
                            mRvNav.getChildAt(mNavRealSelPosition).requestFocus();
                        }
                        return true;
                    }else if (mFocusPosition == FOCUS_ON_NAV){
                        mLlMax.requestFocus();
                        return true;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    LogUtil.e("__onKeyDown", "下方向键" +mFocusPosition );
                    if (mFocusPosition == FOCUS_ON_MAX || mFocusPosition == FOCUS_ON_COLLECT || mFocusPosition == FOCUS_ON_VIP) {
                        if (mRvAdapterCourseNav != null) {
                            //让复用之后实际item获取焦点
                            mRvNav.getChildAt(mNavRealSelPosition).requestFocus();
                        }
                        return true;
                    }else if (mFocusPosition == FOCUS_ON_NAV){

                        return false;
                    }
                    break;
            }
        }catch (Exception e){

        }
        return super.onKeyDown(keyCode, event);
    }

    private void initVideoView(){

    }
    private boolean mIsPlaying = true;
//    private Handler mHandler = new Handler();
//    private Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            if (mIsPlaying){
//                if (mVideoView != null){
//                    LogUtil.e("__mRunnable_cdetail_getCurrentPosition",mVideoView.getCurrentPosition() + "");
//                    LogUtil.e("__mRunnable_cdetail_getDuration",mVideoView.getDuration() + "");
//                    //无法监听到视频播放完毕，只能通过此方法监听播放完成，切换下一集
//                    if (mVideoView.getDuration() - mVideoView.getCurrentPosition() < 2000
//                            && mVideoView.getDuration() - mVideoView.getCurrentPosition() >= 0){
//                        playNext();
//                    }
//                }
//                mHandler.postDelayed(mRunnable,5000);
//            }
//        }
//    };

    //弹窗
    private ClearCollectionDialog mDialog;
    private void showVipDialog(){
        if (mDialog == null){
            mDialog = new ClearCollectionDialog().builder(this);
            mDialog.setTitle("购买VIP会员，所有课程视频均可观看")
                    .setLeftText("立即购买");
            mDialog.setOnClickListener(new ClearCollectionDialog.OnClickListener() {
                @Override
                public void onRightClick() {

                }

                @Override
                public void onLeftClick() {
                    Intent intent = new Intent(CourseDetailActivity.this,VipActivity3.class);
                    startActivityForResult(intent,REQUEST_CODE_VIP);
                }
            });
            mDialog.setFocusOnCancleBtnR(false);
            mDialog.show();
        }else {
            mDialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null){
            mVideoView.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsPlaying = false;
//        mHandler.removeCallbacksAndMessages(null);
        mVideoView.stopPlayback();
    }

    public static VideoUrlModel mVideoUrlBean;
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case PY_HTTP_GET_COURSE_LIST:
//                getVideoDataSuccess(reqType,msg);
                mCourseListModel = (CourseListModel) msg.obj;
//             添加数据，填充多100个进去，做调试用
//                for (int i = 0; i < 100; i++) {
//                    mCourseListModel.getData().getClass_list().add(mCourseListModel.getData().getClass_list().get(0));
//                }
                getListSuccess();
                break;
            case PY_HTTP_GET_VIDEO_URL:
//                getVideoDataSuccess(reqType,msg);
                mVideoUrlBean = (VideoUrlModel) msg.obj;
                refreshPlayingBean(msg.arg1);
                playVideo();
                break;
            case PY_HTTP_ADD_COLLECT:
                mCourseListModel.getData().getCourse_info().setCollection(1);
                setCollect();
                break;
            case PY_HTTP_DEL_COLLECT:
                mCourseListModel.getData().getCourse_info().setCollection(0);
                setCollect();
                break;
        }
    }

    /**
     * 刷新正在播放的bean
     */
    private void refreshPlayingBean(int video_id) {
        List<CourseListModel.Databean.ClassListbean> mClassList = mCourseListModel.getData().getClass_list();
        for (int i = 0; i < mClassList.size(); i++) {
            CourseListModel.Databean.ClassListbean classListbean = mClassList.get(i);
            if (video_id == classListbean.getId()){
                mPlayingBean = classListbean;
                mPlayPosition = i;
            }
        }
        try {
            int position = -1;
            for (int i = 0; i < mRvCourse.getChildCount(); i++) {
                if (mRvCourse.getChildAt(i).hasFocus()){
                    position = i;
                }
            }
            //记录当前有焦点的item,刷新之后，再让item重新请求焦点
            mRvAdapterCourse.notifyDataSetChanged();
            final int finalPosition = position;
            mRvCourse.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (finalPosition != -1){
                        mRvCourse.getChildAt(finalPosition).requestFocus();
                        //记得移除监听
                        mRvCourse.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }catch (Exception e){
            LogUtil.e("__refreshPlayingBean","Exception");
        }

    }

    private void playVideo() {
        //本地的视频 需要在手机SD卡根目录添加一个 fl1234.mp4 视频
        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/fl1234.mp4" ;
        //网络视频
//        Uri uri = Uri.parse( mTestUrl);
//        Uri uri = Uri.parse(VIDEO_URL);
        Uri uri;
        if (mVideoUrlBean != null){
//            uri = Uri.parse(mTestUrl);
            uri = Uri.parse(mVideoUrlBean.getData().getUrl());
//            uri = Uri.parse("rtsp://10.79.2.18/88888888/16/20180213/268555162/268555162.ts?");
//          uri = Uri.parse(mTestUrl);
        }else {
            uri = Uri.parse("");
        }
        //设置视频控制器
//        mVideoView.setMediaController(mUniversalMediaController);
        //播放完成回调
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.e("__mVideoView_onCompletion","onCompletion");
                try {
                    if (mVideoView != null){
                        //监听播放完成，切换下一集
                        playNext();
                    }
                }catch (Exception e){
                    LogUtil.e("__mVideoView_Exception",e.getMessage() + "__");
                }
            }
        });
        //设置视频路径
        mVideoView.setVideoURI(uri);
//      mVideoView.setVideoPath(mVideoUrlBean.getRealUrl());
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                ToastUtil.showToast("播放错误");
                return false;
            }
        });
        mVideoView.setVideoViewCallback(this);
        mVideoView.start();
    }

    /**
     * 播放下一集
     */
    private void playNext() {
        List<CourseListModel.Databean.ClassListbean> mClassList = mCourseListModel.getData().getClass_list();
        if (mPlayPosition == mClassList.size() - 1){//表示已经是最后一集了，需要重头开始播放
            CourseListModel.Databean.ClassListbean classListbean = mClassList.get(0);
            mHttpHelper.getVideoUrl(this,classListbean.getId(),classListbean.getIs_free(),REQUEST_TYPE_PLAY_NEXT);
        }else {
            CourseListModel.Databean.ClassListbean classListbean = mClassList.get(mPlayPosition + 1);
            mHttpHelper.getVideoUrl(this,classListbean.getId(),classListbean.getIs_free(),REQUEST_TYPE_PLAY_NEXT);
        }
    }


    /**
     * 获取课程列表成功
     */
    private void getListSuccess() {
        if(mCourseListModel != null && mCourseListModel.getData()!=null){
            if (mCourseListModel.getData().getCourse_info() != null){
                //设置课程名字
                String title = mCourseListModel.getData().getCourse_info().getName();
                if (!TextUtils.isEmpty(title)){
                    mTvTitle.setText(title);
                }else {
                    title = "暂无课程名称";
                    mCourseListModel.getData().getCourse_info().setName(title);
                    mTvTitle.setText(title);
                }
                //设置介绍
                String introduce = mCourseListModel.getData().getCourse_info().getContent();
                if (!TextUtils.isEmpty(introduce)){
                    mTvIntroduce.setText(introduce);
                }else {
                    introduce = "暂无课程介绍";
                    mCourseListModel.getData().getCourse_info().setContent(introduce);
                    mTvIntroduce.setText(introduce);
                }
                //设置点击量
                int clickInt = mCourseListModel.getData().getCourse_info().getClick();
//                int clickInt = 10000;
                if (clickInt < 10000){//点击次数小于1万
                    mTvClick.setText(String.valueOf(clickInt));
                    mTvClickUnit.setText("次播放");
                }else {
                    double clickDouble = clickInt/10000d;
                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    mTvClick.setText(decimalFormat.format(clickDouble));
                    mTvClickUnit.setText("万次播放");
                }
            }
            //设置教师名字
            if (mCourseListModel.getData().getTeacher() != null){
                List<CourseListModel.Databean.Teacherbean> teacherList = mCourseListModel.getData().getTeacher();
                StringBuilder teacherBuilder = new StringBuilder("主讲老师：");
                if (teacherList.size() <= 0){
                    mTvTeacher.setText("主讲老师：暂无" );
                }else {
                    for (int i = 0; i < teacherList.size(); i++) {
                        int count = 0;//添加次数，如果有些老师名字没有，则不添加、
                        CourseListModel.Databean.Teacherbean bean = teacherList.get(i);
                        if (!TextUtils.isEmpty(bean.getTeacher())){
                            String teacher = bean.getTeacher();
                            if (count > 0){
                                teacherBuilder.append("、");
                            }
                            teacherBuilder.append(teacher);
                            count++;
                        }
                    }
                    mTvTeacher.setText(teacherBuilder.toString());
                }
            }
            if (mCourseListModel.getData().getClass_list() != null
                    && mCourseListModel.getData().getClass_list().size() > 0){
                arrangeData();
            }else {
                ToastUtil.showToast("暂无课程列表");
            }
        }else {
            ToastUtil.showToast("获取课程数据失败");
        }
        setCollect();
    }
    public List<String> mNavTags;//整理后的标签集合
    public List<List<CourseListModel.Databean.ClassListbean>> mClassLists;//整理分页后的课程集合
    public List<CourseListModel.Databean.ClassListbean> mCurrentClassList;//当前展示的页面
    public static CourseListModel.Databean.ClassListbean mPlayingBean;//正在播放的视频的bean
    public static  int mPlayPosition;//记录播放到哪个位置，方便播放下一集
//    public static CourseListModel.Databean.ClassListbean mPlayingBean;//正在播放的视频的bean
    /**
     * 整理课程数据，每页12个课程
     */
    private void arrangeData() {
        LogUtil.e("__initNavData","arrangeData");
        mNavTags = new ArrayList<>();
        mClassLists = new ArrayList<>();
        List<CourseListModel.Databean.ClassListbean> courseList = mCourseListModel.getData().getClass_list();
        List<CourseListModel.Databean.ClassListbean> list = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            courseList.get(i).setName(i+1 + "、" + courseList.get(i).getName());//给课程名字前加上序号
            list.add(courseList.get(i));
            if (list.size() == 12){//每页12个课程，够12个就放进去
                mClassLists.add(list);
                mNavTags.add((i - 10) + "-" + (i + 1));//放标签
                list = new ArrayList<>();
            }else if (i == courseList.size() - 1){//所有数据放完了，也放进去
                mClassLists.add(list);
                if (list.size() == 1){
                    mNavTags.add(String.valueOf(i + 1));//放标签
                }else {
                    mNavTags.add(((i + 1) - (list.size() - 1 )) + "-" + (i + 1));//放标签
                }
            }
        }
        LogUtil.e("__initNavData","arrangeData","整理数据结束");
        //以上为数据整理，整理完之后，开始初始化导航栏数据
        initNavData();
        initCourseListData();
//        设置第一个为播放
        mPlayingBean = mCurrentClassList.get(0);
        mHttpHelper.getVideoUrl(this,mPlayingBean.getId(),mPlayingBean.getIs_free(),REQUEST_TYPE_PLAY_CLICK);
        mPlayPosition = 0;
    }

    private RvAdapterCourse mRvAdapterCourse;

    /**
     * 初始化课程列表数据
     */
    private void initCourseListData() {
        LogUtil.e("__initCourseListData","mClassLists","size",mClassLists.size() + "");
        mCurrentClassList = mClassLists.get(mNavSelPosition);
        LogUtil.e("__initCourseListData","mCurrentClassList","size",mCurrentClassList.size() + "");
        mRvAdapterCourse = new RvAdapterCourse(mCurrentClassList,this);
        mRvAdapterCourse.setItemEventListener(new RvAdapterCourse.ItemEventListener() {
            @Override
            public void onItemClick(int position) {
                mListbeanPrepare = mCurrentClassList.get(position);
                mHttpHelper.getVideoUrl(CourseDetailActivity.this, mListbeanPrepare.getId(), mListbeanPrepare.getIs_free(),CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK);
//                mListbeanPrepare = mCurrentClassList.get(position);
//                if (mListbeanPrepare.getIs_free()==1){
//                    if (!UserManager.isSignTokenNULL()){
//                        LogUtil.e("______________sssssssssss");
//                        mHttpHelper.getVideoUrl(CourseDetailActivity.this, mListbeanPrepare.getId(), mListbeanPrepare.getIs_free(),REQUEST_TYPE_PLAY_CLICK);
////                LogUtil.e("__mRvAdapterCourse","onclick","name",mCurrentClassList.get(position).getName());
//                    }else {
//                        UserManager.isSignTokenIntentActivity(getApplicationContext());
//                    }
//                }else {
//                    LogUtil.e("________________sssss",REQUEST_TYPE_PLAY_CLICK+"");
//                    mHttpHelper.getVideoUrl(CourseDetailActivity.this, mListbeanPrepare.getId(), mListbeanPrepare.getIs_free(),REQUEST_TYPE_PLAY_CLICK);
//                }

            }

            @Override
            public void onItemFocusChange(int position) {
                mFocusPosition = position + 5;
//                LogUtil.e("__mRvAdapterCourse","__onItemFocusChange",position + "");
//                LogUtil.e("__mRvAdapterCourse","mFocusPosition",mFocusPosition + "");
                if (position < 4){//不要频繁刷新，当第一排item获取焦点时刷新就行
                    notifiAdapterChange();
                }
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        mRvCourse.setLayoutManager(layoutManager);
        mRvCourse.setAdapter(mRvAdapterCourse);
    }
    //课程导航栏Adapter
    private RvAdapterCourseNav mRvAdapterCourseNav;
    private int mNavSelPosition;//导航栏选中position
    private int mNavRealSelPosition;//导航栏复用之后，在rv中实际的position

    /**
     * 初始化导航栏
     */
    private void initNavData() {
        LogUtil.e("__initNavData","Tag.size",mNavTags.size() + "");
        for (int i = 0; i < mNavTags.size(); i++) {
            LogUtil.e("__initNavData","Tag" + i,mNavTags.get(i) + "");
        }
        mRvAdapterCourseNav = new RvAdapterCourseNav(mNavTags,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvAdapterCourseNav.setItemFocusChangeListener(new RvAdapterNavCourseDetail.OnItemFocusChangeListener() {
            @Override
            public void onItemFocusChange(int position) {
                mFocusPosition = FOCUS_ON_NAV;
                mNavSelPosition = position;
                try {
                    mRvNav.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            for (int i = 0; i < mRvNav.getChildCount(); i++) {
                                if (mRvNav.getChildAt(i).hasFocus()){
                                    mNavRealSelPosition = i;
                                }
                            }
                        }
                    });
                    for (int i = 0; i < mRvNav.getChildCount(); i++) {
                        if (mRvNav.getChildAt(i).hasFocus()){
                            mNavRealSelPosition = i;
                        }
                    }
                    mRvNav.smoothToCenter(position);
                } catch (Exception e) {
                }
                //遍历所有子view，设置字体颜色，相当于刷新adapter
                for (int i = 0; i < mRvNav.getChildCount(); i++) {
                    TextView tv = (TextView) mRvNav.getChildAt(i);
                    tv.setTextColor(getResources().getColor(R.color.color_white));
                    tv.setFocusable(true);
                }
                initCourseListData();
            }
        });
        mRvNav.setLayoutManager(manager);
        mRvNav.setAdapter(mRvAdapterCourseNav);
    }

    /**
     * 设置是否已收藏
     */
    private void setCollect() {
        //是否收藏,0没收藏，1为收藏
        if (mCourseListModel.getData().getCourse_info().getCollection() == 1){
            mTvCollect.setText("已收藏");
            if (mLlCollect.hasFocus()){//有焦点
                mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.collected_selected));
            }else {
                mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.collected_selected));
            }
        }else {
            mTvCollect.setText("收藏");
            if (mLlCollect.hasFocus()){//有焦点
                mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.collect_icon_selected_main));
            }else {
                mIvCollect.setImageDrawable(getResources().getDrawable(R.drawable.icon_collect_normal_video_activity));
            }
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case HTTP_GET_VIDEO_DATA:
                ToastUtil.showToast(String.valueOf(error));
                break;
            case PY_HTTP_GET_VIDEO_URL:
                ToastUtil.showToast(String.valueOf(error));
                break;
        }
    }

    @Override
    public void onTokenError(int reqType, String error, String errorCode) {
        toLogin();
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
//        LogUtil.e("__onStart","onStart");
        mFlLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onHttpErrorMsg(int reqType, Message message, String error) {
        switch (reqType){
//            case PY_HTTP_GET_VIDEO_URL:
//                if (message.what == REQUEST_TYPE_PLAY_NEXT){
//                    if (UserManager.isSignTokenNULL()){
//                        if (message.arg1 == BaseHelper.ERROR_NOT_VIP){//表示播放下一个时，到了vip才能看的视频，此时从头开始播放
////                      设置第一个为播放
//                            mPlayingBean = mCurrentClassList.get(0);
//                            mHttpHelper.getVideoUrl(this,mPlayingBean.getId(),mPlayingBean.getIs_free(),REQUEST_TYPE_PLAY_CLICK);
//                        }else {
////                            ToastUtil.showToast(String.valueOf(error));
//                            ToastUtil.showToast("您还不是会员");
//                            showVipDialog();
//                        }
//                    }
////                    ToastUtil.showToast(String.valueOf(error));
//
//                }else if (message.what == REQUEST_TYPE_PLAY_CLICK){
//                    if (message.arg1 == BaseHelper.ERROR_NOT_VIP){
//                        showVipDialog();
//                    }
////                    ToastUtil.showToast(String.valueOf(error));
//                }
//                break;
            case PY_HTTP_GET_VIDEO_URL:
                if (message.what == CourseDetailActivity.REQUEST_TYPE_PLAY_NEXT){
                    LogUtil.e("__onHttpErrorMsg","what","PLAY_NEXT");
//                    ToastUtil.showToast(String.valueOf(error));
                    if (message.arg1 == BaseHelper.ERROR_NOT_VIP){//表示播放下一个时，到了vip才能看的视频，此时从头开始播放
                        if(UserManager.isSignTokenNULL()){
                            //                      设置第一个为播放
                            CourseDetailActivity.mPlayingBean = mCurrentClassList.get(0);
                            mHttpHelper.getVideoUrl(this,CourseDetailActivity.mPlayingBean.getId(),CourseDetailActivity.mPlayingBean.getIs_free(),CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK);
                        }else{
                            UserManager.isSignTokenIntentActivity(this,3);
                            ToastUtil.showToast("您还不是会员");
                        }

                    }else {
                        ToastUtil.showToast(String.valueOf(error));
                        showVipDialog();
                    }
                }else if (message.what == CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK){
//                    LogUtil.e("__onHttpErrorMsg","what","PLAY_CLICK");
//                    ToastUtil.showToast(String.valueOf(error));
                    showVipDialog();
                }
                break;
        }
    }
    private int mSeek;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        LogUtil.e("__onActivityResult","getUrl",mVideoUrlBean.getData().getUrl());
        LogUtil.e("__onActivityResult","RESULT_OK",String.valueOf(resultCode == RESULT_OK));
        LogUtil.e("__onActivityResult","requestCode == REQUEST_CODE_PLAY_VIDEO",String.valueOf(requestCode == REQUEST_CODE_PLAY_VIDEO));
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_VIP){
                if (mVideoView != null){
//                mVideoView.seekTo(mSeek);
//                playVideo();
//                refreshPlayingBean(mPlayingBean.getId());
                    if (mListbeanPrepare != null){
                        mHttpHelper.getVideoUrl(this,mListbeanPrepare.getId(),mListbeanPrepare.getIs_free(),REQUEST_TYPE_PLAY_CLICK);
                    }
                }
            }else if (requestCode == REQUEST_CODE_PLAY_VIDEO){
                if (mVideoView != null){
                    try {
                        int position = data.getIntExtra("seek",0);

                        if (mVideoUrlBean != null){
                            LogUtil.e("__onActivityResult","getUrl",mVideoUrlBean.getData().getUrl());
                            LogUtil.e("__onActivityResult","position",position + "");
//                            LogUtil.e("__onActivityResult","",mVideoUrlBean.getData().getUrl());
                            refreshPlayingBean(mPlayingBean.getId());
                            playVideo();
                            mVideoView.seekTo(position);
                        }
//            mVideoView.start();
                    }catch (Exception e){

                    }
                }
            }
        }
    }
}
