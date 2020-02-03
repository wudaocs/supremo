package com.td.network.entity;

import com.td.network.IRequestCallback;

import java.io.Serializable;
import java.util.Map;

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class PostEntity<T> implements Serializable {

    private String url;

    private Map<String, String> heads;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IRequestCallback<T> getRequestCallback() {
        return requestCallback;
    }

    public void setRequestCallback(IRequestCallback<T> requestCallback) {
        this.requestCallback = requestCallback;
    }
}
