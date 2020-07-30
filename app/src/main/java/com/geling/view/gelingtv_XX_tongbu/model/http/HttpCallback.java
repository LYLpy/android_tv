package com.geling.view.gelingtv_XX_tongbu.model.http;

/**
 * Created by zlw on 2019/9/18.
 */

public interface HttpCallback {
    void onSuccess(String json);

    void onFailed(String json);
}
