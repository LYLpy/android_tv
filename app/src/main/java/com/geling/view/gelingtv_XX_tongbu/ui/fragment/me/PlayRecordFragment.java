package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.PlayReocrdModel;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MainActivity3;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.RvAdapterPlayReocrd;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 *播放记录的Fragment
 * */
public class PlayRecordFragment extends BaseFragment implements View.OnFocusChangeListener {

    @BindView(R.id.view_titile_me_fragment_text_name)
    TextView vTextTitle;
    @BindView(R.id.image_blue_bottom)
    ImageView imageBlueBottom;
    @BindView(R.id.ll_btn_blue_bottom)
    LinearLayout llBtnBlueBottom;
    @BindView(R.id.btn_not_data)
    Button btnNotData;
    //    @BindView(R.id.tv_back)
//    TextView tvBack;
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.SV)
    ScrollView dataSV;
    @BindView(R.id.rm)
    RecyclerView tvRecyclerView;
    int mCurrentFocusPosition;
    @BindView(R.id.tv_not_data)
    TextView tvNotData;
    @BindView(R.id.ll_not_data)
    LinearLayout llNotData;
    private RvAdapterPlayReocrd mAdapter;
    private PlayReocrdModel mData = new PlayReocrdModel();
    private List<PlayReocrdModel.DataBeanX.DataBean> item = new ArrayList<>();
    private int limit = 10000;
    private int page = 1;
    private String timestamp;
    Unbinder unbinder;
    private Intent intent;
    private String mjson;

    @Override
    protected void initData() {

        timestamp = String.valueOf(System.currentTimeMillis());
        TreeMap<String, String> treeMap = new TreeMap<>();

        treeMap.put("timestamp", timestamp);
        treeMap.put("limit", String.valueOf(limit));
        treeMap.put("page", String.valueOf(page));
        XHttpUtils.getHttpMyClassList(0, treeMap, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.e("__MyClassList", json);
//
                mjson = json;
                LogUtil.e("__mjson",mjson);
               mData = PlayReocrdModel.gsonBean(json);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                tvRecyclerView.setLayoutManager(manager);
                mAdapter = new RvAdapterPlayReocrd(mData, getActivity());
                if (mAdapter.getItemCount()==0){
                    dataSV.setVisibility(View.GONE);
                    llNotData.setVisibility(View.VISIBLE);
                }else {
                    dataSV.setVisibility(View.VISIBLE);
                    llNotData.setVisibility(View.GONE);
                    mAdapter.setOnItemStateChangeListener(new RvAdapterPlayReocrd.OnItemStateChangeListener() {
                        @Override
                        public void onItemClick(String courseId) {
                            isCourseForbidden(courseId,null);

                        }
                    });
                    tvRecyclerView.setAdapter(mAdapter);
                }


            }
            @Override
            public void onFailed(String  json) {
//                LogUtil.e("__MyClassList","__onFailed");
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_play_reocrd;
    }

    @Override
    protected void initView(View view) {
        btnNotData.setOnFocusChangeListener(this);
        btnNotData.setOnClickListener(this);
        vButtonReturn.setOnClickListener(this);
        vButtonReturn.setOnFocusChangeListener(this);
        imageBlueBottom.setOnFocusChangeListener(this);
        imageBlueBottom.setOnClickListener(this);
        //标题
        vTextTitle.setText("历史记录");
    }

    @Override
    public View getFirstView() {
        return null;
    }

    @Override
    public void setData(Object data) {


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_not_data:
                intent = new Intent(getActivity(), MainActivity3.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.view_titile_me_fragment_but_return:
                intent = new Intent(getActivity(), MainActivity3.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;

            case R.id.image_blue_bottom:
                page++;
                timestamp = String.valueOf(System.currentTimeMillis());
                TreeMap<String, String> treeMap = new TreeMap<>();
                treeMap.put("timestamp", timestamp);
                treeMap.put("limit", String.valueOf(limit));
                treeMap.put("page", String.valueOf(page));
                XHttpUtils.getHttpMyClassList(0, treeMap, new HttpCallback() {
                    @Override
                    public void onSuccess(String json) {
                        if (mjson == json) {
                            tvNotData.setVisibility(View.VISIBLE);
                            imageBlueBottom.setVisibility(View.GONE);
                        } else {
//                            tvNotData.setVisibility(View.VISIBLE);
//                            imageBlueBottom.setVisibility(View.GONE);
                            mjson = json;
                            mData = PlayReocrdModel.gsonBean(json);
                            List lis = mData.getData().getData();
                            LogUtil.e("___lis",lis+"");
                            if (mData.getData().getData().size() !=0 ) {
                                for (int i = 0; i <=mData.getData().getData().size() ; i++) {
                                    item.add(mData.getData().getData().get(i));
                                }
                                //setData(mData.getData());
                                tvRecyclerView.setVisibility(View.VISIBLE);
                                GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                tvRecyclerView.setLayoutManager(manager);
                                mAdapter = new RvAdapterPlayReocrd(mData, getActivity());
                                tvRecyclerView.setAdapter(mAdapter);
                            }else{
                                imageBlueBottom.setVisibility(View.GONE);
                                tvNotData.setVisibility(View.VISIBLE);
                                tvNotData.setFocusable(true);
                                tvNotData.setNextFocusUpId(R.id.rv);
                            }
                        }
                    }
                    @Override
                    public void onFailed(String  json) {

                    }
                });

                break;
            default:
                break;
        }
    }





    @Override
    public boolean onKeyDownFm(int keyCode) {
        if (mData.getData().getData().equals("") || mData.getData().getData() == null) {
            LogUtil.e("__onKeyDownFm_null",keyCode + "");
            btnNotData.setFocusable(true);
            tvRecyclerView.setFocusable(false);
            btnNotData.setNextFocusUpId(R.id.tv_back);
            vButtonReturn.setNextFocusDownId(R.id.view_titile_me_fragment_but_return);
        } else {
            btnNotData.setFocusable(false);
//            tvRecyclerView.setFocusable(true);
            LogUtil.e("__onKeyDownFm",keyCode + "");
        }
        return super.onKeyDownFm(keyCode);
    }

    /**
     * Called when the focus state of a view has changed.
     *
     * @param v        The view whose state has changed.
     * @param hasFocus The new focus state of v.
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.tv_back:
                    ScaleUtil.scale(vButtonReturn, (float) 1.08);
//                    tvBack.setTextColor(getResources().getColor(R.color.black_gold));
                    break;

                default:
                    ScaleUtil.scale(v, (float) 1.2);
                    break;
            }
        } else {
            ScaleUtil.scale(v,  1);
        }

    }

}
