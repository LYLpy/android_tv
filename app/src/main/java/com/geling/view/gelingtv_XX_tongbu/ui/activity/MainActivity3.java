
package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.ChildNavModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.MainNavModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.MainNavModel3;
import com.geling.view.gelingtv_XX_tongbu.model.bean.OhterModel1;
import com.geling.view.gelingtv_XX_tongbu.model.bean.VideoUrlBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod.HomeOtherModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SearchModelPy;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.UserInfoModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.VideoUrlModel;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterChildNav;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterMainNav;
import com.geling.view.gelingtv_XX_tongbu.ui.event.EventSignUpdate;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.main.ChildFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.main.MainFragmentType1;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.main.MainFragmentType4;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.main.MainFragmentType5;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.main.PrimarySchoolFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseForbiddenDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CustomRecycleView;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ExitPageDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GsonUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.SysUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoViewSmall;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.HTTP_GET_VIDEO_DATA;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_GET_USER_INFO;
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.PY_HTTP_GET_VIDEO_URL;//首页的播放接口
import static com.geling.view.gelingtv_XX_tongbu.model.HttpHelper.RECOMMEND_CLICK_TYPE_POSITION;

/**
 * MainActivity class that loads {@link MainFragmentType1}.
 */
public class MainActivity3 extends BaseActivity implements View.OnClickListener, BaseHelper.IHttpCallback {

    public String mVideoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    //                                                              旧项目的包名 com.geling.view.gelingtv_JX
    private String pac = "com.geling.view.gelingtv_JX";
    private List<MainNavModel> mNavTitleAll = new ArrayList<>();//首页导航栏全部8个item
    private List<MainNavModel> mNavTitleReality = new ArrayList<>();//首页导航栏实际item
    private RvAdapterMainNav mRvAdapterMainNav;
    @BindView(R.id.activity_main_bag_imag)
    ImageView mActivityMainBagImag;
    @BindView(R.id.iv_vip_info)
    ImageView mIvVipInfo;
    @BindView(R.id.tv_vip_info)
    TextView mTvVipInfo;
    @BindView(R.id.ll_vip_info)
    LinearLayout mLlVipInfo;
    @BindView(R.id.iv_history)
    ImageView mIvHistory;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.ll_history)
    LinearLayout mLlHistory;
    @BindView(R.id.iv_mine)
    ImageView mIvMine;
    @BindView(R.id.tv_mine)
    TextView mTvMine;
    @BindView(R.id.ll_collect)
    LinearLayout mLMine;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;

    @BindView(R.id.ll_top)
    LinearLayout mLlTop;

    @BindView(R.id.rv_nav_main)
    CustomRecycleView mRvNavMain;
    @BindView(R.id.fl)
    FrameLayout mFl;

    FrameLayout mFlNotData;

    private List<ImageView> mIvListHome = new ArrayList<>();//首页ImageView集合
    private RvAdapterChildNav mAdapterChildNav;
    private List<ChildNavModel> mDataChildNav = new ArrayList<>();
    private RecyclerView.LayoutManager mManagerChild;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private String mHomeType;//首页五种模式，对应MainFragmentType:1,MainFragmentType:2,MainFragmentType:3...
    private MainNavModel3 mMainNavData;//主页导航栏数据
    private HomeModel mHomeModel;//首页模块数据
    private List<HomeModel.Databean.RecommendationDatabean> mHomeModelList = new ArrayList<>();//首页非Banner非视频数据
    private List<HomeModel.Databean.RecommendationDatabean.LoopDatabean> mHomeBannerList;//首页Banner数据
    private List<HomeOtherModel> mOtherModelList = new ArrayList<>();

    private int positionX;//banner图位置当前
    private int mMainNavSelectedPosition;//适配器选择的item位置
    private int mMainNavCurrentSelectedPosition;//rv复用之后，实际选中的位置
    private boolean mIsFocusOnMainNav;
    private boolean mIsUpToMainNav;//向上是否跳转到主导航栏上
    /*焦点所在类型，
    *1：表示焦点在最上面一栏（vip、历史、搜索那里）；
    *2：焦点在主导航栏上；
    *3：31、焦点在首页顶部；32焦点在首页底部
    *4：41、焦点在幼儿（专题、初中、高中）推荐位顶部；41、幼儿推荐位底部；421、幼儿课程位顶部；422、幼儿推荐位底部
    *5：51、焦点在小学推荐位顶部；51、小学推荐位底部；521、小学课程位顶部；522、小学推荐位底部
    */
    private int mFocusType;
    private static final int FOCUS_ON_TOP = 1;
    private static final int FOCUS_ON_MAIN_NAV = 2;
    private static final int FOCUS_ON_HOME_TOP = 31;//焦点在首页上面的View,再向上就跳到主导航栏
    private static final int FOCUS_ON_HOME_CENTER = 32;//焦点在首页中间的View
    private static final int FOCUS_ON_HOME_BOTTOM = 33;//焦点在首页下面的View
    private static final int FOCUS_ON_CHILD_RECOMMEND_TOP = 411;
    private static final int FOCUS_ON_CHILD_RECOMMEND_CENTER = 412;
    private static final int FOCUS_ON_CHILD_RECOMMEND_BOTTOM = 413;
    private static final int FOCUS_ON_CHILD_SUBJECT_TOP = 421;
    private static final int FOCUS_ON_CHILD_SUBJECT_BOTTOM = 422;
    private static final int FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP = 511;
    private static final int FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER = 512;
    private static final int FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM = 513;
    private static final int FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP = 521;
    private static final int FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM = 522;
    private static final int FOCUS_ON_FM_NAV_TOP = 61;//焦点在左边导航栏顶部
    private static final int FOCUS_ON_FM_NAV_BOTTOM = 62;//焦点在左边导航栏底部
    private BaseFragment mHomeFm;
    private LinearLayout mLlHomeRootView;//当首页是包含视频的时候，这个就是首页根布局
    //首页的iv
    private ImageView mHomeIv1;//
    private ImageView mHomeIv2;//
    private ImageView mHomeIv3;//
    private ImageView mHomeIv4;//
    private ImageView mHomeIv5;//
    private ImageView mHomeIv6;//
    private ImageView mHomeIv7;//
    private ImageView mHomeIv8;//
    private UniversalVideoViewSmall mVideoView;
