package com.geling.view.gelingtv_XX_tongbu.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.IHttp;
import com.geling.view.gelingtv_XX_tongbu.model.bean.ClearnCollection;
import com.geling.view.gelingtv_XX_tongbu.model.bean.CollectionModel;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ClearCollectionDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.CourseForbiddenDialog;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.Base64Util;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.SysUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/28------更新-----
 * 收藏
 */

public class CollectionActivity extends BaseActivity {

    @BindView(R.id.activity_main_bag_imag)
    ImageView mActivityMainBagImag;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_page)
    TextView mTvPage;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.iv1_collection)
    ImageView mIv1Collection;
    @BindView(R.id.tv_title_item1)
    TextView mTvTitleItem1;
    @BindView(R.id.tv_category_name_item1)
    TextView mTvCategoryNameItem1;

    @BindView(R.id.ll_item1_collection)
    LinearLayout mLlItem1Collection;
    @BindView(R.id.iv2_collection)
    ImageView mIv2Collection;
    @BindView(R.id.tv_title_item2)
    TextView mTvTitleItem2;
    @BindView(R.id.tv_category_name_item2)
    TextView mTvCategoryNameItem2;

    @BindView(R.id.ll_item2_collection)
    LinearLayout mLlItem2Collection;
    @BindView(R.id.iv3_collection)
    ImageView mIv3Collection;
    @BindView(R.id.tv_title_item3)
    TextView mTvTitleItem3;
    @BindView(R.id.tv_category_name_item3)
    TextView mTvCategoryNameItem3;

    @BindView(R.id.ll_item3_collection)
    LinearLayout mLlItem3Collection;
    @BindView(R.id.iv4_collection)
    ImageView mIv4Collection;
    @BindView(R.id.tv_title_item4)
    TextView mTvTitleItem4;
    @BindView(R.id.tv_category_name_item4)
    TextView mTvCategoryNameItem4;

    @BindView(R.id.ll_item4_collection)
    LinearLayout mLlItem4Collection;
    @BindView(R.id.iv5_collection)
    ImageView mIv5Collection;
    @BindView(R.id.tv_title_item5)
    TextView mTvTitleItem5;
    @BindView(R.id.tv_category_name_item5)
    TextView mTvCategoryNameItem5;
    @BindView(R.id.ll_item5_collection)
    LinearLayout mLlItem5Collection;
    @BindView(R.id.iv6_collection)
    ImageView mIv6Collection;
    @BindView(R.id.tv_title_item6)
    TextView mTvTitleItem6;
    @BindView(R.id.tv_category_name_item6)
    TextView mTvCategoryNameItem6;
    @BindView(R.id.tv_clear)
    TextView mTvClear;
    @BindView(R.id.ll_item6_collection)
    LinearLayout mLlItem6Collection;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    @BindView(R.id.iv_next_page)
    ImageView mIvNextPage;
    @BindView(R.id.iv_pre_page)
    ImageView mIvPrePage;
    @BindView(R.id.ll_not_data)
    LinearLayout mLlNotData;
    @BindView(R.id.tv_go_to_explore)
    TextView mTvGoToExplore;
    private List<LinearLayout> mLLList = new ArrayList<>();//六个item布局集合
    private List<ImageView> mIvList = new ArrayList<>();//六个item的iv集合
    private List<TextView> mTvTitleList = new ArrayList<>();//六个item标题的tv集合
    private List<TextView> mTvCategoryNameList = new ArrayList<>();//六个item课程tv集合
    private List<List<CollectionModel.Databean.PageDatabean>> mDataTotal;//所有页面数据
    private List<CollectionModel.Databean.PageDatabean> mDataCurrent;//当前页数据
    private int mCurrentFocusPosition; //焦点所在位置，0表示在返回或者在清空上

    private List<CollectionModel.Databean.PageDatabean> mAllData;//拿到后台数据之后，把所有的数据放在这里，方便后续操作

    //    private String mTitle;
    private int mCurrentPage = 0;//当前页码
    private int mTotalPage = 0;//总页数
    private int mTotalCount = 0;//总条数

    @Override
    protected void initData() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        String userId = SysUtil.getSystemProperties(this, SysUtil.SYSKEY_JX_SMART_CARD);
        treeMap.put("userId", userId);
        JSONObject jsonObject111 = CommonUtils.getRequestJsonObj(treeMap);
        String data = "";
        String sign = "";
        try {
            data = jsonObject111.getString("data");
            sign = jsonObject111.getString("sign");
        } catch (Exception e) {

        }
        OkHttpUtils.get()
                .url(IHttp.MY_COLLECTION_DATA)
                .addParams("data", data)
                .addParams("sign", sign)
