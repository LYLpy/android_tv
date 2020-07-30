package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CourseListModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.VideoUrlModel;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterNavCourseDetail;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterCourse;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterCourseNav;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ClearCollectionDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.MyTvRecyclerView;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_GET_COURSE_LIST;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_GET_VIDEO_URL;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/9------更新------
 * 视频播放界面
 */

public class VideoPlayActivity extends BaseActivity implements UniversalVideoView.VideoViewCallback, UniversalMediaController.StateChangeCallback, BaseHelper.NewIHttpCallback {

//    public String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    public String videoUrl = "http://google.haccc.cn/videos/big_buck_bunny.mp4";
    @BindView(R.id.video_view)
    UniversalVideoView mVideoView;
    @BindView(R.id.media_controller)
    UniversalMediaController mMediaController;
    @BindView(R.id.tv_course_name)
    TextView mTvCourseName;
    @BindView(R.id.tv_video_name)
    TextView mTvVideoName;
    @BindView(R.id.tv_playing_video_name)
    TextView mTvPlayingVideoName;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_title_part_right)
    LinearLayout mLlTitlePartRight;
    @BindView(R.id.ll_course_list)
    LinearLayout mLlCourseList;
    @BindView(R.id.rl_title_part)
    RelativeLayout mRlTitlePart;
    @BindView(R.id.rl_soon_play)
    RelativeLayout mRlSoonPlay;
    @BindView(R.id.tv_soon_play_name)
    TextView mTvSoonPlayName;
    @BindView(R.id.rv_content)
    RecyclerView mRvCourse;
    @BindView(R.id.rv_nav)
    MyTvRecyclerView mRvNav;
    //    private VideoView mVideoView;
