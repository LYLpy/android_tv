package com.geling.view.gelingtv_XX_tongbu.model;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/28------更新------
 * 域名和请求接口
 */

public interface

IHttp {

    //记录访问界面：0:活动页；1:index首页；3:coursedetail课程详情；6:pay是Vip订购页面,7:search搜索,8:collection收藏,9:history历史
    int VISIT_ACT = 0;
    int VISIT_INDEX = 1;
    int VISIT_COURSEDETAIL = 3;
    int VISIT_PAY = 6;
    int VISIT_SEARCH = 7;
    int VISIT_COLLECTION = 8;
    int VISIT_HISTORY = 9;


//    String BASE_URL = "http://test-xxtbpy.getlearn.cn/";//域名
    String BASE_uer1 = "http://test-xxtbpy.getlearn.cn/api/iptv/login/wytLogin";
//    String BASE_uer1 = " http://30.30.101.17:8751/index.php/api/iptv/login/wytLogin";

//    String BASE_URL = "http://test-xxtbpy.getlearn.cn/";//域名
    String BASE_URL = "http://newxxtbpy.getlearn.net/";//新的测试域名
//    String BASE_URL = "http://xxtbpy.getlearn.cn/";//正式域名

    //    =========================================App电视端=============================================================
    String LOGIN_VerificationCode= BASE_URL+"index.php/api/iptv/login/index";//用户验证码登陆-注册接口
    String LOGIN_DATA= BASE_URL+"index.php/api/iptv/login/index";//用户手机号登陆-返回数据
    String PERSONAL_INFORMATION_DATA= BASE_URL+"index.php/api/iptv/personal/getuserinfo";//用户手机号登陆-返回数据
    String PERSONAL_USEROUT_DATA= BASE_URL+"index.php/api/iptv/personal/loginout";//用户手机号登陆-返回数据
    String MY_ORDER_DATA= BASE_URL+"index.php/api/iptv/order/index";//我的订单
    String MY_COLLECTION_DATAS= BASE_URL+"index.php/api/iptv/Conllection/index";//我的收藏
    String MY_COLLECTION_TITLE_DATAS= BASE_URL+"index.php/api/iptv/Conllection/getConllectionSubject";//收藏科目
    String MY_EMPTY_COLLECTION_DATAS= BASE_URL+"index.php/api/iptv/Conllection/clearConllection";//清空收藏
    String MY_COUPON_DATAS= BASE_URL+"index.php/api/iptv/gift/myCouponList";//我的优惠卷
    String MY_EXCHANGE_DATAS= BASE_URL+"index.php/api/iptv/exchange_code/myExchangeLog";//历史兑换码
    String MY_SUBMIT_EXCHANGE_CODE_DATAS= BASE_URL+"index.php/api/iptv/exchange_code/exchange";//提交兑换码
    String GR_MODIFY_VERIFICATION_Code= BASE_URL+"index.php/api/iptv/personal/getVerificationCode";//个人信息--更换手机号--修改手机号的验证码
    String GR_MODIFY_SUBMISSION_PHONE= BASE_URL+"index.php/api/iptv/personal/updatePhone";//个人信息--更换手机号--修改提交要修改的手机号
    String MY_CODE_IMG= BASE_URL+"index.php/api/iptv/exchange_code/getexchangeqrcode";//兑换码--兑换二维码
    String FEED_BACK_URL = BASE_URL+"api/iptv/feed_back/getfeedbackurlqrcode";//获取客服反馈二维码


    String MAIN_NAV_DATA = BASE_URL + "index.php?s=/V300/Home/navData";//横向导航栏数据
    String MAIN_MODULE_DATA = BASE_URL + "index.php?s=/V300/Home/navModData";//首页各个模块数据
    String HISTORY_ALL_DATA = BASE_URL + "index.php?s=/V300/History/historyData";//播放历史，全部数据
    String HISTORY_PAGING_DATA = BASE_URL + "index.php?s=/V300/History/historyDataSlowLoading";//播放历史，分页获取，从1开始
    String MY_COLLECTION_DATA = BASE_URL + "index.php?s=/V300/Collection/collectionData";//我的收藏
    String COURSE_DETAIL = BASE_URL + "index.php?s=/V300/CourseDetail/courseDetailData";//课程详情
    String SEARCH_HOT = BASE_URL + "index.php?s=/V300/Search/searchHotData";//搜索界面热点数据
    String SEARCH_DATA = BASE_URL + "index.php?s=/V300/Search/searchKeyData";//搜索界面，关键词搜索
    String EXIT_PAGE = BASE_URL + "index.php?s=/V300/Home/exitPageData";//退出页面数据
    String OTHER_DATA = BASE_URL + "index.php?s=/V300/Home/otherData";//其他数据，比如闪屏页和首页logo数据
    String CLEAR_COLLECTION = BASE_URL + "index.php?s=/V300/Collection/collectionClear";// 清空收藏
    String COLLECTION_TOGGLE = BASE_URL + "index.php?s=/V300/CourseDetail/collectionToggle";// 收藏/取消收藏
    String IS_COURSE_FORBIDDEN = BASE_URL + "index.php?s=/V300/CourseDetail/isShowAndPop";// 检查课程是否禁用
    String GET_VIDEO_DATA = BASE_URL + "index.php?s=/V300/JxgdVideo/getVideoData";//获取视频播放地址
    String RECODE_USER_VISIT = BASE_URL + "index.php?s=/V300/Personas/recordUVPV";//记录用户访问哪个界面，返回数据无需处理
    String RECODE_USER_VISIT_HOME = BASE_URL + "index.php?s=/V300/Personas/recordUserHomeVisit";//记录用户访问首页
    String RECODE_USER_CLICK_HOME = BASE_URL + "index.php?s=/V300/Home/featuredAdd";//记录用户点击首页推荐位的哪个位置
    String GET_QR_CODE = BASE_URL + "index.php?s=/V300/JxgdQRCode/getQRCodeUrl";//获取支付二维码接口
    String GET_PRODUCT_PACK_LIST = BASE_URL + "index.php?s=/V300/JxgdQRCode/getProductPakcList";//获取产品包
        String CHECK_ORDER = BASE_URL + "index.php?s=/V300/JxgdQRCode/getQRCodeStatus";//查询二维码订单状态
    String IS_VIP_OFFLINE = BASE_URL + "index.php?s=/V300/JxgdOss/jxgdAuth";//线下鉴权
    String IS_VIP_ONLINE = BASE_URL + "index.php?s=/V300/JxgdOss/gljyAuth";//线上鉴权
    String IS_VIP_OFFLINE_BY_VIDEO_ID = BASE_URL + "index.php?s=/V300/JxgdOss/jxgdAuthByVideoId";//线下鉴权，带VideoId


    //    =========================================以前tv后台的接口=============================================================
    String BASE_URL_JXGD = "http://10.60.33.178/";
//    String BASE_URL_JXGD = "http://192.168.3.251/";
    String REQUEST_RECOMMEND = BASE_URL_JXGD + "index.php?s=/Api/index/typography_v12";// 请求推荐数据
    String GET_RTSP = BASE_URL_JXGD + "index.php?s=/Api/Videourl/searchcode";// 请求地址数据
//    String GET_RTSP = BASE_URL_JXGD + "index.php?s=/Api/Try/index";// 请求地址数据


//    ============================同步培优TV端接口=================================

    String PY_START = BASE_URL + "api/iptv/index/start";//启动页
    String PY_COURSE_LIST = BASE_URL + "api/iptv/curriculum/getList";//课程列表
//    String PY_GET_VIDEO_URL = BASE_URL + "api/curriculum/getplayurl";//获取播放地址 app的接口
    String PY_GET_VIDEO_URL = BASE_URL + "api/iptv/curriculum/getplayurl";//获取播放地址
    String PY_GET_USER_INFO = BASE_URL + "api/iptv/personal/getuserinfo";//获取用户个人信息
    String PY_ADD_COLLECT = BASE_URL + "api/iptv/Conllection/addConllection";//添加收藏
    String PY_DEL_COLLECT = BASE_URL + "api/iptv/Conllection/delConllection";//删除收藏
    String PY_SEARCH_COURSE = BASE_URL + "api/iptv/search/searchKeyData";//搜索
    String PY_GET_MEAL = BASE_URL + "index.php/api/iptv/app_set_meal/getmeal";//获取产品套餐
    String PY_GET_MY_COUPON = BASE_URL + "index.php/api/coupon/index";//获取我的优惠券
    String PY_GET_MY_COUPON_LIST_SORT_BY_PRICE = BASE_URL + "index.php/api/iptv/gift/myCouponListSortByPrice";//根据套餐价格获取我的优惠券
    String START = BASE_URL+"index.php/api/iptv/index/start";//启动页面
    String PY_MAIN_NAV = BASE_URL+"api/iptv/Home/getNavData";//首页横向导航栏
    String PY_MAIN_MODULE = BASE_URL+"api/iptv/Home/getNavCourseData";//首页各个模块数据
    String PY_IS_COURSE_FORBIDDEN = BASE_URL+"api/iptv/Home/getCourseStatus";//检查课程是否禁用
    String PY_GET_PAY_QR_CODE = BASE_URL+"api/iptv/order/getQRCodeUrl";//获取支付订单
    String PY_GET_ORDER_STATUS = BASE_URL+"index.php/api/iptv/order/getOrderStatus";//查询支付结果
    String PY_GET_PERSONAL = BASE_URL+"index.php/api/iptv/personal/index";//我的首页信息
    String PY_GET_QRCODE_BY_URL = BASE_URL+"index.php/api/iptv/order/getPayUrlQrCode";//通过URL获取二维码接口
    String MY_ClASS_LIST = BASE_URL + "index.php/api/iptv/study/myclasslist";//播放记录
    String USER_PROTOCOL = BASE_URL + "api/iptv/protocol/protocol";//用户协议
    String USER_Secrecy = BASE_URL + "api/iptv/protocol/userSecrecy";//保密协议
 }
