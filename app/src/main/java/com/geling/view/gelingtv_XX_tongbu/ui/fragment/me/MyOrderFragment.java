package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionTitleBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyOrderBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MeActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis.IntentsUtlis;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyCollectionTitleAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyOrderAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ViewUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/***
 * 我的订单
 *
 * **/
public class MyOrderFragment extends BaseFragment {
    private int mOrderTypeAll =99;
    private int mOrderTypeNoPayment =0;
    private int mOrderTypePayment =1;
    private int mOrderTypeInvalid =2;
    @BindView(R.id.view_titile_me_fragment_text_name)
    TextView vTextTitle;
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.fragment_data_empty_bg)
    LinearLayout vLayutDataNull;
    @BindView(R.id.view_data_null_go_button)
    LinearLayout vDataNullGoButton;

    @BindView(R.id.fragment_my_order_re_list)
    TvRecyclerView vRecOrderList;
    @BindView(R.id.fragment_my_order_list_title)
    TvRecyclerView vRecOrderTitleList;
    private int page =1;
    private  int totalPage ;
    private int subject=0;
    private int mTitleHistorySubscript=0;

    private MyOrderAdapter myOrderAdapter;
    private MyOrderBean myOrderBean;
    private List<MyOrderBean.DataBeanX.DataBean> myOrderListData = new ArrayList<>();

    private MyCollectionTitleAdapter myTitleAdapter;
    private List<MyCollectionTitleBean.DataBean> listTitle = new ArrayList<>();

    @Override
    protected void initData() {
     fragmentData();
    }

    private void fragmentData() {
        //标题 数据
        listTitle.add(0,new MyCollectionTitleBean.DataBean(mOrderTypeAll,"全部"));
        listTitle.add(1,new MyCollectionTitleBean.DataBean(mOrderTypePayment,"已付款"));
        listTitle.add(2,new MyCollectionTitleBean.DataBean(mOrderTypeNoPayment,"未付款"));
        //列表数据
        getMyOrder(mOrderTypeAll,1,0);
    }

    private void getMyOrder(int status,int page,int update){
        //        订单状态0待付款，1已付款，2订单失效
        XHttpUtils.getHttpMyOrderJson(status, page, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("myOrederFragment_Json"+json);
                myOrderBean = MyOrderBean.gsonBean(json);
                myOrderListData =myOrderBean.getData().getData();
                if (myOrderListData.size()<1){
                    vLayutDataNull.setVisibility(View.VISIBLE);
                    vRecOrderList.setVisibility(View.GONE);
                }else {
                    vLayutDataNull.setVisibility(View.GONE);
                    vRecOrderList.setVisibility(View.VISIBLE);
                }
                if (update ==0){
                    totalPage= myOrderBean.getData().getLast_page();
                    myOrderAdapter.setNewData(myOrderListData);
                }else {
                    myOrderAdapter.addData(myOrderListData);
                }
            }
            @Override
            public void onFailed(String  json) {
                vLayutDataNull.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_order;
    }

    @Override
    protected void initView(View view) {
        //标题
        myTitleAdapter = new MyCollectionTitleAdapter(listTitle);
        ViewUtils.setRecyclerViewAdapter(getActivity(),myTitleAdapter,vRecOrderTitleList, LinearLayoutManager.HORIZONTAL);
        //列表
        myOrderAdapter = new MyOrderAdapter(myOrderListData);
        myOrderAdapter.setHasStableIds(true);
//        vRecOrderList.setItemAnimator(null);
        ViewUtils.setReViewGridAdapter(getActivity(),myOrderAdapter,vRecOrderList,2);
        //标题
        vTextTitle.setText("我的订单");
        //初始化点击事件

        vButtonReturn.setOnClickListener(this);
        vDataNullGoButton.setOnClickListener(this);
        //初始化适配器事件功能
        initAdapterListener();
        //焦点处理
        initViewListener();
    }
    private void initViewListener() {
        //返回按钮
        vButtonReturn.setOnKeyListener(new View.OnKeyListener() {
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
    }

    private void initAdapterListener() {

        vRecOrderList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                switch (direction) {
                    case View.FOCUS_DOWN:
                        if (page<totalPage){
                            getMyOrder(subject,++page,0);
                            vRecOrderList.setSelection(0);
                        }
                        break;
                    case View.FOCUS_UP:
                        if (page>1) {
                            getMyOrder(subject,--page,0);
                            vRecOrderList.setSelection(0);
                        }else {
                            vRecOrderTitleList.setSelection(mTitleHistorySubscript);
                        }
                        break;
                    case View.FOCUS_LEFT:
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;
                    case View.FOCUS_RIGHT:
                        break;
                }
                return false;
            }
        });
        vRecOrderTitleList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                myTitleAdapter.setmTextColor(false);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
                switch (direction) {
                    case View.FOCUS_DOWN:
                            vRecOrderList.setSelection(0);
                        break;
                    case View.FOCUS_LEFT:
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;
                }
                return false;
            }
        });
        //item选中、点击监听
        vRecOrderTitleList.setOnItemListener(new TvRecyclerView.OnItemListener() {
            @Override
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                //上次选中
                myTitleAdapter.setmPosition(mTitleHistorySubscript);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
            }

            @Override
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                //当前选中
                LogUtil.e("___sssssssssss选中");
                page=1;
                mTitleHistorySubscript=position;
                subject = listTitle.get(mTitleHistorySubscript).getId();
                getMyOrder(subject,1,0);
                myTitleAdapter.setmPosition(mTitleHistorySubscript);
                myTitleAdapter.setmTextColor(true);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
            }

            @Override
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                //点击
                LogUtil.e("___ssssssssss点击");
            }
        });

        vRecOrderList.setOnItemListener(new TvRecyclerView.OnItemListener() {
            @Override
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {

            }

            @Override
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                LogUtil.e("___sssss选中");
            }

            @Override
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                LogUtil.e("___sssss点击");
            }
        });


//        vRecOrderList.setOnClickListener(new TvRecyclerView.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtil.e("___ssssssssss点击");
//            }
//        });
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
            case R.id.view_data_null_go_button:
                IntentsUtlis.startExplore(getActivity());
                break;
        }
    }



    @Override
    public boolean onKeyDownFm(int keyCode) {
        if (myOrderAdapter.getItemCount()<1){
            vRecOrderTitleList.setSelection(mTitleHistorySubscript);
        }else {
            vRecOrderList.setSelection(0);
        }
        return super.onKeyDownFm(keyCode);
    }

    /**
     * 判断是否是初始化Fragment
     */
    private boolean mIsHasStarted = false;
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (UserManager.isSignTokenNULL()==false){
//            vLayutDataNull.setVisibility(View.VISIBLE);
//        }
//    }
}
