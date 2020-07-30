package com.geling.view.gelingtv_XX_tongbu.model;

import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CollectModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CouponByPriceModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CouponModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.ForbiddenModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.MealModel;
import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.geling.view.gelingtv_XX_tongbu.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import com.geling.view.gelingtv_XX_tongbu.UserManager;
import com.geling.view.gelingtv_XX_tongbu.model.bean.OhterModel1;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.CourseListModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.HttpMsg;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.OrderStatusModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.PayQrCodeModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.QrCodeOrderModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.SearchModelPy;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.UserInfoModel;
import com.geling.view.gelingtv_XX_tongbu.model.bean.pybean.VideoUrlModel;
import com.geling.view.gelingtv_XX_tongbu.utils.CommonUtils;

import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/30------更新------
 * 网络请求协助类
 */

public class HttpHelper extends BaseHelper{

    //记录首页点击
    public static final String RECOMMEND_CLICK_TYPE_POSITION = "position";//点击的是推荐位
    public static final String RECOMMEND_CLICK_TYPE_ORDER = "order";//点击的是轮播图

    //判断是哪个接口
    public static final int HTTP_OTHER = 1;//其他数据接口
    public static final int HTTP_MAIN_NAV = 2;//首页导航栏
    public static final int HTTP_MAIN_MODULE = 3;//首页各个模块数据
    public static final int HTTP_HISTORY_ALL = 4;//历史数据，一次获取全部
    public static final int HTTP_HISTORY_PAGING = 5;//历史数据，分页获取
    public static final int HTTP_MY_COLLECTION = 6;//我的收藏
    public static final int HTTP_COURSE_DETAIL = 7;//课程详情
    public static final int HTTP_SEARCH_HOT = 8;//热门搜索
    public static final int HTTP_SEARCH = 9;//关键词搜索
    public static final int HTTP_EXIT_PAGE = 10;//退出推荐数据
    public static final int HTTP_CLEAR_COLLECTION = 11;//清空收藏
    public static final int HTTP_COLLECTION_TOGGLE = 12;//收藏/


    public static final int HTTP_IS_COURSE_FORBIDDEN = 13;//检查课程是否禁用
    public static final int HTTP_GET_VIDEO_DATA = 14;//获取视频播放数据，比如播放地址等
    public static final int HTTP_GET_QR_CODE = 15;//获取支付二维码
    public static final int HTTP_GET_PRODUCT_PACK_LIST = 16;//获取产品包
    public static final int HTTP_CHECK_ORDER = 17;//检查二维码支付状态
    public static final int HTTP_IS_VIP_OFFLINE = 18;//线下鉴权
    public static final int HTTP_IS_VIP_ONLINE = 19;//线上鉴权
    public static final int HTTP_IS_VIP_OFFLINE_BY_VIDEO_ID = 20;//线下鉴权，带VideoId
    public static final int HTTP_GET_VERIFICATION_CODE  = 21;//获取验证码
    public static final int HTTP_RECODE_USER_VISIT_HOME = 22;//线下鉴权，带VideoId
//    ================================培优TV版==================================
    //支付方式，0微信，1支付宝
    public static final int PY_PAY_TYPE_WECHAT = 0;//微信
    public static final int PY_PAY_TYPE_ALIPAY = 1;//支付宝
    public static final int PY_PAY_TYPE_DANGBEI =100;//当贝
    public static final int PY_WECHAT_CS = 2;//微信客服




