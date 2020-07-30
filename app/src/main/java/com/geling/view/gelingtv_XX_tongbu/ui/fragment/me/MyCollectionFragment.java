package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionTitleBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MeActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis.IntentsUtlis;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyCollectionAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyCollectionTitleAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ViewUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/***
 * 我的收藏
 * */
public class MyCollectionFragment extends BaseFragment {
    @BindView(R.id.view_titile_me_fragment_text_name)
    TextView vTextTitle;
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.view_titile_me_fragment_but_empty)
    LinearLayout vTitleButtonEmpty;
    @BindView(R.id.fragment_my_collection_re_list)
    TvRecyclerView vRecCollectionList;
    @BindView(R.id.fragment_my_collection_re_list_title)
    TvRecyclerView vRecCollectionListTitle;
    @BindView(R.id.fragment_data_empty_bg)
    LinearLayout vLayutDataNull;
    @BindView(R.id.view_data_null_go_button)
    LinearLayout vDataNullGoButton;

    private MyCollectionAdapter myCollectionAdapter;
    private List<MyCollectionBean.DataBeanX.AppUserCollectionListBean.DataBean> listMyCollection = new ArrayList<>();
    private MyCollectionTitleAdapter myTitleAdapter;
    private List<MyCollectionTitleBean.DataBean> listTitle = new ArrayList<>();

    private int mTitleHistorySubscript=0;
    private int page =1;
    private  int totalPage ;
    private int subject=0;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_collection;
    }

    protected void initData() {
        getMyCollectionTitle();
        getMyCollection(0,1,0);
    }

    private void getMyCollection(int subject,int page,int update) {
        XHttpUtils.getHttpMyCollectionJson(subject, page, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("my_Collection_json"+json);
                MyCollectionBean myCollectionBean =MyCollectionBean.gsonBean(json);
                listMyCollection =myCollectionBean.getData().getAppUserCollectionList().getData();
                if (listMyCollection.size()<1){
                    vLayutDataNull.setVisibility(View.VISIBLE);
                    vRecCollectionList.setVisibility(View.GONE);
//                    vDataNullGoButton.setFocusable(true);
//                    vRecCollectionListTitle.setNextFocusDownId(R.id.view_data_null_go_button);
//                    vDataNullGoButton.setFocusable(true);

                }else {
                    vLayutDataNull.setVisibility(View.GONE);
                    vRecCollectionList.setVisibility(View.VISIBLE);
                }
                if (update ==0){
                     totalPage = myCollectionBean.getData().getAppUserCollectionList().getLast_page();
                    myCollectionAdapter.setNewData(listMyCollection);
                }else {
                    myCollectionAdapter.addData(listMyCollection);
                }
            }

            @Override
            public void onFailed(String  json) {
                vLayutDataNull.setVisibility(View.VISIBLE);
            }
        });
    }
    private void getMyCollectionTitle() {
        XHttpUtils.getHttpMyCollectionTitleJson(  new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("my_Collection_json_title"+json);
                listTitle =MyCollectionTitleBean.gsonBean(json).getData();
                listTitle.add(0,new MyCollectionTitleBean.DataBean(0,"全部"));
                myTitleAdapter.setNewData(listTitle);
            }

            @Override
            public void onFailed(String  json) {

            }
        });
    }



    @Override
    protected void initView(View view) {
        //收藏标题
        myTitleAdapter = new MyCollectionTitleAdapter(listTitle);
        ViewUtils.setRecyclerViewAdapter(getActivity(),myTitleAdapter,vRecCollectionListTitle, LinearLayoutManager.HORIZONTAL);
//        myTitleAdapter.setmPosition(0);
        // 收藏列表
        myCollectionAdapter = new MyCollectionAdapter(listMyCollection);
        myCollectionAdapter.setHasStableIds(true);

        vRecCollectionList.setItemAnimator(null);
        ViewUtils.setReViewGridAdapter(getActivity(),myCollectionAdapter,vRecCollectionList,2);
//        vRecCollectionList.setAdapter(myCollectionAdapter);
        //标题
        vTextTitle.setText("我的收藏");
        vTitleButtonEmpty.setVisibility(View.VISIBLE);//显示清空按钮
        //初始化点击事件
        vButtonReturn.setOnClickListener(this);
        vTitleButtonEmpty.setOnClickListener(this);
        vDataNullGoButton.setOnClickListener(this);
        //adapter点击事件
        initAdapterClick();
        //焦点处理
        initViewListener();
    }
    private void initViewListener() {
        //返回按钮
        vTitleButtonEmpty.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT){
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                    }
                }
                return false;
            }
        });
        vButtonReturn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    float scale = 1.08f;
                    ScaleUtil.scale(vButtonReturn,scale);
