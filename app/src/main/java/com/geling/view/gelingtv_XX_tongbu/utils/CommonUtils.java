package com.geling.view.gelingtv_XX_tongbu.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateFormat;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/5------更新------
 * 公共工具类，常用的方法放这里
 */

public class CommonUtils {

    /**
     * 获取签名值
     * @param map
     * @return
     */
    /**
     * 将参数集合升序排序并且获取签名
     * @param map
     * @return
     */
    public static String getSign(TreeMap<String,Object> map){
        StringBuilder signBuilder = new StringBuilder();
        Iterator it = map.keySet().iterator();
        LogUtil.e("__用户",map+"");
        while (it.hasNext()) {
            String temp = it.next().toString();
            //将参数的值通过“，”拼接，
            if (map.get(temp) != null && !map.get(temp).equals("") && !map.get(temp).toString().equals("0")){
                if (!signBuilder.toString().equals("")){
                    signBuilder.append(",");
                }
                signBuilder.append(map.get(temp));
            }
        }
        signBuilder.append(APP_KEY_2);
//        LogUtil.e("__getSign",signBuilder.toString());
        String md5 = md5(signBuilder.toString()).toUpperCase();
//        LogUtil.e("__md5_up",md5);
        return md5;
    }

    /**
     * 获取时间戳String
     * @return
     */
    public static String getTimeStampStr(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static int getTimeStampInt(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
//            LogUtil.e("__getTimeStampInt",String.valueOf(Integer.valueOf(timestamp.substring(0,length-3))));
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
    /**
     * 需要的系统时间格式，如"hh:mm:ss"
     * @param format
     * @return
     */
    public static String getSysTime(String format) {
        String sysTimeStr = "";
        try {
            long sysTime = System.currentTimeMillis();//获取系统时间
            sysTimeStr = String.valueOf(DateFormat.format(format, sysTime));//时间显示格式
        } catch (Exception e) {

        }
        return sysTimeStr;
    }
    /**
     * 获取当前时间戳int
     * @return
     */
    public static int getCurrentTimeStampInt(){
        return getTimeStampInt(new Date());
    }

    /**
     * 获取当前时间戳String
     * @return
     */
    public static String getCurrentTimeStampString(){
        return String.valueOf(getCurrentTimeStampInt());
    }

    /**
     * 获取MD5值
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        LogUtil.e("__getSign_md5",string);
        String result = string;
        try {
            if(string != null) {
                MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
                md.update(string.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
                while(result.length() < 32) { //31位string
                    result = "0" + result;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * 获取MD5值
     * @param string
     * @return
     */
    public static String md5BackUp(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
//            LogUtil.e("__md5",result);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static final String APP_KEY_2 = "HN5zcglmXSHXaQZEImVwBuYvipFKEHUf";//之前TV项目用到加密解密用的秘钥

    public static JSONObject decodeResult(String response) {
        LogUtil.e("__deCodeResult_Exception",response + "__");
        JSONObject jsonObject;
        try {
            JSONObject jsonObject1111 = new JSONObject(response);
            String data = jsonObject1111.getString("data");
            LogUtil.e("__deCodeResult_Exception_data",data + "__");
            String paramMap = Base64Util.decodeStr(data);
            LogUtil.e("__deCodeResult_Exception_paramMap",paramMap + "__");
            jsonObject = new JSONObject(paramMap);
        }catch (Exception e){
            LogUtil.e("__deCodeResult_Exception",e.getMessage() + "");
            jsonObject = null;
        }
        return jsonObject;
    }
    private final String secret_key = "0000fb6d204147aehel04df502187e9c";

    /**
     * @param parms 将需要的参数放进来，会转化成请求所需要的data和sign;
     * @return
     */
    public static JSONObject getRequestJsonObj(TreeMap<String,String> parms) {
//        LogUtil.e("__getRequestContent_",response + "__");
        //一、将map变成json；
        JSONObject json =new JSONObject(parms);
        JSONObject jsonObject = new JSONObject();
        try {
            LogUtil.e("__getRequestJsonObj__data",json.toString() + "__");
            StringBuilder builder = new StringBuilder();
            int count = 0;
            builder.append("{");
            //遍历集合,按照顺序将数据添加到Json里面
            Iterator<String> io = parms.keySet().iterator();
            while(io.hasNext()){
                if (count != 0){
                    builder.append(",");
                }
                String key = io.next();
                builder.append("\"");
                builder.append(key);
                builder.append("\"");

                builder.append(":");

                String value= parms.get(key);
                builder.append("\"");
                builder.append(value);
                builder.append("\"");
                json.put(key, value);
                LogUtil.e("__getRequestJsonObj__Iterator",key + ":" +value + "__");
                count ++;
            }
            builder.append("}");
//            String dataUtr_8 = new String(builder.toString().getBytes("UTF-8"), "UTF-8");
//            String dataUtf_8 = URLDecoder.decode(builder.toString(), "UTF-8");  ;
//            String data = Base64Util.encodeStr(dataUtf_8);
            String data = Base64Util.encodeStr(builder.toString());
//            String data = new String(Base64.encodeBase64Chunked(builder.toString().getBytes()));
            LogUtil.e("__getRequestJsonObj__d_stringB",builder.toString() + "__");
//            LogUtil.e("__getRequestJsonObj__data_stringB_utf-8",dataUtf_8 + "__");
//            LogUtil.e("__getRequestJsonObj__data_stringB_utf-8",builder.toString().get + "__");
            LogUtil.e("__getRequestJsonObj__d_data",data);
            LogUtil.e("__getRequestJsonObj__d_data_KEY",data + APP_KEY_2);
            //通过map获取sign
            String sign = CommonUtils.md5(data + APP_KEY_2);
//            LogUtil.e("__getRequestJsonObj__data",data + "__");
            LogUtil.e("__getRequestJsonObj__sign",sign + "__");
            jsonObject.put("data",data);
            jsonObject.put("sign",sign);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("__getRequestJsonObj__sign",e.getMessage() + "__");
        }
        return jsonObject;
    }

    public static JSONObject getRequestJsonObjBackUp(TreeMap<String,String> parms) {
//        LogUtil.e("__getRequestContent_",response + "__");
        //一、将map变成json；
        JSONObject json =new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            //遍历集合,按照顺序将数据添加到Json里面
            Iterator<String> io = parms.keySet().iterator();
            while(io.hasNext()){
                String key = io.next();
                String value= parms.get(key);
                json.put(key, value);
                LogUtil.e("__getRequestJsonObj__Iterator",key + ":" +value + "__");
            }
            LogUtil.e("__getRequestJsonObj__data",json.toString() + "__");
            String data = Base64Util.encodeStr(json.toString());
            //通过map获取sign
            String sign = CommonUtils.md5(data + APP_KEY_2);
            LogUtil.e("__getRequestJsonObj__data",data + "__");
            LogUtil.e("__getRequestJsonObj__sign",sign + "__");
            jsonObject.put("sign",sign);
            jsonObject.put("data",data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * @param name 姓名
     * @param password 密码
     * @param parm   测试参数
     * @param sex      性别
     * @return
     */
    public static String getStudyIndexSign(String name, String password, int parm, int sex, int timestamp){
        TreeMap<String,Object> map = new TreeMap<>();
        map.put("name",name);
        map.put("password",password);
        map.put("sex",sex);//性别
        map.put("timestamp",timestamp);//时间戳
        map.put("parm",parm);
        return getSign(map);
    }

    /**
     * 通过两个date判断是否同一天
     * @param date
     * @param sameDate
     * @return
     */
    public static boolean isSameDay(Date date, Date sameDate) {
        if (null == date || null == sameDate) {
            return false;
        }
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(sameDate);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DATE) == dateCalendar.get(Calendar.DATE)) {
            return true;
        }
        return false;
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 判断当前线程是否是主线程
     * @return true表示当前是在主线程中运行
     */
    public static boolean isMainThread() {
        // 主线程的Looper == 当前线程的Looper
        return Looper.getMainLooper() == Looper.myLooper();
    }
    /** 在主线程运行 */
    public static void runOnMainThread(Runnable run) {
        if (isMainThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }
    public static Handler getMainHandler() {
        return mHandler;
    }

    /**
     * 检查是否有网络
     * @param context
     * @return
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo netInfo = connManager.getActiveNetworkInfo();
            if (netInfo != null){
                return netInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 时间戳转字符串
     * @param milSecond 时间戳
     * @param pattern 日期格式，如"yyyy年MM月dd日 HH:mm:ss"
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    /**
     * 将毫秒转时分秒
     *
     * @param time
     * @return
     */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02dh%02dm%02ds", hours, minutes, seconds) : String.format("%02dm%02ds", minutes, seconds);
    }

    /**
     * 进行UrlEncode
     * @param paramString
     * @return
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            LogUtil.e("toURLEncoded error:" + paramString);
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }catch (Exception localException) {
            LogUtil.e("toURLEncoded error:"+paramString, localException.getMessage());
        }
        return "";
    }

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }

    /*注：上面所说的key指的是清单文件中你在注册友盟统计时的"UMENG_CHANNEL"（即getChannelNumber(Context context, String key)方法的第二个参数key传入"UMENG_CHANNEL"），而不是"UMENG_APPKEY"，也不是任何一个value值
            <!-- 友盟统计 -->
    <meta-data
    android:name="UMENG_APPKEY"
    android:value="*****************" />
    <meta-data
    android:name="UMENG_CHANNEL"
    android:value="${UMENG_CHANNEL_VALUE}" />*/
    /**
     * 在需要的地方调用上述方法
     * String channelNumber = getAppMetaData(getBaseContext(), "UMENG_CHANNEL");//获取app当前的渠道号
     */

    /**
     * 将String格式的时间转换成Date
     * @param strTime 时间
     * @param formatType 格式,如yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date stringToDate(String strTime, String formatType){
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        }catch (Exception e){
        }
        return date;
    }

    /**
     * 转utf-8格式
     * @param string
     * @return
     */
    public static String toUtf8(String string){
        String result = "";
        try {
            result = new String(string.getBytes("UTF-8"),"UTF-8");
        }catch (Exception e){
            LogUtil.e("__toUtr8_Exception",e.getMessage() + "__");
        }
        return result;
    }

    /**
     * 使用 SAX 方式解析 XML
     * @param result
     */
    public static void parseXMLWithSAX(String result,DefaultHandler handler) {
        try {
            LogUtil.e("__parseXMLWithSAX",result + "__");
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader reader=factory.newSAXParser().getXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(new StringReader(result)));
        } catch (SAXException e) {
            LogUtil.e("__parseXMLWithSAX_SAXException",e.getMessage() + "__");
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            LogUtil.e("__parseXMLWithSAX_ParserConfigurationException",e.getMessage() + "__");
            e.printStackTrace();
        } catch (IOException e) {
            LogUtil.e("__parseXMLWithSAX_IOException",e.getMessage() + "__");
            e.printStackTrace();
        }
    }

    /**
     * 当不需要传参数的时候，使用此方法获取sign和data
     * @return
     */
    public static Map getEmptyParam(){
        Map<String,String> result = new HashMap<>();
        String parm = "[]";
        String data = Base64Util.encodeStr(parm);
        String sign = md5(CommonUtils.APP_KEY_2);
        result.put("data",data);
        result.put("sign",sign);
        return result;
    }

    public static String PY_KEY = "b6c3b6e7e6c7b909";
    /**
     * 格灵同步培优APP获取签名方法，token不参与签名运算
     * 将参数集合升序排序并且获取签名
     * @param map
     * @return
     */
    public static String getSignPY(TreeMap<String,Object> map){
        StringBuilder signBuilder = new StringBuilder();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            //it.next()得到的是key，tm.get(key)得到obj
//            System.out.println(map.get(it.next()));
            String temp = it.next().toString();
            //map.get(temp).equals("0")与后台签名算法保持一致，值为0不参与签名运算
            if (map.get(temp) != null && !map.get(temp).equals("") && !map.get(temp).toString().equals("0")){
                signBuilder.append(temp);
                signBuilder.append("=");
                signBuilder.append(map.get(temp));
                signBuilder.append("&");
                //LogUtil.e("__getSign",signBuilder+"");
            }
        }
        signBuilder.append("key=");
        signBuilder.append(PY_KEY);
       // LogUtil.e("__getSign",signBuilder.toString());
        String md5 = md5(signBuilder.toString()).toUpperCase();
        LogUtil.e("__md5_up",md5);
        return md5;
    }

    public static String getSignPYString(Map<String,String> map){
        StringBuilder signBuilder = new StringBuilder();
        LogUtil.e("__用户计算签名的参数",map+"");
         int i =0;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            //it.next()得到的是key，tm.get(key)得到obj
//            System.out.println(map.get(it.next()));
            String temp = it.next().toString();
            LogUtil.e("__temp",temp);

            //map.get(temp).equals("0")与后台签名算法保持一致，值为0不参与签名运算
            if (map.get(temp) != null && !map.get(temp).equals("") && !map.get(temp).toString().equals("0")){
                i++;
                signBuilder.append(temp);
                signBuilder.append("=");
                signBuilder.append(map.get(temp));
                signBuilder.append("&");
                LogUtil.e("__getSign1",signBuilder+"");
                LogUtil.e("__getSign1","i="+i);
            }
        }
        signBuilder.append("key=");
        signBuilder.append(PY_KEY);
        LogUtil.e("__getSign",signBuilder.toString());
        String md5 = md5(signBuilder.toString()).toUpperCase();
        LogUtil.e("__getSign_md5_up",md5);
        return md5;
    }
}