    public static final int PY_HTTP_START = 1;//启动页
    public static final int PY_HTTP_GET_COURSE_LIST = 2;//课程列表
    public static final int PY_HTTP_GET_VIDEO_URL = 3;//获取播放地址
    public static final int PY_HTTP_GET_USER_INFO = 4;//用户个人信息
    public static final int PY_HTTP_ADD_COLLECT = 5;//添加收藏
    public static final int PY_HTTP_DEL_COLLECT = 6;//删除收藏
    public static final int PY_HTTP_KEY_SEARCH = 7;//关键词搜索
    public static final int PY_HTTP_HOT_SEARCH = 8;//热门搜索
    public static final int PY_HTTP_GET_MEAL = 9;//产品套餐
    public static final int PY_HTTP_GET_MY_COUPON = 10;//获取我的优惠券
    public static final int PY_HTTP_GET_MY_COUPON_LIST_SORT_BY_PRICE = 11;//根据价格获取我的优惠券
    public static final int PY_HTTP_GET_MAIN_NAV = 12;//主页导航栏数据
    public static final int PY_HTTP_GET_MAIN_MODULE = 13;//主页各个模块数据
    public static final int PY_HTTP_COURSE_FORBIDDEN = 14;//检查课程是否禁用
    public static final int PY_HTTP_GET_PAY_QR_CODE = 15;//获取支付二维码(创建订单号)
    public static final int PY_HTTP_GET_ORDER_STATUS = 16;//获取订单状态
    public static final int PY_HTTP_GET_PERSONAL = 17;//获取个人信息
    public static final int PY_HTTP_GET_QRCODE_BY_URL = 18;//获取个人信息
    /**
     * 记录首页推荐位点击，传到后台，无需处理返回数据
     * @param indexModuleId 首页布局类型
     * @param type 是推荐位还是轮播图
     * @param position 点击的数据在首页的position
     */
    public void recommendClick(String indexModuleId,String type,int position){
        TreeMap<String,String> treeMap = new TreeMap<>();
//        String userId = SysUtil.getSystemProperties(this,SysUtil.SYSKEY_JX_SMART_CARD);
        treeMap.put("indexModuleId",indexModuleId);
        treeMap.put(type,String.valueOf(position));
        JSONObject jsonObject = CommonUtils.getRequestJsonObj(treeMap);
        LogUtil.e("__recommendClick_indexModuleId",indexModuleId + "__");
        LogUtil.e("__recommendClick_type",type + "__");
        LogUtil.e("__recommendClick_position",position + "__");
//        LogUtil.e("__recodeHomeVisit_userId",userId + "__");
        //用Json格式传给后台
        OkHttpUtils
                .postString()
                .content(jsonObject.toString())
                .url(IHttp.RECODE_USER_CLICK_HOME)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("__recommendClick","_onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtil.e("__recommendClick_onResponse",response.toString());
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONObject jsonObject = CommonUtils.decodeResult(response);
//                            LogUtil.e("__RECODE_USER_VISIT记录用户访问_onResponse_jsonObject" + "_" + visit_type, jsonObject.toString());
//                            int code = jsonObject.getInt("code");
                            //成功
//                            if (code == 10000) {
//                                LogUtil.e("__RECODE_USER_VISIT记录用户访问_onResponse_code == 10000" + "_" + visit_type,  "记录成功");
//                            } else {
//                                LogUtil.e("__RECODE_USER_VISIT记录用户访问_onResponse_code != 10000" + "_" + visit_type,  "记录失败");
//                            }
                        } catch (Exception e) {
//                            LogUtil.e("__RECODE_USER_VISIT记录用户访问_onResponse_code != 10000" + "_" + visit_type,  "记录解析异常：" + e.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取“其他”接口数据
     * @param callback
     */
    public void getOtherData(IHttpCallback callback){
        String data = "";
        String sign = "";
        Map<String,String> map = CommonUtils.getEmptyParam();
        data = map.get("data");
        sign = map.get("sign");
        RequestCall call = OkHttpUtils
                .get()
                .addParams("data",data)
                .addParams("sign",sign)
                .url(IHttp.OTHER_DATA)
                .build();
        super.enqueue(call,callback,HTTP_OTHER,null);
    }

    /**
     * 获取播放信息，比如播放地址
     * @param callback
     * @param videoId
     * @param userId
     * @param sessionid
     * @param epg
     */
    public void requestVideoData(IHttpCallback callback,String videoId,String userId,String sessionid,String epg){
        //一、升序排序的TreeMap；
        TreeMap<String, String> treeMap = new TreeMap<>();
        LogUtil.e("__requestVideoData__sessionid", sessionid + "__");
        LogUtil.e("__requestVideoData__epg222", epg + "__");
        LogUtil.e("__requestVideoData__videoId", videoId + "__");
        LogUtil.e("__requestVideoData__userId", userId + "__");
        treeMap.put("sessionid", sessionid);
        treeMap.put("epg", epg);
        treeMap.put("videoId", videoId);
        treeMap.put("userId", userId);
        String data = "";
        String sign = "";
        try {
            JSONObject jsonObject111 = CommonUtils.getRequestJsonObj(treeMap);
            data = jsonObject111.getString("data");
            sign = jsonObject111.getString("sign");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestCall call = OkHttpUtils
                .get()
                .addParams("data", data)
                .addParams("sign", sign)
                .url(IHttp.GET_VIDEO_DATA)
                .build();
        super.enqueue(call,callback,HTTP_GET_VIDEO_DATA,null);
    }

    /**
     * 获取首页导航栏数据
     * @param callback
     */
    public void getMainNavData(IHttpCallback callback){
        String data = "";
        String sign = "";
        Map<String,String> map = CommonUtils.getEmptyParam();
        data = map.get("data");
        sign = map.get("sign");
        RequestCall call = OkHttpUtils
                .get()
                .addParams("data",data)
                .addParams("sign",sign)
                .url(IHttp.MAIN_NAV_DATA)
                .build();
        super.enqueue(call,callback,HTTP_MAIN_NAV,null);
    }
    /**
     * 获取首页导航各个模块数据
     * @param callback
     */
    public void getMainModuleData(IHttpCallback callback){
        String data = "";
        String sign = "";
        Map<String,String> map = CommonUtils.getEmptyParam();
        data = map.get("data");
        sign = map.get("sign");
        RequestCall call = OkHttpUtils
                .get()
                .addParams("data",data)
                .addParams("sign",sign)
                .url(IHttp.MAIN_MODULE_DATA)
                .build();
        super.enqueue(call,callback,HTTP_MAIN_MODULE,null);
    }

    /**
     * 记录用户访问首页,没有数据返回，无需做处理
     * @param callback
     */
    public void recodeHomeVisit(IHttpCallback callback,String userId){
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("userId",userId);
        JSONObject jsonObject = CommonUtils.getRequestJsonObj(treeMap);
        //用Json格式传给后台
        RequestCall call = OkHttpUtils
                .postString()
                .content(jsonObject.toString())
                .url(IHttp.RECODE_USER_VISIT_HOME)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build();
        super.enqueue(call,callback,HTTP_RECODE_USER_VISIT_HOME,null);
    }

    public void getVerificationCode(String phone,IHttpCallback callback){
        LogUtil.e("__httphelper_getVerificationCode_phone",phone);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("timestamp", String.valueOf(CommonUtils.getCurrentTimeStampInt()));
        treeMap.put("phone", phone);
        Map<String, String> parameters = new TreeMap<>();
        parameters.putAll(treeMap);
        parameters.put("sign",CommonUtils.getSignPYString(treeMap));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url("http://test-xxtbpy.getlearn.cn/index.php/api/iptv/login/index")
                .build();
        super.enqueue(call,callback,HTTP_GET_VERIFICATION_CODE,null);
    }

    /**
     *
     * @param callback
     */
    public void getStart(IHttpCallback callback){
        String timestamp  = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSignPY(mapGetSign);
        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_START)
                .build();
        super.enqueue(call,callback,PY_HTTP_START, OhterModel1.class);
    }

    /**
     * 培优,获取课程列表
     * @param callback
     */
    public void getCourseList(IHttpCallback callback,String course_id,int limit,int page){
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("course_id",course_id);
        mapGetSign.put("limit",String.valueOf(limit));
        mapGetSign.put("page",String.valueOf(page));
        String sign = CommonUtils.getSignPY(mapGetSign);
        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("token",token);
        parameters.put("timestamp",timestamp);
        parameters.put("course_id",course_id);
        parameters.put("limit",String.valueOf(limit));
        parameters.put("page",String.valueOf(page));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_COURSE_LIST)
                .build();
        super.enqueue(call,callback,PY_HTTP_GET_COURSE_LIST, CourseListModel.class);
    }

    /**
     * 培优,获取播放地址
     * @param callback
     * @param video_id
     * @param isFree 是否免费，
     * @param what 课程详情界面，如果是其他界面，可以随便传
     */
    public void getVideoUrl(IHttpCallback callback, int video_id, int isFree, int what) {

        TreeMap mapGetSign = new TreeMap();
        Map<String, String> parameters = new TreeMap<>();
        if (UserManager.isSignTokenNULL()){
            String token = UserManager.getToken();
            parameters.put("token",token);
        }

        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        mapGetSign.put("timestamp", timestamp);
        mapGetSign.put("video_id", String.valueOf(video_id));
        mapGetSign.put("isFree", String.valueOf(isFree));
        String sign = CommonUtils.getSignPY(mapGetSign);
        LogUtil.e("sssssssssssssss", "sssssssssss");
        //请求参数
        parameters.put("sign", sign);
        parameters.put("timestamp", timestamp);
        parameters.put("video_id", String.valueOf(video_id));
        parameters.put("isFree", String.valueOf(isFree));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_GET_VIDEO_URL)
                .build();
        LogUtil.e("sssssssssss",IHttp.PY_GET_VIDEO_URL+parameters);
        //把视频的video_id放进去，方便在请求成功的时候，知道是在播放那个视频
        super.enqueue(call, callback, PY_HTTP_GET_VIDEO_URL, VideoUrlModel.class, what, video_id);

        LogUtil.e("sssssssss", IHttp.PY_GET_VIDEO_URL + parameters);
    }
    /**
     * 培优,获取用户个人信息
     * @param callback
     */
    public void getUserInfo(IHttpCallback callback){
        if(UserManager.isSignTokenNULL()){
            String token = UserManager.getToken();
            String timestamp = CommonUtils.getCurrentTimeStampString();
            //获取sign
            TreeMap mapGetSign = new TreeMap();
            mapGetSign.put("timestamp",timestamp);
            String sign = CommonUtils.getSignPY(mapGetSign);

            //请求参数
            Map<String, String> parameters = new TreeMap<>();
            parameters.put("sign",sign);
            parameters.put("token",token);
            parameters.put("timestamp",timestamp);
//            LogUtil.d("__getUserInfo","sign",String.valueOf(sign));
//            LogUtil.d("__getUserInfo","token",String.valueOf(token));
//            LogUtil.d("__getUserInfo","timestamp",String.valueOf(timestamp));
            RequestCall call = OkHttpUtils
                    .get()
                    .params(parameters)
                    .url(IHttp.PY_GET_USER_INFO)
                    .build();
            super.enqueue(call,callback,PY_HTTP_GET_USER_INFO, UserInfoModel.class);
        }else {
            ToastUtil.showToast("未登录");
        }

    }

