package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.ExchangeBean;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MyCollectionTitleBean;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.DialogActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MeActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis.IntentsUtlis;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.ExchangeAdaoter;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.MyCollectionTitleAdapter;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ViewUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *   兑换码页面
 *
 * */
public class CouponFragment extends BaseFragment {
    @BindView(R.id.view_titile_me_fragment_text_name)
    TextView vTextTitle;
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.fragment_my_volume_re_list)
    TvRecyclerView vRecVolumeList;
    @BindView(R.id.frm_coupon_code_layout)
    RelativeLayout vRelFrmCouponCodeLayout;
    @BindView(R.id.fragment_exchange_et_button)
    LinearLayout vButtonEtexchange;
    @BindView(R.id.fragment_exchange_et)
    EditText vEtexchange;
    @BindView(R.id.fragment_data_empty_bg)
    LinearLayout vLayutDataNull;
    @BindView(R.id.fragment_my_order_list_title)
    TvRecyclerView vRecTitleList;
    @BindView(R.id.frm_coupon_code_imag)
    ImageView vImgCode;
    @BindView(R.id.view_data_null_go_button)
    LinearLayout vDataNullGoButton;

    private ExchangeAdaoter exchangeAdaoter;
    private List<ExchangeBean.DataBeanX.AppExchangeListBean.DataBean> listExchange = new ArrayList<>();

    private MyCollectionTitleAdapter myTitleAdapter;
    private List<MyCollectionTitleBean.DataBean> listTitle = new ArrayList<>();

    private int page =1;
    private  int totalPage ;
    private int mTitleHistorySubscript=0;
    private int subject=0;

    @Override
    protected void initData() {
        //标题 数据
        listTitle.add(0,new MyCollectionTitleBean.DataBean(0,"扫码兑换"));
        listTitle.add(1,new MyCollectionTitleBean.DataBean(1,"兑换记录"));


        XHttpUtils.getHttpCodeImagJson(new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                Log.i("json_x",json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String data= jsonObject.getString("data");
                    JSONObject jsonObject2 = new JSONObject(data);
                    String imgUrl = jsonObject2.getString("qrcodeUrl");
                    GroceryStoreUtils.GlideImag(getActivity(),imgUrl,vImgCode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String  json) {

            }
        });

    }
    private void getVolumeData(int page,int update) {
        XHttpUtils.getHttpExchangeJson(page, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("my_Volume_json"+json);
                ExchangeBean exchangeBean =ExchangeBean.gsonBean(json);
                listExchange =exchangeBean.getData().getAppExchangeList().getData();
                if (listExchange.size()<1){
                    vLayutDataNull.setVisibility(View.VISIBLE);
                    vRecVolumeList.setVisibility(View.GONE);
                }else {
                    vRecVolumeList.setVisibility(View.VISIBLE);
                    vLayutDataNull.setVisibility(View.GONE);
                }
                if (update ==0){
                    totalPage = exchangeBean.getData().getAppExchangeList().getLast_page();
                    exchangeAdaoter.setNewData(listExchange);
                }else {
                    exchangeAdaoter.addData(listExchange);
                }
            }

            @Override
            public void onFailed(String  json) {
                vLayutDataNull.setVisibility(View.VISIBLE);
            }
        });
    }

    private void SubmitExchangeCode(String code){
        XHttpUtils.postSubmitExchangeCodeJson( code, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.d("my_SubmitExchangeCode_json"+json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String name = jsonObject.getJSONObject("data").getString("name");
                    page=1;
                    getVolumeData(page,0);
                    DialogActivity.starExchangeDialogActivity(getActivity(),1,name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String  json) {
                Toast.makeText(getActivity(), "兑换码输入错误或已被使用！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initView(View view) {
        //标题
        myTitleAdapter = new MyCollectionTitleAdapter(listTitle);
        ViewUtils.setRecyclerViewAdapter(getActivity(),myTitleAdapter,vRecTitleList, LinearLayoutManager.HORIZONTAL);
        // 收藏列表
        exchangeAdaoter = new ExchangeAdaoter(listExchange);
        exchangeAdaoter.setHasStableIds(true);
        vRecVolumeList.setItemAnimator(null);
//        vRecVolumeList.setAdapter(exchangeAdaoter);
        ViewUtils.setReViewGridAdapter(getActivity(),exchangeAdaoter,vRecVolumeList,2);
        //标题
        vTextTitle.setText("兑换码");
        //初始化点击事件
        vButtonReturn.setOnClickListener(this);
        vButtonEtexchange.setOnClickListener(this);
        vDataNullGoButton.setOnClickListener(this);
        //初始化适配器事件功能
        initAdapterListener();
        //处理事件
        initViewLinstener();
    }

    private void initViewLinstener() {
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
        vRecVolumeList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                switch (direction) {
                    case View.FOCUS_DOWN:
                        if (page<totalPage){
                            getVolumeData( ++page, 0);
                            vRecVolumeList.setSelection(0);
                        }
                        break;
                    case View.FOCUS_UP:
                        if (page>1) {
                            getVolumeData( --page, 0);
                            vRecVolumeList.setSelection(0);
                        }else {
                            vRecTitleList.setSelection(mTitleHistorySubscript);
//                            vEtexchange.requestFocus();
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


        vRecTitleList.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() {
            @Override
            public boolean onInBorderKeyEvent(int direction, View focused) {
                myTitleAdapter.setmTextColor(false);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
                switch (direction) {
                    case View.FOCUS_DOWN:
                        vRecVolumeList.setSelection(0);
                        break;
                    case View.FOCUS_LEFT:
                        MeActivity.vRecButtonList.setSelection(MeActivity.sCurrentSubscript);
                        break;
                }

                return false;
            }
        });
        //item选中、点击监听
        vRecTitleList.setOnItemListener(new TvRecyclerView.OnItemListener() {
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
                myTitleAdapter.setmPosition(mTitleHistorySubscript);
                myTitleAdapter.setmTextColor(true);
                myTitleAdapter.notifyItemChanged(mTitleHistorySubscript);
                if (subject==0){
                    vRecVolumeList.setVisibility(View.GONE);
                    vLayutDataNull.setVisibility(View.GONE);
                    vRelFrmCouponCodeLayout.setVisibility(View.VISIBLE);
                }else {
                    //列表
                    getVolumeData(1,0);
                    vRecVolumeList.setVisibility(View.VISIBLE);
                    vRelFrmCouponCodeLayout.setVisibility(View.GONE);
                }
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
            case R.id.fragment_exchange_et_button://确定兑换码
                SubmitExchangeCode(vEtexchange.getText().toString());
//                DialogActivity.starExchangeDialogActivity(getActivity(),1,"优惠金额为666元");

                break;
            case R.id.view_data_null_go_button:
                IntentsUtlis.startExplore(getActivity());
                break;
        }
    }

    @Override
    public boolean onKeyDownFm(int keyCode) {
//        vEtexchange.requestFocus();
//        vEtexchange.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
