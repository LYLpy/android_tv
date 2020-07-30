package com.geling.view.gelingtv_XX_tongbu.ui.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MeButtonBean;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MeButtonAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.CouponFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.CustomerServiceCenterFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.MyCollectionFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.MyCouponFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.MyOrderFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.PersonalInformationFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.me.PlayRecordFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ViewUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MeActivity extends BaseActivity {
    @BindView(R.id.view_activity_me_fragment)
    FrameLayout view_activity_me_fragment;
    @BindView(R.id.activity_me_rec_but_list)
    TvRecyclerView vRecButtonLista;
    @BindView(R.id.activity_me_app_img)
    ImageView vImgApp;
    @BindView(R.id.activity_me_wx_img)
    ImageView vImgWx;
    public static TvRecyclerView vRecButtonList ;
    public static int sCurrentSubscript=0;//子页面获取下标
    private MeButtonAdapter meButtonAdapter;
    private List<MeButtonBean> listBut = new ArrayList<>();

    private FragmentTransaction fragmentTransaction;
    private FragmentManager mFragmentManager;//fragment管理
    private List<BaseFragment> mFragmentList = new ArrayList<>();//fragment 集合


    @Override
    protected void initData() {
        //listButton 添加 数据
        listBut.add(0, new MeButtonBean(R.drawable.icon_personal_information,"个人信息"));
        listBut.add(1, new MeButtonBean(R.drawable.icon_my_order,"我的订单"));
        listBut.add(2, new MeButtonBean(R.drawable.icon_my_collection,"我的收藏"));
        listBut.add(3, new MeButtonBean(R.drawable.icon_historical_record,"历史记录"));
        listBut.add(4, new MeButtonBean(R.drawable.icon_coupon,"优惠券"));
        listBut.add(5, new MeButtonBean(R.drawable.icon_exchange_code,"兑换码"));
        listBut.add(6, new MeButtonBean(R.drawable.icon_customer_service_center,"客服中心"));
        //给 fragment list 添加 fragment
        mFragmentList.add(new PersonalInformationFragment());//个人信息
        mFragmentList.add(new MyOrderFragment());//我的订单
        mFragmentList.add(new MyCollectionFragment());//我的收藏
        mFragmentList.add(new PlayRecordFragment());//播放记录
        mFragmentList.add(new MyCouponFragment());//优惠卷
        mFragmentList.add(new CouponFragment());//兑换优惠卷
        mFragmentList.add(new CustomerServiceCenterFragment());//客服中心
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_me;
    }

    @Override
    protected void initView() {

        vRecButtonList = vRecButtonLista;
        //初始化fragment管理
        mFragmentManager= getFragmentManager();
        int newPlayReocrd = getIntent().getIntExtra("newPlayReocrd",0);
        //初始化进入页面的第一个焦点
        vRecButtonLista.setSelection(newPlayReocrd);
        //展示第一个fragment页面
        getMeFragment(newPlayReocrd);
        //是否需要登录
        UserManager.isSignTokenIntentActivity(this,1);

        //初始化 点击列表
        meButtonAdapter = new MeButtonAdapter(listBut);
        ViewUtils.setRecyclerViewAdapter(this,meButtonAdapter,vRecButtonList, LinearLayoutManager.VERTICAL);
        //初始化点击事件
        initClick();
        //图片
//        GroceryStoreUtils.GlideImag(this, MainApp.getInstance().getOhterModel().getData().getCustomer_service_code(),vImgWx);
        GroceryStoreUtils.GlideImag(this,MainApp.getInstance().getOhterModel().getData().getApp_code(),vImgApp);
    }

    private void initClick() {
        vRecButtonLista.setOnItemListener(new TvRecyclerView.OnItemListener() {
            @Override
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                meButtonAdapter.setmPosition(sCurrentSubscript);
                meButtonAdapter.notifyItemChanged(sCurrentSubscript);
            }

            @Override
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                if(!isDestroyed()){
                sCurrentSubscript =position;
                getMeFragment(position);
                meButtonAdapter.setmPosition(sCurrentSubscript);
                meButtonAdapter.notifyItemChanged(sCurrentSubscript);
                }
            }

            @Override
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {

            }
        });


//焦点移动边界监听
        vRecButtonLista.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                switch (direction) {
                    case View.FOCUS_RIGHT:
                        fragment1.onKeyDownFm(1);
                        break;
                }
                //返回true时,事件将会被拦截由你来控制焦点
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //获取指定的 fragment 页面
    private void getMeFragment(int position){
        switch (position){
            case 0:
                //个人信息
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,0);
                break;
            case 1:
                //我的订单
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,1);
                break;
            case 2:
                //我的收藏
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,2);
                break;
            case 3:
                //播放记录
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,3);
                break;
            case 4:
                //优惠卷
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,4);
                break;
            case 5:
                //兑换码
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,5);
                break;
            case 6:
                //客服中心
                getFragmentLayout(mFragmentList,R.id.view_activity_me_fragment,6);
                break;
        }
    }
    BaseFragment fragment1;
    private void getFragmentLayout(List<BaseFragment> fragmentlist, int viewID, int pos){
         fragmentTransaction = mFragmentManager.beginTransaction();
        fragment1 = fragmentlist.get(pos);
        for (int i =0;i<fragmentlist.size();i++){
            if (fragmentlist.get(i) != null) {
                fragmentTransaction.hide(fragmentlist.get(i));
            }
        }
        if (!fragment1.isAdded()) {
            // 如果MessageFragment为空，则创建一个并添加到界面上
            fragmentTransaction.add(viewID, fragment1);
            fragmentTransaction.show(fragment1);
        }else {
            //如果MessageFragment不为空，则直接将它显示出来
            fragmentTransaction.show(fragment1);
        }
            fragmentTransaction.commit();

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("wdadawda","onStop");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ( fragment1.onKeyDownFm(event.getKeyCode())==true){
//            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
//            }
//        }
        return super.onKeyDown(keyCode, event);
    }
}