    /**
     * 培优,添加收藏
     * @param callback
     */
    public void addCollect(IHttpCallback callback,int courseId){
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("courseId",courseId);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("token",token);
        parameters.put("timestamp",timestamp);
        parameters.put("courseId",String.valueOf(courseId));
        RequestCall call = OkHttpUtils
                .post()
                .params(parameters)
                .url(IHttp.PY_ADD_COLLECT)
                .build();
        super.enqueue(call,callback,PY_HTTP_ADD_COLLECT, CollectModel.class);
    }
    /**
     * 培优,删除收藏
     * @param callback
     */
    public void delCollect(IHttpCallback callback,int courseId){
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("courseId",courseId);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("token",token);
        parameters.put("timestamp",timestamp);
        parameters.put("courseId",String.valueOf(courseId));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
//        LogUtil.e("__delCollect","timestamp",timestamp);
//        LogUtil.e("__delCollect","courseId",String.valueOf(courseId));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_DEL_COLLECT)
                .build();
        super.enqueue(call,callback,PY_HTTP_DEL_COLLECT, CollectModel.class);
    }
    /**
     * 培优,搜索课程
     * @param callback
     * @param key 搜索关键词，不传则是拿热门数据
     * @param request_type 请求类型，热门搜索/关键词搜索
     */
    public void searchCourse(IHttpCallback callback,String key,int request_type,int limit){
//        int limit = 10000;//一次拿全部数据
//        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("key",key);
        mapGetSign.put("limit",String.valueOf(limit));
//        mapGetSign.put("page",String.valueOf(page));
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("key",key);
        parameters.put("timestamp",timestamp);
        parameters.put("limit",String.valueOf(limit));
//        parameters.put("page",String.valueOf(page));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
//        LogUtil.e("__delCollect","timestamp",timestamp);
//        LogUtil.e("__delCollect","courseId",String.valueOf(courseId));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_SEARCH_COURSE)
                .build();
        super.enqueue(call,callback, request_type, SearchModelPy.class);
    }


