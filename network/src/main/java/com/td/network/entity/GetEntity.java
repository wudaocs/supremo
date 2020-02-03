package com.td.network.entity;

import com.td.network.IRequestCallback;

import java.io.Serializable;
import java.util.Map;

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class GetEntity<T> implements Serializable {

    private String url;

    private Map<String, String> heads;

    private Map<String, String> params;

    private IRequestCallback<T> requestCallback;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeads() {
        return heads;
    }

    public void setHeads(Map<String, String> heads) {
        this.heads = heads;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public IRequestCallback<T> getRequestCallback() {
        return requestCallback;
    }

    public void setRequestCallback(IRequestCallback<T> requestCallback) {
        this.requestCallback = requestCallback;
    }
}
