package com.td.network;

import android.text.TextUtils;

import com.td.network.entity.GetEntity;
import com.td.network.entity.PostEntity;
import com.td.network.util.SLogUtil;

import java.util.Map;

/**
 * Description : 网络请求管理类
 * Created by Mc on 2018/7/24.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public class SRequestManager {

    private static final String TAG = "http/https";

    private volatile static ISRequestManager mInstance;

    private static ISRequestManager getInstance() {
        if (mInstance == null) {
            synchronized (SRequestManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpRequestManager();
                }
            }
        }
        return mInstance;
    }

    @SuppressWarnings("unchecked")
    public static <T> void get(String url, Map<String, String> params, IRequestCallback<T> requestCallback) {
        get(url, null, params, requestCallback);
    }

    @SuppressWarnings("unchecked")
    public static <T> void get(String url, Map<String, String> heads, Map<String, String> params, IRequestCallback<T> requestCallback) {
        if (TextUtils.isEmpty(url)) {
            SLogUtil.e(TAG, "url is null");
            return;
        }
        GetEntity<T> getEntity = new GetEntity<>();
        getEntity.setUrl(url);
        if (heads != null) {
            getEntity.setHeads(heads);
        }
        if (params != null) {
            getEntity.setParams(params);
        }
        if (requestCallback != null) {
            getEntity.setRequestCallback(requestCallback);
        }
        getInstance().get(getEntity);
    }

    @SuppressWarnings("unchecked")
    public static <T> void post(String url, String requestBodyJson, IRequestCallback<T> requestCallback) {
        post(url, null, requestBodyJson, requestCallback);
    }

    @SuppressWarnings("unchecked")
    public static <T> void post(String url, Map<String, String> heads, String requestBodyJson, IRequestCallback<T> requestCallback) {
        if (TextUtils.isEmpty(url)) {
            SLogUtil.e(TAG, "url is null");
            return;
        }
        PostEntity<T> postEntity = new PostEntity<>();
        postEntity.setUrl(url);
        if (heads != null) {
            postEntity.setHeads(heads);
        }
        if (!TextUtils.isEmpty(requestBodyJson)) {
            postEntity.setContent(requestBodyJson);
        }
        if (requestCallback != null) {
            postEntity.setRequestCallback(requestCallback);
        }
        getInstance().post(postEntity);
    }

}