    /**
     * 培优,获取产品套餐
     * @param callback
     */
    public void getMeal(IHttpCallback callback){
        int limit = 10000;//一次拿全部数据
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("limit",String.valueOf(limit));
//        mapGetSign.put("page",String.valueOf(page));
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("token",token);
        parameters.put("timestamp",timestamp);
        parameters.put("limit",String.valueOf(limit));
//        parameters.put("page",String.valueOf(page));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
//        LogUtil.e("__delCollect","timestamp",timestamp);
//        LogUtil.e("__delCollect","courseId",String.valueOf(courseId));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_GET_MEAL)
                .build();
        super.enqueue(call,callback, PY_HTTP_GET_MEAL, MealModel.class);
    }

    /**
     * 培优,获取我的优惠券
     * @param callback
     * @param status 优惠券状态0未使用，1已使用，2已失效需要显示过期或其他状态优惠券
     */
    public void getMyCoupon(IHttpCallback callback,int status){
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("status",String.valueOf(status));//
//        mapGetSign.put("limit",String.valueOf(limit));
//        mapGetSign.put("page",String.valueOf(page));
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("token",token);
        parameters.put("timestamp",timestamp);
        parameters.put("status",String.valueOf(status));
//        parameters.put("page",String.valueOf(page));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
//        LogUtil.e("__delCollect","timestamp",timestamp);
//        LogUtil.e("__delCollect","courseId",String.valueOf(courseId));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_GET_MY_COUPON)
                .build();
        super.enqueue(call,callback, PY_HTTP_GET_MY_COUPON, CouponModel.class);
    }

