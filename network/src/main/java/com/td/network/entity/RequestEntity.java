package com.td.network.entity;

/**
 * Description : 网络返回数据格式
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class RequestEntity<T> {

    private String message;

    private int code;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