//    private LinearLayout mFlVideoViewParent;
    private FrameLayout mFlVideoViewParent;
    private ExitPageDialog mExitPageDialog;
    private SearchModelPy mExitPageModel;//退出界面数据,这里直接用热门搜索的
    private List<List<SearchModelPy.DatabeanX.Databean>> mExitPaging;//分页之后的数据
    private int mDialogCurrentPage;//当前页码
    private int mDialogTotalPage;//总页数
    private BaseFragment mCurrentFm;//当前的fragment
    private UniversalMediaController mMediaController;
    private String mBtnNorBgColor;//按钮正常背景颜色
    private String mBtnFocBgColor;//按钮聚焦背景颜色
    private String mTextNorColor;//正常时字体颜色
    private String mTextFocColor;//聚焦时字体颜色

    @Override
    protected void initData() {
        mNavTitleAll.add(new MainNavModel("首页", R.drawable.home_normal, R.drawable.home_selected, R.drawable.home_focus, 0));
        mNavTitleAll.add(new MainNavModel("幼儿", R.drawable.child_normal, R.drawable.child_selected, R.drawable.child_focus, 1));
        mNavTitleAll.add(new MainNavModel("专题", R.drawable.subject_normal, R.drawable.subject_selected, R.drawable.subject_focus, 2));
        mNavTitleAll.add(new MainNavModel("一年级", R.drawable.grade1_normal, R.drawable.grade1_selected, R.drawable.grade1_focus, 3));
        mNavTitleAll.add(new MainNavModel("二年级", R.drawable.grade2_normal, R.drawable.grade2_selected, R.drawable.grade2_focus, 4));
        mNavTitleAll.add(new MainNavModel("三年级", R.drawable.grade3_normal, R.drawable.grade3_selected, R.drawable.grade3_focus, 5));
        mNavTitleAll.add(new MainNavModel("四年级", R.drawable.grade4_normal, R.drawable.grade4_selected, R.drawable.grade4_focus, 6));
        mNavTitleAll.add(new MainNavModel("五年级", R.drawable.grade5_normal, R.drawable.grade5_selected, R.drawable.grade5_focus, 7));
        mNavTitleAll.add(new MainNavModel("六年级", R.drawable.grade6_normal, R.drawable.grade6_selected, R.drawable.grade6_focus, 8));
        initMainData();
    }

    private static final String APP_KEY = "HN5zcglmXSHXaQZEImVwBuYvipFKEHUf";

    /**
     * 初始化主页数据
     */
    private void initMainData() {
        //加载logo
        loadLogo();
        //获取导航栏数据
//        mHttpHelper.getMainNavData(this);
        mHttpHelper.getMainNav(this);
    }

    /**
     * 加载logo
     */
    private void loadLogo() {
        if (MainApp.getInstance().getOhterModel() == null
                || MainApp.getInstance().getOhterModel().getData() == null) {
            //如果没有logo的Url，就到后台获取
            mHttpHelper.getStart(this);
        } else {//如果有logo的Url，就直接加载
//            GroceryStoreUtils.GlideImag(MainActivity3.this, MainApp.getInstance().getOhterModel().getData().getLogo(), mIvLogo);
            mBtnNorBgColor = MainApp.getInstance().getOhterModel().getData().getButton_color();
            mBtnFocBgColor = MainApp.getInstance().getOhterModel().getData().getButton_color_focus();
            mTextNorColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color();
            mTextFocColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color_focus();
            String norColor =  MainApp.getInstance().getOhterModel().getData().getButton_font_color();
            String focColor =  MainApp.getInstance().getOhterModel().getData().getButton_font_color_focus();
            String selColor =  MainApp.getInstance().getOhterModel().getData().getButton_font_color_selected();
            RvAdapterChildNav.mBgNorColor = MainApp.getInstance().getOhterModel().getData().getButton_color();
            RvAdapterChildNav.mBgFocColor = MainApp.getInstance().getOhterModel().getData().getButton_color_focus();
            RvAdapterChildNav.mTextNorColor = norColor;
            RvAdapterChildNav.mTextFocColor = focColor;
            RvAdapterChildNav.mTextSelColor = selColor;
            RvAdapterMainNav.mNormalTextColor = norColor;
            RvAdapterMainNav.mFocusTextColor = focColor;
            RvAdapterMainNav.mSelTextColor = selColor;
            RvAdapterMainNav.mFocBgColor = MainApp.getInstance().getOhterModel().getData().getButton_color_focus();
//            mTvVipInfo.setText(MainApp.getInstance().getOhterModel().getData().getVip_text());
            setVipData();
            initMainBtnColor();
            if (!TextUtils.isEmpty(MainApp.getInstance().getOhterModel().getData().getHome_img())){
                GroceryStoreUtils.GlideImag(MainActivity3.this
                        , MainApp.getInstance().getOhterModel().getData().getHome_img(), mActivityMainBagImag);
            }
        }

    }


    FragmentManager fmManager = getFragmentManager();
    private FragmentTransaction transaction;

    private void changeFragment() {
//        transaction = fmManager.beginTransaction();
        /*先全部隐藏起来*/
//        for (int i = 0; i < mFragments.size(); i++) {
//            transaction.hide(mFragments.get(i));
//        }
//        transaction.commit();
        //再开一个事务，展示当前选中的Fragment
//        transaction = fmManager.beginTransaction();
//        transaction.show(mFragments.get(mMainNavSelectedPosition))
//                .commit();
//        transaction = fmManager.beginTransaction();
//        LogUtil.e("__changeFragment_SelectedPosition", mMainNavSelectedPosition + "");
//        LogUtil.e("__changeFragment_mFragments", mFragments.size() + "");
        if (mHomeType != null) {
            if (mHomeType.equals("2") || mHomeType.equals("3")) {//表示Home不是Fragment
                if (mMainNavSelectedPosition == 0) {
//                    LogUtil.e("__changeFragment_notHomeFm", "notHomeFm_mMainNavSelectedPosition == 0");
                    transaction = fmManager.beginTransaction();
                    for (int i = 0; i < mFragments.size(); i++) {
                        transaction.remove(mFragments.get(i));
                    }
                    transaction.commit();
                    mCurrentFm = null;
                    if (mLlHomeRootView != null) {
                        mLlHomeRootView.setVisibility(View.VISIBLE);
                    }
                    if (mVideoView != null) {
                        mVideoView.start();
                    }
                } else {
                    transaction = fmManager.beginTransaction();
                    for (int i = 0; i < mFragments.size(); i++) {
                        if (i == mMainNavSelectedPosition - 1) {//因为首页没有放Fragment，所以这里mMainNavSelectedPosition -1
                            mCurrentFm = mFragments.get(i);
                            transaction.replace(R.id.fl, mFragments.get(i));
                        }
                    }
                    transaction.commit();
                    if (mLlHomeRootView != null) {
                        mLlHomeRootView.setVisibility(View.GONE);
                    }
                    if (mVideoView != null) {
                        mVideoView.pause();
                    }
                }
            } else {
                LogUtil.e("__changeFragment_notHomeFm", "notHomeFm_mMainNavSelectedPosition != 0");
                transaction = fmManager.beginTransaction();
                for (int i = 0; i < mFragments.size(); i++) {
                    if (i == mMainNavSelectedPosition) {
                        mCurrentFm = mFragments.get(i);
                        transaction.replace(R.id.fl, mFragments.get(i));
                    }
                }
                transaction.commit();
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initView() {
        LogUtil.e("__home_initView_","initView");
        initMainView();
//        initChildView();
//        initPrimarySchoolView();
        mFlNotData = findViewById(R.id.fl_root_view_not_data);
    }

    /**
     * 初始化小学View
     */
    private void initPrimarySchoolView() {
//        GroceryStoreUtils.GlideGif(MainActivity3.this,R.drawable.arrow_down, mSivNextPrimarySchoolSubject);
    }

    /**
     * 初始化幼儿、专题、初中View
     */
    private void initChildView() {
//        GroceryStoreUtils.GlideGif(MainActivity3.this,R.drawable.arrow_down, mSivNextChild);
    }

    /**
     * 初始化首页模式二View
     */
    private void initHomeView2() {
        mLlHomeRootView = findViewById(R.id.ll_root_view_home_2);
        mHomeIv1 = mLlHomeRootView.findViewById(R.id.siv1_home_2);
        mHomeIv2 = mLlHomeRootView.findViewById(R.id.siv2_home_2);
        mHomeIv3 = mLlHomeRootView.findViewById(R.id.siv3_home_2);
        mHomeIv4 = mLlHomeRootView.findViewById(R.id.siv4_home_2);
        mHomeIv5 = mLlHomeRootView.findViewById(R.id.siv5_home_2);
        mHomeIv6 = mLlHomeRootView.findViewById(R.id.siv6_home_2);
        mHomeIv7 = mLlHomeRootView.findViewById(R.id.siv7_home_2);
        mHomeIv8 = mLlHomeRootView.findViewById(R.id.siv8_home_2);
        mFlVideoViewParent = mLlHomeRootView.findViewById(R.id.video_view_home_parent_2);
        mVideoView = mLlHomeRootView.findViewById(R.id.video_view_home_2);
        mMediaController = mLlHomeRootView.findViewById(R.id.media_controller_2);
        mIvListHome.add(mHomeIv1);
        mIvListHome.add(mHomeIv2);
        mIvListHome.add(mHomeIv3);
        mIvListHome.add(mHomeIv4);
        mIvListHome.add(mHomeIv5);
        mIvListHome.add(mHomeIv6);
        mIvListHome.add(mHomeIv7);
        mIvListHome.add(mHomeIv8);

        mHomeIv1.setOnClickListener(this);
        mHomeIv2.setOnClickListener(this);
        mHomeIv3.setOnClickListener(this);
        mHomeIv4.setOnClickListener(this);
        mHomeIv5.setOnClickListener(this);
        mHomeIv6.setOnClickListener(this);
        mHomeIv7.setOnClickListener(this);
        mHomeIv8.setOnClickListener(this);
        mFlVideoViewParent.setOnClickListener(this);
        initHomeFocus();
        mHomeIv2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mFocusType = FOCUS_ON_HOME_TOP;
                }
            }
        });
        mHomeIv3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });
        mHomeIv8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });
        mFlVideoViewParent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                LogUtil.e("__mVideoView_onFocusChange", hasFocus + "");
                if (hasFocus) {
                    mFocusType = FOCUS_ON_HOME_TOP;
                    mIsFocusOnMainNav = false;
//                    mFlVideoViewParent.setBackground(getResources().getDrawable(R.drawable.bg_border6));
//                    mController.hide();
//                    ViewCompat.animate(mFlVideoViewParent)
//                            .setDuration(200)
//                            .scaleX(1.02f)
//                            .scaleY(1.02f)
//                            .start();
//                    ViewCompat.animate(mVideoView)
//                            .setDuration(200)
//                            .scaleX(1.02f)
//                            .scaleY(1.02f)
//                            .start();
//                    mIsFocusOnVideoView = true;
                } else {
//                    ViewCompat.animate(mFlVideoViewParent)
//                            .setDuration(200)
//                            .scaleX(1f)
//                            .scaleY(1f)
//                            .start();
//                    ViewCompat.animate(mVideoView)
//                            .setDuration(200)
//                            .scaleX(1f)
//                            .scaleY(1f)
//                            .start();
//                    mFlVideoViewParent.setBackground(null);
//                    mController.hide();
//                    mIsFocusOnVideoView = false;
                }
            }
        });
        initVideoModeData();
    }

    /**
     * 初始化首页模式3View
     */
    private void initHomeView3() {

        mLlHomeRootView = findViewById(R.id.ll_root_view_home_3);
        mHomeIv1 = mLlHomeRootView.findViewById(R.id.siv1_home_3);
        mHomeIv2 = mLlHomeRootView.findViewById(R.id.siv2_home_3);
        mHomeIv3 = mLlHomeRootView.findViewById(R.id.siv3_home_3);
        mHomeIv4 = mLlHomeRootView.findViewById(R.id.siv4_home_3);
        mHomeIv5 = mLlHomeRootView.findViewById(R.id.siv5_home_3);
        mHomeIv6 = mLlHomeRootView.findViewById(R.id.siv6_home_3);
        mHomeIv7 = mLlHomeRootView.findViewById(R.id.siv7_home_3);
        mFlVideoViewParent = mLlHomeRootView.findViewById(R.id.video_view_home_parent_3);
        mVideoView = mLlHomeRootView.findViewById(R.id.video_view_home_3);
        mMediaController = mLlHomeRootView.findViewById(R.id.media_controller_3);
        mIvListHome.add(mHomeIv1);
        mIvListHome.add(mHomeIv2);
        mIvListHome.add(mHomeIv3);
        mIvListHome.add(mHomeIv4);
        mIvListHome.add(mHomeIv5);
        mIvListHome.add(mHomeIv6);
        mIvListHome.add(mHomeIv7);

        mHomeIv1.setOnClickListener(this);
        mHomeIv2.setOnClickListener(this);
        mHomeIv3.setOnClickListener(this);
        mHomeIv4.setOnClickListener(this);
        mHomeIv5.setOnClickListener(this);
        mHomeIv6.setOnClickListener(this);
        mHomeIv7.setOnClickListener(this);
        mFlVideoViewParent.setOnClickListener(this);

        initHomeFocus();
        mHomeIv2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv2_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_TOP;
                }
            }
        });
        mHomeIv3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv3_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });

        mFlVideoViewParent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                LogUtil.e("__focusOn_mVideoView_onFocusChange", hasFocus + "");
                if (hasFocus) {
                    mFocusType = FOCUS_ON_HOME_TOP;
                    mIsFocusOnMainNav = false;
//                    mFlVideoViewParent.setBackground(getResources().getDrawable(R.drawable.bg_border6));
//                    mController.hide();
//                    ViewCompat.animate(mFlVideoViewParent)
//                            .setDuration(200)
//                            .scaleX(1.02f)
//                            .scaleY(1.02f)
//                            .start();
//                    ViewCompat.animate(mVideoView)
//                            .setDuration(200)
//                            .scaleX(1.02f)
//                            .scaleY(1.02f)
//                            .start();
//                    mIsFocusOnVideoView = true;
                } else {
//                    ViewCompat.animate(mFlVideoViewParent)
//                            .setDuration(200)
//                            .scaleX(1f)
//                            .scaleY(1f)
//                            .start();
//                    ViewCompat.animate(mVideoView)
//                            .setDuration(200)
//                            .scaleX(1f)
//                            .scaleY(1f)
//                            .start();
//                    mFlVideoViewParent.setBackground(null);
//                    mController.hide();
//                    mIsFocusOnVideoView = false;
                }
            }
        });
        initVideoModeData();
    }

    private void initHomeFocus() {
        mHomeIv1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv1_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_TOP;
                }
            }
        });

        mHomeIv4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv4_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });
        mHomeIv5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv5_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });
        mHomeIv6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv6_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });
        mHomeIv7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    LogUtil.e("__focusOn_homeType", "mHomeIv7_" + mHomeType);
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                }
            }
        });
    }

    /**
     * 初始化主页View
     */
    private void initMainView() {
        if (true){
            initMainFocusListener();
            initNavigationView();
            mLlVipInfo.setOnClickListener(this);
            mLlHistory.setOnClickListener(this);
            mLMine.setOnClickListener(this);
            mLlSearch.setOnClickListener(this);
            return;
        }

    }

    /**
     * 初始化导航栏
     */
    private void initNavigationView() {

    }
    //限制3秒内vip按钮只能点击一次
    private boolean mIsVipClickable = true;
    @Override
    public void onClick(View view) {
        try {
            Intent intent = null;
            String type = "";
            switch (view.getId()) {
                case R.id.ll_vip_info:
                    if (!mIsVipClickable){
                        return;
                    }
                    if (UserManager.getToken().equals("")||UserManager.getToken()==null){
                        UserManager.isSignTokenIntentActivity(this,3);
                    }else {
                        intent=new Intent(MainActivity3.this, VipActivity3.class);
                        startActivity(intent);
                    }
                    mIsVipClickable = false;
                    delayHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mIsVipClickable = true;
                        }
                    }, 3000);
