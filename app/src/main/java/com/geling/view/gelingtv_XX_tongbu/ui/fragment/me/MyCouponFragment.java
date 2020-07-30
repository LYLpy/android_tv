package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionTitleBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCouponBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MeActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis.IntentsUtlis;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyCollectionTitleAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyCouponAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ViewUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * 我的优惠卷
 * */
public class MyCouponFragment extends BaseFragment {
    @BindView(R.id.view_titile_me_fragment_text_name)
    TextView vTextTitle;
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.fragment_my_coupon_re_list_title)
    TvRecyclerView vRecCouponListTitle;
    @BindView(R.id.fragment_my_coupon_re_list)
    TvRecyclerView vRecCouponList;
    @BindView(R.id.fragment_data_empty_bg)
    LinearLayout vLayutDataNull;
    @BindView(R.id.view_data_null_go_button)
    LinearLayout vDataNullGoButton;

    private MyCouponAdapter myCouponAdapter;
    private List<MyCouponBean.DataBeanX.AppGiftListBean.DataBean> listMyCoupon = new ArrayList<>();

    private MyCollectionTitleAdapter myTitleAdapter;
    private List<MyCollectionTitleBean.DataBean> listTitle = new ArrayList<>();

    private int mTitleHistorySubscript=0;
    private int page =1;
    private int totalPage ;
    private int couponStatus=1;
    @Override
    protected void initData() {
        //标题 数据
//        优惠券状态 -1 表示已过期 0 表示已使用 1 表示未使用
        listTitle.add(0,new MyCollectionTitleBean.DataBean(1,"未使用"));
        listTitle.add(1,new MyCollectionTitleBean.DataBean(0,"已使用"));
        listTitle.add(2,new MyCollectionTitleBean.DataBean(-1,"已过期"));
        //列表数据
        getMyCoupon(couponStatus,1,0);

    }
    private void getMyCoupon(int couponStatus,int page,int update) {
        XHttpUtils.getHttpMyCouponJson(couponStatus, page, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("my_couponStatus_json"+json);
                MyCouponBean myCouponBean =MyCouponBean.gsonBean(json);
                listMyCoupon =myCouponBean.getData().getAppGiftList().getData();
                if (listMyCoupon.size()<1){
                    vLayutDataNull.setVisibility(View.VISIBLE);
                    vRecCouponList.setVisibility(View.GONE);
                }else {
                    vLayutDataNull.setVisibility(View.GONE);
                    vRecCouponList.setVisibility(View.VISIBLE);
                }
                if (update ==0){
                    totalPage = myCouponBean.getData().getAppGiftList().getLast_page();
                    myCouponAdapter.setNewData(listMyCoupon);
                }else {
                    myCouponAdapter.addData(listMyCoupon);
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
        return R.layout.fragment_my_coupon;
    }

    @Override
    protected void initView(View view) {
        //标题
        myTitleAdapter = new MyCollectionTitleAdapter(listTitle);
        ViewUtils.setRecyclerViewAdapter(getActivity(),myTitleAdapter,vRecCouponListTitle, LinearLayoutManager.HORIZONTAL);
        //列表
        myCouponAdapter = new MyCouponAdapter(listMyCoupon);
        myCouponAdapter.setHasStableIds(true);
        vRecCouponList.setItemAnimator(null);
        ViewUtils.setReViewGridAdapter(getActivity(),myCouponAdapter,vRecCouponList,2);
        //标题
        vTextTitle.setText("我的优惠券");
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
               //焦点移动边界监听
        vRecCouponList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                switch (direction) {
                    case View.FOCUS_DOWN:
                            if (page<totalPage){
                                getMyCoupon(couponStatus,++page,0);
                                vRecCouponList.setSelection(0);
                            }
                        break;
                    case View.FOCUS_UP:
                        if (page>1) {
                            getMyCoupon(couponStatus, --page, 0);
                            vRecCouponList.setSelection(0);
                        }else {
                            vRecCouponListTitle.setSelection(mTitleHistorySubscript);
                        }
                        break;
                    case View.FOCUS_LEFT:
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;
                    case View.FOCUS_RIGHT:

                        break;
                }
                //返回true时,事件将会被拦截由你来控制焦点
                return false;
            }
        });
        //标题
        vRecCouponListTitle.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                myTitleAdapter.setmTextColor(false);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
                switch (direction) {
                    case View.FOCUS_DOWN:
                        vRecCouponList.setSelection(0);
                        break;
                    case View.FOCUS_LEFT:
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;
                }

                return false;
            }
        });



        //item选中、点击监听
        vRecCouponListTitle.setOnItemListener(new TvRecyclerView.OnItemListener() {
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
                couponStatus = listTitle.get(mTitleHistorySubscript).getId();
                getMyCoupon(couponStatus,1,0);
                myTitleAdapter.setmPosition(mTitleHistorySubscript);
                myTitleAdapter.setmTextColor(true);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
            }

            @Override
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                //点击
            }
        });
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
        if (myCouponAdapter.getItemCount()<1){
            vRecCouponListTitle.setSelection(mTitleHistorySubscript);
        }else {
            vRecCouponList.setSelection(0);
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