    /**
     * 培优,根据套餐价格，获取合适的优惠券,此接口返回的优惠券集合，其中status等于0时，表示该优惠券不能用于当前套餐
     * 等于1时，表示该优惠券可以用于当前套餐
     * @param callback
     * @param price 	当前价格（如果是限时套餐要传限时价）
     * 没有合适的优惠券时，返回{"error":10021,"success":"success","status":"200","msg":"没有符合条件的优惠券","data":""}
     */
    public void getMyCouponListSortByPrice(IHttpCallback callback,String price,int what){
        int page = 1;
        int per_page = 10000;//拿全部数据
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("page",String.valueOf(page));
        mapGetSign.put("per_page",String.valueOf(per_page));
        mapGetSign.put("price",price);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("token",token);
        parameters.put("timestamp",timestamp);
        parameters.put("page",String.valueOf(page));
        parameters.put("per_page",String.valueOf(per_page));
        parameters.put("price",price);
//        LogUtil.e("__ssss","__getMyCouponListSortByPrice","sign",sign);
//        LogUtil.e("__ssss","__getMyCouponListSortByPrice","token",token);
//        LogUtil.e("__ssss","__getMyCouponListSortByPrice","timestamp",timestamp);
//        LogUtil.e("__ssss","__getMyCouponListSortByPrice","page",String.valueOf(page));
//        LogUtil.e("__ssss","__getMyCouponListSortByPrice","per_page",String.valueOf(per_page));
//        LogUtil.e("__ssss","__getMyCouponListSortByPrice","price",String.valueOf(price));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_GET_MY_COUPON_LIST_SORT_BY_PRICE)
                .build();
        super.enqueue(call,callback, PY_HTTP_GET_MY_COUPON_LIST_SORT_BY_PRICE, CouponByPriceModel.class,what);
    }