//                    LogUtil.e("__onClick", "ll_vip_advert");

                    break;
                case R.id.ll_history:
//                    LogUtil.e("__onClick", "ll_history");
                    //是否需要登录
                    if (UserManager.getToken().equals("")||UserManager.getToken()==null){
                        LogUtil.e("__________sssss","发送1");
                        UserManager.isSignTokenIntentActivity(this,3);
                    }else {
                        intent=new Intent(MainActivity3.this, MeActivity.class);
                        intent.putExtra("newPlayReocrd",3);
                        startActivity(intent);
                    }

                    break;
                case R.id.ll_search:
                    intent = new Intent(this, SearchActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ll_collect:
                    LogUtil.e("__onClick", "ll_collect");
                    //是否需要登录
                    if (UserManager.getToken().equals("")||UserManager.getToken()==null){
                            LogUtil.e("__________sssss","发送1");
                            UserManager.isSignTokenIntentActivity(this,1);
                        }else {
                            startActivity(new Intent(MainActivity3.this, MeActivity.class));
                    }

                    break;
                case R.id.video_view_home_parent_2:
                case R.id.video_view_home_parent_3:

                    isCourseForbidden(mHomeVideoBean.getCourse_id(), null);
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,2);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,1);
                            break;
                    }
                    break;
                case R.id.siv1_home_2:
                case R.id.siv1_home_3:
                    type = mHomeModelList.get(0).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(0).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {
                        //表示是课程
                        isCourseForbidden(mHomeModelList.get(0).getBind_url(), null);
                    }

                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,1);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,2);
                            break;
                    }
                    break;
                case R.id.siv2_home_2:
                case R.id.siv2_home_3:
                    type = mHomeModelList.get(1).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(1).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(1).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(1).getBind_url(), null);
                    }
//                    LogUtil.e("__onClick", "siv2__" + mHomeModelList.get(1).getCourse_id());
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(1).getCourse_id());
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,3);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,3);
                            break;
                    }
                    break;
                case R.id.siv3_home_2:
                case R.id.siv3_home_3:
                    type = mHomeModelList.get(2).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(2).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(2).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(2).getBind_url(), null);
                    }
//                    LogUtil.e("__onClick", "siv3__" + mHomeModelList.get(2).getCourse_id());
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(2).getCourse_id());
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,4);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,4);
                            break;
                    }
                    break;
                case R.id.siv4_home_2:
                case R.id.siv4_home_3:
//                    LogUtil.e("__onClick", "siv4__" + mHomeModelList.get(3).getCourse_id());
                    type = mHomeModelList.get(3).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(3).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(3).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(3).getBind_url(), null);
                    }
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(3).getCourse_id());
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,5);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,5);
                            break;
                    }
                    break;
                case R.id.siv5_home_2:
                case R.id.siv5_home_3:
//                    LogUtil.e("__onClick", "siv5__" + mHomeModelList.get(4).getCourse_id());
                    type = mHomeModelList.get(4).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(4).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(4).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(4).getBind_url(), null);
                    }
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(4).getCourse_id());
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,6);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,6);
                            break;
                    }
                    break;
                case R.id.siv6_home_2:
                case R.id.siv6_home_3:
//                    LogUtil.e("__onClick", "siv6__" + mHomeModelList.get(5).getCourse_id());
                    type = mHomeModelList.get(5).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(5).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(5).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(5).getBind_url(), null);
                    }
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,7);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,7);
                            break;
                    }
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(5).getCourse_id());
                    break;
                case R.id.siv7_home_2:
                case R.id.siv7_home_3:
//                    LogUtil.e("__onClick", "siv7__" + mHomeModelList.get(6).getCourse_id());
                    type = mHomeModelList.get(6).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(6).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(6).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(6).getBind_url(), null);
                    }
                    switch (mHomeType){
                        case "2":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,8);
                            break;
                        case "3":
                            mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,8);
                            break;
                    }
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(6).getCourse_id());
                    break;
                case R.id.siv8_home_2:
//                    LogUtil.e("__onClick", "siv8__" + mHomeModelList.get(7).getCourse_id());
                    type = mHomeModelList.get(7).getBind_type();
                    if (type.equals("3")) {//表示是url
                        intent = new Intent(this, WebViewActivity.class);
                        intent.putExtra("url", mHomeModelList.get(7).getBind_url());
                        startActivity(intent);
                    } else if (type.equals("1")) {//表示是课程
//                    intent.putExtra("courseId",mHomeModelList.get(7).getBind_url());
//                    intent = new Intent(this,CourseDetailActivity.class);
                        isCourseForbidden(mHomeModelList.get(7).getBind_url(), null);
                    }
                    mHttpHelper.recommendClick(mHomeType,RECOMMEND_CLICK_TYPE_POSITION,9);
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mHomeModelList.get(7).getCourse_id());
                    break;
            }
        } catch (Exception e) {
            LogUtil.e("__onClick_Exception", e.getMessage() + "__");
            new CourseForbiddenDialog().builder(MainActivity3.this, "").show();
        }
    }

    private boolean mIsPlaying = true;//是否正在播放
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsPlaying) {
                try{
                    if (mVideoView != null) {
                        LogUtil.e("__mRunnable_main_getCurrentPosition", mVideoView.getCurrentPosition() + "");
                        LogUtil.e("__mRunnable_main_getDuration", mVideoView.getDuration() + "");
                        LogUtil.e("__mRunnable_main_getDuration_diff1", mVideoView.getDuration() - mVideoView.getCurrentPosition() + "");
                        //视频播放完毕，重新播放
                        if (mVideoView.getDuration() - mVideoView.getCurrentPosition() < 2000 && mVideoView.getDuration() - mVideoView.getCurrentPosition() >= 0) {
                            Uri uri;
//                            LogUtil.e("__mRunnable_main_getDuration_diff2", String.valueOf(mVideoUrlBean != null) + "__");
                            if (mVideoUrlBean != null) {
                                String url = mVideoUrlBean.getRealUrl();
//                                String url = "rtsp://10.79.2.18/88888888/16/20180213/268555164/268555164.ts";
                                uri = Uri.parse(url);
                                LogUtil.e("__mRunnable_main_getDuration_url", url + "__");
//                                设置视频路径
                                mVideoView.setVideoURI(uri);
                                mVideoView.start();
                            }
//                            if (mHomeVideoBean != null){
//                                requestVideoUrl(mHomeVideoBean.getBind_url());
//                            }
                        }
                    }
                }catch (Exception e){
                    LogUtil.e("__mRunnable_main_Exception", e + "__");
                }finally {
                    mHandler.postDelayed(mRunnable, 5000);
                }

            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                LogUtil.e("__onKeyDown方向下", "方向下");
                LogUtil.e("__onKeyDown方向下", mFocusType + "");
                switch (mFocusType) {
                    case FOCUS_ON_TOP:
                        for (int i = 0; i < mRvNavMain.getChildCount(); i++) {
                            if (i == mMainNavCurrentSelectedPosition) {
                                LogUtil.e("____onKeyDown方向下 + i", i + "");
                                mRvNavMain.getChildAt(i).requestFocus();
                                return true;
                            }
                        }
                    case FOCUS_ON_MAIN_NAV:
                        LogUtil.e("__onKeyDown方向下_mFocusType", "方向下" + FOCUS_ON_MAIN_NAV);
                        if (getDownFirstView() != null) {
                            getDownFirstView().requestFocus();
                            mRvAdapterMainNav.notifyDataSetChanged();
                        }
                        return true;
                    case FOCUS_ON_HOME_BOTTOM:
                        break;
                    case FOCUS_ON_CHILD_RECOMMEND_BOTTOM:
                    case FOCUS_ON_CHILD_SUBJECT_TOP:
                    case FOCUS_ON_CHILD_SUBJECT_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP:
                        if (mCurrentFm != null) {
                            return mCurrentFm.onKeyDownFm(KeyEvent.KEYCODE_DPAD_DOWN);
                        }
                        break;
                    case FOCUS_ON_FM_NAV_TOP:
                    case FOCUS_ON_FM_NAV_BOTTOM:
                    case FOCUS_ON_HOME_CENTER:
                    case FOCUS_ON_CHILD_RECOMMEND_TOP:
                    case FOCUS_ON_CHILD_RECOMMEND_CENTER:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER:
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                LogUtil.e("__onKeyDown方向上", "方向上");
                LogUtil.e("____onKeyDown方向上 + NavSelected", mMainNavSelectedPosition + "");
                LogUtil.e("____onKeyDown方向上 + NavSelected_re", mMainNavCurrentSelectedPosition + "");
                LogUtil.e("____onKeyDown方向上 + mFocusType", mFocusType + "");
                switch (mFocusType) {
                    case FOCUS_ON_FM_NAV_TOP:
                    case FOCUS_ON_HOME_TOP:
                    case FOCUS_ON_CHILD_RECOMMEND_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP:
                        for (int i = 0; i < mRvNavMain.getChildCount(); i++) {
                            if (i == mMainNavCurrentSelectedPosition) {
                                LogUtil.e("__onKeyDown方向上 + i", i + "");
                                mRvNavMain.getChildAt(i).requestFocus();
                                if (mCurrentFm != null) {
                                    mCurrentFm.notificationNav();
                                }
                                return true;
                            }
                        }
                        break;
                    case FOCUS_ON_CHILD_SUBJECT_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP:
                        if (mCurrentFm != null) {
                            return mCurrentFm.onKeyDownFm(KeyEvent.KEYCODE_DPAD_UP);
                        }
                        break;
                    case FOCUS_ON_HOME_CENTER:
                    case FOCUS_ON_CHILD_RECOMMEND_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER:
                    case FOCUS_ON_CHILD_SUBJECT_BOTTOM:
                    case FOCUS_ON_CHILD_RECOMMEND_CENTER:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM:
                    case FOCUS_ON_FM_NAV_BOTTOM:
                    case FOCUS_ON_HOME_BOTTOM:
                        break;
                    case FOCUS_ON_MAIN_NAV:
                        mLlVipInfo.requestFocus();
                        return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                LogUtil.e("__onKeyDown方向左", "方向左");
                LogUtil.e("__onKeyDown方向左", "mFocusType：" + mFocusType);
                switch (mFocusType) {
                    case FOCUS_ON_CHILD_RECOMMEND_TOP:
                    case FOCUS_ON_CHILD_RECOMMEND_BOTTOM:
                    case FOCUS_ON_CHILD_SUBJECT_TOP:
                    case FOCUS_ON_CHILD_SUBJECT_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM:
                        if (mCurrentFm != null) {
                            return mCurrentFm.onKeyDownFm(KeyEvent.KEYCODE_DPAD_LEFT);
                        }
                        break;
                    case FOCUS_ON_HOME_BOTTOM:
                        break;
                    case FOCUS_ON_FM_NAV_TOP:
                    case FOCUS_ON_FM_NAV_BOTTOM:
                        return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                LogUtil.e("__onKeyDown方向右", "方向右");
                LogUtil.e("__onKeyDown方向右", "方向右" + mFocusType);
                switch (mFocusType) {
                    case FOCUS_ON_CHILD_RECOMMEND_TOP:
                    case FOCUS_ON_CHILD_RECOMMEND_BOTTOM:
                    case FOCUS_ON_CHILD_SUBJECT_TOP:
                    case FOCUS_ON_CHILD_SUBJECT_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP:
                    case FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM:
                    case FOCUS_ON_FM_NAV_TOP:
                    case FOCUS_ON_FM_NAV_BOTTOM:
                        if (mCurrentFm != null) {
                            return mCurrentFm.onKeyDownFm(KeyEvent.KEYCODE_DPAD_RIGHT);
                        }
                }
                break;
            case KeyEvent.KEYCODE_BACK:
//                LogUtil.e("__onKeyDown返回", "返回方向键");
//                if (mExitPageDialog != null && mExitPageModel != null) {
                if (mExitPaging != null) {
                    showExitDialog();
                } else {
                    requestExitPage(true);
                }
//                finish();
                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitDialog() {
        LogUtil.e("__showExitDialog", String.valueOf(mExitPageDialog == null) + "__");
        if (mExitPageDialog == null) {
            mExitPageDialog = new ExitPageDialog().builder(this, mExitPaging);
            mExitPageDialog.setOnClickListener(new ExitPageDialog.OnClickListener() {
                @Override
                public void onExitClick() {
                    finish();
                }

                @Override
                public void onSeeAgainClick() {

                }

                @Override
                public void onItemClick(SearchModelPy.DatabeanX.Databean pageDatabean) {
//                    LogUtil.e("__onItemClick_pageDatabean.getCourse_id()", pageDatabean.getId());
                    isCourseForbidden(String.valueOf(pageDatabean.getId()),null);
//                    Intent intent = new Intent(MainActivity3.this, CourseDetailActivity.class);
//                    intent.putExtra("courseId", String.valueOf(pageDatabean.getId()));
//                    startActivity(intent);
                }
            });
            LogUtil.e("__showExitDialog", "showExitDialog");
        }
        if (!mExitPageDialog.getDialog().isShowing() && mExitPageDialog != null){
            mExitPageDialog.show();
        }
    }

    private View getDownFirstView() {
        View view = null;
        if (mHomeType != null) {
            if (mHomeType.equals("2") || mHomeType.equals("3")) {
                if (mMainNavSelectedPosition == 0) {
                    if (mHomeType.equals("3")) {
                        view = mFlVideoViewParent;
                    } else {
                        view = mHomeIv1;
                    }

                } else {
                    for (int i = 0; i < mFragments.size(); i++) {
                        if (i == mMainNavSelectedPosition - 1) {//因为此时首页不是Fragment
                            view = mFragments.get(i).getFirstView();
                        }
                    }
                }
            } else {
                for (int i = 0; i < mFragments.size(); i++) {
                    if (i == mMainNavSelectedPosition) {
                        view = mFragments.get(i).getFirstView();
                    }
                }
            }
        }
        return view;
    }

    /**
     * 解析首页模块数据
     *
     * @param dataHome
     */
    private void setHomeModel(String dataHome) {
        try {
            LogUtil.e("__setHomeModel",dataHome);
            Gson gson = new Gson();
            mHomeModel = gson.fromJson(dataHome, HomeModel.class);
            if (mHomeModel == null) {
                LogUtil.e("__setHomeData", "mHomeModData == null");
                return;
            }
            LogUtil.e("__mHomeModel_getNav_id", mHomeModel.getNav_id());
            LogUtil.e("__mHomeModel_getNav_type", mHomeModel.getNav_type());
            mHomeType = mHomeModel.getIndex_module_id();
//            mHomeType = "2";
            LogUtil.e("__mHomeModel_getIndex_module_id", mHomeType);
//            mHomeFm = new MainFragmentType2();
//            mFragments.add(0,mHomeFm);
            switch (mHomeType) {
                case "1":
                    mHomeFm = new MainFragmentType1();
                    mHomeFm.setOnFocusChangeLiestener(mOnFmFocusChange);
                    mFragments.add(0, mHomeFm);
                    mHomeFm.setData(mHomeModel);
                    break;
                //这两个类型的Fragment中包含了VideoView，不添加Fragment，否则切换的时候会失去焦点，造成错乱
                case "2":
//                    mHomeFm = new MainFragmentType2();
//                    mFragments.add(0,mHomeFm);
                    initHomeView2();
                    break;
                case "3":
//                    mHomeFm = new MainFragmentType3();
//                    mFragments.add(0,mHomeFm);
                    initHomeView3();
                    break;
                case "4":
                    mHomeFm = new MainFragmentType4();
                    mHomeFm.setOnFocusChangeLiestener(mOnFmFocusChange);
                    mFragments.add(0, mHomeFm);
                    mHomeFm.setData(mHomeModel);
                    break;
                case "5":
                    mHomeFm = new MainFragmentType5();
                    mHomeFm.setOnFocusChangeLiestener(mOnFmFocusChange);
                    mFragments.add(0, mHomeFm);
                    mHomeFm.setData(mHomeModel);
                    break;
            }

            changeFragment();
            //确定首页使用哪个模块之后，就展示
//            mLlRootViewHome.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            LogUtil.e("__mHomeModel_Exception", e.getMessage());
        }
    }

    /**
     * 设置除首页之外其他模块数据
     *
     * @param response
     */
    private void setOtherModel(String response) {
        LogUtil.e("__setOtherModel", response + "");
        try {
            //一、拿到json数据，解析成JSONObject，取出data
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(response);
            String str = jsonObject.getString("data");
            //二、将data解析成JSONArray
            JSONArray array = new JSONArray(str);
            LogUtil.e("__decodeOtherModel_array.length()", array.length() + "");
            //从第一个开始拿，因为0是首页的数据
            for (int i = 1; i < array.length(); i++) {
                LogUtil.e("__decodeOtherModel_i", i + "");
                JSONObject jsonObjectTemp = new JSONObject(array.get(i).toString());
                //模块数据
                LogUtil.e("__setOtherModel_" + i, jsonObjectTemp.toString());
                //设置其他模块数据
                HomeOtherModel otherModel = new HomeOtherModel();
                String nav_id = jsonObjectTemp.getString("nav_id");
                String nav_type = jsonObjectTemp.getString("nav_type");
//                String class_name = jsonObjectTemp.getString("left_nav_name");
//                String class_name = jsonObjectTemp.getString("class_name");
                String dataStr = jsonObjectTemp.getString("data");
                otherModel.setNav_id(nav_id);
                otherModel.setNav_type(nav_type);
//                otherModel.setClass_name(class_name);
                JSONObject data = new JSONObject(dataStr);
                //获取、解析、设置推荐的数据
                String recommendation_dataStr = data.getString("recommendation_data");
                HomeOtherModel.Databean databean = new HomeOtherModel.Databean();
                List<HomeOtherModel.Databean.RecommendationDatabean> recommendationDatabeanList = (List<HomeOtherModel.Databean.RecommendationDatabean>) GsonUtil.parseJsonToList(recommendation_dataStr, new TypeToken<List<HomeOtherModel.Databean.RecommendationDatabean>>() {
                }.getType());
                databean.setRecommendation_data(recommendationDatabeanList);
                otherModel.setData(databean);
                //获取、解析、设置推荐课程的
                String course_dataStr = data.getString("course_list_data");
                LogUtil.e("__decodeOtherModel_course_dataStr_iiiiiiiiii_" + i, course_dataStr);
                if (course_dataStr.equals("[]")) {
                    //表示没有数据
                    LogUtil.e("__decodeOtherModel_course_dataStr", "equals_[]");
//                    HomeOtherModel.Databean.CourseListDatabean courseListDatabean = new HomeOtherModel.Databean.CourseListDatabean();
//                    courseListDatabean.setCourse_data(null);
//                    courseListDatabean.setCourse_nav(null);
                    databean.setCourse_list_data(null);
                } else {
                    LogUtil.e("__decodeOtherModel_course_dataStr", "!!!!!!equals_[]");
                    //表示有数据
//                    List<HomeOtherModel.Databean.CourseListDatabean.CourseNavbean> courseNavbeanList = (List<HomeOtherModel.Databean.CourseListDatabean.CourseNavbean>) GsonUtil.parseJsonToList(course_dataStr,new TypeToken<List<HomeOtherModel.Databean.RecommendationDatabean>>(){}.getType());
//                    List<HomeOtherModel.Databean.CourseListDatabean.CourseDatabean> courseDatabeanList = (List<HomeOtherModel.Databean.CourseListDatabean.CourseDatabean>) GsonUtil.parseJsonToList(course_dataStr,new TypeToken<List<HomeOtherModel.Databean.RecommendationDatabean>>(){}.getType());
                    HomeOtherModel.Databean.CourseListDatabean courseListDatabean = gson.fromJson(course_dataStr, HomeOtherModel.Databean.CourseListDatabean.class);
                    databean.setCourse_list_data(courseListDatabean);
                }
                mOtherModelList.add(otherModel);//这里将8个数据全部加进去了
            }
            LogUtil.e("__setOtherModel","mNavTitleReality",mNavTitleReality.size() + "");
            LogUtil.e("__setOtherModel","mOtherModelList",mOtherModelList.size() + "");
            LogUtil.e("__setOtherModel","mFragments",mFragments.size() + "");
            for (int j = 1; j < mNavTitleReality.size(); j++) {
                LogUtil.e("__setData","mFragments_j-1",j - 1 + "");
                LogUtil.e("__setData","mFragments_getIndex() - 1",mNavTitleReality.get(j).getIndex() - 1 + "");
                if (mHomeType.equals("2") || mHomeType.equals("3")) {
                    //2或者3会少一个fragment
                    mFragments.get(j - 1).setData(mOtherModelList.get(mNavTitleReality.get(j).getIndex() - 2));
                } else {
                    mFragments.get(j).setData(mOtherModelList.get(mNavTitleReality.get(j).getIndex() - 2));
                }
            }
            LogUtil.e("__decodeOtherModel_size", mOtherModelList.size() + "");
        } catch (Exception e) {
            LogUtil.e("__decodeOtherModel_Exception", e.getMessage());
            LogUtil.e("__decodeOtherModel_Exception_size", mOtherModelList.size() + "");
            LogUtil.e("__decodeOtherModel_Exception_Reality", mNavTitleReality.size() + "");
            LogUtil.e("__decodeOtherModel_Exception_mFragments", mFragments.size() + "");
        }
    }

    private BaseFragment.OnFocusChangeLiestener mOnFmFocusChange = new BaseFragment.OnFocusChangeLiestener() {
        @Override
        public void onRecommendFocusTop() {
            LogUtil.e("__onRecommendFocusTop", mNavTitleReality.get(mMainNavSelectedPosition).getIndex() + "");
            switch (mNavTitleReality.get(mMainNavSelectedPosition).getIndex()) {
                case 0:
                    mFocusType = FOCUS_ON_HOME_TOP;
                    break;
                case 1:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 2:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 3:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP;
                    break;
                case 4:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP;
                    break;
                case 5:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP;
                    break;
                case 6:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP;
                    break;
                case 7:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP;
                    break;
                case 8:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_TOP;
                    break;
                case 9:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 10:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 11:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 12:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 13:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
                case 14:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_TOP;
                    break;
            }
//            }

        }

        @Override
        public void onRecommendFocusCenter() {
            switch (mNavTitleReality.get(mMainNavSelectedPosition).getIndex()) {
                case 0:
                    mFocusType = FOCUS_ON_HOME_CENTER;
                    break;
                case 1:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 2:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 3:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER;
                    break;
                case 4:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER;
                    break;
                case 5:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER;
                    break;
                case 6:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER;
                    break;
                case 7:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER;
                    break;
                case 8:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_CENTER;
                    break;
                case 9:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 10:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 11:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 12:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 13:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
                case 14:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_CENTER;
                    break;
            }

        }

        @Override
        public void onRecommendFocusBottom() {
            switch (mNavTitleReality.get(mMainNavCurrentSelectedPosition).getIndex()) {
                case 0:
                    mFocusType = FOCUS_ON_HOME_BOTTOM;
                    break;
                case 1:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 2:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 3:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM;
                    break;
                case 4:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM;
                    break;
                case 5:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM;
                    break;
                case 6:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM;
                    break;
                case 7:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM;
                    break;
                case 8:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_RECOMMEND_BOTTOM;
                    break;
                case 9:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 10:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 11:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 12:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 13:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
                case 14:
                    mFocusType = FOCUS_ON_CHILD_RECOMMEND_BOTTOM;
                    break;
            }
//            }
        }

        @Override
        public void onSubjectFocusTop() {
//            if (mHomeType.equals("2") || mHomeType.equals("3") && mMainNavSelectedPosition == 0){
//
//            }else {
            switch (mNavTitleReality.get(mMainNavCurrentSelectedPosition).getIndex()) {
                case 0:
//                        mFocusType = FOCUS_ON_HOME_BOTTOM;
                    break;
                case 1:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 2:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 3:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP;
                    break;
                case 4:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP;
                    break;
                case 5:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP;
                    break;
                case 6:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP;
                    break;
                case 7:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP;
                    break;
                case 8:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_TOP;
                    break;
                case 9:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 10:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 11:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 12:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 13:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
                case 14:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_TOP;
                    break;
            }
        }

        @Override
        public void onSubjectFocusBottom() {
            switch (mNavTitleReality.get(mMainNavCurrentSelectedPosition).getIndex()) {
                case 0:
//                        mFocusType = FOCUS_ON_HOME_BOTTOM;
                    break;
                case 1:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 2:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 3:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM;
                    break;
                case 4:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM;
                    break;
                case 5:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM;
                    break;
                case 6:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM;
                    break;
                case 7:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM;
                    break;
                case 8:
                    mFocusType = FOCUS_ON_PRIMARY_SCHOOL_SUBJECT_BOTTOM;
                    break;
                case 9:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 10:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 11:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 12:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 13:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
                case 14:
                    mFocusType = FOCUS_ON_CHILD_SUBJECT_BOTTOM;
                    break;
            }
        }
//        }

        @Override
        public void onNavTop() {
            mFocusType = FOCUS_ON_FM_NAV_TOP;
        }

        @Override
        public void onNavBottom() {
            mFocusType = FOCUS_ON_FM_NAV_BOTTOM;
        }
    };
    private HomeModel.Databean.RecommendationDatabean mHomeVideoBean;//首页推荐位视频bean

    /**
     * 当首页使用2、3模板时，有视频，没有Banner图
     */
    private void initVideoModeData() {
        List<HomeModel.Databean.RecommendationDatabean> list = mHomeModel.getData().getRecommendation_data();
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            HomeModel.Databean.RecommendationDatabean databean = list.get(i);
            LogUtil.e("__initVideoModeData_getBind_type_" + i, databean.getBind_type());
            if (databean.getBind_type() == null || databean.getBind_type().equals("2")) {//这里是因为使用模板1的数据模拟，所以databean.getBind_type() == null，后续可以删除
                //表示是视频
                mHomeVideoBean = databean;
                LogUtil.e("__initVideoModeData_VideoBean_courseId" + i, databean.getCourse_id() + "__");
                LogUtil.e("__initVideoModeData_VideoBean_getBind_url" + i, databean.getBind_url() + "__");
                //获取视频播放地址
//                requestVideoUrl(mHomeVideoBean.getBind_url());
                requestVideoUrl(mHomeVideoBean);
            } else {
                //不是视频的则此List里面
                mHomeModelList.add(databean);
                position++;
            }
        }
        LogUtil.e("__initVideoModeData_mHomeModelList.size()", mHomeModelList.size() + "");
        //设置推荐位图片
        for (int i = 0; i < mHomeModelList.size(); i++) {
            HomeModel.Databean.RecommendationDatabean databean = mHomeModelList.get(i);
//            GroceryStoreUtils.GlideImag(MainActivity3.this, databean.getRecommendation_img(), mIvListHome.get(i));
            GroceryStoreUtils.GlideImagHasPlaceHolder(MainActivity3.this, databean.getRecommendation_img(), mIvListHome.get(i),R.drawable.placeholder_course);
        }
    }

    private String mSessionid;
    private VideoUrlBean mVideoUrlBean;
    public VideoUrlModel mVideoUrlModel;
    private void requestVideoUrl(HomeModel.Databean.RecommendationDatabean homeVideoBean) {

            int isFree;

            mHttpHelper.getVideoUrl(this,Integer.valueOf(homeVideoBean.getBind_url()),0,CourseDetailActivity.REQUEST_TYPE_PLAY_CLICK);
        }

    private MediaController mController;
    private MediaPlayer mMediaPlayer;

    private void initVideoView() {
//        mVideoView.stopPlayback();
//        mVideoView.seekTo(0);
        try {
            if (mVideoUrlModel == null) {
                return;
            }
            Uri uri;
            if (mVideoUrlModel != null) {
                String url = mVideoUrlModel.getData().getUrl();
//                String url = "rtsp://10.79.2.18/88888888/16/20180213/268555164/268555164.ts";
//                uri = Uri.parse(mVideoUrlBean.getRealUrl());
                uri = Uri.parse(url);
//                uri = Uri.parse(mTestUrl);
//                uri = Uri.parse(mVideoUrl);
                LogUtil.e("__initVideoView", url + "__");
//          uri = Uri.parse(mTestUrl);
            } else {
                uri = Uri.parse("");
            }
            mController = new MediaController(this);
            //设置视频控制器
//          mVideoView.setMediaController(mMediaController);
            //播放完成回调
            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    LogUtil.e("__onCompletion", "onCompletion");
                    //视频播放完毕，重新播放
                        Uri uri;
//                            LogUtil.e("__mRunnable_main_getDuration_diff2", String.valueOf(mVideoUrlBean != null) + "__");
                        if (mVideoUrlModel != null) {
                            String url = mVideoUrlModel.getData().getUrl();
//                                String url = "rtsp://10.79.2.18/88888888/16/20180213/268555164/268555164.ts";
                            uri = Uri.parse(url);
//                            uri = Uri.parse(mTestUrl);
                            LogUtil.e("__mVideoView_onCompletion", url + "__");
//                                设置视频路径
                            mVideoView.setVideoURI(uri);
                            mVideoView.start();
                        }
//                        if (mHomeVideoBean != null){
//                                requestVideoUrl(mHomeVideoBean.getBind_url());
//                            }
                    }
            });
            mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    ToastUtil.showToast("播放错误");
                    return false;
                }
            });