//                .addParams("userId", UserManager.getUserId())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("__initData", "collection_onError:" + e.getMessage());
                        LogUtil.e("__initData", "请求收藏数据错误_onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject1111 = new JSONObject(response.toString());
                            String dataSrc = jsonObject1111.getString("data");
                            LogUtil.e("__initData收藏_response_dataSrc", dataSrc + "__");
                            String data = Base64Util.decodeStr(dataSrc);
//                            JSONObject jsonObject = new JSONObject(data);
                            LogUtil.e("__initData收藏_response_data", data + "__");
                            Gson gson = new Gson();
                            CollectionModel model;
                            model = gson.fromJson(data, CollectionModel.class);
                            if (model.getCode() == 10000 && model.getData().getTotal_count() > 0) {
                                mDataTotal = model.getData().getPage_data();
                                mTotalPage = model.getData().getTotal_pages();
                                mTotalCount = model.getData().getTotal_count();
                                pageDown();
                                mTvClear.setVisibility(View.VISIBLE);
                                mLlNotData.setVisibility(View.GONE);
                                mLlContent.setVisibility(View.VISIBLE);
                                mTvPage.setVisibility(View.VISIBLE);
                                mLlItem1Collection.requestFocus();
                                addAllData();
                            } else {
                                mLlNotData.setVisibility(View.VISIBLE);
                                mTvGoToExplore.requestFocus();
                                LogUtil.e("__initData", "collection_response_fail_code:" + String.valueOf(model.getCode()));
                                LogUtil.e("__initData", "collection_response_fail_msg:" + String.valueOf(model.getMsg()));
                            }
                        } catch (Exception e) {
                            mLlNotData.setVisibility(View.VISIBLE);
                            mTvGoToExplore.requestFocus();
                            LogUtil.e("__initData", "collection_Exception:" + String.valueOf(e.getMessage()));
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
//        Intent intent = getIntent();
//        mTitle = intent.getStringExtra("title");
//        mTvTitle.setText(String.valueOf(mTitle));

        mTvBack.setOnClickListener(this);

        mLlItem1Collection.setOnClickListener(this);
        mLlItem2Collection.setOnClickListener(this);
        mLlItem3Collection.setOnClickListener(this);
        mLlItem4Collection.setOnClickListener(this);
        mLlItem5Collection.setOnClickListener(this);
        mLlItem6Collection.setOnClickListener(this);

        mLLList.add(mLlItem1Collection);
        mLLList.add(mLlItem2Collection);
        mLLList.add(mLlItem3Collection);
        mLLList.add(mLlItem4Collection);
        mLLList.add(mLlItem5Collection);
        mLLList.add(mLlItem6Collection);

        mIvList.add(mIv1Collection);
        mIvList.add(mIv2Collection);
        mIvList.add(mIv3Collection);
        mIvList.add(mIv4Collection);
        mIvList.add(mIv5Collection);
        mIvList.add(mIv6Collection);

        mTvTitleList.add(mTvTitleItem1);
        mTvTitleList.add(mTvTitleItem2);
        mTvTitleList.add(mTvTitleItem3);
        mTvTitleList.add(mTvTitleItem4);
        mTvTitleList.add(mTvTitleItem5);
        mTvTitleList.add(mTvTitleItem6);

        mTvCategoryNameList.add(mTvCategoryNameItem1);
        mTvCategoryNameList.add(mTvCategoryNameItem2);
        mTvCategoryNameList.add(mTvCategoryNameItem3);
        mTvCategoryNameList.add(mTvCategoryNameItem4);
        mTvCategoryNameList.add(mTvCategoryNameItem5);
        mTvCategoryNameList.add(mTvCategoryNameItem6);

        mTvGoToExplore.setOnClickListener(this);
        mTvClear.setOnClickListener(this);
        mLlItem1Collection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange", "mLlCollection：" + b);
                if (b) {
                    mCurrentFocusPosition = 1;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {

                }
            }
        });
        mLlItem2Collection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange", "mLlCollection：" + b);
                if (b) {
                    mCurrentFocusPosition = 2;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {

                }
            }
        });
        mLlItem3Collection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange", "mLlCollection：" + b);
                if (b) {
                    mCurrentFocusPosition = 3;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {

                }
            }
        });
        mLlItem4Collection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange", "mLlCollection：" + b);
                if (b) {
                    mCurrentFocusPosition = 4;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {

                }
            }
        });
        mLlItem5Collection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange", "mLlCollection：" + b);
                if (b) {
                    mCurrentFocusPosition = 5;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {

                }
            }
        });
        mLlItem6Collection.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                LogUtil.e("__onFocusChange", "mLlCollection：" + b);
                if (b) {
                    mCurrentFocusPosition = 6;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {

                }
            }
        });
        mTvBack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mCurrentFocusPosition = 0;
                    float scale = 1.08f;
                    ScaleUtil.scale(mTvBack, scale);
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {
                    float scale = 1f;
                    ScaleUtil.scale(mTvBack, scale);
                }
            }
        });
        mTvClear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    float scale = 1.05f;
                    ScaleUtil.scale(mTvClear, scale);
                    mCurrentFocusPosition = 0;
