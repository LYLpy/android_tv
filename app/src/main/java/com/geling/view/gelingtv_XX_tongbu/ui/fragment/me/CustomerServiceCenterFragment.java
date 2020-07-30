package com.geling.view.gelingtv_XX_tongbu.ui.fragment.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.FreedbackModel;
import com.geling.view.gelingtv_XX_tongbu.model.http.HttpCallback;
import com.geling.view.gelingtv_XX_tongbu.model.http.XHttpUtils;
import com.geling.view.gelingtv_XX_tongbu.ui.fragment.BaseFragment;
import com.geling.view.gelingtv_XX_tongbu.ui.view.ScaleUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import org.json.HTTP;

import butterknife.BindView;

public class CustomerServiceCenterFragment extends BaseFragment {
    @BindView(R.id.view_titile_me_fragment_text_name)
    TextView vTextTitle;
    @BindView(R.id.view_titile_me_fragment_but_return)
    LinearLayout vButtonReturn;
    @BindView(R.id.fragment_customer_service_center_wx_img)
    ImageView vImgWx;
    @BindView(R.id.fragment_customer_service_center_web_img)
    ImageView vImgWeb;




    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_customer_service_center;
    }

    @Override
    protected void initView(View view) {
        //标题
        vTextTitle.setText("客服中心");
        //初始化点击事件
        vButtonReturn.setOnClickListener(this);
        //图片
//        vImgWx.setscr(R.drawable.wx_gl);
        XHttpUtils.getFeedBackUrl(0, new HttpCallback() {
            @Override
            public void onSuccess(String json) {
                LogUtil.e("__json获取客服反馈二维码",json);
                FreedbackModel freedbackModel = new FreedbackModel().gsonBean(json);

                GroceryStoreUtils.GlideImag(getActivity(),freedbackModel.getData().getQrcodeUrl(),vImgWeb);
                //        GroceryStoreUtils.GlideImag(getActivity(),MainApp.getInstance().getOhterModel().getData().getCustomer_service_code(),vImgWx);
            }

            @Override
            public void onFailed(String json) {
                LogUtil.e("__json获取客服反馈二维码失败",json);
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
        }
    }
}
