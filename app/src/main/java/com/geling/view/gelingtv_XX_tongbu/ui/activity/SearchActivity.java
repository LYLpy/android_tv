package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.BaseHelper;
import com.geling.view.gelingtv_XX_tongbu.model.HttpHelper;
import com.geling.view.gelingtv_XX_tongbu.model.bean.RvAdapterLetter;
import com.geling.view.gelingtv_XX_tongbu.model.bean.SearchModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SearchModelPy;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterSearch;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterSearchPy;
import com.geling.view.gelingtv_XX_tongbu.ui.view.MyTvRecyclerView;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/7------更新------
 */

public class SearchActivity extends BaseActivity implements BaseHelper.IHttpCallback {

    @BindView(R.id.edit)
    EditText mEdit;
    @BindView(R.id.rv_letter)
    MyTvRecyclerView mRvLetter;
    @BindView(R.id.ll_left)
    LinearLayout mLlLeft;
    @BindView(R.id.ll_right)
    LinearLayout mLlRight;
    @BindView(R.id.tv_clear)
    TextView mTvClear;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.tv_search_type)
    TextView mTvSearchType;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_page)
    TextView mTvPage;
    @BindView(R.id.ll_not_data)
    LinearLayout mLlNotData;
    @BindView(R.id.rv_content)
    MyTvRecyclerView mRvContent;
    @BindView(R.id.iv_next_page)
    ImageView mIvNextPage;
    private List<String> mData = new ArrayList<>();
    private RvAdapterLetter mAdapterLetter;
    private String mEditStr = "";
    private SearchModel mHotModel;
    private SearchModel mSearchModel;
    private int mDataType = 1;//1表示是热门数据，2表示是搜索数据
    private RvAdapterSearch mRvAdapterSearch;
    private int mCurrentPageHot;
    private int mCurrentPageSearch;
    private List<SearchModel.Databean.PageDatabean> mDataCurrent;
    private int mAdapterFocusPosition;
    private boolean mIsNotData = true;
    //焦点所在位置
    private int mFocusPosition;
    private static final int FOCUS_ON_CLEAR = 0;//清空
    private static final int FOCUS_ON_DELETE = 1;//删除
    private static final int FOCUS_ON_LETTER = 2;//字母输入框
    private static final int FOCUS_ON_BACK = 3;//返回
    private static final int FOCUS_ON_CONTENT = 4;//内容
    private int mLetterFocus;//字母光标选中位置
    private int mContentFocus;//内容光标选中位置
    private int mFlipSelectedPosition;//翻页之后选中位置

    private SearchModelPy mHotModelPy;
    private SearchModelPy mSearchModelPy;

    private List<List<SearchModelPy.DatabeanX.Databean>> mHotModelPagingList;//热门搜索分页之后的数据，每页12个
    private List<List<SearchModelPy.DatabeanX.Databean>> mSearchModelPagingList;//关键词搜索分页之后的数据
    private List<SearchModelPy.DatabeanX.Databean> mDataCurrentPy;
    private RvAdapterSearchPy mRvAdapterSearchPy;

    private final int TYPE_HOT = 1;
    private final int TYPE_SEARCH = 2;
    @Override
    protected void initData() {
        mRvContent.setLayoutManager(new GridLayoutManager(this,4));
        setLetterData();
        mAdapterLetter = new RvAdapterLetter(mData,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,6);
        mRvLetter.setAdapter(mAdapterLetter);
        mRvLetter.setLayoutManager(layoutManager);
        mAdapterLetter.setOnItemClickListener(new RvAdapterLetter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                LogUtil.e("__onItemClick",position + "");
                LogUtil.e("__onItemClick",mData.get(position) + "");
                mEdit.setText(String.valueOf(mEdit.getText().toString() + mData.get(position)));
                mSearchModel = null;
                mCurrentPageSearch = 0;
                search();
            }

            @Override
            public void onItemFocusChange(int position) {
                mAdapterFocusPosition = -1;
                mFocusPosition = FOCUS_ON_LETTER;
                mLetterFocus = position;
            }
        });
        mRvLetter.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRvLetter.getChildAt(0).requestFocus();
                mRvLetter.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        getHorSearch();
    }
    private final int FLIP_UP = 1;
    private final int FLIP_DOWN = 2;
    /**
     *
     * @param isFlip   是否翻页
     * @param flipType  翻页类型，1为上，2为下
     */
    private void setHotData(boolean isFlip,int flipType){
        if (mHotModelPy == null || mHotModelPy.getData() == null || mHotModelPy.getData().getData() == null || mHotModelPy.getData().getData().size() == 0){
            mTvPage.setVisibility(View.GONE);
            mIsNotData = true;
            return;
        }
        mIsNotData = false;
        mLlNotData.setVisibility(View.GONE);
        mRvContent.setVisibility(View.VISIBLE);
        mTvSearchType.setText("热门搜索");
        mTvPage.setVisibility(View.VISIBLE);
        mTvPage.setText(mCurrentPageHot + 1 + "/" + mHotModelPagingList.size());
        mCurrentPageSearch = 0;
        mDataType = TYPE_HOT;
        mDataCurrentPy = mHotModelPagingList.get(mCurrentPageHot);
        LogUtil.e("__mFlipSelectedPosition_mAdapterFocusPosition111",mAdapterFocusPosition + "");
        if (isFlip){
            if (flipType == FLIP_DOWN){//向下翻页时，计算翻页之后选中位置
                if (mDataCurrentPy.size() - 1 >= mAdapterFocusPosition){
                    mFlipSelectedPosition = mAdapterFocusPosition;
                }else {
                    mFlipSelectedPosition = mDataCurrentPy.size() - 1;
                }
            }else if (flipType == FLIP_UP){//向上翻页时，计算翻页之后选中位置
                switch (mAdapterFocusPosition){
                    case 0:
                        mFlipSelectedPosition = 0;
                        break;
                    case 1:
                        mFlipSelectedPosition = 1;
                        break;
                    case 2:
                        mFlipSelectedPosition = 2;
                        break;
                    case 3:
                        mFlipSelectedPosition = 3;
                        break;
                    default:
                        mFlipSelectedPosition = 0;
                        break;
                }
            }
            mRvContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //给GridView设置Adapter，在adapter的getView中获取GridView的高度，在这个回调之前获取的高度都是0
                    mRvContent.getChildAt(mFlipSelectedPosition).requestFocus();
                    //处理完后remove掉
                    mRvContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        mRvAdapterSearchPy = new RvAdapterSearchPy(mDataCurrentPy,this);
        mRvContent.setAdapter(mRvAdapterSearchPy);
        mRvAdapterSearchPy.setOnItemClickListener(new RvAdapterSearchPy.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                isCourseForbidden(String.valueOf(mDataCurrentPy.get(position).getId()),null);
//                Intent intent = new Intent(SearchActivity.this,CourseDetailActivity.class);
//                intent.putExtra("courseId",String.valueOf(mDataCurrentPy.get(position).getId()));
//                startActivity(intent);
            }
            @Override
            public void onItemPisitionFocusGetted(int position) {
                mFocusPosition = FOCUS_ON_CONTENT;
                mAdapterFocusPosition = position;
                LogUtil.e("__onItemPisitionFocusChange","mAdapterFocusPosition:" + mAdapterFocusPosition);
            }
        });