//                    mAdapterFocusPosition = -1;
                }else {
                    float scale = 1f;
                    ScaleUtil.scale(vButtonReturn,scale);
                }
            }
        });
        vTitleButtonEmpty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    float scale = 1.08f;
                    ScaleUtil.scale(vTitleButtonEmpty,scale);
//                    mAdapterFocusPosition = -1;
                }else {
                    float scale = 1f;
                    ScaleUtil.scale(vTitleButtonEmpty,scale);
                }
            }
        });
    }
    private void initAdapterClick() {
        //焦点移动边界监听
        vRecCollectionList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused)
            {
                switch (direction) {
                    case View.FOCUS_DOWN:
                            if (page<totalPage){
                                getMyCollection(subject,++page,0);
                                vRecCollectionList.setSelection(0);
                            }
                        break;
                    case View.FOCUS_UP:
                        if (page>1) {
                            getMyCollection(subject, --page, 0);
                            vRecCollectionList.setSelection(0);
                        }else {
                            vRecCollectionListTitle.setSelection(mTitleHistorySubscript);
                        }
                        break;
                    case View.FOCUS_LEFT:
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;
                    case View.FOCUS_RIGHT:

                        break;
                }
                //返回true时,事件将会被拦截由你来控制焦点
                return true;
            }
        });
        //标题
        vRecCollectionListTitle.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                myTitleAdapter.setmTextColor(false);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
                switch (direction) {
                    case View.FOCUS_DOWN:
                            vRecCollectionList.setSelection(0);
                        break;
                    case View.FOCUS_UP:
                        vTitleButtonEmpty.requestFocus();
                        break;
                    case View.FOCUS_LEFT:
                         MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;

                }

                return true;
            }
        });


        //item选中、点击监听
        vRecCollectionListTitle.setOnItemListener(new TvRecyclerView.OnItemListener() {
            @Override
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                //上次选中
                myTitleAdapter.setmPosition(mTitleHistorySubscript);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
            }

            @Override
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                //当前选中
                page=1;
                mTitleHistorySubscript=position;
                subject = listTitle.get(mTitleHistorySubscript).getId();
                getMyCollection(subject,1,0);
                myTitleAdapter.setmPosition(mTitleHistorySubscript);
                myTitleAdapter.setmTextColor(true);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
            }

            @Override
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                //点击
                LogUtil.e("__收藏点击",position+"______"+parent);
            }
        });

        //我的优惠卷
//       vRecCollectionList.setOnItemListener();
    }

    @Override
    public View getFirstView() {
        return null;
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_titile_me_fragment_but_return:
                getActivity().finish();
                break;
            case R.id.view_titile_me_fragment_but_empty:
                emptyListData();
                break;
            case R.id.view_data_null_go_button:
                IntentsUtlis.startExplore(getActivity());
                break;
        }
    }

    private void emptyListData() {
        XHttpUtils.getHttpEmpty(new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("my_emptyListData_json"+json);
                getMyCollectionTitle();
                getMyCollection(0,1,0);
                mTitleHistorySubscript=0;
            }

            @Override
            public void onFailed(String  json) {

            }
        });
    }


    @Override
    public boolean onKeyDownFm(int keyCode) {
        if (myCollectionAdapter.getItemCount()<1){
            vRecCollectionListTitle.setSelection(mTitleHistorySubscript);
            vRecCollectionList.setFocusable(false);
            vDataNullGoButton.setFocusable(true);
            vRecCollectionListTitle.setNextFocusDownId(R.id.view_data_null_go_button);

        }else {
            vRecCollectionList.setSelection(0);
        }
        return super.onKeyDownFm(keyCode);
    }
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (UserManager.isSignTokenNULL()==false){
//            vLayutDataNull.setVisibility(View.VISIBLE);
//        }
//    }
}