//    private MediaController mController;
    public static final String KEY_COURSE_ID = "courseId";
    public static final String KEY_SEEK = "seek";
    private int seek;//播放进度
    private String mCourseId;
    private int mFocusPosition;//焦点所在位置,0表示在课程介绍，1表示在全屏，2表示在收藏，3表示在vip购买，4表示在导航栏，5以上表示在课程Item
    private CourseListModel.Databean.ClassListbean mListbeanPrepare;//点击准备播放的视频，如果购买VIP回来，会播放他点击的这个

    @Override
    protected void initData() {
        Intent intent = getIntent();
        seek = intent.getIntExtra(KEY_SEEK,0);
                mCourseId = intent.getStringExtra(KEY_COURSE_ID);
                mHttpHelper.getCourseList(this,mCourseId,10000,1);
                mTvSoonPlayName.setText("即将播放：" + CourseDetailActivity.mPlayingBean.getName());
                playVideo();
                mVideoView.seekTo(seek);
//        mVideoId = intent.getStringExtra("videoId");
//        LogUtil.e("__initData_videoId",String.valueOf(mVideoId==null));
//        LogUtil.e("__initData_videoId",String.valueOf(mVideoId + "..."));
//        mHttpHelper.getStart(CommonUtils.getCurrentTimeStampString(),this);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initView() {
//        playDemo();
        mMediaController.setStateChangeCallback(this);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 播放demo
     */
    private void playDemo() {
        LogUtil.e("__playDemo", "playDemo");
        //网络视频
        Uri uri = Uri.parse(videoUrl);
//        mVideoView = findViewById(R.id.video_view);
//        mController = new MediaController(this);
//        mVideoView.setVideoViewCallback(this);
        //设置视频控制器
        mVideoView.setMediaController(mMediaController);
        //播放完成回调
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.e("__onCompletion", "onCompletion");
                playNext();
            }
        });
        //设置视频路径
        mVideoView.setVideoURI(uri);
        mMediaController.setStateChangeCallback(this);
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(VideoPlayActivity.this, "播放错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mVideoView.requestFocus();
//        mHandler.post(mRunnable);
        //开始播放视频
        mVideoView.start();
    }

    /**
     * 当课程数据是
     */
    private void initDataCourseDetail(){

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent();
                intent.putExtra("seek",0);
                if (mVideoView != null){
                    intent.putExtra("seek",mVideoView.getCurrentPosition());
//                    setIntent(intent);
                }
                setResult(RESULT_OK,intent);
                finish();
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mShowListTime = 3000;
                LogUtil.e("__onKeyDown方向下", "方向下");
                if (mLlCourseList.getVisibility() == View.GONE){
                    setCourseListVisible(true);
                    return true;
                }else {
                    mMediaController.hide();
                    return false;
                }
//                break;
//                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                mShowListTime = 3000;
                if (mLlCourseList.getVisibility() == View.VISIBLE){
                    mRvNav.getChildAt(mNavSelPosition).requestFocus();
                    return true;
                }
                LogUtil.e("__onKeyDown方向上", "方向上");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mShowListTime = 3000;
                if (mLlCourseList.getVisibility() == View.VISIBLE){
                    return true;
                }
                LogUtil.e("__onKeyDown方向左_isShowing",  mMediaController.isShowing() + "");
                //后退10秒
//                    seek = mVideoView.getCurrentPosition() + (10 * 1000);
                    LogUtil.e("__onKeyDown方向左","前进10秒_getDuration", mVideoView.getDuration() + "");
                    LogUtil.e("__onKeyDown方向左","前进10秒_getCurrentPosition", mVideoView.getCurrentPosition() + "");
                    LogUtil.e("__onKeyDown方向左","前进10秒_getBufferPercentage", mVideoView.getBufferPercentage() + "");
//                    int position = mVideoView.getCurrentPosition();//当前播放位置
//                    int duration = mVideoView.getDuration();//视频真实长度
//                    if (duration > 0) {
//                        // use long to avoid overflow
//                        long pos = 1000L * position / duration;
//                        mMediaController.getProgress().setProgress((int) pos);
//                    }
//                    int percent = mVideoView.getBufferPercentage();
//                    mMediaController.getProgress().setSecondaryProgress(percent * 10);
//                    if (mVideoView.getCurrentPosition() - 15000 > 0){
//                        mVideoView.seekTo(mVideoView.getCurrentPosition() - 15000);
//                    }else {
//                        mVideoView.seekTo(0);
//                    }
                if (!mMediaController.isShowing()){
                    mMediaController.show();
                    return true;
                }else {
                    int seek = mVideoView.getCurrentPosition();
                    if (seek > (10 * 1000)){
                        mVideoView.seekTo(Integer.valueOf(seek - (10 * 1000)));
                    }else {
                        mVideoView.seekTo(0);
                    }
                    return true;
                }
//                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mShowListTime = 3000;
                if (mLlCourseList.getVisibility() == View.VISIBLE){
                    return true;
                }
                LogUtil.e("__onKeyDown方向右", "方向右");
                //快进10秒
//                    seek = mVideoView.getCurrentPosition() + (10 * 1000);
                    LogUtil.e("__onKeyDown方向左","前进10秒_getDuration", mVideoView.getDuration() + "");
                    LogUtil.e("__onKeyDown方向左","前进10秒_getCurrentPosition", mVideoView.getCurrentPosition() + "");
                    LogUtil.e("__onKeyDown方向左","前进10秒_getBufferPercentage", mVideoView.getBufferPercentage() + "");
//                    mVideoView.seekTo((mVideoView.getCurrentPosition() + 10) * 1000);
//                    mMediaController.getProgress().setProgress(500);
//                    mVideoView.seekTo(50 * 1000);
//                    mVideoView.seekTo(mVideoView.getCurrentPosition() + 15000);
                if (!mMediaController.isShowing()){
                    mMediaController.show();
                    return true;
                }else {
                    mVideoView.seekTo(Integer.valueOf(mVideoView.getCurrentPosition() + (15 * 1000)));
                    return true;
                }
//                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                LogUtil.e("__onKeyDown_中间", "CENTER");
                break;
            case KeyEvent.KEYCODE_ENTER:
                LogUtil.e("__onKeyDown_ENTER", "ENTER");
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        LogUtil.d("__video","onPause","onPause");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        LogUtil.d("__video","onStart","onStart");
        mRlSoonPlay.setVisibility(View.GONE);
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        LogUtil.d("__video","onBufferingStart","onBufferingStart");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        LogUtil.d("__video","onBufferingEnd","onBufferingEnd");
    }

    @Override
    public void onHide() {
        Log.d("__controller","onHide");
        if (mRlTitlePart != null){
            mRlTitlePart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onShow() {
        Log.d("__controller","onShow");
        if (mRlTitlePart != null){
            mRlTitlePart.setVisibility(View.VISIBLE);
            mTvTime.setText(CommonUtils.getSysTime("HH:mm"));
        }
    }
    public CourseListModel mCourseListModel;
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case PY_HTTP_GET_COURSE_LIST:
//                getVideoDataSuccess(reqType,msg);
                mCourseListModel = (CourseListModel) msg.obj;
                getListSuccess();
                break;
            case PY_HTTP_GET_VIDEO_URL:
//                getVideoDataSuccess(reqType,msg);
                LogUtil.e("________________PY_HTTP_GET_VIDEO_URL");
                CourseDetailActivity.mVideoUrlBean = (VideoUrlModel) msg.obj;
                refreshPlayingBean(msg.arg1);
                playVideo();
                break;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        LogUtil.e("__onHttpError","error",error);
        switch (reqType){
            case HttpHelper.PY_HTTP_START:

                break;
            case PY_HTTP_GET_VIDEO_URL:
                ToastUtil.showToast(error);
                break;
        }
    }

    @Override
    public void onTokenError(int reqType, String error, String errorCode) {
        toLogin();
    }

    @Override
    public void onHttpErrorMsg(int reqType, Message message, String error) {
        LogUtil.e("__onHttpErrorMsg","error",error);
        switch (reqType){
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
    private RvAdapterCourse mRvAdapterCourse;//课程列表adapter
    /**
     * 刷新正在播放的bean
     */
    private void refreshPlayingBean(int video_id) {
        List<CourseListModel.Databean.ClassListbean> mClassList = mCourseListModel.getData().getClass_list();
        for (int i = 0; i < mClassList.size(); i++) {
            CourseListModel.Databean.ClassListbean classListbean = mClassList.get(i);
            if (video_id == classListbean.getId()){
                CourseDetailActivity.mPlayingBean = classListbean;
                CourseDetailActivity.mPlayPosition = i;
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

    /**
     * 播放视频
     */
    private void playVideo() {
        //本地的视频 需要在手机SD卡根目录添加一个 fl1234.mp4 视频
//        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/fl1234.mp4" ;
        //网络视频
//        Uri uri = Uri.parse( mTestUrl);
        mTvPlayingVideoName.setText("正在播放：" + CourseDetailActivity.mPlayingBean.getName());
        mTvVideoName.setText("正在播放：" + CourseDetailActivity.mPlayingBean.getName());
        Uri uri;
        if (CourseDetailActivity.mVideoUrlBean != null){
//            uri = Uri.parse(mTestUrl);
            uri = Uri.parse(CourseDetailActivity.mVideoUrlBean.getData().getUrl());
        }else {
            uri = Uri.parse("");
        }
        //设置视频控制器
        mVideoView.setMediaController(mMediaController);
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
        if (CourseDetailActivity.mPlayPosition == mClassList.size() - 1){//表示已经是最后一集了，需要重头开始播放
            CourseListModel.Databean.ClassListbean classListbean = mClassList.get(0);
            mHttpHelper.getVideoUrl(this,classListbean.getId(),classListbean.getIs_free(),CourseDetailActivity.REQUEST_TYPE_PLAY_NEXT);
        }else {
            CourseListModel.Databean.ClassListbean classListbean = mClassList.get(CourseDetailActivity.mPlayPosition + 1);
            mHttpHelper.getVideoUrl(this,classListbean.getId(),classListbean.getIs_free(),CourseDetailActivity.REQUEST_TYPE_PLAY_NEXT);
        }
    }

    /**
     * 获取课程列表成功
     */
    private void getListSuccess() {
        if (mCourseListModel != null && mCourseListModel.getData() != null) {
            if (mCourseListModel.getData().getCourse_info() != null) {
                if (mCourseListModel.getData().getClass_list() != null
                        && mCourseListModel.getData().getClass_list().size() > 0) {
                    arrangeData();
                } else {
                    ToastUtil.showToast("暂无课程列表");
                }
            } else {
                ToastUtil.showToast("获取课程数据失败");
            }
        }
    }
    public List<String> mNavTags;//整理后的标签集合
    public List<List<CourseListModel.Databean.ClassListbean>> mClassLists;//整理分页后的课程集合
    public List<CourseListModel.Databean.ClassListbean> mCurrentClassList;//当前展示的页面
    public int mPlayPosition;//记录播放到哪个位置，方便播放下一集
    /**
     * 整理课程数据
     */
    private void arrangeData() {
        LogUtil.e("__initNavData", "arrangeData");
        mNavTags = new ArrayList<>();
        mClassLists = new ArrayList<>();
        List<CourseListModel.Databean.ClassListbean> courseList = mCourseListModel.getData().getClass_list();
        List<CourseListModel.Databean.ClassListbean> list = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            list.add(courseList.get(i));
            if (list.size() == 12) {//每页12个课程，够12个就放进去
                mClassLists.add(list);
                mNavTags.add((i - 10) + "-" + (i + 1));//放标签
                list = new ArrayList<>();
            } else if (i == courseList.size() - 1) {//所有数据放完了，也放进去
                mClassLists.add(list);
                if (list.size() == 1) {
                    mNavTags.add(String.valueOf(i + 1));//放标签
                } else {
                    mNavTags.add(((i + 1) - (list.size() - 1)) + "-" + (i + 1));//放标签
                }
            }
        }
        LogUtil.e("__initNavData","arrangeData","整理数据结束");
        //以上为数据整理，整理完之后，开始初始化导航栏数据
        initNavData();
        initCourseListData();
//        设置第一个为播放
//        mPlayingBean = mCurrentClassList.get(0);
//        mHttpHelper.getVideoUrl(this,mPlayingBean.getId(),mPlayingBean.getIs_free(),CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK);
//        mPlayPosition = 0;
    }
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
                mFocusPosition = CourseDetailActivity.FOCUS_ON_NAV;
                LogUtil.e("__onItemFocusChange_mNavSelPosition",mNavSelPosition + "");
                mNavSelPosition = position;
                mShowListTime = 3000;
                for (int i = 0; i < mRvNav.getChildCount(); i++) {
                    if (mRvNav.getChildAt(i).hasFocus()){
                        mNavRealSelPosition = i;
                    }
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
    //课程导航栏Adapter
    private RvAdapterCourseNav mRvAdapterCourseNav;
    private int mNavSelPosition;//导航栏选中position
    private int mNavRealSelPosition;//导航栏复用之后，在rv中实际的position
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
//                CourseListModel.Databean.ClassListbean listbean = mCurrentClassList.get(position);
                mHttpHelper.getVideoUrl(VideoPlayActivity.this, mListbeanPrepare.getId(), mListbeanPrepare.getIs_free(),CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK);
//                LogUtil.e("__mRvAdapterCourse","onclick","name",mCurrentClassList.get(position).getName());
            }

            @Override
            public void onItemFocusChange(int position) {
//                mFocusPosition = position + 5;
//                LogUtil.e("__mRvAdapterCourse","__onItemFocusChange",position + "");
//                LogUtil.e("__mRvAdapterCourse","mFocusPosition",mFocusPosition + "");
                if (position == 0){
                    notifiAdapterChange();
                }
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        mRvCourse.setLayoutManager(layoutManager);
        mRvCourse.setAdapter(mRvAdapterCourse);
    }

    private void notifiAdapterChange() {
        if (mRvAdapterCourseNav == null) {
            return;
        }
        LogUtil.e("__notifiAdapterChange","notifiAdapterChange");
        mShowListTime = 3000;
        mRvAdapterCourseNav.notifyDataSetChanged();
    }

    private int mShowListTime = 3000;//展示课程列表的时间
    /**
     *设置课程列表状态
     * @param isVieible 是否展示，true展示，false隐藏
     */
    private void setCourseListVisible(boolean isVieible){
        LogUtil.e("__setCourseListVisible_mNavSelPosition",mNavSelPosition +"");
        if (isVieible){//展示
            mMediaController.hide();
//            mVideoView.setControlling(false);
            try {
                mLlCourseList.setVisibility(View.VISIBLE);
                mTvPlayingVideoName.setVisibility(View.VISIBLE);
//                mRvNav.smoothScrollToPosition(mNavSelPosition);
//                LogUtil.e("__setCourseListVisible_mNavSelPosition",String.valueOf(mNavSelPosition >= mRvNav.getChildCount() -1));
                if (mNavSelPosition <= mRvNav.getChildCount() -0){
                    mRvNav.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            LogUtil.e("__setCourseListVisible_onGlobalLayout_",mRvNav.getChildCount() +"");
                            mRvNav.getChildAt(mNavSelPosition).requestFocus();
                            mRvNav.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }else {
                    mRvNav.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            mRvNav.getChildAt(mRvNav.getChildCount() -0).requestFocus();
                            mRvNav.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }
            }catch (Exception e){

            }
            //3秒后隐藏
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mShowListTime > 0){
                        mShowListTime = mShowListTime - 1000;
                        delayHandler.postDelayed(this,1000);
                    }else {
                        mShowListTime = 3000;
                        setCourseListVisible(false);
//                        mVideoView.setControlling(true);
                    }
                }
            },3000);
        }else {//隐藏
            mLlCourseList.setVisibility(View.GONE);
            mTvPlayingVideoName.setVisibility(View.GONE);
        }
    }

    /**
     * 计算正在播放的视频所在的标签，方便展示课程列表的时候，选中正在播放的标签
     */
    private void calculatePlayingTag(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

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
                    Intent intent = new Intent(VideoPlayActivity.this,VipActivity3.class);
                    startActivityForResult(intent,CourseDetailActivity.REQUEST_CODE_VIP);
                }
            });
            mDialog.setFocusOnCancleBtnR(false);
            mDialog.show();
        }else {
            mDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CourseDetailActivity.REQUEST_CODE_VIP) {
                if (mVideoView != null) {
//                mVideoView.seekTo(mSeek);
//                playVideo();
//                refreshPlayingBean(mPlayingBean.getId());
                    if (mListbeanPrepare != null) {
                        mHttpHelper.getVideoUrl(this, mListbeanPrepare.getId(), mListbeanPrepare.getIs_free(), CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK);
                    }
                }
            }
        }
    }
}