//            mVideoView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText( VideoActivity.this, "onTouch", Toast.LENGTH_SHORT).show();
//                Log.e("_onInfo_i","onTouch");
//                LogUtil.e("__mVideoView_onTouch",motionEvent.getAction() + "");
//                    return false;
//                }
//            });
//        mVideoView.requestFocus();
//        mHandler.post(mRunnable);
            //设置视频路径
            mVideoView.setVideoURI(uri);
            //开始播放视频
            mVideoView.start();
        } catch (Exception e) {

        }
    }
    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    /**
     * 请求退出页面数据
     */
    public void requestExitPage(final boolean isShowDia) {
            if(true){
                //需求是30个推荐数据
                mHttpHelper.searchCourse(this,"", HttpHelper.PY_HTTP_HOT_SEARCH,30);
                return;
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        recodeVisit(IHttp.VISIT_INDEX);
//        recodeHomeVisit();
        mHttpHelper.getUserInfo(this);
        if (mVideoView != null) {
            if (mRvAdapterMainNav != null && mMainNavSelectedPosition == 0){
                mVideoView.start();
            }
        }
        if (UserManager.getToken()==null||UserManager.getToken().equals("")){
            OhterModel1 ohterModel1 = MainApp.getInstance().getOhterModel();
            mTvVipInfo.setText(ohterModel1.getData().getVip_text());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("__home_onDestroy_","onDestroy");
        mIsPlaying = false;
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }
    private final String paramString = "";
    private final String UPGRADE_BASE_URL = "http://appstore.jxtvnet.com/";
    private void showUpgradeDia(int newVerCode) {
        CourseForbiddenDialog dialog = new CourseForbiddenDialog().builder(MainActivity3.this,"发现新版本" )
                .setConfirmText("马上升级")
                .setClickListener(new CourseForbiddenDialog.OnBtnClickListener() {
                    @Override
                    public void onBtnClick() {
//                        toAppStore();
                    }
                });
//        dialog.getDialog().setCanceledOnTouchOutside(false);//禁止外部取消
        //进制返回键
        dialog.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_BACK:
                        return true;
                        default:break;
                }
                return false;
            }
        });
        dialog.show();
    }

    /**
     * 记录用户访问,没有数据返回，无需做处理
     */
    public void recodeHomeVisit(){
        String userId = SysUtil.getSystemProperties(this,SysUtil.SYSKEY_JX_SMART_CARD);
        mHttpHelper.recodeHomeVisit(this,userId);
//        LogUtil.e("__recodeHomeVisit_userId",userId + "__");
    }

    /**
     * 网络请求成功
     * @param reqType 区分调用的是哪一个接口
     * @param msg     请求成功后返回的数据
     */
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case HttpHelper.PY_HTTP_START:
                getOtherSuccess(msg);
                break;
            case HttpHelper.PY_HTTP_GET_MAIN_NAV:
                LogUtil.e("__onHttpSuccess","获取导航栏数据成功");
                Message messageNav = new Message();
                messageNav.obj = msg.obj.toString();
                getNavSuccess(messageNav);
                break;
            case HttpHelper.PY_HTTP_GET_MAIN_MODULE:
                Message messageModule = new Message();
                messageModule.obj = msg.obj.toString();
                LogUtil.e("__onHttpSuccess","获取各个模块数据成功");
                getMainModule(messageModule);
                break;
            case PY_HTTP_GET_VIDEO_URL:
                mVideoUrlModel = (VideoUrlModel) msg.obj;

                 getVideoDataSuccess(msg);
