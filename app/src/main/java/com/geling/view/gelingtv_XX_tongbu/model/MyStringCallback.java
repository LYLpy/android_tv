package com.geling.view.gelingtv_XX_tongbu.model;

import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/27------更新------
 */

public abstract class MyStringCallback extends Callback<String> {
    @Override
    public boolean validateReponse(Response response, int id) {
        return true;
    }

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        try {
            LogUtil.e("__MyStringCallback_headers",response.headers().toString());
            LogUtil.e("__MyStringCallback_message",response.message());
            LogUtil.e("__MyStringCallback_toString",response.toString());
            LogUtil.e("__MyStringCallback_body().string",response.body().string());
            LogUtil.e("__MyStringCallback_code",response.code() + "__");
        }catch (Exception e){
            LogUtil.e("__MyStringCallback_log_Exception:" + e.getMessage());
        }
        if(response.code()>=200 && response.code()<300){
            return response.body().string();
        }else{
            throw new Exception("code is:"+response.code()+"\n"+response.body().string());
        }
    }
}