    /**
     * 培优,获取首页导航栏数据
     * @param callback
     */
    public void getMainNav(IHttpCallback callback){
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
//        parameters.put("status",String.valueOf(status));
//        parameters.put("page",String.valueOf(page));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
        LogUtil.e("__getMainNav","timestamp",timestamp);
        LogUtil.e("__getMainNav","sign",String.valueOf(sign));
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_MAIN_NAV)
                .build();
        super.enqueue(call,callback, PY_HTTP_GET_MAIN_NAV, null);
    }

    /**
     * 培优,获取首页导航栏数据(shou)
     * @param callback
     */
    public void getMainModule(IHttpCallback callback){
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
//        parameters.put("status",String.valueOf(status));
//        parameters.put("page",String.valueOf(page));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
        LogUtil.e("__getMainModule","timestamp",timestamp);
        LogUtil.e("__getMainModule","sign",sign);
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_MAIN_MODULE)
                .build();
        super.enqueue(call,callback, PY_HTTP_GET_MAIN_MODULE, null);
    }

    /**
     * 培优,检查课程是否禁用
     * @param courseId
     * @param callback
     */
    public void isCourseForbidden(String courseId,IHttpCallback callback){
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("courseId",courseId);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
        parameters.put("courseId",courseId);
//        parameters.put("status",String.valueOf(status));
//        parameters.put("page",String.valueOf(page));
//        LogUtil.e("__delCollect","sign",sign);
//        LogUtil.e("__delCollect","token",token);
//        LogUtil.e("__getMainModule","timestamp",timestamp);
//        LogUtil.e("__getMainModule","sign",sign);
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_IS_COURSE_FORBIDDEN)
                .build();
        super.enqueue(call,callback, PY_HTTP_COURSE_FORBIDDEN, ForbiddenModel.class);
    }


    /**
     * 培优,获取支付订单
     * @param callback
     * @param goods_id  套餐id
     * @param order_amount 订单金额
     * @param discount 是否限时特惠
     * @param coupon_id 优惠券id，有优惠券则传入优惠券id
     * @param u_gift_id 用户优惠券id，有优惠券则传入优惠券id
     * @param coupon_amount 优惠券金额
     * @param pay_type 0微信，1支付宝默认微信 100表示通过渠道支付（数据库里实际存100以上的渠道ID，渠道ID从101开始）
     * @param what 由套餐ID和优惠券ID拼接而来的数字，作为存放到二维码MAP中的KEY
     */
    public void getPayQrCode(IHttpCallback callback,String goods_id
                             ,String order_amount,String discount,String coupon_id
                            ,String u_gift_id,String coupon_amount,String pay_type,int what){
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        if (pay_type!="1"||pay_type!="0"){
            mapGetSign.put("channel","dangbei");
        }else {
//            mapGetSign.put("channel","dangbei");
        }
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("goods_id",goods_id);
        mapGetSign.put("order_amount",order_amount);
        mapGetSign.put("discount",discount);
        mapGetSign.put("coupon_id",coupon_id);
        mapGetSign.put("u_gift_id",u_gift_id);
        mapGetSign.put("coupon_amount",coupon_amount);
        mapGetSign.put("pay_type",pay_type+"");
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        if (pay_type!="1"||pay_type!="0"){
            parameters.put("channel","dangbei");
        }else{
//            parameters.put("channel","dangbei");
        }
        parameters.put("token",token);
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
        parameters.put("goods_id",goods_id);
        parameters.put("order_amount",order_amount);
        parameters.put("discount",discount);
        parameters.put("coupon_id",coupon_id);
        parameters.put("u_gift_id",u_gift_id);
        parameters.put("coupon_amount",coupon_amount);
        parameters.put("pay_type",pay_type);

        RequestCall call = OkHttpUtils
                .post()
                .params(parameters)
                .url(IHttp.PY_GET_PAY_QR_CODE)
                .build();
        LogUtil.e("__code二维码",IHttp.PY_GET_PAY_QR_CODE+parameters);
        super.enqueue(call,callback, PY_HTTP_GET_PAY_QR_CODE, QrCodeOrderModel.class,what);
    }

    /**
     * 培优,获取订单状态
     * @param callback
     * @param order_id
     */
    public void getOrderStatus(IHttpCallback callback,String order_id){
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("order_id",order_id);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("token",token);
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
        parameters.put("order_id",order_id);

//        LogUtil.e("__getOrderStatus","sign",sign);
//        LogUtil.e("__getOrderStatus","token",token);
//        LogUtil.e("__getOrderStatus","timestamp",timestamp);
        LogUtil.e("__getOrderStatus","order_id",order_id);
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_GET_ORDER_STATUS)
                .build();