//                playVideo();
                break;
            case PY_HTTP_GET_USER_INFO:
                UserInfoModel userInfoModel = (UserInfoModel) msg.obj;
                UserManager.setUserInfo(userInfoModel);
                setVipData();
                break;
            case HttpHelper.PY_HTTP_HOT_SEARCH:
                mExitPageModel = (SearchModelPy) msg.obj;
//                mHotModel = (SearchModel) msg.obj;
                if (mExitPageModel == null || mExitPageModel.getData() == null || mExitPageModel.getData().getData() == null
                        || mExitPageModel.getData().getData().size() == 0){
                    finish();
                    return;
                }
                arrangeExit();
                showExitDialog();
//                setHotData(false,0);
                break;
        }
    }
    /**
     * 整理退出界面的数据，进行分页
     */
    private void arrangeExit() {
        try {
            mExitPaging = new ArrayList<>();
            List<SearchModelPy.DatabeanX.Databean> courseList = mExitPageModel.getData().getData();
            List<SearchModelPy.DatabeanX.Databean> list = new ArrayList<>();
            for (int i = 0; i < courseList.size(); i++) {
                list.add(courseList.get(i));
                if (list.size() == 4){//每页4个课程，够4个就放进去
                    mExitPaging.add(list);
                    list = new ArrayList<>();
                }else if (i == courseList.size() - 1){//所有数据放完了，也放进去
                    mExitPaging.add(list);
                }
            }
        }catch (Exception e){
            finish();
        }
//        mHotModelPagingList
    }
    /**
     * 设置vip数据
     */
    private void setVipData() {
        UserInfoModel userInfoModel = UserManager.getUserInfo();
        if (userInfoModel != null){
            //        会员：0非会员，1会员
            if (userInfoModel.getData().getIsVip() == 1){
//            其中的vip时间是这个格式2020-01-11 14:41:49，需求是只到日期
                mTvVipInfo.setText("您已是VIP，" + userInfoModel.getData().getVip_time() + "到期");
            }else {
                OhterModel1 ohterModel1 = MainApp.getInstance().getOhterModel();
                if (ohterModel1 != null){
                    mTvVipInfo.setText(ohterModel1.getData().getVip_text());
                }
            }
        }else {
            OhterModel1 ohterModel1 = MainApp.getInstance().getOhterModel();
            if (ohterModel1 != null){
                mTvVipInfo.setText(ohterModel1.getData().getVip_text());
            }
        }
    }

    /**
     * 成功获取到首页各个模块数据
     * @param msg
     */
    private void getMainModule(Message msg) {
        String data = (String) msg.obj;
        try {
            //一、拿到json数据，解析成JSONObject，取出data
            Gson gson = new Gson();
              JSONObject jsonObject = new JSONObject(data);
//            JSONObject jsonObject = CommonUtils.decodeResult(data);
//            LogUtil.e("__requestModeData_onResponse_deCodeResult",  jsonObject.toString() + "__");
            String str = jsonObject.getString("data");
//            LogUtil.e("__requestModeData_onResponse_deCodeResult_data", str + "__");
            //二、将data解析成JSONArray
            JSONArray array = new JSONArray(str);
//            LogUtil.e("__requestModeData_onResponse_str_length", array.length() + "");
//            LogUtil.e("__requestModeData_onResponse_array_0", array.get(0).toString());
//            LogUtil.e("__requestModeData_onResponse_str_1", array.get(1).toString());
            //设置首页数据
            setHomeModel(array.get(0).toString());
            //设置其他模块数据
//                            setOtherModel(response);
            setOtherModel(jsonObject.toString());
            //在首页模块获取加载完毕之后，设置焦点给主导航栏第一项
            if (mVideoView != null) {
                mFlVideoViewParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LogUtil.e("__first_requestFocus_onGlobalLayout", "mRvNavMain");
                        if (mRvNavMain.getChildCount() > 0) {
                            mRvNavMain.getChildAt(0).requestFocus();
                        }
                        mRvNavMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
                mVideoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LogUtil.e("__first_requestFocus_onGlobalLayout", "mRvNavMain");
                        if (mRvNavMain.getChildCount() > 0) {
                            mRvNavMain.getChildAt(0).requestFocus();
                        }
                        mRvNavMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } catch (Exception e) {
//            LogUtil.e("__requestModeData_onResponse", "导航栏_模块_数据解析异常_response_msg:" + e.getMessage());
        }
    }

    /**
     * 获取视频数据成功
     * @param msg
     */
    private void getVideoDataSuccess(Message msg) {
            mVideoUrlModel = (VideoUrlModel) msg.obj;
            initVideoView();
    }

    /**
     * 获取导航栏数据成功
     * @param msg
     */
    private void getNavSuccess(Message msg){
        try {
            String data = (String) msg.obj;
            LogUtil.e("__getNavSuccess_onResponse", data + "");
            Gson gson = new Gson();
            mMainNavData = gson.fromJson(data, MainNavModel3.class);
            LogUtil.e("__getNavSuccess_mMainNavData.getCode()", mMainNavData.getError() + "");
            //成功
//            if (mMainNavData.getCode() == 10000) {
            if (mMainNavData.getError() == 10000) {
                List<MainNavModel3.Databean> list = mMainNavData.getData();
//                            遍历，根据isShow确定该导航栏是否展示，1表示展示这个item,0表示不展示
                for (int i = 0; i < mMainNavData.getData().size(); i++) {
                    if (list.get(i).getIs_show() == 1) {
                        MainNavModel mainNavModel = mNavTitleAll.get(i);
                        mainNavModel.setNormalIconUrl(list.get(i).getIcon());
                        mainNavModel.setSelectedIconUrl(list.get(i).getSelected_icon());
                        mainNavModel.setFocusIconUrl(list.get(i).getFocus_icon());
                        mNavTitleReality.add(mainNavModel);
                        BaseFragment fm;
                        switch (i) {
                            case 1:
                            case 2:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                                fm = new ChildFragment();
                                fm.setOnFocusChangeLiestener(mOnFmFocusChange);
                                mFragments.add(fm);
                                break;
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                fm = new PrimarySchoolFragment();
                                fm.setOnFocusChangeLiestener(mOnFmFocusChange);
                                mFragments.add(fm);
                                break;
                            default:
                                break;
                        }
                    } else {
                    }
                }
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity3.this);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRvAdapterMainNav = new RvAdapterMainNav(mNavTitleReality, MainActivity3.this);
                mRvNavMain.setLayoutManager(manager);
                mRvNavMain.setAdapter(mRvAdapterMainNav);
                mRvNavMain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mRvNavMain.getChildAt(0).requestFocus();
                        mRvNavMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
                mRvAdapterMainNav.setOnItemChangeListener(new RvAdapterMainNav.OnItemChangeListener() {
                    @Override
                    public void onItemFocusChange(boolean hasfocus, int position) {
                        if (hasfocus) {
                            mFocusType = FOCUS_ON_MAIN_NAV;
                            mIsUpToMainNav = false;
                            try {
                                mRvNavMain.smoothToCenter(position);
                                if (mCurrentFm != null) {
                                    mCurrentFm.notificationNav();
                                }
                            } catch (Exception e) {
                            }
                            mMainNavSelectedPosition = position;
                            changeFragment();
                            mRvNavMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                    super.onScrolled(recyclerView, dx, dy);
                                    //监听滑动完毕之后，获取选中位置
                                    for (int i = 0; i < mRvNavMain.getChildCount(); i++) {
                                        if (mRvNavMain.getChildAt(i).hasFocus()) {
                                            mMainNavCurrentSelectedPosition = i;
                                        }
                                    }
                                }
                            });
                            //当处在开头或者结尾的时候，左右移动并不会使Rv移动，此时就需要在这里得到最后获取焦点的实际位置
                            for (int i = 0; i < mRvNavMain.getChildCount(); i++) {
                                if (mRvNavMain.getChildAt(i).hasFocus()) {
                                    mMainNavCurrentSelectedPosition = i;
                                }
                            }
                        } else {

                        }
                    }
                });
                //获取各个模块数据
