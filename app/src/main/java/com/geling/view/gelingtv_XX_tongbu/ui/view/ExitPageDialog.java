package com.geling.view.gelingtv_XX_tongbu.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geling.view.gelingtv_XX_tongbu.MainApp;
import com.geling.view.gelingtv_XX_tongbu.R;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SearchModelPy;
import com.geling.view.gelingtv_XX_tongbu.ui.adapter.ExitPagerAdapter;
import com.geling.view.gelingtv_XX_tongbu.utils.GroceryStoreUtils;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/19------更新------
 * 退出页面dialog
 */

public class ExitPageDialog {

    private Context mContext;
    private Dialog dialog;
    private TextView mTvExit;
    private TextView mTvSeeAgain;
    private ViewPager mVp;
    private ExitPagerAdapter mPagerAdapter;
//    private ImageView mIv1;
//    private ImageView mIv2;
//    private ImageView mIv3;
//    private ImageView mIv4;
//    private ImageView mIvPageUp;
//    private ImageView mIvPageDown;
//    private List<ExitPageModel.Databean.PageDatabean> mData;
//    private List<List<ExitPageModel.Databean.PageDatabean>> mData;
    private List<List<SearchModelPy.DatabeanX.Databean>> mData;
    private List<LinearLayout> mPagerViews = new ArrayList<>();
    private LayoutInflater mInflater;
    public ExitPageDialog builder(Context context,List<List<SearchModelPy.DatabeanX.Databean>> data) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_exit_page, null);
        mTvExit = view.findViewById(R.id.tv_exit);
        mTvSeeAgain = view.findViewById(R.id.tv_see_again);
        mVp = view.findViewById(R.id.vp);
//        mIv1 = view.findViewById(R.id.iv_1);
//        mIv2 = view.findViewById(R.id.iv_2);
//        mIv3 = view.findViewById(R.id.iv_3);
//        mIv4 = view.findViewById(R.id.iv_4);
//        mIvPageUp = view.findViewById(R.id.iv_page_up);
//        mIvPageDown = view.findViewById(R.id.iv_page_down);

        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onExitClick();
                }
                cancle();
            }
        });
        mTvSeeAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onSeeAgainClick();
                }
                cancle();
            }
        });
        initVp();

        dialog = new Dialog(mContext,R.style.sytle_my_dialog);

//        //设置alterdialog全屏
        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width,  height);

        dialog.setContentView(view, layoutParams);
//        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        return this;
    }
    String imgUrl = MainApp.imgUrl;
    /**
     * 初始化ViewPager
     */
    private void initVp() {
        LinearLayout ll;
        ImageView iv1;
        ImageView iv2;
        ImageView iv3;
        ImageView iv4;
        for (int i = 0; i < mData.size(); i++) {
            ll = (LinearLayout) mInflater.inflate(R.layout.layout_exit_viewpager,null);
            iv1 = ll.findViewById(R.id.iv_1);
            iv2 = ll.findViewById(R.id.iv_2);
            iv3 = ll.findViewById(R.id.iv_3);
            iv4 = ll.findViewById(R.id.iv_4);
            List<SearchModelPy.DatabeanX.Databean> model = mData.get(i);

            switch (model.size()){
                case 1:
                    GroceryStoreUtils.GlideImag(mContext,model.get(0).getIcon(),iv1);
                    iv2.setVisibility(View.GONE);
                    iv3.setVisibility(View.GONE);
                    iv4.setVisibility(View.GONE);
                    break;
                case 2:
                    GroceryStoreUtils.GlideImag(mContext,model.get(0).getIcon(),iv1);
                    GroceryStoreUtils.GlideImag(mContext,model.get(1).getIcon(),iv2);
                    iv3.setVisibility(View.GONE);
                    iv4.setVisibility(View.GONE);
                    break;
                case 3:
                    GroceryStoreUtils.GlideImag(mContext,model.get(0).getIcon(),iv1);
                    GroceryStoreUtils.GlideImag(mContext,model.get(1).getIcon(),iv2);
                    GroceryStoreUtils.GlideImag(mContext,model.get(2).getIcon(),iv3);
                    iv4.setVisibility(View.GONE);
                    break;
                case 4:
                    GroceryStoreUtils.GlideImag(mContext,model.get(0).getIcon(),iv1);
                    GroceryStoreUtils.GlideImag(mContext,model.get(1).getIcon(),iv2);
                    GroceryStoreUtils.GlideImag(mContext,model.get(2).getIcon(),iv3);
                    GroceryStoreUtils.GlideImag(mContext,model.get(3).getIcon(),iv4);
                    break;
                    default:break;
            }
            mPagerViews.add(ll);
        }
        mPagerAdapter = new ExitPagerAdapter(mPagerViews);
        mPagerAdapter.setListener(new ExitPagerAdapter.OnPageItemViewClickListener() {
            @Override
            public void onPageItemViewClick(int positionPage, int positionView) {
                LogUtil.e("__onPageItemViewClick_positionPage",positionPage + "");
                LogUtil.e("__onPageItemViewClick_positionView",positionView + "");
//                LogUtil.e("__onPageItemViewClick","__" + mData.get(positionPage).get(positionView).getCourse_id());
                if (mOnClickListener != null){
                    mOnClickListener.onItemClick(mData.get(positionPage).get(positionView));
                    cancle();
                }
            }
        });
        mVp.setAdapter(mPagerAdapter);
        mVp.setCurrentItem(mData.size() * 30000);//30000是随便写的
    }

    public void show() {
        if(dialog!=null)dialog.show();
        mTvSeeAgain.requestFocus();
    }

    public void cancle() {
        if(dialog!=null)dialog.cancel();
    }



    public Dialog getDialog() {
        return dialog;
    }


    public ExitPageDialog setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    private OnClickListener mOnClickListener;
    public interface OnClickListener{
        void onExitClick();
        void onSeeAgainClick();
        void onItemClick(SearchModelPy.DatabeanX.Databean pageDatabean);
    }



}
