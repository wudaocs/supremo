package com.td.network;

/**
 * Description : 网络回调接口
 * Created by Mc on 2018/7/24.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */
public interface IRequestCallback {

    void onSuccess(String response);

    void onFail(Throwable throwable);
}