//        if (mCurrentPageHot == mHotModel.getData().getPage_data().size() - 1){
        if (mCurrentPageHot == mHotModelPagingList.size() - 1){
            mIvNextPage.setVisibility(View.GONE);
        }else {
            mIvNextPage.setVisibility(View.VISIBLE);
        }
    }

    /**
     *
     * @param isFlip   是否翻页
     * @param flipType 1是上翻页，2是下
     */
    private void setSearchData(boolean isFlip,int flipType){

//        if (mSearchModel == null || mSearchModel.getData() == null || mSearchModel.getData().getPage_data() == null || mSearchModel.getData().getPage_data().size() == 0){
        if (mSearchModelPy == null || mSearchModelPy.getData() == null || mSearchModelPy.getData().getData()== null || mSearchModelPy.getData().getData().size() == 0){
            mTvPage.setVisibility(View.GONE);
            mRvContent.setVisibility(View.GONE);
            mLlNotData.setVisibility(View.VISIBLE);
            mIvNextPage.setVisibility(View.GONE);
            mTvSearchType.setText("共0个结果");
            mCurrentPageSearch = 0;
            mIsNotData = true;
            LogUtil.e("__setSearchData","null");
            return;
        }
        mIsNotData = false;
        mRvContent.setVisibility(View.VISIBLE);
        mLlNotData.setVisibility(View.GONE);
//        mTvSearchType.setText("共" + mSearchModel.getData().getTotal_count() + "个结果");
        mTvSearchType.setText("共" + mSearchModelPy.getData().getTotal() + "个结果");
        mTvPage.setVisibility(View.VISIBLE);
//        mTvPage.setText(mCurrentPageSearch + 1 + "/" + mSearchModel.getData().getTotal_pages());
        mTvPage.setText(mCurrentPageSearch + 1 + "/" + mSearchModelPagingList.size());
        mDataType = TYPE_SEARCH;
//        mDataCurrent = mSearchModel.getData().getPage_data().get(mCurrentPageSearch);
        mDataCurrentPy = mSearchModelPagingList.get(mCurrentPageSearch);
        if (isFlip){
            if (flipType == FLIP_DOWN){//向下翻页时，计算翻页之后选中位置
//                if (mDataCurrent.size() - 1 >= mAdapterFocusPosition){
                if (mDataCurrentPy.size() - 1 >= mAdapterFocusPosition){
                    mFlipSelectedPosition = mAdapterFocusPosition;
                }else {
//                    mFlipSelectedPosition = mDataCurrent.size() - 1;
                    mFlipSelectedPosition = mDataCurrentPy.size() - 1;
                }
            }else if (flipType == FLIP_UP){//向上翻页时，计算翻页之后选中位置
                switch (mAdapterFocusPosition){
                    case 0:
                        mFlipSelectedPosition = 0;
                        break;
                    case 1:
                        mFlipSelectedPosition = 1;
                        break;
                    case 2:
                        mFlipSelectedPosition = 2;
                        break;
                    case 3:
                        mFlipSelectedPosition = 3;
                        break;
                    default:
                        mFlipSelectedPosition = 0;
                        break;
                }
            }
            LogUtil.e("__mFlipSelectedPosition",mFlipSelectedPosition + "__");
            LogUtil.e("__mFlipSelectedPosition_flipType","__" + flipType);
            LogUtil.e("__mFlipSelectedPosition_mAdapterFocusPosition222",mAdapterFocusPosition + "");
            mRvContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LogUtil.e("__mFlipSelectedPosition222",mFlipSelectedPosition + "");
                    //给GridView设置Adapter，在adapter的getView中获取GridView的高度，在这个回调之前获取的高度都是0
                    mRvContent.getChildAt(mFlipSelectedPosition).requestFocus();
                    //处理完后remove掉
                    mRvContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
//            LogUtil.e("__mRvContent",mRvCourse.getDataSize() + "");
//            mRvCourse.getChildAt(0).requestFocus();
        }
//        mRvAdapterSearch = new RvAdapterSearch(mDataCurrent,this);
        mRvAdapterSearchPy = new RvAdapterSearchPy(mDataCurrentPy,this);
//        LogUtil.e("__setSearchData_size",mSearchModel.getData().getPage_data().size() + "");
//        LogUtil.e("__setSearchData_mDataCurrent",mDataCurrent.size() + "");
//        LogUtil.e("__setSearchData_mCurrentPageSearch",mCurrentPageSearch + "");
//        mRvContent.setAdapter(mRvAdapterSearch);
        mRvContent.setAdapter(mRvAdapterSearchPy);
//        mRvAdapterSearch.setOnItemClickListener(new RvAdapterSearch.OnItemClickListener() {
        mRvAdapterSearchPy.setOnItemClickListener(new RvAdapterSearchPy.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtil.e("__onItemClick_position",position + "");
//                LogUtil.e("__onItemClick_getCourse_id",mDataCurrent.get(position).getCourse_id());
                LogUtil.e("__onItemClick_getCourse_id",mDataCurrentPy.get(position).getId() + "");
//                isCourseForbidden(mDataCurrent.get(position).getCourse_id(),null);
                Intent intent = new Intent(SearchActivity.this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(position).getCourse_id());
                intent.putExtra("courseId",String.valueOf(mDataCurrentPy.get(position).getId()));
                startActivity(intent);
            }
            @Override
            public void onItemPisitionFocusGetted(int position) {
                mFocusPosition = FOCUS_ON_CONTENT;
                mAdapterFocusPosition = position;
            }
        });