//                mHttpHelper.getMainModuleData(this);
                mHttpHelper.getMainModule(this);
            } else {
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        switch (reqType){
            case HTTP_GET_VIDEO_DATA:
                ToastUtil.showToast(String.valueOf(error));
                break;
            case HttpHelper.PY_HTTP_HOT_SEARCH:
                finish();
                break;
            case PY_HTTP_GET_VIDEO_URL:
                LogUtil.e("__PY_HTTP_GET_VIDEO_URLsssssss",error);
        }
    }

    @Override
    public void onTokenError(int reqType, String error, String errorCode) {
        toLogin();
    }

    /**
     * 成功获取到“其他”数据
     * @param msg
     */
    private void getOtherSuccess(Message msg){
//        String data = (String) msg.obj;
//        int code = msg.arg1;//请求结果的code
        try {
            //成功
//            if (code == 10000) {
//                Gson gson = new Gson();
//                OhterModel1 ohterModel = gson.fromJson(data, OhterModel1.class);
                OhterModel1 ohterModel = (OhterModel1) msg.obj;
                MainApp.getInstance().setOhterModel(ohterModel);
                String logoUrl = ohterModel.getData().getLogo();
//                GroceryStoreUtils.GlideImag(MainActivity3.this, logoUrl, mIvLogo);

            mBtnNorBgColor = ohterModel.getData().getButton_color();
            mBtnFocBgColor = ohterModel.getData().getButton_color_focus();
            mTextNorColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color();
            mTextFocColor = MainApp.getInstance().getOhterModel().getData().getButton_font_color_focus();

            String norColor =  ohterModel.getData().getButton_font_color();
            String focColor =  ohterModel.getData().getButton_font_color_focus();
            String selColor =  ohterModel.getData().getButton_font_color_selected();
            RvAdapterChildNav.mBgNorColor = ohterModel.getData().getButton_color();
            RvAdapterChildNav.mBgFocColor = ohterModel.getData().getButton_color_focus();
            RvAdapterChildNav.mTextNorColor = norColor;
            RvAdapterChildNav.mTextFocColor = focColor;
            RvAdapterChildNav.mTextSelColor = selColor;
            RvAdapterMainNav.mNormalTextColor = norColor;
            RvAdapterMainNav.mFocusTextColor = focColor;
            RvAdapterMainNav.mSelTextColor = selColor;
            RvAdapterMainNav.mFocBgColor = ohterModel.getData().getButton_color_focus();
            mTvVipInfo.setText(MainApp.getInstance().getOhterModel().getData().getVip_text());
            initMainBtnColor();
            if (!TextUtils.isEmpty(MainApp.getInstance().getOhterModel().getData().getHome_img())){
                GroceryStoreUtils.GlideImag(MainActivity3.this,
                        MainApp.getInstance().getOhterModel().getData().getHome_img(), mActivityMainBagImag);
            }

        } catch (Exception e) {
        }
    }

    private void initMainBtnColor(){
        try{
            mLlVipInfo.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
            mLlHistory.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
            mLMine.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
            mLlSearch.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
        }catch (Exception e){
            mLlVipInfo.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
            mLlHistory.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
            mLMine.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
            mLlSearch.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
//            mLlVipInfo.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
        }
        try{
//            mTvVipInfo.setTextColor(Color.parseColor(mTextNorColor));
            mTvHistory.setTextColor(Color.parseColor(mTextNorColor));
            mTvMine.setTextColor(Color.parseColor(mTextNorColor));
            mTvSearch.setTextColor(Color.parseColor(mTextNorColor));
        }catch (Exception e){
//            mLlHistory.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
        }

    }

    private void initMainFocusListener(){
        mLlVipInfo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
//                LogUtil.e("__onFocusChange", "mLlVipInfo：" + b);
                if (b) {
                    mFocusType = FOCUS_ON_TOP;
//                    mIsUpToMainNav = false;
//                    mIsDownToNav = true;
                    if (mRvAdapterMainNav != null) {
                        try {
                            mRvAdapterMainNav.notifyDataSetChanged();
                        } catch (Exception e) {
//                            LogUtil.e("__Exception", e.getMessage());
                        }
                    }
//                    float scale = 1.02f;
                    ScaleUtil.scale(mLlVipInfo, 1.04f,1.12f);
                    mLlVipInfo.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_gold));
                    mIvVipInfo.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_selected_main));
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.black_gold));
                    try{
//                        mLlVipInfo.setBackgroundColor(Color.parseColor(mBtnFocBgColor));
                    }catch (Exception e){

                    }
                        mTvVipInfo.setTextColor(getResources().getColor(R.color.black_gold));
                } else {
                    float scale = 1f;
                    ScaleUtil.scale(mLlVipInfo, scale);
                    mLlVipInfo.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_normal));
                    mIvVipInfo.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_normal_main));
                    mTvVipInfo.setTextColor(getResources().getColor(R.color.color_gold));
                    try{
//                        mLlVipInfo.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
                    }catch (Exception e){
//                        mLlVipInfo.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
                    }
                }
            }
        });
        mLlHistory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