//                    mTvVipAdvert.setTextColor(getResources().getColor(R.color.black_gold));
                } else {
                    float scale = 1f;
                    ScaleUtil.scale(mTvClear, scale);
                }
            }
        });
    }

    private void pageDown() {
        try{
            mCurrentPage++;
            if (mCurrentPage < mTotalPage) {
                mIvNextPage.setVisibility(View.VISIBLE);
            } else {
                mIvNextPage.setVisibility(View.GONE);
            }
            if (mCurrentPage > 1) {
                mIvPrePage.setVisibility(View.VISIBLE);
            }
            mTvBack.setVisibility(View.VISIBLE);
            mTvPage.setText(mCurrentPage + "/" + mTotalPage);
            mDataCurrent = mDataTotal.get(mCurrentPage - 1);
            for (int i = 0; i < mLLList.size(); i++) {
                if (mDataCurrent.size() > i) {
                    mLLList.get(i).setVisibility(View.VISIBLE);
                    CollectionModel.Databean.PageDatabean databean = mDataCurrent.get(i);
                    //                GroceryStoreUtils.GlideImag(this,databean.getCourse_img(),mIvList.get(i));
//                String imgUrl = MainApp.imgUrl;
                    String imgUrl = mDataCurrent.get(i).getCourse_img();
                    GroceryStoreUtils.GlideImag(this, imgUrl, mIvList.get(i));
                    mTvTitleList.get(i).setText(databean.getCourse_name());
                    mTvCategoryNameList.get(i).setText("来自" + databean.getCategory_name());
                } else {
                    mLLList.get(i).setVisibility(View.GONE);
                }
            }
            mTvCount.setText("共" + mTotalCount + "个课程");
        }catch (Exception e){

        }
    }

    private void pageUp() {
        try{
            LogUtil.e("__pageUp", "_mCurrentPage_before_" + mCurrentPage);
            mCurrentPage--;
            if (mCurrentPage > 1) {
                mIvPrePage.setVisibility(View.VISIBLE);
            } else {
                mIvPrePage.setVisibility(View.GONE);
            }
            mIvNextPage.setVisibility(View.VISIBLE);
            LogUtil.e("__pageUp", "_mCurrentPage_after_" + mCurrentPage);
            mTvPage.setText(mCurrentPage + "/" + mTotalPage);
            mDataCurrent = mDataTotal.get(mCurrentPage - 1);
            if (mCurrentPage == 1) {
                mIvPrePage.setVisibility(View.GONE);
            }
            for (int i = 0; i < mLLList.size(); i++) {
                if (mDataCurrent.size() > i) {
                    mLLList.get(i).setVisibility(View.VISIBLE);
                    CollectionModel.Databean.PageDatabean databean = mDataCurrent.get(i);
                    //                GroceryStoreUtils.GlideImag(this,databean.getCourse_img(),mIvList.get(i));
//                String imgUrl = MainApp.imgUrl;
                    String imgUrl = databean.getCourse_img();
                    GroceryStoreUtils.GlideImag(this, imgUrl, mIvList.get(i));
                    mTvTitleList.get(i).setText(databean.getCourse_name());
                    mTvCategoryNameList.get(i).setText("来自" + databean.getCategory_name());
                } else {
                    mLLList.get(i).setVisibility(View.GONE);
                }
            }
        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View view) {
        try {
            Intent intent;
            switch (view.getId()) {
                case R.id.tv_back:
                case R.id.tv_go_to_explore:
                    finish();
                    break;
                case R.id.ll_item1_collection:
                    isCourseForbidden(mDataCurrent.get(0).getCourse_id(), null);
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(0).getCourse_id());
                    LogUtil.e("__onClick", "__category_id_1_getCategory_name:" + mDataCurrent.get(0).getCategory_name());
                    LogUtil.e("__onClick", "__category_id_1_getCourse_name:" + mDataCurrent.get(0).getCourse_name());
//                startActivity(intent);
                    break;
                case R.id.ll_item2_collection:
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(1).getCourse_id());
//                LogUtil.e("__onClick","__category_id_getCategory_name:" + mDataCurrent.get(1).getCategory_name());
//                LogUtil.e("__onClick","__category_id_getCourse_name:" + mDataCurrent.get(1).getCourse_name());
//                startActivity(intent);
                    isCourseForbidden(mDataCurrent.get(1).getCourse_id(), null);
                    break;
                case R.id.ll_item3_collection:
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(2).getCourse_id());
//                LogUtil.e("__onClick","__category_id_getCategory_name:" + mDataCurrent.get(2).getCategory_name());
//                LogUtil.e("__onClick","__category_id_getCourse_name:" + mDataCurrent.get(2).getCourse_name());
//                startActivity(intent);
                    isCourseForbidden(mDataCurrent.get(2).getCourse_id(), null);
                    break;
                case R.id.ll_item4_collection:
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(3).getCourse_id());
//                LogUtil.e("__onClick","__category_id_getCategory_name:" + mDataCurrent.get(3).getCategory_name());
//                LogUtil.e("__onClick","__category_id_getCourse_name:" + mDataCurrent.get(3).getCourse_name());
//                startActivity(intent);
                    isCourseForbidden(mDataCurrent.get(3).getCourse_id(), null);
                    break;
                case R.id.ll_item5_collection:
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(4).getCourse_id());
//                LogUtil.e("__onClick","__category_id_getCategory_name:" + mDataCurrent.get(4).getCategory_name());
//                LogUtil.e("__onClick","__category_id_getCourse_name:" + mDataCurrent.get(4).getCourse_name());
//                startActivity(intent);
                    isCourseForbidden(mDataCurrent.get(4).getCourse_id(), null);
                    break;
                case R.id.ll_item6_collection:
//                intent = new Intent(this,CourseDetailActivity.class);
//                intent.putExtra("courseId",mDataCurrent.get(5).getCourse_id());
//                LogUtil.e("__onClick","__category_id_getCategory_name:" + mDataCurrent.get(5).getCategory_name());
//                LogUtil.e("__onClick","__category_id_getCourse_name:" + mDataCurrent.get(5).getCourse_name());
//                startActivity(intent);
                    isCourseForbidden(mDataCurrent.get(5).getCourse_id(), null);
                    break;
                case R.id.tv_clear:
                    ClearCollectionDialog dialog = new ClearCollectionDialog().builder(this);
                    dialog.setOnClickListener(new ClearCollectionDialog.OnClickListener() {
                        @Override
                        public void onRightClick() {

                        }

                        @Override
                        public void onLeftClick() {
                            clearCollection();
                        }
                    });
                    dialog.show();
                    break;
            }
        } catch (Exception e) {
            new CourseForbiddenDialog().builder(this, "").show();
        }
    }

    /**
     * 清空收藏
     */
    private void clearCollection() {
//        OkHttpUtils.post()
//                .url(IHttp.CLEAR_COLLECTION)
//                .addParams("userId", UserManager.getUserId())
//                .build()
        //用Json格式传给后台
        TreeMap<String, String> treeMap = new TreeMap<>();
        String userId = SysUtil.getSystemProperties(this, SysUtil.SYSKEY_JX_SMART_CARD);
        treeMap.put("userId", userId);
        JSONObject jsonObject111 = CommonUtils.getRequestJsonObj(treeMap);
        String data = "";
        String sign = "";
        try {
            data = jsonObject111.getString("data");
            sign = jsonObject111.getString("sign");
        } catch (Exception e) {

        }
        JSONObject jsonObject = CommonUtils.getRequestJsonObj(treeMap);
        OkHttpUtils
                .postString()
                .content(jsonObject.toString())
                .url(IHttp.CLEAR_COLLECTION)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("__clearCollection清空收藏", "清空收藏数据错误_onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("__clearCollection清空收藏_response", response.toString() + "");
                        try {
                            //返回的数据前面加了标签，需要截取数据
                            JSONObject jsonObject1111 = new JSONObject(response.toString().substring(response.toString().indexOf("{\"data\":")));
                            String dataSrc = jsonObject1111.getString("data");
                            LogUtil.e("__clearCollection清空收藏_response_dataSrc", dataSrc + "__");
                            String data = Base64Util.decodeStr(dataSrc);
//                            JSONObject jsonObject = new JSONObject(data);
                            LogUtil.e("__clearCollection清空收藏_response_data", data + "__");
//                            int code = jsonObject.getInt("code");
                            Gson gson = new Gson();
                            ClearnCollection model;
                            model = gson.fromJson(data, ClearnCollection.class);
                            if (model.getCode() == 20000) {
//                              ToastUtil.showToast("");
                                clearSuccecc();
                            } else {
//                                mTvGoToExplore.requestFocus();
                                LogUtil.e("__clearCollection清空收藏_Code()!=20000", "collection_response_fail_code:" + String.valueOf(model.getCode()));
                                LogUtil.e("__clearCollection清空收藏", "collection_response_fail_msg:" + String.valueOf(model.getMsg()));
                            }
                        } catch (Exception e) {
//                            mTvGoToExplore.requestFocus();
                            LogUtil.e("__clearCollection清空收藏_Exception", "collection_Exception:" + String.valueOf(e.getMessage()));
                        }
                    }
                });
    }

    /**
     * 清除记录成功
     */
    private void clearSuccecc() {
        mCurrentPage = 0;
        mTotalPage = 0;
        mLlNotData.setVisibility(View.VISIBLE);
        mTvClear.setVisibility(View.GONE);
        mLlContent.setVisibility(View.GONE);
        mIvNextPage.setVisibility(View.GONE);
        mIvPrePage.setVisibility(View.GONE);
        mTvPage.setVisibility(View.GONE);
        mTvBack.setVisibility(View.GONE);
        mTvCount.setText("共0个课程");
        mTvGoToExplore.requestFocus();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //模拟器测试时键盘中的的Enter键，模拟ok键
            case KeyEvent.KEYCODE_ENTER:
                break;
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                LogUtil.e("__onKeyDown", "中间键");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                LogUtil.e("__onKeyDown", "左");
                LogUtil.e("__onKeyDown", "左");
                LogUtil.e("__onKeyDown_mCurrentPage", "左" + mCurrentPage);
                LogUtil.e("__onKeyDown_mCurrentFocusPosition", "左" + mCurrentFocusPosition);
                if (mCurrentFocusPosition != 0) {
                    if (mCurrentFocusPosition == 1 || mCurrentFocusPosition == 3 || mCurrentFocusPosition == 5) {
                        if (mCurrentPage > 1) {
                            switch (mCurrentFocusPosition) {
                                case 1:
                                    mLlItem1Collection.requestFocus();
                                    break;
                                case 3:
                                    mLlItem3Collection.requestFocus();
                                    break;
                                case 5:
                                    mLlItem5Collection.requestFocus();
                                    break;
                            }
                            pageUp();
                        }
                        return true;
                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                LogUtil.e("__onKeyDown", "右");
                LogUtil.e("__onKeyDown_mCurrentPage", "右" + mCurrentPage);
                LogUtil.e("__onKeyDown_mTotalPage", "右" + mTotalPage);
                LogUtil.e("__onKeyDown_mCurrentFocusPosition", "右" + mCurrentFocusPosition);
                if (mCurrentFocusPosition != 0) {
                    if (mCurrentFocusPosition == 2 || mCurrentFocusPosition == 4 || mCurrentFocusPosition == 6) {
                        if (mCurrentPage < mTotalPage) {
                            int tempPosition = mCurrentFocusPosition;
                            pageDown();
                            switch (tempPosition) {
                                case 2:
                                    LogUtil.e("____onKeyDown_右_2_mDataCurrent.size()", mDataCurrent.size() + "");
                                    if (mDataCurrent.size() >= 2) {
                                        mLlItem2Collection.requestFocus();
                                    } else {
                                        mLlItem1Collection.requestFocus();
                                    }
                                    return true;
                                case 4:
                                    LogUtil.e("____onKeyDown_右_4_mDataCurrent.size()", mDataCurrent.size() + "");
                                    if (mDataCurrent.size() >= 4) {
                                        mLlItem4Collection.requestFocus();
                                    } else {
                                        if (mDataCurrent.size() >= 3) {
                                            mLlItem3Collection.requestFocus();
                                        } else if (mDataCurrent.size() >= 2) {
                                            mLlItem2Collection.requestFocus();
                                        } else {
                                            mLlItem1Collection.requestFocus();
                                        }
                                    }
                                    return true;
                                case 6:
                                    LogUtil.e("____onKeyDown_右_6_mDataCurrent.size()", mDataCurrent.size() + "");
                                    if (mDataCurrent.size() >= 6) {
                                        mLlItem6Collection.requestFocus();
                                    } else {
                                        if (mDataCurrent.size() >= 5) {
                                            mLlItem5Collection.requestFocus();
                                        } else if (mDataCurrent.size() >= 4) {
                                            mLlItem4Collection.requestFocus();
                                        } else if (mDataCurrent.size() >= 3) {
                                            mLlItem3Collection.requestFocus();
                                        } else if (mDataCurrent.size() >= 2) {
                                            mLlItem2Collection.requestFocus();
                                        } else {
                                            mLlItem1Collection.requestFocus();
                                        }
                                    }
                                    return true;
                            }
                        }
                        return true;
                    } else {
                        LogUtil.e("____onKeyDown_右_6_mDataCurrent.size()", mDataCurrent.size() + "");
                        if (mCurrentPage == mTotalPage) {
                            //表示在最后一页
                            switch (mDataCurrent.size()) {
                                case 1:
                                    if (mCurrentFocusPosition == 1) {
                                        return true;
                                    }
                                    break;
                                case 2:

                                    break;
                                case 3:
                                    if (mCurrentFocusPosition == 3) {
                                        return true;
                                    }
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    if (mCurrentFocusPosition == 5) {
                                        return true;
                                    }
                                    break;
                                case 6:
                                    break;
                            }
                        }
                    }

                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                LogUtil.e("__onKeyDown", "上方向键");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                LogUtil.e("__onKeyDown", "下方向键");
//                if (mCurrentFocusPosition == 0){
//                    if (mDataCurrent != null ){
//                        if (mDataCurrent.size() == 1){
//                            mLlItem1Collection.requestFocus();
//                        }else if (mDataCurrent.size() > 1){
//                            mLlItem2Collection.requestFocus();
//                        }
//                        return true;
//                    }
//                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    //跳转到收藏界面，取消收藏之后返回来
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            LogUtil.e("__onActivityResult_==RESULT_OK", String.valueOf(resultCode == RESULT_OK));
            LogUtil.e("__onActivityResult_RESULT_CANCELED", String.valueOf(resultCode == RESULT_CANCELED));
            LogUtil.e("__onActivityResult_data.getCourseId", "courseId____" + data.getStringExtra("courseId"));
            if (resultCode == RESULT_OK && requestCode == RESULT_COLLECTION_ACTIVITY) {
                //当从这个界面去到课程详情界面之后，取消收藏，然后再回到这个界面
                //首先判断是不是只有一页，一个数据，是的话，展示无数据界面
                LogUtil.e("__onActivityResult_==RESULT_OK", "取消收藏");
                LogUtil.e("__onActivityResult_==RESULT_OK_mTotalPage", "取消收藏" + mTotalPage);
                LogUtil.e("__onActivityResult_==RESULT_OK_mTotalCount", "取消收藏" + mTotalCount);
                String courseId = data.getStringExtra("courseId");
                if (mTotalPage == 1) {//只有一页
                    if (mTotalCount == 1) {//只有一个课程
                        clearSuccecc();
                        LogUtil.e("__onActivityResult_==RESULT_OK_clearSuccecc", "取消收藏");
                    } else {
                        LogUtil.e("__onActivityResult_==RESULT_OK_clearSuccecc", "取消收藏_一页_不止一个课程");
                        for (int i = 0; i < mDataCurrent.size(); i++) {//把收藏数据里面对应的项去掉
                            CollectionModel.Databean.PageDatabean databean = mDataCurrent.get(i);
                            if (databean.getCourse_id().equals(courseId)) {
                                mDataCurrent.remove(i);
                            }
                        }
                        mTotalCount = mDataCurrent.size();
                        mCurrentPage = 0;
                        pageDown();
                        mLlItem1Collection.requestFocus();
                    }
                } else {
                    arrangeData(courseId);
                }
            }
        } catch (Exception e) {
            LogUtil.e("__onActivityResult_Exception", "Exception了");
        }
    }

    /**
     * arrangeData
     * 拿到后台数据之后，全部添加到mAllData
     */
    private void addAllData() {
        mAllData = new ArrayList<>();
        if (mDataTotal != null) {
//            arrangeData
            for (int i = 0; i < mDataTotal.size(); i++) {
                List<CollectionModel.Databean.PageDatabean> list = mDataTotal.get(i);
                for (int j = 0; j < list.size(); j++) {
                    mAllData.add(list.get(j));
                }
            }
        }
        LogUtil.e("__arrangeData_mAllData.size", String.valueOf(mAllData.size()));
    }

    /**
     * 进入收藏界面，如果取消收藏之后，需要重新整理数据
     *
     * @param courseId 取消收藏的courseId
     */
    private void arrangeData(String courseId) {
        try {
            //先循环删除对应的条目
            for (int i = 0; i < mAllData.size(); i++) {
                CollectionModel.Databean.PageDatabean bean = mAllData.get(i);
                if (bean.getCourse_id().equals(courseId)) {
                    mAllData.remove(i);
                }
            }
            //第二步，重新添加数据到mDataTotal
            List<List<CollectionModel.Databean.PageDatabean>> totalData = new ArrayList<>();
            List<CollectionModel.Databean.PageDatabean> list = new ArrayList<>();
            for (int j = 0; j < mAllData.size(); j++) {
                CollectionModel.Databean.PageDatabean bean = mAllData.get(j);
                list.add(bean);
                if (list.size() == 6){//每页6个，满6个就放进去
                    totalData.add(list);
                    list = new ArrayList<>();//重新创建一个集合
                }else {
                    if (j == mAllData.size() - 1){//不满6个，如果已经添加完，也放进去
                        totalData.add(list);
                    }
                }
            }
            mDataTotal = totalData;
            mTotalCount = mAllData.size();
            mTotalPage = mDataTotal.size();
            if (mCurrentPage > mDataTotal.size()){//表示删除一个数据之后，比原来少了一页
                pageUp();
                mTvCount.setText("共" + mTotalCount + "个课程");
            }else {
                mCurrentPage--;
                pageDown();
            }
            mLlItem1Collection.requestFocus();
        }catch (Exception e){
            LogUtil.e("__arrangeData_Exception","Exception了");
        }
    }
}