//        if (mCurrentPageSearch == mSearchModel.getData().getPage_data().size() - 1){
        if (mCurrentPageSearch == mSearchModelPagingList.size() - 1){
            mIvNextPage.setVisibility(View.GONE);
        }else {
            mIvNextPage.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 搜索
     */
    private void search(){
        mDataType = TYPE_SEARCH;
        mHttpHelper.searchCourse(this,mEdit.getText().toString(), HttpHelper.PY_HTTP_KEY_SEARCH,limit);

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        GroceryStoreUtils.GlideGif(SearchActivity.this,R.drawable.arrow_down, mIvNextPage);
        mTvClear.setOnClickListener(this);
        mTvDelete.setOnClickListener(this);
        mTvBack.setOnClickListener(this);
        mTvBack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mFocusPosition = FOCUS_ON_BACK;
                    float scale = 1.08f;
                    ScaleUtil.scale(mTvBack,scale);
//                    mAdapterFocusPosition = -1;
                }else {
                    float scale = 1f;
                    ScaleUtil.scale(mTvBack,scale);
                }
            }
        });
        mTvDelete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mFocusPosition = FOCUS_ON_DELETE;
//                    mAdapterFocusPosition = -1;
                }
            }
        });
        mTvClear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mFocusPosition = FOCUS_ON_CLEAR;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_delete:
                String temp;
                if (mEdit.getText().toString().length() == 1){
                    mEdit.setText("");
                    mCurrentPageHot = 0;
                    setHotData(false,0);
                }else if(mEdit.getText().toString().length() > 1){
//                    LogUtil.e("__onClick_tv_delete",mEdit.getText().toString().length() + "");
                    temp = mEdit.getText().toString().substring(0,mEdit.getText().toString().length() - 1);
                    mEdit.setText(temp);
                    search();
                }
                break;
            case R.id.tv_clear:
                mEdit.setText("");
                mCurrentPageHot = 0;
                setHotData(false,0);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void setLetterData(){
        mData.add("A");
        mData.add("B");
        mData.add("C");
        mData.add("D");
        mData.add("E");
        mData.add("F");
        mData.add("G");
        mData.add("H");
        mData.add("I");
        mData.add("J");
        mData.add("K");
        mData.add("L");
        mData.add("M");
        mData.add("N");
        mData.add("O");
        mData.add("P");
        mData.add("Q");
        mData.add("R");
        mData.add("S");
        mData.add("T");
        mData.add("U");
        mData.add("V");
        mData.add("W");
        mData.add("X");
        mData.add("Y");
        mData.add("Z");
        mData.add("1");
        mData.add("2");
        mData.add("3");
        mData.add("4");
        mData.add("5");
        mData.add("6");
        mData.add("7");
        mData.add("8");
        mData.add("9");
        mData.add("0");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //模拟器测试时键盘中的的Enter键，模拟ok键（推荐TV开发中使用蓝叠模拟器）
            case KeyEvent.KEYCODE_ENTER:
                break;
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                LogUtil.e("__onKeyDown_search","中间键");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                LogUtil.e("__onKeyDown","左方向键");
//                if (mIsLeftToDelete){
//                    mTvDelete.requestFocus();
//                    return true;
//                }else {
//
//                }
                if (mFocusPosition == FOCUS_ON_CONTENT){
                    switch (mAdapterFocusPosition){
                        case 0:
                        case 4:
                        case 8:
                        case 12:
                            mTvDelete.requestFocus();
                            return true;
                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                LogUtil.e("__onKeyDown","右方向键");
                LogUtil.e("__onKeyDown","右方向键__mFocusPosition" + mFocusPosition);
                LogUtil.e("__onKeyDown","右方向键__mLetterFocus" + mLetterFocus);
                if (mFocusPosition == FOCUS_ON_LETTER){//位置在输入字母那里，
                    if (mIsNotData){
                        mTvBack.requestFocus();
                        return true;
                    }else {
                        if (mLetterFocus % 6 == 5){//位置在最右边
                            LogUtil.e("__onKeyDown","右方向键__mLetterFocus获取焦点");
                            if (mRvContent.getChildCount() > 0){
                                LogUtil.e("__onKeyDown","右方向键__mLetterFocus获取焦点2222");
                                mRvContent.getChildAt(0).requestFocus();
                                return true;
                            }
                        }
                    }
                }else if (mFocusPosition == FOCUS_ON_CONTENT){
//                    if (mAdapterFocusPosition == mDataCurrent.size() - 1){//当处在课程最后一个，按右时，不做处理
                    if (mAdapterFocusPosition == mDataCurrentPy.size() - 1){//当处在课程最后一个，按右时，不做处理
                        return true;
                    }
                }else if (mFocusPosition == FOCUS_ON_DELETE){
                    if (mIsNotData){
                        mTvBack.requestFocus();
                        return true;
                    }else {

                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                LogUtil.e("__onKeyDown","上方向键");
                LogUtil.e("__onKeyDown","上方向键_mAdapterFocusPosition:" + mAdapterFocusPosition);
                if (mFocusPosition == FOCUS_ON_CONTENT) {
                    if (mAdapterFocusPosition >= 0 && mAdapterFocusPosition < 4) {//表示在第一行
                        if (mDataType == TYPE_HOT) {
                            if (mCurrentPageHot > 0) {
                                mCurrentPageHot--;
                                setHotData(true,FLIP_UP);
                                return true;
                            } else {
                                mTvBack.requestFocus();
                                return true;
                            }
                        } else if (mDataType == TYPE_SEARCH) {
                            if (mCurrentPageSearch > 0) {
                                mCurrentPageSearch--;
                                setSearchData(true,FLIP_UP);
                                return true;
                            } else {
                                mTvBack.requestFocus();
                                return true;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                LogUtil.e("__onKeyDown","下方向键");
                LogUtil.e("__onKeyDown","下方向键_mAdapterFocusPosition:" + mAdapterFocusPosition);
                if (mFocusPosition == FOCUS_ON_CONTENT){
                        if (mDataType == TYPE_HOT){
                            if (mAdapterFocusPosition > 7) {
//                                if (mCurrentPageHot < mHotModel.getData().getPage_data().size()) {
                                if (mCurrentPageHot < mHotModelPagingList.size()) {
                                    mCurrentPageHot++;
//                                    int tempPosition = mAdapterFocusPosition;
                                    setHotData(true,FLIP_DOWN);
                                    return true;
                                }
                            }else {
//                                if (mCurrentPageHot == mHotModel.getData().getPage_data().size() - 1){//表示是最后一页
                                if (mCurrentPageHot == mHotModelPagingList.size() - 1){//表示是最后一页
//                                    if (mAdapterFocusPosition > mDataCurrent.size() - 1 -4){//表示在最后一行，避免向下跳转到字母那里
//                                    if (mAdapterFocusPosition > mDataCurrent.size() - 1 - (mDataCurrent.size() % 4)){//表示在最后一行，避免向下跳转到字母那里
                                    if (mAdapterFocusPosition > mDataCurrentPy.size() - 1 - (mDataCurrentPy.size() % 4)){//表示在最后一行，避免向下跳转到字母那里
                                        return true;
                                    }
                                }
                            }
                        }else if (mDataType == TYPE_SEARCH){
                            if (mAdapterFocusPosition > 7) {//只有大于7才可能翻页，小于7表示该页还未填满,没有下一页
//                                if (mCurrentPageSearch < mSearchModel.getData().getPage_data().size() - 1) {
                                if (mCurrentPageSearch < mSearchModelPagingList.size() - 1) {
                                    mCurrentPageSearch++;
                                    setSearchData(true,FLIP_DOWN);
                                    return true;
                                }
                            }else {
//                                if (mCurrentPageSearch == mSearchModel.getData().getPage_data().size() - 1){//表示是最后一页
                                if (mCurrentPageSearch == mSearchModelPagingList.size() - 1){//表示是最后一页
//                                    if (mAdapterFocusPosition > mDataCurrent.size() - 1 -4){//表示在最后一行，避免向下跳转到字母那里
//                                    if (mAdapterFocusPosition > mDataCurrent.size() - 1 - (mDataCurrent.size() % 4)){//表示在最后一行，避免向下跳转到字母那里
                                    if (mAdapterFocusPosition > mDataCurrentPy.size() - 1 - (mDataCurrentPy.size() % 4)){//表示在最后一行，避免向下跳转到字母那里
                                            return true;
                                        }
                                }
                            }
                        }
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    private int limit = 10000;
    /**
     * 获取热门搜索数据
     */
    private void getHorSearch(){
        mHttpHelper.searchCourse(this,"", HttpHelper.PY_HTTP_HOT_SEARCH,30);
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        switch (reqType){
            case HttpHelper.PY_HTTP_HOT_SEARCH:
                mHotModelPy = (SearchModelPy) msg.obj;
//                mHotModel = (SearchModel) msg.obj;
                if (mHotModelPy == null || mHotModelPy.getData() == null || mHotModelPy.getData().getData() == null
                        || mHotModelPy.getData().getData().size() == 0){
                    return;
                }
                arrangeHot();
                setHotData(false,0);
                break;
            case HttpHelper.PY_HTTP_KEY_SEARCH:
                mSearchModelPy = (SearchModelPy) msg.obj;
//                mHotModel = (SearchModel) msg.obj;
                arrangeSearchData();
                setSearchData(false,0);
                break;
        }
    }

    /**
     * 整理搜索的数据，进行分页
     */
    private void arrangeSearchData() {
        if (mSearchModelPy == null || mSearchModelPy.getData() == null || mSearchModelPy.getData().getData() == null
                || mSearchModelPy.getData().getData().size() == 0){
            return;
        }
        mSearchModelPagingList = new ArrayList<>();
        List<SearchModelPy.DatabeanX.Databean> courseList = mSearchModelPy.getData().getData();
        List<SearchModelPy.DatabeanX.Databean> list = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            list.add(courseList.get(i));
            if (list.size() == 12){//每页12个课程，够12个就放进去
                mSearchModelPagingList.add(list);
                list = new ArrayList<>();
            }else if (i == courseList.size() - 1){//所有数据放完了，也放进去
                mSearchModelPagingList.add(list);
            }
        }
    }

    /**
     * 整理热门搜索的数据，进行分页
     */
    private void arrangeHot() {
        mHotModelPagingList = new ArrayList<>();
        List<SearchModelPy.DatabeanX.Databean> courseList = mHotModelPy.getData().getData();
        List<SearchModelPy.DatabeanX.Databean> list = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            list.add(courseList.get(i));
            if (list.size() == 12){//每页12个课程，够12个就放进去
                mHotModelPagingList.add(list);
                list = new ArrayList<>();
            }else if (i == courseList.size() - 1){//所有数据放完了，也放进去
                mHotModelPagingList.add(list);
            }
        }
//        mHotModelPagingList
    }

    @Override
    public void onHttpError(int reqType, String error) {

    }

    @Override
    public void onTokenError(int reqType, String error, String errorCode) {
        toLogin();
    }
}