//                LogUtil.e("__onFocusChange", "mLlHistory：" + b);
                if (b) {
                    mFocusType = FOCUS_ON_TOP;
                    if (mRvAdapterMainNav != null) {
                        //通知主导航栏更新状态
                        mRvAdapterMainNav.notifyDataSetChanged();
                    }
//                    mIsUpToMainNav = false;
//                    mIsDownToNav = true;
                    mLlHistory.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_blue));
                    mIvHistory.setImageDrawable(getResources().getDrawable(R.drawable.history_icon_selected_main));
                    float scale = 1.12f;
                    ScaleUtil.scale(mLlHistory, scale);
                    try{
                        mLlHistory.setBackgroundColor(Color.parseColor(mBtnFocBgColor));
                    }catch (Exception e){

                    }
                    try{
                        mTvHistory.setTextColor(Color.parseColor(mTextFocColor));
                    }catch (Exception e){

                    }
//                    ScaleUtil.scale(mTvHistory,scale);
//                    ScaleUtil.scale(mIvHistory,scale);
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.black_gold));
                } else {
                    float scale = 1f;
                    ScaleUtil.scale(mLlHistory, scale);
//                    ScaleUtil.scale(mTvHistory,scale);
//                    ScaleUtil.scale(mIvHistory,scale);
//                    mLlHistory.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_normal));
                    mIvHistory.setImageDrawable(getResources().getDrawable(R.drawable.history_icon_normal_main));
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.color_gold));
                    try{
                        mLlHistory.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
                    }catch (Exception e){
                        mLlHistory.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
                    }
                    try{
                        mTvHistory.setTextColor(Color.parseColor(mTextNorColor));
                    }catch (Exception e){

                    }
                }
            }
        });
        mLMine.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mFocusType = FOCUS_ON_TOP;
                    if (mRvAdapterMainNav != null) {
                        //通知主导航栏更新状态
                        mRvAdapterMainNav.notifyDataSetChanged();
                    }
//                    mIsUpToMainNav = false;
//                    mIsDownToNav = true;
                    mLMine.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_blue));
                    mIvMine.setImageDrawable(getResources().getDrawable(R.drawable.ic_mine_selected));
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.black_gold));
                    float scale = 1.12f;
                    ScaleUtil.scale(mLMine, scale);
                    try{
                        mLMine.setBackgroundColor(Color.parseColor(mBtnFocBgColor));
                    }catch (Exception e){

                    }
                    try{
                        mTvMine.setTextColor(Color.parseColor(mTextFocColor));
                    }catch (Exception e){

                    }
//                    ScaleUtil.scale(mTvMine,scale);
//                    ScaleUtil.scale(mIvMine,scale);
                } else {
                    float scale = 1f;
                    ScaleUtil.scale(mLMine, scale);
//                    ScaleUtil.scale(mTvMine,scale);
//                    ScaleUtil.scale(mIvMine,scale);
//                    mLMine.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_normal));
                    mIvMine.setImageDrawable(getResources().getDrawable(R.drawable.ic_mine_normal));
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.color_gold));
                    try{
                        mLMine.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
                    }catch (Exception e){
//                        mLMine.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
                    }
                    try{
                        mTvMine.setTextColor(Color.parseColor(mTextNorColor));
                    }catch (Exception e){

                    }
                }
            }
        });

        mLlSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mFocusType = FOCUS_ON_TOP;
                    if (mRvAdapterMainNav != null) {
                        //通知主导航栏更新状态
                        mRvAdapterMainNav.notifyDataSetChanged();
                    }
//                    mIsUpToMainNav = false;
//                    mIsDownToNav = true;
                    mLlSearch.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_blue));
                    mIvSearch.setImageDrawable(getResources().getDrawable(R.drawable.search_icon_selected_main));
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.black_gold));
                    float scale = 1.12f;
                    ScaleUtil.scale(mLlSearch, scale);
                    try{
                        mLlSearch.setBackgroundColor(Color.parseColor(mBtnFocBgColor));
                    }catch (Exception e){

                    }
                    try{
                        mTvSearch.setTextColor(Color.parseColor(mTextFocColor));
                    }catch (Exception e){

                    }
//                    ScaleUtil.scale(mTvSearch,scale);
//                    ScaleUtil.scale(mIvSearch,scale);
                } else {
                    float scale = 1f;
                    ScaleUtil.scale(mLlSearch, scale);
//                    ScaleUtil.scale(mTvSearch,scale);
//                    ScaleUtil.scale(mIvSearch,scale);
//                    mLlSearch.setBackground(getResources().getDrawable(R.drawable.bg_tv_main_normal));
                    mIvSearch.setImageDrawable(getResources().getDrawable(R.drawable.search_icon_normal_main));
                    try{
                        mLlSearch.setBackgroundColor(Color.parseColor(mBtnNorBgColor));
                    }catch (Exception e){
                        mLlSearch.setBackgroundColor(getResources().getColor(R.color.color_top_tv_main_normal));
                    }
                    try{
                        mTvSearch.setTextColor(Color.parseColor(mTextNorColor));
                    }catch (Exception e){

                    }
//                    mTvVipInfo.setTextColor(getResources().getColor(R.color.color_gold));
                }
            }
        });
    }

}
