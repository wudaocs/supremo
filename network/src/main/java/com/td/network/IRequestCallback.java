package com.td.network;

import com.td.network.entity.RequestEntity;

/**
 * Description : 网络回调接口
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public interface IRequestCallback<T> {

    void onSuccess(T data);

    void onFail(RequestEntity requestEntity);
}
