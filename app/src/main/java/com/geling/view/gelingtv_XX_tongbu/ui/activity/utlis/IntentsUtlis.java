package com.geling.view.gelingtv_XX_tongbu.ui.activity.utlis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dangbei.dangbeipaysdknew.DangBeiPayActivity;
import com.dangbei.dangbeipaysdknew.DangBeiPayManager;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MainActivity3;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.MeActivity;
import com.geling.view.gelingtv_XX_tongbu.ui.activity.SignInActivity;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;


/**
 *跳转类
 */
public class IntentsUtlis {

    //tapy  1=我的页面 2=播放页面 3=vip页面\我的历史

    //登录页面
    public static void starSignInActivity(Context activity, int name) {
        LogUtil.e("__________name",name+"");
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.putExtra("name", name);
        activity.startActivity(intent);
    }
    //我的页面
    public static void starMeActivity(Activity activity){
        Intent intent = new Intent(activity, MeActivity.class);
        activity.startActivity(intent);
    }
    // 关闭当前页  去往 探索页面
    public static void startExplore(Activity activity){
        Intent intent = new Intent(activity, MainActivity3.class);
        activity.startActivity(intent);
        activity.finish();
    }
    //调用当贝sdk支付
    public static void DBshowSDK(Activity activity,String Pprice,String mPayPrice,String order_on,String isContract){

        Intent  intent = new Intent(activity, DangBeiPayActivity.class);
        intent.putExtra("PID","452");//商品id，最大长度为40个字符，
        intent.putExtra("Pname",mPayPrice); //商品名称，最大长度为60个字符，必填
        intent.putExtra("Pprice",Pprice); //商品价格，必填
        intent.putExtra("Pdesc","ssssssssssssss"); //商品描述，最大长度为60个字符，必填
        intent.putExtra("Pchannel","DB_znds_pay"); //渠道号
        intent.putExtra("order",order_on); //order为订单号，可选
        intent.putExtra("extra",order_on+"TV"); //extra为备用字段，可选
        intent.putExtra("isContract", isContract);//是否自动续费，可选 1表示自动续费，其他表示不是
        activity.startActivityForResult(intent,0);
    }
}