//        super.enqueue(call,callback, PY_HTTP_GET_ORDER_STATUS, JSONObject.class,what);
        super.enqueue(call,callback, PY_HTTP_GET_ORDER_STATUS, OrderStatusModel.class);
    }

    /**
     * 培优,获取二维码
     * @param callback
     * @param payUrl
     */
    public void getQrcodeByUrl(IHttpCallbackWithHttpMsg callback,String payUrl,String type){
        LogUtil.e("__getQrcodeByUrl","url",payUrl);
        String token = UserManager.getToken();
        String timestamp = CommonUtils.getCurrentTimeStampString();
        //获取sign
        TreeMap mapGetSign = new TreeMap();
        mapGetSign.put("timestamp",timestamp);
        mapGetSign.put("url",payUrl);
        mapGetSign.put("type",type);
        String sign = CommonUtils.getSignPY(mapGetSign);

        //请求参数
        Map<String, String> parameters = new TreeMap<>();
        parameters.put("token",token);
        parameters.put("sign",sign);
        parameters.put("timestamp",timestamp);
        parameters.put("type",type);
        parameters.put("url",payUrl);

//        LogUtil.e("__getOrderStatus","sign",sign);
//        LogUtil.e("__getOrderStatus","token",token);
//        LogUtil.e("__getOrderStatus","timestamp",timestamp);
        LogUtil.e("__getQrcodeByUrl","token",token);
        LogUtil.e("__getQrcodeByUrl","sign",sign);
        LogUtil.e("__getQrcodeByUrl","timestamp",timestamp);
        LogUtil.e("__getQrcodeByUrl","url",payUrl);
        LogUtil.e("__getQrcodeByUrl","type",type);
        RequestCall call = OkHttpUtils
                .get()
                .params(parameters)
                .url(IHttp.PY_GET_QRCODE_BY_URL)
                .build();
//        super.enqueue(call,callback, PY_HTTP_GET_ORDER_STATUS, JSONObject.class,what);
        HttpMsg httpMsg = new HttpMsg();
        httpMsg.msg1 = payUrl;
        super.enqueueWithHttpMsg(call,callback,PY_HTTP_GET_QRCODE_BY_URL,PayQrCodeModel.class,httpMsg);
    }

}
