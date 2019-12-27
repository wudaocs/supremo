package com.td.network;

import com.td.network.entity.RequestEntity;

/**
 * Description : 网络回调接口
 * Created by Mc on 2018/7/24.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public interface IRequestCallback<T> {

    void onSuccess(T data);

    void onFail(RequestEntity requestEntity);
}
